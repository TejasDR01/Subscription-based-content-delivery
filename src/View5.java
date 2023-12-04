import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class View5 {
    private JLabel time1;
    private JLabel value;
    private JLabel time2;
    private JTextField time2Textfield;
    private JButton update;
    private JFrame frame1;

    public View5(String time) {
        frame1 = new JFrame("Change settings");
        frame1.getContentPane().setLayout(new BorderLayout());
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame1.setSize(600, 500);
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);

        time1 = new JLabel("Current set time: ");
        time2 = new JLabel("New Time(HH:MM:SS): ");
        value = new JLabel(time);
        time2Textfield = new JTextField();
        update = new JButton("submit");
        GroupLayout layout = new GroupLayout(frame1.getContentPane());
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(time1)
                        .addComponent(time2).addComponent(update))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(value)
                        .addComponent(time2Textfield)));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(time1)
                        .addComponent(value))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(time2)
                        .addComponent(time2Textfield))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(update)));

        layout.linkSize(SwingConstants.HORIZONTAL, update);
        frame1.getContentPane().setLayout(layout);
    }

    public JFrame getFrame() {
        return frame1;
    }

    public void setFrame(JFrame frame1) {
        this.frame1 = frame1;
    }

    public JTextField gettime2Textfield() {
        return time2Textfield;
    }

    public void settime2Textfield(JTextField time2Textfield) {
        this.time2Textfield = time2Textfield;
    }

    public JButton getchoose2() {
        return update;
    }
}
