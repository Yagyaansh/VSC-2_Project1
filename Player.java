package application;
import java.util.ArrayList;
import java.util.Random;

public class Player {
	
	/*
	* ACTUALLY WE MIGHT NOT NEED THE POSITION FIELD
	* THE PLAYERS POSITION WILL KEEP CHANGING
	* THE POSITION SCORES ARRAY WILL HAVE A DISTRIBUTION AND CAN BE USED TO DECIDE WHERE THE PLAYER WILL BE PLAYING
	* MAKES MORE SENSE TO HAVE A FIELD CALLED currentPosition
	* do not give it a value here since this will change from game to game
	* null if the player is not in the starting line-up
	* implement this
	* TODO : By Yagyaansh. Since I have started on this already I will complete this. 
	*/ 

	private String firstName;
	private String lastName;
	private int athleticism;
	private double fit;
	private int salary;
	private boolean rookie;
	private boolean injured;
	private boolean offensive;
	private int age;
	private boolean careerEndingInjury;
	private PlayerPositionStats positionScores;
	private int wonderlic;
	private String bestPosition;
	private boolean isInATeam;
	private Team team; // make the player reference back to the team. If the player is not in a team then set team to null

	/*
	 * Default constructor to initialize a player
	 */
	
	public Player() 
	{
		this.firstName = "";
		this.lastName = "";
		this.athleticism = getRandomAthleticismScore();
		this.fit = getRandomFitScore();
		this.salary = getSalary();
		this.injured = false;
		this.offensive = randomlyAssignOffensive();
		this.age = getRandomAge();
		this.careerEndingInjury = false;
		if (this.age == 21) {
			rookie = true;
		} else {
			rookie = false;
		}
		
		wonderlic = 0;
		this.positionScores = assignPositionScores();
		//this.bestPosition = getBestPosition();
		this.isInATeam = false;
		this.team = null;
	}

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
		this.injured = false;
		this.offensive = randomlyAssignOffensive();
		this.age = getRandomAge();
		this.careerEndingInjury = false;
		if (this.age == 21) {
			rookie = true;
		} else {
			rookie = false;
		}
		
		wonderlic = 0;
		this.positionScores = assignPositionScores();
		//this.bestPosition = getBestPosition();
		this.isInATeam = false;
		this.team = null;
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
		this.injured = false;
		this.offensive = randomlyAssignOffensive();
		this.age = age;
		this.careerEndingInjury = false;
		if (this.age == 21) {
			rookie = true;
		} else {
			rookie = false;
		}
		
