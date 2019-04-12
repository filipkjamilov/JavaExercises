import java.io.DataInputStream;
import java.io.IOException;

class ClientStarterWorkerThread extends Thread {

    private static int ID;
    private static DataInputStream inputStream;

    public ClientStarterWorkerThread(int clientID, DataInputStream inputStream) {
        this.ID = clientID;
        this.inputStream = inputStream;
    }

    @Override
    public void run() {
        // todo: Handle listening to messages

        while (true){
            try {
                String s = this.inputStream.readUTF();
                System.out.println("Client is listening from server");
                //Check here

                if(s.equals("END")){
                    System.out.println("Client receive a message from server 'END'");
                    break;
                }
                System.out.println(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
}
