import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class Player implements Comparable<Player>{

	/*
	 * ACTUALLY WE MIGHT NOT NEED THE POSITION FIELD THE PLAYERS POSITION WILL
	 * KEEP CHANGING THE POSITION SCORES ARRAY WILL HAVE A DISTRIBUTION AND CAN
	 * BE USED TO DECIDE WHERE THE PLAYER WILL BE PLAYING MAKES MORE SENSE TO
	 * HAVE A FIELD CALLED currentPosition do not give it a value here since
	 * this will change from game to game null if the player is not in the
	 * starting line-up implement this TODO : By Yagyaansh. Since I have started
	 * on this already I will complete this.
	 */

	private String firstName;
	private String lastName;
	private int athleticism;
	private double fit;
	private int salary;
	private boolean rookie;
	private boolean injured;
	private static int injuryMean = 2;
	private int injuryDaysRemaining;
	private boolean offensive;
	private Coach coach;
	private int age;
	private boolean careerEndingInjury;
	private int wonderlic;
	private int bestPosition;
	private boolean isInATeam;
	private int[] positionScores;
 	public static final int SECONDARYSCORE = 0;
 	public static final int LINEBACKERSCORE = 1;
 	public static final int DEFENSIVELINESCORE = 2;
 	public static final int OFFENSIVELINESCORE = 3;
 	public static final int RECEIVERSCORE = 4;
 	public static final int RUNNINGBACKSCORE = 5; 
	private Team team; // make the player reference back to the team. If the
						// player is not in a team then set team to null

	/*
	 * Default constructor to initialize a player
	 */

	public Player() {
		this.firstName = "";
		this.lastName = "";
		this.athleticism = getRandomAthleticismScore();
		this.fit = getRandomFitScore();
		//this.salary = calculateSalary();
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
		this.isInATeam = false;
		this.team = null;
	}

	/*
	 * Initializes a player object using first and last name The age field is
	 * used to determine if the player is a rookie or not A number of fields are
	 * randomly determined and assigned as desired
	 */

	public Player(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.athleticism = getRandomAthleticismScore();
		this.fit = getRandomFitScore();
		//this.salary = calculateSalary();
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
		this.isInATeam = false;
		this.team = null;
	}

	/*
	 * Only difference from previous constructor is that age is not randomly
	 * determined It is passed in as a parameter in the constructor
	 */

	public Player(String firstName, String lastName, int age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.athleticism = getRandomAthleticismScore();
		this.fit = getRandomFitScore();
		//this.salary = calculateSalary();
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
		this.isInATeam = false;
		this.team = null;
	}

	/*
	 * Initializes a player with a first name and a last name Takes in the type
	 * of the player - Offensive of Defensive as a boolean True - offensive
	 * False - defensive passed as 3rd parameter when a player is being created
	 */

	public Player(String firstName, String lastName, boolean offensive) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.athleticism = getRandomAthleticismScore();
		this.fit = getRandomFitScore();
		//this.salary = calculateSalary();
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
		this.isInATeam = false;
		this.team = null;
	}

	public Player(String firstName, String lastName, int age, boolean offensive) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.athleticism = getRandomAthleticismScore();
		this.fit = getRandomFitScore();
		//this.salary = calculateSalary();
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
		this.isInATeam = false;
		this.team = null;
	}

	/*
	 * Printing methods
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */

	public void printPlayer() {

		System.out.print("Player: " + this.firstName + " " + this.lastName + " , ");
		System.out.printf("Fit: %.1f, ", this.fit, " , ");
		System.out.print("Athleticism: " + this.athleticism + " , ");
		if(this.injuryDaysRemaining>0){
			System.out.print("Weeks Remaining Unitl Healed: " + this.getWeekRemaining() + " , ");
		}
		System.out.println("Salary: $" + moneyFormatter(this.salary) + ". ");
	}

	/*
	 * End of printing methods
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */

	/*
	 * Random values are determined using the required distributions
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */

	/*
	 * Athleticism score is determined using a one tailed distribution median of
	 * the distribution has to be 50
	 */
	private int getRandomAthleticismScore() {
		Random rand = new Random();
		athleticism = (int) (50 + Math.sqrt(12) * rand.nextGaussian());
		return athleticism;
	}

	/*
	 * Fit score is determined using a uniform distribution form 0 to 100
	 */
	private double getRandomFitScore() {
		Random rand = new Random();
		fit = rand.nextInt(101);
		return fit;
	}
	//Sets the coach, called in main
	public void setCoach(Coach c){
		this.coach = c;
	}

	public int calculateSalary() {
		/*
		 * TODO : 
		 * Verification
		 * I changed the salary factor by 1/100 and subtracted $30,000
		 * The player salary was ridiculous
		 */
		int salary = (int) (this.age + (100 - Math.abs(this.fit - this.coach.getScheme()) + this.athleticism) * 1000) - 30000;
		this.salary = salary;
		return salary;
	}
	
	public int getSalary()
	{
		return salary;
	}

	/*
	 * Determined the Age randomly Follows a uniform distribution between 21 and
	 * 35
	 */
	private int getRandomAge() {
		Random rand = new Random();
		return (rand.nextInt(15) + 21);
	}

	/*
	 * Each player is randomly assigned either an offensive role or a defensive
	 * role
	 */
	private boolean randomlyAssignOffensive() {
		Random rand = new Random();
		return rand.nextBoolean();
	}

	// public String generateRandomPosition()
	// {
	// // Depending on whether the player has been assigned offensive or
	// defensive type
	// // assign the player any one of the 3 available positions in that type
	// // assign the player that position as a string
	// // the standard is to use all lower case letters in the string (no
	// spaces), and the positions are :
	// // Defensive - secondary, linebacker, defensiveline
	// // Offensive - offensiveline, receiver, runningback

	// // generate a random number between (and including 0-2)
	// // check whether player is offensive or defensive type
	// // assign as follows :
	// // Defensive - secondary (0), linebacker (1), defensiveline (2)
	// // Offensive - offensiveline (0), receiver (1), runningback (2)
	// }

	public int[] assignPositionScores() {
		
		ArrayList<Integer> values = new ArrayList<>();
		values.add(100);
		values.add(67);
		values.add(33);
		this.positionScores = new int[6];

		if (this.isOffensive()) {
			// set them randomly to high medium and low
			// the others were initialized to 0 so no change is needed

			Random rand = new Random();
			int x = rand.nextInt(values.size());
			positionScores[OFFENSIVELINESCORE] = values.get(x);
			values.remove(x);
			if (positionScores[OFFENSIVELINESCORE] == 100) {
				this.bestPosition = OFFENSIVELINESCORE;
				positionScores[RUNNINGBACKSCORE] = 67;
				positionScores[RECEIVERSCORE] = 33;
			} else if (positionScores[OFFENSIVELINESCORE] == 67) {
				positionScores[RUNNINGBACKSCORE] = 100;
				this.bestPosition = RUNNINGBACKSCORE;
				positionScores[RECEIVERSCORE] = 33;
			} else {
				x = rand.nextInt(values.size());
				positionScores[RECEIVERSCORE] = values.get(x);
				if (positionScores[RECEIVERSCORE] == 100)
					this.bestPosition = RECEIVERSCORE;
				else
					this.bestPosition = RUNNINGBACKSCORE;
				values.remove(x);
				positionScores[RUNNINGBACKSCORE] = values.get(0);
				values.remove(0);
			}
		} else {
			// set them randomly to high medium and low
			// the others were initialized to 0 so no change is needed

			Random rand = new Random();
			int x = rand.nextInt(values.size());
			positionScores[DEFENSIVELINESCORE] = values.get(x);
			values.remove(x);
			if (positionScores[DEFENSIVELINESCORE] == 100) {
				this.bestPosition = DEFENSIVELINESCORE;
				positionScores[SECONDARYSCORE] = 33;
				positionScores[LINEBACKERSCORE] = 67;
			} else if (positionScores[DEFENSIVELINESCORE] == 67) {
				positionScores[LINEBACKERSCORE] = 100;
				this.bestPosition = LINEBACKERSCORE;
				positionScores[SECONDARYSCORE] = 33;
			} else {
				x = rand.nextInt(values.size());
				positionScores[SECONDARYSCORE] = values.get(x);
				if (positionScores[SECONDARYSCORE] == 100)
					this.bestPosition = SECONDARYSCORE;
				else
					this.bestPosition = LINEBACKERSCORE;
				values.remove(x);
				positionScores[LINEBACKERSCORE] = values.get(0);
				values.remove(0);
			}

		}

		return positionScores;

	}

	/*
	 * End of determining the required distributions
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */

	/*
	 * Getters, setters and update helpers for the fields in the class
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */
	
	public static int getInjuryMean()
	{
		return injuryMean;
	}
	public static void setInjuryMean(int weeks)
	{
		injuryMean = weeks;
	}

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

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public void setRookie(boolean set) {
		this.rookie = set;
	}

	public boolean getRookie() {
		return this.rookie;
	}

	public int getAge() {
		return this.age;
	}
	
	public int getWeekRemaining(){
		return this.injuryDaysRemaining;
	}

	public boolean isInjured() {
		return this.injured;
	}
	

	public void injured() {
		Random rand = new Random();
		this.injuryDaysRemaining = (injuryMean + (Math.abs((int)(rand.nextGaussian()))));
		this.injured = true;
	}

	public void careerEndingInjury() {
		this.careerEndingInjury = true;
	}

	public void heal() {
		if(this.injuryDaysRemaining > 1)
			this.injuryDaysRemaining--;
		else{
			this.injured = false;
			this.injuryDaysRemaining = 0;
		}
	}

	public boolean isOffensive() {
		return this.offensive;
	}

	public void increaseAge() {
		this.age++;
		this.athleticism = this.athleticism - 2;
		if (this.age > 21) {
			this.rookie = false;
		}
	}

	public void updateFit(Coach c) {
		double playerFit = this.getFit();
		int coachScheme = c.getScheme();
		double teachingFactor = c.getTeachingFactor();

		if (Math.abs(playerFit - coachScheme) <= teachingFactor) {
			this.fit = coachScheme;
		} else if (playerFit > coachScheme) {
			this.fit = this.fit - teachingFactor;
		} else {
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
		// this.fit = playerFit;

	}

	public boolean isEndedCareer() {
		return careerEndingInjury;
	}

	public int[] getPositionScores() {
		return this.positionScores;
	}
	
	public void setPositionScores(int[] positionScoreAdd) {
		if(positionScoreAdd.length != 6){
			System.out.println("positionScore length should be size");
		}
		for(int x = 0; x < positionScoreAdd.length; x++){
			this.positionScores[x] = positionScoreAdd[x];
		}
	}

	public int getBestPosition() {
		return this.bestPosition;
	}

	public int getWonderlic() {
		return wonderlic;
	}

	public void setWonderlic(int wonderlic) {
		this.wonderlic = wonderlic;
	}

	public boolean isInATeam() {
		return this.isInATeam;
	}

	public void setIsInATeam(boolean val) {
		this.isInATeam = val;
	}

	/*
	 * Sets the player to a particular team A way of referencing the team from
	 * the Player object
	 */

	public void setTeam(Team t) {
		this.team = t;
	}

	/*
	 * Returns the team to which the player belongs If the player does not
	 * belong to a team then method returns null
	 */

	public Team getTeam() {
		return this.team;
	}

	/*
	 * End of Getters, Setters and update methods -- they are mostly self
	 * explanatory
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */
	
	public String moneyFormatter(int number){
		String convertedString = new DecimalFormat("#,###").format(number);
		return convertedString;
	}

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

	@Override
	public int compareTo(Player p) {
		if(this.getPositionScores()[this.getBestPosition()] == p.getPositionScores()[p.getBestPosition()])
		{
			if(this.getAthleticism() == p.getAthleticism())
			{
				if(this.getWonderlic() == p.getWonderlic())
				{
					Random rand = new Random();
					return ((rand.nextInt(2)*2) - 1);
				}
				return (this.getWonderlic() - p.getWonderlic());
			}
			return (this.getAthleticism() - p.getAthleticism());
		}
		return (this.getPositionScores()[this.getBestPosition()] - p.getPositionScores()[p.getBestPosition()]);
	}

}
