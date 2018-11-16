////////////////////////////////////////////////////////////////////////////////////
//
//H212 Spring 18
//Assignment 3 Part 2
//
//
//
//Author  Sang Hyeong Woo - sangwoo
//Last Edited:  March 2nd, 2018
//
//////////////////////////////////////////////////////////////////////////////////
/*
* once all the bugs in the program are fixed the program should generate output similar to this:
* Student0 91
* Student8 83
* Student17 79
* Student3 76
* Student5 76
* Student12 75
* Student4 74
* Student7 72
* Student11 71
* Student6 62
* Student1 59
* Student2 54
* Student13 45
* Student18 41
* Student16 38
* Student9 36
* Student10 24
* Student15 18
* Student14 6
* Student19 1
*/

/*
 * ERROR LIST
 * 1. (Line 57) public Student(String _username){        -> public Student(String _username, int score){
 * 2. (Line 59)                                          -> this.score = score;
 * 3. (Line 69) while(switches > 0){                     -> while (switches <= students.length) {
 * 4. (Line 76)                                          -> Student temp = students[i];
 * 5. (Line 78) students[i+1] = students[i];             -> students[i+1] = temp;
 * 6. (Line 82) switches++;                              -> (Line 80) switches++;
 * 7. (Line 90)                                          -> (Line 89) students = new Student[10];
 * 8. (Line 96) int score = (int) (Math.random()*100);   -> int score = (int) (Math.random()*101);
 */

package Assignment3;
public class Student {

	public String username;
	public int score;

	public static Student[] students;

	//Call the constructor to create a new student object
	public Student(String _username, int score){
		username = _username;
		this.score = score;
	}

	//sort students by score in descending order
	public static void sort() {

		//Stores number of switches
		int switches = 1;

		//sort the array
		while (switches <= students.length) {

			for (int i = 0; i < students.length-1; i++) {
				//if score of ith student is less than score of i+1th student switch
				if (students[i].score < students[i+1].score){

					//swap students in array
					Student temp = students[i];
					students[i] = students[i+1];
					students[i+1] = temp;
					//increment switches
				}
			}
			switches++;
		}
	}

	//Allocates the student array with a username and a randomly generated score
	public static void allocateStudentArray() {

		String username = "Student";
		students = new Student[10];
		
		// for i less than length of student array
		for (int i = 0; i < students.length; i++) {

			//populate student array
			int score = (int) (Math.random()*101); //generate a random number between 0-100
			students[i] = new Student(username+i, score); //initialize with username


		} 
	}

	public static void main (String args[]) {

		allocateStudentArray ();
		sort ();

		//print output
		for (int i = 0; i < students.length; i++) {
			System.out.println(students[i].username + " "+ students[i].score);
		}

	}

}
