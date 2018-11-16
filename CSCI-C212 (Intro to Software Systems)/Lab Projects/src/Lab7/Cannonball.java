////////////////////////////////////////////////////////////////////////////////////
//
//  Lab 7
//
//  Author  Sang Hyeong Woo 
//  Username   sangwoo
//  Last Edited:  Feb 27th
//
//
//////////////////////////////////////////////////////////////////////////////////

package Lab7;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JComponent;
import javax.swing.JFrame;


// Class called Cannonball that would display its trajectory when it is shoot in the air at certain angle and speed 
public class Cannonball {
	double xPosition;
	double yPosition;
	double xVelocity;
	double yVelocity;
	
	
	// This constructor would take a double variable x, and set the global variable xPosition as x value.
	// It would set the yPosition value as 0.
	public Cannonball (double x) {
		this.xPosition = x;
		yPosition = 0;
		
	}
	
	
	// This move method would take a double variable called deltaSec, and move the ball for deltaSec long to the next position.
	// The xPosition value would be incremented by the xVelocity * deltaSec value.
	// The yPosition value would be updated by taking the gravitational acceleration value into account.
	// The yVelocity value would also be incremented by the gravitational acceleration * deltaSec value.
	public void move(double deltaSec) {
		this.xPosition += xVelocity * deltaSec;
		this.yPosition = -9.81 * 0.5 * Math.pow(deltaSec, 2) + yVelocity * deltaSec + yPosition;
		this.yVelocity += -9.81 * deltaSec; 
	}
	
	
	// getLocation method would return the coordinate of current cannonball location.
	public Point getLocation () {
		return new Point ((int)xPosition, (int)yPosition);
	}
	
	
	// ArrayList 'shoot' would return an array of recorded current location of the cannonball. 
	public ArrayList<Point> shoot (double alpha, double v, double deltaSec){
		this.xVelocity = v * Math.cos(Math.toRadians(alpha));
		this.yVelocity = v * Math.sin(Math.toRadians(alpha));
		
		// This loop would move the change the current location of the cannonball, and add the location of it into the arraylist.
		ArrayList<Point> positions = new ArrayList<Point>();
		while (yPosition >= 0) {
			move(deltaSec);
			positions.add(getLocation());
		}
		return positions;
	}
	
	
	// The main method would take the starting angle and the initial velocity as inputs.
	public static void main (String [] args) {
		double angle = 0;
		double velocity = 0;
		Scanner in = new Scanner(System.in);
		
		System.out.print("Please enter the starting angle = ");
		angle = in.nextDouble();
		
		System.out.print("Please enter the initial velocity = ");
		velocity = in.nextDouble();		
		
		Cannonball h1 = new Cannonball(0); 
		ArrayList<Point> list = h1.shoot(angle, velocity, 0.1);
	
		
		// Sets a blank 1000 x 1000 frame with title "Cannonball" 
		JFrame frame = new JFrame();
		frame.setSize(1000, 1000);
		frame.setTitle("Cannonball");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println(list);
				
		// Fills the frame using imported 'JComponent' class
		JComponent diagram = new JComponent() {
			public void paintComponent(Graphics g) {
				
				// Recover Graphics2D
				Graphics2D g2 = (Graphics2D) g;
				
				// Draw a rectangle box with size of 200px x 200px, filled with the color Red
				for (int i = 0; i < list.size() - 1; i++) {
					Point before = list.get(i);
					Point after = list.get(i+1);
					g2.drawLine(before.x, 900-before.y, after.x, 900-after.y);
				}
			}
		};
		frame.add(diagram);
		frame.setVisible(true);
		
	}
	

}
