package inventory;

import java.util.ArrayList;


/**
 * Created by Anna on 04/11/2016.
 * <p>
 * Represents the main playground and location of components.
 */
public class Ground
{
	public Ground()
	{
		// To contain items/objects/properties/etc. of the main panel

		groundItems = new ArrayList<Item>();
	}


	public boolean addItem(Item item)
	{
		return this.groundItems.add(item);
	}


	public boolean removeItem(Item item)
	{
		return this.groundItems.remove(item);
	}


	/* TODO: Actually, this only flies for now - ground could contain more than items,
	 * so it shouldn't return ItemGrid but something more comprehensive
	 */
	public ItemGrid getDisplay(int x, int y)
	{
		ItemGrid grid = new ItemGrid(this.groundItems.size());
		for (int i = 0; i < this.groundItems.size(); i++)
		{
			grid.addItem(this.groundItems.get(i));
		}
		return grid;
	}


	ArrayList<Item> groundItems;
}
