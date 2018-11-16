package Lab2;
//  Lab 2 
//  
//  Released:  1/11/18
//
//  @Author  Sang Hyeong Woo sangwoo
//  Last Edited:  1/19/18
//
//
//  Directions:  Implement the following methods 
//               
//               
//////////////////////////////////////////////////////////////////////////////////

// Note:  Do not need to explicitly import classes from java.lang but wanted to make it explicit 
// Goal - understand using Static classes - i.e. the Math class was not designed to be Instantiated (make objects) 
// It is a class that provides functionality and will be used to complete one of the methods
import java.lang.Math;

public class Lab2Template {
    
    // computes volume of a sphere when given its diameter
    public static double volumeOfSphere(int diameter) {
      //TODO
    	    	
    	// Takes a diameter and divide it by 2, then set the value as radius
    	double radius = diameter / 2;
    	// Calculates the volume of a sphere using JAVA default PI value and the radius
    	double volume = (4/3) * Math.PI * Math.pow(radius, 3);
    	
    	return volume;
    }


    // computes surface area of a cylinder with given diameter and height
    public static double surfaceAreaCylinder(double diameter, double height) {
      //TODO
    	
    	// Takes a diameter and divide it by 2, then set the value as radius
    	double radius = diameter / 2;
    	// Calculates the surface area of a cylinder using given radius, height values, and JAVA default PI value 
    	double area = (2 * Math.PI * radius * height) + (2 * Math.PI * Math.pow(radius, 2));
    	
    	return area;
    }
    
    // returns a String, in the following format "SquareArea: *, Perimeter: *, Diagonal: *" where * refers to square of 
    // the rectanglar area, perimeter, and the diagonal of the square respectively 
    // (use Pythagorean Theorem)
    public static String squareMeasurements(int len) {
      //TODO
    	
    	// Takes a length value and use it to calculate the area of the square
    	double area = Math.pow(len,2);
    	// Takes a length value and use it to calculate the perimeter of the square
    	double perimeter = len * 4;
    // Takes a length value and use Pythagorean theorem to calculate the diagonal length of the square 
    	double diagonal = Math.sqrt(Math.pow(len, 2) * 2);
    	
    	return ("SquareArea: " + area + ", Perimeter = " + perimeter + ", Diagonal : " + diagonal);
    
    }
    
    // reads a number between 1000 and 9999999 and prints it with commas (,) separating every three digits
    // for example: 12317910 will be printed as 12,317,910
    // hint use modulus (%) to save part of the number, then concatenate back to gether as a String
    public static String addCommas(int num) {
      //TODO
    	
    	// Takes a number and separates it with commas every three digits.
    // The specifier '%d' applies to integers.
    	String output = String.format("%,d", num);
    	
    	return output;
    }
    
    // given angle in Degrees, convert to radians (hint look at Math class Java doc API)
    // and return a string of the Sin, Cos, and Tangent of the angle  
    public static String trigFunctions(double angleInDegrees) {
    	double rad = Math.toRadians(angleInDegrees);

    	String output = ("Sin = " + Math.sin(rad) + ", Cos = " + Math.cos(rad) + ", Tan = " + Math.tan(rad));
    	
    	return output;
    }
    
 
    // test client 
    public static void main(String[] args) {
      //TODO: Modify to test all functions 
        System.out.println("The volume of a sphere is : " + volumeOfSphere(5));
        System.out.println("The surface area of a cylinder is : " + surfaceAreaCylinder(5, 5));
        System.out.println(squareMeasurements(5));
        System.out.println("The commas added to the number 123456789 would be : " + addCommas(1234567));
        System.out.println("The sin, cos, tan value of 45 degree angle is : " + trigFunctions(45));
    }

}

///////////////////////////////////
//                               //
// ANSER THE FOLLOWING QUESTIONS //
//                               //
///////////////////////////////////

/*
 * Questions 1-3 are on explicit and implicit casting of some numerical types  
 *
 * 1.  What happens if you multiply a double with a char? 
 * 		You would get a certain integer as an answer, because each character has a corresponding integer representation.
 * 
 * 2.  What happens if a method has a parameter of type int, but you pass it a char?
 *		The system would return the corresponding integer of the given character.
 *
 * 3.  What happens if a method has a parameter of type char, but you pass it an int? 
 *		The system would return the corresponding character of the given integer.
 * 
 * 4.  What are the 8 primitive data types in the Java language 
 * 		boolean, byte, char, short, int, long, float, double
 * 
 * 5.  Consider the following code snippet.
 *     int i = 10;
 *     n = ++(i++);
 *     System.out.println(++(i++)% 5) ;
 * 
 *     Without Compiling the Program:
 *     What {do you think} are the values of i and n if the code is executed?
 *     		I think the value of both i and n would be 12.
 *     
 *     What are the final value printed?
 *     		The final value would be 2, because the remainder of 12 divided by 5 is 2.
 *     
 *     Now Compile and Run the Program to check your answers. If they are different, explain why 
 *     and provide updated code!
 *     		It failed to compile the code, because the variable n does not have type, and pre-increment and post-increment cannot be used together.
 *     
 *     	int i = 10;
        int n = ++i;
        n = ++i; 
        System.out.println(n % 5) ;
 */
