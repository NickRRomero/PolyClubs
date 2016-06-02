package main.logic;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Nick
 */
public class PolyClubsConsole 
{
    /**User of the application at run time*/
    protected static User user = null;
    
    private PolyClubsConsole()
    {
        
    }
    
    /**
     * @param args the command line arguments
     * @throws JSONException 
     */
    public static void main(String[] args) throws InterruptedException, JSONException
    {
       
        Login login = new Login();
        login.displayWelcomeScreen();
        ClubPrompts clubPrompts = new ClubPrompts();
        JSONObject objec = login.getStudentProfile();
        user = login.setupUser(objec);
        clubPrompts.setUser(user);
        clubPrompts.setAdmin(user instanceof ClubAdmin);
        clubPrompts.displayClubPrompt();
        
        
    }
    
    
   
}
