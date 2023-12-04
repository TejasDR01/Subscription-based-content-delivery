import java.util.*;
import com.mongodb.*;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

class User {
    String Name;

    User(String name) {
        this.Name = name;
    }

    boolean subscribe(ArrayList<String> info) {
        try {
            System.out.println(info);
            MongoClient client = MongoClients.create();
            MongoDatabase db = client.getDatabase("contentdel");
            MongoCollection<Document> collection = db.getCollection("usercredentials");
            // String body = ((Object) collection).findOne().text;
            Document doc = collection.find(new Document("username", this.Name)).first();
            ArrayList<String> topics = (ArrayList<String>) doc.get("topics");
            for (String t : info) {
                topics.add(t);
            }
            System.out.println(topics);
            collection.updateOne(Filters.eq("username", this.Name), Updates.set("topics", topics));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    boolean unsubscribe(ArrayList<String> info) {
        try {
            System.out.println(info);
            MongoClient client = MongoClients.create();
            MongoDatabase db = client.getDatabase("contentdel");
            MongoCollection<Document> collection = db.getCollection("usercredentials");
            // String body = ((Object) collection).findOne().text;
            Document doc = collection.find(new Document("username", this.Name)).first();
            ArrayList<String> topics = (ArrayList<String>) doc.get("topics");
            for (String t : info) {
                topics.remove(t);
            }
            System.out.println(topics);
            collection.updateOne(Filters.eq("username", this.Name), Updates.set("topics", topics));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    boolean changeSettings(String time) {
        try {
            System.out.println(time);
            MongoClient client = MongoClients.create();
            MongoDatabase db = client.getDatabase("contentdel");
            MongoCollection<Document> collection = db.getCollection("usercredentials");
            collection.updateOne(Filters.eq("username", this.Name), Updates.set("time", time));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
