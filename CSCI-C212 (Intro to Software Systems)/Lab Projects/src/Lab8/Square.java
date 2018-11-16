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

//Square class extends Rectangle class
public class Square extends Rectangle {

	private int length;
	
	
	// Square constructor with 5 arguments, containing shape color value and border color value
	public Square (Color fill, Color border, int x, int y, int length) {
		super(fill, border, x, y, length, length);
		this.length = length;
	}
	
	
	// Square constructor with 4 arguments, containing shape color value only
	public Square (Color fill, int x, int y, int length) {
		super (fill, x, y, length, length);
		this.length = length;
	}
	
	
	// Square constructor with no color arguments
	public Square (int x, int y, int length) {
		super(x, y, length, length);
		this.length = length;
	}
	
	
	// Drawing a square shape at given position with given size.
	// Set the border and the shape colors. 
	public void draw(Graphics g) {
		g.setColor(getBorderColor());
		g.drawRect(super.getX(), super.getY(), length, length);
		g.fillRect(super.getX(), super.getY(), length, length);
		g.setColor(getFillColor());

	}
}
