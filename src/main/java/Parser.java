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
	  // Calls on the method GetMetadata
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
      * Flexible, if the data should change length
      * 
      * @param inputBefore First jsonObject
      * @param inputAfter Second jsonObject
      * @param metadataFields string array of fields to be extracted
      * @return JsonArray containing metadata
      */
     public static JSONArray GetMetadata(JSONObject inputBefore, JSONObject inputAfter, String[] metadataFields)
     {
	  JSONArray output = new JSONArray();
	  
	  // Use metadate keys to extract values specified above
	  for (String metadataField : metadataFields)
	  {
	       JSONObject metaData = new JSONObject();
	       metaData.put("field", metadataField);
	       
	       // Ineligant yet functional
	       // Attempt to convert the given string to a datetime string, on failure, input the value found
	       try{ metaData.put("before", TimeZoneConverter.Convert(inputBefore.get(metadataField).toString(), "Europe/Oslo")); } 
	       catch (Exception e) { metaData.put("before", inputBefore.get(metadataField)); }
	       
	       try{ metaData.put("after", TimeZoneConverter.Convert(inputAfter.get(metadataField).toString(), "Europe/Oslo")); } 
	       catch (Exception e) { metaData.put("after", inputAfter.get(metadataField)); }
	       
	       // Atttatch metadata to output
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
	  // Create the JSONObject to be be outputted
	  JSONObject output = new JSONObject();
	  
	  
	  JSONArray CandidatesBefore = inputBefore.getJSONArray("candidates");
	  JSONArray CandidatesAfter = inputAfter.getJSONArray("candidates");
	  
	  // Add edited candidates to output JsonObject directly
	  output.put("edited", GetEditedCadidates(inputBefore.getJSONArray("candidates"), inputAfter.getJSONArray("candidates")));
	  
	  // Add removed to JSONArray, (only removed candidates are left in the before json data
	  JSONArray outputRemoved = new JSONArray();
	  CandidatesBefore.forEach(a ->
	  {
	       JSONObject candidateBefore = (JSONObject)a;
	       outputRemoved.put(new JSONObject().put("id", candidateBefore.getInt("id")));
	  });
	  output.put("removed", outputRemoved); // Add to output
	  
	  // Add edited to JSONArray, (only added candidates are left in the after json data
	  JSONArray outputAdded = new JSONArray();
	  CandidatesAfter.forEach(a ->
	  {
	       JSONObject candidateAfter = (JSONObject)a;
	       outputAdded.put(new JSONObject().put("id", candidateAfter.getInt("id")));
	  });
	  output.put("added", outputAdded); // Add to output
	  
	  // Return JsonObject with the trhee types of candidates
	  return output;
     }
     
     /**
      * Gets the candidates that are in both arrays, and deletes them,
      * (The Deletion functionality was accidentally brought when separation out to a method,
      * It is unclear why the deletions work, but my theory is that the inputted values are references and not new objects)
      * 
      * @param inputBefore JsonArray containing a list of candidates
      * @param inputAfter JsonArray containing a list of candidates
      * @return JsonArray containing only the candidates located in both JsonObjects
      */
     public static JSONArray GetEditedCadidates(JSONArray inputBefore, JSONArray inputAfter)
     {
	  // Create the JSONObject to be retured
	  JSONArray output = new JSONArray();
	  
	  // Check for candidates existing i both arrays
	  // Use a foreach loop to iterate through to delete the candidates present in both Before and After .json files
	  inputBefore.forEach(a ->
	  {
	       JSONObject candidateBefore = (JSONObject)a; // must create a new JSONObject, as "a" is not recognized as such
	       inputAfter.forEach(b ->
	       {
		    JSONObject candidateAfter = (JSONObject)b;
		    if (candidateBefore.get("id") == candidateAfter.get("id"))
		    {
			 output.put(new JSONObject().put("id", candidateBefore.getInt("id"))); // Add the candidate locadet in both arrays to the output array
		    }
	       });
	  });
	  
	  // Function but not pretty
	  // Delete edited entries to simplify the process of seperating added and deleted
	  // Unclear how this part should work on a variable set in a different method
	  output.forEach(c ->
	  {
	       for (int i = 0; i < inputBefore.length(); i++)
	       {
		    JSONObject candidate = (JSONObject)c;
		    if (inputBefore.getJSONObject(i).get("id") == candidate.get("id") )
		    {
			 inputBefore.remove(i); // Why does this work?
		    }
	       }
	       for (int i = 0; i < inputAfter.length(); i++)
	       {
		    JSONObject candidate = (JSONObject)c;
		    if (inputAfter.getJSONObject(i).get("id") == candidate.get("id") )
		    {
			 inputAfter.remove(i);  // Why does this work?
		    }
	       }
	  });
	  
	  // returns a JsonArray with the candidates that are in both JsonArrays
	  return output;

     }
     
     
}
