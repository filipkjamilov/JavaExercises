import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;
    public SLLNode(E element, SLLNode<E> succ) {
        this.element = element;
        this.succ = succ;
    }
}

class Queue<E> {
    private SLLNode<E> front, rear;
    private int length;
    public Queue() {
        front = null;
        rear = null;
        length = 0;
    }
    public boolean isEmpty() {
        return length == 0;
    }
    public int size() {
        return length;
    }
    public E peek() {
        if(length > 0)
            return front.element;
        else
            System.out.println("nema elementi vo stekot");
        return null;
    }
    public void enque(E x) {
        SLLNode<E> tmp = new SLLNode(x, null);
        if(length > 0){
            rear.succ = tmp;
            rear = tmp;
        } else
            front = rear = tmp;
        length++;
    }
    public E deque() {
        if(length > 0){
            E tmp = front.element;
            front = front.succ;
            length--;
            return tmp;
        } else
            System.out.println("nema elementi vo stekot");
        return null;

    }
    @Override
    public String toString() {
        String s = new String();
        SLLNode<E> node = front;
        while(node != null){
            s += node.element + "\n";
            node = node.succ;
        }
        return s;
    }
}
class chovek{
    private String imePrezime;
    private int lichna,pasosh,vozachka;

    public chovek(String imePrezime, int lichna, int pasosh, int vozachka) {
        this.imePrezime = imePrezime;
        this.lichna = lichna;
        this.pasosh = pasosh;
        this.vozachka = vozachka;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public int getLichna() {
        return lichna;
    }

    public int getPasosh() {
        return pasosh;
    }

    public int getVozachka() {
        return vozachka;
    }
}


public class MVR {
    public static void proveri(chovek ljuge[]){
        Queue<chovek> redica1=new Queue<chovek>();
        Queue<chovek> redica2=new Queue<chovek>();
        Queue<chovek> redica3=new Queue<chovek>();

        for (int i=0;i<ljuge.length;i++){
            if (ljuge[i].getLichna()==1){
                redica1.enque(ljuge[i]);
            }
            else if(ljuge[i].getPasosh()==1){
                redica2.enque(ljuge[i]);
            }
            else if(ljuge[i].getVozachka()==1){
                redica3.enque(ljuge[i]);
            }
        }
        //izminavum prva redica i gu pituvum sekoj pud red za deka na drugo mesto ima da ode/na majkamu gzut
        while (!redica1.isEmpty()){

            if(redica1.peek().getPasosh()==1){
                redica2.enque(redica1.deque());
            }
            else if(redica1.peek().getVozachka()==1){
                redica3.enque(redica1.deque());
            }
            else {
                chovek ice=redica1.deque(); //odi si doma
                System.out.println(ice.getImePrezime());
            }
        }

        while (!redica2.isEmpty()){
            if(redica2.peek().getVozachka()==1){
                redica3.enque(redica2.deque());
            }
            else {
                chovek pero=redica2.deque();
                System.out.println(pero.getImePrezime());
            }
        }
        while (!redica3.isEmpty()){

            chovek zoki=redica3.deque();
            System.out.println(zoki.getImePrezime());
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader cin=new BufferedReader(new InputStreamReader(System.in));

        String s=cin.readLine();
        int kolku=Integer.parseInt(s);
        chovek []pomniza=new chovek[kolku];



        for (int i=0;i<pomniza.length;i++){
            String ime=cin.readLine();
            int lichnaK=Integer.parseInt(cin.readLine());
            int pasosh=Integer.parseInt(cin.readLine());
            int vdozola=Integer.parseInt(cin.readLine());

            pomniza[i]=new chovek(ime,lichnaK,pasosh,vdozola);
        }
        proveri(pomniza);

    }
}