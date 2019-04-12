import java.io.File;

public class FileScanner extends  Thread{

    private String fileToScan;
    //TODO: Initialize the start value of the counter
    private static Long counter = 0l;

    public FileScanner (String fileToScan) {
        this.fileToScan=fileToScan;
        counter++;
        //TODO: Increment the counter on every creation of FileScanner object
    }

    public static void printInfo(File file)  {

        /*
         * TODO: Print the info for the @argument File file, according to the requirement of the task
         * */
        if(file.isDirectory()){
            System.out.println("dir: " + file.getAbsolutePath() + " - " + file.length());
        }else {
            System.out.println("file: " + file.getAbsolutePath() + " - " + file.length());
        }

    }

    public static Long getCounter () {
        return counter;
    }


    public void run() {

        //TODO Create object File with the absolute path fileToScan.
        File file = new File(this.fileToScan);

        //TODO Create a list of all the files that are in the directory file.
        File [] files = null;
        if(file.exists()){
            files = file.listFiles();
        }else{
            System.out.println("Ne postoi Fajlot");
            return;
        }



        for (File f : files) {

            /*
             * TODO If the File f is not a directory, print its info using the function printInfo(f)
             * */
            if(!f.isDirectory()){
                printInfo(f);
            }
            /*
             * TODO If the File f is a directory, create a thread from type FileScanner and start it.
             * */
            if(f.isDirectory()){
                printInfo(f);
               FileScanner s = new FileScanner(f.getAbsolutePath());
                try {
                    s.start();
                    s.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //TODO: wait for all the FileScanner-s to finish
        }

    }

    public static void main (String [] args) {
        String FILE_TO_SCAN = "D:\\Desktop\\lab";

        //TODO Construct a FileScanner object with the fileToScan = FILE_TO_SCAN
        FileScanner fileScanner = new FileScanner(FILE_TO_SCAN);


        try {
            fileScanner.start();
            fileScanner.join();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(counter);
        }



        //TODO Start the thread from type FileScanner

        //.start();

        //TODO wait for the fileScanner to finish
        // JOIn

        //TODO print a message that displays the number of thread that were created
        //System.out.println(counter);


    }
}

