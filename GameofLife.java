package quiz;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GameofLife extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private LifePanel field;
	private JButton reset = new JButton("Reset");
	private JButton rand = new JButton("Random");
	private JButton step = new JButton("Step");
	private JButton play = new JButton("Play");
	private JButton stop = new JButton("Stop");
	private Timer timer;

	public GameofLife(int length) {
		super("Game of life");
		setLayout(new FlowLayout());
		reset.addActionListener(this);
		add(reset);
		rand.addActionListener(this);
		add(rand);
		step.addActionListener(this);
		add(step);
		play.addActionListener(this);
		add(play);
		stop.addActionListener(this);
		add(stop);
		field = new LifePanel(length, this);
		setSize(800, 700);
		setVisible(true);
	}

	public void reset() {
		int length = field.cells.length;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				field.cells[i][j].setAlive(false);
			}
		}
	}

	public void randomize() {
		int length = field.cells.length;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				field.cells[i][j].setAlive(Math.random() > 0.75);
			}
		}
	}

	public void step() {
		int length = field.cells.length;
		for (int rowNum = 0; rowNum < length; rowNum++) {
			for (int colNum = 0; colNum < length; colNum++) {
				int startRow = rowNum - 1 > -1 ? rowNum - 1 : rowNum;
				int endRow = rowNum + 1 < length ? rowNum + 1 : rowNum;
				int startCol = colNum - 1 > -1 ? colNum - 1 : colNum;
				int endCol = colNum + 1 < length ? colNum + 1 : colNum;
				int aliveNeighborsCount = 0;
				boolean aliveState = field.cells[rowNum][colNum].isAlive();
				for (int i = startRow; i <= endRow; i++) {
					for (int j = startCol; j <= endCol; j++) {
						if (Math.abs(colNum - i) + Math.abs(rowNum - j) > 0) {
							aliveNeighborsCount += field.cells[i][j].isAlive() ? 1 : 0;
						}
					}
				}
				
				if(aliveState) {
					if(aliveNeighborsCount < 2 || aliveNeighborsCount > 3)
						field.cells[rowNum][colNum].setAlive(false);
				}
				else {
					if(aliveNeighborsCount == 3)
						field.cells[rowNum][colNum].setAlive(true);
				}
			}
		}
	}
	
	public void play() {
		timer = new Timer();
		TimerTask timerTask = new TimerTask() {
		    @Override
		    public void run() {
		       step();
		    }
		};
		timer.schedule(timerTask, 0, 50);
	}
	
	public void stop() {
		timer.cancel();
	}

	@Override
	public void actionPerformed(ActionEvent click) {
		if (click.getSource() == reset)
			reset();
		else if (click.getSource() == rand)
			randomize();
		else if (click.getSource() == step)
			step();
		else if (click.getSource() == play)
			play();
		else if (click.getSource() == stop)
			stop();

	}

	public static void main(String[] args) {
		new GameofLife(30);
	}

}
