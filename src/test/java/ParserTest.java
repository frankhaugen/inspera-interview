//import jdk.nashorn.internal.runtime.RewriteException;
import org.json.JSONObject;
import org.junit.After;
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
	
	// Get the result from the parser
	jsonResult = Parser.parse(jsonBefore,jsonAfter);

	  
    }
    
    // Test to see if the metadata is collected correctly
    @Test
    public void testIfJsonMetaData()
    {
	 JSONAssert.assertEquals(jsonResult.get("meta").toString(), jsonDiff.get("meta").toString(), JSONCompareMode.LENIENT);
    }
    
    // Test to see if the whole parser works as planned
    @Test
    public void testIfJsonParser()
    {
	 JSONAssert.assertEquals(jsonResult, jsonDiff, JSONCompareMode.LENIENT);
    }
}
