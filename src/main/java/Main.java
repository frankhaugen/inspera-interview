import org.json.*;

public class Main {
     
    public static void main(String[] args)
    {
        
	  // Decleared new JSONObjects
	  JSONObject jsonBefore = new JSONObject(FiletextCollector.Collect("src\\main\\resources\\before.json"));
	  JSONObject jsonAfter = new JSONObject(FiletextCollector.Collect("src\\main\\resources\\after.json"));

	  // Display the parsed data in the console output
	  System.out.println(Parser.parse(jsonBefore, jsonAfter).toString(1));
	  
    }
}
