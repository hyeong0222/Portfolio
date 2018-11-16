////////////////////////////////////////////////////////////////////////////////////
//
//  C212 Lab 9
//
//  Author  Sang Hyeong Woo 
//  Username   sangwoo
//  Last Edited:  Mar 20th
//
//
//////////////////////////////////////////////////////////////////////////////////

// we have not covered awt graphcis yet, but you do not need to know much for this lab // we will be covering that next week
package Lab9;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


abstract class Shape {
	
	private Color fillColor; 
	private Color borderColor; 
//	private Boolean isFilled; 
	private Point location;
	private int dx;
	private int dy;

	// the three constructors initialize the instance fields 
	public Shape (Color fillColor, Color borderColor, int x, int y) {
		this.fillColor = fillColor;
		this.borderColor = borderColor;
		this.location = new Point(x, y);
		this.dx = 1;
		this.dy = 1;
	}
	
	// set borderColor to Black since not provided.
	public Shape(Color fillColor, int x, int y) { 
		this(fillColor, Color.BLACK, x, y);
		this.dx = 1;
		this.dy = 1;
	}
	
	// set fillColor to white and border color to black.
	public Shape(int x, int y) { 
		this(Color.white, x, y);
		this.dx = 1;
		this.dy = 1;
	}

	// set the value of the instance variable fillColor as Color c.
	public void setFillColor(Color c) { 
		this.fillColor = c;
	} 
	
	// get the value of the instance variable fillColor.
	public Color getFillColor() { 
		return fillColor;
	}
	
	// set the value of the instance variable borderColor as Color c.
	public void setBorderColor(Color c) { 
		this.borderColor = c;
	}
	
	// get the value of the instance variable borderColor.
	public Color getBorderColor() {
		return borderColor;
	}
	
	// set the value of the instance variable location as Point pt.
	public void setLocation(Point pt) { 
		this.location = pt;
	} 
	
	// get the value of the instance variable location.
	public Point getLocation() {
		return location;
	}

	// Note: subclasses of Shape do not inherent private members so we need methods the subclasses 
	// can use to get the x and y values from the private Point instance field
	
	// returns the x coordinate of the location
	public int getX() { 
		int tempX = (int)this.location.getX();
		return tempX;
	}
	
	// sets the x coordinate of the location
	public void setX(int x) { 
		int tempY = (int)this.location.getY();
		location.setLocation(x, tempY);
	}
	
	// returns the y coordinate of the location
	public int getY() {
		int tempY = (int)this.location.getY();
		return tempY;
	} 
	
	// sets the y coordinate of the location
	public void setY(int y) { 
		int tempX = (int)this.location.getX();
		location.setLocation(tempX, y);
		
	}
	
	public int getDx() {
		return dx;
	}
	
	public void setDx(int dx) {
		this.dx = dx;
	}
	
	public int getDy() {
		return dy;
	}
	
	public void setDy(int dy) {
		this.dy = dy;
	}
	
	public void changeDx() {
		this.dx *= -1;
	}
	
	public void changeDy() {
		this.dy *= -1;
	}

	// if fillColor is white returns true, else returns false 
	public boolean isFilled() { 
		return (fillColor.equals(Color.white));
	}
	
	// moves location by dx and dy
//	public void moveLocation(int dx, int dy) { 
//		location.move(dx, dy);
//	}
	
	public void moveLocation() {
		this.location.x += this.dx;
		this.location.y += this.dy;
	}
	
	abstract double getArea();
	abstract double getPerimeter(); 
	abstract void draw(Graphics g);
	
}