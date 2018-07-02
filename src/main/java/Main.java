import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
//import net.sf.json.*;
import org.json.*;

public class Main {
    // Instantiate a new Parser
    public static Parser parser = new Parser();
    
    // Declare variables containing URIs to source data
    public static String jsonFileBeforeURI = "src\\main\\resources\\before.json";
    public static String jsonFileAfterURI = "src\\main\\resources\\after.json";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Json Objects
        JSONObject jsonBefore = new JSONObject(GetTextFromFile(jsonFileBeforeURI));
        JSONObject jsonAfter = new JSONObject(GetTextFromFile(jsonFileBeforeURI));
        JSONObject jsonResult = parser.parse(jsonBefore, jsonAfter);
        
	JSONArray result = new JSONArray();
	result.put(jsonResult);
	
        // Display the parsed data
        System.out.print(result.toString(3));
    }
    
    // Method to extract text from files from inputted path
    public static String GetTextFromFile(String input)
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
