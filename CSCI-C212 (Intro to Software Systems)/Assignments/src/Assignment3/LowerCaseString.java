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
 * Converts a string to lowercase,
 * and displays the string's length
 * as well as a count of letters
 */

/*The program should produce the following outupt once all the bugs are fixed.
 * Output:
 * In all lowercase, the sentence is: "debugging is fun!!!" 
 * The number of CHARACTERS in the string is: 19 
 * The number of LETTERS is: 14
 */


/*
 * ERROR LIST
 * 1. (Line 36) int stringLength = str.length;                                   -> int stringLength = str.length();
 * 2. (Line 39) for(int i = 0; i < length; i++) {                                -> for(int i = 0; i < stringLength; i++) {
 * 3. (Line 40) char ch = Character.toLowerCase(str[i]);                         -> char ch = Character.toLowerCase(str.charAt(i));
 * 4. (Line 42) if(Character.isLetter())                                         -> if(Character.isLetter(ch))
 * 5. (Line 49) System.out.println("The number of LETTERS is: " + stringLength); -> System.out.println("The number of LETTERS is: " + numLetters);
 */

package Assignment3;

public class LowerCaseString {
	
	public static void main(String[] args){
    
		String str = "Debugging is FUN!!!"; //input string that needs to be converted to lowercase
    
		int numLetters = 0;
 
		//converts string to lower case and counts number of letters
		int stringLength = str.length();
		System.out.print("In all lowercase, the sentence is: \"");
		
		for(int i = 0; i < stringLength; i++) {
			char ch = Character.toLowerCase(str.charAt(i));
			System.out.print(ch);
			if(Character.isLetter(ch))
				numLetters++;
		}
		
		//printing number of letters and characters
		System.out.println("\"");
		System.out.println("The number of CHARACTERS in the string is: " + stringLength);
		System.out.println("The number of LETTERS is: " + numLetters);
		}
	}