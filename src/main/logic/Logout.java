package main.logic;

/**
 * Class to deal with logging out and/or exiting.
 * When a user chooses to exit in another class, create new Logout object.
 * The constructor will take care of the logic.
 * @author mboyken
 */

import java.util.Scanner;
import java.io.PrintWriter;

public class Logout
{
   private Scanner scan;
   private String input;
   private PrintWriter writer;

   /**
    * Constructor for Logout object.
    * 
    * @throws Exception
    */
   public Logout() throws Exception
   {
      scan = new Scanner(System.in);
      writer = new PrintWriter(System.out);
      writer.println("Type \"logout\" to log out of the app.");
      writer.println("Type \"exit\" to exit the app.");

      while (!input.equals("logout") && !input.equals("exit"))
      {
         input = scan.next();

         // if the user types "logout", log the user out and return to login
         if (input.equals("logout"))
         {
            logout();
         }
         // if the user types "exit", the app will close
         else if (input.equals("exit"))
         {
            exit();
         }
         // if the user types neither, error message and return
         else
         {
            writer.println("Incorrect input. Type \"logout\" or \"exit\"");
         }
      }
   }

   /**
    * Log out of the app by calling the main method of the app.
    * 
    * @throws Exception
    */
   private void logout() throws Exception
   {
      PolyClubsConsole.main(null);
   }

   /**
    * Exit the app by making an exit call to System.
    */
   private void exit()
   {
      System.exit(0);
   }

}
