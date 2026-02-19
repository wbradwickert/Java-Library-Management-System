/**
 * 
 */
package edu.ilstu;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.text.DecimalFormat;

/**
 * This BookInventory class uses the Author and Book class to manage its inventory and manage separate files.
 * 
 * @author Brad Wickert
 * 
 */
public class BookInventory {
	
	/**
	 * Reads the BooksAndAuthors file and stores all the info into an array.
	 * @param inputFileName
	 * @return bookArray
	 */
	public static Book[] read(String inputFileName)
	{
		//Initialize temporary Book array and variables.
		Book bookArray[] = new Book[1];
		boolean titleDone = false;
		boolean isbnDone = false;
		boolean invalidRecord = false;
		String title = "";
		int isbn = 0;
		int numOfElements = 0;
		
		//Try catch for reading the file.
		try
		{
			Scanner input = new Scanner(new File(inputFileName));
			int commaCount = 0;
			int bookCount = 0;
			
			//Scans each word on file.
			while (input.hasNext())
			{
				Book tempBook = new Book();
				Author tempAuthor = new Author();
				String line = input.nextLine();
				Scanner scnr = new Scanner(line);
				scnr.useDelimiter(",");
				String value;
				while(scnr.hasNext())
				{
					value = scnr.next();
					commaCount++;
					switch(commaCount)
					{
					case 1:
						try
						{
							tempBook.setTitle(value);
							break;
						}
						catch (NumberFormatException e)
						{
							System.out.println("Invalid data for record " + tempBook.getTitle());
							invalidRecord = true;
							break;
						}
						
					case 2:
						try
						{
							tempBook.setIsbn(Long.valueOf(value).longValue());
							break;
						}
						catch (NumberFormatException e)
						{
							System.out.println("Invalid data for record " + tempBook.getTitle());
							invalidRecord = true;
							break;
						}
					case 3: 
						try
						{
							tempAuthor.setName(value);
							tempBook.setAuthor(tempAuthor);
							break;
						}
						catch (NumberFormatException e)
						{
							System.out.println("Invalid data for record " + tempBook.getTitle());
							invalidRecord = true;
							break;
						}
						
					case 4:
						try
						{
							tempAuthor.setBirthYear(Integer.valueOf(value).intValue());
							break;
						}
						catch (NumberFormatException e)
						{
							System.out.println("Invalid data for record " + tempBook.getTitle());
							invalidRecord = true;
							break;
						}
					case 5:
						try
						{
							tempAuthor.setNationality(value);
							break;
						}
						catch (NumberFormatException e)
						{
							System.out.println("Invalid data for record " + tempBook.getTitle());
							invalidRecord = true;
							break;
						}
						
					case 6:
						try
						{
							tempBook.setYearOfPublication(Integer.valueOf(value).intValue());
							break;
						}
						catch (NumberFormatException e)
						{
							System.out.println("Invalid data for record " + tempBook.getTitle());
							invalidRecord = true;
							break;
						}
						
					case 7:
						try
						{
							tempBook.setRetailPrice(Double.valueOf(value).doubleValue());
							break;
						}
						catch (NumberFormatException e)
						{
							System.out.println("Invalid data for record " + tempBook.getTitle());
							invalidRecord = true;
							break;
						}
						
					case 8:
						try
						{
							tempBook.setQuantityOnHand(Integer.valueOf(value).intValue());
							break;
						}
						catch (NumberFormatException e)
						{
							System.out.println("Invalid data for record " + tempBook.getTitle());
							invalidRecord = true;
							break;
						}
					}
				}
				if(!invalidRecord)
				{
					//Restarts comma count so the switch statement will be accurate.
					commaCount = 0;
					Book[] booksArrayTemp = new Book[bookCount + 1];
					
					//Makes a copy of previous array and creates bigger array.
					for(int i = 0; i < bookArray.length; i++)
					{
						booksArrayTemp[i] = bookArray[i];
					}
					booksArrayTemp[bookCount] = tempBook;
					bookArray = booksArrayTemp;
					bookCount++;
				}
				else
				{
					//Discards invalid book.
					commaCount = 0;
					invalidRecord = false;
				}
				
			}
			System.out.println("Finished reading from file.\n");
		} catch (FileNotFoundException e)
		{
			System.out.println("readArray error. File not found.");
			System.exit(1);
		}
		return bookArray;

	}
	
