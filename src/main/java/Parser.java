import org.json.JSONArray;
import org.json.JSONObject;

/**
 *   This parser takes two JSONObjects and compares them in a specific manner
 */
public class Parser {
     
     public static JSONObject jsonBefore;
     public static JSONObject jsonAfter;
     
     
     /**
      *	Gives out a parsed JsonObject based on two given JssonObjects
      * The parsing evaluates candidates who have been edited, removed and added
      * 
      * 
      * @param before Takes an JSONObject
      * @param after Takes a JSONObject
      * @return JSONObject which is a "diff" between before and after
      */
     public static JSONObject parse(JSONObject before, JSONObject after) {
	 
	  jsonBefore = before;
	  jsonAfter = after;
	  
	  // Create output JSONObject
	  JSONObject output = new JSONObject();
	  
	  // Create JSONObject that contains merged metadata
	  JSONArray outputMeta = GetMetadata(jsonBefore.getJSONObject("meta"), jsonAfter.getJSONObject("meta"), new String[] {"title","endTime"});
	  
	  // Collect data about changes to candidates list
	  JSONObject outputCandidates = GetCandidates(jsonBefore, jsonAfter);
	  
	  // Collect JSON data in the output
	  output.put("candidates", outputCandidates);
	  output.put("meta", outputMeta);
	  
	  // Output a JSONObject
	  return output;
    }
     
     /**
      * Extracts metadata fields from two jsonobject containing the keys given
      * 
      * @param inputBefore First jsonObject
      * @param inputAfter Second jsonObject
      * @param metadataFields string array of fields to be extracted
      * @return JsonArray containing metadata
      */
     public static JSONArray GetMetadata(JSONObject inputBefore, JSONObject inputAfter, String[] metadataFields)
     {
	  JSONArray output = new JSONArray();
	  
	  //String[] metadataFields = {"title","endTime"};
	  
	  // Use metadate keys to extract values specified above
	  for (String metadataField : metadataFields)
	  {
	       JSONObject metaData = new JSONObject();
	       metaData.put("field", metadataField);
	       
	       try{ metaData.put("before", TimeZoneConverter.Convert(inputBefore.get(metadataField).toString(), "Europe/Oslo")); } 
	       catch (Exception e) { metaData.put("before", inputBefore.get(metadataField)); }
	       
	       try{ metaData.put("after", TimeZoneConverter.Convert(inputAfter.get(metadataField).toString(), "Europe/Oslo")); } 
	       catch (Exception e) { metaData.put("after", inputAfter.get(metadataField)); }
	       
	       output.put(metaData);
	  }
	  
	  return output;
     }
     
     
     /**
      * Collects the different types of changes done to the list of candidates between the two JsonObjects
      * 
      * @param inputBefore JsonObject
      * @param inputAfter JsonObject
      * @return JsonObject
      */
     public static JSONObject GetCandidates(JSONObject inputBefore, JSONObject inputAfter)
     {
	  JSONObject output = new JSONObject();
	  
	  JSONArray CandidatesBefore = inputBefore.getJSONArray("candidates");
	  JSONArray CandidatesAfter = inputAfter.getJSONArray("candidates");
	  
	  // Add edited candidates to output JsonObject
	  output.put("edited", GetEditedCadidates(inputBefore.getJSONArray("candidates"), inputAfter.getJSONArray("candidates")));
	  
	  // Add removed to JSONArray
	  JSONArray outputRemoved = new JSONArray();
	  CandidatesBefore.forEach(a ->
	  {
	       JSONObject candBefore = (JSONObject)a;
	       outputRemoved.put(new JSONObject().put("id", candBefore.getInt("id")));
	  });
	  
	  // Add added to JSONArray
	  JSONArray outputAdded = new JSONArray();
	  CandidatesAfter.forEach(a ->
	  {
	       JSONObject candAfter = (JSONObject)a;
	       outputAdded.put(new JSONObject().put("id", candAfter.getInt("id")));
	  });
	  
	  
	  // Collect the different types for 
	  output.put("added", outputAdded);
	  output.put("removed", outputRemoved);
	  
	  return output;
     }
     
     /**
      * Gets the candidates that are in both arrays, and deletes them,
      * (Deteltion functionality was accidentaly brought when separration out to a method,
      * It is unclear why the deletions work)
      * 
      * @param inputBefore JsonArray containing a list of candidates
      * @param inputAfter JsonArray containing a list of candidates
      * @return JsonArray containing only the candidates located in both JsonObjects
      */
     public static JSONArray GetEditedCadidates(JSONArray inputBefore, JSONArray inputAfter)
     {
	  JSONArray output = new JSONArray();
	  
	  // Check for candidates existing i both arrays
	  inputBefore.forEach(a ->
	  {
	       JSONObject candBefore = (JSONObject)a;
	       inputAfter.forEach(b ->
	       {
		    JSONObject candAfter = (JSONObject)b;
		    if (candBefore.get("id") == candAfter.get("id"))
		    {
			 output.put(new JSONObject().put("id", candBefore.getInt("id")));
		    }
	       });
	  });
	  
	  // Delete edited entries to simplify the process of sort added and deleted
	  // Unclear how this part sgould work on a variable set in a different method
	  output.forEach(c ->
	  {
	       for (int i = 0; i < inputBefore.length(); i++)
	       {
		    JSONObject candTest = (JSONObject)c;
		    if (inputBefore.getJSONObject(i).get("id") == candTest.get("id") )
		    {
			 inputBefore.remove(i); // Why does this work?
		    }
	       }
	       for (int i = 0; i < inputAfter.length(); i++)
	       {
		    JSONObject candTest = (JSONObject)c;
		    if (inputAfter.getJSONObject(i).get("id") == candTest.get("id") )
		    {
			 inputAfter.remove(i);  // Why does this work?
		    }
	       }
	  });
	  
	  
	  // returns a JsonArray with the candidates that are in both JsonArrays
	  return output;

     }
     
     
}
