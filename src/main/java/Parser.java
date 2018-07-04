//import net.sf.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import org.json.*;

/**
 *
 */
public class Parser {
     
     /**
      *
      */
     public List<Candidate> candidates = new ArrayList<Candidate>();
     
    public JSONObject parse(JSONObject before, JSONObject after) {
	  // TODO Implement this
	  
	  JSONObject output = new JSONObject();
	  output.accumulate("first", before);
	  
	  // Before
	  
	  
	  System.out.println(candidates.toString());
        return output;
    }
    
    public List<Candidate> CreateCandidateList()
    {
	 JSONArray jsonArrayBeforeCandidates = before.getJSONArray("candidates");
	  JSONObject jsonObjectBeforeMeta = before.getJSONObject("meta");
	  
	  int uid = 0;
	  try
	 {
	      for (int i = 0; i < jsonArrayBeforeCandidates.length(); i++)
	       {
		    JSONObject candidatedetails = jsonArrayBeforeCandidates.getJSONObject(i);
		    //System.out.println(candidatedetails);
		    
		    candidates.add(new Candidate(
			 uid,
			 candidatedetails.getInt("id"),
			 jsonObjectBeforeMeta.getString("title"),
			 candidatedetails.getString("candidateName"),
			 candidatedetails.getInt("extraTime"),
			 jsonObjectBeforeMeta.getString("startTime"),
			 jsonObjectBeforeMeta.getString("endTime")));
		    uid++;
	       }
	 } catch (Exception e)
	 {
	      System.out.println("For loop ERROR: " + e);
	 }
    }

}
