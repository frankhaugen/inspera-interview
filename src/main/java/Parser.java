import java.util.ArrayList;
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
	 
	  // Create output JSONObject
	  JSONObject output = new JSONObject();
	  
	  // Create JSONObject to contain metadata
	  JSONArray outputMeta = GetMetadata(before.getJSONObject("meta"), after.getJSONObject("meta"));
	  
	  
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
	  outputCandidates.put("edited", outputEdited);
	  outputCandidates.put("added", outputAdded);
	  outputCandidates.put("removed", outputRemoved);
	  
	  
	  // Final pieces of the out JSONObject
	  
	  output.put("candidates", outputCandidates);
	  output.put("meta", outputMeta);
	  
	  // Output a JSONObject
	  return output;
    }
     
     
     // Collects the meta date from the two JSONObjects
     public static JSONArray GetMetadata(JSONObject inputBefore, JSONObject inputAfter)
     {
	  JSONArray output = new JSONArray();
	  
	  String[] metadataFields = {"title","endTime"};
	  
	  for (String metadataField : metadataFields)
	  {
	       JSONObject metaData = new JSONObject();
	       metaData.put("field", metadataField);
	       try{ metaData.put("before", TimeZoneConverter.Convert(inputBefore.get(metadataField).toString(), "Europe/Oslo")); } 
	       catch (Exception e) { metaData.put("before", inputBefore.get(metadataField)); }
	       try{ metaData.put("after", TimeZoneConverter.Convert(inputBefore.get(metadataField).toString(), "Europe/Oslo")); } 
	       catch (Exception e) { metaData.put("after", inputAfter.get(metadataField)); }
	       output.put(metaData);
	  }
	  
	  return output;
     }
}
