package Logic;

/**
 * User class of PolyClubs.
 * @author mboyken
 */

import org.json.*;
import java.util.*;
import java.io.*;

public class User
{
   private String name;
   private String phoneNum;
   private String email;
   private String empl;
   private Calendar events;
   private Schedule schedule;
   private ArrayList<Club> clubs;
   private ArrayList<String> messages;
   private boolean hasMsg;
   private JSONObject jsonDB;
   private DatabaseManager databaseManager;

   public User(String name, String phoneNum, String empl,
      String email) throws Exception
   {
      this.name = name;
      this.phoneNum = phoneNum;
      this.empl = empl;
      this.email = email;
      clubs = new ArrayList<Club>();
      messages = new ArrayList<String>();
      hasMsg = false;
      databaseManager = new DatabaseManager();
      jsonDB = new databaseManager.execture().get();
      schedule = new Schedule();
   }

   /**
    * Method to leave a club.
    * @param club The club that will be affected.
    */
   private void leaveClub(Club club)
   {
      clubs.remove(club);
   }

   /**
    * Method to request to join a club.
    * @param club The club that the user is requesting to join.
    */
   private void requestJoin(Club club)
   {
      club.addRequest(this);
   }

   /**
    * Method to view another club member.
    * Will use Android Studio GUI.
    * @param member The member the user wants to view.
    */
   private void viewMember(User member)
   {
      System.out.println("Name: " + member.getName());
      System.out.println("Phone number: " + member.getPhoneNum());
      System.out.println("Email address: " + member.getEmail());
   }

   /**
    * Getter for name.
    * @return The user's name.
    */
   public String getName()
   {
      return name;
   }
   /**
    * Getter for phone number.
    * @return The user's phone number.
    */
   public String getPhoneNum()
   {
      return phoneNum;
   }

   /**
    * Getter for email.
    * @return The user's email.
    */
   public String getEmail()
   {
      return email;
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
      member.addMsg(msg, this.name);
   }

   /**
    * Method to pull the user's schedule from their Portal.
    * The JSONObject holds only the information for the courses.
    * The JSONArray hold each portion of the courses attribute.
    * Will use database object.
    */
   private void syncSchedule()
   {
      private JSONObject jsonObj = jsonDB.getJSONObject("courses");
      private JSONArray arr = jsonDB.getJSONArray("courses");
      private ArrayList<String> courseList = new ArrayList<String>();
      private ArrayList<String> dayList = new ArrayList<String>();
      private int startH;
      private int startM;
      private int endH;
      private int endM;
      private Reader rdr;
      private Scanner scan;
      private String curText
      private String name;
      private Course course;

      //iterate through JSONArray to get strings for each course
      for (int iter = 0; iter < arr.length(); iter++)
      {
         courseList.add(arr.getJsonString(iter).getString());
      }

      //iterate through courses to parse each course
      for (int iter = 0; iter < courseList.length; iter++)
      {
         rdr = new StringReader(courses[iter]);
         scan = new Scanner(rdr).useDelimiter(":- _");

         //get start hour
         startH = Integer.parseInt(scan.next());
         //get start minute
         startM = Integer.parseInt(scan.next());
         //get end hour
         endH = Integer.parseInt(scan.next());
         //get end minute
         endM = Integer.parseInt(scan.next());
         //collect all days
         while (scan.hasNext())
         {
            dayList.add(scan.next());
         }

         course = new Course(name, startH, startM, endH, endM, dayList);

         schedule.addCourse(course);
      }
   }

   /**
    * Method to receive message.
    * @param msg The message that is received.
    */
   private void addMsg(String msg, String from)
   {
      messages.add("From: " + from + "Message: " + msg);
      hasMsg = true;
   }
}
