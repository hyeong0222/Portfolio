package Lab3;
//  Lab 3 
//
//  Name:  Sang Hyeong Woo 
//  Username: sangwoo
//  Last Edited:  1/28/18
//
//
//               
//               
//////////////////////////////////////////////////////////////////////////////////

import java.util.Scanner;

public class Lab3Exercises {
	
	// Takes a string to check if it is a palidrome, and return true or false 
	public static boolean palidrome(String myPString) {
		
		int position = 0;
		String reverseString = "";
		
		// Produces a reverse string of myPString using .charAt method 
		while (position < myPString.length()) {
			reverseString = myPString.charAt(position) + reverseString;
			position++;
		}
		
		// Checks if myPString and reverseString are identical, and returns a boolean
		return myPString.equals(reverseString);
		
	}
	
	
	public static String numbers() {
		
		Scanner input = new Scanner(System.in);
				
		int maximum = 0;
		int minimum = 10;
		int total = 0;  
		
		String numberArray = "";
	
		// If an input is an integer, and if the integer is not a duplicate, the integer gets stored in the string 'numberArray'.
		// Using the integers within the string, the maximum, minimum and the total values are calculated.
		while (input.hasNextInt()) {
			int newNumber = input.nextInt();
			if (numberArray.indexOf(newNumber + "") == -1) {
				numberArray += newNumber;
				if (maximum < newNumber) {
					maximum = newNumber;
				}
				if (minimum > newNumber) {
					minimum = newNumber;
				}
				total += newNumber;
			}
		}
		
		// Returns a string with the total, minimum, and the maximum values within the string.
		return ("SUM: " + total + ", MIN: " + minimum + ", MAX: " + maximum);
	}
		
		
	
	public static String grade() {
		Scanner input = new Scanner(System.in);
		
		double grade = input.nextDouble();
		
		// Takes a double value, and decides if the input value falls within certain letter grade boundaries.
		if (grade > 92.1) {
			return "A";
		}
		else if (grade > 82.1) {
			return "B";
		}
		else if (grade > 72.1) {
			return "C";
		}
		else if (grade > 62.1) {
			return "D";
		}
		else return "F";
				
	}
	
	public static String intToBinary(int n) {
		
		String binary = "";
		
		// Takes an integer and convert it into binary by dividing it by 2 until the value becomes 1. 
		// Each time a division is done, if the remainder is 1 then '1' gets added to the string.
		// If the remainder is 0 then '0' gets added.
		while (n > 1) {
			if (n == 1) { 
				binary += "1";
			}
			else if (n >= 2) {
				if (n % 2 == 1) {
					n = n / 2;
					binary += "1 ";
				}
				else {
					n = n / 2;
					binary +=  "0 ";
				}
			}
		}
		binary += "1";
		
		// Reverse the string 'binary' and store it into a new string 'reverse'.
		String reverse = new StringBuilder(binary).reverse().toString();
		return reverse;
	}
	
	
	public static void main(String [] args) {
		System.out.println(palidrome("racecar"));
		System.out.println(numbers());
		System.out.println("Your grade is a " + grade());
		System.out.println(intToBinary(8));
	}
}

//	i. Give the type and value for each of the following expressions:
//		1. (1 + 2.236)/2
//			Double, 1.618
//		2. 1+2+3+4.0
//			Double, 10.0
//		3. 4.1 >= 40
//			Boolean, False
//		4. 1+2+“3”
//			Compile-error, No Result
//	
//	ii. Give the value printed by each of the following code fragments:
//		1. The following code in 1 is using Newton’s Method to calculate the square root of a number
//			double t = 9.0;
//			while (Math.abs(t – 9.0/t) > .001) {
//				t = (9.0/t + t) / 2.0; }
//			System.out.println(t);
//
//			sum = 3.00009155413138
//
//		2. int sum=0;
//			for (int i = 1; i < 1000; i++) {
//				for (int j = 0; j < 1000; j++) { sum++;
//				} 
//			}
//			System.out.println(sum);
//
//			sum = 999000
//
//		3. int sum=0;
//			for (int i = 1; i < 1000; i *= 2) {
//				for (int j = 0; j < 1000; j++) { sum++;
//				} 
//			}
//			System.out.println(sum);
//
//			sum = 10000
