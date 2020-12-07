package graphicalElements;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import gameCommons.Game;
import gameCommons.IFrog;
import gameCommons.LauchGame;
import util.Direction;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

// create a panel that you can draw on.
class MyPanel extends JPanel {

	public int progress;

	public MyPanel(int prg)
	{
		this.progress = prg;
	}

	public void paint(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(0,0,this.progress * 10 ,15);
	}


}


public class FroggerGraphic extends JPanel implements IFroggerGraphics, KeyListener {
	private ArrayList<Element> elementsToDisplay;

	public int getElapsedTime() {
		return elapsedTime;
	}

	private int pixelByCase = 16;
	private int width;
	private int height;
	private IFrog frog;
	private JFrame frame;
	private boolean selectMode = true;
	public int elapsedTime = 0;
	public int score = 0;
	public boolean warningIsMade = false;
	public Game myGame;
	public boolean part4;

	public int getScore() {
		return score;
	}

	public void setPart4(boolean part4) {
		this.part4 = part4;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void animWarning()
	{
		setBackground(Color.YELLOW);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		setBackground(Color.GRAY);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		setBackground(Color.YELLOW);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		setBackground(Color.GRAY);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		setBackground(Color.YELLOW);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		setBackground(Color.GRAY);

	}

	public FroggerGraphic(int width, int height) {
		this.width = width;
		this.height = height;
		this.part4 = false;
		elementsToDisplay = new ArrayList<Element>();

		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(width * pixelByCase, height * pixelByCase));



		JFrame frame = new JFrame("Frogger");
		this.frame = frame;

		this.frame.setLayout(new BorderLayout());


		/*MyPanel panelTop = new MyPanel(0);
		panelTop.setBackground(Color.gray);
		panelTop.setPreferredSize(new Dimension(500, 15));



		this.frame.add(panelTop,BorderLayout.PAGE_START);*/



			JLabel label = new JLabel("FROGGER", JLabel.CENTER);
			this.frame.add(label, BorderLayout.PAGE_END);




		Runnable helloRunnable = new Runnable() {
			public void run() {

				elapsedTime++;
				score++;
				if(part4)
				{
					label.setText("Il te reste " + Integer.toString(30 - elapsedTime) + " secondes !");
				}
				else
				{
					label.setText("-");
				}

				if(30 - elapsedTime <= 10)
				{
					if(!warningIsMade && part4)
					{

						animWarning();
						warningIsMade = true;
					}

				}
				//frame.remove(panelTop);
				//Puis on redessine le panelTop
				//MyPanel panelTop = new MyPanel(elapsedTime);
				//panelTop.setBackground(Color.gray);
				//.setPreferredSize(new Dimension(500, 15));
				//frame.add(panelTop,BorderLayout.PAGE_START);

			}
		};

		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(helloRunnable, 0, 1, TimeUnit.SECONDS);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(this);
		frame.pack();
		frame.setVisible(true);
		frame.addKeyListener(this);



	}




	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Element e : elementsToDisplay) {
			g.setColor(e.color);
			g.fillRect(pixelByCase * e.absc, pixelByCase * (height - 1 - e.ord), pixelByCase, pixelByCase - 1);
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			frog.move(Direction.up);
			break;
		case KeyEvent.VK_DOWN:
			frog.move(Direction.down);
			break;
		case KeyEvent.VK_LEFT:
			frog.move(Direction.left);
			break;
		case KeyEvent.VK_RIGHT:
			frog.move(Direction.right);
		}
	}

	public void clear() {
		this.elementsToDisplay.clear();
	}

	public void add(Element e) {
		this.elementsToDisplay.add(e);
	}

	public void setFrog(IFrog frog) {
		this.frog = frog;
	}

	public void endGameScreen(String s) {
		frame.remove(this);
		JLabel label = new JLabel(s);
		label.setFont(new Font("Verdana", 1, 20));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setSize(this.getSize());
		frame.getContentPane().add(label);
		frame.repaint();

	}

}
