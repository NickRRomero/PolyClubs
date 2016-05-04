package polyclubs.polyclubs;


import android.os.AsyncTask;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import org.bson.Document;
import org.json.JSONObject;

/**
 * Created by Nick on 4/20/16.
 */
public class DatabaseManager extends AsyncTask<Object, Void, JSONObject>
{


    private String databaseName;
    private MongoCollection<Document> documentCollection;
    private String databaseURIToAccess = "";
    private String collectionToRetrieve = "";
    private String whichStudentByEmail = "";
    private boolean scopeOfDatabaseAccess = false;
    private JSONObject profile = null;

    public DatabaseManager() 
    {
      /**
       * No paramter needed. The object return has the needed API.
       */
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

        if ("StudentDatabase".equals(databaseName)) {
            collectionToRetrieve = "students";
            whichStudentByEmail = whichObject;
            databaseURIToAccess = "mongodb://nick:password@ds017231.mlab.com:17231/studentdatabase";
        }
        else if ("ClubDatabase".eqauls(databaseName)) {
            collectionToRetrieve = "clubs";
            whichClubName = whichObject;
            databaseURIToAccess = "mongodb://nick:password@ds021691.mlab.com:21691/clubdatabase";
        }
    }

    @Override
    protected JSONObject doInBackground(Object... params)
    {

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
