package main.Logic;

/**
 * User class of PolyClubs.
 * Made all methods public for testing purposes.
 * @author mboyken
 */

import java.io.Reader;
import java.io.StringReader;
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
   private ArrayList<Club> clubs;
   private ArrayList<String> messages;
   public boolean hasMsg;
   private JSONObject jsonDB;
   private DatabaseManager databaseManager;
   private Scanner scan;

   public User(String name, String phoneNum, String empl, String email)
         throws Exception
   {
      this.name = name;
      this.phoneNum = phoneNum;
      this.empl = empl;
      this.email = email;
      clubs = new ArrayList<Club>();
      messages = new ArrayList<String>();
      hasMsg = false;
      databaseManager = DatabaseManager.getInstance();
      databaseManager.setDataBaseDestination("StudentDatabase", name, true);
      // jsonDB = new databaseManager.execute().get();
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
      System.out.println("Name: " + member.getName());
      System.out.println("Phone number: " + member.getPhoneNum());
      System.out.println("Email address: " + member.getEmail());
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
      ArrayList<Event> events = club.getEvents();

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
      System.out.println("Enter a short message to be sent to "
            + member.getName() + ".");
      msg = scan.nextLine();
      member.addMsg(msg, this.name);
   }

   public void syncSchedule() throws JSONException
   {
      jsonDB = databaseManager.getSingleDatabaseResults();
      JSONArray arr = jsonDB.getJSONArray("courses");
      ArrayList<String> courseList = new ArrayList<String>();

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
   public Schedule createSchedule(ArrayList<String> courseList)
   {
      String dayList;
      int startH;
      int startM;
      int endH;
      int endM;
      Reader rdr;
      Scanner scan;
      String courseName = "";
      Course course;
      Time startTime;
      Time endTime;

      // iterate through courses to parse each course
      for (int iter = 0; iter < courseList.size(); iter++)
      {
         rdr = new StringReader(courseList.get(iter));
         scan = new Scanner(rdr).useDelimiter(" |:|-");

         // get course name
         courseName = scan.next();

         // get start hour
         startH = scan.nextInt();

         // get start minute
         startM = scan.nextInt();

         // get end hour
         endH = scan.nextInt();

         // get end minute
         endM = scan.nextInt();

         // collect string of days
         dayList = scan.next();

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
      System.out.println(messages.get(position));
      return messages.get(position);
   }
}
