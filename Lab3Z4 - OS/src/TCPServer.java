import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class TCPServer {

    private ServerSocket server;
    private HashMap<String, Integer> voteList;
    private static Semaphore max;

    void print(){
        int k1=0;
        int k2=0;
        int k3=0;
        for (String s : voteList.keySet()){
            if(voteList.get(s)==1){
                k1++;
            }else if(voteList.get(s)==2){
                k2++;
            }else if(voteList.get(s)==3){
                k3++;
            }
        }
        System.out.println("Filip Kjamilov ima: "+k1+" glasovi.");
        System.out.println("Klementina Gjorgieva ima: "+k2+" glasovi.");
        System.out.println("Janaki Bajatovski ima: "+k3+" glasovi.");

    }



    synchronized void addConnection(String id, int glas) {
        voteList.put(id, glas);
    }



    TCPServer(int port) throws IOException {
        this.server = new ServerSocket(port);
        this.voteList = new HashMap<>();
        max = new Semaphore(100);
    }


    void listen() throws IOException, InterruptedException {
        new Thread(() -> {
            while(true){
                Scanner s = new Scanner(System.in);
                while (s.hasNext()){
                    String cmd = s.next();
                    if(cmd.equals("print")){
//                        voteList.forEach((a,b)->{
//                            System.out.println(a+": "+b);
//                        });
                        print();
                    }
                }
            }
        }).start();


        while (true){

            max.acquire();
            Socket client = this.server.accept();
            ServerWorkerThread s = new ServerWorkerThread(client, this);
            s.start();
            max.release();

        }

    }

    public static void main(String[] args) throws IOException, InterruptedException {

        TCPServer server = new TCPServer(9876);
        server.listen();


    }
}