	/**
	 * Writes the elements of bookArray to the specified file.
	 * 
	 * @param bookArray
	 * @param outputFileName
	 */
	public static void write(Book[] bookArray, String outputFileName)
	{
		//Try catch to check if the outputFileName exists.
		try
		{
			PrintWriter pw = new PrintWriter(new FileWriter(outputFileName, false));
			
			//For each book in the array
			for(int i = 0; i < bookArray.length; i++)
			{
				//For each comma hit in a line
				for(int comma = 0; comma < 8; comma++)
				{
					Author tempAuthor = bookArray[i].getAuthor();
					
					//Checks which part of the line the scanner is at.
					switch (comma)
					{
					case 0:
						pw.write(bookArray[i].getTitle() + ",");
						break;
					case 1:
						pw.write(bookArray[i].getIsbn() + ",");
						break;
					case 2:
						pw.write(tempAuthor.getName() + ",");
						break;
					case 3:
						pw.write(tempAuthor.getBirthYear() + ",");
						break;
					case 4:
						pw.write(tempAuthor.getNationality() + ",");
						break;
					case 5:
						pw.write(bookArray[i].getYearOfPublication() + ",");
						break;
					case 6:
						pw.write(bookArray[i].getRetailPrice() + ",");
						break;
					case 7:
						pw.write(bookArray[i].getQuantityOnHand() + "");
						pw.println();
						break;
						default:
							break;
					}
				}
			}
			System.out.println("Finished writing to file " + outputFileName + "\n");
			pw.close();
		} catch (IOException e)
		{
			System.out.println("writeArray error. File not found.");
			System.exit(1);
		}
	}
	
	/**
	 * Adds a new book made by the user into the bookArray and resizes array accordingly.
	 * @param bookArray
	 * @return newArray
	 */
	public static Book[] addBook(Book[] bookArray)
	{
		boolean validInput = false;
		boolean terminate = false;
		Scanner scnr = new Scanner(System.in);
		long userISBN = 0;
		System.out.print("Enter the ISBN of the book: ");
		
		//Validates ISBN input.
		while(!validInput)
		{
			if(scnr.hasNextLong())
			{
				userISBN = scnr.nextLong();
				validInput = true;
			}
			else
			{
				validInput = false;
				System.out.print("Must be numeric: ");
				scnr.next();
			}
		}
		scnr.nextLine();
		validInput = false;
		
		//Checks if the loop should stop.
		while(!terminate)
		{
			for(int i = 1; i < bookArray.length; i++)
			{
				//If ISBN is found in a book that already exists
				if(bookArray[i].getIsbn() == userISBN)
				{
					terminate = true;
					System.out.println("That book is already in the inventory.");
					System.out.println();
				}
			}
			if(!terminate)
			{
				Book tempBook = new Book();
				Author tempAuthor = new Author();
				
				tempBook.setIsbn(userISBN);
				
				System.out.print("Enter the title of the book: ");
				tempBook.setTitle(scnr.nextLine());
				
				System.out.print("Enter the Author of the book: ");
				tempAuthor.setName(scnr.nextLine());
				
				System.out.print("Enter the Author birth year: ");
				
				//Validates birth year input.
				while(!validInput)
				{
					if(scnr.hasNextInt())
					{
						tempAuthor.setBirthYear(scnr.nextInt());
						validInput = true;
					}
					else
					{
						validInput = false;
						System.out.print("Must be a numeric year: ");
						scnr.next();
					}
				}
				
				validInput = false;
				scnr.nextLine();
				System.out.print("Enter the Author nationality: ");
				tempAuthor.setNationality(scnr.nextLine());
				
				System.out.print("Enter the release year of the book: ");
				
				//Validates publication year input.
				while(!validInput)
				{
					if(scnr.hasNextInt())
					{
						tempBook.setYearOfPublication(scnr.nextInt());
						validInput = true;
					}
					else
					{
						validInput = false;
						System.out.print("Must be a numeric year: ");
						scnr.next();
					}
				}
				
				validInput = false;
				System.out.print("Enter the price of the book: ");
				
				//Validates price input.
				while(!validInput)
				{
					if(scnr.hasNextDouble())
					{
						tempBook.setRetailPrice(scnr.nextDouble());
						validInput = true;
					}
					else
					{
						validInput = false;
						System.out.print("Must be a numeric price: ");
						scnr.next();
					}
				}
				
				validInput = false;
				System.out.print("Enter the quantity held of the book: ");
				
				//Validates quantity input.
				while(!validInput)
				{
					if(scnr.hasNextInt())
					{
						tempBook.setQuantityOnHand(scnr.nextInt());
						validInput = true;
					}
					else
					{
						validInput = false;
						System.out.print("Must be a numeric quantity: ");
						scnr.next();
					}
				}
				
				validInput = false;
				
				tempBook.setAuthor(tempAuthor);
				Book[] booksArrayTemp = new Book[bookArray.length + 1];
				for(int i = 0; i < bookArray.length; i++)
				{
					booksArrayTemp[i] = bookArray[i];
				}
				booksArrayTemp[bookArray.length] = tempBook;
				System.out.println("New book added: \n" + tempBook.toString() + "\n");
				return booksArrayTemp;
				
			}
			return bookArray;
		}
		return bookArray;
	}
	
