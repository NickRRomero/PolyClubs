package main.logic;

/** 
 * @author Holly
 * Represents a club event
 * Event start and end times based on a 24-hour clock
 */
 
 import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Event
{
   private String dayOfWeek;
   private Date eventDate;
   private Time startTime; 
   private Time endTime;
   private String descrip; // formatted as "event_name|location|event_details"
   private static final Logger logger = Logger.getLogger( Event.class.getName() );

   public Event(String day, Date date, Time startT, Time endT, String description)
   {
      dayOfWeek = day;
      eventDate = date;
      startTime = startT;
      endTime = endT;
      descrip = description;
   }

   // Event getters
   public String getDay()
   {
      return dayOfWeek;
   }
  
   public Date getDate()
   {
      return eventDate;
   }

   public Time getStartTime()
   {
      return startTime;
   } 

   public Time getEndTime()
   {
      return endTime;
   }

   public String getDescrip()
   {
      return descrip;
   }


   // Event setters
   public void setDayOfWeek(String day)
   {
      dayOfWeek = day;
   }

   public void setDate(Date date)
   {
      eventDate = date;
   }

   public void setTime(Time startT, Time endT)
   {
      startTime = startT;
      endTime = endT;
   }

   public void setDescrip(String description)
   {
      descrip = description;
   }

   // Print event information
   public void printEventInfo()
   {
	  String[] desc = descrip.split("\\|");
	  
	  logger.log(Level.INFO, "   - Event name: " + desc[0]);
	  logger.log(Level.INFO, "      - Location: " + desc[1]);
	  logger.log(Level.INFO, "      - Description: " + desc[2]);
	  logger.log(Level.INFO, "      - Date: " + eventDate.toString() + "/16");
          logger.log(Level.INFO, "      - Time: " + startTime.toString() + "-" + endTime.toString());   
   }
}
