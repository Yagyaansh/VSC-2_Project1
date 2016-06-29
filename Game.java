package application;
import java.util.Random;

public class Game {

	private double team1Determinate;
	private double team2Determinate;
	private double team1Stochiastic;
	private double team2Stochiastic;
	private double team1advantage;
	private double team2advantage;
	private double team1Score;
	private double team2Score;
	private Team team1;
	private Team team2;
	private Team victor;
	private String city;
	private boolean isLastGame;

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
		this.team1Stochiastic = 0.0;
		this.team2Stochiastic = 0.0;
		this.team1advantage = 0.0;
		this.team2advantage = 0.0;
		this.team1Score = 0.0;
		this.team2Score = 0.0;
		this.team1 = team1;
		this.team2 = team2;
		this.city = team1.getHometown();
		this.victor = new Team();
		this.isLastGame = false;
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
		this.team1.profitCalculator();
		this.team2.profitCalculator();
		
		// Increase the salary of winning team's coach
		victor.getCoach().increaseSalary();

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
		victor.updateWinningTeamRevenue();
	}

	/*
	 * Determining the victor based on the desired criteria Ties are settled
	 * randomly The victor is returned
	 */
	public Team play() {

		team1Determinate = team1.TeamDeterminateCalculator();
		team2Determinate = team2.TeamDeterminateCalculator();

		Random generator1 = new Random();
		double number1 = generator1.nextGaussian();
		Random generator2 = new Random();
		double number2 = generator2.nextGaussian();
		team1Stochiastic = 10 * number1;
		team2Stochiastic = 10 * number2;

		team1advantage = 7;
		team2advantage = 0;

		team1Score = team1Determinate + team1Stochiastic + team1advantage;
		team2Score = team2Determinate + team2Stochiastic + team2advantage;

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
			Random rand = new Random();
			boolean team1Wins = rand.nextBoolean();
			if (team1Wins) {
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
			if (d >= 0.9) {
				team1.getStarters().get(i).injured();
				if (this.isLastGame()) {
					team1.getStarters().get(i).careerEndingInjury();
				}
			}
		}
		for (int i = 0; i < 22; i++) {
			Random rand = new Random();
			double d = rand.nextDouble();
			if (d >= 0.9) {
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
		System.out.println(
				team1.getTeamName() + ": " + this.team1Score + "   " + team2.getTeamName() + ": " + this.team2Score);
		System.out.println(team1.getTeamName() + " Determinate = " + "  " + this.team1Determinate + "  "
				+ team2.getTeamName() + " Determinate = " + this.team2Determinate);
		System.out.println(team1.getTeamName() + " Stochiastic = " + "  " + this.team1Stochiastic + "  "
				+ team2.getTeamName() + " Stochiastic = " + this.team2Stochiastic);
		System.out.println(team1.getTeamName() + " Advantage = " + "  " + this.team1advantage + "  "
				+ team2.getTeamName() + " Advantage = " + this.team2advantage);
	}

	/*
	 * End of printing methods
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */

}
