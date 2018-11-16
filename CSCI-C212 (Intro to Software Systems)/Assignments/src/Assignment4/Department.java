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

public class Department {
	
	private ArrayList<Course> course;
	private ArrayList<Student> student;
	private ArrayList<Instructor> instructor;
	
	
	// constructor
	public Department() {
		course = new ArrayList <Course>();
		student = new ArrayList <Student>();
		instructor = new ArrayList <Instructor>();
	}
	
	
	// search by a course number, and return the course information
	public void searchByCourseNumber (int courseNumber) {
		for (Course allCourse : course) {
			if (allCourse.getCourseNumber() == courseNumber) {
				System.out.println("Course Number: " + allCourse.getCourseNumber()); 
				System.out.println("Course Name: " + allCourse.getCourseName());
				System.out.println("Instructor Name: " + allCourse.getInstructorName());
				System.out.println("Number of Seats Available: " + allCourse.getNumberOfSeats());
				System.out.println("Course Location: " + allCourse.getCourseLocation());
			}
			else System.out.println("No result");
		}
	}
	
	// search by a course name, and return the course information
	public void searchByCourseName (String courseName) {
		for (Course allCourse : course) {
			if (allCourse.getCourseName().equals(courseName)) {
				System.out.println("Course Number: " + allCourse.getCourseNumber()); 
				System.out.println("Course Name: " + allCourse.getCourseName());
				System.out.println("Instructor Name: " + allCourse.getInstructorName());
				System.out.println("Number of Seats Available: " + allCourse.getNumberOfSeats());
				System.out.println("Course Location: " + allCourse.getCourseLocation());
			}
			else System.out.println("No result");
		}
	}
	
	// search by an instructor name, and return the course information
	public void searchByInstructorName (String professorName) {
		for (Course allCourse : course) {
			if (allCourse.getInstructorName().equals(professorName)) {
				System.out.println("Course Number: " + allCourse.getCourseNumber()); 
				System.out.println("Course Name: " + allCourse.getCourseName());
				System.out.println("Instructor Name: " + allCourse.getInstructorName());
				System.out.println("Number of Seats Available: " + allCourse.getNumberOfSeats());
				System.out.println("Course Location: " + allCourse.getCourseLocation());
			}
			else System.out.println("No result");
		}
	}
	
	// getter
	public void getCourse() {
		for (Course allCourse : course) {
			System.out.println(allCourse.getCourseName());
		}
	}
	
	// adding a new course
	public void addCourse (Course course) {
		this.course.add(course);
	}
	
	// removing an existing course
	public void removeCourse (Course course) {
		this.course.remove(course);
	}
	
	
	// getter
	public void getStudent() {
		for (Student allStudent : student) {
			System.out.println(allStudent.getID() + allStudent.getName());
		}
	}
	
	// adding a new student
	public void addStudent (Student student) {
		this.student.add(student);
	}
	
	// removing an existing student
	public void removeStudent (Student student) {
		this.student.remove(student);
	}
	
	
	// getter
	public void getInstructor() {
		for (Instructor allInstructor : instructor) {
			System.out.println(allInstructor.getID() + allInstructor.getName());
		}
	}
	
	// adding a new instructor
	public void addInstructor (Instructor instructor) {
		this.instructor.add(instructor);
	}
	
	// removing an existing instructor
	public void removeInstructor (Instructor instructor) {
		this.instructor.remove(instructor);
	}

}
