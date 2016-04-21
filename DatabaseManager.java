package polyclubs.polyclubs;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

/**
 * Created by Nick on 4/20/16.
 */
public class DatabaseManager {

    String MongoDBConnectionIP;
    boolean hasIPBeenSet = false;
    private String databaseName;
    private String studentKey;
    private String clubKey;
    private boolean clubDBAccess;
    private boolean studentDBAccess;

    /**
     * Constructor for a database object
     * @param nicksLocalPolyIP Nick Romero's ip needed for the local system test in CPE309
     */
    public DatabaseManager(String nicksLocalPolyIP) {

        if (hasIPBeenSet == false) {
            MongoDBConnectionIP = nicksLocalPolyIP;
            hasIPBeenSet = true;
        }

    }

    /**
     * Determine which database needs to be access
     * @param whichDatabase Either 'StudentDatabase' or 'ClubDatabase'
     */
    public void setDataBaseDestination(String whichDatabase) {
        databaseName = whichDatabase;
        setDatabaseAccess();
    }


    /**
     * Initialize the database accessing fields
     */
    private void setDatabaseAccess() {

        if (databaseName.equals("StudentDatabase")) {
            studentKey = "Students";
            studentDBAccess = true;
            clubKey = null;
            clubDBAccess = false;
        }
        else if (databaseName.equals("ClubDatabase")) {
            clubKey = "Clubs";
            clubDBAccess = true;
            studentKey = null;
            studentDBAccess = false;
        }
    }

    /**
     * Access the provided database given in the constructor.
     * @return a JSON Document containing the database information
     */
    public Document accessDatabase() {
        MongoClientURI uri = new MongoClientURI("mongodb://"+MongoDBConnectionIP+"/"+databaseName);
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase db = mongoClient.getDatabase(uri.getDatabase());

        FindIterable<Document> iter = db.getCollection("Clubs").find();
        //TODO Determine what to return and when
        return iter.first();

    }

}

