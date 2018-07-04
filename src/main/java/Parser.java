//import net.sf.json.JSONObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
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
	  
	  List<String> list = new ArrayList<String>();

	  Iterator<String> iterator = list.iterator();

	  while(iterator.hasNext()){
	      String aString = iterator.next();
	  }
	  
	  
	  JSONObject output = new JSONObject();
	  output.accumulate("first", before);
	  //output.accumulate("first", after);
	  
	  // Before
	  JSONArray jsonArrayBeforeCandidates = before.getJSONArray("candidates");
	  //System.out.println(jsonArrayBeforeCandidates);
	  JSONObject jsonObjectBeforeMeta = before.getJSONObject("meta");
	  //System.out.println(jsonObjectBeforeMeta);
	  
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
	  
	  System.out.println(candidates.toString());
        return output;
    }

}
