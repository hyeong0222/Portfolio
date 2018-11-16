////////////////////////////////////////////////////////////////////////////////////
//
//  Lab 6
//
//  Author  Sang Hyeong Woo 
//  Username   sangwoo
//  Last Edited:  Feb 20th
//
//
//////////////////////////////////////////////////////////////////////////////////

package Lab6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class FrequencyCounter {
	private int lines;
	private int wordCount;
	static ArrayList<StringFrequency> frequencies = new ArrayList<>(); // An ArrayList storing the entire strings of imported file, but it will be modified.
	static ArrayList<StringFrequency> frequencies1 = new ArrayList<>(); // An ArrayList storing the entire strings of imported file.
	ArrayList<StringFrequency> frequencies2 = new ArrayList<>(); // An ArrayList storing distinct words of the imported file.


	public FrequencyCounter(String filename) {
		try {
			// create new File object and pass to the scanner
			Scanner scan = new Scanner(new File(filename));
			Scanner scan2 = new Scanner(new File(filename));
			
			// This while loop would check if the scanned string has next line or not.
			// If there is a next line, it would increment the variable 'lines' to increase the total number of lines. 
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				StringFrequency newLine = new StringFrequency(line);
				newLine.setString(line);				
				lines ++;
				
				// This inner while-loop would take each word from the given string, and add those words into ArrayList 'frequencies' and 'frequencies1'.
				// It would also increase the variable 'wordCount' to show how many words are in the imported text file.
				while (scan2.hasNext()) {
					String word = scan2.next();
					StringFrequency newWord = new StringFrequency(word);
					frequencies.add(newWord);
					frequencies1.add(newWord);
					wordCount++;
				}
			}
		}	
			// Use Scanner methods have you normally have to process the file
			// Gather all the data you need in the try block, and store in instance fields
		catch (FileNotFoundException e) {
			e.printStackTrace(); 
		}
	}

	
	// This method would take an ArrayList, and use it to calculate how many words are distinct words.
	public int distinctWord(ArrayList<StringFrequency> frequency) {
		
		// This for-loop would take the first value of frequency ArrayList, add that value into 'frequencies2' ArrayList, 
		// and compare it with the remaining values of the ArrayList. If there is a duplicate value, this loop would remove the second one.
		for (int i = 0; i < frequency.size(); i++) {
			String name1 = frequency.get(i).getString();
			if (name1 != "/n" || name1 != "" || name1 != " " || name1 != "  ") {
				frequencies2.add(frequency.get(i));
			
				for (int j = i+1; j < frequency.size(); j++) {
					String name2 = frequency.get(j).getString();
					if (name1.equals(name2)) {
						frequency.remove(j);
					}
				}
			}
		}
//		return frequencies2.
		return frequencies2.size();
	}
	
	
	public static void main(String [] args) {
		FrequencyCounter tinyTale = new FrequencyCounter("tale.txt");
		
		System.out.println("tinyTale.txt has " + tinyTale.wordCount + " number of words.");
		System.out.println("tinyTale.txt has " + tinyTale.lines + " number of lines.");
		System.out.println("tinyTale.txt has " + tinyTale.distinctWord(frequencies) + " distinct words.");
		System.out.println();
		
		// This for-loop would take the first value from the ArrayList 'frequencies1', and compare it with the remaining values of the ArrayList.
		// If the values match, it would increase the count of each distinct word occurances.
		frequencies1.get(0).setCount(1);
		for (int i = 0; i < frequencies1.size(); i++) {
			String name1 = frequencies1.get(i).getString();
			for (int j = 1; j < frequencies1.size(); j++) {
				String name2 = frequencies1.get(j).getString();
				if (name1.equals(name2)) {
					frequencies1.get(i).increment();
				}
			}
		}
		
		// This loop would print each distinct words, with their length and count values.
		for (int i = 0; i < frequencies.size(); i++) {
			String name = frequencies.get(i).getString();
			frequencies.get(i).setLength(name.length());
			System.out.println(name + " {Word length: " + frequencies.get(i).getLength() + "}: " + frequencies.get(i).getCount());
		}
	}
}
