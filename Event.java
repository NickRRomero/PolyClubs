/** 
 * @author Holly
 * Represents a club event
 * Event start and end times based on a 24-hour clock
 */

import java.util.*;

public class Event
{
   private int startHour;
   private int startMinute;
   private int endHour;
   private int endMinute;
   private String location; // location of the event
   private String descrip; // description of the event
   private ArrayList<User> going; // users going to the event

   public Event(int sh, int sm, int eh, int em, String loc, String description)
   {
      startHour = sh;
      startMinute = sm;
      endHour = eh;
      endMinute = em;
      location = loc;
      descrip = description;
      going = new ArrayList<User>();
   }

   // Event getters
   public String getStartTime()
   {
      return Integer.toString(startHour) + ':' + Integer.toString(startMinute);
   } 

   public String getEndTime()
   {
      return Integer.toString(endHour) + ':' + Integer.toString(endMinute);
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
}