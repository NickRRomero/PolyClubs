/** 
 * @author Holly
 * Represents a club event
 * Event start and end times based on a 24-hour clock
 */

import java.util.*;

public class Event
{
   private String dayOfWeek;
   private Date eventDate;
   private Time startTime; 
   private Time endTime;
   private String descrip; // contains event name, location, and details
   private ArrayList<User> going; // users going to the event

   public Event(String day, Date date, Time startT, Time endT, String description)
   {
      dayOfWeek = day;
      eventDate = date;
      startTime = startT;
      endTime = endT;
      descrip = description;
      going = new ArrayList<User>();
   }

   // Event getters
   private String getDay()
   {
      return dayOfWeek;
   }
  
   private Date getDate()
   {
      return eventDate;
   }

   private Time getStartTime()
   {
      return startTime;
   } 

   private Time getEndTime()
   {
      return endTime;
   }

   private String getDescrip()
   {
      return descrip;
   }

   private ArrayList<User> getGoing()
   {
      return going;
   }

   // Event setters
   private void setDayOfWeek(String day)
   {
      dayOfWeek = day;
   }

   private void setDate(Date date)
   {
      eventDate = date;
   }

   private void setTime(Time startT, Time endT)
   {
      startTime = startT;
      endTime = endT;
   }

   private void setDescrip(String description)
   {
      descrip = description;
   }

   // Event attendee functions
   private void addMember(User user)
   {
      going.add(user);
   }

   private void deleteMember(User user)
   {
      going.remove(user);
   }

   // Print event information
   private void printEventInfo()
   {
      System.out.println("Start time: " + startTime.toString());
      System.out.println("End time: " + endTime.toString());
      System.out.println("Event description: " + descrip);
      System.out.println("Members going: ");
      for (int i = 0; i < going.size(); i++)
      {
         System.out.println("   -" + going.get(i).getName());
      }
   }
}
