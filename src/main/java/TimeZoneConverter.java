import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author frank
 */
public class TimeZoneConverter
{

     /**Convert a datetime string to another timezone
      *
      * @param inputTime The datetime string to be changed. format: "2016-01-20T18:00:00Z"
      * @param zoneId The string value for the timezone to convert to. e.g. "Europe/Oslo"
      * @return A DateTime string with which has been offset to the time zone specified
      */
     public static String Convert(String inputTime, String zoneId)
     {
	  // Formatter to specify the returned datetime strings's format
	  DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
	  
	  // Convert inputted datetime-string to a recognizable format before manipulation. Instant is the most logical format
	  Instant instant = Instant.parse(inputTime);
	  
	  // Convert Instant to another DateTime format and changing the time to
	  ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of(zoneId));
	  
	  // create a string for the output using a formatter to format the datetime-string
	  String output = zonedDateTime.format(dateTimeFormatter);
	  return output.substring(0, output.length() - 3); // Cheat to get the wanted format for date, (+01 in stead of +01:00 which is proper)
     }
}
