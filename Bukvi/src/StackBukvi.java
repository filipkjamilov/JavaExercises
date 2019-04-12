import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

class ArrayStack<E>{
    private E[] elems;
    private int depth;

    @SuppressWarnings("unchecked")
    public ArrayStack (int maxDepth) {
        // Konstrukcija na nov, prazen stek.
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }

    public int length(){
        return depth;
    }

    public boolean isEmpty () {
        // Vrakja true ako i samo ako stekot e prazen.
        return (depth == 0);
    }


    public E peek () {
        // Go vrakja elementot na vrvot od stekot.
        if (depth == 0)
            throw new NoSuchElementException("Prazen stek");
        return elems[depth-1];
    }


    public void clear () {
        // Go prazni stekot.
        for (int i = 0; i < depth; i++)  elems[i] = null;
        depth = 0;
    }


    public void push (E x) {
        // Go dodava x na vrvot na stekot.
        elems[depth++] = x;
    }


    public E pop () {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (depth == 0)
            throw new NoSuchElementException("Prazen stek");
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}

public class StackBukvi {
    static void proveri_t_posle_s(char [] niza) //primam niza od karakteri
    {//UYGSTTMST
        ArrayStack<Character> stek1=new ArrayStack<Character>(100);
        ArrayStack<Integer> stek2=new ArrayStack<Integer>(100);
        int suma=0;
        //S T U S T S T S G

        for (int i=0;i<niza.length;i++){
            if(niza[i]=='S'){
                for (int j = i+1; j<niza.length; j++){
                    if(niza[j]=='T'){
                        stek1.push(niza[j]);
                    }else if(niza[j] == 'S'){
                        suma = stek1.length();
                        stek1.clear();
                        stek2.push(suma);
                        suma=0;
                    }
                }if (!stek1.isEmpty()){
                     suma = stek1.length();
                     stek1.clear();
                     stek2.push(suma);
                     suma=0;
                }
            }
            break;

        }


        int flag = 0;
        int pop1,pop2;
        while (!stek2.isEmpty()){
            pop1=stek2.pop();
            if(!stek2.isEmpty()){
                pop2 = stek2.pop();
                if(pop1==pop2){
                    flag = 1 ;
                    stek2.push(pop2);
                }else {
                    flag = 0;
                    System.out.println(flag);
                    System.exit(-1);
                }
            }
        }
        if(flag==1){
            System.out.println("1");
        }else {
            System.out.println("0");
        }



    }

    public static void main(String[] args) throws IOException {
        char [] niza=new char[100];

        Scanner f=new Scanner(System.in);
        String st=f.next();
        niza=st.toCharArray();

        proveri_t_posle_s(niza);

    }


}