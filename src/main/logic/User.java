package main.logic;

/**
 * User class of PolyClubs.
 * Made all methods public for testing purposes.
 * @author mboyken
 */

import java.io.Reader;
import java.io.StringReader;
import java.util.logging;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class User
{
   private String name;
   private String phoneNum;
   private String email;
   private String empl;
   private Schedule schedule;
   private ArrayList<> clubs;
   private ArrayList<> messages;
   private boolean hasMsg;
   private JSONObject jsonDB;
   private DatabaseManager databaseManager;
   private Scanner scan;
   private static final Logger logger = Logger.getLogger( User.class.getName() );

   public User(String name, String phoneNum, String empl, String email)
         throws Exception
   {
      this.name = name;
      this.phoneNum = phoneNum;
      this.empl = empl;
      this.email = email;
      clubs = new ArrayList<>();
      messages = new ArrayList<>();
      hasMsg = false;
      databaseManager = DatabaseManager.getInstance();
      databaseManager.setDataBaseDestination("StudentDatabase", name, true);
      schedule = new Schedule();
   }

   /**
    * Method to leave a club.
    * 
    * @param club
    *           The club that will be affected.
    */
   public void leaveClub(Club club)
   {
      club.removeMember(this);
      clubs.remove(club);
   }

   /**
    * Method to view another club member.
    * 
    * @param member
    *           The member the user wants to view.
    */
   public void viewMember(User member)
   {
      logger.log(Level.INFO, "Name: " + member.getName());
      logger.log(Level.INFO, "Phone number: " + member.getPhoneNum());
      logger.log(Level.INFO, "Email address: " + member.getEmail());
   }

   /**
    * Getter for name.
    * 
    * @return The user's name.
    */
   public String getName()
   {
      return name;
   }

   /**
    * Getter for user's empl ID number.
    * 
    * @return The Empl ID number in String format.
    */
   public String getEmpl()
   {
      return empl;
   }

   /**
    * Getter for phone number.
    * 
    * @return The user's phone number.
    */
   public String getPhoneNum()
   {
      return phoneNum;
   }

   /**
    * Getter for email.
    * 
    * @return The user's email.
    */
   public String getEmail()
   {
      return email;
   }

   /**
    * Method to get all events from a club.
    * 
    * @param club
    *           The club that the user wants to view the events of.
    */
   public void getClubEvents(Club club)
   {
      ArrayList<> events = club.getEvents();

      for (int iter = 0; iter < events.size(); iter++)
      {
         events.get(iter).printEventInfo();
      }
   }

   /**
    * Method to send a message to another user.
    * 
    * @param User
    *           The member to which the message is to be sent.
    */
   public void sendMsg(User member)
   {
      String msg;

      scan = new Scanner(System.in);
      logger.log(Level.INFO, "Enter a short message to be sent to "
            + member.getName() + ".");
      msg = scan.nextLine();
      member.addMsg(msg, this.name);
   }

   public void syncSchedule() throws JSONException
   {
      jsonDB = databaseManager.getSingleDatabaseResults();
      JSONArray arr = jsonDB.getJSONArray("courses");
      List<> courseList = new ArrayList<>();

      // iterate through JSONArray to get strings for each course
      for (int iter = 0; iter < arr.length(); iter++)
      {
         courseList.add(arr.getString(iter));
      }

      createSchedule(courseList);
   }

   /**
    * Method to pull the user's schedule from their Portal. The JSONObject holds
    * only the information for the courses. The JSONArray hold each portion of
    * the courses attribute. Will use database object.
    * 
    * @throws JSONException
    */
   public Schedule createSchedule(List<> courseList)
   {
      String dayList;
      int startH;
      int startM;
      int endH;
      int endM;
      Reader rdr;
      Scanner stringScan;
      String courseName;
      Course course;
      Time startTime;
      Time endTime;

      // iterate through courses to parse each course
      for (int iter = 0; iter < courseList.size(); iter++)
      {
         rdr = new StringReader(courseList.get(iter));
         stringScan = new Scanner(rdr).useDelimiter(" |:|-");

         // get course name
         courseName = stringScan.next();

         // get start hour
         startH = stringScan.nextInt();

         // get start minute
         startM = stringScan.nextInt();

         // get end hour
         endH = stringScan.nextInt();

         // get end minute
         endM = stringScan.nextInt();

         // collect string of days
         dayList = stringScan.next();

         startTime = new Time(startH, startM);
         endTime = new Time(endH, endM);

         course = new Course(courseName, startTime, endTime, dayList);

         schedule.add(course);
      }

      return schedule;
   }

   /**
    * Method to receive message.
    * 
    * @param msg
    *           The message that is received.
    */
   public void addMsg(String msg, String from)
   {
      messages.add("From: " + from + " Message: " + msg);
      hasMsg = true;
   }

   /**
    * Get a message from User's messages.
    * 
    * @param position
    *           Number position of the message.
    */
   public String getMsg(int position)
   {
      logger.log(Level.INFO, messages.get(position));
      return messages.get(position);
   }
   
   /**
    * Returns whether or not the user has any messages.
    * 
    * @return True if there are any messages.
    */
   public boolean hasMsg()
   {
      return hasMsg;
   }
}
