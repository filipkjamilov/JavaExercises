import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CalciumNitride {


    public static Semaphore calcium;
    static Semaphore nitrogen;
    static Lock calciumHere, caIsDone;
    static Lock nitrogenHere, nIsDone;
    static int ca, caDone;
    static int n, nDone;
    static Semaphore caReady;
    static Semaphore nReady;
    static Semaphore bondingDone;
    static Semaphore waitToValidate;


    public static void init() {
        calcium = new Semaphore(3);
        nitrogen = new Semaphore(2);
        calciumHere = new ReentrantLock();
        caIsDone = new ReentrantLock();
        caReady = new Semaphore(0);
        nitrogenHere = new ReentrantLock();
        nIsDone = new ReentrantLock();
        nReady = new Semaphore(0);
        bondingDone = new Semaphore(0);
        waitToValidate = new Semaphore(0);

        ca = 0;
        n = 0;
    }

    public static class Calcium extends TemplateThread {

        public Calcium(int numRuns) {
            super(numRuns);
        }

        @Override
        public void execute() throws InterruptedException {

            calcium.acquire();

            calciumHere.lock();
            ca++;
            if(ca==3) {
                caReady.release(2);
                ca = 0;
            }
            calciumHere.unlock();

            nReady.acquire();
            state.bond();

            caIsDone.lock();
            caDone++;
            if(caDone==3) {
                bondingDone.acquire();
                state.validate();
                caDone = 0;
                waitToValidate.release(5);
            }
            caIsDone.unlock();


            waitToValidate.acquire();

            calcium.release();
        }
    }

    public static class Nitrogen extends TemplateThread {

        public Nitrogen(int numRuns) {
            super(numRuns);
        }

        @Override
        public void execute() throws InterruptedException {


            nitrogen.acquire();

            nitrogenHere.lock();
            n++;
            if(n==2) {
                nReady.release(3);
                n = 0;
            }
            nitrogenHere.unlock();

            caReady.acquire();
            state.bond();

            nIsDone.lock();
            nDone++;
            if(nDone==2) {
                nDone = 0;
                bondingDone.release();
            }
            nIsDone.unlock();

            waitToValidate.acquire();
            nitrogen.release();
        }
    }
    static CalciumNitrideState state = new CalciumNitrideState();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            run();
        }
    }

    public static void run() {
        try {
            Scanner s = new Scanner(System.in);
            int numRuns = 1;
            int numIterations = 100;
            s.close();

            HashSet<Thread> threads = new HashSet<Thread>();

            for (int i = 0; i < numIterations; i++) {
                Nitrogen n = new Nitrogen(numRuns);
                threads.add(n);
                Calcium ca = new Calcium(numRuns);
                threads.add(ca);
                ca = new Calcium(numRuns);
                threads.add(ca);
                n = new Nitrogen(numRuns);
                threads.add(n);
                ca = new Calcium(numRuns);
                threads.add(ca);
            }

            init();

            ProblemExecution.start(threads, state);
            System.out.println(new Date().getTime());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}


class CalciumNitrideState extends AbstractState {

    private static final String BONDING_NOT_PARALLEL = "The bonding is not done in parallel!";
    private static final String MOLECULE_NOT_BOUNDED_COMPLITELY = "The previous molecule has not bonded completely.";
    private static final String MAXIMUM_3_CALCIUM = "Maximum 3 Calcium atoms for bonding are allowed.";
    private static final String MAXIMUM_2_NITROGEN = "Maximum 2 Nitrogen atoms for bonding are allowed.";
    private static final int MAXIMUM_2_NITROGEN_POINTS = 5;
    private static final int MAXIMUM_3_CALCIUM_POINTS = 5;
    private static final int MOLECULE_NOT_BOUNDED_COMPLITELY_POINTS = 10;
    private static final int BONDING_NOT_PARALLEL_POINTS = 5;

    int numAtoms = 0;
    private BoundCounterWithRaceConditionCheck Ca;
    private BoundCounterWithRaceConditionCheck N;

    public CalciumNitrideState() {
        Ca = new BoundCounterWithRaceConditionCheck(0, 3,
                MAXIMUM_3_CALCIUM_POINTS, MAXIMUM_3_CALCIUM, null, 0, null);
        N = new BoundCounterWithRaceConditionCheck(0, 3,
                MAXIMUM_2_NITROGEN_POINTS, MAXIMUM_2_NITROGEN, null, 0, null);
    }

    public void bond() {
        synchronized (this) {
            numAtoms++;
        }
        Switcher.forceSwitch(3);
        if (getThread() instanceof CalciumNitride.Calcium) {
            log(Ca.incrementWithMax(false), "Calcium bonding");
        } else if (getThread() instanceof CalciumNitride.Nitrogen) {
            log(N.incrementWithMax(false), "Nitrogen bonding");
        }
    }

    public void validate() {
        synchronized (this) {
            if (numAtoms == 5) {
                if (Ca.getValue() == 3 && N.getValue() == 2) {
                    reset();
                    log(null, "Ca3N3 molecule is formed.");
                } else {
                    log(new PointsException(
                            MOLECULE_NOT_BOUNDED_COMPLITELY_POINTS,
                            MOLECULE_NOT_BOUNDED_COMPLITELY), null);

                }
            }
        }
    }

    private synchronized void reset() {
        Ca.setValue(0);
        N.setValue(0);
        numAtoms = 0;
    }

    @Override
    public synchronized void finalize() {
        if (Ca.getMax() == 1 && N.getMax() == 1) {
            logException(new PointsException(BONDING_NOT_PARALLEL_POINTS,
                    BONDING_NOT_PARALLEL));
        }
    }

}