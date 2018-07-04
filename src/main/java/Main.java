import org.json.*;

public class Main {
     
     // Testing variables
     public static String testDT = "2016-01-20T10:00:00Z";
     
    // Instantiate a new Parser
    public static Parser parser = new Parser();
    
    // Instantiate class time zone editor
    public static TimeZoneConverter tzConverter = new TimeZoneConverter();
    
    // Declare variables containing URIs to source data
    public static String jsonFileBeforeURI = "src\\main\\resources\\before.json";
    public static String jsonFileAfterURI = "src\\main\\resources\\after.json";
    
    // Declearing a variable containing the timezone-ID we want to convert date to
    public static String timeZone = "Europe/Oslo";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Decleared new JSONObjects
        JSONObject jsonBefore = new JSONObject(FiletextCollector.Collect(jsonFileBeforeURI));
        JSONObject jsonAfter = new JSONObject(FiletextCollector.Collect(jsonFileAfterURI));
        
	 try
	 {
	       // Display the parsed data in the console output
	       System.out.println(parser.parse(jsonBefore, jsonAfter).toString(3));
	 }
	 catch (Exception e)
	 {
	      System.out.println("ERROR: " + e);
	 }
	
	// Beautification
	System.out.println("");
	
	// Display and convert the time based on timezone ID
	System.out.println(tzConverter.Convert(testDT, timeZone));
    }
}
