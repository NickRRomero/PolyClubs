package Logic;

import static java.lang.Thread.sleep;
import java.util.Scanner;
import org.json.simple.*;
/**
 *
 * @author Nick
 */
public class Login 
{
    /**Student's email address*/
    private String studentEmail = "";
    
    /**Student's password*/
    private String studentPassword = "";
    
    /**Console Prompt for the Student Email*/
    private static final String emailPrompt = "Enter your CalPoly Email "
            + "Address: \nexample@calpoly.edu\n";
    
    /**Console Prompt for the Student password*/
    private static final String passwordPrompt = "Enter your CalPoly Password:\n";
    
    /**Error message for logging in*/
    private static final String errorLogin = "Incorrect email or password.\n";
    
    private boolean successfullLogin = false;
    
    /**Scanner to capture user information*/
    private Scanner scanner;
    
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
     */
    public void displayWelcomeScreen() throws InterruptedException 
    {
        System.out.println("Poly\n    Clubs\n");
        System.out.println("Please enter Login Credentials or type EXIT in email "
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
     */
    private void displayLoginRequests() throws InterruptedException
    {
        scanner = new Scanner(System.in);
        System.out.println(emailPrompt);
        studentEmail = scanner.next();
        
        /**
         * Determine if the user decided to exit the system.
         * TODO Maybe IX
         */
        if ("EXIT".equals(studentEmail))
        {
            System.exit(0);
        }
        
        System.out.println(passwordPrompt);
        studentPassword = scanner.next();
        confirmStudentInformation(studentEmail, studentPassword);
    }
    
    /**
     * Confirm the entered student information
     * @param user username
     * @param pass password
     * @return true if student entered correct information
     */
    private void confirmStudentInformation(String user, String pass) throws InterruptedException
    {
        /**Object to hold returned user data.*/
        JSONObject userProfile = null;
        
        /**Database Manager Object used to access mlab.com*/
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        
        /**Comparison key for returned profile*/
        String tempKey;
        
        databaseManager.setDataBaseDestination("StudentDatabase", user, true);
        
        databaseManager.accessDatabase();
        sleep(12000L);
        userProfile = databaseManager.getSingleDatabaseResults();
        /**Determine if the user was found in the database and/or entered 
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
        }
        else
        {
            repromptStudentLogin();
        }
        
        successfullLogin = true;
    }

    /**
     * Re-prompt for more user information.
     */
    private void repromptStudentLogin() throws InterruptedException 
    {
        System.out.println(errorLogin);
        displayLoginRequests();
    }
}
