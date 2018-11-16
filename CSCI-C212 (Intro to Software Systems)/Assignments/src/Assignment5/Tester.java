////////////////////////////////////////////////////////////////////////////////////
//
//  Assignment 5 Part 3
//
//  Author  Sang Hyeong Woo 
//  Username   sangwoo
//  Last Edited:  April 20th
//
//
//////////////////////////////////////////////////////////////////////////////////
package Assignment5;

import java.util.Scanner;

public class Tester {

	public static void main(String[] args) {
		Implementation test = new Implementation();
		
		boolean running = true;
		Scanner sc = new Scanner (System.in);

		do {	
			// Repeat the whole process until the user selects Quit
			System.out.println("Please enter the command you want to perform:");
			System.out.println("Buy stocks: 1, Sell stocks: 2, Quit: 3");
			int command = sc.nextInt();
					
			if (command == 1) {
				
				System.out.println("Enter the symbol:");
				String symbol = sc.next();
				
				System.out.println("Enter the quantity of the stock you want to purchase: ");
				int quantity = sc.nextInt();
				
				System.out.println("Enter the price of the stock you want to purchase: ");
				double price = sc.nextDouble();
				
				test.buyStock(symbol, quantity, price);
			}
			
			else if (command == 2) {
				System.out.println("Enter the symbol: ");
				String symbol = sc.next();
				
				System.out.println("Enter the quantity of the stock you want to sell: ");
				int quantity = sc.nextInt();
				sc.close();		
				
				System.out.println("Enter the price of the stock you want to purchase: ");
				double price = sc.nextDouble();
				sc.close();	
				
				test.sellStock(symbol, quantity, price);
			}
			
			// Stops repeating when the user types 3.
			else if (command == 3) {
				running = false;
			}
		}
		while (running);	
	}
}
