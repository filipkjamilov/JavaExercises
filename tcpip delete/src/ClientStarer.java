import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientStarer {

    private int ID;
    //todo: init other required variables here
    private String host;
    private int port;
    private Socket socket;
    DataOutputStream dos;
    ClientStarterWorkerThread c;

    ClientStarer(int id, String host, int port) throws IOException {
        this.ID = id;
        this.host = host;
        this.port = port;

        // todo: Connect to server and send client ID
        socket = new Socket(host, port);
        dos = new DataOutputStream(this.socket.getOutputStream());
        dos.writeInt(id);
        dos.flush();
        // todo: Listen for incoming messages
        listen();

    }

    // todo: Implement the sending message mechanism
    void sendMessage(int idReceiver, String message) throws IOException {
        dos.writeUTF(message+":"+idReceiver);
        dos.flush();
    }

    // todo: end communication - send END to server
    private void endCommunication() throws IOException {
        dos.writeUTF("END");
        dos.flush();
    }

    // todo: listen for icoming messages from the server.
    // It should start a separate thread to handle listening
    // and not block the execution
    // Should start a new ClientStarterWorkerThread
    private void listen() throws IOException {
        DataInputStream dis = new DataInputStream(this.socket.getInputStream());
        c = new ClientStarterWorkerThread(ID, dis);
        c.start();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        //todo: Initialize and start 3 clients

        ClientStarer client1 = new ClientStarer(1, "localhost", 9876);
        ClientStarer client2 = new ClientStarer(2, "localhost", 9876);
        ClientStarer client3 = new ClientStarer(3, "localhost", 9876);

        // Simulate chat
        client1.sendMessage(2, "Hello from client 1");
        Thread.sleep(1000);
        client2.sendMessage(3, "Hello from client 2");
        Thread.sleep(1000);
        client1.sendMessage(3, "Hello from client 1");
        Thread.sleep(1000);
        client3.sendMessage(1, "Hello from client 3");
        Thread.sleep(1000);
        client3.sendMessage(2, "Hello from client 3");

        // Exit the chatroom
        client1.endCommunication();
        client2.endCommunication();
        client3.endCommunication();
    }
}
