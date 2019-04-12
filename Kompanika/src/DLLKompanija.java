import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class DLLNode<E> {
    protected E element;
    protected DLLNode<E> prev, succ;
    public DLLNode(E elem, DLLNode<E>
            prev, DLLNode<E> succ) {
        this.element = elem;
        this.prev = prev;
        this.succ = succ;
    }
}

class DLL<E>{
    protected DLLNode<E> first;
    protected DLLNode<E> last;

    public DLL() {
        this.first = null;
        this.last = null;
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public void setFirst(DLLNode<E> first) {
        this.first = first;
    }

    public DLLNode<E> getLast() {
        return last;
    }

    public void setLast(DLLNode<E> last) {
        this.last = last;
    }


    public void insertFirst(E o){
        DLLNode<E> ins = new
                DLLNode<E>(o, null, first);
        if (first == null)
            last = ins;
        else
            first.prev = ins;
        first = ins;
    }
    public void insertLast(E o){
        if (first == null)
            insertFirst(o);
        else {
            DLLNode<E> ins = new
                    DLLNode<E>(o, last, null);
            last.succ = ins;
            last = ins;
        }
    }


    @Override
    public String toString() {
        String s = new String();
        DLLNode<E> tmp = first;
        while (tmp!=null){
            s+= tmp.element.toString();
           tmp = tmp.succ;
        }
        return s;
    }
    public E deleteFirst() {
        if (first != null) {
            DLLNode<E> tmp = first;
            first = first.succ;
            first.prev = null;
            if (first == null)
                last = null;
            return tmp.element;
        } else
            return null;
    }

    public E deleteLast() {
        if (first != null) {
            if (first.succ == null)
                return deleteFirst();
            else {
                DLLNode<E> tmp = last;
                last = last.prev;
                last.succ = null;
                return tmp.element;
            }
        }
        // else throw Exception
        return null;
    }

    public E delete(DLLNode<E> node) {
        if(node==first){
            deleteFirst();
            return node.element;
        }
        if(node==last){
            deleteLast();
            return node.element;
        }
        node.prev.succ = node.succ;
        node.succ.prev = node.prev;
        return node.element;

    }

}
class Vraboten{
    private int ID;
    private int Plata;

    public Vraboten(){
    }


   public String toString() {
        String s = new String();
        s+= getID() + " " + getPlata() + "\n";
        return s;
    }

    public Vraboten(int ID, int plata) {
        this.ID = ID;
        Plata = plata;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getPlata() {
        return Plata;
    }

    public void setPlata(int plata) {
        Plata = plata;
    }




}

public class DLLKompanija {

    private static void otsrani(DLL<Vraboten> lista, int iznos){
        DLLNode<Vraboten> tmp = lista.getFirst();
        while (tmp!=null){
            if(tmp.element.getPlata()<iznos){
                tmp = tmp.succ;
                lista.delete(tmp.prev);
            }else {
                tmp = tmp.succ;
            }
        }
    }
    private static void sortiraj(DLL<Vraboten> lista) {

        DLLNode<Vraboten> tmp1 = lista.getFirst();
        DLLNode<Vraboten> sleden = tmp1.succ;
        Vraboten cuvaj;
        while (tmp1!=null){
            sleden = tmp1.succ;
            while (sleden!=null){
                if(tmp1.element.getID() < sleden.element.getID()){
                    cuvaj = tmp1.element;
                    tmp1.element = sleden.element;
                    sleden.element = cuvaj;
                }
                    sleden = sleden.succ;
            }
            tmp1 = tmp1.succ;
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader cin=new BufferedReader(new InputStreamReader(System.in));
        String s=cin.readLine();
        int kolku=Integer.parseInt(s);

        DLL<Vraboten> lista = new DLL<Vraboten>();

        for(int i=0;i<kolku;i++){
            lista.insertLast(new Vraboten(Integer.parseInt(cin.readLine()),Integer.parseInt(cin.readLine())));
        }

        int iznos=Integer.parseInt(cin.readLine());

        otsrani(lista,iznos);
        sortiraj(lista);
        System.out.println(lista);

    }

}