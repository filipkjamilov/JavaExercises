import java.io.*;

public class HW01_2 {

    public static void main(String[] args) throws IOException{

        FileOutputStream to= null;
        RandomAccessFile rf = null;

        try{
            rf = new RandomAccessFile(args[0], "r");
            to = new FileOutputStream(args[1]);
            long position = rf.length();
            rf.seek(position);
            while (position != 0) {
                position -= 1;
                rf.seek(position);
                byte b = rf.readByte();
                to.write(b);
            }
        }finally {
            if(rf!=null){
                rf.close();
            }
            if(to!=null){
                to.flush();
                to.close();
            }
        }


    }
}
