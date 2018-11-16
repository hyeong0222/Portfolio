////////////////////////////////////////////////////////////////////////////////////
//
//  C343 Lab 1
//
//  Author  Sang Hyeong Woo 
//  Username   sangwoo
//  Last Edited:  Aug 24th
//
//
//////////////////////////////////////////////////////////////////////////////////

public class lab1 {
	
	// Search method to find the starting index of the subarray in the main array
	public static int search (int[] a, int[] b) {
		int m = a.length;
		int n = b.length;
		int index = 0;
		int position = 0;
		
		for (int i = 0; i < n; i++) {
			
			if (a[0] == b[i]) {
				index = i;
				
				for (int j = index; j < m; j++) {
					if (b[j] != a[position]) {
						return -1;
					}
					
					else position++;
				}
				
				return index;
				
			}
		}
		
		return -1;
	}

	// Test cases
	public static void main (String[] args) {
		int[] a = new int[] {2, 3, 4, 5};
		int[] b = new int[] {1, 2, 3, 4, 5, 6, 7, 8};
		int[] c = new int[] {2, 3, 4, 5, 6, 7};
		int[] d = new int[] {5, 8, 2, 4, 7};
		int[] e = new int[] {7, 4, 2, 5, 8, 2, 4, 7, 6, 3};
		
		System.out.println(search(a, b));
		System.out.println(search(a, c));
		System.out.println(search(d, e));
		System.out.println(search(a, d));
		
	}
}
