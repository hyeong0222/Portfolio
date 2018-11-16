////////////////////////////////////////////////////////////////////////////////////
//
//H212 Spring 18
//Assignment 3 Part 1
//
//
//
//Author  Sang Hyeong Woo - sangwoo
//Last Edited:  March 2nd, 2018
//
//////////////////////////////////////////////////////////////////////////////////

package Assignment3;

import java.awt.Point;

// Create  a class called Robot, which would move the position on an infinite plane.
public class Robot {
	private String direction = "North";
	private double xPosition;
	private double yPosition;
	private Point current;
	
	
	// turnLeft method would change the direction without changing the location. 
	public void turnLeft() {
		if (this.direction.equals("North")) {
			this.direction = "West";
		}
		else if (this.direction.equals("East")) {
			this.direction = "North";
		}
		else if (this.direction.equals("South")) {
			this.direction = "East";
		}
		else if (this.direction.equals("West")) {
			this.direction = "South";
		}
	}
	
	
	// turnRight method would change the direction without changing the location.
	public void turnRight() {
		if (this.direction.equals("North")) {
			this.direction = "East";
		}
		else if (this.direction.equals("East")) {
			this.direction = "South";
		}
		else if (this.direction.equals("South")) {
			this.direction = "West";
		}
		else if (this.direction.equals("West")) {
			this.direction = "North";
		}
	}
	
	
	// move method would change the position to the direction the robot is facing, by on unit.
	public void move() {
		if (this.direction.equals("North")) {
			this.yPosition++;
		}
		else if (this.direction.equals("East")) {
			this.xPosition++;
		}
		else if (this.direction.equals("South")) {
			this.yPosition--;
		}
		else if (this.direction.equals("West")) {
			this.xPosition--;
		}
	}
	
	
	// move method that moves the robot to newPoint position.
	public void move(Point newPoint) {
		double tempX = newPoint.getX();
		double tempY = newPoint.getY();
		this.current = new Point((int)tempX, (int)tempY);
		
	}
	
	
	// returns the current location
	public Point getLocation() {
		return current;
	}
	
	
	// returns the direction  
	public String getDirection() {
		return direction;
	}
	
	
	// returns the current direction and the current location in a string form.
	public String toString() {
		return getDirection() + ", " + getLocation();
	}
	
	
	public static void main(String [] args) {
		Point next = new Point(0, 0);
		Robot test = new Robot();
		
		// The robot would start at Point (0, 0), and travel in the shape of 8.
		test.move(next);
		test.move();
		test.move();
		test.turnRight();
		test.move(new Point (2,2));
		test.turnRight();
		test.move(new Point (2,0));
		test.turnRight();
		test.move(next);
		test.turnLeft();
		test.move();
		test.move();
		test.turnLeft();
		test.move(new Point (2, -2));
		test.turnLeft();
		test.move(new Point (2, 0));
		test.turnLeft();
		test.move(next);
		
		System.out.println(test.toString());
	}
}
