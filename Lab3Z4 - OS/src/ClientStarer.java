import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ClientStarer {

    private String ID;
    Socket server;
    DataOutputStream dos;
    DataInputStream dis;

    ClientStarer(String id, String host, int port) throws IOException, InterruptedException {
        this.ID = id;
        this.server = new Socket(host, port);

        dos = new DataOutputStream(this.server.getOutputStream());
        dos.writeUTF(id);
        dos.flush();
        listen();
    }

    private void listen() throws IOException, InterruptedException {
         dis = new DataInputStream(this.server.getInputStream());
         ClientStarterWorkerThread client = new ClientStarterWorkerThread(ID, dis, dos);
         client.start();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ClientStarer client1 = new ClientStarer("A123456", "localhost", 9876);
        ClientStarer client2 = new ClientStarer("A789654", "localhost", 9876);
        ClientStarer client3 = new ClientStarer("A741852", "localhost", 9876);
        for(int i=0; i<500; i++){
            ClientStarer cc =  new ClientStarer("A74"+i, "localhost", 9876);
        }
    }
}
