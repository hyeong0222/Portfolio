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

// Circle class extends Ellipse class
public class Circle extends Ellipse {
	
	private int length;
	

	// Circle constructor with 6 arguments, containing shape color value and border color value
	public Circle (Color fill, Color border, int x, int y, int length) {
		super(fill, border, x, y, length, length);
		this.length = length;
	}
	
	
	// Circle constructor with 5 arguments, containing shape color value only
	public Circle (Color fill, int x, int y, int length) {
		super(fill, x, y, length, length);
		this.length = length;
	}
	
	
	// Circle constructor with no color arguments
	public Circle (int x, int y, int length) {
		super(x, y, length, length);
		this.length = length;
	}
	
	
	// Drawing a circle at given position with given size.
	// Set the border and the shape colors. 
	void draw(Graphics g) {
		g.setColor(getBorderColor());
		g.drawOval(super.getX(), super.getY(), length, length);
		g.fillOval(super.getX(), super.getY(), length, length);
		g.setColor(getFillColor());
	}
	
}
