//import net.sf.json.JSONObject;
import java.util.List;
import org.json.*;

/**
 *
 */
public class Parser {
     
     public List<Candidate> candidates;
     
    public JSONObject parse(JSONObject before, JSONObject after) {
	  // TODO Implement this

	  JSONObject output = new JSONObject();

	  // Before

	  JSONArray jsonArrayBefore = before.getJSONArray("candidates");


	  for (int i = 0; i < jsonArrayBefore.length(); i++)
	  {
	       System.out.println(jsonArrayBefore.getJSONObject(i).get("candidateName"));
	       
	       
	  }
	
	
	
	
	
        output.accumulate("first", before);
	// Candidate cand = new Candidate(123, 0, Title, Name, 0, StartTime, EndTime);
	
	
	
        return output;
    }

}
