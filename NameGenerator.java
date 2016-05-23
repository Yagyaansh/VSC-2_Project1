import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class NameGenerator{
	
	ArrayList<String> firstNames;
	ArrayList<String> lastNames;
	
	public NameGenerator() throws Exception{
		//Generates a first and last name
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
	
	public String randomFirstName(){
		//Randomly returns a first name
		Random rand = new Random();
		String name = this.firstNames.get(rand.nextInt(1000));
		return name;
	}
	public String randomLastName(){
		//Randomly returns a last name
		Random rand = new Random();
		String name = this.lastNames.get(rand.nextInt(1000));
		return name;
	}
}
