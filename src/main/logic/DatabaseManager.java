package main.logic;



import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.*;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Nick on 4/20/16.
 */
public class DatabaseManager 
{
    /*Logger to output*/
    private static final Logger logger = Logger.getLogger( DatabaseManager.class.getName() );
    
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
    private boolean retrieveEntireDatabaseQuery = false;
    
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
    
    private String studentDatabase = "StudentDatabase";
    private String clubDatabase = "ClubDatabase";
    private String clubs = "clubs";
    private String clubName = "ClubName";
    private String email = "email";
    private String members = "members";
    private String description = "description";
    private String eventString = "events";
    private String phoneNumber = "phoneNumber";
    private String time = "time";
    
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
    public void setDataBaseDestination(String whichDatabase, String whichObject, boolean entireDatabase) {
        databaseName = whichDatabase;
        retrieveEntireDatabaseQuery = entireDatabase;
        setDatabaseAccess(whichObject);
    }

   
    /**
     * Initialize the database information
     * @param whichObject name of the database to access
     */
    private void setDatabaseAccess(String whichObject) 
    {

        if (studentDatabase.equals(databaseName)) 
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
    public void accessDatabase()
    {
        System.out.println("Above");
    	System.out.println(whichDocumentByName);
    	System.out.println(collectionToRetrieve);

    	System.out.println(databaseURIToAccess);
    	System.out.println("Below");
    	
    	
        initializeDatabaseConnection();
        MongoCollection<Document> collection =  db.getCollection(collectionToRetrieve);
    

        /**
         * scopeOfAccess determines if a single document is returned from the database
         * or a collection of documents
         */
        if (retrieveEntireDatabaseQuery)
        {
            documentCollection = collection;
        }
        else
        {
            Iterable<Document> iterable;
            String key;
            Document doc;
            /**
             * Determine which access mode to use.
             */
            if (clubs.equals(collectionToRetrieve))
            {
                key = clubName;
            }
            else if ("students".equals(collectionToRetrieve))
            {
                key = email;
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
        BasicDBObject newStudent = new BasicDBObject(clubName, whichDocumentByName); 
        BasicDBObject students = new BasicDBObject(members, student);
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
        BasicDBObject newStudent = new BasicDBObject(clubName, whichDocumentByName); 
        BasicDBObject students = new BasicDBObject(members, student);
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
        collection.updateOne(eq(clubName, whichDocumentByName), 
                set(description, newDescription));
    }
    
    /**
     * Add an event to a Club's page
     * @param jsonobject Format - "Club Name or Description", "Information"
     * @throws JSONException 
     */
    public void addEventToClub(JSONObject jsonobject) throws JSONException
    {
        initializeDatabaseConnection();
        MongoCollection<Document> collection = db.getCollection(collectionToRetrieve);
        BasicDBObject club = new BasicDBObject(clubName, whichDocumentByName);
        
        
        collection.updateOne(club, new BasicDBObject("$addToSet", new BasicDBObject(eventString,new BasicDBObject(description, jsonobject.getString(description))
                        .append("time", jsonobject.getString("time")))));
    }
    
    /**
     * Remove an event from a Club's page
     * @param jsonobject Format - "Club Name or Description", "Information"
     * @throws JSONException 
     */
    public void removeEventFromClub(String eventName) throws JSONException
    {
        initializeDatabaseConnection();
        accessDatabase();
        MongoCollection<Document> collection = db.getCollection(collectionToRetrieve);
        String desc;
        JSONObject event;
        JSONObject clubJson = getSingleDatabaseResults();
        JSONArray events = clubJson.getJSONArray(eventString);
        String eventTime = "";
        String eventDescription = "";
        
        logger.log(Level.INFO, clubJson.toString());
        for (int i = 0; i < events.length(); i++)
        {
            event = events.getJSONObject(i);
            
            desc = (String)(event.get(description));
            
            desc = desc.split("\\|")[0];
            
            if (desc.equals(eventName))
            {
               
                break;
            }
            eventTime = event.getString(time);
            eventDescription = event.getString(description);
        }
        
        BasicDBObject club = new BasicDBObject(clubName, whichDocumentByName);
        
        collection.updateOne(club, new BasicDBObject("$pull", new BasicDBObject(eventString, 
                new BasicDBObject(description, 
                        eventDescription).append(time, 
                                eventTime))));
    }
    
    /**
     * Set the advisor of a database.
     * @param advisor string name of advisor to add to club
     * @param clubName string the club to add the advisor to
     * @throws JSONException 
     */
    public void setAdvisorOfClub(String advisorEmail, String clubName) throws JSONException
    {
        JSONObject advisorProfile;
        String advisorPhoneNumber;
        String advisorName;
        String newAdvisorEmail;
        
        setDataBaseDestination("StudentDatabase", advisorEmail ,true);
        initializeDatabaseConnection();
        accessDatabase();
        
        advisorProfile = getSingleDatabaseResults();
        advisorName = advisorProfile.getString("name");
        advisorPhoneNumber = advisorProfile.getString(phoneNumber);
        newAdvisorEmail = advisorProfile.getString(email);
        
        setDataBaseDestination(clubDatabase, clubName, false);
        initializeDatabaseConnection();
        
        MongoCollection<Document> collection = db.getCollection(collectionToRetrieve);
        BasicDBObject newAdvisor = new BasicDBObject("name", advisorName).append("phoneNumber", advisorPhoneNumber)
                .append(email, newAdvisorEmail);
        
        collection.updateOne(eq(clubName, clubName), new Document("$set", new Document("Advisor", newAdvisor)));    
    }
    
   
    /**
     * Add A new club to the database
     * @param clubName string Name Of club to be added to the database
     * @param presidentEmail string President's email to be added to the database
     * @param desc string description of the club
     * @throws JSONException 
     */
    public void createNewClub(String clubName, String presidentEmail, String desc) throws JSONException
    {
        JSONObject userProfile;
        
        uri = new MongoClientURI(databaseURIToAccess);
        mongoClient = new MongoClient(uri);
        db = mongoClient.getDatabase(uri.getDatabase());
        
        this.setDataBaseDestination(studentDatabase, presidentEmail, false);
        this.accessDatabase();
        userProfile = this.getSingleDatabaseResults();
        
        uri = new MongoClientURI("mongodb://nick:password@ds021691.mlab.com:21691/clubdatabase");
        mongoClient = new MongoClient(uri);
        db = mongoClient.getDatabase(uri.getDatabase());
        
        db.getCollection(clubs).insertOne(
                new Document()
                    .append("ClubName", clubName)
                    .append("President", new Document()
                            .append("name", userProfile.get("name"))
                            .append(phoneNumber, userProfile.get(phoneNumber))
                            .append(email, userProfile.get(email)))
                    .append("Advisor", new Document()
                            .append("name", "")
                            .append(phoneNumber, "")
                            .append(email, ""))
                    .append(description, desc)
                    .append("type", "New")
                    .append(members, Arrays.asList(userProfile.get("name")))
                    .append("events", Arrays.asList()));
                    
    }
    
    /**
     * Check if a student has admin privileges.
     * @param currentStudentUser name of a student. I.e. Nick Romero
     * @return true if the student was located in the admin database.
     * @throws JSONException 
     */
    public boolean checkIfAdmin(String currentStudentUser) throws InterruptedException, JSONException
    {
        this.setDataBaseDestination("ClubAdministrators", "Main", false);
        initializeDatabaseConnection();
        
        ArrayList<String> admins = new ArrayList<>();
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
