import java.io.IOException;
import org.json.*;

public class Main {
     
    public static void main(String[] args) throws IOException
    {
        
	  // Decleared new JSONObjects and filling them with json strings from resource files
	  JSONObject jsonBefore = new JSONObject(FiletextCollector.Collect("src\\main\\resources\\before.json"));
	  JSONObject jsonAfter = new JSONObject(FiletextCollector.Collect("src\\main\\resources\\after.json"));

	  // Display the parsed data in the console output in a human-readable manner
	  System.out.println(Parser.parse(jsonBefore, jsonAfter).toString(1));
	  System.in.read();
    }
}
