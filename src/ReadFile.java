import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFile {
    public static String readFile(String filePath){
        String data1 = "";
        try{
        data1 = new String(Files.readAllBytes(Paths.get(filePath)));
        }
        catch(IOException e){
            System.out.println("File was not read");
        }
        //System.out.println(data1);
        return data1;

    }
}
