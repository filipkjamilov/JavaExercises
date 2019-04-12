import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.BufferPoolMXBean;
import java.net.*;
import java.util.HashMap;

class ServerWorkerThread extends Thread{

    private Socket socket;

    public ServerWorkerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

            Socket socket=this.socket;

            try{
                BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));// kje ischituva podatoci od klientot sho ni ispratil

                String line=null;
                while ((line=reader.readLine())!=null){
                    String [] pom = line.split(":");


                }
            }catch (Exception e){
                e.printStackTrace();
            }




    }
}

public class TCPServer {

    private ServerSocket server;
    private HashMap<Integer, Socket> activeConnections;

    //todo: Initialize server
    TCPServer(int port) throws IOException {
        this.server = new ServerSocket(port);
    }


    // todo: Get the required connection
    public Socket getConnection(int id) {

    }

    // todo: Add connected client to the hash map
    void addConnection(int id, Socket connection) {
        activeConnections.put(id, connection);
    }

    synchronized void endConnection(int id){
        activeConnections.remove(id);
    }

    // todo: Handle server listening
    // todo: For each connection, start a separate
    // todo: thread (ServerWorkerThread) to handle the communication
    void listen() throws IOException {


        new ServerWorkerThread(this.server.accept());



    }

    public static void main(String[] args) throws IOException {
        // todo: Start server
        TCPServer app = new TCPServer(9876);
        System.out.println("\r\nRunning Server: "+"Host="+app.server.getInetAddress()+" Port="+app.server.getLocalPort());

        app.listen();
    }



}