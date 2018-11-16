////////////////////////////////////////////////////////////////////////////////////
//
//  Assignment 5 Part 2
//
//  Author  Sang Hyeong Woo 
//  Username   sangwoo
//  Last Edited:  April 20th
//
//
//////////////////////////////////////////////////////////////////////////////////
package Assignment5;

import java.util.*;
	
public class Reverse {
	
	// instance variables
	private LinkedList<String> list;
	private Stack<String> reversedList;
	private PriorityQueue<String> sortedList;
	
	// constructor
	public Reverse() {
		this.list = new LinkedList<String>();
		this.reversedList = new Stack<String>();
		this.sortedList = new PriorityQueue<String>();
	}
	
	// adding ten strings to a LinkedList.
	public void addList() {
		list.add("Adeel");
		list.add("Angad");
		list.add("Eric");
		list.add("Yiqing");
		list.add("Bhavesh");
		list.add("Patrick");
		list.add("Shiyue");
		list.add("Raakesh");
		list.add("Nicholas");
		list.add("Sai");	
	}
	
	// sorting the LinkedList in reversed order
	public void reserved() {
		LinkedList<String> temp = new LinkedList<String>();
		for (String elements : list) {
			reversedList.add(elements);
		}
		
		while (!reversedList.isEmpty()) {
			temp.add(reversedList.pop());
		}
		
		System.out.println(temp);
	}
	
	
	// sorting the LinkedList
	public void sorted() {
		LinkedList<String> temp = new LinkedList<String>();
		for (String elements : list) {
			sortedList.add(elements);
		}
		
		while (!sortedList.isEmpty()) {
			temp.add(sortedList.poll());
		}
		
		System.out.println(temp);
	}
	
	
	public static void main (String [] args) {
		Reverse test = new Reverse();
		test.addList();
		System.out.println("The reversed order of the list is:");
		test.reserved();
		System.out.println("The sorted order of the list is: ");
		test.sorted();
	}

}
