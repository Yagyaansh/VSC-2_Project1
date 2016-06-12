import java.util.Random;

public class Player {

	private String firstName;
	private String lastName;
	private int athleticism;
	private int fit;
	private int salary;
	private boolean rookie;
	private Random ran;
	private boolean injured;
	private boolean offensive;
	private int age;
	private boolean careerEndingInjury;


	/*
	 * Initializes a player object using first and last name
	 * The age field is used to determine if the player is a rookie or not
	 * A number of fields are randomly determined and assigned as desired
	 */

	public Player(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.athleticism = getRandomAthleticismScore();
		this.fit = getRandomFitScore();
		this.salary = getSalary();
		this.ran = new Random();
		this.injured = false;
		this.offensive = randomlyAssignOffensive();
		this.age = getRandomAge();
		this.careerEndingInjury = false;
		if (this.age == 21) {
			rookie = true;
		} else {
			rookie = false;
		}
	}
	
	/*
	 * Only difference from previous constructor is that age is not randomly determined
	 * It is passed in as a parameter in the constructor
	 */
	
	public Player(String firstName, String lastName, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.athleticism = getRandomAthleticismScore();
		this.fit = getRandomFitScore();
		this.salary = getSalary();
		this.ran = new Random();
		this.injured = false;
		this.offensive = randomlyAssignOffensive();
		this.age = age;
		this.careerEndingInjury = false;
		if (this.age == 21) {
			rookie = true;
		} else {
			rookie = false;
		}
	}
	
	
	/*
	 * Printing methods
	 * -------------------------------------------------------------------------------------------------
	 */
	

	public void printPlayer() {

		System.out.print("Player: " + this.firstName + " " + this.lastName + " , ");
		System.out.print("Fit: " + this.fit + " , ");
		System.out.print("Athleticism: " + this.athleticism + " , ");
		System.out.println("Salary: $" + this.salary + ". ");
	}
	

	/*
	 * End of printing methods
	 * -------------------------------------------------------------------------------------------------
	 */
	

	/*
	 * Random values are determined using the required distributions
	 * -------------------------------------------------------------------------------------------------
	 */

	/*
	 * Athleticism score is determined using a one tailed distribution
	 * median of the distribution has to be 50
	 */
	private int getRandomAthleticismScore() {
		athleticism = (int) (50 + Math.sqrt(12) * ran.nextGaussian()); 
		return athleticism;
	}
	
	/*
	 * Fit score is determined using a uniform distribution form 0 to 100
	 */
	private int getRandomFitScore() {
		fit = ran.nextInt(100);
		return fit;
	}
	
	/*
	 * Determines the salary of the player - depending on the rookie field
	 */
	private int getSalary() {
		if (this.isRookie()) {
			return 77000;
		}
		return 100000;
	}

	/*
	 * Determined the Age randomly
	 * Follows a uniform distribution between 21 and 30
	 */
	private int getRandomAge() {
		return (int) (Math.random() * (30 - 21) + 21);
	}

	/*
	 * Each player is randomly assigned either an offensive role or a defensive role
	 */
	private boolean randomlyAssignOffensive() {
		Random rand = new Random();
		return rand.nextBoolean();
	}
	
	/*
	 * End of determining the required distributions
	 * -------------------------------------------------------------------------------------------------
	 */
	
	
	/*
	 * Getters, setters and update helpers for the fields in the class
	 * -------------------------------------------------------------------------------------------------
	 */
	

	public boolean isRookie() {
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
	
	public int getAge(){
		return this.age;
	}

	public boolean isInjured() {
		return this.injured;
	}
	
	public void injured(){
		this.injured = true;
	}
	
	public void careerEndingInjury()
	{
		this.careerEndingInjury = true;
	}
	
	public void heal(){
		this.injured = false;
	}

	public boolean isOffensive() {
		return this.offensive;
	}

	public void increaseAge() {
		this.age++;
		if(this.age>21){
			this.rookie=false;
		}
	}
	
	public void updateFit(int coachScheme) {
		
		int playerFit = this.getFit();
		
		if(Math.abs(playerFit - coachScheme) <= 5)
		{
			playerFit = coachScheme;
		}
		else if(playerFit > coachScheme)
		{
			playerFit -= 5;
		}
		else
		{
			playerFit += 5;
		}
		
		this.fit = playerFit;

		}

	public boolean isEndedCareer() 
	{
		return careerEndingInjury;
	}
	
	/*
	 * End of Getters, Setters and update methods -- they are mostly self explanatory
	 * -------------------------------------------------------------------------------------------------
	 */

}
