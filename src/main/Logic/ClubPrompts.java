package Logic;

import java.util.Scanner;
import org.json.JSONException;

/**
 *
 * @author Nick Romero
 */
public class ClubPrompts 
{
    /**System search command*/
    private final String search = "Club Search";
    
    /**System view club command*/
    private final String viewClub = "View Club";
    
    /**System exit command*/
    private final String systemExit = "Exit";
    
    /**Scanner for user input*/
    private Scanner userChoice;
    
    /**ClubSearch Instance*/
    private ClubSearch clubSearch;
    
    /**
     * Constructor for ClubPrompts
     */
    public ClubPrompts()
    {
        /**
         * Instantiated Object has needed methods
         */
    }
    
    /**
     * Display ClubOptions to user.
     */
    public void displayClubPrompt() throws Exception
    {
        System.out.println("Welcome to PolyClubs.\nPlease enter one of the"
                + "following options");
        System.out.println("To Search For Clubs: Enter \"Club Search\"");
        System.out.println("To Load a Club's Page: Enter \"View Club\"");
        
        System.out.println("To Exit the application: Enter \"Exit\"");
        
        retrieveClubPromptInput();
    }
    
    /**
     * Retrieve club prompts
     */
    public void retrieveClubPromptInput() throws JSONException, Exception
    {
        String choice;
        
        userChoice = new Scanner(System.in);
        
        choice = userChoice.next();
        //choice += " ";
        //choice += userChoice.next();
        
        System.out.println(choice);
        
        /**
         * Determine if an invalid option was entered as a prompt.
         */
        if (!choice.equals(search) && !choice.equals(viewClub) && 
                !choice.equals(systemExit))
        {
            displayIncorrectOption();
        }
        else if (!choice.equals(systemExit))
        {
            actOnClubPromptInput(choice);
        }
        else
        {
            System.out.println("JERE");
            Logout logout = new Logout();
        }
    }
    
    private void actOnClubPromptInput(String choice)
    {
        if (choice.equals(search))
        {
            clubSearch = ClubSearch.getInstance();
        }
        
        
    }

    /**
     * Incorrect Option
     */
    private void displayIncorrectOption() throws Exception 
    {
        System.out.println("Invalid Option. Please choose from one of the following");
        displayClubPrompt();
    }
    
}
