import java.util.Random;

public class Game {

	double team1Determinate;
	double team2Determinate;

	double team1Stochiastic;
	double team2Stochiastic;

	double team1advantage;
	double team2advantage;

	double team1Score;
	double team2Score;
	Team team1;
	Team team2;
	Team victor;
	String city;
	
	public Game(Team team1, Team team2)
	{
		this.team1 = team1;
		this.team2 = team2;
		this.city = team1.getHometown(); // since the team mentioned 1st is always the one playing at home
		gameSimulation();
	}

	public void gameSimulation() {
		
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
		team1.updateGrossRevenue();
		
		// if (city == team1.getHometown()) {
		// 	team1advantage = 7;
		// 	team2advantage = 0;
		// 	team1.updateGrossRevenue();
		// } else if (city == team2.getHometown()) {
		// 	team1advantage = 0;
		// 	team2advantage = 7;
		// 	team2.updateGrossRevenue();
		// } else {
		// 	team1advantage = 0;
		// 	team2advantage = 0;
		// }

		team1Score = team1Determinate + team1Stochiastic + team1advantage;
		team2Score = team2Determinate + team2Stochiastic + team2advantage;

		if (team1Score > team2Score) {
			victor = team1;
		} else if (team2Score > team1Score) {
			victor = team2;
		}

	}
	
	public Team play()
	{
		// play the single game set up with team1 and team2
		// check injuries and all the stuff you have to update
		// calculate who wins the game
		// return the victor Team
	}

	public void subscorePrinter() {
		System.out.println(team1.getTeamName() + " Determinate= " + "  "
				+ this.team1Determinate + "  " + team2.getTeamName()
				+ " Determinate= " + this.team2Determinate);
		System.out.println(team1.getTeamName() + " Stochiastic= " + "  "
				+ this.team1Stochiastic + "  " + team2.getTeamName()
				+ " Stochiastic= " + this.team2Stochiastic);
		System.out.println(team1.getTeamName() + " Advantage= " + "  "
				+ this.team1advantage + "  " + team2.getTeamName()
				+ " Advantage= " + this.team2advantage);
	}

}
