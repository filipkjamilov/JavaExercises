import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

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
    }

    synchronized void endConnection(int id){
        activeConnections.remove(id);
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
        while (true){
            Socket client = this.server.accept();
            ServerWorkerThread s = new ServerWorkerThread(client, this);
            s.start();
        }
    }

    public static void main(String[] args) throws IOException {
        // todo: Start server

        TCPServer server = new TCPServer(9876);
        server.listen();

    }
}