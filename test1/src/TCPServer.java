import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

class ServerWorkerThread extends Thread{

    private Socket client;
    private TCPServer server;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;

    ServerWorkerThread(Socket client, TCPServer server){
        this.client = client;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            this.dataInputStream = new DataInputStream(this.client.getInputStream());
            int d = dataInputStream.readInt();
            server.addConnection( d,this.client);
            System.out.println("New Thread from ServerWorkerThread");

            dataOutputStream = new DataOutputStream(this.client.getOutputStream());

            while (true){

                System.out.println("Comes in while");
                String s;
                s = this.dataInputStream.readUTF();

                System.out.println("Server is reading from client: " + s);
                if(s.equals("END")){

                    dataOutputStream.writeUTF("END");
                    dataOutputStream.flush();
                    server.endConnection(d);
                    System.out.println("End connection from Server side!");

                }else if(s.length()>8){

                    String [] pom = s.split(":");
                    System.out.println("Server is sending messages to client with id: "+ pom[1] + " - The message is - "+ pom[0]);
                    Socket sendTo = server.getConnection(Integer.parseInt(pom[1]));
                    DataOutputStream send = new DataOutputStream(sendTo.getOutputStream());
                    send.writeUTF(pom[0]);
                    send.flush();

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class TCPServer {

    private ServerSocket server;
    private HashMap<Integer, Socket> activeConnections;

    // todo: Get the required connection
    public Socket getConnection(int id) {

        return activeConnections.get(id);

    }

    // todo: Add connected client to the hash map
    synchronized void addConnection(int id, Socket connection) {

        activeConnections.put(id, connection);
        System.out.println("Server is adding a connection to the HashMap");

    }

    synchronized void endConnection(int id){
        activeConnections.remove(id);
        System.out.println("Server is deleting a connection");
    }

    //todo: Initialize server
    TCPServer(int port) throws IOException {

        this.activeConnections = new HashMap<>();
        this.server = new ServerSocket(port);

    }

    // todo: Handle server listening
    // todo: For each connection, start a separate
    // todo: thread (ServerWorkerThread) to handle the communication
    void listen() throws IOException {
        while(true) {
            Socket client = this.server.accept();
            ServerWorkerThread s = new ServerWorkerThread(client, this);
            System.out.println("Server is running a new ServerWorkedThread and starts the thread");
            s.start();
        }
    }

    public static void main(String[] args) throws IOException {
        // todo: Start server
        TCPServer app =  new TCPServer(9876);

        app.listen();

    }
}