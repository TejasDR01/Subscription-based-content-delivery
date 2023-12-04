import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class View1 {
    private JFrame frame1;
    private JButton signup1;
    private JTextField passTextfield2;
    private JTextField passTextfield1;
    private JTextField UsrTextfield1;
    private JLabel pass2;
    private JLabel pass1;
    private JLabel Usr1;

    public View1() {
        frame1 = new JFrame("Signup");
        frame1.getContentPane().setLayout(new BorderLayout());
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame1.setSize(400, 300);
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);

        Usr1 = new JLabel("Enter Username :");
        pass1 = new JLabel("Password :");
        pass2 = new JLabel("Re-enter Password :");
        UsrTextfield1 = new JTextField();
        passTextfield1 = new JTextField();
        passTextfield2 = new JTextField();
        signup1 = new JButton("Submit");
        GroupLayout layout = new GroupLayout(frame1.getContentPane());
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(Usr1)
                        .addComponent(pass1).addComponent(pass2).addComponent(signup1))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(UsrTextfield1)
                        .addComponent(passTextfield1).addComponent(passTextfield2)));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(Usr1)
                        .addComponent(UsrTextfield1))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(pass1)
                        .addComponent(passTextfield1))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(pass2)
                        .addComponent(passTextfield2))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(signup1)));

        layout.linkSize(SwingConstants.HORIZONTAL, signup1);
        frame1.getContentPane().setLayout(layout);
    }

    public JFrame getFrame() {
        return this.frame1;
    }

    public void setFrame(JFrame frame1) {
        this.frame1 = frame1;
    }

    public JTextField getUsrTextfield1() {
        return this.UsrTextfield1;
    }

    public void setUsrTextfield1(JTextField UsrTextfield1) {
        this.UsrTextfield1 = UsrTextfield1;
    }

    public JTextField getpassTextfield1() {
        return this.passTextfield1;
    }

    public void setpassTextfield1(JTextField passTextfield1) {
        this.passTextfield1 = passTextfield1;
    }

    public JTextField getpassTextfield2() {
        return this.passTextfield2;
    }

    public void setpassTextfield2(JTextField passTextfield2) {
        this.passTextfield2 = passTextfield2;
    }

    public JButton getsignup1() {
        return this.signup1;
    }
}
