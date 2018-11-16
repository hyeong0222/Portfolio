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
import java.util.ArrayList;
import java.util.Scanner;

// Main Tester class with instance variables
public class Tester {
	Quadrilateral quadrilateral;
	Trapezoid trapezoid;
	Parallelogram parallelogram;
	Rectangle rectangle;
	Square square;	
	
	// A constructor instantiating given instance variables
	public Tester (Quadrilateral quadrilateral, Trapezoid trapezoid, Parallelogram parallelogram, Rectangle rectangle, Square square) {
		this.quadrilateral = quadrilateral;
		this.trapezoid = trapezoid;
		this.parallelogram = parallelogram;
		this.rectangle = rectangle;
		this.square = square;
	}
	
	
	public static void main(String[] args) {
		
		// Setting points for each shapes.
		Quadrilateral quadrilateral = new Quadrilateral (new Point(1,0), new Point(4,4), new Point(5,4), new Point(6,2));
		Trapezoid trapezoid = new Trapezoid (new Point(1,0), new Point(4,0), new Point(5,4), new Point(0,4));
		Parallelogram parallelogram = new Parallelogram (new Point(1,0), new Point(4,0), new Point(5,5), new Point(2,5));
		Rectangle rectangle = new Rectangle (new Point(1,0), new Point(4,0), new Point(4,5), new Point(1,5));
		Square square = new Square (new Point(1,0), new Point(4,0), new Point(4,3), new Point (1,3));
		
		Tester test = new Tester(quadrilateral, trapezoid, parallelogram, rectangle, square);
		
		// Asks a user to choose a desired shape, and return its area and perimeters.
		Scanner in = new Scanner(System.in);
		System.out.print("Please choose the shape (1.Quadrilateral, 2.Trapezoid, 3.Parallelogram, 4.Rectangle, 5.Square): ");
		int input = in.nextInt();
		if (input > 0 && input < 6) {
			if (input == 1) {
				System.out.println("The area of the quadrilateral is: " + test.quadrilateral.getArea());
				System.out.println("The perimeter of the quadrilateral is: " + test.quadrilateral.getPerimeter());
			}
			if (input == 2) {
				System.out.println("The area of the trapezoid is: " + test.trapezoid.getArea());
				System.out.println("The perimeter of the trapezoid is: " + test.trapezoid.getPerimeter());
			}
			if (input == 3) {
				System.out.println("The area of the parallelogram is: " + test.parallelogram.getArea());
				System.out.println("The perimeter of the parallelogram is: " + test.parallelogram.getPerimeter());
			}
			if (input == 4) {
				System.out.println("The area of the rectangle is: " + test.rectangle.getArea());
				System.out.println("The perimeter of the rectangle is: " + test.rectangle.getPerimeter());
			}
			if (input == 5) {
				System.out.println("The area of the square is: " + test.square.getArea());
				System.out.println("The perimeter of the square is: " + test.square.getPerimeter());
			}
		}
		else System.out.println("Invalid number");
	}

}
