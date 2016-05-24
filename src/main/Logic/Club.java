package Logic;

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
   private String admin;
   private String advisor; 
   private ArrayList<String> members;
   private ArrayList<Event> clubEvents;
   private DatabaseManager db;

   
   /**
    * Initializes a Club object with information from the club's database entry
    * @param nm - name of the club
    */
   public Club(String nm)
   {
      // Set database destination to the club database
      db = DatabaseManager.getInstance();
      db.setDataBaseDestination("ClubDatabase", nm, true);
      db.accessDatabase();
      
      // Get the club's database entry
      JSONObject clubJson = db.getSingleDatabaseResults();
      
      // Initialize the instance variables
	  name = nm;
	  descrip = clubJson.getString("description");
	  admin = clubJson.getJSONObject("President").getString("name");
	  advisor = clubJson.getJSONObject("Advisor").getString("name");
	  
	  // Initalize the ArrayList of club members
	  JSONArray mems = clubJson.getJSONArray("members");
	  members = new ArrayList<String>();	  
	  for (int i = 0; i < mems.length(); i++)
	  {
		  members.add(mems.getString(i));
	  }
	  
	  // Initialize the ArrayList of club events
	  JSONArray events = clubJson.getJSONArray("events");
	  clubEvents = new ArrayList<Event>();
	  for (int i = 0; i < events.length(); i++)
	  {
		  JSONObject event = events.getJSONObject(i);
		  String desc = (String)(event.get("description"));

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
    * @desc - description of the club
    */
   public Club(String nm, String presEmail, String desc)
   {
	   // Set database destination to the club database
	   db = DatabaseManager.getInstance();
	   db.setDataBaseDestination("ClubDatabase", presEmail, true);
	   
	   // Create the club
	   db.createNewClub(nm, presEmail, desc);
	  	      
	   // Get the club's database entry
	   db.setDataBaseDestination("ClubDatabase", nm, true);
	   db.accessDatabase();
	   JSONObject clubJson = db.getSingleDatabaseResults();
	   
	   // Initialize instance variables
	   name = nm;
	   descrip = desc;
	   admin = clubJson.getJSONObject("President").getString("name");

	   // Initalize the ArrayList of club members
	   JSONArray mems = clubJson.getJSONArray("members");
	   members = new ArrayList<String>();	  
	   for (int i = 0; i < mems.length(); i++)
	   {
		   members.add(mems.getString(i));
	   }
		  
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
   
   public String getAdmin()
   {
      return admin;
   }

   public ArrayList<String> getMembers()
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

   
   /**
    * Sets the club's advisor
    * @param advEmail - Cal Poly email address of the club's advisor
    */
   public void setAdvisor(String advEmail)
   {
	   db.setDataBaseDestination("ClubDatabase", name, true);
	   db.setAdvisorOfClub(advEmail, name);
   }
   
   // Club event functions
   public void addEvent(Event event)
   {
      // Create the JSON object for the event
      JSONObject obj = new JSONObject();
      Date eDate = event.getDate();
      Time startT = event.getStartTime();
      Time endT = event.getEndTime();
      
      obj.put("description", event.getDescrip()).append("time", eDate.getMonth() + " " +
    		  eDate.getDay() + " " + startT.getHour() + " " + startT.getMinute() + " " +
    		  endT.getHour() + " " + endT.getMinute() + event.getDay());

      // Add the event to the database
      db.setDataBaseDestination("ClubDatabase", name, true);
      db.addEventToClub(obj, description.split("\\|")[0]); 

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
      db.removeEventFromClub(obj); 

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
      members.add(user.getName());
   }

   public void removeMember(User user)
   {
      // Set database destination to the club database
      DatabaseManager db = DatabaseManager.getInstance();
      db.setDataBaseDestination("ClubDatabase", name, true);
      
      // Remove user from the database
      db.removeStudentFromClub(user.getName());

      // Remove user from the local ArrayList
      members.remove(user.getName());
   }

   // Print club information
   public void printClubInfo()
   {
      System.out.println("Club name: " + name);
      System.out.println("Club description: " + descrip);
      
      System.out.println("Club admin: " + admin);
      System.out.println("Club advisor: " + advisor);
      System.out.println("Club members: ");
      for (int i = 0; i < members.size(); i++)
      {
         System.out.println("   - " + members.get(i));
      }

        System.out.println("Club events: ");
        for (int i = 0; i < clubEvents.size(); i++)
        {
            clubEvents.get(i).printEventInfo();
        }
   }
}
