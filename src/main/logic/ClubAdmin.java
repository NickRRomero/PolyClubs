package main.logic;

/**
 * Class for ClubAdmin.
 * Made all methods public for testing purposes.
 * @author mboyken
 */

import java.util.Scanner;
import java.util.logging;

import org.json.JSONException;

public class ClubAdmin extends User
{
   private Scanner scan;
   private static final Logger logger = Logger.getLogger( ClubAdmin.class.getName() );

   public ClubAdmin(String name, String phoneNum, String empl, String email)
         throws Exception
   {
      super(name, phoneNum, empl, email);
   }

   /**
    * Method to create an Event for the club.
    * 
    * @param club
    *           The club the event is part of.
    * @throws JSONException
    */
   public Event createEvent(Club club) throws JSONException
   {
      Event event;
      String day;
      int startH;
      int startM;
      int endH;
      int endM;
      int month;
      int dayMonth;
      String descr = "";
      Time startTime;
      Time endTime;
      Date date;

      logger.log(Level.INFO, "Enter the day of the week of the event.");
      day = scan.next();
      logger.log(Level.INFO, "Enter the month of the event as a number.");
      month = scan.nextInt();
      logger.log(Level.INFO, "Enter the day of the month of the event as a number.");
      dayMonth = scan.nextInt();
      logger.log(Level.INFO, "Enter the start hour of the event.");
      startH = scan.nextInt();
      logger.log(Level.INFO, "Enter the start minute of the event.");
      startM = scan.nextInt();
      logger.log(Level.INFO, "Enter the end hour of the event.");
      endH = scan.nextInt();
      logger.log(Level.INFO, "Enter the end minute of the event.");
      endM = scan.nextInt();
      logger.log(Level.INFO, "Enter the name of the event.");
      descr.concat(scan.next());
      descr.concat("|");
      logger.log(Level.INFO, "Enter the location of the event.");
      descr.concat(scan.next());
      descr.concat("|");
      logger.log(Level.INFO, "Enter the description of the event.");
      descr.concat(scan.next());

      date = new Date(month, dayMonth);
      startTime = new Time(startH, startM);
      endTime = new Time(endH, endM);

      event = new Event(day, date, startTime, endTime, descr);
      club.addEvent(event);
      return event;
   }

   /**
    * Method to create a club.
    * 
    * @return The new club created.
    * @throws JSONException
    */
   public Club createClub() throws JSONException
   {
      String clubName;
      String descr;

      logger.log(Level.INFO, "Enter the name of the new club.");
      clubName = scan.next();
      logger.log(Level.INFO, "Enter the description of the club.");
      descr = scan.next();

      return new Club(clubName, super.getEmail(), descr);
   }

   /**
    * Method to delete an event.
    * 
    * @param event
    *           The event to be deleted.
    * @param club
    *           The club that the event belonged to.
    * @throws JSONException
    */
   public void removeEvent(Event event, Club club) throws JSONException
   {
      club.removeEvent(event);
   }

   /**
    * Method to update an event.
    * 
    * @param event
    *           The event to be changed.
    */
   public void setTime(Event event)
   {
      int startH;
      int startM;
      int endH;
      int endM;
      Time startTime;
      Time endTime;

      scan = new Scanner(System.in);

      logger.log(Level.INFO, "Enter the new start hour of the event.");
      startH = scan.nextInt();
      logger.log(Level.INFO, "Enter the new start minute of the event.");
      startM = scan.nextInt();
      logger.log(Level.INFO, "Enter the new end hour of the event.");
      endH = scan.nextInt();
      logger.log(Level.INFO, "Enter the new end minute of the event.");
      endM = scan.nextInt();

      startTime = new Time(startH, startM);
      endTime = new Time(endH, endM);

      event.setTime(startTime, endTime);
   }

   /**
    * Method to update event location.
    * 
    * @param event
    *           The event to be changed.
    */
   public void setDescrip(Event event)
   {
      String descrip = "";

      scan = new Scanner(System.in);
      logger.log(Level.INFO, "Enter the new name of the event.");
      descrip.concat(scan.next());
      descrip.concat("|");
      logger.log(Level.INFO, "Enter the description of the event.");
      descrip.concat(scan.next());
      descrip.concat("|");
      logger.log(Level.INFO, "Enter the location of the event.");
      descrip.concat(scan.next());
      event.setDescrip(descrip);
   }

}
