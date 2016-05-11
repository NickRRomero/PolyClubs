package polyclubsconsole;



import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import java.io.Console;
import org.bson.BSON;
import org.bson.BSONObject;
import org.json.simple.*;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 * Created by Nick on 4/20/16.
 */
public class DatabaseManager 
{
    /**Database Instance*/
    private static DatabaseManager instance;
    
    /**Database Name to access*/
    private String databaseName;
    
    /**Collection returned from Database Query*/
    private MongoCollection<Document> documentCollection;
    
    /**Which MLAB Database to access*/
    private String databaseURIToAccess = "";
    
    /**Which collection to retrieve, user or club*/
    private String collectionToRetrieve = "";
    
    /**Student to look for*/
    private String whichDocumentByName = "";
    
    /**Which club to access*/
    //private String whichClubByName = "";
    
    /**How large of a collection to retrieve from MLAB*/
    private boolean scopeOfDatabaseAccess = false;
    
    /**Profile holding a single document from query*/
    private JSONObject profile = null;

    MongoClientURI uri;// = new MongoClientURI(databaseURIToAccess);
    MongoClient mongoClient;// = new MongoClient(uri);
    MongoDatabase db;// = mongoClient.getDatabase(uri.getDatabase());
    
    /**
     * Constructor for DatabaseManager instance
     */
    private DatabaseManager() 
    {
      /**
       * No parameter needed. The object return has the needed API.
       */
    }
    
    /**
     * Create a singleton instance of DatabaseManager.
     */
    public static synchronized DatabaseManager getInstance()
    {
        /**
         * Determine if the class has already been created
         */
        if (instance == null)
        {
            instance = new DatabaseManager();
        }
        
        return instance;
        
    }
    
      
    /**
     * Determine which database needs to be access
     * @param whichDatabase Either 'studentDatabase' or 'clubDatabase'
     * @param whichObject name of a student i.e. Nick Romero or name of 
     * @param scopeOfAccess grab an entire document or a single document
     */
    public void setDataBaseDestination(String whichDatabase, String whichObject, boolean scopeOfAccess) {
        databaseName = whichDatabase;
        scopeOfDatabaseAccess = scopeOfAccess;
        setDatabaseAccess(whichObject);
    }

    /**
     * Initialize the database accessing fields
     */
    private void setDatabaseAccess(String whichObject) 
    {

        if ("StudentDatabase".equals(databaseName)) 
        {
            collectionToRetrieve = "students";
            whichDocumentByName= whichObject;
            databaseURIToAccess = "mongodb://nick:password@ds017231.mlab.com:17231/studentdatabase";
        }
        else if ("ClubDatabase".equals(databaseName)) 
        {
            collectionToRetrieve = "clubs";
            whichDocumentByName = whichObject;
            databaseURIToAccess = "mongodb://nick:password@ds021691.mlab.com:21691/clubdatabase";
        }
    }

    /**
     * Access the database and query the selected information*
     */
    protected void accessDatabase()
    {
        //System.err.close();
        initializeDatabaseConnection();
        MongoCollection<Document> collection =  db.getCollection(collectionToRetrieve);

        
        /**
         * scopeOfAccess determines if a single document is returned from the database
         * or a collection of documents
         */
        if (!scopeOfDatabaseAccess)
        {
            documentCollection = collection;
        }
        else
        {
            Iterable<Document> iterable;
            String key;
            Document doc = null;
            /**
             * Determine which access mode to use.
             */
            if ("clubs".equals(collectionToRetrieve))
            {
                key = "ClubName";
            }
            else
            {
                key = "email";
            }
            iterable = collection.find(Filters.eq(key, whichDocumentByName));
            
            /**
             * Determine if a document was found.
             */
            if (iterable.iterator().hasNext())
            {
                doc = iterable.iterator().next();
                profile = new JSONObject(doc);
            }
            
        }
    }
    
    public void initializeDatabaseConnection()
    {
        uri = new MongoClientURI(databaseURIToAccess);
        mongoClient = new MongoClient(uri);
        db = mongoClient.getDatabase(uri.getDatabase());
    }
    

    /**
     * Getter for database query
     * @return documentCollection
     */
    public MongoCollection<Document> getEntireDatabaseResults()
    {
        return documentCollection;
    }

    /**
     * Getter for a single database query
     * @return profile a JSONObject
     */
    public JSONObject getSingleDatabaseResults()
    {
        return profile;
    }
    
    
    /**
     * Add a student to a club as a member.
     * @param student name of student to add to database
     */
    public void addStudentToClub(String student)
    {
        initializeDatabaseConnection();
        MongoCollection<Document> collection = db.getCollection(collectionToRetrieve);
        BasicDBObject newStudent = new BasicDBObject("ClubName", whichDocumentByName); 
        BasicDBObject students = new BasicDBObject("members", student);
        collection.updateOne(newStudent, new BasicDBObject("$addToSet", students));
    }
    
    /**
     * Remove a student from a club as a member.
     * @param student name of student to remove from database
     */
    public void removeStudentFromClub(String student)
    {
        initializeDatabaseConnection();
        MongoCollection<Document> collection = db.getCollection(collectionToRetrieve);
        BasicDBObject newStudent = new BasicDBObject("ClubName", whichDocumentByName); 
        BasicDBObject students = new BasicDBObject("members", student);
        collection.updateOne(newStudent, new BasicDBObject("$pull", students));
    }
   
    /**
     * Change the description of a club.
     * @param newDescription new description of a club.
     */
    public void changeClubDescription(String newDescription)
    {
        initializeDatabaseConnection();
        MongoCollection<Document> collection = db.getCollection(collectionToRetrieve);
        collection.updateOne(eq("ClubName", whichDocumentByName), 
                set("description", newDescription));
    }
    
    /**
     * Add an event to a Club's page
     * @param jsonobject Format - "Club Name or Description", "Information"
     */
    public void addEventToClub(JSONObject jsonobject)
    {
        initializeDatabaseConnection();
        MongoCollection<Document> collection = db.getCollection(collectionToRetrieve);
        BasicDBObject newEvent = new BasicDBObject("ClubName", whichDocumentByName);
        BasicDBObject events = new BasicDBObject("events", jsonobject);
        collection.updateOne(newEvent, new BasicDBObject("$addToSet", events));
    }
    
    /**
     * Remove an event from a Club's page
     * @param jsonobject Format - "Club Name or Description", "Information"
     */
    public void removeEventFromClub(JSONObject jsonobject)
    {
        initializeDatabaseConnection();
        MongoCollection<Document> collection = db.getCollection(collectionToRetrieve);
        BasicDBObject newEvent = new BasicDBObject("ClubName", whichDocumentByName);
        BasicDBObject events = new BasicDBObject("events", jsonobject);
        collection.updateOne(newEvent, new BasicDBObject("$pull", events));
    }
   
    
}

