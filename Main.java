import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws Exception {

		// first use the createTeams method to create desired number of teams
		// then start a simulation run
		// each run will have 4 seasons
		// What is supposed to happen at the end of 4 seasons ?

		ArrayList<Season> seasons = new ArrayList<Season>();
		ArrayList<Team> results = new ArrayList<>();
		int numberOfSeasons = 4; // hardcode the value here !

		// after all the teams have been created
		// run the simulation

		for (int i = 0; i < numberOfSeasons; i++) {
			seasons.add(new Season()); // create a list of all the seasons
			// have to add the correct parameters while creating each season

		}

		for (Season s : seasons) {
			s.startSeason();
			s.play();
			results.add(s.seasonResult());
		}

		// the ArrayList<Team> results now stores the final result of every
		// season

		// Constructing the Pools
		Player_Pool PlayerPool = new Player_Pool(10000);
		Coaches_Pool CoachPool = new Coaches_Pool(100);
		GeneralManager_Pool GeneralManagerPool = new GeneralManager_Pool(100);

		double BearsSeasonScore;
		double CardinalsSeasonScore;

		Team seasonVictor;
		boolean thirdGame = false;

		// Constructing the teams
		ArrayList<Team> teams = new ArrayList<Team>();
		Team chicagoBears = new Team("Bears", "Chicago", GeneralManagerPool, CoachPool);
		Team arizonaCardinals = new Team("Cardinals", "Phoenix", GeneralManagerPool, CoachPool);
		Team pittsburghSteelers = new Team("Pittsburgh Steelers", "Pittsburgh", GeneralManagerPool, CoachPool);
		Team bostonPatriots = new Team("Boston Patriots", "Boston", GeneralManagerPool, CoachPool);
		teams.add(chicagoBears);
		teams.add(arizonaCardinals);

		// Determining the players for each team
		PlayerPool.pickAlternator(PlayerPool, teams);
		chicagoBears.setPlayerList();
		arizonaCardinals.setPlayerList();

		Game game1 = new Game();
		Game game2 = new Game();
		Game game3 = new Game();

		game1.gameSimulation(chicagoBears, arizonaCardinals, "Chicago");

		game2.gameSimulation(chicagoBears, arizonaCardinals, "Phoenix");

		BearsSeasonScore = game1.team1Score + game2.team1Score;
		CardinalsSeasonScore = game1.team2Score + game2.team2Score;

		if (BearsSeasonScore > CardinalsSeasonScore) {
			seasonVictor = chicagoBears;
		} else if (CardinalsSeasonScore > BearsSeasonScore) {
			seasonVictor = arizonaCardinals;
		} else {

			game3.gameSimulation(chicagoBears, arizonaCardinals, "Washington");
			seasonVictor = game3.victor;
			thirdGame = true;
		}
		System.out.println("Game 1 Winner: " + game1.victor.getTeamName());
		System.out.println("Bears: " + game1.team1Score + "  Cardinals: " + game1.team2Score);
		game1.subscorePrinter();
		System.out.println("----------------------------------------------------------------------------------------");

		System.out.println("Game 2 Winner: " + game2.victor.getTeamName());
		System.out.println("Bears: " + game2.team1Score + "  Cardinals: " + game2.team2Score);
		game2.subscorePrinter();
		System.out.println("----------------------------------------------------------------------------------------");

		if (thirdGame) {
			System.out.println("Game 3 Winner: " + game3.victor.getTeamName());
			System.out.println("Bears: " + game3.team1Score + "  Cardinals: " + game3.team2Score);
			game3.subscorePrinter();
			System.out.println(
					"----------------------------------------------------------------------------------------");

		}

		System.out.println("Season Winner: " + seasonVictor.getTeamName());
		System.out.println("Season Score: Bears:" + (game1.team1Score + game2.team1Score) + "  Cardinals: "
				+ (game1.team2Score + game2.team2Score));
		System.out.println("----------------------------------------------------------------------------------------");

		chicagoBears.profitCalculator();
		arizonaCardinals.profitCalculator();
		chicagoBears.profitPrinter();
		arizonaCardinals.profitPrinter();

		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("STARTING LINEUP:");
		chicagoBears.printTeam();
		System.out.println("----------------------------------------------------------------------------------------");
		arizonaCardinals.printTeam();
		/*
		 * 
		 * for (int x = 0; x < GeneralManagerPool.getSize(); x++) {
		 * GeneralManagerPool.getGeneralManagerPool().get(x).printCoach(); }
		 * 
		 * for (int x = 0; x < CoachPool.getSize(); x++) {
		 * CoachPool.getCoachesPool().get(x).printCoach();
		 * 
		 * }
		 * 
		 * for (int x = 0; x < PlayerPool.getSize(); x++) {
		 * PlayerPool.getPlayerPool().get(x).printPlayer();
		 * 
		 * }
		 */

		/*
		 * for (int x = 0; x < GMPicksBears.size(); x++) {
		 * 
		 * GMPicksBears.get(x).printPlayer();
		 * 
		 * }
		 * 
		 * 
		 * for (int x = 0; x < GMPicksCardinals.size(); x++) {
		 * 
		 * GMPicksCardinals.get(x).printPlayer();
		 * 
		 * }
		 */

		/*
		 * for (int x = 0; x < CoachPicksBears.size(); x++) {
		 * 
		 * CoachPicksBears.get(x).printPlayer();
		 * 
		 * }
		 * 
		 * for (int x = 0; x < CoachPicksCardinals.size(); x++) {
		 * 
		 * CoachPicksCardinals.get(x).printPlayer();
		 * 
		 * }
		 */

	}

	/*
	 * public static int readInPlayer(){
	 * 
	 * Scanner inputs = new Scanner(System.in); System.out.println(
	 * "Number of Players?"); //Player Num return inputs.nextInt(); }
	 * 
	 * public static int readInCoach(){ Scanner inputs = new Scanner(System.in);
	 * System.out.println("Number of Coaches?"); //Coaches Num return
	 * inputs.nextInt(); }
	 */

	// This method accepts an integer as a parameter and returns a list of
	// the desired number of teams
	public ArrayList<Team> createTeams(int numberOfTeams, ArrayList<GeneralManager> randomListOfGM)
	 {
	 	ArrayList<Team> allTeams = new ArrayList<Team>():
	 	String[] teamNames = new String[32];
	 	String[] teamHometowns = new String[32];
	 	// now iterate over numberOfTeams and create Teams with the name and hometown
	 	for(int i=0; i<numberOfTeams; i++)
	 	{
	 		allTeams.add(new Team(teamNames[i],teamHometowns[i]));
	 	}
	 	// Then assign randomly 1 GM to each of the teams
	 	int c = 0;
	 	for(Team t: allTeams)
	 	{
	 		t.setGM(randomListOfGM(c++));
	 	}
	 	// Let each of the general managers pick 50 players from the player pool. 
	 	// GMs choosing the players will choose one at a time and it will go in rounds
	 	for(int i=0; i<50; i++)
	 	{
	 	for(Team t: allTeams)
	 	{
	 	t.pickNewRandomPlayerForRoster();	
	 	}
	 	}
	 	// From the Pool of coaches each GM will then choose a coach for the team
	 	// Coach selection is in order of City name (alphabetical order)
	 	for(Team t: sortByCityNames(allTeams))
	 	{
	 		t.pickACoach();
	 	}
	 	// Now the team is ready for the season
	 	// the list of all the teams created is in allTeams
	 	
	 	// Now populate each team with 22 starters
	 	for(Team t: allTeams)
	 	{
	 		t.selectStarters(t.getOffensivePlayersInRoster(),t.getDefensivePlayersInRoster());
	 	}
	 	
	 	return allTeams;
	 }

	// method takes a list of teams and sorts them in alpahbetical order of city
	// names (hometowns)
	// return list of teams
	// used to sort teams while selecting coaches for each team
	public ArrayList<Team> sortByCityNames(ArrayList<Team> toSort) {
		// figure out how to sort the teams by hometown
		// compile sorted list in an arraylist<team> type
		// return
	}

}
