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
	private int age;

	// -------------------------------------------------------

	// -----------------------------------------------------

	public Player(String firstName, String lastName) {
		
		// sets all the parameters according to the desired distributions
		// abstracted the way the distribution is calculated to make changes easier in later iterations
		this.firstName = firstName;
		this.lastName = lastName;
		this.athleticism = getRandomAthleticismScore();
		this.fit = getRandomFitScore();
		this.salary = getSalary();
		this.age = getRandomAge();
		this.offensive = randomlyAssignOffensive();
		// checks if player is rookie or not
		// sets rookie field accordingly
		if(this.age == 21) 
		{
			rookie = true;
		}
		else
		{
			rookie = false;
		}
	}

	public void printPlayer() {

		System.out.print("Player: " + this.firstName + " " + this.lastName
				+ " , ");
		System.out.print("Fit: " + this.fit + " , ");
		System.out.print("Athleticism: " + this.athleticism + " , ");
		System.out.println("Salary: $" + this.salary + ". ");
		// System.out.println("Rookie: " + this.rookie+" . ");

	}
	
	private int getRandomAthleticismScore()
	{
		// the distribution of athleticism should be one tailed
		// very few players at the top of the scale, a lot of players at the bottom of the scale
		// median has to be 50
		
		athleticism = (int) (50 + Math.sqrt(12) * ran.nextGaussian()); // does this satisfy the criteria ?
		return athleticism;
	}
	
	private int getRandomFitScore()
	{
		// fit score is uniformly distributed from 0 to 100
		
		fit = ran.nextInt(100);
		return fit;
	}
	
	private int getSalary()
	{
		if(this.isRookie())
		{
			return 77000;
		}
		return 100000;
	}
	
	private int getRandomAge()
	{
		// age is uniformly distributed between 21 to 30
		// randomly assign a player a range from this range
		return (int)(Math.random()*(30-21)+21);
	}
	
	private boolean randomlyAssignOffensive()
	{
		/*random = // generate 0 or 1
		if(random == 0)
		{
			return false;
		}
		return true;*/
		Random rand = new Random();
		//Randomly returns true or false
		return rand.nextBoolean();
	}
	
	public boolean isRookie()
	{
		return this.rookie;
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

	public int getSalaryAmount() {
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
