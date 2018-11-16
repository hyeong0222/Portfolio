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
 * Once all the bugs are fixed the program should 
 * generate the following output for inputs "mississippi" and "Trending"
 * output:
 * First repeating char in mississippi is: i 
 * First repeating char in Trending is: n 
 */

/*
 * ERROR LIST
 * 1. (Line 54) if (find(ch, j))                        -> if (find(ch, i+1))
 * 2. (Line 86) words[0].word = "mississippi";          -> words[0] = new Word("mississippi");
 * 3. (Line 87) words[1].word = "Trending";             -> words[1] = new Word("Trending");
 * 4. (Line 90) char ch = words.firstRepeatCharacter(); -> char ch = words[0].firstRepeatCharacter();
 * 5. (Line 92) 	words.firstRepeatCharacter();           -> ch = words[1].firstRepeatCharacter();

 */

package Assignment3;

public class Word {
   /*
    * Constructs an analyzer for a given word.
    * @param aWord the word to be analyzed 
    */
  
	public Word(String _word) {
		
		word = _word;
	}

   /*
    * Gets the first repeating character. 
    * For example, given "hello" as input the function returns "l". 
    * The function returns 0 if no characters are repeating
    */

	public char firstRepeatCharacter() {
      
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			if (find(ch, i+1))
				return ch;
		}
		
		return 0;
	}

   /*
    * returns true if input character 'c' is 
    * equal to the char at position 'pos'
    * else returns false
    */
   
	private boolean find(char c, int pos) {
      
		for (int i = pos; i < word.length(); i++) {
         
			if (word.charAt(i) == c) {
         
				return true;
			}
		}
      
		return false;
	}
   
	private String word;
   
   
	public static void main(String args[]) {
		Word[] words = new Word[2];
		
		words[0] = new Word("mississippi");
		words[1] = new Word("Trending");
 
		//find the first repeating char for the two words in the array
		char ch = words[0].firstRepeatCharacter();
		System.out.println("First repeating char in "+ words[0].word+ " is: " + ch);
    
		ch = words[1].firstRepeatCharacter();
		System.out.println("First repeating char in "+ words[1].word+ " is: " + ch);
	
	}
}