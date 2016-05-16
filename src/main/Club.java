/** 
 * @author Holly
 * Represents a Cal Poly campus club
 */

import java.util.*;

public class Club
{
   private String name;
   private String descrip;
   private ArrayList<User> userRequests;
   private boolean areRequests;

   public Club(String nm, String des, ClubAdmin admin)
   {
      name = nm;
      descrip = des;
      this.addAdmin(admin);
      areRequests = false;
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

   // Club admin functions
   public boolean addAdmin(ClubAdmin admin)
   {

      return admins.add(admin);
   }

   public boolean deleteAdmin(ClubAdmin admin)
   {
      return admins.remove(admin);
   }

   // Member request functions
   public boolean addRequest(User user)
   {
      areRequests = true;
      return userRequests.add(user);
   }

   public boolean removeRequest(User user)
   {
      boolean val = false;
      val = userRequests.remove(user);
      areRequests = !userRequests.isEmpty();

      return val;
   }

   // Club event functions
   public void addEvent(Event event)
   {
      return clubEvents.add(event);
   }

   public void removeEvent(Event event)
   {
      return clubEvents.remove(event);
   }

   // Club membership functions
   public void addMember(User user)
   {
      // Set database destination to the club database
      DatabaseManager db = DatabaseManager.getInstance();
      db.setDataBaseDestination("ClubDatabase", name, true);
      
      // Add user to the database
      db.addStudentToClub(user.name);

      // Add user to the local ArrayList
      members.add(user);
   }

   public void removeMember(User user)
   {
      // Set database destination to the club database
      DatabaseManager db = DatabaseManager.getInstance();
      db.setDataBaseDestination("ClubDatabase", name, true);
      
      // Remove user from the database
      db.removeStudentFromClub(user.name);

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

