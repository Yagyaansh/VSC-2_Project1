import java.util.Random;

public class Game {

	private double team1Determinate;
	private double team2Determinate;
	private double team1Stochastic;
	private double team2Stochastic;
	private static double team1advantage = 7;
	private static double injuryRate = .9;
	private static double stochStdev = 4;
	private int team2advantage;
	private double team1Score;
	private double team2Score;
	private Team team1;
	private Team team2;
	private Team victor;
	private String city;
	private boolean isLastGame;
//	int intHomeFieldAdvantage;

	//Deep Copy for printing out purposes
	private Team tempTeam1;
	private Team tempTeam2;

	/*
	 * Initializes a game object using 2 classes team 1 is always at home input
	 * is provided in a way to make sure that every team plays home and away
	 */
	public Game(Team team1, Team team2) {
		this.team1Determinate = 0.0;
		this.team2Determinate = 0.0;
		this.team1Stochastic = 0.0;
		this.team2Stochastic = 0.0;
		this.team1Score = 0.0;
		this.team2Score = 0.0;
		this.team1 = team1;
		this.team2 = team2;
		this.city = team1.getHometown();
		this.victor = new Team();
		this.isLastGame = false;
		this.team2advantage = 0;
	}

	/*
	 * the two teams first select their starters for the game the game is played
	 * and the victor is determined all players currently injured are healed
	 * since all players heal after one game then players are randomly injured
	 * the revenues are updated and the profits are calculated and printed the
	 * victor of the game is returned to the calling object
	 */
	public Team runGameSimulation() {

		team1.selectStarters();
		team2.selectStarters();

		Team victor = this.play();
		this.injuriesHealed();
		this.causeInjuries();

		updateBothTeamRevenues(victor);
		this.team1.updateHomeTeamExpenses();
		this.team2.updateAwayTeamExpenses();
		
		this.team1.updateProfit();
		this.team2.updateProfit();

		// A copy to refer to later
		this.tempTeam1 = team1.deepCopy();
		this.tempTeam2 = team2.deepCopy();

		return victor;
	}

	/*
	 * The revenues of each team are updated according to home team and victor
	 * team
	 */
	public void updateBothTeamRevenues(Team victor) {
		this.team1.updateHomeTeamRevenue();
		this.team2.updateAwayTeamRevenue();
		victor.updateWinningTeamRevenue();
	}

	/*
	 * Determining the victor based on the desired criteria Ties are settled
	 * randomly The victor is returned
	 */
	public Team play() {

		team1Determinate = team1.TeamDeterminateCalculator();
		team2Determinate = team2.TeamDeterminateCalculator();
		
		team1Determinate = team1Determinate/175;
		team2Determinate = team2Determinate/175;
		
		team1Determinate = 7*Math.round(team1Determinate);
		team2Determinate = 7*Math.round(team2Determinate);

		Random generator1 = new Random();
		
		team1Stochastic = Math.round(stochStdev * generator1.nextGaussian());
		team2Stochastic = Math.round(stochStdev * generator1.nextGaussian());
		
		team1Stochastic = Math.abs(3*team1Stochastic);
		team2Stochastic = Math.abs(3*team2Stochastic);

		team1Score = team1Determinate + team1Stochastic + team1advantage;
		team2Score = team2Determinate + team2Stochastic + team2advantage;

		if (team1Score > team2Score) {
			victor = team1;
			team1.incrementWins();
			team2.incrementLosses();
		} else if (team2Score > team1Score) {
			victor = team2;
			team1.incrementLosses();
			team2.incrementWins();
		} else {
			// when we can have draws then just increment the number of draws for both teams
			if (generator1.nextBoolean()) {
				victor = team1;
				team1.incrementWins();
				team2.incrementLosses();
			} else {
				victor = team2;
				team1.incrementLosses();
				team2.incrementWins();
			}
		}
		return victor;
	}

	/*
	 * PLayers are randomly injured after the game
	 */
	public void causeInjuries() {
		for (int i = 0; i < 22; i++) {
			Random rand = new Random();
			double d = rand.nextDouble();
			if (d >= injuryRate) {
				team1.getStarters().get(i).injured();
				if (this.isLastGame()) {
					team1.getStarters().get(i).careerEndingInjury();
				}
			}
			d = rand.nextDouble();
			if (d >= injuryRate) {
				team2.getStarters().get(i).injured();
				if (this.isLastGame()) {
					team2.getStarters().get(i).careerEndingInjury();
				}
			}
		}
	}

	/*
	 * Checks if the current game bein played is the last game of the season
	 */
	public boolean isLastGame() {
		return this.isLastGame;
	}

	/*
	 * All players who are currently injured will be healed This is because the
	 * current requirement is that every player is healed after being injured
	 * for one game
	 */
	public void injuriesHealed() {
		for (Player p : team1.getRoster()) {
			p.heal();
		}
		for (Player p : team2.getRoster()) {
			p.heal();
		}
	}

	/*
	 * Getters and setters for the fields in the class
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */
	
	public static void setTeam1Advantage(double homeFieldAdvantage){
		//Advantage of 7 for home team (team 1)
		
		System.out.println(homeFieldAdvantage);

		team1advantage = homeFieldAdvantage;

	}
	
	public static void setStochStdev(int stdev)
	{
		stochStdev = stdev;
	}
	
	public static void setInjuryRate(double injRate)
	{
		injuryRate = injRate;
	}
	
	private static double getInjuryRate()
	{
		return injuryRate;
	}
	
	public static double getTeam1Advantage(){
		return team1advantage;
	}
	
	public double getTeam1Score()
	{
		return this.team1Score;
	}
	
	public double getTeam2Score()
	{
		return this.team2Score;
	}

	public Team getVictor() {
		return this.victor;
	}

	public Team getTeam1() {
		return this.team1;
	}

	public Team getTeam2() {
		return this.team2;
	}
	
	public Team getTempTeam1() {
		return this.tempTeam1;
	}

	public Team getTempTeam2() {
		return this.tempTeam2;
	}

	/*
	 * End of Getters and Setters
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */

	/*
	 * Printing methods
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */

	public void printGame() {
		if(this.team1advantage>0){
			System.out.println(team1.getTeamName() + "@Home");
		}
		else{
			System.out.println(team1.getTeamName() + "@Away");
		}
		System.out.println(
				team1.getTeamName() + " Score: " + this.team1Score + "   " + team2.getTeamName() + " Score: " + this.team2Score);
		System.out.println(team1.getTeamName() + " Determinate  = " + "  " + this.team1Determinate + "  "
				+ team2.getTeamName() + " Determinate = " + this.team2Determinate);
		System.out.println(team1.getTeamName() + " Stochastic      = " + "  " + this.team1Stochastic + "  "
				+ team2.getTeamName() + " Stochastic = " + this.team2Stochastic);
		System.out.println(team1.getTeamName() + " Advantage     = " + "  " + this.team1advantage + "  "
				+ team2.getTeamName() + " Advantage =   " + this.team2advantage);
	}

	/*
	 * End of printing methods
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */

}
