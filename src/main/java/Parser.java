import org.json.JSONArray;
import org.json.JSONObject;

/**
 *   This parser takes two JSONObjects and compares them in a specific manner
 */
public class Parser {
     
     /**
      *	Created to parse two json datasets and returns a  
      * parsed json dataset
      * 
      * @param before Takes an JSONObject
      * @param after Takes a JSONObject
      * @return JSONObject which is a "diff" between before and after
      */
     public static JSONObject parse(JSONObject before, JSONObject after) {
	 
	  JSONObject inputMetaBefore = before.getJSONObject("meta");
	  JSONObject inputMetaAfter = after.getJSONObject("meta");
	  
	  // Create output JSONObject
	  JSONObject output = new JSONObject();
	  
	  // Create JSONObject to contain metadata
	  JSONArray outputMeta = new JSONArray();
	  
	  // Metadata has two child elements, this is the one we 
	  JSONObject outputMetaChild1 = new JSONObject();
	  JSONObject outputMetaChild2 = new JSONObject();
	  
	  // 
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
	  
	  
	  JSONArray inputCandidatesBefore = before.getJSONArray("candidates");
	  JSONArray inputCandidatesAfter = after.getJSONArray("candidates");
	  
	  
	  // Check for edited
	  inputCandidatesBefore.forEach(a ->
	  {
	       JSONObject candBefore = (JSONObject)a;
	       inputCandidatesAfter.forEach(b ->
	       {
		    JSONObject candAfter = (JSONObject)b;
		    if (candBefore.get("id") == candAfter.get("id"))
		    {
			 outputEdited.put(new JSONObject().put("id", candBefore.getInt("id")));
		    }
	       });
	  });
	  
	  // Delete edited entries to simplify the process of sort added and deleted
	  outputEdited.forEach(c ->
	  {
	       for (int i = 0; i < inputCandidatesBefore.length(); i++)
	       {
		    JSONObject candTest = (JSONObject)c;
		    if (inputCandidatesBefore.getJSONObject(i).get("id") == candTest.get("id") )
		    {
			 inputCandidatesBefore.remove(i);
		    }
	       }
	       for (int i = 0; i < inputCandidatesAfter.length(); i++)
	       {
		    JSONObject candTest = (JSONObject)c;
		    if (inputCandidatesAfter.getJSONObject(i).get("id") == candTest.get("id") )
		    {
			 inputCandidatesAfter.remove(i);
		    }
	       }
	  });
	  
	  /// TODO: Can this be done differently, (extract tom function/method?)
	  // Add removed to JSONArray
	  inputCandidatesBefore.forEach(a ->
	  {
	       JSONObject candBefore = (JSONObject)a;
	       outputRemoved.put(new JSONObject().put("id", candBefore.getInt("id")));
	  });
	  
	  // Add added to JSONArray
	  inputCandidatesAfter.forEach(a ->
	  {
	       JSONObject candAfter = (JSONObject)a;
	       outputAdded.put(new JSONObject().put("id", candAfter.getInt("id")));
	  });
	  
	  
	  // Collect the different types for 
	  outputCandidates.accumulate("edited", outputEdited);
	  outputCandidates.accumulate("added", outputAdded);
	  outputCandidates.accumulate("removed", outputRemoved);
	  
	  
	  // Final pieces of the out JSONObject
	  
	  output.put("candidates", outputCandidates);
	  output.put("meta", outputMeta);
	  
	  // Output a JSONObject
	  return output;   
    }
}
