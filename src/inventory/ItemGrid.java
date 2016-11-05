package inventory;

import javax.swing.*;
import java.awt.*;


/**
 * Created by Anna on 04/11/2016.
 * <p>
 * A generic grid panel for placement of items.
 */
public class ItemGrid extends JPanel
{
	public ItemGrid(int n)
	{
		super(new GridLayout(n, 2));
		this.setBackground(Color.black);
	}


	/**
	 * Add item display components to the grid
	 *
	 * @param item
	 */
	public void addItem(Item item)
	{
		this.add(item.getDisplay());

	}

}
