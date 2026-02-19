/**
 * 
 */
package edu.ilstu;

/**
 * Author class that contains information about each author.
 * 
 * @author Brad Wickert
 * 
 */
public class Author {

	private String name;
	private int birthYear;
	private String nationality;
	
	/**
	 * Blank Author constructor.
	 */
	public Author()
	{
	}
	
	/**
	 * Parameter filled Author constructor.
	 * @param name
	 * @param birthYear
	 * @param nationality
	 */
	public Author(String name, int birthYear, String nationality)
	{
		this.name = name;
		this.birthYear = birthYear;
		this.nationality = nationality;
	}
	
	/**
	 * Getter for name.
	 * @return name
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Getter for birthYear
	 * @return birthYear
	 */
	public int getBirthYear()
	{
		return this.birthYear;
	}
	
	/**
	 * Getter for nationality
	 * @return nationality
	 */
	public String getNationality()
	{
		return this.nationality;
	}
	
	/**
	 * Setter for name
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Setter for birthYear
	 * @param birthYear
	 */
	public void setBirthYear(int birthYear)
	{
		this.birthYear = birthYear;
	}
	
	/**
	 * Setter for nationality
	 * @param nationality
	 */
	public void setNationality(String nationality)
	{
		this.nationality = nationality;
	}
	
	/**
	 * toString method that returns all of the variable information.
	 */
	public String toString()
	{
		return "Author Name: " + name + ", Birth Year: " + birthYear + ", Nationality: " + nationality;
	}
	
	/**
	 * equals method for the Author class
	 * @param object
	 * @return boolean
	 */
	public boolean equals(Author object)
	{
		if(this.name == object.getName() && this.birthYear == object.getBirthYear())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
