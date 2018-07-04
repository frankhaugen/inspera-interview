import org.json.*;

public class Main {
     
    // Declare variables containing URIs to source data
    public static String jsonFileBeforeURI = "src\\main\\resources\\before.json";
    public static String jsonFileAfterURI = "src\\main\\resources\\after.json";

    public static void main(String[] args) {
        
        // Decleared new JSONObjects
        JSONObject jsonBefore = new JSONObject(FiletextCollector.Collect(jsonFileBeforeURI));
        JSONObject jsonAfter = new JSONObject(FiletextCollector.Collect(jsonFileAfterURI));
        
	 try
	 {
	       // Display the parsed data in the console output
	       System.out.println(Parser.parse(jsonBefore, jsonAfter).toString(1));
	 }
	 catch (Exception e)
	 {
	      System.out.println("ERROR: " + e);
	 }
    }
}