		wonderlic = 0;	
		this.positionScores = assignPositionScores();
		//this.bestPosition = getBestPosition();
		this.isInATeam = false;
		this.team = null;
	}
	
	/*
	 * Initializes a player with a first name and a last name
	 * Takes in the type of the player - Offensive of Defensive as a boolean
	 * True - offensive
	 * False - defensive
	 * passed as 3rd parameter when a player is being created
	 */
	
	public Player(String firstName, String lastName, boolean offensive) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.athleticism = getRandomAthleticismScore();
		this.fit = getRandomFitScore();
		this.salary = getSalary();
		this.injured = false;
		this.offensive = offensive;
		this.age = getRandomAge();
		this.careerEndingInjury = false;
		if (this.age == 21) {
			rookie = true;
		} else {
			rookie = false;
		}
		
		wonderlic = 0;
		this.positionScores = assignPositionScores();
		this.bestPosition = getBestPosition();
		this.isInATeam = false;
		this.team = null;
	}
	
	public Player(String firstName, String lastName, int age, boolean offensive) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.athleticism = getRandomAthleticismScore();
		this.fit = getRandomFitScore();
		this.salary = getSalary();
		this.injured = false;
		this.offensive = offensive;
		this.age = age;
		this.careerEndingInjury = false;
		if (this.age == 21) {
			rookie = true;
		} else {
			rookie = false;
		}
		
		wonderlic = 0;	
		this.positionScores = assignPositionScores();
		this.bestPosition = getBestPosition();
		this.isInATeam = false;
		this.team = null;
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
		Random rand = new Random ();
		athleticism = (int) (50 + Math.sqrt(12) * rand.nextGaussian()); 
		return athleticism;
	}
	
	/*
	 * Fit score is determined using a uniform distribution form 0 to 100
	 */
	private double getRandomFitScore() {
		Random rand = new Random ();
		fit = rand.nextInt(100);
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
		return (int) (Math.random() * (35 - 21) + 21);
	}

	/*
	 * Each player is randomly assigned either an offensive role or a defensive role
	 */
	private boolean randomlyAssignOffensive() {
		Random rand = new Random();
		return rand.nextBoolean();
	}
	
	// public String generateRandomPosition()
	// {
	// 	// Depending on whether the player has been assigned offensive or defensive type
	// 	// assign the player any one of the 3 available positions in that type
	// 	// assign the player that position as a string
	// 	// the standard is to use all lower case letters in the string (no spaces), and the positions are : 
	// 	// Defensive - secondary, linebacker, defensiveline
	// 	// Offensive - offensiveline, receiver, runningback
		
	// 	// generate a random number between (and including 0-2)
	// 	// check whether player is offensive or defensive type
	// 	// assign as follows : 
	// 	// Defensive - secondary (0), linebacker (1), defensiveline (2)
	// 	// Offensive - offensiveline (0), receiver (1), runningback (2)
	// }
	
	public PlayerPositionStats assignPositionScores()
	{
		// assign player scores for all positions randomly
		PlayerPositionStats stats = new PlayerPositionStats();
		
		ArrayList<Integer> values = new ArrayList<>();
		values.add(100); values.add(67); values.add(33);
		
		if(this.isOffensive())
		{
			// set them randomly to high medium and low
			// the others were initialized to 0 so no change is needed
			
			Random rand = new Random();
			int x = rand.nextInt(values.size());
			stats.setOffensivelineScore(values.get(x));
			values.remove(x);
			x = rand.nextInt(values.size());
			stats.setReceiverScore(values.get(x));
			values.remove(x);
			stats.setRunningbackScore(values.get(0));
			values.remove(0);
		}
		else
		{
			// set them randomly to high medium and low
			// the others were initialized to 0 so no change is needed
			
			Random rand = new Random();
			int x = rand.nextInt(values.size());
			stats.setSecondaryScore(values.get(x));
			values.remove(x);
			x = rand.nextInt(values.size());
			stats.setLinebackerScore(values.get(x));
			values.remove(x);
			stats.setDefensivelineScore(values.get(0));
			values.remove(0);
		}
		
		return stats;
		
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

	public double getFit() {
		return fit;
	}

	public void setFit(int fit) {
		this.fit = fit;
	}

	public int getSalaryAmount(Coach c) {
		int salary= (int)(this.age+(100-Math.abs(this.fit-c.getScheme()) + this.athleticism)*100000);
		this.salary=salary;
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
	
	public String getName() {
		return this.firstName + " " + this.lastName;
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
		this.athleticism=this.athleticism-2;
		if(this.age>21){
			this.rookie=false;
		}
	}
	
	public void updateFit(Coach c) {
		double playerFit = this.getFit();
		int coachScheme = c.getScheme();
		double teachingFactor = c.getTeachingFactor();
		
		if(Math.abs(playerFit - coachScheme) <= teachingFactor) {
			this.fit = coachScheme;
		}
		else if(playerFit > coachScheme) {
			this.fit = this.fit - teachingFactor;
		}
		else {
			this.fit = this.fit + teachingFactor;
		}
		
	
		// if(Math.abs(playerFit - coachScheme) <= 5)
		// {
		// playerFit = coachScheme;
		// }
		// else if(playerFit > coachScheme)
		// {
		// playerFit -= 5;
		// }
		// else
		// {
		// playerFit += 5;
		// }
		//this.fit = playerFit;

		}

	public boolean isEndedCareer() 
	{
		return careerEndingInjury;
	}
	
	public PlayerPositionStats getPositionScores() {
		return this.positionScores;
	}

	public void setPositionScores(PlayerPositionStats positionScores) {
		this.positionScores = positionScores;
	}
	
	public String getBestPosition() {
		return this.positionScores.getBestPosition();
	}

	public int getWonderlic() {
		return wonderlic;
	}

	public void setWonderlic(int wonderlic) {
		this.wonderlic = wonderlic;
	}
	
	public boolean isInATeam()
	{
		return this.isInATeam;
	}
	
	public void setIsInATeam(boolean val)
	{
		this.isInATeam = val;
	}
	
	/*
	* Sets the player to a particular team
	* A way of referencing the team from the Player object
	*/
	
	public void setTeam(Team t)
	{
		this.team = t;
	}
	
	/*
	* Returns the team to which the player belongs
	* If the player does not belong to a team then 
	* method returns null
	*/
	
	public Team getTeam()
	{
		return this.team;
	}

	
	/*
	 * End of Getters, Setters and update methods -- they are mostly self explanatory
	 * -------------------------------------------------------------------------------------------------
	 */
	


	public Player deepCopy() {
		Player p = new Player();
		p.fit = this.fit;
		p.athleticism = this.athleticism;
		p.salary = this.salary;
		p.rookie = this.rookie;
		p.firstName = this.firstName;
		p.lastName = this.lastName;
		p.injured = this.injured;
		p.offensive = this.offensive;
		p.age = this.age;
		return p;
	}

}
