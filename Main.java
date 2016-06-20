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
	 * notchange too much Each season is then simulated and the results are
	 * stored in a list called results This stores all the final results for the
	 * simulation
	 * 
	 * 
	 * Hardcode the pool sizes in the getters This value is called numerous
	 * times throughout the code (not just this class)
	 * 
	 */
	private GeneralManager_Pool generalManagerPool;
	private Coach_Pool coachPool;
	private Player_Pool playerPool;

	/*
	 * Constructor - hard-coded with the sizes of the pools Change pool size
	 * here if needed
	 */

	public Main() {
		this.generalManagerPool = new GeneralManager_Pool(getGMPoolSize());
		this.coachPool = new Coach_Pool(getCoachPoolSize());
		this.playerPool = new Player_Pool(getPlayerPoolSize());

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

		ArrayList<Team> teams = new ArrayList<Team>();

		int numberOfTeams = 16;
		teams = createTeams(numberOfTeams, getGeneralManagerPool(), getCoachPool(), getPlayerPool());

		ArrayList<Season> seasons = new ArrayList<Season>();
		ArrayList<Team> results = new ArrayList<Team>();
		int numberOfSeasons = 4;

		for (int i = 0; i < numberOfSeasons; i++) {
			seasons.add(new Season(teams));
		}

		for (Season s : seasons) {
			s.startSeason();
			s.play();
			results.add(s.seasonResult());
			s.offSeason(this.getPlayerPool(), this.getCoachPool());
		}
		
		printInputs(numberOfTeams, numberOfSeasons);
		printOutputs(seasons);
	}

	/*
	 * Generalizes team creation Assigns GM, Coach, Players to roster, and
	 * divides into offensive and devensive rosters. returns a list of teams
	 * participating in the simulation
	 */

	public static ArrayList<Team> createTeams(int numberOfTeams, GeneralManager_Pool generalManagerPool,
			Coach_Pool coachPool, Player_Pool playerPool) {
		ArrayList<Team> allTeams = new ArrayList<Team>();
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
			allTeams.add(t);
		}

		for (int i = 0; i < 50; i++) {
			for (Team t : allTeams) {
				Player player = t.getGM().pickAPlayer(playerPool);
				t.addToRoster(player);
			}
		}

		for (Team t : allTeams) {
			t.divideRoster_OffensiveAndDefensive();
		}

		return allTeams;
	}

	/*
	 * Getters and setters for GeneralManagerPool, PLayerPool and CoachPool
	 * -------------------------------------------------------------------------
	 * ----------------------------
	 */

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
	
	public static void printInputs(int numberOfTeams, int numberOfSeasons){
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
		for (int i = 0; i < seasons.size(); i++) {
			Season s = seasons.get(i);
			System.out.println("SEASON #" + (i + 1) + " Victor: " + s.victors.get(i).getTeamName());
			s.printSeason();
			System.out.println("");
		}
	}

	public static void printWinLossRecords(ArrayList<Season> seasons) {
		for (int i = 0; i < seasons.size(); i++) {
			Season s = seasons.get(i);
			System.out.println("SEASON #" + (i + 1));
			for (Team t : s.tempTeams) {
				System.out.println(t.getTeamName() + ": " + "Wins-" + t.getWins() + " " + "Losses-" + t.getLosses());
			}
			System.out.println("");
		}
	}

	public static void printTeamDetails(ArrayList<Season> seasons) {
		for (int i = 0; i < seasons.size(); i++) {
			Season s = seasons.get(i);
			System.out.println("SEASON #" + (i + 1));
			s.printRosterDetails();
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
