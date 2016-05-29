package main.Logic;

/** 
 * @author Holly
 * Represents a club event
 * Event start and end times based on a 24-hour clock
 */

public class Event
{
   private String dayOfWeek;
   private Date eventDate;
   private Time startTime; 
   private Time endTime;
   private String descrip; // formatted as "event_name|location|event_details"

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
	  
	  System.out.println("   - Event name: " + desc[0]);
	  System.out.println("      - Location: " + desc[1]);
	  System.out.println("      - Description: " + desc[2]);
	  System.out.println("      - Date: " + eventDate.toString() + "/16");
      System.out.println("      - Time: " + startTime.toString() + "-" + endTime.toString());   
   }
}
