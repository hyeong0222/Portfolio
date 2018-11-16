////////////////////////////////////////////////////////////////////////////////////
//
//  Assignment 4 Part 3
//
//  Author  Sang Hyeong Woo 
//  Username   sangwoo
//  Last Edited:  April 17th
//
//
//////////////////////////////////////////////////////////////////////////////////

package Assignment4;

public class Person {
	
	private String name;
	private int id;
	
	
	// constructor
	public Person (String name, int id) {
		this.name = name;
		this.id = id;
	}
	
	
	// setters
	public void setName (String name) {
		this.name = name;
	}
	
	
	public void setID (int id) {
		this.id = id;
	}
	
	
	// getters
	public String getName () {
		return name;
	}
	
	
	public int getID () {
		return id;
	}
	
	
	public String information () {
		return "Name: " + name + ", ID: " + id;
	}

}
