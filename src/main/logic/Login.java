package main.logic;

import static java.lang.Thread.sleep;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author Nick
 */
public class Login
{
   /** Logger for output */
   private static final Logger logger = Logger.getLogger( Login.class.getName() );
   
   /** Student's email address */
   protected String studentEmail = "";

   /** Student's password */
   private String studentPassword = "";

   /** Console Prompt for the Student Email */
   private static final String emailPrompt = "Enter your CalPoly Email "
         + "Address: example@calpoly.edu\n";

   /** Console Prompt for the Student password */
   private static final String passwordPrompt = "Enter your CalPoly Password:\n";

   /** Error message for logging in */
   private static final String errorLogin = "Incorrect email or password.\n";

   private boolean successfullLogin = false;

   /** Scanner to capture user information */
   private Scanner scanner;

   /** Object to hold returned user data. */
   JSONObject userProfile = null;

   /**
    * Display Login Screen to console;
    */
   public Login()
   {
      /**
       * Only need object to instantiate displayWelcomeScreen
       */
   }

   /**
    * Display the login screen to the console.
    * 
    * @throws JSONException
    */
   public void displayWelcomeScreen() throws InterruptedException,
         JSONException
   {
      logger.log(Level.INFO, "Poly\n    Clubs\n");
      logger.log(Level.INFO, "Please enter Login Credentials or type EXIT in email "
                  + "field to close the application.\n");
      /**
       * Continuously prompt user for correct information.
       */
      while (!successfullLogin)
      {
         displayLoginRequests();
      }
   }

   /**
    * Display login requests.
    * 
    * @throws JSONException
    */
   private void displayLoginRequests() throws InterruptedException,
         JSONException
   {
      scanner = new Scanner(System.in);
      logger.log(Level.INFO, emailPrompt);
      studentEmail = scanner.next();
      logger.log(Level.INFO, "\n");

      /**
       * Determine if the user decided to exit the system. to-do Maybe IX
       */
      if ("EXIT".equals(studentEmail))
      {
         System.exit(0);
      }

      logger.log(Level.INFO, passwordPrompt);
      studentPassword = scanner.next();
      confirmStudentInformation(studentEmail, studentPassword);
   }

   /**
    * Confirm the entered student information
    * 
    * @param user
    *           username
    * @param pass
    *           password
    * @return true if student entered correct information
    * @throws JSONException
    */
   private void confirmStudentInformation(String user, String pass)
         throws InterruptedException, JSONException
   {

      /** Database Manager Object used to access mlab.com */
      DatabaseManager databaseManager = DatabaseManager.getInstance();

      /** Comparison key for returned profile */
      String tempKey;

      databaseManager.setDataBaseDestination("StudentDatabase", user, false);

      databaseManager.accessDatabase();

      logger.log(Level.INFO, "Checking Login Credentials");
      /**
       * Simulate a loading bar.
       */
      for (int i = 0; i < 13; i++)
      {
         logger.log(Level.INFO, ".");
         sleep(500L);
      }
      logger.log(Level.INFO, "\n");

      userProfile = databaseManager.getSingleDatabaseResults();
      /**
       * Determine if the user was found in the database and/or entered
       * incorrect email.
       */
      if (userProfile != null)
      {

         tempKey = userProfile.get("key").toString();

         /**
          * Confirmed user password
          */
         if (!tempKey.equals(pass))
         {
            repromptStudentLogin();
         }
      } else
      {
         repromptStudentLogin();
      }
      studentPassword = "";
      successfullLogin = true;
   }

   /**
    * Re-prompt for more user information.
    * 
    * @throws JSONException
    */
   private void repromptStudentLogin() throws InterruptedException,
         JSONException
   {
      logger.log(Level.INFO, errorLogin);
      displayLoginRequests();
   }

   public JSONObject getStudentProfile()
   {
      return userProfile;
   }

   public User setupUser(JSONObject userProfile) throws JSONException, InterruptedException
   {
      String name;
      String phoneNum;
      String empl;
      String email;
      DatabaseManager db = DatabaseManager.getInstance();
      boolean isClubAdmin;

      name = userProfile.get("name").toString();
      phoneNum = userProfile.get("phoneNumber").toString();
      empl = userProfile.get("EmplID").toString();
      email = userProfile.get("email").toString();

      logger.log(Level.INFO, name + '\n' + phoneNum + '\n' + empl + '\n' + email);
      isClubAdmin = db.checkIfAdmin(name);

      /**
       * Determine if a user is a club administrator and should have the
       * associated privilages.
       */
      if (isClubAdmin)
      {
         return new ClubAdmin(name, phoneNum, empl, email);
      } else
      {
         return new User(name, phoneNum, empl, email);
      }

   }
}
