package Assignment1;
////////////////////////////////////////////////////////////////////////////////////
//
//  Assignment 1 Part 3
//
//  Author  Sang Hyeong Woo 
//  Username   sangwoo
//  Last Edited:  Feb 2nd
//
//
//////////////////////////////////////////////////////////////////////////////////

import java.util.Scanner;

public class PrintingSymbol {
	
	public static void main(String [] args) {
		
		int lineNumber = 0;
		int symbolNumber = 0;
		char symbol = ' ';
		String tab = "";
		Scanner input = new Scanner(System.in);
		
		// Takes an input integer value and store it in the variable lineNumber
		System.out.print("Enter Lines to display: ");
		lineNumber = input.nextInt();
		
		// Determine if the input value is an odd number, and if it is greater than 7
		if (lineNumber % 2 == 1) {
			if (lineNumber <= 7) {
				System.out.println("Please enter valid number of lines to print: ");
			}
		}
		else System.out.println("Please enter a valid number of lines to print: ");
		
		
		//Takes an input integer value and store it in the variable symbolNumber
		System.out.print("Enter the number of Symbols per Line: ");
		symbolNumber = input.nextInt();
		
		// Determine if the input value is between 25 and 50
		if (symbolNumber < 25 || symbolNumber > 50) {
			System.out.println("Please enter a valid number of symbols to print: ");
		}
		
		
		//Takes an input character and store it in the variable symbol
		System.out.print("Enter the Symbol to print: ");
		symbol = input.next().charAt(0);
		
		// Determine if the ASCII value of the input character is between 33 and 47
		if ((int) symbol < 33 || (int) symbol > 47) {
			System.out.println("Please enter a valid symbol to print: ");
		}
		
		
		// Generate a string that consists the input symbol for a given times, and store the string in variable symbols
		StringBuffer symbols = new StringBuffer("");
		while (symbolNumber != 0) {
			symbols.insert(symbols.length(), symbol).toString();
			symbolNumber--;
		}
				
	
		System.out.println();
		
		
		// Print the generated strings in separate lines for the given times, each time shifting the string to the right
		// by the tab amount
		System.out.println(symbols);
		while (lineNumber != 1) {
			if (lineNumber !=0) {
				tab += "      ";
				System.out.println(tab + symbols);
			}
			lineNumber--;
		}
	}
}
