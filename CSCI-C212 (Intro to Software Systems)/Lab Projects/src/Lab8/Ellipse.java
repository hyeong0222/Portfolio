////////////////////////////////////////////////////////////////////////////////////
//
//  C212 Lab 8
//
//  Author  Sang Hyeong Woo 
//  Username   sangwoo
//  Last Edited:  Mar 6th
//
//
//////////////////////////////////////////////////////////////////////////////////

package Lab8;

import java.awt.Color;
import java.awt.Graphics;

// Ellipse class extends Shape class
public class Ellipse extends Shape {
	
	private int length;
	private int height;
	
	
	// Ellipse constructor with 6 arguments, containing shape color value and border color value
	public Ellipse (Color fill, Color border, int x, int y, int length, int height) {
		super(fill, border, x, y);
		this.length = length;
		this.height = height;
	}
	
	
	// Ellipse constructor with 5 arguments, containing shape color value only
	public Ellipse (Color fill, int x, int y, int length, int height) {
		super(fill, x, y);
		this.length = length;
		this.height = height;
	}
	
	
	// Ellipse constructor with no color arguments
	public Ellipse (int x, int y, int length, int height) {
		super(x, y);
		this.length = length;
		this.height = height;
	}

	
	// Calculates the area of an ellipse.
	public double getArea() {
		double area = (0.5 * length) * (0.5 * height) * Math.PI;
		return area;
	}

	
	// Calculates the perimeter of an ellipse.
	public double getPerimeter() {
		double perimeter = Math.PI * (length + height) * ((3 * Math.pow(length - height, 2) / (Math.pow(length + height, 2) * ((Math.sqrt(-3 * Math.pow(length - height, 2) / Math.pow(length + height, 2) + 4) + 10)) + 1)));
		return perimeter;
	}
	

	// Drawing an ellipse at given position with given size.
	// Set the border and the shape colors. 
	void draw(Graphics g) {
		g.setColor(getBorderColor());
		g.drawOval(super.getX(), super.getY(), length, height);
		g.fillOval(super.getX(), super.getY(), length, height);
		g.setColor(getFillColor());
	}
	
	
	// Returns the size and the perimeter of an ellipse in a string form.
	public String toString() {
		return "The area of the ellipse is: " + this.getArea() + "and the perimeter is : " + this.getPerimeter(); 
	}
	
	
	// Returns the boolean value after comparing an ellipse with another one. 
	public boolean equal(Ellipse x) {
		if ((this.length == x.length) && (this.height == x.height)) {
			return true;
		}
		else return false;
	}

}
