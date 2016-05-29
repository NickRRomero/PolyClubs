package main.logic;

import java.util.Scanner;
import java.util.logging.*;
import org.json.JSONException;

/**
 *
 * @author Nick Romero
 */
public class ClubPrompts 
{
    /**Logger for output*/
    private static final Logger logger = Logger.getLogger( ClubPrompts.class.getName() );
    
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
        logger.log(Level.INFO, "Welcome to PolyClubs.\nPlease enter one of the"
                + "following options");
        logger.log(Level.INFO, "To Search For Clubs: Enter \"Club Search\"");
        logger.log(Level.INFO, "To Load a Club's Page: Enter \"View Club\"");
        
        logger.log(Level.INFO, "To Exit the application: Enter \"Exit\"");
        
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
        if ("Exit".equals(choice))
        {
            Logout logout = new Logout();
        }
        
        choice += " ";
        choice += userChoice.next();
        
        
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
        
    }
    
    private void actOnClubPromptInput(String choice) throws InterruptedException
    {
        if (choice.equals(search))
        {
            clubSearch = ClubSearch.getInstance();
        }
        if (choice.equals(viewClub))
        {
            String clubChoice;
            Scanner s = new Scanner(System.in).useDelimiter("\n");
            logger.log(Level.INFO, "Please enter a club to search for.");
            clubChoice = s.next();
            Club club = new Club(clubChoice);
            club.printClubPromptsAndInfo(isAdmin);
        }
        
        
    }

    /**
     * Incorrect Option
     */
    private void displayIncorrectOption() throws Exception 
    {
        logger.log(Level.INFO, "Invalid Option. Please choose from one of the following");
        displayClubPrompt();
    }
    
}
