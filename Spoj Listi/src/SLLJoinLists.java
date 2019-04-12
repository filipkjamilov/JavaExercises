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

    public void delete(SLLNode<E> node) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != node && tmp.succ.succ != null){
                tmp = tmp.succ;
            }
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
            }
        }

    }


    @Override
    public String toString() {

        String s = "";
        SLLNode<E> tmp = getFirst();
        while (tmp!=null){
            s+=tmp.element+" ";
            tmp = tmp.succ;
        }

        return s;
    }

    public void setFirst(SLLNode<E> first) {
        this.first = first;
    }
}

public class SLLJoinLists {
    private static void gluposti(SLL<Integer> l1, SLL<Integer> l2) {

        SLLNode<Integer> t1 = l1.getFirst();
        SLLNode<Integer> t2 = l2.getFirst();
        while (t1.succ!=null)t1=t1.succ;
        t1.succ = t2;
        l2.setFirst(null);
        t1 = l1.getFirst();
        while (t1!=null){
            SLLNode<Integer> t3 = t1.succ;
            while (t3!=null){
                if(t1.element>t3.element){
                    int el = t1.element;
                    t1.element = t3.element;
                    t3.element = el;
                }else if(t1.element==t3.element){
                    l1.delete(t3);
                }
                t3 = t3.succ;
            }
            t1 = t1.succ;

        }
        System.out.println(l1);

    }


    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(
                System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);

        SLL<Integer> l1 = new SLL<Integer>();
        SLL<Integer> l2 = new SLL<Integer>();

        s = stdin.readLine();
        String[] pomniza = s.split(" ");
        for (int i = 0; i < N; i++) {
            l1.insertLast(Integer.parseInt(pomniza[i]));
        }

        s = stdin.readLine();
        N = Integer.parseInt(s);
        s = stdin.readLine();
        pomniza = s.split(" ");
        for (int i = 0; i < N; i++) {
            l2.insertLast(Integer.parseInt(pomniza[i]));
        }

        gluposti(l1, l2);



    }


}
