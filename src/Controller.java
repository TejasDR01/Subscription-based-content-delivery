import javax.swing.JOptionPane;
import java.io.*;
import java.util.*;

import com.mongodb.*;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

public class Controller {
    private View view;
    private View1 view1;
    private View2 view2;
    private View3 view3;
    private View4 view4;
    private View5 view5;

    public Controller(View v) {
        this.view = v;
    }

    public void initController() {
        this.view.getlogin().addActionListener(e -> login());
        this.view.getsignup().addActionListener(e -> signup());
    }

    private void login() {
        String usr = this.view.getUsrTextfield().getText();
        String pass = this.view.getpassTextfield().getText();
        try {
            MongoClient client = MongoClients.create();
            MongoDatabase db = client.getDatabase("contentdel");
            MongoCollection<Document> collection = db.getCollection("usercredentials");
            // String body = ((Object) collection).findOne().text;
            Document doc = collection.find(new Document("username", usr)).first();
            if (doc != null) {
                String pass1 = doc.getString("password");
                String name = doc.getString("username");
                if (pass.equals(pass1)) {
                    System.out.println("logged in successfully");
                    this.view.getFrame().dispose();
                    user(name);
                } else {
                    System.out.println("invalid username or password");
                    JOptionPane.showMessageDialog(null, "incorrect username or password", "Info",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "incorrect username or password", "Info",
                        JOptionPane.INFORMATION_MESSAGE);
                System.out.println("invalid username or password");
            }
        } catch (Exception e) {
            System.out.println("Error:\n" + e);
            JOptionPane.showMessageDialog(null, "Error:\n" + e, "Info",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void signup() {
        this.view1 = new View1();
        this.view1.getsignup1().addActionListener(e -> save());
    }

    private void save() {
        String usr = this.view1.getUsrTextfield1().getText();
        String pass1 = this.view1.getpassTextfield1().getText();
        String pass2 = this.view1.getpassTextfield2().getText();
        ArrayList<String> topics = new ArrayList<String>();
        String time = "";
        try {
            if (usr.isEmpty() || pass1.isEmpty()) {
                JOptionPane.showMessageDialog(null, "fields left empty:\n", "Info",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (pass1.equals(pass2)) {
                MongoClient client = MongoClients.create();
                MongoDatabase db = client.getDatabase("contentdel");
                MongoCollection<Document> collection = db.getCollection("usercredentials");
                Document doc = collection.find(new Document("username", usr)).first();
                if (doc != null) {
                    JOptionPane.showMessageDialog(null, "User already exists:\n", "Info",
                            JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                Document dbContent = new Document("_id", new ObjectId());
                dbContent.append("username", usr)
                        .append("password", pass1)
                        .append("topics", topics)
                        .append("time", time);
                collection.insertOne(dbContent);
                // collection.insertOne();
            } else {
                JOptionPane.showMessageDialog(null, "Password not matched:\n", "Info",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        } catch (Exception e) {
            System.out.println("Error:\n" + e);
            JOptionPane.showMessageDialog(null, "Error:\n" + e, "Info",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(null,
                "Created User: " + usr + "\nLog in and subscribe to topics, also set your time.", "Info",
                JOptionPane.INFORMATION_MESSAGE);
        this.view1.getFrame().dispose();
    }

    private void user(String name) {
        User u = new User(name);
        this.view2 = new View2();
        this.view2.getsubscribe().addActionListener(e -> subscribe(u));
        this.view2.getunsubscribe().addActionListener(e -> unsubscribe(u));
        this.view2.getchgsttgs().addActionListener(e -> chgsttgs(u));
        JOptionPane.showMessageDialog(null, "Logged in successfully", "Info",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void subscribe(User u) {
        try {
            MongoClient client = MongoClients.create();
            MongoDatabase db = client.getDatabase("contentdel");
            MongoCollection<Document> collection = db.getCollection("topics");
            MongoCollection<Document> collection1 = db.getCollection("usercredentials");
            // String body = ((Object) collection).findOne().text;
            Document doc = collection.find(new Document("key", "alltopics")).first();
            Document doc1 = collection1.find(new Document("username", u.Name)).first();
            if (doc != null) {
                ArrayList<String> cons = (ArrayList<String>) doc.get("value");
                ArrayList<String> cons1 = (ArrayList<String>) doc1.get("topics");
                for (String t : cons1) {
                    cons.remove(t);
                }
                String[] topics = new String[cons.size()];
                topics = cons.toArray(topics);
                this.view3 = new View3(topics);
                this.view3.getchoose().addActionListener(e -> submit1(u));
            }
        } catch (Exception e) {
            System.out.println("Error:\n" + e);
            JOptionPane.showMessageDialog(null, "Error:\n" + e, "Info",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void unsubscribe(User u) {
        try {
            MongoClient client = MongoClients.create();
            MongoDatabase db = client.getDatabase("contentdel");
            MongoCollection<Document> collection = db.getCollection("usercredentials");
            // String body = ((Object) collection).findOne().text;
            Document doc = collection.find(new Document("username", u.Name)).first();
            if (doc != null) {
                ArrayList<String> cons = (ArrayList<String>) doc.get("topics");
                String[] topics = new String[cons.size()];
                topics = cons.toArray(topics);
                this.view4 = new View4(topics);
                this.view4.getchoose1().addActionListener(e -> submit2(u));
            }
        } catch (Exception e) {
            System.out.println("Error:\n" + e);
            JOptionPane.showMessageDialog(null, "Error:\n" + e, "Info",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void chgsttgs(User u) {
        try {
            MongoClient client = MongoClients.create();
            MongoDatabase db = client.getDatabase("contentdel");
            MongoCollection<Document> collection = db.getCollection("usercredentials");
            // String body = ((Object) collection).findOne().text;
            Document doc = collection.find(new Document("username", u.Name)).first();
            if (doc != null) {
                String time = doc.getString("time");
                this.view5 = new View5(time);
                this.view5.getchoose2().addActionListener(e -> submit3(u));
            }
        } catch (Exception e) {
            System.out.println("Error:\n" + e);
            JOptionPane.showMessageDialog(null, "Error:\n" + e, "Info",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void submit1(User u) {
        ArrayList<String> info = (ArrayList<String>) this.view3.getmylist().getSelectedValuesList();
        if (u.subscribe(info)) {
            JOptionPane.showMessageDialog(null, "updated successfully:\n", "Info",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error:\n", "Info",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void submit2(User u) {
        ArrayList<String> info = (ArrayList<String>) this.view4.getmylist1().getSelectedValuesList();
        if (u.unsubscribe(info)) {
            JOptionPane.showMessageDialog(null, "updated successfully:\n", "Info",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error:\n", "Info",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void submit3(User u) {
        String time = this.view5.gettime2Textfield().getText();
        if (u.changeSettings(time)) {
            JOptionPane.showMessageDialog(null, "updated successfully\n", "Info",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Error:\n", "Info",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
