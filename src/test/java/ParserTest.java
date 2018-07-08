import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
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
    
    // Test to see if the collection of text from file is working properly
    @Test
    public void testFiletextCollector()
    {
	 assertEquals("foo!bar", FiletextCollector.Collect("src\\test\\resources\\testFile.txt"));
    }
    
    // Test to see if edited time based on timezone-id returns correct values
    @Test
    public void testTimezoneConversion()
    {
	 assertEquals("2016-01-20T17:00:00+01" , TimeZoneConverter.Convert(jsonBefore.getJSONObject("meta").getString("endTime"), "Europe/Oslo"));
    }
    
    // Test to see if the ca is collected correctly
    @Test
    public void testJsonExtractEditedCandidates()
    {
	 JSONAssert.assertEquals(jsonResult.get("candidates").toString(), jsonDiff.get("candidates").toString(), JSONCompareMode.LENIENT);
    }
    
    
    // Test to see if the metadata is collected correctly
    @Test
    public void testJsonMetaDataMelding()
    {
	 JSONAssert.assertEquals(jsonResult.get("meta").toString(), jsonDiff.get("meta").toString(), JSONCompareMode.LENIENT);
    }
    
    // Test to see if the whole parser works as planned
    @Test
    public void testJsonParser()
    {
	 JSONAssert.assertEquals(jsonResult, jsonDiff, JSONCompareMode.LENIENT);
    }
}
