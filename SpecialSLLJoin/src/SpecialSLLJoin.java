import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class SLLNode<E>{
    public E element;
    public SLLNode<E> succ;

    public SLLNode(E element, SLLNode<E> succ) {
        this.element = element;
        this.succ = succ;
    }
}
class SLL<E>{
    public SLLNode<E> first;

    public SLLNode<E> getFirst() {
        return first;
    }

    public SLL() {
        this.first = null;
    }
    public void insertFirst(E o){
        SLLNode<E> ins = new SLLNode<E>(o, first);
        first = ins;
    }
    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            SLLNode<E> ins = new SLLNode<E>(o, null);
            tmp.succ = ins;
        }
        else {
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
    public void print() {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp != null) {
                System.out.print(tmp.element+" ");
                tmp = tmp.succ;

            }

        }else System.out.print("Listata e prazna");
    }
    public SLL<E> specialJoin(SLL<E> l1, SLL<E> l2){
        SLLNode<E> t1 = l1.getFirst();
        SLLNode<E> t2 = l2.getFirst();

        SLL<E> rez = new SLL<E>();

        SLLNode<E> tt1 = null;
        SLLNode<E> tt2 = null;
        if(t1.succ==null && t2.succ==null){

            rez.insertLast(t1.element);
            rez.insertLast(t2.element);
            return rez;

        }
        while(t1!=null && t2!=null){
            if(t1.succ==null || t2.succ==null){
                break;
            }else{
                tt1 = t1.succ;
                tt2 = t2.succ;
            }
            rez.insertLast(t1.element);
            rez.insertLast(tt1.element);
            rez.insertLast(t2.element);
            rez.insertLast(tt2.element);
            if(tt1.succ==null || tt2.succ==null || tt1.succ.succ==null || tt2.succ.succ==null){
                break;
            }
            t1=tt1.succ;
            tt1=tt1.succ.succ;
            t2=tt2.succ;
            tt2=tt2.succ.succ;

        }

        while (tt1.succ!=null){
            rez.insertLast(tt1.succ.element);
            if(tt1.succ.succ==null)break;
            tt1=tt1.succ;
        }
        while (tt2.succ!=null){
            rez.insertLast(tt2.succ.element);
            if(tt2.succ.succ==null)break;
            tt2=tt2.succ;
        }


        return rez;
    }
    public void duplikati(){

        while()



    }

}
public class SpecialSLLJoin {


    public static void main(String[] args) throws IOException{

        BufferedReader stdin = new BufferedReader(new InputStreamReader(
                System.in));
        String s = stdin.readLine();
        int N = Integer.parseInt(s);

        SLL<Integer> lista1 = new SLL<Integer>();
        SLL<Integer> lista2 = new SLL<Integer>();

        s = stdin.readLine();
        String[] pomniza = s.split(" ");
        for (int i = 0; i < N; i++) {
            lista1.insertLast(Integer.parseInt(pomniza[i]));
        }

        /*s = stdin.readLine();
        N = Integer.parseInt(s);
        s = stdin.readLine();
        pomniza = s.split(" ");
        for (int i = 0; i < N; i++) {
            lista2.insertLast(Integer.parseInt(pomniza[i]));
        }
        SLL<Integer> spoeni = new SLL<Integer>();
        spoeni=spoeni.specialJoin(lista1,lista2);

        spoeni.print();*/

        lista1.duplikati();


    }
}
