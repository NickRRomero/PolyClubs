package Logic;

import com.mongodb.client.MongoCollection;

import java.util.ArrayList;
import java.util.Scanner;
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
   
   // Constructor for Club Search. Calls display method
   private ClubSearch() 
   {
      in = new Scanner(System.in);
      db = DatabaseManager.getInstance();
      
      openPrompt = "Would you like to search for a club, " +
  	    "filter by type of club, or exit? (s/f/e)";
   }
   
   public static synchronized ClubSearch getInstance()
   {
       if (clubSearch == null)
       {
           clubSearch = new ClubSearch();
       }
       
       return clubSearch;
   }
   
   // Prompt giving user choice to search club or filter by category
   private void displayOpen() {
	   boolean done = false;
	   
	   do {
		   System.out.println("\n" + openPrompt);
		   String choice = in.nextLine();
		   
		   if (choice.equalsIgnoreCase("s")) {
			   done = true;
			   displaySearch();
		   }
		   else if (choice.equalsIgnoreCase("f")) {
			   done = true;
			   displayFilter();
		   }
		   else if (choice.equalsIgnoreCase("e")) {
			   done = true;
			   ; //exit
		   }
		   else 
			   System.out.println("Invalid option.");
		   
	   } while (!done);
   }
   
   // Displays search prompt to user
   private void displaySearch() {
      System.out.println("\nPlease enter the name of the club you would " +
       "like to search for or 'back' to return to initial prompt:");
      
      // club name that user searches for
      String club = in.nextLine();
      
      // exit if user chooses to do so, otherwise search database
      if ("back".equalsIgnoreCase(club))
         displayOpen();
      else
         searchClub(club);
   }
   
   // Search database for the name of the club input by user
   public boolean searchClub(String club){
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
         return true;
      }
      else {
         System.out.println("The club you entered is " +
          "not in the database.");
         displaySearch();
         return false;
      } 
   }
   
   // Ask user to view a club page
   private void viewPage(String club) {
      System.out.println("Would you like to view the " + 
       club + " clubpage? (y/n)");
      
      // if yes, go to club page
      // else return to display prompt
      if("y".equalsIgnoreCase(in.nextLine())) 
         ;// club page
      else 
         displaySearch(); 
   }
   
   // Displays filter prompt to user
   private void displayFilter() {
      System.out.println("\nPlease enter the type of club you would " +
       "like to filter by or 'back' to return to initial prompt:");
      
      // club name that user searches for
      String filter = in.nextLine();
      
      // exit if user chooses to do so, otherwise search database
      if ("back".equalsIgnoreCase(filter))
         displayOpen();
      else
         filterClub(filter);
   }
   
   // Filter clubs by category
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
      
      if (clubs.size() == 0) {
    	  System.out.println("There are no clubs matching that type.");
    	  displayFilter();
      }
      else     
    	  filter(clubs); 
}

   // display filtered clubs to user
   private void filter(ArrayList<JSONObject> clubs) {
	  int index; 
	  String back = "";
	  boolean done = false;
	   
	  // print clubs 
	  do {
	      for (JSONObject c : clubs) {
	    	  index = clubs.indexOf(c) + 1;
	    	  System.out.println(index + ". " + c.get("ClubName").toString());
	      }
	
	      index = -1;
            
    	  System.out.println("Please enter the number of the club you" +
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
	      else if (back.equals("back")) {
	    	  done = true;
	    	  displayFilter();
	      }
	      else {
	    	  done = false;
	    	  System.out.println("Invalid option.\n");
	      }
      } while (!done);
   }
   
   public static void main(String[] args) {
      ClubSearch ds = ClubSearch.getInstance();
      
      ds.displayOpen();
   }
}
