import java.util.ArrayList;
import java.util.Random;

public class GeneralManager {

	private int scoutingScore;
	private int favor;
	private int salary;
	private String firstNameManager;
	private String lastNameManager;
	public ArrayList<Player> GMPickTeam;

	/*
	 * Initialize a GM with first name and last name
	 */
	public GeneralManager(String firstName, String lastName) {
		this.firstNameManager = firstName;
		this.lastNameManager = lastName;
		this.setscoutingScore(50);
		this.setSalary(500000);
		this.GMPickTeam = new ArrayList<Player>();

	}

	public GeneralManager() {
		this.scoutingScore = 0;
		this.favor = 0;
		this.salary = 0;
		firstNameManager = null;
		lastNameManager = null;
		GMPickTeam = null;
	}

	/*
	 * Printing methods
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */

	public void printGeneralManager() {
		System.out.print("General Manager: " + firstNameManager + " " + lastNameManager + " , ");
		System.out.print("Scouting Score: " + this.scoutingScore + " , ");
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

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public void setscoutingScore(int scoutingScore) {
		this.scoutingScore = scoutingScore;
	}

	public void setFavor(int favor) {
		this.favor = favor;
	}

	public int getFavor() {
		return favor;
	}

	public int getSalary() {
		return salary;
	}

	public int getscoutingScore() {
		return scoutingScore;
	}

	/*
	 * End of Getters and Setters
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */

	/*
	 * Returns a randomly selected coach from the coach pool (passed as a
	 * parameter) the coach after being selected is removed from the coach pool
	 */
	public Coach pickACoach(Coach_Pool coachPool) {
		Random rand = new Random();
		int index = rand.nextInt(coachPool.getSize());
		Coach coach = coachPool.getCoachesPool().get(index);
		coachPool.getCoachesPool().remove(index);
		return coach;
	}

	/*
	 * Returns a randomly selected player from the player pool (passed as a
	 * parameter) the player after being selected is removed from the player
	 * pool
	 */
	public Player pickAPlayer(int optionNumber) {
		
	}

	

	public GeneralManager deepCopy() {
		GeneralManager g = new GeneralManager();
		g.scoutingScore = this.scoutingScore;
		g.favor = this.favor;
		g.salary = this.salary;
		g.firstNameManager = this.firstNameManager;
		g.lastNameManager = this.lastNameManager;
		ArrayList<Player> GMPickTeam = new ArrayList<Player>();
		for (Player p : this.GMPickTeam) {
			GMPickTeam.add(p.deepCopy());
		}
		g.GMPickTeam = GMPickTeam;
		return g;
	}

}
