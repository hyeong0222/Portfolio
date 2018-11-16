package Lab4;
////////////////////////////////////////////////////////////////////////////////////
//
//  Lab 4 Part 4
//
//  Author  Sang Hyeong Woo 
//  Username   sangwoo
//  Last Edited:  Feb 5th
//
//
//////////////////////////////////////////////////////////////////////////////////

import java.util.Random;
import java.lang.Math;

public class Lab4BuffonNeedleExperiment {
	public static void main (String [] args) {
	
		Random random = new Random();
		double yLow = 0;
		double angleDegree = 0;
		double hit = 0;
		double yHigh = 0;
		int count = 0;
		
		// The for loop will iterate for 10000 times and determine how many needles touch the line 
		for (count = 0; count < 10000; count++) {
			yLow = random.nextDouble() * 2;
			angleDegree = random.nextDouble() * 180;
			double angleRadian = Math.toRadians(angleDegree);
			yHigh = yLow + Math.sin(angleRadian);
			if (yHigh >= 2) {
				hit++; 
			}
		}

		System.out.println(count / hit);
	}
}
