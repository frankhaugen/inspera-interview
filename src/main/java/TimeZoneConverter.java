import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author frank
 */
public class TimeZoneConverter
{
     
     public static String Convert(String inputTime, String zoneId)
     {
	  // Formatter to specify the returned datetime strings's format
	  DateTimeFormatter dtFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
	  
	  // Convert inputtedt datetime-string to a recognizable format before manipulation. Instant is the most logical format
	  Instant instant = Instant.parse(inputTime);
	  
	  // Convert Instant to another DateTime format and changing the time to
	  ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of(zoneId));
	  
	  String output = zonedDateTime.format(dtFormatter);
	  return output;
     }
}
