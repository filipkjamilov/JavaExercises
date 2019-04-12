import java.io.DataInputStream;
import java.io.IOException;

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
        String s;
        while (true){
            try {
                s = this.inputStream.readUTF();
                System.out.println("Waiting for server's messages");
                if(s.equals("END")){
                    System.out.println("Server is sending a END message !");
                    break;
                }
                System.out.println(s);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
}