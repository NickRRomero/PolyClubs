package polyclubs.polyclubs;


import android.os.AsyncTask;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;
import org.json.JSONObject;

/**
 * DatabaseManager class to handle MongoDB requests
 * Associated Files - mongo-java-driver-3.2.0-SNAPSHOT 
 * Created by Nick on 4/20/16.
 */
public class DatabaseManager extends AsyncTask<Object, Void, JSONObject>
{

    /**Database name to determine which database to access*/
    private String databaseName;

    /**Collection of Documents containing all results of a MongoDB query*/
    private MongoCollection<Document> documentCollection;

    /**Document object to hold a single query result*/
    private Document singleDocument;

    /**Database URI used to access MongoDB at MLab.com*/
    private String databaseURIToAccess = "";

    /**Which database to retreive out of the MongoDB*/
    private String collectionToRetrieve = "";

    /**Identifier used to determine which database will be used*/
    private static final String whichClubKey = "ClubName";
    
    /**Identifier used to specify a club*/
    private String whichClubName = "";

    /**Identifier used to determine which database will be used*/
    private static final String whichStudentKey = "email";

    /**Identifier used to specify a student*/
    private String whichStudentByEmail = "";

    /**Modifier used to determine how much of a colleciton is returned from a query*/
    private boolean scopeOfDatabaseAccess = false;

    /**Container for a single student's profile*/
    private JSONObject profile = null;

    /**
     * Class Constructor
     */
    public DatabaseManager() {


    }

    /**
     * Determine which database needs to be access
     * @param whichDatabase Either 'studentDatabase' or 'clubDatabase'
     * @param whichObject name of a student i.e. Nick Romero or name of club i.e. Society of Women Engineers
     */
    public void setDataBaseDestination(String whichDatabase, String whichObject, boolean scopeOfAccess) {
        databaseName = whichDatabase;
        scopeOfDatabaseAccess = scopeOfAccess;
        setDatabaseAccess(whichObject);
    }

    /**
     * Initialize the database accessing fields
     */
    private void setDatabaseAccess(String whichObject) {

        if (databaseName.equals("StudentDatabase")) {
            collectionToRetrieve = "students";
            whichStudentByEmail = whichObject;
            databaseURIToAccess = "mongodb://nick:password@ds017231.mlab.com:17231/studentdatabase";
        }
        else if (databaseName.equals("ClubDatabase")) {
            collectionToRetrieve = "clubs";
            whichClubName = whichObject;
            databaseURIToAccess = "mongodb://nick:password@ds021691.mlab.com:21691/clubdatabase";
        }
    }

    @Override
    protected JSONObject doInBackground(Object... params)
    {

        MongoClientURI uri = new MongoClientURI(databaseURIToAccess);
        JSONObject jsonObject = new JSONObject();
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
            Iterable<Document> iterable = collection.find(eq("email",whichStudentByEmail));
            Document doc = iterable.iterator().next();
            profile = new JSONObject(doc);
        }
        return null;
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
