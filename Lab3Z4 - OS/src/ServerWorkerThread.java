import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerWorkerThread extends Thread{

    private Socket client;
    DataOutputStream dos;
    DataInputStream dis;
    TCPServer server;

    ServerWorkerThread(Socket client, TCPServer server) throws IOException {
        this.client = client;
        this.server = server;
        this.dos = new DataOutputStream(this.client.getOutputStream());
        this.dis = new DataInputStream(this.client.getInputStream());
    }

    @Override
    public void run() {

        String id;
        int glas;
        try {
            id = this.dis.readUTF();
            this.dos.writeUTF("IZBERETE EDEN OD SLEDNIVE KANDIDATI\nFilip Kjamilov - No.1\nKlementina Gjorgieva - No.2\nJanaki Bajatovski - No.3");
            this.dos.flush();
            glas = this.dis.readInt();
            server.addConnection(id, glas);
            return;

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
