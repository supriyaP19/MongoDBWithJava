import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.List;

import static java.util.Arrays.asList;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class POJOoperations {

    public static void main(String[] args) {
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));
        MongoClient mongoClient = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(pojoCodecRegistry).build());
        MongoDatabase database = mongoClient.getDatabase("myDb");
        database = database.withCodecRegistry(pojoCodecRegistry);

        InsertSelectRemoveFindAllDocuments retrievingAllDocuments = new InsertSelectRemoveFindAllDocuments();
        MongoCollection<Person> collection = database.getCollection("people", Person.class);
        //Deleting already created collection
        retrievingAllDocuments.removeDocPOJO(collection);

        collection = database.getCollection("people", Person.class);
        List<Person> people = asList(
                new Person("Charles Babbage", 45, new Address("5 Devonshire Street", "London", "W11")),
                new Person("Alan Turing", 28, new Address("Bletchley Hall", "Bletchley Park", "MK12")),
                new Person("Timothy Berners-Lee", 61, new Address("Colehill", "Wimborne", null))
        );
        collection.insertMany(people);

        Block<Person> printBlock = new Block<Person>() {
            @Override
            public void apply(final Person person) {
                System.out.println(person.getId() +", "+ person.getAge() +", "+ person.getName() +", "+ person.getAddress());
            }
        };

        collection.find().forEach(printBlock);

    }

}
