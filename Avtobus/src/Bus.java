import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Bus {

    public static void main(String[] args) throws Exception {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        br.close();

        // Vasiot kod tuka


        najdiMaxMin(N,M);


    }
    //N - Vozrasni   // 4
    //M - Deca       // 10
    private static void najdiMaxMin(int n, int m) {

        int maxCena=0, minCena=0;


        if(n<m && m!=0){
            maxCena = (((n + m)-1) * 100);
            minCena = (Math.abs(m-n))*100+(n*100);
        }else if(n>m && m!=0){
            maxCena = (Math.abs(m-1))*100+(n*100);
            minCena = (n * 100);
        }else if(m==0){
            maxCena = (n * 100);
            minCena = (n*100);
        }




        System.out.println(minCena);
        System.out.println(maxCena);
    }

}
