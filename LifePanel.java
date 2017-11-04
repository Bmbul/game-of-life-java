package quiz;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class LifePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public LifeButton[][] cells;
	public LifePanel(int length, Container parent)
	{
		super();
		cells = new LifeButton[length][length];
		setLayout(new GridLayout(length, length));
		for(int i = 0; i < length; i++)
		{
			for(int j = 0; j < length; j++)
			{
				cells[i][j] = new LifeButton(this);
			}
		}
		parent.add(this);
	}

}
