import inventory.Ground;
import inventory.Item;

import javax.swing.*;


/**
 * Created by Anna on 01/11/2016.
 */
public class launcher
{

	public static void main(String[] args)
	{
		JFrame jFrame = new JFrame("Lameness Unlimited");

		// Monster
//        JPanel inPanel = new JPanel();
//        inPanel.setLayout(new BoxLayout(inPanel, BoxLayout.PAGE_AXIS));
//
//        ImageIcon monsterIcon = new ImageIcon("images/monster.png");
//        JLabel monsterLabel = new JLabel("Monster image on a label", monsterIcon, JLabel.CENTER);
//        monsterLabel.setIcon(monsterIcon);
//        monsterLabel.setHorizontalTextPosition(JLabel.CENTER);
//        monsterLabel.setVerticalTextPosition(JLabel.BOTTOM);
//
//        JButton killButton = new JButton();
//        killButton.setText("Kill Monster");
//        killButton.addActionListener(new KillMonster(mainPanel));
//
//        inPanel.add(monsterLabel);
//        inPanel.add(killButton);

		// Ground
		Ground ground = new Ground();

		// Create a TEST! item on the ground
		Item testItem = new Item("ring-0");
		ground.addItem(testItem);

		// Open Backpack - probably on button click
		// Also, backpack should be a permanent object per user/session/character/whatnot, so normally
		// we'd just use backpack.getDisplay()
//        Backpack backpack = new Backpack();
//        backpack.getDisplay(5, 2);

//        mainPanel.add(inPanel);
		jFrame.add(ground.getDisplay(300, 300));

		// TODO: the frame size doesn't hold for now
		jFrame.setSize(400, 400);
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		jFrame.pack();
		jFrame.setVisible(true);
	}
}
