package Lab4;
////////////////////////////////////////////////////////////////////////////////////
//
//  Lab 4 Part 1
//
//  Author  Sang Hyeong Woo 
//  Username   sangwoo
//  Last Edited:  Feb 2nd
//
//
//////////////////////////////////////////////////////////////////////////////////

import java.util.Scanner;

public class Lab4RomanNumbers {
	public static void main(String [] args) {
		

		Scanner input = new Scanner(System.in);
		
		int integer = input.nextInt();
		String roman = "";
		
		// If the input integer is greater or equal to 4000, it would return an error message
		if (integer > 3999) {
			System.out.println("Invalid Integer");
		}
		
		// If the given condition is true, it would decrease the input integer value 
		// and concatenate each corresponding Roman characters to the string 'Roman'.
		else {
			while (integer >= 1000) {
				roman += "M";
				integer -= 1000;
			}
			while (integer >= 900) {
				roman += "CM";
				integer -= 900;
			}
			while (integer >= 500) {
				roman += "D";
				integer -= 500;
			}
			while (integer >= 400) {
				roman += "CD";
				integer -= 400;
			}
			while (integer >= 100) {
				roman += "C";
				integer -= 100;
			}
			while (integer >= 90) {
				roman += "XC";
				integer -= 90;
			}
			while (integer >= 50) {
				roman += "L";
				integer -= 50;
			}
			while (integer >= 40) {
				roman += "XL";
				integer -= 40;
			}
			while (integer >= 10) {
				roman += "X";
				integer -= 10;
			}
			while (integer >= 9) {
				roman += "IX";
				integer -= 9;
			}
			while (integer >= 5) {
				roman += "V";
				integer -= 5;
			}
			while (integer >= 4) {
				roman += "IV";
				integer -= 4;
			}
			while (integer >= 1) {
				roman += "I";
				integer -= 1;
			}
			
			System.out.println(roman);
		}
	}
}
