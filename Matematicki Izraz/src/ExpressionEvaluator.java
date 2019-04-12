import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;

class ArrayStack<E> {
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
            throw new NoSuchElementException("Prazen stek");
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
            throw new NoSuchElementException("Prazen stek");
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}



public class ExpressionEvaluator {
    private static void najdi(String izraz) {

        ArrayStack<Integer> sobiraj = new ArrayStack<Integer>(izraz.length());

        String konkat = "";
        String [] niza = new String[izraz.length()];
        int br=0;
        for(int i=0; i<izraz.length(); i++){

            if(Character.isDigit(izraz.charAt(i))){
                konkat+=izraz.charAt(i);
            }else if(izraz.charAt(i) == '*'){
                niza[br] = konkat;
                niza[br+1] = "*";
                br = br + 2;
                konkat = "";

            }else if(izraz.charAt(i) == '+'){
                niza[br] = konkat;
                niza[br+1] = "+";
                br = br + 2;
                konkat = "";
            }
        }

        if(konkat!=""){
            niza[br] = konkat;
        }

        int bre = 0;
        for(int i=0; i<niza.length; i++){

            if(niza[i]!="+" && niza[i]!="*" && niza[i]!=null){
                if(niza[i+1] == "+"){
                    sobiraj.push(Integer.parseInt(niza[i]));
                    bre++;
                }else if(niza[i+1] == "*"){
                    int br1 = Integer.parseInt(niza[i]);
                    int br2 = Integer.parseInt(niza[i+2]);
                    sobiraj.push(br1*br2);
                    bre++;
                    i = i+2;
                }
            }else if(niza[i]=="+" && niza[i]!=null){
                sobiraj.push(Integer.parseInt(niza[i+1]));
                bre++;
                i = i + 1;
            }else if(niza[i]=="*" && niza[i]!=null){
                if(sobiraj.isEmpty()){
                    System.out.println(bre);
                }else{
                    int br1 = sobiraj.pop();
                    int br2 = Integer.parseInt(niza[i+1]);
                    sobiraj.push(br1*br2);
                    bre++;
                    i = i + 1;
                }
            }
        }
        int suma=0;
        while (!sobiraj.isEmpty()){
            suma += sobiraj.pop();
        }

        System.out.println(suma);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader cin=new BufferedReader(new InputStreamReader(System.in));

        String izraz=cin.readLine();


        najdi(izraz);
    }

}