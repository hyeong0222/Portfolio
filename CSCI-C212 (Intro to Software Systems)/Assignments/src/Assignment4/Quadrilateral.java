////////////////////////////////////////////////////////////////////////////////////
//
//  Assignment 4 Part 1
//
//  Author  Sang Hyeong Woo 
//  Username   sangwoo
//  Last Edited:  March 23rd
//
//
//////////////////////////////////////////////////////////////////////////////////

package Assignment4;

import java.awt.Point;

// A class with its instance variables
public class Quadrilateral {

	private Point point1;
	private Point point2;
	private Point point3;
	private Point point4;
	private Double side12;
	private Double side23;
	private Double side34;
	private Double side41;
	
	// A constructor instantiating the class instance variables.
	public Quadrilateral(Point p1, Point p2, Point p3, Point p4) {
		this.point1 = p1;
		this.point2 = p2;
		this.point3 = p3;
		this.point4 = p4;
		this.side12 = getDistance(p1,p2);
		this.side23 = getDistance(p2,p3);
		this.side34 = getDistance(p3,p4);
		this.side41 = getDistance(p4,p1);
	}
	
	// calculates the distance between two given points
	public double getDistance(Point point1, Point point2) {
		double distance = Math.sqrt(Math.pow(point2.getX() - point1.getX(), 2) + Math.pow(point2.getY() - point1.getY(), 2));
		return distance;
	}
	
	// calculates the perimeter of a quadrilateral
	public double getPerimeter() {
		double perimeter = side12 + side23 + side34 + side41;
		return perimeter;
	}
	
	// calculates the area of a quadrilateral
	public double getArea() {
		double diag = getDistance(point1, point3);
		double semi1 = (side12 + side23 + diag) / 2;
		double semi2 = (side34 + side41 + diag) / 2;
		double area = Math.sqrt(semi1 * (semi1 - side12) * (semi1 - side23) * (semi1 - diag)) + Math.sqrt(semi2 * (semi2 - side34) * (semi2 - side41) * (semi2 - diag));
		return area;
	}
}
