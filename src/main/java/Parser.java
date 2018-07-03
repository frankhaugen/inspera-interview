//import net.sf.json.JSONObject;
import org.json.*;

/**
 *
 */
public class Parser {

    public JSONObject parse(JSONObject before, JSONObject after) {
        // TODO Implement this
	
	JSONObject var1 = new JSONObject();
        
	
	
        JSONObject output = new JSONObject();
        
	
	
        output.accumulate("first", before);
        output.accumulate("second", after);
        
	
	
	
        return output;
    }

}
