import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import org.bson.Document;

import java.util.Arrays;

public class CreatingCollection {

    public static void main( String args[] ) {

        // Creating a Mongo client
        MongoClient mongo = new MongoClient( "localhost" , 27017 );

        // Creating Credentials
        MongoCredential credential;
        credential = MongoCredential.createCredential("sampleUser", "myDb",
                "password".toCharArray());
        System.out.println("Connected to the database successfully");

        //Accessing the database
        MongoDatabase database = mongo.getDatabase("myDb");

        //Dropping the same collection created the last time because of the execution of this file
        database.getCollection("sampleCollection1").drop();

        //Creating a collection
        database.createCollection("sampleCollection1");
        System.out.println("Collection created successfully");
    }
}