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

public class Instructor extends Person {
	
	private String email;
	private String officeLocation;
	
	
	// Constructor
	public Instructor (String name, int id, String email, String officeLocation) {
		super(name, id);
		this.email = email;
		this.officeLocation = officeLocation;
	}
	
	
	// email setter
	public void setEmail (String email) {
		this.email = email;
	}
	
	
	// officeLoation setter
	public void setOfficeLocation (String officeLocation) {
		this.officeLocation = officeLocation;
	}
	
	
	// email getter
	public String getEmail () {
		return this.email;
	}
	
	// officeLocation getter
	public String getOfficeLocation () {
		return this.officeLocation;
	}
	
	
	public String professorInformation () {
		return information() + ", Email: " + email + ", Office Location: " + officeLocation;
	}
	

}
