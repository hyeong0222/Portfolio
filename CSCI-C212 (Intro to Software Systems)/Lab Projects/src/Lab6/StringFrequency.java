////////////////////////////////////////////////////////////////////////////////////
//
//  Lab 6
//
//  Author  Sang Hyeong Woo 
//  Username   sangwoo
//  Last Edited:  Feb 20th
//
//
//////////////////////////////////////////////////////////////////////////////////

package Lab6;

public class StringFrequency {
	
	private String string;
	private int count;
	private int length;
	
	// Takes a string and set it as the global variable 'string'.
	public StringFrequency(String string) {
		this.string = string;	
	}
	
	// return the variable 'string'
	public String getString() {
		return string;
	}
	
	// Set the global variable 'string'
	public void setString(String string) {
		this.string = string;
	}
	
	// return the variable 'count'.
	public int getCount() {
		return count;
	}
	
	// Set the global variable 'count'
	public void setCount(int count) {
		this.count = count;
	}
	
	// return the variable 'length'.
	public int getLength() {
		return length;
	}
	
	// Set the global variable 'length'
	public void setLength(int length) {
		this.length = length;
	}
	
	// Increment the count variable by 1.
	public void increment() {
		count++;
	}
}
