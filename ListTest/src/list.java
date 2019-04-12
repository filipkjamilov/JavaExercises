import javax.swing.plaf.SliderUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;
    public SLLNode (E elem,
                    SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }
}
class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
        // kreiranje prazna lista
        this.first = null;
    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new
                SLLNode<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o,
                            SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new
                    SLLNode<E>(o, node.succ);
            node.succ = ins;
        }
        // else throw Exception
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            SLLNode<E> ins = new SLLNode<E>(o, null);
            tmp.succ = ins;
        } else {
            insertFirst(o);
        }
    }
    public void delete(SLLNode<E> node){
        if (first != null){
            SLLNode<E> tmp = first;
            while (tmp.succ != node &&
                    tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node){
                tmp.succ = tmp.succ.succ;
            }
        }

    }


    @Override
    public String toString() {
        return "SLL{" +
                "first=" + first.element +
                '}';
    }

    public void print() {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp != null) {
                System.out.print(tmp.element+" ");
                tmp = tmp.succ;

            }

        }else System.out.print("Listata e prazna");
    }

    public SLL<E> sort(SLL<E> l2){
        SLL<E> l1 = this;

        SLLNode<E> t1 = l1.getFirst();
        SLLNode<E> t2 = l2.getFirst();

        SLL<E> pom = new SLL<E>();

        SLLNode<E> tt = l1.getFirst().succ;

        while(t1!=null){
            if(tt==null){break;}
            else if(t1.element==tt.element){
                l1.delete(tt);
                tt=tt.succ;
            }else{
                t1=t1.succ;
                tt=tt.succ;
            }
        }
        t1 = l1.getFirst();


        tt = l2.getFirst().succ;
        while(t2!=null){
            if(tt==null){break;}
            else if(t2.element==tt.element){
                l2.delete(tt);
                tt=tt.succ;
            }else{
                t2=t2.succ;
                tt=tt.succ;
            }
        }
        t2 = l2.getFirst();



        while(t1!=null && t2!=null){
            if(t1.element.toString().compareTo(t2.element.toString())<0){
                //System.out.println("    Pomal: T1:"+t1.element+"       T2:"+t2.element);
                pom.insertLast(t1.element);

                t1 = t1.succ;

            }else if(t1.element.toString().compareTo(t2.element.toString())>0){
                //System.out.println("        Pogolem: T1:"+t1.element+"       T2:"+t2.element);
                pom.insertLast(t2.element);

                t2 = t2.succ;

            }
            else if(t1.element.toString().compareTo(t2.element.toString())==0){
                //System.out.println("            Ednakvi: T1:"+t1.element+"       T2:"+t2.element);

                pom.insertLast(t1.element);
                delete(t2);
                t1 = t1.succ;
                t2 = t2.succ;
            }
        }
        if(t1!=null){
            while (t1!=null){
                pom.insertLast(t1.element);
                t1 = t1.succ;
            }

        }
        if(t2!=null){
            while (t2!=null){
                pom.insertLast(t2.element);
                t2 = t2.succ;
            }
        }


        return pom;


    }

}

public class list {
    public static void main(String[] args) throws IOException {
        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
        String in = cin.readLine();
        int golemina1 = Integer.parseInt(in);


        SLL<Integer> lista1 = new SLL<Integer>();
        SLL<Integer> lista2 = new SLL<Integer>();

        String s = cin.readLine();
        String[] pomniza = s.split(" ");
        for (int i = 0; i < golemina1; i++) {
            lista1.insertLast(Integer.parseInt(pomniza[i]));
        }

        in = cin.readLine();
        int golemina2 = Integer.parseInt(in);


        s = cin.readLine();
        String[] pomnizaa = s.split(" ");
        for (int i = 0; i < golemina2; i++) {
            lista2.insertLast(Integer.parseInt(pomnizaa[i]));
        }
        /*System.out.println("====================Lista 1======================");
        lista1.print();
        System.out.println("====================Lista 2======================");
        lista2.print();*/
        //System.out.println("====================REZULTANTNA======================");
        SLL<Integer> nova = lista1.sort(lista2);
        nova.print();

    }
}
