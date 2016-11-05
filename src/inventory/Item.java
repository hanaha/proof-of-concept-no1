package inventory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


/**
 * Created by Anna on 04/11/2016.
 *
 * Represents an item that can be picked up and placed in backpack.
 */
public class Item
{
	public Item(String id)
	{
		this.identifier = id;
		try
		{
			// TODO: might need some extra checks on file type
			File iconFile = new File("images/" + this.identifier + ".png");
			if (iconFile.isFile())
			{
				this.image = ImageIO.read(iconFile);
			}
			else
			{
				iconFile = new File("images/default.png");
				this.image = ImageIO.read(iconFile);
			}
		} catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}


	public Dragger getDisplay()
	{
		return new Dragger(this.image);
	}


	String identifier;
	Image image;
}
