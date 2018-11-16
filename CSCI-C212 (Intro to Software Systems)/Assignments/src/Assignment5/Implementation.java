////////////////////////////////////////////////////////////////////////////////////
//
//  Assignment 5 Part 3
//
//  Author  Sang Hyeong Woo 
//  Username   sangwoo
//  Last Edited:  April 20th
//
//////////////////////////////////////////////////////////////////////////////////
package Assignment5;

import java.util.*;

public class Implementation {
	
	// instance variables
	private Map<String, Queue<Stock>> queue;
	private int profit;
	
	// constructor
	public Implementation() {
		queue = new TreeMap();
		this.profit = 0;
	}
	
	// buys the given amount of stock at a given price
	public void buyStock (String stock, int quantity, double price) {
		if (queue.containsKey(stock)) {
			Stock purchased = new Stock(quantity, price);
			queue.get(stock).add(purchased);
		}
		else {
			Queue<Stock> purchasedQueue = new LinkedList();
			purchasedQueue.add(new Stock(quantity, price));
			queue.put(stock, purchasedQueue);
		}
	}
	
	// sells the given amount of stock at a given price
	public void sellStock (String stock, int quantity, double price) {
		if (queue.containsKey(stock)) {
			Queue<Stock> sellingQueue = queue.get(stock);
			while (quantity > 0) {
				if (sellingQueue.peek().getQuantity() > quantity) {
					profit += (quantity * price) - (sellingQueue.peek().getPrice() * quantity); 
					sellingQueue.peek().sellStock(quantity);
					quantity = 0;
				}
				else {
					profit += price *  sellingQueue.peek().getQuantity() - sellingQueue.peek().getPrice() * sellingQueue.peek().getQuantity();
					quantity -= sellingQueue.poll().getQuantity();
				}
			}
		}
	}


}
