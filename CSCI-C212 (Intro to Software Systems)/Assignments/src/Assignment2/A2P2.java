////////////////////////////////////////////////////////////////////////////////////
//
//  Assignment 2 Part 2
//
//  Author  Sang Hyeong Woo 
//  Username   sangwoo
//  Last Edited:  Mar 27th
//
//
//////////////////////////////////////////////////////////////////////////////////

package Assignment2;

import java.util.Scanner;

public class A2P2 {
	private double checkingBalance = 0;
	private double savingsBalance = 0;
	private double investmentBalance = 0;
	private double amount = 0;	
	
	// Main menu to be described after a user inserts the balance and his/her desired account.
	public void menu () {
		System.out.print("Please enter the desired transaction (1. Show Account type and balance, 2. Change Account type, 3. Withdrawal, 4. Deposit, 5. Exit): ");
	}
	
	
	// This method would subtract the input amount of money from the specified type of account.
	public void withdrawal (int type) {
		System.out.print("Enter the amount to withdraw: ");
		Scanner input = new Scanner (System.in);
		amount = input.nextDouble();
		
		if(type == 1) {
			if (amount >= checkingBalance) {
				System.out.println("Over amount");
			}
			else {
				checkingBalance -= amount;
				System.out.println("Your Checking Account has " + checkingBalance);
			}
		}
		else if(type == 2) {
			if(amount >= savingsBalance) {
				System.out.println("Over amount");
			}
			else {
				savingsBalance -= amount;
				System.out.println("Your Savings Account has " + savingsBalance);
			}
		}
		else {
			if(amount > investmentBalance) {
				System.out.println("Over amount");
			}else {
				investmentBalance -= amount;
				System.out.println("Your Investment Account has " + investmentBalance);
			}
		}
	}
	
	
	// This method would add the input amount of money from the specified type of account.
	public void deposit (int type) {
		System.out.print("Enter the amount to deposit: ");
		Scanner in = new Scanner (System.in);
		amount = in.nextDouble();
		
		if(type == 1) {
			checkingBalance += amount;
			System.out.println("Your Checking Account has " + checkingBalance);
		}
		else if(type == 2) {
			savingsBalance += amount;
			System.out.println("Your Savings Account has " + savingsBalance);
		}
		else {
			investmentBalance += amount;
			System.out.println("Your Investment Account has " + investmentBalance);
		}
	}
	
	
	// This method would terminate the java run.
	public void exit () {
		System.exit(0);
	}
	
	// Main testing function
	public static void main(String [] args) {
		A2P2 accountTransaction = new A2P2();
		Scanner in = new Scanner (System.in);
		System.out.print("Please enter the balance of your checking account: ");
		double checkingBalance = in.nextDouble();
		System.out.print("Please enter the balance of your savings account: ");
		double savingsBalance = in.nextDouble();
		System.out.print("Please enter the balance of your investment account: ");
		double investmentBalance = in.nextDouble();
		
		accountTransaction.checkingBalance += checkingBalance;
		accountTransaction.savingsBalance += savingsBalance;
		accountTransaction.investmentBalance += investmentBalance;

		
		// To check if the user balance input is less than 0
		if (checkingBalance < 0) {
			System.out.println("Your input balance for checking account is an invalid number.");
			System.exit(0);
		}
		if (savingsBalance < 0) {
			System.out.println("Your input balance for savings account is an invalid number.");
			System.exit(0);
		}
		if (investmentBalance < 0) {
			System.out.println("Your input balance for investment account is an invalid number.");
			System.exit(0);
		}
		
		// To select the desired account for user's transaction.
		System.out.print("Please choose the type of account (1. Checking, 2. Savings, 3. Investment): ");
		int type = in.nextInt();
		
		
		boolean t = true;
		while (t) {
			accountTransaction.menu();
			int transaction = 0;
			transaction = in.nextInt();
			if (transaction == 1) {
				if(type == 1) {
					System.out.println("Account Type: Checking" + "\nBalance: " + accountTransaction.checkingBalance);
				}
				if(type == 2) {
					System.out.println("Account Type: Checking, Balance: " + accountTransaction.savingsBalance);
				}
				if(type == 3) {
					System.out.println("Account Type: Checking, Balance: " + accountTransaction.investmentBalance);
				}
			}
			
			if (transaction == 2) {
				System.out.println("Which type of account you want to change to? (1. Checking, 2. Savings, 3. Investment):");
//				Scanner in2 = new Scanner (System.in);
				//use scanner
				type = 0;
				type += in.nextInt();
			}
			
			if (transaction == 3) {
				accountTransaction.withdrawal(type);
			}
			
			if (transaction == 4) {
				accountTransaction.deposit(type);
			}
			
			if (transaction == 5) {
				accountTransaction.exit();
			}
		}
	}

}
