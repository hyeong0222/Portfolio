///////////////////////////////////////////////////////////////////
/* C212 Final Project - group 6
 * 
 * Sang Hyeong Woo    sangwoo
 * Jean Chiu    weitchiu
 * Dana Mach    drmach
 * Caroline Cheng     
 * 
 * Last Modified: April 25th
 * 
 * Student Class
 */
////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class Student extends Person {
	
	private ArrayList<Book> booksBorrowed;
	private double penalty;
	private static final double DAILYFEE = 0.2; // 20 cents

	public Student(String username, String password) {
		super(username, password);
		this.booksBorrowed = new ArrayList<Book>();
		this.penalty = 0;
	}
	
	public Student() {
		super("", "");
		this.booksBorrowed = new ArrayList<Book>();
		this.penalty = 0;
	}
	
	public Student(String username, String password, double penalty, ArrayList<Book> booksBorrowed) {
		super(username, password);
		this.penalty = penalty;
		this.booksBorrowed = booksBorrowed;
	}
	
	public String displayBooksBorrowed() {
		Collections.sort(this.booksBorrowed, new BookComparator());
		String booksBorrowed = "";
		for (Book book : this.booksBorrowed)
		{
			booksBorrowed += book.toString();
			booksBorrowed += ", Return Date: " + book.getReturnDate();
			booksBorrowed += "\n";
		}
		return booksBorrowed;
	}
	
	public void payPenalty() {
		System.out.println("Your penalty has been paid.");
		this.penalty = 0;
	}
	
	public String borrow(Library library, Book book, int year, int month, int date) {
		
		String lendingID = book.getLendingID();
		
		// a student cannot check out more than 20 books
		if (this.booksBorrowed.size() < 20)
		{
			// if a student has any penalty within his/her account, he/she cannot check out any more book
			if (this.penalty == 0) {
				this.booksBorrowed.add(book);
				int i = library.getBooks().indexOf(book);
				library.getBooks().get(i).setIsAvailable(false);
				library.getBooks().get(i).setReturnDate(year, month, date);
				return "Lending ID is: " + lendingID;
			}
			else {
				return "Please pay penalty before borrowing books.";
			}
		}
		else
		{
			return "You may not check out more than 20 books at a time.";
		}
	}
	
	public void returnBook(Library library, Book book, String lendingID) {
		if (library.getDate().after(book.getReturnDate()))
		{
			long milliseconds = (library.getDate().getTime() - book.getReturnDate().getTime()); // fix this
			long seconds = milliseconds / 1000;
			long minutes = seconds / 60;
			long daysLate = minutes / (60 * 24);
			this.penalty += DAILYFEE * daysLate;
			System.out.println("Your penalty is now " + this.penalty + ".");
		}
		this.booksBorrowed.remove(book);
		int i = library.getBooks().indexOf(book);
		library.getBooks().get(i).setIsAvailable(true);
	}
	
	public String CSVReadable() {
		ArrayList<String> CSV = new ArrayList<String>();
		for (Book book : this.booksBorrowed)
		{
				CSV.add(book.getISBNNumber());
		}
		String CSVReadable = "";
		for (String s : CSV)
		{
			CSVReadable += s + ",";
		}
		CSVReadable = CSVReadable.substring(0, CSVReadable.length() - 2);
		return  this.getUsername()+ "," + this.getPassword() + "," + this.penalty + "," +
				CSVReadable;
	}
}
