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
 * Librarian Class
 */
////////////////////////////////////////////////////////////////////
import java.util.ArrayList;
import java.util.Collections;

public class Librarian extends Person {
	
	public Librarian(String username, String password) {
		super(username, password);
	}
	
	public void addBook(Library library, Book book) {
		library.addBook(book);;
	}
	
	public void removeBook(Library library, Book book) {
		if (book.isAvailable()) {
			library.removeBook(book);
		}
	}
	
	public String displayAllLentBooks(Library library) {
		ArrayList<Book> allBooks = library.getBooks();
		ArrayList<Book> lentBooks = new ArrayList<Book>();
		String lentBooksString = "";
		for (Book book : allBooks)
		{
			
			if (!book.isAvailable())
			{
				lentBooks.add(book);
			}
		}
		Collections.sort(lentBooks, new BookComparator());
		for (Book book : lentBooks)
		{
			lentBooksString += book.toString();
			lentBooksString += ", Return Date: " + book.getReturnDate();
			lentBooksString += "\n";
		}
		return lentBooksString;
	}
	
	public String displayAllBooks(Library library) {
		ArrayList<Book> allBooks = library.getBooks();
		String allBooksString = "";
		for (Book book : allBooks)
		{
			allBooksString += book.toString();
			allBooksString += "\n";
		}
		return allBooksString;
	}
}
