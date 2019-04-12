import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class ArrayStack<E>{
    // Stekot e pretstaven na sledniot nacin: depth is dlabochinata
// na stekot, a elems[0...depth-1] se negovite elementi.
    private E[] elems;
    private int depth;
    public ArrayStack (int maxDepth) {
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }

    public boolean isEmpty () {
        return (depth == 0);
    }
    public E peek () {
        if (depth == 0)
            return null;//throw new NoSuchElementException();
        return elems[depth-1];
    }
    public void clear () {
        for (int i = 0; i < depth; i++) elems[i] = null;
        depth = 0;
    }

    public void push (E x) {
        elems[depth++] = x;
    }
    public E pop () {
        if (depth == 0)
            return null;//throw new NoSuchElementException();
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}


public class JavaStack {
    public static void main(String[] args) throws IOException {

        BufferedReader cin = new BufferedReader(new InputStreamReader(System.in));

        int N = 100;
        ArrayStack<String> nasstek = new ArrayStack<String>(N);

        String izraz = cin.readLine();
        String niza[] = izraz.split("");
        boolean zname = false;
        for (int i = 0; i < niza.length; i++) {
            if (niza[i].compareTo("(")==0 || niza[i].compareTo("[")==0 || niza[i].compareTo("{")==0) {
                nasstek.push(niza[i]);
                System.out.println("Pravime push na:"+niza[i]);
            } else if (niza[i].compareTo(")")==0 || niza[i].compareTo("]")==0 || niza[i].compareTo("}")==0) {
                String pop = nasstek.pop();
                System.out.println("Pravime pop vo promenliva pop na:"+niza[i]);
                if(niza[i].compareTo(")") == 0 && pop.compareTo("(") == 0  || niza[i].compareTo("]") == 0 && pop.compareTo("[") == 0 || niza[i].compareTo("}") == 0 && pop.compareTo("{") == 0){

                }else{
                    nasstek.push(pop);
                    System.out.println("Pravime pop na:"+niza[i]);
                }
            }
                    //s×(s–a)×(s–b×(s–c)

        }
        if(nasstek.isEmpty()){
            System.out.println("Korek");
        }
        else{
            System.out.println("NE");
        }
    }
}
