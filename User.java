/**
 * User class of PolyClubs.
 * @author mboyken
 */

import java.util.*;

public class User
{
   private String name;
   private String phoneNum;
   private String email;
   private String empl;
   private Calendar events;
   private Calendar schedule;
   private ArrayList<Club> clubs;

   public User(String name, String phoneNum, String empl, String email)
   {
      this.name = name;
      this.phoneNum = phoneNum;
      this.empl = empl;
      this.email = email;
   }

   /**
    * Method to leave a club.
    * @param club The club that will be affected.
    */
   private boolean leaveClub(Club club)
   {
      clubs.remove(club);
   }

   /**
    * Method to request to join a club.
    * @param club The club that the user is requesting to join.
    * @return Return if successful.
    */
   private boolean requestJoin(Club club)
   {
      return true;
   }

   /**
    * Method to view another club member.
    * @param member The member the user wants to view.
    */
   private void viewMember(User member)
   {

   }

   /**
    * Method to get all events from a club.
    * @param club The club that the user wants to view the events of.
    * @return An array list of the club's events.
    */
   private ArrayList<Event> getClubEvents(Club club)
   {
      return club.getEvents();
   }

   /**
    * Method to send a message to another user.
    * @param msg The message to send.
    * @param User The member to which the message is to be sent.
    */
   private void sendMsg(String msg, User member)
   {

   }

   /**
    * Method to pull the user's schedule from their Portal.
    */
   private void syncSchedule()
   {

   }
}
