import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.List;

public class TcpClient extends Thread{

    private String absPath;
    private Socket socket;
    private DataOutputStream dos;

    TcpClient(String absPath, String host, int port) throws IOException {
        this.absPath=absPath;
        this.socket=new Socket(host, port);
        this.dos = new DataOutputStream(this.socket.getOutputStream());
        System.out.println("Client is making a connection with server!");
        
    }

    @Override
    public void run() {

        try {
            sendFiles(this.absPath);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendFiles(String path) throws IOException {
        File folder = new File(path);

        if(folder.exists()){
            List<File> fileList = Arrays.asList(folder.listFiles());
            for(File f : fileList){
                if(f.isFile()){
                    sendFile(f);
                }else if(f.isDirectory()){
                    sendFiles(f.getAbsolutePath());
                }
            }
            //socket.close();
            dos.writeUTF("KRAJ");
            dos.flush();
            socket.close();
        }
    }

    public void sendFile(File f) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
        String line;
        line = f.getName();
        //Sending the name to the server
        System.out.println("Sending the file name to the server: "+line);
        String name = "###"+line+"###";
        dos.writeUTF(name);
        dos.flush();

        while((line=reader.readLine())!=null){

            dos.writeUTF(line);
            dos.flush();

        }
        dos.writeUTF("END");
        System.out.println("Client is ending the file");
        dos.flush();


    }


    public static void main(String[] args) throws IOException {

        TcpClient c1 = new TcpClient("D:\\Desktop\\c1", "localhost", 9876);
        TcpClient c2 = new TcpClient("D:\\Desktop\\c2", "localhost", 9876);
        TcpClient c3 = new TcpClient("D:\\Desktop\\c3", "localhost", 9876);
        
        c1.start();
        c2.start();
        c3.start();

    }

}
