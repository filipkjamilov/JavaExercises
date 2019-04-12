import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
class ArrayStack<E> {
    // Stekot e pretstaven na sledniot nacin: depth is dlabochinata
    // na stekot, a elems[0...depth-1] se negovite elementi.
    private E[] elems;
    private int depth;

    public ArrayStack(int maxDepth) {
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }
    public boolean isEmpty () {
        return (depth == 0);
    }
    public E peek () {
        if (depth == 0)
            System.out.println("Nema elementi");
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
            System.out.println("Nema elementi vo stekot");
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }


}

public class PostFixEvaluation {

    public static void presmetaj(char [] karakteri){
        ArrayStack<Integer> stek=new ArrayStack<Integer>(100);
        String broj="";
        for(int i=0;i<karakteri.length;i++){
            if(Character.isDigit(karakteri[i])){ //uslov dali e digit, ako e digit konkatanacija
                broj=broj+karakteri[i]; //72
            }
            if(karakteri[i]==' '){  //uslov dali e prazno mesto,//push na broj vu stek,ako e broj="", izlezi si
                if(broj!=""){
                    stek.push(Integer.parseInt(broj));//vo stek 28
                    broj="";
                }
            }
            if(karakteri[i]=='*'){ //uslov dali e operacija//pop na dvata broja od stekot i vrshenje na operacija i vrakjanje rezultat vo stekot
                int br1=stek.pop();//72
                int br2=stek.pop();//48
                stek.push(br1*br2);//3456
            }
            else if(karakteri[i]=='/'){
                int br1=stek.pop();
                int br2=stek.pop();
                stek.push(br2/br1);
            }
            else if(karakteri[i]=='-'){
                int br1=stek.pop();
                int br2=stek.pop();
                stek.push(Math.abs(br1-br2));
            }
            else if(karakteri[i]=='+'){
                int br1=stek.pop();
                int br2=stek.pop();
                stek.push(br1+br2);
            }

        }

        System.out.println(stek.pop());
    }

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char [] karakteri = expression.toCharArray();


        presmetaj(karakteri);

    }

}