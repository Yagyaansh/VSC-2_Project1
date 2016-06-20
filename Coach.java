import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Coach {

	private int scheme;
	private int salary;
	private String firstName;
	private String lastName;

	/*
	 * This constructor initializes a coach using first name and last name
	 * Scheme is determined in the constructor
	 */
	public Coach(String firstName, String lastName) {
		this.firstName = firstName; 
		this.lastName = lastName;
		this.setScheme(getRandomScheme());
		this.setSalary(1000000);
	}
	
	/*
	 * Initializes a constructor without parameters
	 * Scheme is determined in the constructor
	 */
	public Coach() {
		this.firstName = ""; 
		this.lastName = "";
		this.setScheme(getRandomScheme());
		this.setSalary(0);
	}

	/*
	 * Randomly generates a Scheme value for the Coach
	 * Follows a uniform distribution between 25 and 75
	 */
	public int getRandomScheme()
	{
		return (int)(Math.random()*(75-25))+25;
	}
	

	/*
	 * Printing methods
	 * -------------------------------------------------------------------------------------------------
	 */
	

	public void printCoach() {

		System.out.print("Coach: Coach" /* + firstNameCoach + */+ " "
				+ lastName + " , ");
		System.out.print("Scheme: " + this.scheme + " , ");
		// System.out.print("Favor: " + this.favor+" , ");
		System.out.println("Salary: $" + this.salary + ". ");

	}
	

	/*
	 * End of printing methods
	 * -------------------------------------------------------------------------------------------------
	 */
	
	
	/*
	 * Getters and setters for the fields in the class
	 * -------------------------------------------------------------------------------------------------
	 */

	public int getScheme() {
		return scheme;
	}

	public void setScheme(int scheme) {
		this.scheme = scheme;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	
	/*
	 * End of Getters and Setters
	 * -------------------------------------------------------------------------------------------------
	 */

	public Coach deepCopy(){
		Coach c = new Coach();
		c.scheme=this.scheme;
		c.salary=this.salary;
		c.firstName=this.firstName;
		c.lastName=this.lastName;
		return c;
	}

}
