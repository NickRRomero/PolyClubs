/** 
 * @author Holly
 * Represents a Cal Poly campus club
 */

import java.util.*;
import org.json.*;

public class Club
{
   private String name;
   private String descrip;
   private ArrayList<ClubAdmin> admins;
   private ArrayList<User> members;
   private ArrayList<Event> clubEvents;
   private DatabaseManager db;

   
   /**
    * Initializes a Club object with information from the club's database entry
    * @param nm - name of the club
    */
   public Club(String nm)
   {
      name = nm;
      
      // Set database destination to the club database
      db = DatabaseManager.getInstance();
      db.setDataBaseDestination("ClubDatabase", name, true);
      
      // Initialize the instance variables
     name = nm;
     admins = new ArrayList<ClubAdmin>();
     members = new ArrayList<User> ();
     clubEvents = new ArrayList<Event>();
      
      // Get the club's database entry
   }
   
   /**
    * Adds a new club to the club database
    * @param nm - name of the club
    * @param presEmail - Cal Poly email address of the club's president
    * @desc - description of the club
    */
   public Club(String nm, String presEmail, String desc)
   {
      // Set database destination to the club database
      db = DatabaseManager.getInstance();
      db.setDataBaseDestination("ClubDatabase", name, true);
      
      // Create the club
      db.createNewClub(nm, presEmail, desc);
      
      // Initialize instance variables
      name = nm;
      descrip = desc;
      admins = new ArrayList<ClubAdmin>();
      members = new ArrayList<User> ();
      clubEvents = new ArrayList<Event>();
   }

    // Club getters
   public String getName()
   {
      return name;
   }

   public String getDescrip()
   {
      return descrip;
   }
   
   public ArrayList<ClubAdmin> getAdmins()
   {
      return admins;
   }

   public ArrayList<User> getMembers()
   {
      return members;
   }

   public ArrayList<Event> getEvents()
   {
      return clubEvents;
   }

   // Club setters
   public void setName(String newName)
   {
      name = newName;
   }

   public void setDescription(String newDescrip)
   {
      descrip = newDescrip;
   }

   
   // Club event functions
   public void addEvent(Event event)
   {
      // Create the JSON object for the event
      JSONObject obj = new JSONObject();
      obj.put(event.getDescrip(), event.getDate().toString() + " " +
         event.getStartTime().toString() + "-" + event.getEndTime().toString() +
         event.getDay());

      // Add the event to the database
      db.addEventToClub(obj); 

      // Add the event to the local ArrayList
      clubEvents.add(event);
   }

   public void removeEvent(Event event)
   {
      // Create the JSON object for the event
      JSONObject obj = new JSONObject();
      obj.put(event.getDescrip(), event.getDate().toString() + " " +
         event.getStartTime().toString() + "-" + event.getEndTime().toString() +
         event.getDay());

      // Remove the event from the database
      db.addEventToClub(obj); 

      // Remove the event from the local ArrayList
      clubEvents.remove(event);
   }

   // Club membership functions
   public void addMember(User user)
   {
      // Set database destination to the club database
      DatabaseManager db = DatabaseManager.getInstance();
      db.setDataBaseDestination("ClubDatabase", name, true);
      
      // Add user to the database
      db.addStudentToClub(user.getName());

      // Add user to the local ArrayList
      members.add(user);
   }

   public void removeMember(User user)
   {
      // Set database destination to the club database
      DatabaseManager db = DatabaseManager.getInstance();
      db.setDataBaseDestination("ClubDatabase", name, true);
      
      // Remove user from the database
      db.removeStudentFromClub(user.getName());

      // Remove user from the local ArrayList
      members.remove(user);
   }

   // Print club information
   public void printClubInfo()
   {
      System.out.println("Club name: " + name);
      System.out.println("Club description: " + descrip);
      
      System.out.println("Club admins: ");
      for (int i = 0; i < admins.size(); i++)
      {
         System.out.println("   -" + admins.get(i));
      }

      System.out.println("Club members: ");
      for (int i = 0; i < members.size(); i++)
      {
         System.out.println("   -" + members.get(i));
      }

        System.out.println("Club events: ");
        for (int i = 0; i < clubEvents.size(); i++)
        {
            System.out.println("   -" + clubEvents.get(i).getDescrip());
        }
   }
}