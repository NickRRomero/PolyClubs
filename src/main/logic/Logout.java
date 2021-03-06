package main.logic;

/**
 * Class to deal with logging out and/or exiting.
 * When a user chooses to exit in another class, create new Logout object.
 * The constructor will take care of the logic.
 * @author mboyken
 */

import java.util.Scanner;
import java.util.logging.*;

import org.json.JSONException;

public class Logout
{
   private Scanner scan;
   private String input;
   private static final Logger logger = Logger.getLogger( Logout.class.getName() );

   /**
    * Method to log a user out.
 * @throws InterruptedException 
 * @throws JSONException 
    * 
    */
   public void promptLogout() throws InterruptedException, JSONException
   {
      scan = new Scanner(System.in);
      logger.log(Level.INFO, "Type \"logout\" to log out of the app.");
      logger.log(Level.INFO, "Type \"exit\" to exit the app.");
      input = "";
      while (!"logout".equals(input) && !"exit".equals(input))
      {
         input = scan.next();

         // if the user types "logout", log the user out and return to login
         if ("logout".equals(input))
         {
            logout();
         }
         
         // if the user types neither, error message and return
         else
         {
            logger.log(Level.INFO, "Incorrect input. Type \"logout\" or \"exit\"");
         }
      }
   }

   /**
    * Log out of the app by calling the main method of the app.
 * @throws InterruptedException 
 * @throws JSONException 
    * 
    * @throws Exception
    */
   private void logout() throws InterruptedException, JSONException
   {
      PolyClubsConsole.main(null);
   }

   

}
