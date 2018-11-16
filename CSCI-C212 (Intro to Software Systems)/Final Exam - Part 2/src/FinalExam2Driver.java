////////////////////////////////////////////////////////////////////////////////////
//
// C212 Spring 18
// Final Exam Part 2
//
//
//
// Author  Sang Hyeong Woo
// Username   sangwoo
// Last Edited:  May 1st, 2018
//
//////////////////////////////////////////////////////////////////////////////////
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FinalExam2Driver extends JPanel implements ActionListener {
	
	// local variables
	private final int FRAME_WIDTH = 800;
	private final int FRAME_HEIGHT = 800;
	
	private Timer timer;
	private boolean raceEnd;
	
	private int start;
	private int squares;
	private int tortoisePosition;
	private int tortoiseLap;
	private int harePosition;
	private int hareLap;
	private boolean tHasCheckPoint;
	private boolean hHasCheckPoint;
	private Point tortoisePos;
	private Point harePos;
	
	private JLabel tPosition;
	private JLabel tLap;
	private JLabel hPosition;
	private JLabel hLap;
	
	
	// constructor
	public FinalExam2Driver() {
		super();
		this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		this.setFocusable(true);
		this.setBackground(Color.white);
		
		this.timer = new Timer(5, this);
		this.raceEnd = false;
		this.start = 1;
		this.squares = 640;
		this.tortoisePosition = 1;
		this.tortoiseLap = 0;
		this.harePosition = 1;
		this.hareLap = 0;
		this.tHasCheckPoint = false;
		this.hHasCheckPoint = false;
		this.tortoisePos = new Point (1, 700);
		this.harePos = new Point (1, 700);
		this.tPosition = new JLabel("Tortoise Position: " + this.tortoisePosition);
		this.tPosition.setHorizontalAlignment(JLabel.LEFT);
		this.tLap = new JLabel("Tortoise Lap: " + tortoiseLap);
		this.tLap.setHorizontalAlignment(JLabel.LEFT);
		this.hPosition = new JLabel("Hare Position: " + this.harePosition);
		this.hPosition.setHorizontalAlignment(JLabel.LEFT);
		this.hLap = new JLabel("Hare Lap: " + hareLap);
		this.hLap.setHorizontalAlignment(JLabel.LEFT);
		
		
		// defining 'start' jbutton
		JButton start = new JButton("Start");
		class StartActionListener implements ActionListener {
			public void actionPerformed (ActionEvent event) {
				if (raceEnd) {
					timer.stop();
				}
				else {
					System.out.println("BANG !!!!! AND THEY'RE OFF !!!!!");
					timer.start();
					repaint();
				}
			}
		}
		
		// defining 'stop' jbutton
		JButton stop = new JButton("Stop");
		class StopActionListener implements ActionListener {
			public void actionPerformed (ActionEvent event) {
				timer.stop();
			}
		}
		
		ActionListener startListener = new StartActionListener();
		start.addActionListener(startListener);
		
		ActionListener stopListener = new StopActionListener();
		stop.addActionListener(stopListener);
		
		this.add(start);
		this.add(stop);
		this.add(tPosition);
		this.add(tLap);
		this.add(hPosition);
		this.add(hLap);
	}
	
	
	// Drawing 2 mountains, a tortoise and a hare
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int[] mountain1X = {0, 200, 400};
		int[] mountain1Y = {700, 350, 700};
		g.setColor(Color.red);
		g.fillPolygon(mountain1X, mountain1Y, 3);
		
		int[] mountain2X = {400, 600, 800};
		int[] mountain2Y = {700, 350, 700};
		g.setColor(Color.red);
		g.fillPolygon(mountain2X, mountain2Y, 3);
		
		g.setColor(Color.green);
		g.fillRect((int)tortoisePos.getX(), (int)tortoisePos.getY(), 10, 10);
		
		g.setColor(Color.gray);
		g.fillRect((int)harePos.getX(), (int)harePos.getY(), 10, 10);
	}
	
	
	// Defining actions to be performed when JButtons are pressed
	public void actionPerformed (ActionEvent event) {
		
		// Defining direction the tortoise has to travel to
		if (tortoisePosition < squares && !tHasCheckPoint) {
			tortoisePosition = tortoiseForward(tortoisePosition);
			tortoisePos = new Point(tortoisePosition, yPosition(tortoisePosition));
		}
		else if (tortoisePosition == squares && !tHasCheckPoint) {
			tortoisePosition = tortoiseBackward(tortoisePosition);
			tortoisePos = new Point(tortoisePosition, yPosition(tortoisePosition));
			tHasCheckPoint = true;
		}
		else if (tortoisePosition > start && tHasCheckPoint) {
			tortoisePosition = tortoiseBackward(tortoisePosition);
			tortoisePos = new Point(tortoisePosition, yPosition(tortoisePosition));
		}
		else if (tortoisePosition == start && tHasCheckPoint) {
			tortoiseLap ++;
			tortoisePosition = tortoiseForward(tortoisePosition);
			tortoisePos = new Point(tortoisePosition, yPosition(tortoisePosition));
			tHasCheckPoint = false;
		}
		
		// Defining direction the hare has to travel to
		if (harePosition < squares && !hHasCheckPoint) {
			harePosition = hareForward(harePosition);
			harePos = new Point(harePosition, yPosition(harePosition));
		}
		else if (harePosition == squares && !hHasCheckPoint) {
			harePosition = hareBackward(harePosition);
			harePos = new Point(harePosition, yPosition(harePosition));
			hHasCheckPoint = true;
		}
		else if (harePosition > start && hHasCheckPoint) {
			harePosition = hareBackward(harePosition);
			harePos = new Point(harePosition, yPosition(harePosition));
		}
		else if (harePosition == start && hHasCheckPoint) {
			hareLap ++;
			harePosition = hareForward(harePosition);
			harePos = new Point(harePosition, yPosition(harePosition));
			hHasCheckPoint = false;
		}
		
		// Printing the location of tortoise and hare
		print(tortoisePosition, harePosition);
		
		// if either hare or the tortoise has finished 2 laps, end the race and return the result
		if (tortoiseLap == 2 || hareLap == 2) {
			raceEnd = true;
			// if tortoise won
			if (tortoiseLap == 2) {
				System.out.println("TORTOISE WINS!!! YAY!!!");
			}
			// if hare won
			else if (hareLap == 2) {
				System.out.println("HARE WINS! YUCH!");
			}
			// if both tortoise and hare won
			else if (tortoiseLap == 2 && hareLap == 2) {
				System.out.println("IT'S A TIE");
			}
		}
	}
	
	
	// Calculating yPosition of tortoise and hare
	public int yPosition (int x) {
		int y = 700;
		if (x >= 1 && x <160) {
			y = 700 - (int)((350/160) * x);
		}
		else if (x >= 160 && x < 320) {
			y = 700 - (int)(700 - ((350/160) * x));
		}
		else if (x >= 320 && x < 480) {
			y = 700 - (int)(((350/160) * x) - 700);
		}
		else {
			y = 700 - (int)(1400 - ((350/160) * x));
		}
		
		return y;
	}
	
	
	// Possible movements of tortoise when moving forward
	public int tortoiseForward (int x) {
		
		int possibilities = (int) Math.random() * 10 + 1;
		if (possibilities <= 5) {
			x += 3;
		}
		else if (possibilities <= 7) {
			x -= 6; 
		}
		else x += 1;
		
		return x;
	}
	
	// Possible movements of tortoise when moving backward
	public int tortoiseBackward (int x) {
		
		int possibilities = (int) Math.random() * 10 + 1;
		if (possibilities <= 5) {
			x -= 3;
		}
		else if (possibilities <= 7) {
			x += 6; 
		}
		else x -= 1;
		
		return x;
	}
	
	// Possible movements of hard when moving forward
	public int hareForward (int x) {
		
		int possibilities = (int) Math.random() * 10 + 1;
		if (possibilities <= 2) {
			;
		}
		else if (possibilities <= 3) {
			x += 8; 
		}
		else if (possibilities <= 4) {
			x -= 10; 
		}
		else if (possibilities <= 7) {
			x += 1; 
		}
		else x -= 2;
		
		return x;
	}
	
	// Possible movements of hard when moving backward
	public int hareBackward (int x) {
		
		int possibilities = (int) Math.random() * 10 + 1;
		if (possibilities <= 2) {
			;
		}
		else if (possibilities <= 3) {
			x -= 8; 
		}
		else if (possibilities <= 4) {
			x += 10; 
		}
		else if (possibilities <= 7) {
			x -= 1; 
		}
		else x += 2;
		
		return x;
	}
	
	
	// Printing all conditions
	public void print (int t, int h) {
		// if the position of the hare and the tortoise is same, print "Ouch" 
		if (h == t) {
			for (int i = 0; i < h; i++) {
				System.out.print(" ");
			}
			System.out.println("OUCH!!!");
		}
		
		else if (h < t) {
			for (int i = 0; i < h; i++) {
				System.out.print(" ");
			}
			System.out.print("H");
			
			for (int i = 0; i < (t-h); i++) {
				System.out.print(" ");
			}
			System.out.print("T");
		}
		
		else {
			for (int i = 0; i < t; i++) {
				System.out.print(" ");
			}
			System.out.print("T");
			
			for (int i = 0; i < (h-t); i++) {
				System.out.print(" ");
			}
			System.out.print("H");
		}
		System.out.println();
	}
}