	/**
	 * Adjusts the book prices based on user input.
	 * @param bookArray
	 * @return newBookArray
	 */
	public static Book[] adjustPrices(Book[] bookArray)
	{
		boolean validInput = false;
		Scanner scnr = new Scanner(System.in);
		Book[] newBook = bookArray;
		double userPercentage = 0;
		System.out.print("Enter a percent of change in the price of books. Negative for discount or positive for price increase: ");
		
		//Validates percentage input.
		while(!validInput)
		{
			if(scnr.hasNextDouble())
			{
				userPercentage = scnr.nextInt();
				validInput = true;
			}
			else
			{
				validInput = false;
				System.out.print("Must be numeric: ");
				scnr.next();
			}
		}
		if(userPercentage > 0)
		{
			for(int i = 0; i < bookArray.length; i++)
			{
				double subtractMoney = newBook[i].getRetailPrice() * (userPercentage/100);
				newBook[i].setRetailPrice(newBook[i].getRetailPrice() + subtractMoney);
			}
		}
		else if(userPercentage < 0)
		{
			for(int i = 0; i < bookArray.length; i++)
			{
				double addMoney = newBook[i].getRetailPrice() * (userPercentage/100);
				newBook[i].setRetailPrice(newBook[i].getRetailPrice() + addMoney);
			}
		}
		System.out.println("\nPrices adjusted by " + (int)(userPercentage) + "%\n");
		return newBook;
	}
	
	/**
	 * Calculates the total of all books in inventory.
	 * @param bookArray
	 */
	public static void calcTotal(Book[] bookArray)
	{
		double total = 0;
		
		//Calculates each book price.
		for(int i = 0; i < bookArray.length; i++)
		{
			total += bookArray[i].getRetailPrice();
		}
		DecimalFormat df = new DecimalFormat("$####.##");
		System.out.println("Total value of inventory: " + df.format(total) + "\n");
	}
	
	/**
	 * Removes a book from the inventory array and shrinks array.
	 * @param bookArray
	 * @return newBookArray
	 */
	public static Book[] deleteBook(Book[] bookArray)
	{
		boolean validInput = false;
		boolean terminate = false;
		Scanner scnr = new Scanner(System.in);
		long userISBN = 0;
		System.out.print("Enter the ISBN of the book: ");
		while(!validInput)
		{
			if(scnr.hasNextLong())
			{
				userISBN = scnr.nextLong();
				validInput = true;
			}
			else
			{
				validInput = false;
				System.out.print("Must be a numeric ISBN: ");
				scnr.next();
			}
		}
		scnr.nextLine();
		while(!terminate)
		{
			for(int i = 1; i < bookArray.length; i++)
			{
				if(bookArray[i].getIsbn() == userISBN)
				{
					bookArray[i] = null;
					
					//Shifts array to the empty space.
					for(int u = i; u < bookArray.length - 1; u++)
					{
						bookArray[u] = bookArray[u+1];
					}
					bookArray[bookArray.length - 1] = null;
					Book[] newArray = new Book[bookArray.length - 1];
					for(int j = 0; j < newArray.length; j++)
					{
						newArray[j] = bookArray[j];
					}
					return newArray;
				}
			}
			terminate = true;
		}
		return null;
		
		
	}
	
