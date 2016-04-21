/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.FindIterable;
import com.mongodb.Block;
import com.mongodb.*;
import java.util.Scanner;

/**
 *
 * @author Nick
 */
public class MongoTester {
    
    
    public static void main(String [] args) 
    {
        String nicksPolyIP;
        Scanner polyIP = new Scanner(System.in);
        nicksPolyIP = polyIP.next();
        MongoClientURI uri = new MongoClientURI("mongodb://"+nicksPolyIP+"/ClubDatabase");
        
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase db = mongoClient.getDatabase(uri.getDatabase());
        FindIterable<Document> iter = db.getCollection("Clubs").find();
        
        iter.forEach(new Block<Document> () {
            @Override
            public void apply(final Document document) 
            {
                System.out.println(document);
            }
        });
        
    }
}
