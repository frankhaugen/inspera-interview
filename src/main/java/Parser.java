//import net.sf.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import org.json.*;
//import com.google.gson;

/**
 *
 */
public class Parser {
     
     /**
      *
      */
     
    public static JSONObject parse(JSONObject before, JSONObject after) {
	 
	  JSONObject inputMetaBefore = before.getJSONObject("meta");
	  JSONObject inputMetaAfter = after.getJSONObject("meta");
	  // TODO Implement this
	  JSONObject output = new JSONObject();
	  
	  
	  JSONArray outputMeta = new JSONArray();
	  
	  JSONObject outputMetaChild1 = new JSONObject();
	  JSONObject outputMetaChild2 = new JSONObject();
	  
	  outputMetaChild1.put("field", "title");
	  outputMetaChild1.put("before", inputMetaBefore.get("title"));
	  outputMetaChild1.put("after", inputMetaAfter.get("title"));
	  
	  outputMetaChild2.put("field", "endTime");
	  outputMetaChild2.put("before", TimeZoneConverter.Convert(inputMetaBefore.get("endTime").toString(), "Europe/Oslo"));
	  outputMetaChild2.put("after", TimeZoneConverter.Convert(inputMetaAfter.get("endTime").toString(), "Europe/Oslo"));
	  
	  outputMeta.put(outputMetaChild1);
	  outputMeta.put(outputMetaChild2);
	  
	  JSONObject outputCandidates = new JSONObject();
	  
	  JSONArray outputEdited = new JSONArray();
	  JSONArray outputAdded = new JSONArray();
	  JSONArray outputRemoved = new JSONArray();
	  
	  outputCandidates.put("edited", outputEdited);
	  outputCandidates.put("added", outputAdded);
	  outputCandidates.put("removed", outputRemoved);
	  
	  // TODO chack for changes
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  output.put("meta", outputMeta);
	  output.put("candidatates", outputCandidates);
	  

        return output;
    }
    
    
    public List<Candidate> CreateCandidateList(JSONObject input)
    {
	 List<Candidate> output = new ArrayList<Candidate>();
	 
	  JSONArray jsonArrayCandidates = input.getJSONArray("candidates");
	  JSONObject jsonObjectMeta = input.getJSONObject("meta");
	  
	  int uid = 0;
	  try
	 {
	      for (int i = 0; i < jsonArrayCandidates.length(); i++)
	       {
		    JSONObject candidatedetails = jsonArrayCandidates.getJSONObject(i);
		    //System.out.println(candidatedetails);
		    
		    output.add(new Candidate(
			 uid,
			 candidatedetails.getInt("id"),
			 jsonObjectMeta.getString("title"),
			 candidatedetails.getString("candidateName"),
			 candidatedetails.getInt("extraTime"),
			 jsonObjectMeta.getString("startTime"),
			 jsonObjectMeta.getString("endTime")));
		    uid++;
	       }
	 } catch (Exception e)
	 {
	      //System.out.println("For loop ERROR: " + e);
	 }
	  
	  return output;
    }

}
