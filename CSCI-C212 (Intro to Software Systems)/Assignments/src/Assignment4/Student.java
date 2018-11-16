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

import java.util.ArrayList;

public class Student extends Person {
	
	private ArrayList<Course> course;
	
	
	// constructor
	public Student (String name, int id, ArrayList<Course> course) {
		super(name, id);
		this.course = course;
	}
	
	
	// setter
	public void setCourse(ArrayList<Course> course) {
		this.course = course;
	}
	
	
	// adding a new course
	public void addCourse (Course course) {
		this.course.add(course);
	}
	
	
	// dropping an existing course
	public void dropCourse (Course course) {
		this.course.remove(course);
	}

}
