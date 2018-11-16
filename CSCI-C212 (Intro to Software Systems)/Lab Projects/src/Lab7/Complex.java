////////////////////////////////////////////////////////////////////////////////////
//
//  Lab 7
//
//  Author  Sang Hyeong Woo 
//  Username   sangwoo
//  Last Edited:  Feb 27th
//
//
//////////////////////////////////////////////////////////////////////////////////

package Lab7;

// The Complex class would perform arithmetics using complex numbers.
public class Complex {
	
	private double r;
	private double i;
	private double rAngle;
	private double angle;
	
	
	public double getR() {
		return r;
	}

	
	public void setR(float r) {
		this.r = r;
	}

	
	public double getI() {
		return i;
	}

	
	public void setI(float i) {
		this.i = i;
	}
	
	
	// This constructor would take two double variables, and set each of their values into the global variables r and i.
	public Complex (double a, double b) {
		this.r = a;
		this.i = b;
	}
	
	
	// This constructor takes no arguments, and set the r and i values as 0. 
	public Complex() {
		r = 0;
		i = 0;
	}
	
	
	// 'conjugate' method returns a new Complex function with inverting the i value from positive to negative.
	public Complex conjugate () {
		return new Complex (r, -i);
	}
	
	
	// 'polar' method will convert the complex numbers to polar coordinates. 
	public Complex polar () {
		this.rAngle = Math.sqrt(Math.pow(r, 2) + Math.pow(i, 2));
		this.angle = Math.toDegrees(Math.atan2(i, r));
		return this;
	}
	
	
	// This method would add two Complex numbers
	public Complex add (Complex b) {
		double real = this.r + b.r;
		double imaginary = this.i + b.i;
		return new Complex(real, imaginary);
	}
	
	
	// This method would subtract two Complex numbers.
	public Complex subtract (Complex b) {
		double real = this.r - b.r;
		double imaginary = this.i - b.i;
		return new Complex(real, imaginary);
	}
	
	
	// This method would multiply two Complex numbers.
	public Complex multiply (Complex b) {
		double real = ((this.r * b.r) - (this.i * b.i));
		double imaginary = ((this.r * b.i) + (this.i * b.r));
		return new Complex(real, imaginary);
	}
	
	
	// This method would divide two Complex numbers.
	public Complex divide (Complex b) {
		double real = (((this.r * b.r) + (this.i * b.i)) / (Math.pow(b.r, 2) + Math.pow(b.i, 2)));
		double imaginary = (((this.i * b.r) - (this.r * b.i)) / (Math.pow(b.r, 2) + Math.pow(b.i, 2)));
		return new Complex(real, imaginary);
	}
	
	
	// This method would print the complex numbers in (realPart, imageinaryPart) and (amplitude, angle) format.
	public String toString () {
		return "(" + this.r + ", " + this.i + ") and (" +  this.rAngle + ", " + this.angle + ")";
	}
	
	
	// The main method would take two different complex numbers, and test each arithmetics defined above.
	public static void main (String [] args) {
		Complex a = new Complex (5.0, 6.0);
		Complex b = new Complex (-3.0, 4.0);
		
		System.out.println("a            = " + a.polar());
		System.out.println("b            = " + b.polar());
		System.out.println("Real a       = " + a.polar().r);
		System.out.println("Imaginary a  = " + a.polar().i);
		System.out.println("Add          = " + a.add(b).polar().toString());
		System.out.println("Subtract     = " + a.subtract(b).polar().toString());
		System.out.println("Multiply     = " + a.multiply(b).polar().toString());
		System.out.println("Divide       = " + a.divide(b).polar().toString());
		
	}
}
