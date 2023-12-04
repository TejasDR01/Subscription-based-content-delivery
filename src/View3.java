import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;

public class View3 {
    private JList mylist;
    private JLabel topic;
    private JButton choose;
    private JFrame frame1;

    public View3(String[] info) {
        frame1 = new JFrame("Subscribe");
        frame1.getContentPane().setLayout(new BorderLayout());
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame1.setSize(600, 500);
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
        topic = new JLabel("select the topics: ");
        choose = new JButton("subscribe");
        // String[] info = { "art", "tech", "science" };
        JComboBox selectTopic = new JComboBox(info);
        selectTopic.setEditable(true);

        // create a list with the same data model
        mylist = new JList(selectTopic.getModel());
        GroupLayout layout = new GroupLayout(frame1.getContentPane());
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(topic)
                        .addComponent(choose))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(mylist)));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(topic)
                        .addComponent(mylist))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(choose)));

        layout.linkSize(SwingConstants.HORIZONTAL, choose);
        frame1.getContentPane().setLayout(layout);
    }

    public JFrame getFrame() {
        return this.frame1;
    }

    public void setFrame(JFrame frame1) {
        this.frame1 = frame1;
    }

    public JButton getchoose() {
        return this.choose;
    }

    public JList getmylist() {
        return this.mylist;
    }
}
