import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Coach {

	private int scheme;
	private int favor;
	private int salary;
	private String firstNameCoach;
	private String lastNameCoach;
	public ArrayList<Player> coachPicks;

	// private Random ran = new Random();

	public Coach(String firstName, String lastName, int scheme) {
		this.firstNameCoach=firstName;
		this.lastNameCoach=lastName;
		this.setScheme(scheme);
		this.setFavor(50);
		this.setSalary(1000000);
		this.coachPicks=new ArrayList<Player>();
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

	public int getFavor() {
		return favor;
	}

	public void setFavor(int favor) {
		this.favor = favor;
	}

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
