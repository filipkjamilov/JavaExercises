import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;

public class ArithmeticExpression {

    // funkcija za presmetuvanje na izrazot pocnuvajki
    // od indeks l, zavrsuvajki vo indeks r
    static int presmetaj(char c[], int a, int N) throws ScriptException {
        // Vasiot kod tuka
        int n=0;
        Integer x,o,y,z,w;
        Character znak;
        String [] nasa = new String[N];
        Integer brojac=0;
        for(int i=0; i<N;i++){
            z = Character.getNumericValue(c[i]);
            if(z>=0 && z<10){

                x = Character.getNumericValue(c[i]);
                znak = c[i+1];
                y = Character.getNumericValue(c[i+2]);

                if (znak.compareTo('+')==0) {

                    w = x+y;
                    nasa[brojac]= w.toString();
                    brojac++;


                }else {

                    w = x-y;
                    nasa[brojac]= w.toString();
                    brojac++;

                }

                i = i+3;

            }else{
                if(c[i] == '+'){
                    nasa[brojac] = Character.toString(c[i]);
                    brojac++;
                }
                else if(c[i] == '-'){
                    nasa[brojac] = Character.toString(c[i]);
                    brojac++;
                }
            }
        }
        for(int i=0; i<brojac; i++)
            System.out.println(nasa[i]);
        Integer rezultat = 0;
        String znakk;
        for(int i = 0;i<brojac;i++){
            if(i<1){
                x = Integer.parseInt(nasa[i]);//2
                //znak = nasa[i+1].charAt(0);//+
                znakk = nasa[i+1];//+
                y = Integer.parseInt(nasa[i+2]);//2
                i = i+2;// i se podmestil za 3 meesta i sega e na 2
            }else{
                x = rezultat;//4,6
                znakk = nasa[i];//+ +
                y = Integer.parseInt(nasa[i+1]);//2,2
                i++;
            }

            if(znakk.compareTo("+")==0){
                rezultat += x+y;//4,6,8
            }else if(znakk.compareTo("-")==0){
                rezultat -= x-y;//
            }

        }


        return rezultat;
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = presmetaj(exp, 0, exp.length-1);
        System.out.println(rez);

        br.close();

    }

}
