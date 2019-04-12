import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class TcpServer {

    private ServerSocket server;
    private HashMap<Integer, Socket> hashMap;
    private String dest;

    public TcpServer(String dest, int port) throws IOException {
        server = new ServerSocket(port);
        hashMap = new HashMap<>();
        this.dest=dest;

    }

    public void listen() throws IOException {

        while (true){

            Socket client = server.accept();

            SocketWorkerThread socket = new SocketWorkerThread(client,hashMap,dest);
            socket.start();

        }

    }

    public static void main(String[] args) throws IOException {
        TcpServer server = new TcpServer("D:\\Desktop\\serverFolder",9876);
        server.listen();
    }

}
