package polyclubsconsole;



import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.json.simple.*;
import org.bson.Document;

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
    private String whichStudentByEmail = "";
    
    /**How large of a collection to retrieve from MLAB*/
    private boolean scopeOfDatabaseAccess = false;
    
    /**Profile holding a single document from query*/
    private JSONObject profile = null;

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
     * @param whichObject name of a student i.e. Nick Romero or name of club i.e. Society of Women Engineers
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
            whichStudentByEmail = whichObject;
            databaseURIToAccess = "mongodb://nick:password@ds017231.mlab.com:17231/studentdatabase";
        }
        else if ("ClubDatabase".equals(databaseName)) 
        {
            collectionToRetrieve = "clubs";
            databaseURIToAccess = "mongodb://nick:password@ds021691.mlab.com:21691/clubdatabase";
        }
    }

    /**
     * Access the database and query the selected information*
     */
    protected void accessDatabase()
    {
        System.err.close();
        MongoClientURI uri = new MongoClientURI(databaseURIToAccess);
        MongoClient mongoClient = new MongoClient(uri);
        
        MongoDatabase db = mongoClient.getDatabase(uri.getDatabase());
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
            Iterable<Document> iterable = collection.find(Filters.eq("email",whichStudentByEmail));
            Document doc = iterable.iterator().next();
            profile = new JSONObject(doc);
        }
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
}

