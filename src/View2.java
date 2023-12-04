import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

public class View2 {
    private JFrame frame;
    private JButton subscribe;
    private JButton chgsttgs;
    private JButton unsubscribe;

    public View2() {
        // frame.getContentPane().removeAll();
        // frame.repaint();

        frame = new JFrame("User");
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 100);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        subscribe = new JButton("subscribe");
        unsubscribe = new JButton("unsubscribe");
        chgsttgs = new JButton("change settings");
        GroupLayout layout = new GroupLayout(frame.getContentPane());
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(subscribe))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(unsubscribe))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(chgsttgs)));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(subscribe)
                        .addComponent(unsubscribe).addComponent(chgsttgs)));

        layout.linkSize(SwingConstants.HORIZONTAL, subscribe, unsubscribe, chgsttgs);
        frame.getContentPane().setLayout(layout);
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JButton getsubscribe() {
        return this.subscribe;
    }

    public JButton getunsubscribe() {
        return this.unsubscribe;
    }

    public JButton getchgsttgs() {
        return this.chgsttgs;
    }
}
