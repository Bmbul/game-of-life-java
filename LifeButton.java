package quiz;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class LifeButton extends JButton implements ActionListener {
	private static final long serialVersionUID = 1L;
	// We could have general state declared here and use it for all
	// The operations
	public LifeButton(Container parent)
	{
		super();
		setBackground(Color.white);
		setPreferredSize(new Dimension(20, 20));
		// Problems on Mac, should add those lines
		setOpaque(true);
		setBorderPainted(false);
		parent.add(this);
		addActionListener(this);
	}
	public boolean isAlive()
	{
		// If we did the thing mentioned above we just would be
		// returning that state variable's value here
		return getBackground() == Color.black ? true : false;
	}
	public void setAlive(boolean state)
	{
		Color bgColor = state ? Color.black : Color.white;
		setBackground(bgColor);
		// Could be setting the state here
	}
	public void actionPerformed(ActionEvent click)
	{
		setAlive(!isAlive());
	}
}
