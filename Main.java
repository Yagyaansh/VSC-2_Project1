import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

	/*
	 * README :
	 * 
	 * All three pools are created here. Instead of using the main it is easier
	 * to create an instance of the Main class
	 * 
	 * The start method is basically the entire main method we has earlier The
	 * skeleton description is as follows :
	 * 
	 * The generalized create teams method us used for creating the teams. The
	 * names of the teams have to be hard-coded in any way so I hard-coded them
	 * in alphabetical order The method creates a list of teams Each team is
	 * assigned a GM (randomly) The GM then randomly picks a coach for the team
	 * The GM then picks players, taking turns as desired. This can handle any
	 * number of teams so we don't have to make changes The roster for each team
	 * is then divided into an offensiveRoster and a defensiveRoster A list of
	 * all the teams is then returned by the method createTeams() Then all the
	 * seasons are created using the Season class The seasons are added to a
	 * list The number of seasons is hard coded since the number should
	 * not change too much Each season is then simulated and the results are
	 * stored in a list called results This stores all the final results for the
	 * simulation
	 * 
	 * 
	 * 
	 * Hardcode the pool sizes in the getters This value is called numerous
	 * times throughout the code (not just this class)
	 * 
	 */
	private GeneralManager_Pool generalManagerPool;
	private Coach_Pool coachPool;
	private Player_Pool playerPool;
	private ArrayList<Team> teams;

	/*
	 * Constructor - hard-coded with the sizes of the pools Change pool size
	 * here if needed
	 */

	public Main() {
		this.generalManagerPool = new GeneralManager_Pool(getGMPoolSize());
		this.coachPool = new Coach_Pool(getCoachPoolSize());
		this.playerPool = new Player_Pool(getPlayerPoolSize());
		
		int numberOfTeams = 16;
		this.teams = new ArrayList<Team>();
		this.createTeams(numberOfTeams, getGeneralManagerPool(), getCoachPool(), getPlayerPool());
	}

	/*
	 * Only creates an object called m1 and then runs the simulation. Code
	 * begins here.
	 */
	public static void main(String[] args) {
		Main m1 = new Main();
		try {
			m1.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Performs all the functionalities that the older main method did
	 */

	public void start() throws Exception {

		int numberOfTeams = 16;
		ArrayList<Season> seasons = new ArrayList<Season>();
		ArrayList<Team> results = new ArrayList<Team>();
		int numberOfSeasons = 4;

		for (int i = 0; i < numberOfSeasons; i++) {
			seasons.add(new Season(this.teams));
		}

	//	for (Season s : seasons) {
		for(int i=0; i<seasons.size(); i++) {
			Season s = seasons.get(i);
			boolean isFirstSeason = false;
			if(i==0) {
				isFirstSeason= true;
			}
			s.startSeason();
			s.play();
			results.add(s.seasonResult());
			s.offSeason(this.getPlayerPool(), this.getCoachPool(), isFirstSeason);
		}
		printInputs(numberOfTeams, numberOfSeasons);
		printOutputs(seasons);
	}

	/*
	 * Generalizes team creation Assigns GM, Coach, Players to roster, and
	 * divides into offensive and devensive rosters. returns a list of teams
	 * participating in the simulation
	 */

	public void createTeams(int numberOfTeams, GeneralManager_Pool generalManagerPool,
			Coach_Pool coachPool, Player_Pool playerPool) {
		String[] teamNames = { "Atlanta Falcons", "Baltimore Ravens", "Carolina Panthers", "Chicago Bears",
				"Cincinnati Bengals", "Cleveland Browns", "Detroit Lions", "Green Bay Packers", "Houston Texans",
				"Indianapolis Colts", "Jacksonville Jaguars", "Minnesota Vikings", "Tennessee Titans",
				"New Orleans Saints", "Pittsburgh Steelers", "Tampa Bay Buccaneers", "T" };
		String[] teamHometowns = { "Atlanta", "Baltimore", "Charlotte", "Chicago", "Cincinnati", "Cleveland", "Detroit",
				"Green Bay", "Houston", "Indianapolis", "Jacksonville", "Minneapolis", "Nashville", "New Orleans",
				"Pittsburgh", "Tampa" };

		for (int i = 0; i < numberOfTeams; i++) {
			Team t = new Team(teamNames[i], teamHometowns[i], generalManagerPool.getRandomGM());
			Coach coach = t.getGM().pickACoach(coachPool);
			t.setCoach(coach);
			this.teams.add(t);
		}
		int rosterSize = 50;
		int numberOfPositions = 6;
		this.fillAllTeamRosters(rosterSize, numberOfPositions);		

		for (Team t : this.teams) {
			t.divideRoster_OffensiveAndDefensive();
			//t.printTeam();
		}

	}

	/*
	 * Getters and setters for GeneralManagerPool, PLayerPool and CoachPool
	 * -------------------------------------------------------------------------
	 * ----------------------------
	 */

	private void fillAllTeamRosters(int rosterSize, int numberOfPositions) {
		int i = 0;
		
		Collections.sort(this.playerPool.getPlayerPool());
		for(i=0; i< (rosterSize/numberOfPositions); i++)
		{
			System.out.println("inside fillall");
			System.out.println(this.teams);
			this.allTeamsPick(Player.RUNNINGBACKSCORE);
			this.allTeamsPick(Player.RECEIVERSCORE);
			this.allTeamsPick(Player.OFFENSIVELINESCORE);
			this.allTeamsPick(Player.SECONDARYSCORE);
			this.allTeamsPick(Player.LINEBACKERSCORE);
			this.allTeamsPick(Player.DEFENSIVELINESCORE);
		}
		int remaining = rosterSize % numberOfPositions;
		
		this.allTeamsPick(Player.RUNNINGBACKSCORE);
		remaining--;
		if(remaining == 0)
			return;
		
		this.allTeamsPick(Player.RECEIVERSCORE);
		remaining--;
		if(remaining == 0)
			return;
		
		this.allTeamsPick(Player.OFFENSIVELINESCORE);
		remaining--;
		if(remaining == 0)
			return;
		
		this.allTeamsPick(Player.SECONDARYSCORE);
		remaining--;
		if(remaining == 0)
			return;
		
		this.allTeamsPick(Player.LINEBACKERSCORE);
		remaining--;
		
	}


	private void allTeamsPick(int position) {
		for(Team team: this.teams)
		{
			Player player = team.getGM().pickAPlayer(this.playerPool, position);
			team.addToRoster(player);
			//System.out.println("inside all teams pick");
			player.setIsInATeam(true);
			player.setTeam(team);
			player.setCoach(team.getCoach());
			this.playerPool.getPlayerPool().remove(player);
		}
		
	}

	public void setGeneralManagerPool(GeneralManager_Pool generalManagerPool) {
		this.generalManagerPool = generalManagerPool;
	}

	public void setPlayerPool(Player_Pool playerPool) {
		this.playerPool = playerPool;
	}

	public void setCoachPool(Coach_Pool coachPool) {
		this.coachPool = coachPool;
	}

	public Player_Pool getPlayerPool() {
		return playerPool;
	}

	public GeneralManager_Pool getGeneralManagerPool() {
		return generalManagerPool;
	}

	public Coach_Pool getCoachPool() {
		return coachPool;
	}

	public static int getPlayerPoolSize() {
		return 10000;
	}

	public static int getCoachPoolSize() {
		return 100;
	}

	public static int getGMPoolSize() {
		return 100;
	}

	/*
	 * End of Getters and Setters
	 * -------------------------------------------------------------------------
	 * ----------------------------
	 */

	/*
	 * Helper print methods for debugging
	 * -------------------------------------------------------------------------
	 * ----------------------------
	 */
	public static void printInputs(int numberOfTeams, int numberOfSeasons) {
		System.out.println("***************************************");
		System.out.println("INPUTS");
		System.out.println("Number of Teams: " + numberOfTeams);
		System.out.println("Number of Seasons: " + numberOfSeasons);
		System.out.println("***************************************");

	}

	public static void printOutputs(ArrayList<Season> seasons) {
		while (true) {
			System.out.println("Option 1: Print out game statistics");
			System.out.println("Option 2: Print out win loss records");
			System.out.println("Option 3: Print out team roster details");
			System.out.println("Option 4: Print out team revenue details");
			System.out.print("Enter an option number (1,2, 3, or 4): ");
			Scanner scan1 = new Scanner(System.in);
			String input = scan1.nextLine();
			System.out.println("");
			if (input.equals("1")) {
				printSeasonDetails(seasons);
			} else if (input.equals("2")) {
				printWinLossRecords(seasons);
			} else if (input.equals("3")) {
				printTeamDetails(seasons);
			} else if (input.equals("4")) {
				printRevenueDetails(seasons);
			} else {
				System.out.println("Invalid number. Program terminated. ");
				break;
			}
			System.out.print("Would you like to continue? (y/n): ");
			Scanner scan2 = new Scanner(System.in);
			input = scan2.nextLine();
			if (input.equals("n")) {
				break;
			}
			System.out.println("");
			// scan1.close();
			// scan2.close();
		}

	}

	public static void printSeasonDetails(ArrayList<Season> seasons) {
		// Print out season outcome and game statistics in each season
//		for (int i = 0; i < seasons.size(); i++) {
//			Season s = seasons.get(i);
//			System.out.println("SEASON #" + (i + 1) + " Victor: " + s.victors.get(i).getTeamName());
//			s.printSeason();
//			System.out.println("");
//		}
		System.out.println("OVERALL OUTCOME");
		System.out.println("Victor: " + seasons.get(3).victors.get(3).getTeamName());
		seasons.get(3).printSeason();
	}

	public static void printWinLossRecords(ArrayList<Season> seasons) {
//		for (int i = 0; i < seasons.size(); i++) {
//			Season s = seasons.get(i);
//			System.out.println("SEASON #" + (i + 1));
//			System.out.println(s.tempTeams.get(0).getWins());
//			for (Team t : s.teams) {
//				System.out.println(t.getTeamName() + ": " + "Wins-" + t.getWins() + " " + "Losses-" + t.getLosses());
//			}
//			System.out.println("");
//		}
		System.out.println("TOTAL RECORD ACROSS SEASONS");
				for (Team t : seasons.get(3).teams) {
					System.out.println(t.getTeamName() + ": " + "Wins-" + t.getWins() + " " + "Losses-" + t.getLosses());
				}
	}

	public static void printTeamDetails(ArrayList<Season> seasons) {
		for(int i=0; i<seasons.get(0).teams.size(); i++) {
			System.out.println(seasons.get(0).teams.get(i).getTeamName());
		}
		System.out.println("");
		System.out.print("Please select a team: ");
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		for (int i = 0; i < seasons.size(); i++) {
			Season s = seasons.get(i);
			System.out.println("SEASON #" + (i + 1));
			s.printRosterDetails(input);
			System.out.println("");
		}
	}

	public static void printRevenueDetails(ArrayList<Season> seasons) {
		for (int i = 0; i < seasons.size(); i++) {
			Season s = seasons.get(i);
			System.out.println("SEASON #" + (i + 1));
			for (int j = 0; j < s.teams.size(); j++) {
				s.tempTeams.get(j).profitPrinter();
			}
			System.out.println("");
		}

	}

	/*
	 * All the print methods are between the lines
	 * -------------------------------------------------------------------------
	 * ----------------------------
	 */
}
