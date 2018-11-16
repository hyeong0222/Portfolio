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
 * Book Class
 */
////////////////////////////////////////////////////////////////////
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

public class Book {

	private String bookName;
	private String authorName;
	private String ISBNNumber;
	private boolean isAvailable;
	private Date returnDate;
	private String lendingID;

	public Book(String bookName, String authorName, String ISBNnumber) {
		this.bookName = bookName;
		this.authorName = authorName;
		this.ISBNNumber = ISBNnumber; //unique for each book
		this.isAvailable = true;
		this.returnDate = new Date();
		this.lendingID = "";
	}
	
	public Book(String bookName, String authorName, String ISBNnumber, boolean isAvailable, Date returnDate) {
		this.bookName = bookName;
		this.authorName = authorName;
		this.ISBNNumber = ISBNnumber;
		this.isAvailable = isAvailable;
		this.returnDate = returnDate;
	}
	
	public void setLendingID(String lendingID)
	{
		this.lendingID = lendingID;
	}
	
	public String getLendingID()
	{
		return this.lendingID;
	}
	
	public String getAuthorName()
	{
		return this.authorName;
	}
	
	public String getBookName()
	{
		return this.bookName;
	}
	
	public String getISBNNumber()
	{
		return this.getISBNNumber();
	}
	
	public boolean isAvailable() {
		return this.isAvailable;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public void setISBNNumber(String ISBNNumber) {
		this.ISBNNumber = ISBNNumber;
	}

	public void setIsAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public void setReturnDate(int year, int month, int date) {
		this.returnDate = new Date(year, month, date);
	}

	public String toString() {
		return "Book Name: " + this.bookName + ", Author Name: " + this.authorName + ", ISBN Number: " + this.ISBNNumber
				+ "Available: " + this.isAvailable;
	}

	public String CSVReadable() {
		return this.bookName + "," + this.authorName + "," + this.ISBNNumber + "," + this.isAvailable + "," + 
				this.returnDate + "," + this.lendingID;
	}

	public Date getReturnDate() {
		return this.getReturnDate();
	}
}
