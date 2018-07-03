import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author frank
 */
public class TimeZoneConverter
{
     public String Convert(String inputTime, String zoneId)
     {
	  
	  Instant instant = Instant.parse(inputTime);
	  ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("Europe/Oslo"));
	  
	  String output = zonedDateTime.toString();
	  return output;
     }
}
