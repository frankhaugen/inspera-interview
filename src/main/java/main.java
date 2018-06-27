import java.io.*;

import org.json.*;
import org.junit.Before;
//import net.sf.json.*;


@SuppressWarnings("unused")
public class main {
	
	public Parser parser = new Parser();
	public JSONObject jsonresult = new JSONObject();
	//public 
	public JSONObject jsonrafter = new JSONObject();
	
	public static String beforestr = "{\n" + 
			"  \"id\": 1,\n" + 
			"  \"meta\": {\n" + 
			"    \"title\": \"Title\",\n" + 
			"    \"startTime\": \"2016-01-20T10:00:00Z\",\n" + 
			"    \"endTime\": \"2016-01-20T16:00:00Z\"\n" + 
			"  },\n" + 
			"  \"candidates\": [\n" + 
			"    {\n" + 
			"      \"id\": 10,\n" + 
			"      \"candidateName\": \"C1\",\n" + 
			"      \"extraTime\": 0\n" + 
			"    },\n" + 
			"    {\n" + 
			"      \"id\": 11,\n" + 
			"      \"candidateName\": \"C2\",\n" + 
			"      \"extraTime\": 10\n" + 
			"    },\n" + 
			"    {\n" + 
			"      \"id\": 12,\n" + 
			"      \"candidateName\": \"C3\",\n" + 
			"      \"extraTime\": 20\n" + 
			"    }\n" + 
			"  ]\n" + 
			"}";
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.print("Hello World");
		
		JSONObject jsonbefore = new JSONObject(beforestr);
		
		System.out.print(jsonbefore.toString(2));
		
	}

}
