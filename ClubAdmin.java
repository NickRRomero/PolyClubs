/**
 * Class for ClubAdmin.
 * @author mboyken
 */

import java.util.*;

public class ClubAdmin extends User
{
   private int adminID;

   public ClubAdmin(int adminID, String name, String phoneNum,
      String empl, String email)
   {
      this.adminID = adminID;
      this.super(name, phoneNum, empl, email);
   }

   /**
    * Method to create an Event for the club.
    * @param start The start time of the event.
    * @param end The end time of the event.
    * @param loc The location of the event.
    * @param descr The description of the event.
    * @return Returns the created Event.
    */
   private void createEvent(Club club, String start, String end, String loc, String descr)
   {
      club.createEvent(start, end, loc, descr);
   }

   /**
    * Method to create a club.
    * @param name The name of the club.
    * @param descr The description of the club.
    */
   private Club createClub(String name, String descr)
   {
      return new Club(name, descr, this);
   }

   /**
    * Method to add a new administrator to a club.
    * @param club The club that will be affected.
    * @param admin The admin to be added.
    */
   private void addAdmin(Club club, ClubAdmin admin)
   {
      club.addAdmin(admin);
   }

   /**
    * Method to delete an event.
    * @param event The event to be deleted.
    * @param club The club that the event belonged to.
    */
   private void deleteEvent(Event event, Club club)
   {
      club.deleteEvent(event);
   }

   /**
    * Method to update an event.
    * @param event The Event to be updated.
    * @return The updated Event.
    */
   private void updateEvent(Club club, Event event, String start, String end,
       String loc, String descr)
   {
      club.updateEvent(event, start, end, loc, descr);
   }

   /**
    * Method to accept or reject member's request to join club.
    * @param user The User that will be affected.
    * @param club The Club that will be affected.
    * @param accept The decision; true if accepted; false if rejected.
    * @return Returns the decision of the admin.
    */
   private boolean acceptMember(User user, Club club, boolean accept)
   {
      if (accept)
      {
         club.addUser(user);
      }
      club.removeRequest(user);
      return accept;
   }

   /**
    * Getter for adminID.
    * @return Returns adminID.
    */
   public int getAdminID()
   {
      return adminID;
   }
}
