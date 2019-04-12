import java.io.File;
import java.io.IOException;

class fileAlreadyExistsException extends IOException{
    public fileAlreadyExistsException(String message) {
        super("File already exists from FILIP");
    }
}

public class java_io {

    public static void main(String[] args) throws IOException {
        File file = new File("D:/IdeaProjects/Voved_Java_IO/index/folder");

        System.out.println(args[0]);
        System.out.println(args[1]);


    }

}
