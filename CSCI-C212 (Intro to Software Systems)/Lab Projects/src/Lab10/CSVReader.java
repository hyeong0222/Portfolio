////////////////////////////////////////////////////////////////////////////////////
//
//  C212 Lab 10 Part 1
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
import java.util.ArrayList;
import java.util.Scanner;


public class CSVReader {
	ArrayList<ArrayList<String>> list = new ArrayList<>();
	private int numberOfRows = 0;
	private int numberOfFields = 0;
	
	
	// Scans a .csv file and reads data inside the file.
	public CSVReader(String filename) {
		try {
			Scanner scan = new Scanner (new File(filename));

			// Continuously run while the next row in the file is not empty.
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				
				if (!line.startsWith(",")) {
				
				numberOfRows++;
				String word = "";
				
				// a temporary arraylist to store characters of each row
				ArrayList <String> temp = new ArrayList<String>();
				
				boolean isInside = false;
				String tempWord = "";
				
				// store each and every characters from each lines
				for (int i = 0;  i < line.length(); i++) {
					char c = line.charAt(i);
					if (c == ',') {
						if (isInside) {
							tempWord += c;
						}
						else {
							temp.add(tempWord);
							numberOfFields++;
							tempWord = "";
						}
						
					}
					
					else if (c == '\"' && !isInside) {
						isInside = true;
					}
					
					else if (c == '\"' && isInside) {
						isInside = false;
					}
					
					else {
						tempWord += c;
					}
					

				}  
				list.add(temp);
				
				
				}
			}
		}
		// when the file read returns an error
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
			
	}
	
	
	// returns the number of rows in the read file
	public int numberOfRows() {
		return numberOfRows;
	}
	
	
	// returns the number of fields in the read file
	public int numberOfFields() {
		return numberOfFields;
	}
	
	
	// calculates the maximum, minimum, and the sum of the values in each column of the read file
	String statisticsHelper (int n) {
		double max = 0;
		double min = Double.MAX_VALUE;
		double sum = 0;
		
		for (ArrayList<String> each: list) {
			String temp = each.get(n);
			if (isNumber(temp)) {
				double tempDouble = Double.parseDouble(temp);
				if (tempDouble > max) {
					max = tempDouble;
				}
				if (tempDouble < min) {
					min = tempDouble;
				}
				sum += tempDouble;
			}
		}
		
		double average = sum / (list.size() - 1);
		return "At " + (n+1) + "th column, the maximum is: " + max + ", the minimum is: " + min + ", and the average is: " + average;
	}
	
	
	// runs the statistics helper method on column 3 to 8
	public void statistics () {
		for (int i = 2; i < 8; i++) {
			System.out.println(statisticsHelper(i));
		}
	}
	
	
	// a boolean method to convert each character into type double value
	public static boolean isNumber (String s) {
		try {
			Double.parseDouble(s);
			return true;
		}
		catch (NumberFormatException e) {
			return false;
		}
	}
	
	public static void main (String [] args) {
		CSVReader read = new CSVReader("/Users/sangwoo/Desktop/C212 Lab Project/src/Lab10/sample.csv");
		read.statistics();
	}

}
