////////////////////////////////////////////////////////////////////////////////////
//
//  Assignment 2 Part 3-2
//
//  Author  Sang Hyeong Woo 
//  Username   sangwoo
//  Last Edited:  Feb 16th
//
//
//////////////////////////////////////////////////////////////////////////////////

package Assignment2;

import java.util.Random;

public class Die {
	private Random rand;
	
	
	public Die() {
		rand = new Random();
	}
	
	// return a random number between 0 and 5.
	public int roll() {
		int randomRoll = rand.nextInt(5);
		return randomRoll;
	}

}
