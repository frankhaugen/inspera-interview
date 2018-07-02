import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author frank
 */
public class TimeZoneConverter
{
     public String Convert(String inputTime, int timeZoneOffset)
     {
	  
	  DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
	  
	  
	  
	  LocalDateTime dt = LocalDateTime.parse(inputTime, formatter);
	  System.out.println(dt);
	  
	  String output = dt.toString();
	  
	  return output;
     }
}
