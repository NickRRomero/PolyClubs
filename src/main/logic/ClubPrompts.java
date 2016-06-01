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
    private String search = "Club Search";
    
    /**System view club command*/
    private String viewClub = "View Club";
    
    /**System exit command*/
    private String systemExit = "Exit";
    
    private boolean isAdmin;
    
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
     * @throws InterruptedException 
     * @throws JSONException 
     */
    public void displayClubPrompt() throws JSONException, InterruptedException
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
     * @throws InterruptedException 
     */
    public void retrieveClubPromptInput() throws JSONException, InterruptedException
    {
        String choice;
        
        userChoice = new Scanner(System.in);
        
        choice = userChoice.next();
        if ("Exit".equals(choice))
        {
            Logout logout = new Logout();
            logout.promptLogout();
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
    
    private void actOnClubPromptInput(String choice) throws InterruptedException, JSONException
    {
        Scanner s = new Scanner(System.in).useDelimiter("\n");
        
        if (choice.equals(search))
        {
            clubSearch = ClubSearch.getInstance();
            clubSearch.searchClub(s.next());
        }
        if (choice.equals(viewClub))
        {
            String clubChoice;
            
            logger.log(Level.INFO, "Please enter a club to search for.");
            clubChoice = s.next();
            Club club = new Club(clubChoice);
            club.printClubPromptsAndInfo(isAdmin);
        }
        
        
    }

    /**
     * Incorrect Option
     * @throws InterruptedException 
     * @throws JSONException 
     */
    private void displayIncorrectOption() throws JSONException, InterruptedException 
    {
        logger.log(Level.INFO, "Invalid Option. Please choose from one of the following");
        displayClubPrompt();
    }
    
    /**
     * Getter for isAdmin
     * @return If user is an admin
     */
    public boolean getIsAdmin()
    {
    	return isAdmin;
    }
    
    public void setAdmin(boolean admin)
    {
    	isAdmin = admin;
    }
    
}
