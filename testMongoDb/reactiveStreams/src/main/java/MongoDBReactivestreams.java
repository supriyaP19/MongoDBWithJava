import com.mongodb.client.model.CreateCollectionOptions;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ValidationOptions;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;


public class MongoDBReactivestreams {

    public static void main(String[] args) {

        // // Connect to a Standalone MongoDB Instance ->
        MongoClient mongoClient = MongoClients.create();
        // //You can explicitly specify the hostname to connect to a MongoDB instance running on the specified host on port 27017
        // MongoClient mongoClient = MongoClients.create("mongodb://host1");
        // //You can explicitly specify the hostname and the port
        // MongoClient mongoClient = MongoClients.create("mongodb://host1:27017");
        //##########################################################################################


        // //Connect to a Replica Set ->
        //You can specify a list of the all the replica set members’ ServerAddress:
        //MongoClient mongoClient = MongoClients.create(
        //        MongoClientSettings.builder()
        //                .applyToClusterSettings(builder ->
        //                        builder.hosts(Arrays.asList(
        //                                new ServerAddress("host1", 27017),
        //                                new ServerAddress("host2", 27017),
        //                                new ServerAddress("host3", 27017))))
        //                .build());
        //// You can specify the members using a ConnectionString
        //MongoClient mongoClient = MongoClients.create("mongodb://host1:27017,host2:27017,host3:27017");
        //
        ////With at least one member of the replica set and the replicaSet option specifying the replica set name:
        //MongoClient mongoClient = MongoClients.create("mongodb://host1:27017,host2:27017,host3:27017/?replicaSet=myReplicaSet");
        //##########################################################################################

        //connect to a sharded cluster
        // same as above
        //##########################################################################################

        // You can also use MongoClientSettings to specify TLS/SSL and the MongoCredential for the authentication information:
//        String user = "user1"; // the user name
//        String database = "myDb"; // the name of the database in which the user is defined
//        char[] password = "welcome".toCharArray(); // the password as a character array
//        // ...
//
//        MongoCredential credential = MongoCredential.createCredential(user, database, password);
//
//        // u can add one more option: .applyToSslSettings(builder -> builder.enabled(true))
//
//        MongoClientSettings settings = MongoClientSettings.builder()
//                .credential(credential)
//                .applyToClusterSettings(builder ->
//                        builder.hosts(Arrays.asList(new ServerAddress("host1", 27017))))
//                .build();
//
//        MongoClient mongoClient = MongoClients.create(settings);
        //##########################################################################################


        //Datbases and Collection
        MongoDatabase mongoDatabase = mongoClient.getDatabase("myDb");

        MongoCollection<Document> coll = mongoDatabase.getCollection("myTestCollection");
        ValidationOptions collOptions = new ValidationOptions().validator(
                Filters.or(Filters.exists("email"), Filters.exists("phone")));

        mongoDatabase.createCollection("contacts", new CreateCollectionOptions().validationOptions(collOptions))
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        System.out.println(" subscribed!!!");

                    }

                    @Override
                    public void onNext(Void aVoid) {

                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {
                        System.out.println(" collection created! ");

                    }
                });

        mongoDatabase.listCollectionNames().subscribe(new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                System.out.println(" subscribed!!!");
            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                System.out.println(" Collection list displaying..");
            }
        });

        // Pass BsonDocument.class as the second argument
        MongoCollection<BsonDocument> collection = mongoDatabase.getCollection("mycoll", BsonDocument.class);

        // insert a document
        BsonDocument document = BsonDocument.parse("{x: 1}");
        collection.insertOne(document).subscribe(new Subscriber<InsertOneResult>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(InsertOneResult insertOneResult) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });



    }

}
