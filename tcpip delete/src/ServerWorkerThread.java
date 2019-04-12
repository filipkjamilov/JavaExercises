import javax.xml.crypto.Data;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.atomic.DoubleAccumulator;

public class ServerWorkerThread extends Thread{

    private Socket client;
    DataInputStream dis;
    private TCPServer server;
    DataOutputStream dos;
    ServerWorkerThread(Socket client, TCPServer server){
        this.client=client;
        this.server = server;
    }

    @Override
    public void run() {
        //pustanje na poraka na nekoj klient pa i slusanje na klientot
        try {

            this.dis = new DataInputStream(this.client.getInputStream());
            this.dos = new DataOutputStream(this.client.getOutputStream());
            int id;
            id = this.dis.readInt();
            this.server.addConnection(id, this.client);
            String s;
            while (true){
                s = dis.readUTF();
                if(s.equals("END")){
                    this.dos.writeUTF("END");
                    this.dos.flush();
                    this.server.endConnection(id);
                    break;
                }else{
                    String [] pom = s.split(":");
                    Socket send = this.server.getConnection(Integer.parseInt(pom[1]));
                    DataOutputStream messageSend = new DataOutputStream(send.getOutputStream());
                    messageSend.writeUTF(pom[0]);
                    messageSend.flush();

                }

                //poraka za :25



            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
