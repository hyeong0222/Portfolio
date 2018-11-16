////////////////////////////////////////////////////////////////////////////////////
//
//  Assignment 2 Part 3-1
//
//  Author  Sang Hyeong Woo 
//  Username   sangwoo
//  Last Edited:  Feb 16th
//
//
//////////////////////////////////////////////////////////////////////////////////

package Assignment2;

public class Counter {
	private final String name;
	private int count;
	
	// Takes a string 'id' and set it as the global variable 'name'.
	public Counter (String id) {
		name = id;
	}
	
	// increment the number of count
	public void increment () {
		count++;
	}
	
	// returns the number of count from increment method
	public int tally () {
		return count;
	}
	
	// Concatenate a string with name and count information.
	public String toString() {
		String result = "Name: " + name + " " + "Count: " + count + "\n";
		return result;
	}

}
