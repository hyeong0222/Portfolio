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

// Triangle class extends Shape class
public class Triangle extends Shape {

	private int x;
	private int y;
	private int length;
	private int height;
	
	
	// Triangle constructor with 6 arguments, containing shape color value and border color value
	public Triangle(Color fill, Color border, int x, int y, int length, int height) {
		super(fill, border, x, y);
		this.x = x;
		this.y = y;
		this.length = length;
		this.height = height;
	}
	
	
	// Triangle constructor with 5 arguments, containing shape color value only
	public Triangle(Color fill, int x, int y, int length, int height) {
		super(fill, x, y);
		this.x = x;
		this.y = y;
		this.length = length;
		this.height = height;
	}
	
	
	// Triangle constructor with no color arguments
	public Triangle (int x, int y, int length, int height) {
		super(x, y);
		this.x = x;
		this.y = y;
		this.length = length;
		this.height = height;
	}

	
	// Calculates the area of a triangle.
	public double getArea() {
		double area = length * height * 0.5;
		return area;
	}

	
	// Calculates the perimeter of a triangle.
	public double getPerimeter() {
		double perimeter = Math.sqrt(Math.pow(length, 2) + Math.pow(height, 2));
		return perimeter;
	}

	
	// Drawing a triangle at given position with given size.
	// Set the border and the shape colors. 
	void draw(Graphics g) {
		int[] xArrays = new int[3];
		xArrays[0] = x;
		xArrays[1] = x;
		xArrays[2] = x+length;
		
		int[] yArrays = new int[3];
		yArrays[0] = y;
		yArrays[1] = y+height;
		yArrays[2] = y;
		
		g.setColor(getBorderColor());
		g.drawPolygon(xArrays, yArrays, 3);
		g.fillPolygon(xArrays, yArrays, 3);
		g.setColor(getFillColor());
	}

	
	// Returns the size and the perimeter of a triangle in a string form.
	public String toString() {
		return "The area of the triangle is: " + this.getArea() + "and the triangle is : " + this.getPerimeter(); 
	}
		
		
	// Returns the boolean value after comparing a triangle with another one. 
	public boolean equal(Triangle x) {
		if ((this.length == x.length) && (this.height == x.height)) {
			return true;
		}
		else return false;
	}
}
