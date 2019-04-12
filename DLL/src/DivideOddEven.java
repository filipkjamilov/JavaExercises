import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
class DLLNode<E> {
    protected E element;
    protected DLLNode<E> prev, next;
    public DLLNode(E elem, DLLNode<E>
            prev, DLLNode<E> next) {
        this.element = elem;
        this.prev = prev;
        this.next = next;
    }
}
class DLL<E> {
    private DLLNode<E> first, last;
    public DLL () {
        // kreiranje prazna lista
        this.first = null;
        this.last = null;
    }

    public DLLNode<E> getFirst() {
        return first;
    }

    public DLLNode<E> getLast() {
        return last;
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
            last.next = ins;
            last = ins;
        }
    }



    public void setFirst(DLLNode<E> first) {
        this.first = first;
    }

    public void setLast(DLLNode<E> last) {
        this.last = last;
    }

    @Override
    public String toString() {

        String s = new String();
        DLLNode<E> tmp = first;

        while(tmp!=null){
            s += tmp.element+" ";
            tmp = tmp.next;
        }

        return s;
    }
}

public class DivideOddEven {

    private static void parnost(DLL<Integer> lista) {
//3 3 5 7 1 3 2 5 6 4
    DLLNode<Integer> tmp = lista.getFirst();
    DLL<Integer> l2 = new DLL<Integer>();
    while(tmp!=null){
        if(tmp.next==null){
            if(tmp.element%2!=0){
                l2.insertLast(tmp.element);
                if(lista.getFirst()==lista.getLast()){
                    lista.setFirst(null);
                    lista.setLast(null);
                }else{
                    tmp.prev.next=null;
                }



            }
            break;
        }
        if(tmp.element%2!=0){//Kod za neparni
            l2.insertLast(tmp.element);

            if(tmp==lista.getFirst()){
                tmp=tmp.next;
                lista.setFirst(tmp);
                tmp.prev = null;

            }else{
                tmp = tmp.next;
                tmp.prev.prev.next = tmp;
                tmp.prev = tmp.prev.prev;

            }

        }else{
            tmp = tmp.next;

        }
    }
        System.out.println(l2);
        System.out.println(lista);


    }

    public static void main(String[] args) throws IOException {

        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(cin.readLine());
        String []niza = (cin.readLine().split(" "));

        DLL<Integer> lista = new DLL<Integer>();

        for(int i=0; i<N; i++){
            lista.insertLast(Integer.parseInt(niza[i]));
        }


        parnost(lista);


    }


}