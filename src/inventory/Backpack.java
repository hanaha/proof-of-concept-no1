package inventory;

import java.util.ArrayList;


/**
 * Created by Anna on 04/11/2016.
 *
 * Player's backpack.
 */
public class Backpack
{
	public Backpack(int packSize)
	{
		this.packList = new ArrayList<>(packSize);
		this.packSize = packSize;
		this.packFill = 0;
	}


	public boolean addItem(Item item)
	{
		if (this.packList.add(item))
		{
			this.packFill++;
			return true;
		}
		return false;
	}


	public boolean removeItem(Item item)
	{
		if (this.packList.remove(item))
		{
			this.packFill--;
			return true;
		}
		return false;
	}


	public ItemGrid getDisplay(int x, int y)
	{
		ItemGrid backpackGrid = new ItemGrid(this.packFill);
		return backpackGrid;
	}


	public ArrayList<Item> packList;
	// If backpack is limited to a number of items
	public int packSize;
	public int packFill;
}
