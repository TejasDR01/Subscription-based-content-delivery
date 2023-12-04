import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class View {

    private JFrame frame;
    private JLabel Usr;
    private JLabel pass;
    private JTextField UsrTextfield;
    private JTextField passTextfield;
    private JButton login;
    private JButton signup;

    public View(String title) {
        frame = new JFrame(title);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Usr = new JLabel("Username :");
        pass = new JLabel("Password :");

        UsrTextfield = new JTextField();
        passTextfield = new JTextField();
        login = new JButton("login");
        signup = new JButton("Signup");
        GroupLayout layout = new GroupLayout(frame.getContentPane());
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(Usr)
                        .addComponent(pass).addComponent(login))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(UsrTextfield)
                        .addComponent(passTextfield).addComponent(signup)));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(Usr)
                        .addComponent(UsrTextfield))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(pass)
                        .addComponent(passTextfield))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(login)
                        .addComponent(signup)));

        layout.linkSize(SwingConstants.HORIZONTAL, login);
        frame.getContentPane().setLayout(layout);
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JTextField getUsrTextfield() {
        return this.UsrTextfield;
    }

    public void setUsrTextfield(JTextField UsrTextfield) {
        this.UsrTextfield = UsrTextfield;
    }

    public JTextField getpassTextfield() {
        return this.passTextfield;
    }

    public void setpassTextfield(JTextField passTextfield) {
        this.passTextfield = passTextfield;
    }

    public JButton getlogin() {
        return this.login;
    }

    public JButton getsignup() {
        return this.signup;
    }
}
