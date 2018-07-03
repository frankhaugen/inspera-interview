import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author frank
 */
public class FiletextCollector
{
     // Method to extract text from files from inputted path
    public static String Collect(String input)
    {
        String output = "";
        try {
            output = Files.lines(Paths.get(input)).collect(Collectors.joining("\n"));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return output;
    }
}
