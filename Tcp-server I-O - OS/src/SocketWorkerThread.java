import java.io.*;
import java.net.Socket;
import java.util.HashMap;

public class SocketWorkerThread extends Thread {

    private Socket client;
    private DataInputStream dis;
    private String absDestination;

    public SocketWorkerThread(Socket client, HashMap<Integer, Socket> hashmap, String absDestination) throws IOException {
        this.client = client;
        this.absDestination=absDestination;
        this.dis = new DataInputStream(client.getInputStream());
        System.out.println("SWT - Adding the connection to the hash map with LocalPort: " + client.getLocalAddress());
        hashmap.put(client.getLocalPort(), client);

    }

    @Override
    public void run() {

        try {
            BufferedWriter writer = null;

            while(true){
                String line = dis.readUTF();
                if(line.equals("KRAJ")){
                    return;
                }
                if(line.startsWith("###")){
                    File file = new File(absDestination+"\\"+line.replace("#", ""));
                    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
                }else if(line.equals("END")){
                    writer.flush();
                    writer.close();
                }else{
                    writer.write(line);

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
