///////////////////////////////////////////////////////////////////
/* C212 Final Project - group 6
 * 
 * Sang Hyeong Woo    sangwoo
 * Jean Chiu    weitchiu
 * Dana Mach    drmach
 * Caroline Cheng     
 * 
 * Last Modified: April 25th
 * 
 * BookComparator Class
 */
////////////////////////////////////////////////////////////////////
import java.util.Comparator;

public class BookComparator implements Comparator<Book>{
	
	public int compare(Book b1, Book b2) {
		if (b1.getReturnDate().compareTo(b2.getReturnDate()) == -1)
		{
			return -1;
		}
		else
		{
			return 1;
		}
	}
}
