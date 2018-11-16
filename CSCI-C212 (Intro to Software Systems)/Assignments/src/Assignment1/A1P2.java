package Assignment1;
////////////////////////////////////////////////////////////////////////////////////
//
//  Assignment 1 Part 2
//
//  Author  Sang Hyeong Woo 
//  Username   sangwoo
//  Last Edited:  Feb 2nd
//
//
//////////////////////////////////////////////////////////////////////////////////

import java.util.Scanner;

public class A1P2 {
	public static void main(String [] args) {
	
		int correct = 0;
		Scanner input = new Scanner(System.in);

		
		System.out.println("Question 1: Which of the following former vice president of the United States was featured in the documentary 'The Inconvenient Truth?");
		System.out.println("1. George H. W. Bush");
		System.out.println("2. Joe Biden");
		System.out.println("3. Al Gore");
		System.out.println("4. Dick Cheney");
		System.out.println("");
		System.out.print("Submit your answer: ");
		int answer1 = input.nextInt();
		System.out.println("");
		
		if (answer1 == 3) {
			correct++;
		}
		
		
		System.out.println("Question 2: Which of the following person is an advocate of Global Warming?");
		System.out.println("1. Myron Ebell");
		System.out.println("2. Michael Bloomberg");
		System.out.println("3. Ivar Giaever");
		System.out.println("4. Freeman Dyson");
		System.out.println("");
		System.out.print("Submit your answer: ");
		int answer2 = input.nextInt();
		System.out.println("");

		if (answer2 == 2) {
			correct++;
		}
		
		
		System.out.println("Question 3: Are global temperatures rising now?");
		System.out.println("1. Yes, at a very rapid speed");
		System.out.println("2. Yes, but at a very minute rate");
		System.out.println("3. No, the global temperatures is the same each year");
		System.out.println("4. There is no way to know because the year-to-year temperature variability is too large");
		System.out.println("");
		System.out.print("Submit your answer: ");
		int answer3 = input.nextInt();
		System.out.println("");

		if (answer3 == 4) {
			correct++;
		}
		
		
		System.out.println("Question 4: Which of the following is not a reasonable source of increasing atmospheric concentrations of water vapor, carbon dioxide, methane, and nitrous oxide?");
		System.out.println("1. Industralization");
		System.out.println("2. Dehumanization");
		System.out.println("3. Deforestation");
		System.out.println("4. Pollution");
		System.out.println("");
		System.out.print("Submit your answer: ");
		int answer4 = input.nextInt();
		System.out.println("");

		if (answer4 == 2) {
			correct++;
		}
		
		
		System.out.println("Question 5: Which of the following countries emits the greatest amount of carbon dioxide?");
		System.out.println("1. China");
		System.out.println("2. India");
		System.out.println("3. Russia");
		System.out.println("4. United States");
		System.out.println("");
		System.out.print("Submit your answer: ");
		int answer5 = input.nextInt();
		System.out.println("");

		if (answer5 == 1) {
			correct++;
		}
		
		
		if (correct == 5) {
			System.out.println("Excellent");
		}
		else if (correct == 4) {
			System.out.println("Very good");
		}
		else System.out.println("Time to brush up on your knowledge of global warming");
		
		System.out.println("Websites used to generate quiz: ");
		System.out.println("   https://en.wikipedia.org/wiki/An_Inconvenient_Truth");
		System.out.println("   http://www.businessinsider.com/the-ten-most-important-climate-change-skeptics-2009-7/freeman-dyson-1");	
		System.out.println("   http://www.drroyspencer.com/my-global-warming-skepticism-for-dummies/");
		System.out.println("   https://news.nationalgeographic.com/news/2004/12/1206_041206_global_warming_2.html");
		System.out.println("   https://en.wikipedia.org/wiki/Paris_Agreement");

	}
}
