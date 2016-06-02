package tests.test;

import junit.framework.TestCase;
import main.logic.DatabaseManager;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

/**
 *
 * @author Nick Romero
 */
public class TestDatabaseManager extends TestCase 
{
    
    public TestDatabaseManager(String testName) 
    {
        super(testName);
        System.err.close();
    }

    
   
    
    
    
    @Test
    public void testDatabaseManagerNewInstance()
    {
        System.out.println("testDatabaseManagerNewInstance");
        DatabaseManager db = DatabaseManager.getInstance();
        assertTrue(db instanceof DatabaseManager);
    }
    
    @Test
    public void testDatabaseManagerSameInstance()
    {
        System.out.println("testDatabaseManagerSameInstance");
        DatabaseManager db = DatabaseManager.getInstance();
        DatabaseManager identicalInstance = DatabaseManager.getInstance();
        assertEquals(db, identicalInstance);
    }
    
    @Test
    public void testCheckIfAdminIsAdmin() throws InterruptedException, JSONException
    {
        System.out.println("testCheckIfAdminIsAdmin");
        DatabaseManager db = DatabaseManager.getInstance();
        boolean isAdmin;
        isAdmin = db.checkIfAdmin("Nick Romero");
        assertTrue(isAdmin);
    }
    
    @Test
    public void testCheckIfAdminIsNotAdmin() throws InterruptedException, JSONException
    {
        System.out.println("testCheckIfAdminIsNotAdmin");
        DatabaseManager db = DatabaseManager.getInstance();
        boolean isNotAdmin;
        isNotAdmin = db.checkIfAdmin("Kevin Costello");
        assertFalse(isNotAdmin);
    }
    
    @Test
    public void testDatabaseAccessSuccessful()
    {
        System.out.println("testDatabaseAccessSuccessful");
        DatabaseManager db = DatabaseManager.getInstance();
        
        
        db.setDataBaseDestination("ClubAdministrators", "Main", false);
        db.accessDatabase();
        JSONObject aprofile = db.getSingleDatabaseResults();
        assertNotNull(aprofile);
    }
    
/*
 	 @Test
    public void testSetAdvisorOfClub() throws JSONException
    {
        DatabaseManager db = DatabaseManager.getInstance();  
        
        db.setAdvisorOfClub("mboyken@calpoly.edu", "Cats cats and more cats");
    }
    
    public void testChangeClubDescription()
    {
        DatabaseManager db = DatabaseManager.getInstance();
        db.setDataBaseDestination("ClubDatabase", "Society of Physics Students", true);
        db.changeClubDescription("To bring community to the physics students "
                + "at cal poly, as well as any students interested in physics");
    }
    
    public void testAddStudentToClub()
    {
        DatabaseManager db = DatabaseManager.getInstance();
        db.setDataBaseDestination("ClubDatabase", "Society of Women Engineers", true);
        db.addStudentToClub("Olive The Kitty");
    }
    
    public void testRemoveStudentFromClub()
    {
        DatabaseManager db = DatabaseManager.getInstance();
        db.setDataBaseDestination("ClubDatabase", "Society of Women Engineers", true);
        db.removeStudentFromClub("Olive The Kitty");
    }
    
    public void testAddEventToClub()
    {
        DatabaseManager db = DatabaseManager.getInstance();
        db.setDataBaseDestination("ClubDatabase", "Society of Women Engineers", true);
        
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Network With Apple", "05-08-16 16:00-17:00 W");
        db.removeEventFromClub(jsonObject);
    }

    public void testCreateNewClub()
    {
        DatabaseManager db = DatabaseManager.getInstance();
        db.setDataBaseDestination("StudentDatabase", "nrromero@calpoly.edu", true);
        db.createNewClub("Cats cats and more cats", "nrromero@calpoly.edu", 
                "We are a dog loving club. Cats not required.");
    }
*/
    
    
    
    
}
