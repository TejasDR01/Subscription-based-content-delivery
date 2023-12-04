public class App {
    public static void main(String[] args) {
        View v = new View("Login");
        Controller c = new Controller(v);
        c.initController();
        Service s = new Service();
    }
}