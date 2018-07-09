import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author frank
 */
public class FiletextCollector
{
     
     /**Collect text data from a file
      *
      * @param input Path string for the file which we want to extract
      * @return string of the contents from the file in path inputted
      */
     public static String Collect(String input)
    {
	 // Create the string variable to be outputted
        String output = "";
	
	// The text contents from the file is added to the output
        try {
	  output = Files.lines(Paths.get(input)).collect(Collectors.joining("\n")); }
	catch (IOException ex) {
	  Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex); }
        
	// Return the string from the file
        return output;
    }
}
