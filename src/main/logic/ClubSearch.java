package main.logic;

import com.mongodb.client.MongoCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.*;
import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author jacob
 */
public class ClubSearch 
{ 
   
   private final Scanner in;
   private String openPrompt;
   private String cDatabase;
   private String cName;
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
  	    "filter by type of club, list clubs, or exit? (s/f/l/e)";
      cDatabase = "ClubDatabase";
      cName = "ClubName";
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
 * @throws JSONException 
 * @throws InterruptedException 
    */
   public void displayOpen() throws JSONException, InterruptedException {
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
		   // list
		   else if ("l".equalsIgnoreCase(choice)) {
			   done = true;
			   list();
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
 * @throws JSONException 
 * @throws InterruptedException 
    */
   private void displaySearch() throws JSONException, InterruptedException {
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
 * @throws JSONException 
 * @throws InterruptedException 
    */
   public void searchClub(String club) throws JSONException, InterruptedException{
      /**Database Manager Object used to access mlab.com*/
      db.setDataBaseDestination(cDatabase, club, false);
      db.accessDatabase();

      // club returned by search or null if no club found
      JSONObject clubName = db.getSingleDatabaseResults();
      
      // if club is in database, ask user if they would like to view club
      // else display message and return to search prompt
      if(clubName != null) {
         String name = clubName.get(cName).toString();
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
 * @throws JSONException 
 * @throws InterruptedException 
    */
   private void viewPage(String club) throws JSONException, InterruptedException {
      logger.log(Level.INFO, "Would you like to view the " + 
       club + " clubpage? (y/n)");
      
      // if yes, go to club page
      // else return to display prompt
      if("y".equalsIgnoreCase(in.nextLine())) {
    	  Club clubToView = new Club(club);
    	  clubToView.printClubPromptsAndInfo(PolyClubsConsole.user instanceof ClubAdmin);
      }
         
      else 
         displaySearch(); 
   }
   
   /**
    * Display filter prompt to user
 * @throws JSONException 
 * @throws InterruptedException 
    */
   private void displayFilter() throws JSONException, InterruptedException {
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
    * @throws JSONException 
    * @throws InterruptedException 
    */
   private void filterClub(String filter) throws JSONException, InterruptedException {
	   /**Database Manager Object used to access mlab.com*/
      db.setDataBaseDestination(cDatabase, null, true);
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
    * @throws JSONException 
    * @throws InterruptedException 
    */
   private void filter(ArrayList<JSONObject> clubs) throws JSONException, InterruptedException {
	  int index; 
	  String back = "";
	  boolean done;
	   
	  // print clubs 
	  do {
	      for (JSONObject c : clubs) {
	    	  index = clubs.indexOf(c) + 1;
	    	  logger.log(Level.INFO, index + ". " + c.get(cName).toString());
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
   
   /**
    * List clubs in alphabetical order
    * @param filter
    * @throws JSONException 
    * @throws InterruptedException 
    */
   private void list() throws JSONException, InterruptedException {
	   /**Database Manager Object used to access mlab.com*/
      db.setDataBaseDestination(cDatabase, null, true);
      db.accessDatabase();
      MongoCollection<Document> col = db.getEntireDatabaseResults();
      
      // iterator to go through clubs in database
      Iterable<Document> iter;
      iter = col.find();
      
      // ArrayList of clubs names 
      ArrayList<String> clubs = new ArrayList<>();
      
      // add names to list
      for (Document doc : iter) {
         JSONObject profile = new JSONObject(doc);
         clubs.add(profile.get(cName).toString());
      }
            
      Collections.sort(clubs);
      
      for (String name : clubs)
    	  logger.log(Level.INFO, name);
      
      displayOpen();
   }
   
   public static void main(String[] args) throws JSONException, InterruptedException {
   		ClubSearch cs = ClubSearch.getInstance();
   		cs.displayOpen();
   }
}
