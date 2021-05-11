import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import com.mongodb.client.model.Indexes;
import org.bson.Document;

public class InsertSelectRemoveFindAllDocuments {

    public void setUpTestData(MongoCollection<Document> collection){
        List<Document> list = new ArrayList<Document>();
        for (int i=1; i <= 10000; i++) {
            Document document1 = new Document("employeeId", i).append("employeeName", "TestEmployee_"+i);
            list.add(document1);
        }
        collection.insertMany(list);
    }

    private static void selectAllRecordsFromACollection(MongoCollection<Document> collection)
    {
        // Getting the iterable object
        FindIterable<Document> iterDoc = collection.find();
        int i = 1;
        // Getting the iterator
        Iterator it = iterDoc.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
            i++;
        }
    }

    public void removeDoc(MongoCollection<Document> collection) {
        BasicDBObject document = new BasicDBObject();
        collection.deleteMany(document);
        System.out.println(" Deleted all documents in the collection " + collection.getNamespace());

    }

    private static void findQuery(MongoCollection<Document> collection) {

        var query = new BasicDBObject("employeeId",
                new BasicDBObject("$gt", 9950));

        collection.find(query).forEach((Consumer<Document>) doc ->
                System.out.println(doc.toJson()));
    }

    private static void selectSingleRecordAndFieldByRecordNumber(DBCollection collection)
    {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("employeeId", 5);
        BasicDBObject fields = new BasicDBObject();
        fields.put("employeeId", 1);

        DBCursor cursor = collection.find(whereQuery, fields);
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

    public static void main( String args[] ) {

        // Creating a Mongo client
        MongoClient mongo = new MongoClient( "localhost" , 27017 );

        // Creating Credentials
        MongoCredential credential;
        credential = MongoCredential.createCredential("sampleUser", "myDb", "password".toCharArray());
        System.out.println("Connected to the database successfully");

        // Accessing the database
        MongoDatabase database = mongo.getDatabase("myDb");

        // Retrieving a collection using DBCollection
        MongoCollection<Document> collection = database.getCollection("sampleCollection3");
        System.out.println("Collection sampleCollection selected successfully");

        InsertSelectRemoveFindAllDocuments retrievingAllDocuments = new InsertSelectRemoveFindAllDocuments();
        //Deleting already created collection
        retrievingAllDocuments.removeDoc(collection);

        //Adding documents in the collection
        retrievingAllDocuments.setUpTestData(collection);

        //Selects all docs of a collection and print
        // retrievingAllDocuments.selectAllRecordsFromACollection(collection);

        long start1 = System.currentTimeMillis();
        retrievingAllDocuments.findQuery(collection);
        long end1 = System.currentTimeMillis();
        long timeElapsed = end1 - start1;


        collection.createIndex(Indexes.ascending("employeeId"));

        long start2 = System.currentTimeMillis();
        retrievingAllDocuments.findQuery(collection);
        long end2 = System.currentTimeMillis();
        long timeElapsed2 = end2 - start2;
        System.out.println("##################################################################################");
        System.out.println(" the time before and after indexing is : " + timeElapsed + " AND " + timeElapsed2); // TIME : 54ms and 8ms respectively
        System.out.println("##################################################################################");



    }
}