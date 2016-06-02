package main.logic;

/** 
 * @author Holly
 * Represents a Cal Poly campus club
 */

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.*;


public class Club
{
   private String name;
   private String descrip;
   private String admin;
   private String advisor; 
   private ArrayList<String> members;
   private ArrayList<Event> clubEvents;
   private DatabaseManager db;
   private static final Logger logger = Logger.getLogger( Club.class.getName() );
   private String description = "description";
   private String clubDatabase = "ClubDatabase";
   private String studentDatabase = "StudentDatabase";
   
   /**
    * Initializes a Club object with information from the club's database entry
    * @param nm - name of the club
 * @throws JSONException 
    */
   public Club(String nm) throws JSONException
   {
      // Set database destination to the club database
      db = DatabaseManager.getInstance();
      System.out.println(nm);
      db.setDataBaseDestination(clubDatabase, nm, false);
      db.accessDatabase();
      
      // Get the club's database entry
      JSONObject clubJson = db.getSingleDatabaseResults();
      logger.log(Level.INFO, clubJson.toString() + '\n');
      // Initialize the instance variables
      name = nm;
      descrip = clubJson.getString(description);
      admin = clubJson.getJSONObject("President").getString("name");
      advisor = clubJson.getJSONObject("Advisor").getString("name");
    
      // Initalize the ArrayList of club members
      JSONArray mems = clubJson.getJSONArray("members");
      members = new ArrayList<>();    
      for (int i = 0; i < mems.length(); i++)
      {
         members.add(mems.getString(i));
      }
    
      // Initialize the ArrayList of club events
      JSONArray events = clubJson.getJSONArray("events");
      clubEvents = new ArrayList<>();
      for (int i = 0; i < events.length(); i++)
      {
         JSONObject event = events.getJSONObject(i);
         String desc = (String)(event.get(description));

         String time = (String)(event.getString("time"));
         String[] timeInfo = time.split(" ");


         Date d = new Date(Integer.parseInt(timeInfo[0]), Integer.parseInt(timeInfo[1]));
         Time start = new Time(Integer.parseInt(timeInfo[2]), Integer.parseInt(timeInfo[3]));
         Time end = new Time(Integer.parseInt(timeInfo[4]), Integer.parseInt(timeInfo[5]));

         Event e = new Event(timeInfo[6], d, start, end, desc);
         clubEvents.add(e);
      }  
   }
   
