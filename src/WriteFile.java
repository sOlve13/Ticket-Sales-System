import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {

    public static void writeFile(String filePath, String text, boolean append) {
        try {
            FileWriter textWrite = new FileWriter(filePath, append);
            textWrite.write(text);
            textWrite.close();
        } catch (IOException e) {
            System.out.println("Couldn't open the file");
        }
    }
}
