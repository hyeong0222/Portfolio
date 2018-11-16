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

// Goal - The goal of this program is to compare each character's integer value. This program would take a character, and return its corresponding integer value.

public class Lab2Integer {
	public static void main(String[] args) {
		
		// each line below would take a given character, and display its corresponding integer.
		System.out.printf("The character %c has the value %d\n", 'A', ( (int) 'A' ) );
		System.out.printf("The character %c has the value %d\n", 'B', ( (int) 'B' ) );
		System.out.printf("The character %c has the value %d\n", 'C', ( (int) 'C' ) );
		System.out.printf("The character %c has the value %d\n", 'a', ( (int) 'a' ) );
		System.out.printf("The character %c has the value %d\n", 'b', ( (int) 'b' ) );
		System.out.printf("The character %c has the value %d\n", 'c', ( (int) 'c' ) );
		System.out.printf("The character %c has the value %d\n", '0', ( (int) '0' ) );
		System.out.printf("The character %c has the value %d\n", '1', ( (int) '1' ) );
		System.out.printf("The character %c has the value %d\n", '2', ( (int) '2' ) );
		System.out.printf("The character %c has the value %d\n", '$', ( (int) '$' ) );
		System.out.printf("The character %c has the value %d\n", '*', ( (int) '*' ) );
		System.out.printf("The character %c has the value %d\n", '+', ( (int) '+' ) );
		System.out.printf("The character %c has the value %d\n", '/', ( (int) '/' ) );
		System.out.printf("The character %c has the value %d\n", ' ', ( (int) ' ' ) );
	}	
}

/*
 * The Java code functions perfectly, because the returned integer values match with the values in ASCII table.
*/