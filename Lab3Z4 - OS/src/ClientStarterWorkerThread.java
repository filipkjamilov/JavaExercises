import java.io.*;
import java.util.Random;

class ClientStarterWorkerThread extends Thread {

    private String ID;
    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    public ClientStarterWorkerThread(String clientID, DataInputStream inputStream, DataOutputStream outputStream) {
        this.ID = clientID;
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    @Override
    public void run() {
        // todo: Handle listening to messages
        String voteList;
        try {
            voteList = inputStream.readUTF();
            System.out.println("Printanje na izbriracni spisok:");
            System.out.println(voteList);

            Random rn = new Random();
            int n = rn.nextInt(3 - 1 + 1) + 1;

            outputStream.writeInt(n);
            outputStream.flush();
            System.out.println("Uspesno Glasavte!");
            System.out.println();
            return;


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
