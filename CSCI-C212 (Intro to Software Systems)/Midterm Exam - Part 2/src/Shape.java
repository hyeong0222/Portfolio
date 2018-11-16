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
import java.awt.Point;


abstract class Shape {
	
	private Color fillColor; 
	private Color borderColor; 
//	private Boolean isFilled;
	private int[] xPoints;
	private int[] yPoints;
	private int points;
	private int posIncrement;
	private int directionChange;
	

	// the three constructors initialize the instance fields 
	public Shape (Color fillColor, Color borderColor, int[] xPoints, int[] yPoints, int points) {
		this.fillColor = fillColor;
		this.borderColor = borderColor;
		this.xPoints = xPoints;
		this.yPoints = yPoints;
		this.points = points;
		this.posIncrement = 1;
		this.directionChange = -1;
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
	
	// this method will be used to find the largest x and y coordinates of a hexagon shape
	public int getLargest (int[] array) {
		int max = array[0];
		for (int i = 1; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		return max;
	}
	
	// this method will be used to find the smallest x and y coordinates of a hexagon shape
	public int getSmallest (int[] array) {
		int min = array[0];
		for (int i = 1; i < array.length; i++) {
			if (array[i] < min) {
				min = array[i];
			}
		}
		return min;
	}
	
	// this method will move the location of a hexagon shape.
	public void moveLocation() {
		for (int i = 0; i < xPoints.length; i++) {
			this.xPoints[i] += this.posIncrement;
			this.yPoints[i] += this.posIncrement;
		}

	}
	
//	abstract double getArea();
//	abstract double getPerimeter(); 
	abstract void draw(Graphics g);
	
}