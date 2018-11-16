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
 * Driver Class
 */
////////////////////////////////////////////////////////////////////
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args)
	{
		Library library = new Library("students.txt", "librarians.txt", "books.txt");
		Student currentStudent = new Student();
		//Librarian currentLibrarian = new Librarian();
		Book currentBook;
		Scanner in = new Scanner(System.in);
		
		// ask for student or librarian view
		System.out.println("Please select a view.");
		System.out.println("1. Student view");
		System.out.println("2. Librarian view");
		// if student view
		System.out.println("What would you like to do?");
		System.out.println("1. Log in");
		System.out.println("2. Register");
		// give 2 options :
		//  1. login
		System.out.println("Please enter your username.");
		System.out.println("Please enter your password.");
		//  2. register
		System.out.println("Please enter a username.");
		String username = in.next();
		System.out.println("Please enter a password.");
		String password = in.next();
		library.addStudent(new Student(username, password));
		// after login 
		// give 5 options? :
		//  1. view all books
		//  2. search for a book
		//  	a. select?
		// 		b. borrow (give different options for durations)
		//   	c. check status (display return date if not available)
		//  3. return a book (ask for lending id)
		//  4. pay penalty
		//  5. view all borrowed books
		System.out.println("What would you like to do?");
		System.out.println("1. Search for a book.");
		System.out.println("2. Display books borrowed.");
		System.out.println("3. Pay penalty.");
		System.out.println("4. Return a book.");
		System.out.println("5. Log out.");
		// search for a book
		System.out.println("What would you like to search by?");
		System.out.println("1. Book name");
		System.out.println("2. Author name.");
		System.out.println("3. ISBN number.");
		// by book name
		System.out.println("Please enter a book name.");
		String bookName = in.next();
		ArrayList<Book> byBookName = library.searchByBookName(bookName);
		System.out.println("Which book would you like to borrow");
		for (int i = 0; i < byBookName.size(); i++)
		{
			System.out.println(i + ". " + byBookName.get(i).toString());
		}
		Book bookSelected = byBookName.get(in.nextInt());
		currentStudent.borrow(library, bookSelected, library.getDate().getYear(), 
								library.getDate().getMonth(), library.getDate().getDay() + 7); // fix this
		// by author name
		System.out.println("Please enter an author name.");
		String authorName = in.next();
		ArrayList<Book> byAuthorName = library.searchByBookName(authorName);
		System.out.println("Which book would you like to borrow");
		for (int i = 0; i < byAuthorName.size(); i++)
		{
			System.out.println(i + ". " + byAuthorName.get(i).toString());
		}
		Book bookSelected1 = byAuthorName.get(in.nextInt());
		currentStudent.borrow(library, bookSelected1, library.getDate().getYear(), 
								library.getDate().getMonth(), library.getDate().getDay() + 7); // fix this
		
		// by ISBN number
		System.out.println("Please enter an ISBN number.");
		String ISBNnumber = in.next();
		ArrayList<Book> byNumber = library.searchByBookName(ISBNnumber);
		System.out.println("Which book would you like to borrow");
		for (int i = 0; i < byNumber.size(); i++)
		{
			System.out.println(i + ". " + byNumber.get(i).toString());
		}
		Book bookSelected2 = byNumber.get(in.nextInt());
		currentStudent.borrow(library, bookSelected2, library.getDate().getYear(), 
								library.getDate().getMonth(), library.getDate().getDay() + 7); // fix this
		
		// go back to menu
		
		// display borrowed books
		System.out.println(currentStudent.displayBooksBorrowed());
		
		// Pay penalty
		currentStudent.payPenalty();
		System.out.println("Your penalty is now 0.");
		
		// return a book
		System.out.println("Please enter the lending ID.");
		String lendingID = in.next();
		Book returnedBook; 
		for (int j = 0; j < library.getBooks().size(); j++)
		{
			if (library.getBooks().get(j).getLendingID().equals(lendingID))
			{
				returnedBook = library.getBooks().get(j);
				currentStudent.returnBook(library, returnedBook, lendingID);
			}
		}

		// log out
		// go back to main menu
		currentStudent = new Student();
		
		// if librarian view
		System.out.println("Please enter your username.");
		System.out.println("Please enter your password.");

		// give 4 options :
		//  1. add book
		//  2. remove book
		//  3. view all books
		//  4. view all lent books
		System.out.println("What would you like to do?");
		System.out.println("1. Add a book.");
		System.out.println("2. Remove a book.");
		System.out.println("3. View all books.");
		System.out.println("4. View all lent books.");
		
		// add book
		System.out.println("Book name: ");
		String bookName1 = in.next();
		System.out.println("Author name: ");
		String authorName1 = in.next();
		System.out.println("ISBN number: ");
		String ISBNNumber1 = in.next();
		//currentLibrarian.addBook(library, new Book(bookName1, authorName1, ISBNNumber1));
		
		// remove book
		System.out.println("Please enter the ISBN number.");
		String ISBNNumber2 = in.next();
		for (Book book : library.getBooks())
		{
			if (book.getISBNNumber().equals(ISBNNumber2))
			{
				//currentLibrarian.removeBook(library, book);
			}
		}

		
		// display all books
		//System.out.println(currentLibrarian.displayAllBooks(library));
		
		// display all lent books
		//System.out.println(currentLibrarian.displayAllLentBooks(library));
		
		// log out
		
	}
}
