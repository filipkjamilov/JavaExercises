import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ZigZagSequence {

    static int najdiNajdolgaCikCak(int a[]) {
        // Vasiot kod tuka
        int br=1;
        int max = 1;
        for(int i =0; i< a.length-1; i++){
            if((a[i] > 0 && a[i+1] < 0) || (a[i] < 0 && a[i+1] > 0)){
                br++;
                if(max < br){
                    max = br;
                }
            }else if(a[i] == 0){
                br = 1;

            }else{
                br = 1;
            }

        }
        return max;


    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int a[] = new int[N];
        for (i=0;i<N;i++)
            a[i] = Integer.parseInt(br.readLine());

        int rez = najdiNajdolgaCikCak(a);
        System.out.println(rez);

        br.close();

    }
}