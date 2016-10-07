import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Coach {

	private int scheme;
	private int salary;
	private double teachingFactor;
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
		this.setTeachingFactor(getRandomTeachingFactor());
	}

	/*
	 * Initializes a constructor without parameters Scheme is determined in the
	 * constructor
	 */
	public Coach() {
		this.firstName = "";
		this.lastName = "";
		this.setScheme(getRandomScheme());
		this.setSalary(1000000);
		this.setTeachingFactor(0);
	}

	/*
	 * Randomly generates a Scheme value for the Coach Follows a uniform
	 * distribution between 25 and 75
	 */
	public int getRandomScheme() {
		Random rand = new Random();
		return (rand.nextInt(51) + 25);
	}

	/*
	 * Randomly generates a teaching factor value for the Coach Follows a
	 * uniform distribution with a mean of 5 and a standard deviation of 0.5
	 */

	public double getRandomTeachingFactor() {
		Random rand = new Random();
		return (double) (rand.nextGaussian() * 0.5 + 5);
	}

	/*
	 * Increase salary for the coaches by 100000
	 */
	
	public void updateSalary(int wins) {
		this.salary = 600000 + (wins*100000);
	}
	
	/*
	 * 
	 * Printing methods
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */

	public void printCoach() {

		System.out.print("Coach: Coach" /* + firstNameCoach + */ + " " + lastName + " , ");
		System.out.print("Scheme: " + this.scheme + " , ");
		System.out.print("Teaching Factor: " + this.teachingFactor+" , ");
		System.out.println("Salary: $" + this.salary + ". ");

	}

	/*
	 * End of printing methods
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */

	/*
	 * Getters and setters for the fields in the class
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */

	public String getName()
	{
		return firstName + " " + lastName;
	}
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

	public double getTeachingFactor() {
		return teachingFactor;
	}

	public void setTeachingFactor(double teachingFactor) {
		this.teachingFactor = teachingFactor;
	}

	/*
	 * End of Getters and Setters
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */

	public Coach deepCopy() {
		Coach c = new Coach();
		c.scheme = this.scheme;
		c.salary = this.salary;
		c.firstName = this.firstName;
		c.lastName = this.lastName;
		return c;
	}

}
