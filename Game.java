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

	public Game(Team team1, Team team2) {
		this.team1 = team1;
		this.team2 = team2;
		this.city = team1.getHometown(); // since the team mentioned 1st is
						// always the one playing at home

	}


	// first select the starting team
	// play the game
	// find who the victor is
	// update the injuries for players
	// update the gross revenues for each team
	// return the victor
	public Team runGameSimulation() {
		
		// NEED TO IMPLEMENT ACCORDING TO ABOVE SKELETON
		// all methods are already there - we just need to use them correctly here
		// injuries method is not yet set up !
		
		
		Team victor = this.play(); 
		updateBothTeamRevenues(victor);

		// then update injuries and other relevant results
		return victor;// returns the victor for every game
	}

	public void updateBothTeamRevenues(Team victor) {
		this.team1.updateHomeTeamRevenue(); // team1 is always home team. Update
											// revenue using SMSA
		victor.updateWinningTeamRevenue(); // the team that wins receives
											// additional 100,000. Check if
											// using victor
		// will work here. The team1 or team2 revenue might not update. ****
	}

	public Team play() {
		// play the single game set up with team1 and team2

		// calculate who wins the game
		// return the victor Team

		// calculate each team's determinate Team compoenent
		team1Determinate = team1.TeamDeterminateCalculator();
		team2Determinate = team2.TeamDeterminateCalculator();

		// calculate each team's stochastic Team component
		Random generator1 = new Random();
		double number1 = generator1.nextGaussian();
		Random generator2 = new Random();
		double number2 = generator2.nextGaussian();
		team1Stochiastic = 10 * number1;
		team2Stochiastic = 10 * number2;

		// Field advantage
			team1advantage = 7;
			team2advantage = 0;
		
		// Total team score
		team1Score = team1Determinate + team1Stochiastic + team1advantage;
		team2Score = team2Determinate + team2Stochiastic + team2advantage;

		// Determine the victor
		if (team1Score > team2Score) {
			victor = team1;
		} else if (team2Score > team1Score) {
			victor = team2;
		} else {
			// what if the scores are exactly equal. We have to consider this
			// scenario right ?

			// break ties randomly
			// Pick 0 or 1 randomly.
			// If 0 then team 1 wins
			// If 1 then team 2 wins

			Random rand = new Random();
			boolean team1Wins=rand.nextBoolean();
			if(team1Wins){
				victor=team1;
			}
			else{
				victor=team2;
			}
		}
		return victor; // return the victor
	}

	public void subscorePrinter() {
		System.out.println(team1.getTeamName() + " Determinate= " + "  " + this.team1Determinate + "  "
				+ team2.getTeamName() + " Determinate= " + this.team2Determinate);
		System.out.println(team1.getTeamName() + " Stochiastic= " + "  " + this.team1Stochiastic + "  "
				+ team2.getTeamName() + " Stochiastic= " + this.team2Stochiastic);
		System.out.println(team1.getTeamName() + " Advantage= " + "  " + this.team1advantage + "  "
				+ team2.getTeamName() + " Advantage= " + this.team2advantage);
	}

}
