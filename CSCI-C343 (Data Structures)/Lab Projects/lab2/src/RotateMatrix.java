import java.util.Arrays;

/**
 * Rotate function would take an n x n array, and rotate it clockwise.
 * The rotate method would loop for (n/2) times to cover all integers in the array.
 * Within the inner loop, the method would take the integer at a[0][0] and store it
 * as a temporary value, and place the rotated integer into the temporary value position.
 * The method will loop the entire matrix one by one, until only the very middle integer is
 * left. 
 * 
 * @author Sang Hyeong Woo
 * @username sangwoo
 * 
 */

public class RotateMatrix {

  /**
   * Takes an n x n array of integers and rotates it in place in a
   * clockwise direction. Uses at most O(n) extra space.
   */

	public static void rotateCW(int[][] a) {
		int n = a.length;
		// TODO
		
		for (int i = 0; i < (n/2); i++) {
			int firstRow = i;
			int lastRow = n - 1 - i;
			
			for (int j = firstRow; j < lastRow; j++) {
				int nTimes = j - firstRow;
				int temp = a[firstRow][j];
				
				a[firstRow][j] = a[lastRow-nTimes][firstRow];
				a[lastRow-nTimes][firstRow] = a[lastRow][lastRow - nTimes];
				a[lastRow][lastRow - nTimes] = a[j][lastRow];
				a[j][lastRow] = temp;
				
			}
		}
	}

	
	public static void printMatrix(int[][] a) {
		for (int[] row : a)
			System.out.println(Arrays.toString(row));
	}

	
	public static void main(String[] args) {
		int[][] a;
		a = new int[][] {
			{ 11, 12, 13, 14, 15 },
			{ 26, 27, 28, 29, 16 },
			{ 25, 34, 35, 30, 17 },
			{ 24, 33, 32, 31, 18 },
			{ 23, 22, 21, 20, 19 },
		};
 
		System.out.println("before: ");
		printMatrix(a);
		rotateCW(a);
		System.out.println("\nafter: ");
		printMatrix(a);
    
    
		int[][] b;
		b = new int[][] {
			{ 5 },
		};
 
		System.out.println("before: ");
		printMatrix(b);
		rotateCW(b);
		System.out.println("\nafter: ");
		printMatrix(b);
    
    
		int[][] c;
		c = new int[][] {
    			{ 1, 2 },
    			{ 4, 3 },
		};
 
		System.out.println("before: ");
		printMatrix(c);
		rotateCW(c);
		System.out.println("\nafter: ");
		printMatrix(c);
    
    
    		int[][] d;
    		d = new int[][] {
    			{ 1, 1, 1, 1, 1, 1 },
    			{ 1, 2, 2, 2, 2, 1 },
    			{ 1, 2, 3, 4, 2, 1 },
    			{ 1, 2, 6, 5, 2, 1 },
    			{ 1, 2, 2, 2, 2, 1 },
    			{ 1, 1, 1, 1, 1, 1 },
    		};
 
    		System.out.println("before: ");
    		printMatrix(d);
    		rotateCW(d);
    		System.out.println("\nafter: ");
    		printMatrix(d);

  	}
}
