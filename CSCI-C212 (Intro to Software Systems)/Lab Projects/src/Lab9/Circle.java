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

package Lab9;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

// Circle class extends Ellipse class
public class Circle extends Shape {
	
	private int length;
	

	// Circle constructor with 6 arguments, containing shape color value and border color value
	public Circle (Color fill, Color border, int x, int y, int length) {
		super(fill, border, x, y);
		this.length = length;
	}
	
	
	// Circle constructor with 5 arguments, containing shape color value only
	public Circle (Color fill, int x, int y, int length) {
		super(fill, x, y);
		this.length = length;
	}
	
	
	// Circle constructor with no color arguments
	public Circle (int x, int y, int length) {
		super(x, y);
		this.length = length;
	}
	
	
	public double getRadius() {
		double radius = length / 2;
		return radius;
	}
	
	public void setRadiusZero() {
		this.length = 0;
	}
	
	
	// Calculates the area of a circle
	public double getArea() {
		double area = Math.PI * Math.pow(0.5 * length, 2);
		return area;
	}

	
	// Calculates the perimeter of a circle.
	public double getPerimeter() {
		double perimeter = 2 * Math.PI * (0.5 * length);
		return perimeter;
	}
	
	public Point getCenter() {
		Point center = new Point (getX() + (length / 2) , getY() + (length / 2));
		return center;
	}
	
	
	// Drawing a circle at given position with given size.
	// Set the border and the shape colors. 
	void draw(Graphics g) {
		g.setColor(getBorderColor());
		g.drawOval(this.getX(), this.getY(), length, length);
		g.setColor(getFillColor());
		g.fillOval(this.getX(), this.getY(), length, length);
	}
	
	// Returns the size and the perimeter of an ellipse in a string form.
	public String toString() {
		return "The area of the circle is: " + this.getArea() + "and the perimeter is : " + this.getPerimeter(); 
	}
		
		
	// Returns the boolean value after comparing an ellipse with another one. 
	public boolean equal(Circle x) {
		if (this.length == x.length) {
			return true;
		}
		else return false;
	}
	
}
