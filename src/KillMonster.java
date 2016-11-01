import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Anna on 01/11/2016.
 */
public class KillMonster implements ActionListener {
    JPanel panel;
    public KillMonster(JPanel panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        JLabel notice = new JLabel("whutwhutwhut! ya killed da monstah, munn!");
//        JDialog notice = new JDialog(this.panel);
        ImageIcon icon = new ImageIcon("images/winning.png");
        JOptionPane.showMessageDialog(this.panel, "whutwhutwhut! yo ya killed da monstah, munn!", "killah!", JOptionPane.PLAIN_MESSAGE, icon);
    }
}
