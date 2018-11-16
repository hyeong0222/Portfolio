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

// Rectangle class extends Shape class
public class Rectangle extends Shape {
	
	private int length;
	private int height;
	
	
	// Rectangle constructor with 6 arguments, containing shape color value and border color value
	public Rectangle (Color fill, Color border, int x, int y, int length, int height) {
		super(fill, border, x, y);
		this.length = length;
		this.height = height;
	}
	
	
	// Rectangle constructor with 5 arguments, containing shape color value only
	public Rectangle (Color fill, int x, int y, int length, int height) {
		super (fill, x, y);
		this.length = length;
		this.height = height;
	}
	
	
	// Rectangle constructor with no color arguments
	public Rectangle (int x, int y, int length, int height) {
		super(x, y);
		this.length = length;
		this.height = height;
	}

	
	// Calculates the area of a rectangle shape.
	public double getArea() {
		double area = length * height;
		return area;
	}

	
	// Calculates the perimeter of a rectangle shape.
	public double getPerimeter() {
		double perimeter = (length * 2) + (height * 2);
		return perimeter;
	}

	
	// Drawing a rectangle shape at given position with given size.
	// Set the border and the shape colors. 
	public void draw(Graphics g) {
		g.setColor(getBorderColor());
		g.drawRect(super.getX(), super.getY(), length, height);
		g.fillRect(super.getX(), super.getY(), length, height);
		g.setColor(getFillColor());
	}
	
	
	// Returns the size and the perimeter of a rectangle in a string form
	public String toString() {
		return "The area of the rectangle is: " + this.getArea() + "and the perimeter is : " + this.getPerimeter(); 
	}
	
	
	// Returns the boolean value after comparing a rectangle with another rectangle. 
	public boolean equal(Rectangle x) {
		if ((this.length == x.length) && (this.height == x.height)) {
			return true;
		}
		else return false;
	}

}
