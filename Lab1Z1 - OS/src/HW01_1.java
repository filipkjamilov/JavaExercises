import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

class MyFileFilter implements FilenameFilter {

    @Override
    public boolean accept(File directory, String fileName) {
        if (fileName.endsWith(".txt")) {
            return true;
        }
        return false;
    }
}

public class HW01_1 {

    public static double listFile(String absPath){
        File file = new File(absPath);
        FilenameFilter filter = new MyFileFilter();
        int br = 0;
        if(file.exists()){
            File[] subfiles = file.listFiles(filter);
            for(File f : subfiles){
                br++;
                if(f.isDirectory()){
                    listFile(f.getAbsolutePath());
                }
            }
        }

        return br;
    }

    public static double listFileLength(String absPath){
        File file = new File(absPath);
        FilenameFilter filter = new MyFileFilter();
        int len = 0;
        if(file.exists()){
            File[] subfiles = file.listFiles(filter);
            for(File f : subfiles){
                len += f.length();
                if(f.isDirectory()){
                    listFile(f.getAbsolutePath());
                }
            }
        }

        return len;
    }

    public static void main(String[] args) throws IOException{

        System.out.println((listFile(args[0])/listFileLength(args[0]))/1024);

    }
}