	/**
	 * Updates book information based on user input.
	 * @param bookArray
	 */
	public static void updateBook(Book[] bookArray)
	{
		boolean validInput = false;
		boolean terminate = false;
		Scanner scnr = new Scanner(System.in);
		long userISBN = 0;
		System.out.print("Enter the ISBN of the book: ");
		while(!validInput)
		{
			if(scnr.hasNextLong())
			{
				userISBN = scnr.nextLong();
				validInput = true;
			}
			else
			{
				validInput = false;
				System.out.print("Must be a numeric ISBN: ");
				scnr.next();
			}
		}
		scnr.nextLine();
		while(!terminate)
		{
			for(int i = 0; i < bookArray.length; i++)
			{
				if(bookArray[i].getIsbn() == userISBN)
				{
					Book tempBook = new Book();
					Author tempAuthor = new Author();
					
					tempBook.setIsbn(userISBN);
					
					System.out.print("Enter the title of the book: ");
					tempBook.setTitle(scnr.nextLine());
					
					System.out.print("Enter the Author of the book: ");
					tempAuthor.setName(scnr.nextLine());
					
					System.out.print("Enter the Author birth year: ");
					while(!validInput)
					{
						if(scnr.hasNextInt())
						{
							tempAuthor.setBirthYear(scnr.nextInt());
							validInput = true;
						}
						else
						{
							validInput = false;
							System.out.print("Must be a numeric year: ");
							scnr.next();
						}
					}
					
					validInput = false;
					scnr.nextLine();
					System.out.print("Enter the Author nationality: ");
					tempAuthor.setNationality(scnr.nextLine());
					
					System.out.print("Enter the release year of the book: ");
					while(!validInput)
					{
						if(scnr.hasNextInt())
						{
							tempBook.setYearOfPublication(scnr.nextInt());
							validInput = true;
						}
						else
						{
							validInput = false;
							System.out.print("Must be a numeric year: ");
							scnr.next();
						}
					}
					
					validInput = false;
					System.out.print("Enter the price of the book: ");
					while(!validInput)
					{
						if(scnr.hasNextDouble())
						{
							tempBook.setRetailPrice(scnr.nextDouble());
							validInput = true;
						}
						else
						{
							validInput = false;
							System.out.print("Must be a numeric price: ");
							scnr.next();
						}
					}
					
					validInput = false;
					System.out.print("Enter the quantity held of the book: ");
					while(!validInput)
					{
						if(scnr.hasNextInt())
						{
							tempBook.setQuantityOnHand(scnr.nextInt());
							validInput = true;
						}
						else
						{
							validInput = false;
							System.out.print("Must be a numeric quantity: ");
							scnr.next();
						}
					}
					
					validInput = false;
					
					tempBook.setAuthor(tempAuthor);
					bookArray[i] = tempBook;
					System.out.println("Book " + bookArray[i].getTitle() + " updated.\n");
					terminate = true;
					
				}
			}
		}
	}
	
