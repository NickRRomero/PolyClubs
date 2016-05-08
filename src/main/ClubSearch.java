/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polyclubsconsole;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.util.JSON;
import java.util.Scanner;
import org.bson.Document;
import org.json.simple.JSONObject;

/**
 *
 * @author jacob
 */
public class ClubSearch { 
   private final Scanner in;
   
   public ClubSearch() {
      in = new Scanner(System.in);
   }
   
   private void displaySearch() {
      System.out.println("Please enter the name of the club " +
       "you would like to search for or 'exit' to exit search:");
      
      // club name that user searches for
      String club = in.nextLine();
      
      if ("exit".equalsIgnoreCase(club))
         ;///////////////
      else
         searchClub(club);
   }
   
   private void searchClub(String club){
      /**Database Manager Object used to access mlab.com*/
      DatabaseManager databaseManager = DatabaseManager.getInstance();
      databaseManager.setDataBaseDestination("ClubDatabase", club, true);
      databaseManager.accessDatabase();

      JSONObject clubName = databaseManager.getSingleDatabaseResults();
      filterClubs(databaseManager);
      
      if(clubName != null) {
         String name = clubName.get("ClubName").toString();
         viewPage(name);
      }
      else {
         System.out.println("The club you entered is " +
          "not in the database.\n");
         displaySearch();
      } 
   }
   
   private void filterClubs(DatabaseManager db) {
      MongoCollection<Document> col = db.getEntireDatabaseResults();
      Iterable<Document> iter;
      iter = col.find();
      
      for (Document doc : iter) {
         JSONObject profile = new JSONObject(doc);
         System.out.println(profile.get("ClubName").toString());
      }
   }
   
   private void viewPage(String club) {
      System.out.println("Would you like to view the " + 
       club + " clubpage? (y/n)");
      
      if("y".equalsIgnoreCase(in.nextLine())) 
         ;// club page
      else {
         System.out.println();
         displaySearch(); 
      }
   }
   
   public static void main(String[] args) {
      ClubSearch ds = new ClubSearch();
      ds.displaySearch();
   }
}
