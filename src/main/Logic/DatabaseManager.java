package Logic;



import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;

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
    
    /**How large of a collection to retrieve from MLAB*/
    private boolean scopeOfDatabaseAccess = false;
    
    /**Profile holding a single document from query*/
    private JSONObject profile = null;

    /**ClubAdministraor MLABURI*/
    String uriAdmin = "mongodb://nick:password@ds011820.mlab.com:11820/clubadministrators";
    
    /** ClubDatabase MLABURI*/
    String uriClubs = "mongodb://nick:password@ds021691.mlab.com:21691/clubdatabase";
    
    /**StudentDabase MLABURI*/
    String uriStudents = "mongodb://nick:password@ds017231.mlab.com:17231/studentdatabase";
    
    /** URI used to access a Mongo database*/
    MongoClientURI uri;
    
    /**MongoClient used to access MLAB.com*/
    MongoClient mongoClient;
    
    /**Database Object for the Mongo Connection*/
    MongoDatabase db;
    
    /**
     * Constructor for DatabaseManager instance
     */
    private DatabaseManager() 
    {
      /**
       * No parameter needed. The object returned has the needed API.
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
     * @param whichObject name of a student i.e. Nick Romero or name of club
     * @param retrieveEntireDatabaseQuery grab an entire document or a single document
     */
    public void setDataBaseDestination(String whichDatabase, String whichObject, boolean retrieveEntireDatabaseQuery) {
        databaseName = whichDatabase;
        scopeOfDatabaseAccess = retrieveEntireDatabaseQuery;
        setDatabaseAccess(whichObject);
    }

   
    /**
     * Initialize the database information
     * @param whichObject name of the database to access
     */
    private void setDatabaseAccess(String whichObject) 
    {

        if ("StudentDatabase".equals(databaseName)) 
        {
            collectionToRetrieve = "students";
            whichDocumentByName= whichObject;
            databaseURIToAccess = uriStudents;
        }
        else if ("ClubDatabase".equals(databaseName)) 
        {
            collectionToRetrieve = "clubs";
            whichDocumentByName = whichObject;
            databaseURIToAccess = uriClubs;
        }
        else if ("ClubAdministrators".equals(databaseName))
        {
            collectionToRetrieve = "clubadmins";
            whichDocumentByName = whichObject;
            databaseURIToAccess = uriAdmin;
        }
    }

    /**
     * Access the database and query the selected information*
     */
    protected void accessDatabase()
    {
        
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
            else if ("students".equals(collectionToRetrieve))
            {
                key = "email";
            }
            else
            {
                key = "AdminList";
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
    public void addEventToClub(JSONObject jsonobject, String eventName)
    {
        initializeDatabaseConnection();
        MongoCollection<Document> collection = db.getCollection(collectionToRetrieve);
        BasicDBObject club = new BasicDBObject("ClubName", whichDocumentByName);
        
        BasicDBObject events = new BasicDBObject("event1", jsonobject);
        
        collection.updateOne(club, new BasicDBObject("$addToSet", new BasicDBObject("events", 
                new BasicDBObject(eventName, new BasicDBObject("description", jsonobject.getString("description"))
                        .append("time", jsonobject.getString("time"))))));
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
    
    /**
     * Set the advisor of a database.
     * @param advisor string name of advisor to add to club
     * @param clubName string the club to add the advisor to
     */
    public void setAdvisorOfClub(String advisorEmail, String clubName)
    {
        JSONObject advisorProfile;
        String advisorPhoneNumber;
        String advisorName;
        
        setDataBaseDestination("StudentDatabase", advisorEmail ,true);
        initializeDatabaseConnection();
        accessDatabase();
        
        advisorProfile = getSingleDatabaseResults();
        advisorName = advisorProfile.getString("name");
        advisorPhoneNumber = advisorProfile.getString("phoneNumber");
        advisorEmail = advisorProfile.getString("email");
        
        setDataBaseDestination("ClubDatabase", clubName, false);
        initializeDatabaseConnection();
        
        MongoCollection<Document> collection = db.getCollection(collectionToRetrieve);
        BasicDBObject newAdvisor = new BasicDBObject("name", advisorName).append("phoneNumber", advisorPhoneNumber)
                .append("email", advisorEmail);
        
        collection.updateOne(eq("ClubName", clubName), new Document("$set", new Document("Advisor", newAdvisor)));    
    }
    
   
    /**
     * Add A new club to the database
     * @param clubName string Name Of club to be added to the database
     * @param presidentEmail string President's email to be added to the database
     * @param desc string description of the club
     */
    public void createNewClub(String clubName, String presidentEmail, String desc)
    {
        JSONObject userProfile;
        
        uri = new MongoClientURI(databaseURIToAccess);
        mongoClient = new MongoClient(uri);
        db = mongoClient.getDatabase(uri.getDatabase());
        
        this.setDataBaseDestination("StudentDatabase", presidentEmail, true);
        this.accessDatabase();
        userProfile = this.getSingleDatabaseResults();
        
        uri = new MongoClientURI("mongodb://nick:password@ds021691.mlab.com:21691/clubdatabase");
        mongoClient = new MongoClient(uri);
        db = mongoClient.getDatabase(uri.getDatabase());
        
        db.getCollection("clubs").insertOne(
                new Document()
                    .append("ClubName", clubName)
                    .append("President", new Document()
                            .append("name", userProfile.get("name"))
                            .append("phoneNumber", userProfile.get("phoneNumber"))
                            .append("email", userProfile.get("email")))
                    .append("Advisor", new Document()
                            .append("name", "")
                            .append("phoneNumber", "")
                            .append("email", ""))
                    .append("description", desc)
                    .append("members", Arrays.asList(userProfile.get("name"))));
                    
    }
    
    /**
     * Check if a student has admin privileges.
     * @param currentStudentUser name of a student. I.e. Nick Romero
     * @return true if the student was located in the admin database.
     */
    public boolean checkIfAdmin(String currentStudentUser) throws InterruptedException
    {
        this.setDataBaseDestination("ClubAdministrators", "Main", true);
        initializeDatabaseConnection();
        
        ArrayList<String> admins = new ArrayList<String>();
        this.accessDatabase();
        
        JSONObject aprofile = this.getSingleDatabaseResults();
        JSONArray jsonArray = (JSONArray) aprofile.get("admins");
        
        
        for (int i = 0; i < jsonArray.length(); i++)
        {
            admins.add(jsonArray.getString(i));
        }
        
        return admins.contains(currentStudentUser);
    }
    
}
