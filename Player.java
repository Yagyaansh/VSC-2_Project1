import java.util.Random;

public class Player {

	private int fit;
	private int athleticism;
	private int salary;
	private boolean rookie;
	private Random ran = new Random();
	private String firstName;
	private String lastName;
	private boolean injured;
	private boolean offensive;

	// -------------------------------------------------------

	// -----------------------------------------------------

	public Player(String firstName, String lastName) {

		this.firstName=firstName;
		this.lastName=lastName;
		fit = ran.nextInt(100);
		athleticism = (int) (50 + Math.sqrt(12) * ran.nextGaussian());
		rookie = true;

		/*
		 * if(rookie){ setSalary(70000); }else{ setSalary(100000); }
		 */
		setSalary(100000);

	}

	public void printPlayer() {

		System.out.print("Player: " + this.firstName + " " + this.lastName
				+ " , ");
		System.out.print("Fit: " + this.fit + " , ");
		System.out.print("Athleticism: " + this.athleticism + " , ");
		System.out.println("Salary: $" + this.salary + ". ");
		// System.out.println("Rookie: " + this.rookie+" . ");

	}

	public int getAthleticism() {
		return athleticism;
	}

	public void setAthleticism(int athleticism) {
		this.athleticism = athleticism;
	}

	public int getFit() {
		return fit;
	}

	public void setFit(int fit) {
		this.fit = fit;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public void setRookie(boolean set) {
		this.rookie = set;
	}

	public boolean getRookie() {
		return this.rookie;
	}
	
	// this method is used to check if the player is injured
	public boolean isInjured()
	{
		return this.injured;
	}
	
	// this method is used to check if the player is offensive or defensive type
	public boolean isOffensive()
	{
		return this.offensive;
	}

}
