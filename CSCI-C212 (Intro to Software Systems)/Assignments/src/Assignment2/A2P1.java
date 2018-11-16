////////////////////////////////////////////////////////////////////////////////////
//
//  Assignment 2 Part 1
//
//  Author  Sang Hyeong Woo 
//  Username   sangwoo
//  Last Edited:  Mar 27th
//
//
//////////////////////////////////////////////////////////////////////////////////

package Assignment2;

public class A2P1 {
	
	// This method would compare the first character and the second character of the string, and compare each of their integer value
	public static boolean isSorted(String S) {
		int index1 = 0;
		int index2 = 1;
		boolean result = true;
		
		for (int i = 1; i < S.length(); i++) {
			char character1 = S.charAt(index1);
			char character2 = S.charAt(index2);
			// if the integer value of character1 is greater than the value of character2, it would set the result to false.
			if (character1 >= 65 && character1 <= 90) {
				if (character1 > character2) {
					result = false;
				}
				// it would increment both index1 and index2 by 1, so the values of 2nd and 3rd characters can be compared.
				else {
					index1++;
					index2++;
				}
			}
			else if (character1 >= 97 && character1 <= 122) {
				if (character1 > character2) {
					result = false;
				}
				// it would increment both index1 and index2 by 1, so the values of 2nd and 3rd characters can be compared.
				else {
					index1++;
					index2++;
				}
			}
			else result = false;
		}
		// returns the boolean result
		return result;
	}
	
	
	// This method uses nested for loop to print out + signs.
	// It would print + sign by the amount of N value on the first and the last of M value, and would print empty character on the rest,
	// except for it would print 'o' character when the position is at the center.
	public static void border(int N, int M) {
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				// If its the first or the last line, it would print '+' continuously for M number of times
				if (i == 0 || i == M-1) {
					System.out.print('+');
					if (j == N-1) {
						System.out.println("");
					}
				}
				// Otherwise, it would print an empty string continuously
				else if (i != 0 || i != M-1) {
					// If the position is in the center, it would print 'o'.
					if (i == (M/2) && j == (N/2)) {
						System.out.print('o');
					}
					else if (j == 0 || j == N-1) {
						System.out.print('+');
						if (j == N-1) {
							System.out.println("");
						}
					}
					else System.out.print(" ");
				}
			}
		}
	}
	
	
	// This function would take an integer n, and would return n x n with asterisks using for and while loops.
	public static void N (int n) {
		int height = n;
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < height; j++) {
				if (j == 0) {
				System.out.print('*');
				}
				if (i == j) {
					System.out.print('*');
				}
				if (j == height - 1) {
					System.out.println('*');
				}
				else System.out.print(' ');
			}		
		}
	}
	
	
	// Test case
	public static void main(String[] args) {
		System.out.println(isSorted("bdfjk"));
		border(13, 5);
		N(5);
	}
}