   /**
    * Adds a new club to the club database
    * @param nm - name of the club
    * @param presEmail - Cal Poly email address of the club's president
 * @throws JSONException 
    * @desc - description of the club
    */
   public Club(String nm, String presEmail, String desc) throws JSONException
   {
      // Set database destination to the club database
      db = DatabaseManager.getInstance();
      db.setDataBaseDestination(studentDatabase, presEmail, true);

      // Create the club
      db.createNewClub(nm, presEmail, desc);
            
      // Get the club's database entry
      db.setDataBaseDestination(clubDatabase, nm, false);
      db.accessDatabase();
      JSONObject clubJson = db.getSingleDatabaseResults();

      // Initialize instance variables
      name = nm;
      descrip = desc;
      admin = clubJson.getJSONObject("President").getString("name");

      // Initalize the ArrayList of club members
      JSONArray mems = clubJson.getJSONArray("members");
      members = new ArrayList<>();   
      for (int i = 0; i < mems.length(); i++)
      {
         members.add(mems.getString(i));
      }

      clubEvents = new ArrayList<>();
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

   public String getAdmin()
   {
      return admin;
   }

   public List<String> getMembers()
   {
      return members;
   }

   public List<Event> getEvents()
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
  
   /**
   * Sets the club's advisor
   * @param advEmail - Cal Poly email address of the club's advisor
 * @throws JSONException 
   */
   public void setAdvisor(String advEmail) throws JSONException
   {
      db.setDataBaseDestination(clubDatabase, name, true);
      db.setAdvisorOfClub(advEmail, name);
   }
   
   // Club event functions
   public void addEvent(Event event) throws JSONException
   {
      // Create the JSON object for the event
      JSONObject obj = new JSONObject();
      Date eDate = event.getDate();
      Time startT = event.getStartTime();
      Time endT = event.getEndTime();
      
      obj.put(description, event.getDescrip());
      obj.put("time", eDate.getMonth() + " " +
         eDate.getDay() + " " + startT.getHour() + " " + startT.getMinute() + " " +
         endT.getHour() + " " + endT.getMinute() + event.getDay());

      // Add the event to the database
      db.setDataBaseDestination(clubDatabase, name, true);
      db.addEventToClub(obj); 

      // Add the event to the local ArrayList
      clubEvents.add(event);
   }

   public void removeEvent(String name) throws JSONException
   {
      String eventName;
      Event event = null;
      
      for (int i = 0; i < clubEvents.size(); i++)
      {
    	  eventName = (clubEvents.get(i).getDescrip()).split("\\|")[0];
    	  if (eventName.equals(name))
    	  {
    		  event = clubEvents.get(i);
    		  break;
    	  }
      }
      db.removeEventFromClub(name);

      // Remove the event from the local ArrayList
      clubEvents.remove(event);
   }
   
  /* public void removeEventFromDatabase(String eventName) throws JSONException
   {
      db.removeEventFromClub(eventName);
   }*/

   // Club membership functions
   public void addMember(String userName)
   {
      // Set database destination to the club database
      db.setDataBaseDestination(clubDatabase, name, true);
      
      // Add user to the database
      db.addStudentToClub(userName);

      // Add user to the local ArrayList
      members.add(userName);
   }

   public void removeMember(String userName)
   {
      // Set database destination to the club database
      db.setDataBaseDestination(clubDatabase, name, true);
      
      // Remove user from the database
      db.removeStudentFromClub(userName);

      // Remove user from the local ArrayList
      members.remove(userName);
   }

   // Print club information
   public void printClubInfo()
   {   
      logger.log(Level.INFO, "Club name: " + name);
      logger.log(Level.INFO, "Club description: " + descrip);      
      logger.log(Level.INFO, "Club admin: " + admin);
      logger.log(Level.INFO, "Club advisor: " + advisor);
      
      logger.log(Level.INFO, "Club members: ");
      for (int i = 0; i < members.size(); i++)
      {
         logger.log(Level.INFO, "   - " + members.get(i));
      }

      logger.log(Level.INFO, "Club events: ");
      for (int i = 0; i < clubEvents.size(); i++)
      {
         clubEvents.get(i).printEventInfo();
      }
   }
   
   /**
    * Logs the user out upon selecting the "Logout" option
    */
   private void logout()
   {
      try 
      {
         Logout lo = new Logout();
         lo.promptLogout();
      } 
      catch (Exception ex) 
      {
         Logger.getLogger(Club.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   /**
    * Method that prompts user for input and prints the requested information
    * @param isAdmin - true if the user is a club administrator
 * @throws JSONException 
 * @throws InterruptedException 
    */
   public void printClubPromptsAndInfo(boolean isAdmin) throws JSONException, InterruptedException
   {
      printClubInfo();
      logger.log(Level.INFO, "\n\n");

      Scanner userInput = new Scanner(System.in).useDelimiter("\n");
      String userChoice = "";

      logger.log(Level.INFO, "Please Enter One of the Following Options");

      while(!"Go To Club Prompts".equals(userChoice))
      {
         logger.log(Level.INFO, "Go To Club Prompts");
         if (isAdmin)
         {
            logger.log(Level.INFO, "Add Member");
            logger.log(Level.INFO, "Remove Member");
            logger.log(Level.INFO, "Add Event To Club");
            logger.log(Level.INFO, "Remove Event From Club");
            logger.log(Level.INFO, "Set Club Advisor");
         }
         logger.log(Level.INFO, "View Club Info");
         logger.log(Level.INFO, "Logout");
         userChoice = userInput.next().trim();

         processUserChoice(userChoice);
      }

      ClubPrompts backToClubPrompts = new ClubPrompts();
      backToClubPrompts.displayClubPrompt();        
   }
   
   private void processUserChoice(String userChoice) throws JSONException
   {
     Scanner input = new Scanner(System.in).useDelimiter("\r\n");
     
     ClubAdmin clubAdmin = new ClubAdmin("", "", "", "");
     switch(userChoice)
     {
         case "Add Member":
             logger.log(Level.INFO, "Please enter the new member's name.");
             addMember(input.next().trim());
             break;
         case "Remove Member":
             logger.log(Level.INFO, "Please enter the name of the member to remove");
             removeMember(input.next().trim());
             break;
         case "Add Event To Club":             
             addEvent(clubAdmin.createEvent(this));
             break;
         case "Remove Event From Club":
             logger.log(Level.INFO, "Please enter the name of the event to remove.");
             removeEvent(input.next().trim());
             break;
         case "Set Club Advisor":
             logger.log(Level.INFO, "Please enter the Cal Poly email of the club advisor.");
             setAdvisor(input.next().trim());
             break;
         case "Logout":
            logout();
            break;
         case "View Club Info":
        	 printClubInfo();
        	 break;
         default:
            break;
     }
   }
}

