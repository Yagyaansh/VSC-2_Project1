package application;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class NameGenerator{
	
	ArrayList<String> firstNames;
	ArrayList<String> lastNames;
	
	/*
	 * Initializes the NameGenerator object to randomly generate
	 * a first name and a last name 
	 * firstNames.txt and lastNames.txt
	 */
	public NameGenerator() {
		try {
		firstNames = new ArrayList<String>();
		lastNames = new ArrayList<String>();
		String fileFirstName = "firstNames.txt";
		String fileLastName = "lastNames.txt";
		Scanner file1 = new Scanner(new File(fileFirstName));
		Scanner file2 = new Scanner(new File(fileLastName));
		while (file1.hasNextLine()) {
			String line = file1.nextLine();
			firstNames.add(line);
		}
		while (file2.hasNextLine()) {
			String line = file2.nextLine();
			lastNames.add(line);
		}
		file1.close();
		file2.close();
		}
		catch(IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	/*
	 * Returns a random first name
	 */
	public String randomFirstName(){
		Random rand = new Random();
		String name = this.firstNames.get(rand.nextInt(1000));
		return name;
	}
	
	/*
	 * Returns a random last name
	 */
	public String randomLastName(){
		Random rand = new Random();
		String name = this.lastNames.get(rand.nextInt(1000));
		return name;
	}
}
