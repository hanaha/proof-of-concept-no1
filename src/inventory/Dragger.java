package inventory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


/**
 * A crude copy from https://examples.javacodegeeks.com/desktop-java/awt/event/a-simple-mouse-drag-example/
 */
public class Dragger extends Component implements MouseListener, MouseMotionListener
{

	public Dragger(Image i)
	{
		super();
		bImage = i;
		setSize(100, 100);
		addMouseListener(this);
		addMouseMotionListener(this);
	}


	@Override
	public void mouseClicked(MouseEvent event)
	{
	}


	@Override
	public void mouseEntered(MouseEvent event)
	{
	}


	@Override
	public void mouseExited(MouseEvent event)
	{
	}


	@Override
	public void mousePressed(MouseEvent event)
	{
		Point point = event.getPoint();
		System.out.println("mousePressed at " + point);
		sX = point.x;
		sY = point.y;
		dragging = true;
	}


	@Override
	public void mouseReleased(MouseEvent event)
	{
		dragging = false;
		System.out.println("Drawn rectangle area IS " + sX + "," + sY + " to " + curX + "," + curY);
	}


	@Override
	public void mouseDragged(MouseEvent event)
	{
		Point p = event.getPoint();
		// System.err.println("mouse drag to " + p);
		curX = p.x;
		curY = p.y;
		if (dragging)
		{
			repaint();
		}
	}


	@Override
	public void paint(Graphics graphic)
	{
		int w = curX - sX, h = curY - sY;
		Dimension dims = getSize();
		graphic.drawImage(bImage, 0, 0, dims.width, dims.height, this);
		if (sX < 0 || sY < 0)
		{
			return;
		}

		System.out.println("Rect[" + sX + "," + sY + "] size " + w + "x" + h);

		graphic.setColor(Color.red);
		graphic.fillRect(sX, sY, w, h);
	}


	@Override
	public void mouseMoved(MouseEvent e)
	{
	}


	int sX = -1, sY = -1;
	Image bImage;
	boolean dragging = false;
	int curX = -1, curY = -1;

}
