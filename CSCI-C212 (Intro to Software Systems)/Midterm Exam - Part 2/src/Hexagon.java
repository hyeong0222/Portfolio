////////////////////////////////////////////////////////////////////////////////////
//
//  C212 Midterm Exam 2 - Part 2
//
//  Author  Sang Hyeong Woo 
//  Username   sangwoo
//  Last Edited:  Mar 22nd
//
//
//////////////////////////////////////////////////////////////////////////////////

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Hexagon extends Shape {

	private int[] xPoints;
	private int[] yPoints;
	private int points;
	
	// Circle constructor with 6 arguments, containing shape color value and border color value
	public Hexagon (Color fills, Color borders, int[] xPoints, int[] yPoints) {
		super(fills, borders, xPoints, yPoints, 6);
		this.xPoints = xPoints;
		this.yPoints = yPoints;
		this.points = 6;
	}
	
	void draw(Graphics g) {
		
		// Drawing a polygon shape with a random fillColor and a random borderColor
		g.setColor(getBorderColor());
		g.drawPolygon(xPoints, yPoints, points);
		g.setColor(getFillColor());
		g.fillPolygon(xPoints, yPoints, points);
	}
	
		
}
