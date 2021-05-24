import com.mongodb.BasicDBObject;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.models.EnglishScore;
import com.mongodb.models.Grade;
import com.mongodb.models.MathsScore;
import com.mongodb.models.Score;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static java.util.Collections.singletonList;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MappingPOJO {

    public static void main(String[] args) {
        //        MongoClientURI mongoClientURI = new MongoClientURI("mongodb://localhost:27017");

        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
        CodecRegistry pojoCodecRegistry = fromProviders(PojoCodecProvider.builder().automatic(true).build());
        CodecRegistry codecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), pojoCodecRegistry);
        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(codecRegistry)
                .build();
        try (MongoClient mongoClient = MongoClients.create(clientSettings)) {
            MongoDatabase db = mongoClient.getDatabase("sample_training");
            MongoCollection<Grade> grades = db.getCollection("grades", Grade.class);

            BasicDBObject document = new BasicDBObject();
            grades.deleteMany(document);

            List<Score> newScores = new ArrayList<>();
            Score score1 = new MathsScore("homework", 222d,11d);
            Score score2 = new EnglishScore("homework", 33d,22d);
            newScores.add(score1);
            newScores.add(score2);

            // create a new grade.
            Grade newGrade = new Grade().setStudentId(10003d)
                    .setClassId(10d)
                    .setScores(newScores);
            grades.insertOne(newGrade);
            System.out.println("Grade inserted.");

            // find by student id
            Grade grade = grades.find(eq("student_id", 10003d)).first();
            System.out.println("Grade found:\t" + grade);

            // ************************************ TRYING TYPECASTING ************************************
            // MathsScore mathsScore =  (MathsScore) grade.getScores().get(0);
            // System.out.println("Grade found:\t" + mathsScore);



            // UPDATE this grade: adding an exam grade
//            newScores = new ArrayList<>(grade.getScores());
//            score1 = new MathsScore("exam", 100d,10d);
//            score2 = new EnglishScore("exam", 90d,30d);
//
////            newScores.add(new Score().setType("exam").setScore(42d));
//            newScores.add(score1);
//            newScores.add(score2);
//            grade.setScores(newScores);
//            Document filterByGradeId = new Document("_id", grade.getId());
//            FindOneAndReplaceOptions returnDocAfterReplace = new FindOneAndReplaceOptions().returnDocument(ReturnDocument.AFTER);
//            Grade updatedGrade = grades.findOneAndReplace(filterByGradeId, grade, returnDocAfterReplace);
//            System.out.println("Grade replaced:\t" + updatedGrade);

            // delete this grade
//            System.out.println("Grade deleted:\t" + grades.deleteOne(filterByGradeId));
        }
    }
}