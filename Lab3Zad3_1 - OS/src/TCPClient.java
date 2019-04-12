import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;



class ClientStarterWorkerThread extends Thread {

    private int ID;
    private DataInputStream inputStream;

    public ClientStarterWorkerThread(int clientID, DataInputStream inputStream) {
        this.ID = clientID;
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        // todo: Handle listening to messages
        System.out.println("Klient so ID " + ID + " ima poraka: " +inputStream);
    }
}



public class TCPClient {

    private Socket socket;
    private Scanner scanner;
    int id;
    private TCPClient(int id, InetAddress serverAddress, int serverPort)throws Exception{
        this.id=id;
        this.socket = new Socket(serverAddress, serverPort);
        this.scanner = new Scanner(System.in);
    }
    void sendMessage(int idReceiver, String message) throws IOException {

            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
            out.println(message+":"+idReceiver);
            out.flush();

    }

    private void endCommunication() throws IOException {

        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        out.println("END");
        out.flush();

    }
    // todo: listen for icoming messages from the server.
    // It should start a separate thread to handle listening
    // and not block the execution
    // Should start a new ClientStarterWorkerThread

    private void listen() throws IOException {

        BufferedReader FromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        while (true) {
            // Check if there's anything to receive
            while (FromServer.ready()) {
                // receive from server
                String st = FromServer.readLine();
                String [] pom = st.split(":");
                //Pretpostavka deka e OK!!
                new ClientStarterWorkerThread(Integer.parseInt(pom[1]), (DataInputStream) FromServer.lines());
                //System.out.println(FromServer.readLine());
            }


        }
    }


    public static void main(String[] args) throws Exception {

        int broj = 1;
        TCPClient client1 = new TCPClient(broj, InetAddress.getByName("localhost"), 9876);
        broj++;
        TCPClient client2 = new TCPClient(broj, InetAddress.getByName("localhost"), 9876);
        broj++;
        TCPClient client3 = new TCPClient(broj, InetAddress.getByName("localhost"), 9876);
        broj++;



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