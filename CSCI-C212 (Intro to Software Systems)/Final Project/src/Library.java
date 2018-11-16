///////////////////////////////////////////////////////////////////
/* C212 Final Project - group 6
 * 
 * Sang Hyeong Woo    sangwoo
 * Jean Chiu    weitchiu
 * Dana Mach    drmach
 * Caroline Cheng     chengyue
 * 
 * Last Modified: April 25th
 * 
 * Library Class
 */
////////////////////////////////////////////////////////////////////
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Library {

	private ArrayList<Student> students;
	private ArrayList<Librarian> librarians;
	private ArrayList<Book> books;
	private Date currentDate;

	public Library(String students, String librarians, String books) {
		try
		{
			this.students = new ArrayList<Student>();
			File studentsFile = new File(students);
			Scanner input = new Scanner(studentsFile);
			while (input.hasNextLine())
			{
				String[] s = input.nextLine().split(",");
				String username = s[0];
				String password = s[1];
				double penalty = Double.parseDouble(s[2]);
				ArrayList<Book> borrowedBooks = new ArrayList<Book>();
				for (Book book : this.books)
				{
					for (String str : s)
					{
						if (book.getISBNNumber().equals(str))
						{
							borrowedBooks.add(book);
						}
					}
				}
				Student student = new Student(username, password, penalty, borrowedBooks);
				this.students.add(student);
			}
		}
		
		catch (IOException exception)
		{
			exception.printStackTrace();
		}
		
		
		try
		{
			this.librarians = new ArrayList<Librarian>();
			File librariansFile = new File(librarians);
			Scanner input = new Scanner(librariansFile);
			while (input.hasNextLine())
			{
				String[] s = input.nextLine().split(",");
				String username = s[0];
				String password = s[1];
				Librarian librarian = new Librarian(username, password);
				this.librarians.add(librarian);
			}		
		}
		
		catch (IOException exception)
		{
			exception.printStackTrace();
		}
		
		
		try
		{
			this.books = new ArrayList<Book>();
			File booksFile = new File(books);
			Scanner input = new Scanner(booksFile);
			while (input.hasNextLine())
			{
				String[] s = input.nextLine().split(",");
				String bookName = s[0];
				String authorName = s[1];
				String ISBNNumber = s[2];
				boolean isAvailable = Boolean.parseBoolean(s[3]);
				String dayString = s[4];
				DateFormat df = new SimpleDateFormat();
				try {
					Date returnDate = df.parse(dayString);
					Book book = new Book(bookName, authorName, ISBNNumber, isAvailable, returnDate);
					this.books.add(book);
				} 
				catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		
		catch (IOException exception)
		{
			exception.printStackTrace();
		}
		this.currentDate = new Date();
	}

	public Library() {
		this.students = new ArrayList<Student>();
		this.librarians = new ArrayList<Librarian>();
		this.books = new ArrayList<Book>();
		this.currentDate = new Date();
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}

	public Date getDate() {
		return this.currentDate;
	}

	public ArrayList<Book> getBooks() {
		return this.books;
	}

	public void addBook(Book book) {
		this.books.add(book);
	}

	public void removeBook(Book book) {
		this.books.remove(book);
	}

	public void updateStudents(PrintWriter studentsCSV) {
		for (Student student : this.students) {
			studentsCSV.println(student.CSVReadable());	
		}
		studentsCSV.flush();
	}

	public void books(PrintWriter booksCSV) {
		for (Book book : this.books) {
			booksCSV.println(book.CSVReadable());	
		}
		booksCSV.flush();
	}
	
	public ArrayList<Book> searchByBookName(String bookName)
	{
		ArrayList<Book> booksByName = new ArrayList<Book>();
		for (Book book : this.getBooks())
		{
			if (book.getBookName().equals(bookName))
			{
				booksByName.add(book);
			}
		}
		return booksByName;
	}
	
	public ArrayList<Book> searchByAuthorName(String authorName)
	{
		ArrayList<Book> booksByAuthor = new ArrayList<Book>();
		for (Book book : this.getBooks())
		{
			if (book.getAuthorName().equals(authorName))
			{
				booksByAuthor.add(book);
			}
		}
		return booksByAuthor;
	}
	
	public Book searchByISBNNumber(String ISBNNumber)
	{
		for (Book book : this.getBooks())
		{
			if (book.getISBNNumber().equals(ISBNNumber))
			{
				return book;
			}
		}
		return (Book) null;
	}
}
