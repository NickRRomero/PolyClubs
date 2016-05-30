package main.logic;

import com.mongodb.client.MongoCollection;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.*;
import org.bson.Document;
import org.json.simple.JSONObject;

/**
 *
 * @author jacob
 */
public class ClubSearch 
{ 
   
   private final Scanner in;
   private String openPrompt;
   private DatabaseManager db;
   private static ClubSearch clubSearch;
   private static final Logger logger = Logger.getLogger( ClubSearch.class.getName() );
   
   /**
    * constructor initializes scanner and database manager
    */
   private ClubSearch() 
   {
      in = new Scanner(System.in);
      db = DatabaseManager.getInstance();
      
      openPrompt = "Would you like to search for a club, " +
  	    "filter by type of club, or exit? (s/f/e)";
   }
   
   /**
    * get an instance of ClubSearch
    * @return instance of ClubSearch
    */
   public static synchronized ClubSearch getInstance()
   {
       if (clubSearch == null)
       {
           clubSearch = new ClubSearch();
       }
       
       return clubSearch;
   }
   
   /**
    * prompt user to search or filter clubs
    */
   private void displayOpen() {
	   boolean done = false;
	   
	   do {
		   logger.log(Level.INFO, "\n" + openPrompt);
		   String choice = in.nextLine();
		   
		   // search
		   if ("s".equalsIgnoreCase(choice)) {
			   done = true;
			   displaySearch();
		   }
		   // filter
		   else if ("f".equalsIgnoreCase(choice)) {
			   done = true;
			   displayFilter();
		   }
		   // exit
		   else if ("e".equalsIgnoreCase(choice)) {
			   done = true;
			   ; //exit
		   }
		   // invalid
		   else 
			   logger.log(Level.INFO, "Invalid option.");
		   
	   } while (!done);
   }
   
   /**
    * display search prompt to user
    */
   private void displaySearch() {
      logger.log(Level.INFO, "\nPlease enter the name of the club you would " +
       "like to search for or 'back' to return to initial prompt:");
      
      // club name that user searches for
      String club = in.nextLine();
      
      // exit if user chooses to do so, otherwise search database
      if ("back".equalsIgnoreCase(club))
         displayOpen();
      else
         searchClub(club);
   }
   
   /**
    * Search database for the name of the club input by user
    * @param club 
    */
   public void searchClub(String club){
      /**Database Manager Object used to access mlab.com*/
      db.setDataBaseDestination("ClubDatabase", club, true);
      db.accessDatabase();

      // club returned by search or null if no club found
      JSONObject clubName = db.getSingleDatabaseResults();
      
      // if club is in database, ask user if they would like to view club
      // else display message and return to search prompt
      if(clubName != null) {
         String name = clubName.get("ClubName").toString();
         viewPage(name);
      }
      else {
         logger.log(Level.INFO, "The club you entered is " +
          "not in the database.");
         displaySearch();
      } 
   }
   
   /**
    * Ask user if they want to view a club page
    * @param club
    */
   private void viewPage(String club) {
      logger.log(Level.INFO, "Would you like to view the " + 
       club + " clubpage? (y/n)");
      
      // if yes, go to club page
      // else return to display prompt
      if("y".equalsIgnoreCase(in.nextLine())) 
         ;// club page
      else 
         displaySearch(); 
   }
   
   /**
    * Display filter prompt to user
    */
   private void displayFilter() {
      logger.log(Level.INFO, "\nPlease enter the type of club you would " +
       "like to filter by or 'back' to return to initial prompt:");
      
      // club name that user searches for
      String filter = in.nextLine();
      
      // exit if user chooses to do so, otherwise search database
      if ("back".equalsIgnoreCase(filter))
         displayOpen();
      else
         filterClub(filter);
   }
   
   /**
    * Filter clubs by category
    * @param filter
    */
   private void filterClub(String filter) {
	   /**Database Manager Object used to access mlab.com*/
      db.setDataBaseDestination("ClubDatabase", null, false);
      db.accessDatabase();

      MongoCollection<Document> col = db.getEntireDatabaseResults();
      
      // iterator to go through clubs in database
      Iterable<Document> iter;
      iter = col.find();
      
      // ArrayList of clubs matching the filter type 
      ArrayList<JSONObject> clubs = new ArrayList<>();
      
      // check clubs to see if they match the type input by the user
      for (Document doc : iter) {
         JSONObject profile = new JSONObject(doc);
         
         if (filter.equals(profile.get("type").toString()))
        	 clubs.add(profile);
      }
      
      if (clubs.isEmpty()) {
    	  logger.log(Level.INFO, "There are no clubs matching that type.");
    	  displayFilter();
      }
      else     
    	  filter(clubs); 
}

   /**
    * display filtered clubs to user
    * @param clubs
    */
   private void filter(ArrayList<JSONObject> clubs) {
	  int index; 
	  String back = "";
	  boolean done;
	   
	  // print clubs 
	  do {
	      for (JSONObject c : clubs) {
	    	  index = clubs.indexOf(c) + 1;
	    	  logger.log(Level.INFO, index + ". " + c.get("ClubName").toString());
	      }
	
	      index = -1;
            
    	  logger.log(Level.INFO, "Please enter the number of the club you" +
		   " would like to view or 'back' to return to the filter page");
    	  
    	  if (in.hasNextInt()) 
        	  index = in.nextInt();
          else
        	  back = in.nextLine();
    	  
    	  // if user wants to view club page go to page
	      if (index > 0 && index <= clubs.size()) {
	    	  done = true;
	    	  clubs.get(index);
	      }
	      else if ("back".equals(back)) {
	    	  done = true;
	    	  displayFilter();
	      }
	      else {
	    	  done = false;
	    	  logger.log(Level.INFO, "Invalid option.\n");
	      }
      } while (!done);
   }
}
