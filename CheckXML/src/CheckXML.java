import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem,
                   SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }
}
class LinkedStack<E>{
    //top e link do prviot jazol na ednostrano-povrzanata
// lista koja sodrzi gi elementite na stekot .
    private SLLNode<E> top;
    public LinkedStack () {
// Konstrukcija Konstrukcija na nov, prazen stek.
        top = null;
    }
    public boolean isEmpty () {
        return (top == null);
    }
    public E peek () {
        if (top == null)
            throw new NoSuchElementException();
        return top.element;
    }
    public void clear () {
        top = null;
    }
    public void push (E x) {
        top = new SLLNode<E>(x, top);
    }
    public E pop () {
        if (top == null)
            throw new NoSuchElementException();
        E topElem = top.element;
        top = top.succ;
        return topElem;
    }
}


public class CheckXML {

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = Integer.parseInt(s);
        String [] redovi = new String[n];

        for(int i=0;i<n;i++)
            redovi[i] = br.readLine();


        validna(redovi);

        br.close();
    }

    private static void validna(String[] redovi) {

        LinkedStack<String> stek = new LinkedStack<String>();

        for (int i = 0 ;i<redovi.length; i++){
            if(redovi[i].contains("[") && redovi[i].contains("]") && !redovi[i].contains("/")){
                stek.push(redovi[i]);
            }else if(redovi[i].contains("[") && redovi[i].contains("]") && redovi[i].contains("/")){
                String tag1 = stek.pop();
                String tag2 = redovi[i].replace("/", "");

                if(tag1.compareTo(tag2)==0){

                }else {
                    System.out.println("0");
                    break;
                }

                //[Korisnik]
                //[/Korisnik]
            }
        }
        if(stek.isEmpty()){
            System.out.println("1");
        }
    }
}