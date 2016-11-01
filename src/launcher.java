import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Anna on 01/11/2016.
 */
public class launcher {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame("Lameness Unlimited");
        jFrame.setSize(400, 400);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        JPanel inPanel = new JPanel();
        inPanel.setLayout(new BoxLayout(inPanel, BoxLayout.PAGE_AXIS));

        ImageIcon monsterIcon = new ImageIcon("images/monster.png");
        JLabel monsterLabel = new JLabel("Monster image on a label", monsterIcon, JLabel.CENTER);
        monsterLabel.setIcon(monsterIcon);
        monsterLabel.setHorizontalTextPosition(JLabel.CENTER);
        monsterLabel.setVerticalTextPosition(JLabel.BOTTOM);

        JButton killButton = new JButton();
        killButton.setText("Kill Monster");
        killButton.addActionListener(new KillMonster(mainPanel));

        inPanel.add(monsterLabel);
        inPanel.add(killButton);

        mainPanel.add(inPanel);
        jFrame.add(mainPanel);

        jFrame.setVisible(true);
    }
}
