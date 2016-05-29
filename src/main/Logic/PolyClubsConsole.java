package main.Logic;

import org.json.JSONObject;

/**
 *
 * @author Nick
 */
public class PolyClubsConsole 
{
    /**User of the application at run time*/
    protected static User user = null;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException, Exception 
    {
       
        
        Login login = new Login();
        ClubPrompts clubPrompts = new ClubPrompts();
        login.displayWelcomeScreen();
        JSONObject objec = login.getStudentProfile();
        user = login.setupUser(objec);
        clubPrompts.displayClubPrompt();
        
        
    }
    
    
   
}
