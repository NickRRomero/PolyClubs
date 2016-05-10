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

   public ArrayList<User> getGoing()
   {
      return going;
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

   // Event attendee functions
   public void addMember(User user)
   {
      going.add(user);
   }

   public void deleteMember(User user)
   {
      going.remove(user);
   }

   // Print event information
   public void printEventInfo()
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
