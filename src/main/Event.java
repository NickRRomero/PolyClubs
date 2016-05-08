/** 
 * @author Holly
 * Represents a club event
 * Event start and end times based on a 24-hour clock
 */

import java.util.*;

public class Event
{
   private String dayOfWeek;
   private int startHour;
   private int startMinute;
   private int endHour;
   private int endMinute;
   private String eventName; 
   private String location; // location of the event
   private String descrip; // description of the event
   private ArrayList<User> going; // users going to the event

   public Event(String day, int sh, int sm, int eh, int em, String name, String loc, String description)
   {
      dayOfWeek = day;
      startHour = sh;
      startMinute = sm;
      endHour = eh;
      endMinute = em;
      eventName = name;
      location = loc;
      descrip = description;
      going = new ArrayList<User>();
   }

   // Event getters
   public String getDay()
   {
      return dayOfWeek;
   }

   public String getStartTime()
   {
      return Integer.toString(startHour) + ':' + Integer.toString(startMinute);
   } 

   public String getEndTime()
   {
      return Integer.toString(endHour) + ':' + Integer.toString(endMinute);
   }

   public String getName()
   {
      return eventName;
   }

   public String getLoc()
   {
      return location;
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

   public void setStartTime(int sh, int sm)
   {
      startHour = sh;
      startMinute = sm;
   }

   public void setEndTime(int eh, int em)
   {
      endHour = eh;
      endMinute = em;
   }

   public void setName(String name)
   {
      eventName = name;
   }
   
   public void setLoc(String loc)
   {
      location = loc;
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
      System.out.println("Start time: " + getStartTime());
      System.out.println("End time: " + getEndTime());
      System.out.println("Event location: " + location);
      System.out.println("Event description: " + descrip);
      System.out.println("Members going: ");
      for (int i = 0; i < going.size(); i++)
      {
         System.out.println("   -" + going.get(i).getName());
      }
   }
}