	/**
	 * Searches for a book the user wants and shows its information.
	 * @param bookArray
	 */
	public static void searchBook(Book[] bookArray)
	{
		boolean validInput = false;
		boolean validChoice = false;
		boolean lookForBook = true;
		boolean bookFound = false;
		int choice = 0;
		Scanner scnr = new Scanner(System.in);
		System.out.println("What would you like to search by?\n1)Title\n2)Author\n3)ISBN");
		
		//Checks if user input is an integer.
		while(!validInput)
		{
			if(scnr.hasNextInt())
			{
				choice = scnr.nextInt();
				
					//Validates user option input.
					if(choice == 1 || choice == 2 || choice == 3)
					{
						validInput = true;
					}
					else
					{
						validInput = false;
						choice = 0;
						System.out.println("Must be 1, 2, or 3. Please try again!");
						System.out.println("What would you like to search by?\n1)Title\n2)Author\n3)ISBN");
						
					}
			}
			else
			{
				validInput = false;
				System.out.println("Must be 1, 2, or 3. Please try again!");
				System.out.println("What would you like to search by?\n1)Title\n2)Author\n3)ISBN");
				scnr.next();
			}
		}
		
		validInput = false;
		scnr.nextLine();
		if(choice == 1)
		{
			System.out.print("Enter title: ");
			String userTitle = scnr.nextLine();
			while(lookForBook)
			{
				for(int i = 0; i < bookArray.length; i++)
				{
					if(bookArray[i].getTitle().equals(userTitle))
					{
						System.out.println("\n" + bookArray[i].toString() + "\n");
						bookFound = true;
						lookForBook = false;
					}
				}
				if(!bookFound)
				{
					System.out.println("\nNot in the system.\n");
					lookForBook = false;
				}
				
			}
		}
		else if(choice == 2)
		{
			System.out.print("Enter the Auhtor's name: ");
			String userAuthName = scnr.nextLine();
			System.out.print("Enter the Author's birth year: ");
			int userYear = 0;
			while(!validInput)
			{
				if(scnr.hasNextInt())
				{
					userYear = scnr.nextInt();
					validInput = true;
				}
				else
				{
					validInput = false;
					System.out.print("Must be a numeric year: ");
					scnr.next();
				}
			}
			
			validInput = false;
			scnr.nextLine();
			while(lookForBook)
			{
				for(int i = 0; i < bookArray.length; i++)
				{
					Author authTemp = bookArray[i].getAuthor();
					if(authTemp.getName().equals(userAuthName) && authTemp.getBirthYear() == userYear)
					{
						System.out.println("\n" + bookArray[i].toString() + "\n");
						bookFound = true;
						lookForBook = false;
					}
				}
				if(!bookFound)
				{
					System.out.println("\nNot in the system.\n");
					lookForBook = false;
				}
				
			}
		}
		else if(choice == 3)
		{
			System.out.print("Enter ISBN: ");
			long userISBN = 0;
			while(!validInput)
			{
				if(scnr.hasNextLong())
				{
					userISBN = scnr.nextLong();
					validInput = true;
				}
				else
				{
					validInput = false;
					System.out.print("Must be a numeric ISBN: ");
					scnr.next();
				}
			}
			
			validInput = false;
			while(lookForBook)
			{
				for(int i = 0; i < bookArray.length; i++)
				{
					if(bookArray[i].getIsbn() == userISBN)
					{
						System.out.println("\n" + bookArray[i].toString() + "\n");
						bookFound = true;
						lookForBook = false;
					}
				}
				if(!bookFound)
				{
					System.out.println("\nNot in the system.\n");
					lookForBook = false;
				}
				
			}
		}
		
	}
	
	/**
	 * Displays every book in the inventory and its information.
	 * @param bookArray
	 */
	public static void displayInventory(Book[] bookArray)
	{
		for(int i = 0; i < bookArray.length; i++)
		{
			System.out.println(bookArray[i] + "\n");
		}
	}
	
	/**
	 * displayMenu method that displays the various choices the user can make when shown the selection.
	 */
	public static void displayMenu()
	{
		System.out.println("Welcome to the Book Inventory. What would you like to do?");
		System.out.println("1- Read from file\n2- Write to file\n3- Add a book\n4- Delete a book\n5- Update a book\n6- Search for book\n7- Display inventory\n8- Adjust prices\n9- Calc total\n0- Quit");
		System.out.print("Make your selection: ");
	}
	

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		//Declare fileName
		String fileName = ("BooksAndAuthors.csv");
		
		//Declare empty book array that will be filled/enlarged later.
		Book[] books = new Book[0];
		
		//Declare other important variables.
		boolean menuRepeat = true;
		int userChoice = 0;
		Scanner scnr = new Scanner (System.in);
		while(menuRepeat)
		{
			boolean isValid = false;
			displayMenu();
			
			//Validates user input.
			while(!isValid)
			{
				if(scnr.hasNextInt())
				{
					userChoice = scnr.nextInt();
					isValid = true;
				}
				else
				{
					isValid = false;
					System.out.print("Must be an integer: ");
					scnr.next();
				}
			}
			switch(userChoice)
			{
			case 1:
				books = read(fileName);
				break;
			case 2:
				System.out.println();
				write(books, fileName);
				break;
			case 3:
				books = addBook(books);
				default:
					break;
				case 4:
					books = deleteBook(books);
					break;
				case 5:
					updateBook(books);
					break;
				case 6:
					searchBook(books);
					break;
				case 7:
					displayInventory(books);
					break;
				case 8:
					books = adjustPrices(books);
					break;
				case 9:
					calcTotal(books);
					break;
				case 0:
					System.out.println("Goodbye. Thank you for using the Book Inventory System.");
					System.exit(0);
					break;
			}
		}

	}

}
