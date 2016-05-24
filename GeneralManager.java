import java.util.Random;

public class GeneralManager {

	// Player_Pool PlayerPool;
	private int scoutingScore;
	private int favor;
	private int salary;
	private String firstNameManager;
	private String lastNameManager;

	// private Random ran = new Random();

	public GeneralManager(String firstName, String lastName){
		this.firstNameManager=firstName;
		this.lastNameManager=lastName;
		this.setscoutingScore(50);
		// this.setFavor(50);
		this.setSalary(500000);
	}

	public Player GeneralManagerPick(Player_Pool PlayerPool) { //General Manager choose from player pool
		Player player1;

		Random ranIndex = new Random();
		int index = ranIndex.nextInt(PlayerPool.getSize());
		player1 = PlayerPool.getPlayerPool().get(index);

		PlayerPool.getPlayerPool().remove(index);
		// System.out.println(PlayerPool.getSize());
		return player1;
	}

	public void printGeneralManager() {

		System.out.print("General Manager: " + firstNameManager + " " + lastNameManager + " , ");
		System.out.print("Scouting Score: " + this.scoutingScore + " , ");
		// System.out.print("Favor: " + this.favor+" , ");
		System.out.println("Salary: $" + this.salary + ". ");

	}

	public int getscoutingScore() {
		return scoutingScore;
	}

	public void setscoutingScore(int scoutingScore) {
		this.scoutingScore = scoutingScore;
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

}
