////////////////////////////////////////////////////////////////////////////////////
//
//  Assignment 5 Part 3
//
//  Author  Sang Hyeong Woo 
//  Username   sangwoo
//  Last Edited:  April 20th
//
//
//////////////////////////////////////////////////////////////////////////////////
package Assignment5;

public class Stock {

	private int quantity;
	private double price;
	
	public Stock (int quantity, double price) {
		this.quantity = quantity;
		this.price = price;
	}
	
	// getters
	public int getQuantity () {
		return this.quantity;
	}
	
	public double getPrice () {
		return this.price;
	}
	
	//setters
	public void setQuantity (int quantity) {
		this.quantity = quantity;
	}
	
	public void setPrice (double price) {
		this.price = price;
	}
	
	//remove the amount of given shares from the quantity
	public void sellStock (int shares) {
		this.quantity = this.quantity - shares;
	}
	
}
