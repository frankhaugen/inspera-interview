//import net.sf.json.JSONObject;
import java.util.ArrayList;
import java.util.Iterator;
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
	  
	  
	  
	  // TODO check for changes
	  
	  JSONArray inputCandidatesBefore = before.getJSONArray("candidates");
	  JSONArray inputCandidatesAfter = after.getJSONArray("candidates");
	  
	  
	  
	  for (int i = 0; i < inputCandidatesBefore.length(); i++)
	  {
	       for (int j = 0; j < inputCandidatesAfter.length(); j++)
	       {
		    if (inputCandidatesBefore.getJSONObject(i).getInt("id") == inputCandidatesAfter.getJSONObject(j).getInt("id"))
		    {
			 outputEdited.put(inputCandidatesAfter.getJSONObject(j));
			 inputCandidatesBefore.remove(i);
			 inputCandidatesAfter.remove(j);
		    }
	       }
	  }
	  
	  // TODO Figure this out
	  for (int i = 0; i < inputCandidatesBefore.length(); i++)
	  {
	       for (int j = 0; j < inputCandidatesAfter.length(); j++)
	       {
		    if (inputCandidatesBefore.getJSONObject(i).getInt("id") != inputCandidatesAfter.getJSONObject(j).getInt("id"))
		    {
			 outputRemoved.put(inputCandidatesBefore.getJSONObject(i));
			 inputCandidatesBefore.remove(i);
		    }
	       }
	  }
	  for (int i = 0; i < inputCandidatesAfter.length(); i++)
	  {
	       outputAdded.put(inputCandidatesAfter.getJSONObject(i));
	  }
	  
	  
	  outputCandidates.put("edited", outputEdited);
	  outputCandidates.put("added", outputAdded);
	  outputCandidates.put("removed", outputRemoved);
	  
	  // Unfortunately keys() just returns a raw Iterator...
	  Iterator beforeIterator = inputCandidatesBefore.iterator();
	  while (beforeIterator.hasNext()) {
	      Object candidateObject = beforeIterator.next();
	      
	      
	      
	      System.out.println(candidateObject.toString());
	  }
	  
	  output.put("meta", outputMeta);
	  output.put("candidatates", outputCandidates);
	  

        return output;
    }
}
