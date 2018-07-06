import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.*;

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
	  
	  inputCandidatesBefore.forEach(a ->
	  {
	       JSONObject candBefore = (JSONObject)a;
	       inputCandidatesAfter.forEach(b ->
	       {
		    JSONObject candAfter = (JSONObject)b;
		    if (candBefore.get("id") == candAfter.get("id"))
		    {
			 outputEdited.put(new JSONObject().put("id", candBefore.getInt("id")));
			 System.out.println("Foreach result true: " + b);
		    }
	       });
	  });
	  
	  
	 
	  
	  
	  
	  //JsonNode patch = JsonDiff.asJson(JsonNode source, JsonNode target);
	  
	  for (Iterator inputIterator = inputCandidatesBefore.iterator(); inputIterator.hasNext();)
	 {
	       //System.out.println("Testing: " + inputIterator.);
	      Object next = inputIterator.next();
	 }
	  
	  outputCandidates.put("edited", outputEdited);
	  outputCandidates.put("added", outputAdded);
	  outputCandidates.put("removed", outputRemoved);
	  
	  output.put("meta", outputMeta);
	  output.put("candidatates", outputCandidates);
	  

        return output;
    }
}
