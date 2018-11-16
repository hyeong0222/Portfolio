package Lab4;
////////////////////////////////////////////////////////////////////////////////////
//
//  Lab 4 Part 2
//
//  Author  Sang Hyeong Woo 
//  Username   sangwoo
//  Last Edited:  Feb 6th
//
//
//////////////////////////////////////////////////////////////////////////////////

import java.util.Scanner;

public class Lab4Doors {
	public static void main(String [] args) {
		
		Scanner input = new Scanner(System.in);
		String command = input.nextLine();
		
		String dashboardLeft = command.substring(0, 1);
		String dashboardRight = command.substring(2, 3);
		String dashboardChildLock = command.substring(4, 5);
		String dashboardMasterUnlock = command.substring(6, 7);
		String insideHandleLeft = command.substring(8, 9);
		String outsideHandleLeft = command.substring(10, 11);
		String insideHandleRight = command.substring(12, 13);
		String outsideHandleRight = command.substring(14, 15);
		String gear = command.substring(16, 17);
		
		// This condition would check various conditions to determine if both the left door and the right door are closed.
		if (dashboardMasterUnlock.equals("0") 
				|| ((dashboardChildLock.equals("1") && (insideHandleLeft.equals("1"))) || (dashboardChildLock.equals("1")) && (insideHandleRight.equals("1")))
				|| (dashboardLeft.equals("0") && dashboardRight.equals("0") &&
						insideHandleLeft.equals("0") && insideHandleRight.equals("0") && outsideHandleLeft.equals("0") && outsideHandleRight.equals("0"))) {
			System.out.println("Both doors stay closed");
		}	
		// This condition would determine either the left or the right door is opened.
		else if (gear.equals("P") && dashboardMasterUnlock.equals("1")) {
			if (dashboardChildLock.equals("0")) {
				if (dashboardLeft.equals("1") || insideHandleLeft.equals("1") || outsideHandleLeft.equals("1")) {
					System.out.println("Left door opens");
				}
				if (dashboardRight.equals("1") || insideHandleRight.equals("1") || outsideHandleRight.equals("1")) {
					System.out.println("Right door opens");
				}
			}
			if (dashboardChildLock.equals("1")) {
				if (dashboardLeft.equals("1") || outsideHandleLeft.equals("1")) {
					System.out.println("Left door opens");
				}
				if (dashboardRight.equals("1") || outsideHandleRight.equals("1")) {
					System.out.println("Right door opens");
				}
			}
		}	
	}
}
