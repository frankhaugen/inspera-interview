import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author frank
 */
public class TimeZoneConverter
{
     public String Convert(String inputTime, Long timeZoneOffset, ZoneId zoneId)
     {
	  
	  DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
	  
	  
	  
	  ZonedDateTime dt = ZonedDateTime.parse(inputTime, formatter);
	  System.out.println(dt);
	  
	  if (timeZoneOffset < 0)
	  {
	       dt = dt.withZoneSameLocal(zoneId);
	  } else
	  {
	       dt = dt.plusHours(timeZoneOffset);
	  }
	  
	  String output = dt.toString();
	  return output;
     }
}
