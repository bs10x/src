package bs10x.var;


import java.util.ArrayList;
import java.util.List;


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
// 'Library' class with the overloaded 'addBook()' and 'removeBook()' methods
// to add and remove 'Book' objects or (for the former) enter attributes and instantiate.
// 
// Additionally, a 'printLibraryDetails()' method
// to print the details about all the books in the 'Library'.
//
// On top of that, a 'getLibraryTotalPageCount()' method
// to get the total page count of the 'Library'.
class Library {
	private String LibraryName;
	private List<Book> collection;		
		
	public Library(String LibraryName) {
		this.LibraryName = LibraryName;
		collection = new ArrayList<>();
	}
	
	public void printLibraryDetails() {
		for (Book book : collection) {
			book.printBookDetails();
		}
	}
	
	public String getLibraryName() {return LibraryName;}
	
	// getLibraryTotalPageCount()
	public int getLibraryTotalPageCount() {
		int LibraryTotalPageCount;
		LibraryTotalPageCount = 0;
		for (Book book : collection) {
			LibraryTotalPageCount += book.getBookPageCount();
		}
		return LibraryTotalPageCount;
	}
			
	public void addBook(Book newBook) {
		collection.add(newBook);
	}
	
	// overloading of the constructor method to take arguments for instantiation
	public void addBook(String title, Author author, int releaseYear, int pageCount, double price, int quantity) {
		Book newBook;
		newBook = new Book(title, author, releaseYear, pageCount, price, quantity);
		collection.add(newBook);
	}

	// method to remove book object
	public void removeBook(Book oldBook) {
		collection.remove(oldBook);
	}
		
}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	
	
// 'Book' class with attributes:
// 'title', 'author' (an instance of the 'Author' class),
// 'releaseYear', 'pageCount', 'price', 'quantity' 
// and constructor, getter, 'pricePerPage' and 'printBookDetails' methods
class Book {
	private String title;
	private Author author;
	private int releaseYear;
	private int pageCount;
	private double price;
	private int quantity;
		
	// constructor method for objects of class 'Book'
	public Book(String title, Author author,  int releaseYear, int pageCount, double price, int quantity) {
		this.title = title;
		this.author = author;
		this.releaseYear = releaseYear;
		this.pageCount = pageCount;
		this.price = price;
		this.quantity = quantity;
	}
	
	// getter methods for the attributes
	public String getBookTitle() {return title;}
	public String getBookAuthor() {return author.getAuthorName();} 	//.getName();?
	public int getBookReleaseYear() {return releaseYear;}
	public int getBookPageCount() {return pageCount;}
	public double getBookPrice() {return price;}
	public int getBookQuantity() {return quantity;}
			
	// method to calculate price per page for the given book
	public double getPricePerPage() {
		double pricePerPage = this.price / (double) this.pageCount;
		return pricePerPage;
	}
		
	// method to print book's details in a nice string
	public void printBookDetails() {
		System.out.println(
				this.getBookTitle()
				+ ", a book written by "
				+ this.getBookAuthor()
				+ " and released in "
				+ this.getBookReleaseYear()
				+ " costs "
				+ this.getBookPrice()
				+ "$ and there are currently "
				+ this.getBookQuantity()
				+ " copies available."
				+ " It has a ppp of "
				+ this.getPricePerPage()
				+ "$."
				);
			
	}		
}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


// EBook class, inheriting from superclass 'Book',
// with overridden 'printBookDetails()' method
// and getter methods for attributes 'font' and 'size'
class EBook extends Book {
	// new attributes
	private String font;
	private double size;
		
	// constructor method
	public EBook(String title, Author bookAuthor, int releaseYear, int pageCount, double price, int quantity, String font, double size) {	
	// call the superclass' constructor?
	super(title, bookAuthor, releaseYear, pageCount, price, quantity); 
	// pass constructor variables to class for instantiation
	this.font = font;
	this.size = size;
	}
	
	public String getEBookFont() {return font;}
	public double getEBookSize() {return size;}
	
	@Override
	public void printBookDetails() {
		System.out.println(
				this.getBookTitle()
				+ ", a book written by "
				+ this.getBookAuthor()
				+ " and released in "
				+ this.getBookReleaseYear()
				+ " costs "
				+ this.getBookPrice()
				+ "$ and there are currently "
				+ this.getBookQuantity()
				+ " copies available."
				+ " It has a ppp of "
				+ this.getPricePerPage()
				+ "$."
				+ " Its font is "
				+ this.getEBookFont()
				+ " and its size "
				+ this.getEBookSize()
				+ "MB."
				);
	}		
}
	

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
// 'Author' class with attributes
// 'name', 'gender', 'age', 'email' and
// constructor and getter methods
class Author {
	private String name;
	private String gender;
	private int age;
	private String email;
		
	// constructor methods for objects of 'Author' class
	public Author(String name, String gender, int age, String email) {
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.email = email;
	}
		
	// getter methods for the attributes
	public String getAuthorName() {return name;}
	public String getAuthorGender() {return gender;}
	public int getAuthorAge() {return age;}
	public String getAuthorEmail() {return email;}
}

	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
	

public class LibrarySystem {	
	public static void main(String[] args) {
		
		Library testLibrary1;
		testLibrary1 = new Library("testLibrary1");
		
		Author testAuthor1;
		testAuthor1 = new Author("Danny Miller", "male", 34, "danny.miller@email.com");
		
		Book testBook1;
		testBook1 = new Book("testBook1", testAuthor1, 2023, 100, 17.90, 7);
		//testBook1.printBookDetails();
		
		EBook testEBook1;
		testEBook1 = new EBook("testEBook1", testAuthor1, 2004, 666, 34.20, 3, "Helvetica Neue", 1.5);
		//testEBook1.printBookDetails();
		
		testLibrary1.addBook(testBook1);
		testLibrary1.addBook(testEBook1);
		testLibrary1.printLibraryDetails();
		
		System.out.println(testLibrary1.getLibraryTotalPageCount());		
	}

}



//adding error handling mechanisms for cases where a book is removed that doesn't exist in the collection
//For the printBookDetails method, you could consider formatting the output in an even more readable and organized manner.
//better comments