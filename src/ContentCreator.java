import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.json.*;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class ContentCreator {
    private String apiLink = "https://newsdata.io/api/1/news?apikey=pub_6805f40a379c0d33af43eeb434841e6cb3fd&country=in&language=en&q=";
    private String query;

    public void setQuery(String query) {
        this.query = query;
    }

    public void getNews() throws Exception {
        String s = apiLink + query;
        try {
            URL url = new URL(s);

            // Preparing the cache
            File file = new File("./" + query + "Cache.txt");

            Scanner scan = new Scanner(url.openStream());
            String str = new String();
            while (scan.hasNext())
                str += scan.nextLine();
            scan.close();

            // Writing to cache
            try (FileOutputStream fos = new FileOutputStream(file);
                    BufferedOutputStream bos = new BufferedOutputStream(fos)) {
                // convert string to byte array
                byte[] bytes = str.getBytes();
                // write byte array to file
                bos.write(bytes);
                bos.close();
                fos.close();
                System.out.print("Data written to file successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
            // for testing
            /*
             * String path = "./cache.txt";
             * String str = Files.readString(new File(path).toPath());
             */

            // build a JSON object
            JSONObject obj = new JSONObject(str);
            if (!obj.getString("status").equals("success"))
                return;

            // get the first result
            JSONObject res = obj.getJSONArray("results").getJSONObject(0);

            // Extract info for the email
            String title = res.getString("title");

            String description = "";
            String content = "";
            String fullDescription = "";

            try {
                description = res.getString("description");
            } catch (Exception e) {
                description = "";
            }

            try {
                content = res.getString("content");
            } catch (Exception e) {
                content = "";
            }

            try {
                fullDescription = res.getString("full_description");
            } catch (Exception e) {
                fullDescription = "";
            }

            long lenDescription = description.length();
            long lenContent = content.length();
            long lenFullDescription = fullDescription.length();

            String emailContent;
            if (lenDescription > lenContent && lenDescription > lenFullDescription) {
                // System.out.println("Description is the longest");
                emailContent = description;
            } else if (lenContent > lenDescription && lenContent > lenFullDescription) {
                // System.out.println("Content is the longest");
                emailContent = content;
            } else {
                // System.out.println("Full description is the longest");
                emailContent = fullDescription;
            }

            // Update db
            // Prepare document
            Document dbContent = new Document("_id", new ObjectId());
            dbContent.append("type", "actual")
                    .append("title", title)
                    .append("emailContent", emailContent);

            // Mongo Connection
            MongoClient client = MongoClients.create();
            MongoDatabase db = client.getDatabase("contentdel");

            // Send to database
            MongoCollection<Document> Collection = db.getCollection(query);
            Collection.insertOne(dbContent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Run this every few hours to update the db
    public static void main(String[] args) {
        ContentCreator cc = new ContentCreator();

        String[] topicList = { "cryptocurrency", "stocks", "cricket", "health", "finance" };

        for (String topic : topicList) {
            cc.setQuery(topic);
            try {
                cc.getNews();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
