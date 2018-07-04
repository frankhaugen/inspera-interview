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
    //public 
     
    public JSONObject parse(JSONObject before, JSONObject after) {
	 
	  JSONObject inputMetaBefore = before.getJSONObject("meta");
	  JSONObject inputMetaAfter = after.getJSONObject("meta");
	  // TODO Implement this
	  JSONObject output = new JSONObject();
	  JSONArray outputMeta = new JSONArray();
	  
	  JSONObject outputMetaChild1 = new JSONObject();
	  JSONObject outputMetaChild1_a = new JSONObject();
	  JSONObject outputMetaChild1_b = new JSONObject();
	  JSONObject outputMetaChild1_c = new JSONObject();
	  
	  //outputMetaChild1_a = inputMetaBefore.get("title");
	  
	  outputMetaChild1.append("field", outputMetaChild1_a);
	  outputMetaChild1.append("before", inputMetaBefore.get("title"));
	  outputMetaChild1.append("after", inputMetaAfter.get("title"));
	  
	  JSONObject outputMetaChild2 = new JSONObject();
	  
	  
	  JSONObject outputCandidates = new JSONObject();
	  
	  JSONArray outputEdited = new JSONArray();
	  JSONArray outputAdded = new JSONArray();
	  JSONArray outputRemoved = new JSONArray();
	  
	  
	  
	  outputCandidates.append("edited", outputEdited);
	  outputCandidates.append("added", outputAdded);
	  outputCandidates.append("removed", outputRemoved);
	  
	  output.append("meta", outputMeta);
	  output.append("candidatates", outputCandidates);
	  
	  //System.out.println("Test output: " + before.getJSONArray("candidates"));
	  //System.out.println("Test output: " + before.getJSONObject("meta"));
	  //System.out.println("Test output: " + before.get("id"));
	  
	  JSONObject jsObj = new JSONObject();
	  jsObj.append("candidates", before.getJSONArray("candidates"));
	  //jsObj.append("candidates", after.getJSONArray("candidates"));
	  
	  for (int i = 0; i < after.getJSONArray("candidates").length(); i++)
	 {
	      if (jsObj.similar(after.getJSONArray("candidates").get(i)))
	      {
		   System.out.println("TEST!");
	      }
	 }
	  
	  
	  
	  //System.out.println(jsObj);
	  
	  for (Object candJSONObject : before.getJSONArray("candidates"))
	  {
	       //System.out.println(candJSONObject);
	  }
	  
	  for (Object candJSONObject : after.getJSONArray("candidates"))
	  {
	       
	  }
	  
	  /*
	  Meta
	       ()
	       ()
	  Candidates
	       ()
	       ()
	       ()
	  
	  /*
	  
	  {
	       "meta": [
		 {
		   "field": "title",
		   "before": "Title",
		   "after": "New Title"
		 },
		 {
		   "field": "endTime",
		   "before": "2016-01-20T18:00:00+02",
		   "after": "2016-01-20T20:00:00+02"
		 }
	       ],
	       "candidates": {
		 "edited": [
		   {
		     "id": 10
		   },
		   {
		     "id": 11
		   }
		 ],
		 "added": [
		   {
		     "id": 13
		   }
		 ],
		 "removed": [
		   {
		     "id": 12
		   }
		 ]
	       }
	     }
	  
	  */
	  
	  
	  
	  
	  
	  
	  
	  List<Candidate> beforeList = CreateCandidateList(before);
	  List<Candidate> afterList = CreateCandidateList(after);
	  List<Candidate> removedList = new ArrayList<Candidate>();
	  
//	  System.out.println(beforeList.toString());
//	  for (Candidate candidate : beforeList)
//	 {
//	      System.out.println(candidate.ID);
//	 }
//	  System.out.println(afterList.toString());
//	  for (Candidate candidate : afterList)
//	 {
//	      System.out.println(candidate.ID);
//	 }
//	  
//	  
//	  for (Candidate cand : afterList)
//	 {
//	      if (beforeList.contains(cand.ID))
//	      {
//		   System.out.println(cand.ID + " Is still here");
//	      }
//	 }
	  
	  
	  
	  for (int i = 0; i < beforeList.size(); i++)
	  {
	       //System.out.println("Checking: " + beforeList.get(i).ID);
	       for (int j = 0; j < afterList.size(); j++)
	       {
		    //System.out.println("With: " + afterList.get(j).ID);
		    if (beforeList.get(i).ID == afterList.get(j).ID)
		    {
			 //System.out.println(beforeList.get(i).ID + " is the same as: " + afterList.get(j).ID);
		    }
	       }
	  }
	  
	  
	  
	  
	  
	  
	  //output.accumulate("first", before);
	  
	  // Before
	  
	  
	  //System.out.println(candidates.toString());
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
