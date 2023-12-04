import java.util.*;
import com.mongodb.client.MongoClients;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Service {
    MongoClient client = MongoClients.create();
    MongoDatabase db = client.getDatabase("contentdel");
    MongoCollection<Document> usrCollection = db.getCollection("usercredentials");

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

    public void backendProces() {

        FindIterable<Document> usrDocList = usrCollection.find();

        /*
         * while(usrDocList.first() == null) {
         * 
         * //Fetch users at the current time
         * usrDocList = usrCollection.find(new Document("time",
         * dtf.format(LocalDateTime.now())));
         * }
         */
        usrDocList = usrCollection.find(new Document("time", dtf.format(LocalDateTime.now())));
        for (Document userDoc : usrDocList) {
            // Extract email
            String dbEmail = userDoc.getString("username");

            // Extract time
            String dbTime = userDoc.getString("time");
            int hh = Integer.parseInt(dbTime.substring(0, 2));
            int mm = Integer.parseInt(dbTime.substring(3, 5));
            int ss = Integer.parseInt(dbTime.substring(6, 8));
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.HOUR_OF_DAY, hh);
            cal.set(Calendar.MINUTE, mm);
            cal.set(Calendar.SECOND, ss);

            // Extract topics
            ArrayList<String> dbTopics = (ArrayList<String>) userDoc.get("topics");

            // Send Email
            ContentDispatcher c = new ContentDispatcher();
            for (String topic : dbTopics) {
                MongoCollection<Document> collection = db.getCollection(topic);
                Document doc = collection.find(new Document("type", "actual")).first();

                if (doc != null) {
                    String title = doc.getString("title");

                    String emailContent = doc.getString("emailContent");
                    c.setDetails(dbEmail, title, emailContent);
                    c.sendmail();

                    /*
                     * Timer timer = new Timer();
                     * 
                     * timer.schedule(new TimerTask() {
                     * 
                     * @Override
                     * public void run() {
                     * // Calling sendmail method here
                     * c.setDetails(dbEmail, title, emailContent);
                     * c.sendmail();
                     * }
                     * }, cal.getTime(), 86400000); // To send email per day (86400000 == 24 hrs)
                     * cal.set(Calendar.SECOND, 7); // Delay of 7 seconds between two emails
                     */
                } else {
                    System.out.println("No content found");
                }

            }
        }
    }

    public Service() {
        while (true)
            this.backendProces();
    }
}
