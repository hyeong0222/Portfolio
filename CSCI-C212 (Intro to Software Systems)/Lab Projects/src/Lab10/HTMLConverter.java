////////////////////////////////////////////////////////////////////////////////////
//
//  C212 Lab 10 Part 2
//
//  Author  Sang Hyeong Woo 
//  Username   sangwoo
//  Last Edited:  Apr 3rd
//
//
//////////////////////////////////////////////////////////////////////////////////

package Lab10;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class HTMLConverter {
	
	public HTMLConverter(String filename, String outcome) {
		
		// scans a file using Scanner, and prints a new file using PrintStream
		try {
			Scanner scan = new Scanner (new File(filename));
			PrintStream out = new PrintStream(new File(outcome));
			
			// prints html headers
			out.println("<html>");
			out.println("<title>");
			out.println("This is my Java html converter, Sang Hyeong Woo");
			out.println("</title>");
			out.println("<body>");

			// scans each line of an input file, and adds '<br>' at the end of each lines.
			while (scan.hasNextLine()) {
				out.println(scan.nextLine() + "<br>");
			}
			
			// prints html footers
			out.println("</body>");
			out.println("</html>");
			
			out.close();
			scan.close();
		}
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main (String[] args) {
		HTMLConverter test = new HTMLConverter("/Users/sangwoo/Desktop/C212 Lab Project/src/Lab10/tinyFile", "myfile.txt");
		System.out.println("Outputting...");
		
	}
}
