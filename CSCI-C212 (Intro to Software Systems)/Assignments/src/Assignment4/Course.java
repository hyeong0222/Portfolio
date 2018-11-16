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

public class Course {
	
	private ArrayList<Student> students;
	private int courseNumber;
	private String courseName;
	private Instructor instructor;
	private String courseLocation;
	private int numberOfSeats;
	
	
	// constructor
	public Course (int courseNumber, String courseName, String courseLocation, Instructor instructor, ArrayList<Student> students, int numberOfSeats) {
		this.courseNumber = courseNumber;
		this.courseName = courseName;
		this.instructor = instructor;
		this.courseLocation = courseLocation;
		this.students = students;
		this.numberOfSeats = numberOfSeats;
	}
	
	
	// getters
	public ArrayList<Student> getStudents () {
		return this.students;
	}
	
	public int getCourseNumber () {
		return this.courseNumber;
	}
	
	public String getCourseName () {
		return this.courseName;
	}
	
	public String getInstructorName () {
		return this.instructor.getName();
	}
	
	public String getCourseLocation () {
		return this.courseLocation;
	}
	
	public int getNumberOfSeats () {
		return this.numberOfSeats;
	}
	
}
