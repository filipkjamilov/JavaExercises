import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client_Starter {

    private int ID;
    //todo: init other required variables here
    private Socket socket;
    ClientStarterWorkerThread t;
    DataOutputStream out;


    Client_Starter(int id, String host, int port) throws IOException {
        this.ID = id;
        // todo: Connect to server and send client ID
        this.socket = new Socket(host, port);

        this.out = new DataOutputStream(this.socket.getOutputStream());
        this.out.writeInt(id);

        System.out.println("Client is sending the first message to server, Sending the client ID: " + id);


        // todo: Listen for incoming messages
        listen();
        

    }

    // todo: Implement the sending message mechanism
    void sendMessage(int idReceiver, String message) throws IOException {
        this.out.writeUTF(message+":"+idReceiver);
        this.out.flush();
        System.out.println("Client is sending a message: "  + message + " to client with id " + idReceiver);
    }

    // todo: end communication - send END to server
    private void endCommunication() throws IOException {
        this.out.writeUTF("END");
        this.out.flush();
        System.out.println("EndCommunication from Client, Sending a txt message 'END'");
    }

    // todo: listen for icoming messages from the server.
    // It should start a separate thread to handle listening
    // and not block the execution
    // Should start a new ClientStarterWorkerThread
    private void listen() throws IOException {
        DataInputStream d = new DataInputStream(this.socket.getInputStream());
        this.t = new ClientStarterWorkerThread(ID, d);
        this.t.start();
        System.out.println("Client listen method");

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        //todo: Initialize and start 3 clients

        Client_Starter client1 = new Client_Starter(1, "localhost", 9876);
        Client_Starter client2 = new Client_Starter(2, "localhost", 9876);
        Client_Starter client3 = new Client_Starter(3, "localhost", 9876);

        // Simulate chats
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