import java.util.Iterator;
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
	  
	  //List<int> array1 = new ArrayList<int>();
	  
	  Boolean objectAdded = false;
	  
	  
	  
	  
	  
	  // Locate the edited candidates
	  for (int i = 0; i < inputCandidatesBefore.length(); i++)
	  {
	       for (int j = 0; j < inputCandidatesAfter.length(); j++)
	       {
		    if (inputCandidatesBefore.getJSONObject(i).getInt("id") == inputCandidatesAfter.getJSONObject(j).getInt("id"))
		    {
			 JSONObject tempJSONObject = new JSONObject();
			 tempJSONObject.put("id", inputCandidatesAfter.getJSONObject(j).get("id"));
			 //outputEdited.put(tempJSONObject);
		    }
	       }
	  }
	  
	  for (Object object : inputCandidatesBefore)
	  {
	       JSONObject testObject = (JSONObject)object;
	       for (Object object2 : inputCandidatesAfter)
	       {
		    JSONObject testObject2 = (JSONObject)object2;
		    if (testObject.getInt("id") == testObject2.getInt("id"))
		    {
			 System.out.println("TEST: " + testObject2.toString());
			 outputEdited.put(testObject);
		    }
	       }
	  }
	  
	  // Locate the added candidates
	  for (int i = 0; i < 10; i++)
	 {
	      for (int j = 0; j < 10; j++)
	      {
		   
	      }
	 }
	  
	  //JsonNode patch = JsonDiff.asJson(JsonNode source, JsonNode target);
	  
	  for (Iterator inputIterator = inputCandidatesBefore.iterator(); inputIterator.hasNext();)
	 {
	       //System.out.println("Testing: " + inputIterator.);
	      Object next = inputIterator.next();
	      
	 }
	  
	  //System.out.println(inputCandidatesBefore);
	  //System.out.println(inputCandidatesAfter);
	  
	  JSONPointer pointer = new JSONPointer("");
	  /*
	  for (int i = 0; i < inputCandidatesAfter.length(); i++)
	  {
	       for (int j = 0; j < inputCandidatesBefore.length(); j++)
	       {
		    if (inputCandidatesAfter.getJSONObject(i).getInt("id") == inputCandidatesBefore.getJSONObject(i).getInt("id"))
		    {
			 objectAdded = true;
		    }
		    
	       }
	       if (objectAdded)
	       {
		    outputAdded.put(inputCandidatesAfter.getJSONObject(i));
		    inputCandidatesAfter.remove(i);
		    objectAdded = false;
	       }
	  }
	  
	  
	  
	  /* TODO Figure this out
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
	  
	  /*
	  boolean found = false;
	  for (int i = 0; i < inputCandidatesAfter.length(); i++)
	      if (inputCandidatesAfter.getString(i).equals(myElementToSearch))
		  found = true;
	  */
	  outputCandidates.put("edited", outputEdited);
	  outputCandidates.put("added", outputAdded);
	  outputCandidates.put("removed", outputRemoved);
	  
	  // Unfortunately keys() just returns a raw Iterator...
	  Iterator beforeIterator = inputCandidatesBefore.iterator();
	  while (beforeIterator.hasNext()) {
	      Object candidateObject = beforeIterator.next();
	      
	      
	      
	      //System.out.println(candidateObject.toString());
	  }
	  
	  output.put("meta", outputMeta);
	  output.put("candidatates", outputCandidates);
	  

        return output;
    }
    
    // Function to find the index of an element in a primitive array in Java
     public static int find(int[] a, int target)
     {
	     for (int i = 0; i < a.length; i++)
		     if (a[i] == target)
			     return i;

	     return -1;
     }
}
