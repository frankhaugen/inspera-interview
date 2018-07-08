//import jdk.nashorn.internal.runtime.RewriteException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.gen5.api.Disabled;
import org.junit.gen5.commons.*;
//import org.junit.Assert.*;
import org.skyscreamer.jsonassert.*;

/**
 *
 * @author frank
 */
public class ParserTest {

    //static Parser parser = new Parser();
    
    static JSONObject jsonBefore;
    static JSONObject jsonAfter;
    static JSONObject jsonDiff;
    
    static JSONObject jsonResult;

     /**
      *
      */
     @Before
    public void setup() {
        
	// Load in test data from before.json and after.json
	jsonBefore = new JSONObject(FiletextCollector.Collect("src\\main\\resources\\before.json"));
	jsonAfter = new JSONObject(FiletextCollector.Collect("src\\main\\resources\\after.json"));
	
	// Loed in test-data for validation from diff.json
	jsonDiff = new JSONObject(FiletextCollector.Collect("src\\main\\resources\\diff.json"));
	
	jsonResult = Parser.parse(jsonBefore,jsonAfter);

	  
    }
    
    // Test to see if 
    @Test
    public void testIfJsonIsParsedCorrectly2()
    {
	 System.out.println("Diff + Result:");
	 System.out.println(jsonDiff.toString(0));
	 System.out.println(jsonResult.toString(0));
	 
	 JSONAssert.assertEquals(jsonResult, jsonDiff, JSONCompareMode.LENIENT);
    }
    
    // Test to see if 
    @Test
    public void testIfJsonMetaDataIsCorrect()
    {
	 System.out.println("Diff + Result:");
	 System.out.println(jsonDiff.toString(0));
	 System.out.println(jsonResult.toString(0));
	 
	 JSONAssert.assertEquals(jsonResult, jsonDiff, JSONCompareMode.LENIENT);
    }
}
