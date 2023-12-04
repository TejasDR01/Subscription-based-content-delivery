import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;

public class View4 {
    private JLabel topic1;
    private JButton choose1;
    private JList mylist1;
    private JFrame frame1;

    public View4(String[] info) {
        frame1 = new JFrame("UnSubscribe");
        frame1.getContentPane().setLayout(new BorderLayout());
        frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame1.setSize(600, 500);
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
        topic1 = new JLabel("select the topics: ");
        choose1 = new JButton("unsubscribe");
        // String[] info = { "art", "tech", "science" };
        JComboBox selectTopic = new JComboBox(info);
        selectTopic.setEditable(true);

        // create a list with the same data model
        mylist1 = new JList(selectTopic.getModel());
        GroupLayout layout = new GroupLayout(frame1.getContentPane());
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(topic1)
                        .addComponent(choose1))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(mylist1)));

        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(topic1)
                        .addComponent(mylist1))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(choose1)));

        layout.linkSize(SwingConstants.HORIZONTAL, choose1);
        frame1.getContentPane().setLayout(layout);
    }

    public JFrame getFrame() {
        return this.frame1;
    }

    public void setFrame(JFrame frame1) {
        this.frame1 = frame1;
    }

    public JButton getchoose1() {
        return this.choose1;
    }

    public JList getmylist1() {
        return this.mylist1;
    }

}
