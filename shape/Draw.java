package shape;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Draw<T> extends Frame
{
	public Draw()
	{
		setVisible(true);
		setSize(300, 300);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
	}

	public void paint(Graphics g)
	{
		g.drawRect(50, 50, 50, 50);
	}

	public static void main(String[] args)
	{
		Draw<Integer> d = new Draw<>();
	}
}
