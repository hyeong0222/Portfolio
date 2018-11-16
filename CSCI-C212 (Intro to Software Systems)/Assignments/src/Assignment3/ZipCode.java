////////////////////////////////////////////////////////////////////////////////////
//
//H212 Spring 18
//Assignment 3 Part 3
//
//
//
//Author  Sang Hyeong Woo - sangwoo
//Last Edited:  March 2nd, 2018
//
//////////////////////////////////////////////////////////////////////////////////
package Assignment3;

import java.util.Scanner;

public class ZipCode {
	
	private String barcode;
	private String actualBarcode;
	private String[] digitValues;
	
	
	// Set the variable barcode as a global variable, and set digitValues to be an array with 6 slots.
	public ZipCode(String barcode) {
		this.barcode = barcode;
		barcodeActual();
		digitValues = new String[6];
		values();
	}
	
	
	// Remove the frame bars from the barcode string
	public void barcodeActual () {
		this.actualBarcode = barcode.substring(1, barcode.length()-1);
	}
	
	
	// Takes a zipcode and turn it into a barcode type.
	public static String toBarcode (int zipcode) {
		String barcode = "|";
		
		// Takes a zipcode and separate each number characters digit by digit.
		int firstDigit = zipcode / 10000;
		int secondDigit = (zipcode / 1000) % 10;
		int thirdDigit = (zipcode / 100) % 10;
		int fourthDigit = (zipcode / 10) % 10;
		int fifthDigit = zipcode % 10;
		int checkDigit = 10 - ((firstDigit + secondDigit + thirdDigit + fourthDigit + fifthDigit) % 10);
		
		String barcode1 = ":::||";
		String barcode2 = "::|:|";
		String barcode3 = "::||:";
		String barcode4 = ":|::|";
		String barcode5 = ":|:|:";
		String barcode6 = ":||::";
		String barcode7 = "|:::|";
		String barcode8 = "|::|:";
		String barcode9 = "|:|::";
		String barcode0 = "||:::";
		
		// Conditions to check each digits of a zipcode, and convert them into corresponding codes. 
		if (firstDigit == 1)
			barcode += barcode1;
		if (firstDigit == 2)
			barcode += barcode2;
		if (firstDigit == 3)
			barcode += barcode3;
		if (firstDigit == 4)
			barcode += barcode4;
		if (firstDigit == 5)
			barcode += barcode5;
		if (firstDigit == 6)
			barcode += barcode6;
		if (firstDigit == 7)
			barcode += barcode7;
		if (firstDigit == 8)
			barcode += barcode8;
		if (firstDigit == 9)
			barcode += barcode9;
		if (firstDigit == 0)
			barcode += barcode0;
		
		if (secondDigit == 1)
			barcode += barcode1;
		if (secondDigit == 2)
			barcode += barcode2;
		if (secondDigit == 3)
			barcode += barcode3;
		if (secondDigit == 4)
			barcode += barcode4;
		if (secondDigit == 5)
			barcode += barcode5;
		if (secondDigit == 6)
			barcode += barcode6;
		if (secondDigit == 7)
			barcode += barcode7;
		if (secondDigit == 8)
			barcode += barcode8;
		if (secondDigit == 9)
			barcode += barcode9;
		if (secondDigit == 0)
			barcode += barcode0;
		
		if (thirdDigit == 1)
			barcode += barcode1;
		if (thirdDigit == 2)
			barcode += barcode2;
		if (thirdDigit == 3)
			barcode += barcode3;
		if (thirdDigit == 4)
			barcode += barcode4;
		if (thirdDigit == 5)
			barcode += barcode5;
		if (thirdDigit == 6)
			barcode += barcode6;
		if (thirdDigit == 7)
			barcode += barcode7;
		if (thirdDigit == 8)
			barcode += barcode8;
		if (thirdDigit == 9)
			barcode += barcode9;
		if (thirdDigit == 0)
			barcode += barcode0;
		
		if (fourthDigit == 1)
			barcode += barcode1;
		if (fourthDigit == 2)
			barcode += barcode2;
		if (fourthDigit == 3)
			barcode += barcode3;
		if (fourthDigit == 4)
			barcode += barcode4;
		if (fourthDigit == 5)
			barcode += barcode5;
		if (fourthDigit == 6)
			barcode += barcode6;
		if (fourthDigit == 7)
			barcode += barcode7;
		if (fourthDigit == 8)
			barcode += barcode8;
		if (fourthDigit == 9)
			barcode += barcode9;
		if (fourthDigit == 0)
			barcode += barcode0;
		
		if (fifthDigit == 1)
			barcode += barcode1;
		if (fifthDigit == 2)
			barcode += barcode2;
		if (fifthDigit == 3)
			barcode += barcode3;
		if (fifthDigit == 4)
			barcode += barcode4;
		if (fifthDigit == 5)
			barcode += barcode5;
		if (fifthDigit == 6)
			barcode += barcode6;
		if (fifthDigit == 7)
			barcode += barcode7;
		if (fifthDigit == 8)
			barcode += barcode8;
		if (fifthDigit == 9)
			barcode += barcode9;
		if (fifthDigit == 0)
			barcode += barcode0;

		if (checkDigit == 1)
			barcode += barcode1;
		if (checkDigit == 2)
			barcode += barcode2;
		if (checkDigit == 3)
			barcode += barcode3;
		if (checkDigit == 4)
			barcode += barcode4;
		if (checkDigit == 5)
			barcode += barcode5;
		if (checkDigit == 6)
			barcode += barcode6;
		if (checkDigit == 7)
			barcode += barcode7;
		if (checkDigit == 8)
			barcode += barcode8;
		if (checkDigit == 9)
			barcode += barcode9;
		if (checkDigit == 0)
			barcode += barcode0;
		
		barcode += "|";
		
		return barcode;
	}


	// Takes a barcode, and change it into a number type zipcode.
	public void values () {
		for (int i = 0; i < 5; i++) {
			int digit = 0;
			
			// Takes the first 5 characters from the barcode, and calculate its corresponding numbers.
			for (int j = 0; j < 5; j++) {
				if (actualBarcode.substring(j, j+1).equals("|")) {
					// Multiplying by each weights
					if (j == 0)
						digit += 7;
					if (j == 1)
						digit += 4;
					if (j == 2)
						digit += 2;
					if (j == 3)
						digit += 1;
					if (j == 4)
						digit += 0;
				}
			}
			
			// If the total value is equal to 11, then return 0.
			if (digit == 11) {
				digitValues[i] = Integer.toString(0);
			}
			else digitValues[i] = Integer.toString(digit);
		
			actualBarcode = actualBarcode.substring(5);
		}
	}
	
	
	// Main method to test the ZipCode.
	public static void main(String [] args) {
		Scanner in1 = new Scanner (System.in);
		System.out.print("Please enter the zipcode = ");
		int zipcode = in1.nextInt();
		
		// Should return the barcode of given zipcode
		System.out.println(toBarcode(zipcode));
		
		
		
		Scanner in2 = new Scanner (System.in);
		
		System.out.print("Please enter the barcode = ");
		String input = in2.next();
		
		// Should return the zipcode of barcode.
		ZipCode bar = new ZipCode(input);
		System.out.println(bar.digitValues[0] + bar.digitValues[1] + bar.digitValues[2] + bar.digitValues[3] + bar.digitValues[4]);
		
		
	}
}