import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Coach {

	private int scheme;
	//private int favor; // guys whats this field ? Could not find it in the document
	private int salary;
	private String firstNameCoach;
	private String lastNameCoach;
	public ArrayList<Player> coachPicks;


	// private Random ran = new Random();
	
	// the scheme will be a random value between 25 and 75. 
	// removed it from the constructor

	public Coach(String firstName, String lastName) {
		this.firstNameCoach = firstName; // will always be set to "Coach"
		this.lastNameCoach = lastName;
		//this.favor=50;
		this.setScheme(getRandomScheme());
		this.setSalary(1000000);
		this.coachPicks=new ArrayList<Player>();

	}
	
	public int getRandomScheme()
	{
		// randomly generated
		// uniform distribution between 25 and 75
		
		return (int)(Math.random()*(75-25))+25;
	}

	public void printCoach() {

		System.out.print("Coach: Coach" /* + firstNameCoach + */+ " "
				+ lastNameCoach + " , ");
		System.out.print("Scheme: " + this.scheme + " , ");
		// System.out.print("Favor: " + this.favor+" , ");
		System.out.println("Salary: $" + this.salary + ". ");

	}

	public int getScheme() {
		return scheme;
	}

	public void setScheme(int scheme) {
		this.scheme = scheme;
	}

	/*public int getFavor() {
		return favor;
	}

	public void setFavor(int favor) {
		this.favor = favor;
	}*/

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public void coachPickStarters(ArrayList<Player> GMPicks) {
		//Coach chooses starters from the players selected by the GM
		Random ranIndex = new Random();
		for (int i = 0; i < 22; i++) {
			int index = ranIndex.nextInt(GMPicks.size());
			coachPicks.add(GMPicks.get(index));
			GMPicks.remove(index);
		}
		// System.out.println(PlayerPool.getSize());

	}
	


}
