////////////////////////////////////////////////////////////////////////////////////
//
//  Assignment 2 Part 3-3
//
//  Author  Sang Hyeong Woo 
//  Username   sangwoo
//  Last Edited:  Feb 16th
//
//
//////////////////////////////////////////////////////////////////////////////////

package Assignment2;

import java.util.Scanner;

public class DieSimulation {
	private Die die;
	private Counter[] fiveCounters;
	
	
	// A new array with 5 slots called 'FiveCounters' is created. 
	public DieSimulation () {
		die = new Die();
		fiveCounters = new Counter[5];
		
		fiveCounters[0] = new Counter ("Zero");
		fiveCounters[1] = new Counter ("One");
		fiveCounters[2] = new Counter ("Two");
		fiveCounters[3] = new Counter ("Three");
		fiveCounters[4] = new Counter ("Four");
	}
	
	
	// This method would throw die for the user's input amount of times.
	public String run () {
		int input = 0;
		Scanner in = new Scanner(System.in);
		
		System.out.print("Please enter the number of times to roll the die: ");
		input = in.nextInt();
		if (input >= 200 && input <= 500) {
			for (int i = 1; i <= input; i++) {
				fiveCounters[die.roll()].increment();
			}
			
		}
		else System.out.println("You entered an invalid number.");
		
		return fiveCounters[0].toString() + " " + 
				fiveCounters[1].toString() + " " + 
				fiveCounters[2].toString() + " " +
				fiveCounters[3].toString() + " " +
				fiveCounters[4].toString();
	}
	
	
	public static void main(String[]args) {
		DieSimulation test = new DieSimulation();
		System.out.println(test.run());
		
	}
}
