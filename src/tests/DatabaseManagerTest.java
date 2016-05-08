
package polyclubsconsole;

import com.mongodb.client.MongoCollection;
import java.util.Map;
import junit.framework.TestCase;
import org.bson.Document;
import org.json.simple.JSONObject;

/**
 *
 * @author Nick
 */
public class DatabaseManagerTest extends TestCase {
    
    public DatabaseManagerTest(String testName) 
    {
        super(testName);
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

}
