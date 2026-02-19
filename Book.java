/**
 * 
 */
package edu.ilstu;

/**
 * Book class that contains information about each book.
 * 
 * @author Brad Wickert
 * 
 */
public class Book {

	private String title;
	private long isbn;
	private Author author;
	private int yearOfPublication;
	private double retailPrice;
	private int quantityOnHand;
	
	/**
	 * Blank Book constructor.
	 */
	public Book()
	{
	}
	
	/**
	 * Parameter filled Book constructor.
	 * @param title
	 * @param isbn
	 * @param author
	 * @param yearOfPublication
	 * @param retailPrice
	 * @param quantityOnHand
	 */
	public Book(String title, long isbn, Author author, int yearOfPublication, double retailPrice, int quantityOnHand)
	{
		this.title = title;
		this.isbn = isbn;
		this.yearOfPublication = yearOfPublication;
		this.retailPrice = retailPrice;
		this.quantityOnHand = quantityOnHand;
	}
	
	/**
	 * Getter for title
	 * @return title
	 */
	public String getTitle()
	{
		return this.title;
	}
	
	/**
	 * Getter for isbn
	 * @return isbn
	 */
	public long getIsbn()
	{
		return this.isbn;
	}
	
	/**
	 * Getter for author
	 * @return author
	 */
	public Author getAuthor()
	{
		return this.author;
	}
	
	/**
	 * Getter for yearOfPublication
	 * @return yearOfPublication
	 */
	public int getYearOfPublication()
	{
		return this.yearOfPublication;
	}
	
	/**
	 * Getter for retailPrice
	 * @return retailPrice
	 */
	public double getRetailPrice()
	{
		return this.retailPrice;
	}
	
	/**
	 * Getter for quantityOnHand
	 * @return quantityOnHand
	 */
	public int getQuantityOnHand()
	{
		return this.quantityOnHand;
	}
	
	/**
	 * Setter for title
	 * @param title
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	/**
	 * Setter for isbn
	 * @param isbn
	 */
	public void setIsbn(long isbn)
	{
		this.isbn = isbn;
	}
	
	/**
	 * Setter for author
	 * @param author
	 */
	public void setAuthor(Author author)
	{
		this.author = author;
	}
	
	/**
	 * Setter for yearOfPublication
	 * @param yearOfPublication
	 */
	public void setYearOfPublication(int yearOfPublication)
	{
		this.yearOfPublication = yearOfPublication;
	}
	
	/**
	 * Setter for retailPrice
	 * @param retailPrice
	 */
	public void setRetailPrice(double retailPrice)
	{
		this.retailPrice = retailPrice;
	}
	
	/**
	 * Setter for quantityOnHand
	 * @param quantityOnHand
	 */
	public void setQuantityOnHand(int quantityOnHand)
	{
		this.quantityOnHand = quantityOnHand;
	}
	
	/**
	 * toString method that returns all variable information.
	 */
	public String toString()
	{
		return "Title: " + title + "\nISBN: " + isbn + "\n" + author.toString() + "\nYear of Publication: " + yearOfPublication + "\nRetail Price: " + retailPrice + "\nQuantity On Hand: " + quantityOnHand;
	}
	
	/**
	 * equals method for the Book class.
	 * @param object
	 * @return boolean
	 */
	public boolean equals(Book object)
	{
		if(this.title == object.getTitle() && this.isbn == object.getIsbn() && this.author == object.getAuthor() && this.yearOfPublication == object.getYearOfPublication() && this.retailPrice == object.getRetailPrice() && this.quantityOnHand == object.getQuantityOnHand())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
