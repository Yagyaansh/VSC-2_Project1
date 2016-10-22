import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
	 * list The number of seasons is hard coded since the number should not
	 * change too much Each season is then simulated and the results are stored
	 * in a list called results This stores all the final results for the
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
	
	public int getCurrWeek() {
		return currWeek;
	}

	public void setCurrWeek(int currWeek) {
		this.currWeek = currWeek;
	}

	public int getCurrSeasonNum() {
		return currSeasonNum;
	}

	public void setCurrSeasonNum(int currSeasonNum) {
		this.currSeasonNum = currSeasonNum;
	}

	public int getSeasonsToSim() {
		return seasonsToSim;
	}

	public void setSeasonsToSim(int seasonsToSim) {
		this.seasonsToSim = seasonsToSim;
	}

	private Player_Pool playerPool;
	private ArrayList<Team> teams;
	private static Scanner mainScanner;
	private int weeksToRun;
	private Season currSeason;
	private int numberOfSeasons;
	private ArrayList<Team> results;
	private ArrayList<Season> seasons;
	private int numberOfTeams;
	private int currWeek;
	private int currSeasonNum;
	private int seasonsToSim;

	/*
	 * Constructor - hard-coded with the sizes of the pools Change pool size
	 * here if needed
	 */

	public Main() {
		this.generalManagerPool = new GeneralManager_Pool(getGMPoolSize());
		this.coachPool = new Coach_Pool(getCoachPoolSize());
		this.playerPool = new Player_Pool(getPlayerPoolSize());

		int numberOfTeams = numberOfTeams();
		this.teams = new ArrayList<Team>();
		this.mainScanner = new Scanner(System.in);
		this.createTeams(numberOfTeams, getGeneralManagerPool(), getCoachPool(), getPlayerPool());
		this.weeksToRun = -1;
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
	
	public void step1(int numTeams,int numSeasons)
	{
		numberOfTeams = numTeams;
		seasons = new ArrayList<Season>();
		results = new ArrayList<Team>();
		numberOfSeasons = numSeasons;
		System.out.println("Number of seasons " + numberOfSeasons);
		System.out.println("teams" + numberOfTeams);
	}
	
	public void step2(int seasonNum, int weeks) {
		boolean isFirstSeason = false;

		try {
			seasons.get(seasonNum);
			System.out.println();
		} catch (NullPointerException e) {
			System.out.println("Inside nullptr");
			seasons.add(new Season(this.teams, numberOfTeams));
			System.out.println("adding" + seasonNum + seasons.size());
			currSeason = seasons.get(seasonNum);
			isFirstSeason = false;
			if (seasonNum == 0) {
				isFirstSeason = true;
			}
			currSeason.scheduleRandSeason();
		} catch (IndexOutOfBoundsException e)
		{
			System.out.println("inside out" + seasonNum);
			seasons.add(new Season(this.teams, numberOfTeams));
			System.out.println("adding" + seasonNum + seasons.size());
			currSeason = seasons.get(seasonNum);
			isFirstSeason = false;
			if (seasonNum == 0) {
				isFirstSeason = true;
			}
			currSeason.scheduleRandSeason();
		}
		currSeason.play(weeks);
		if (currSeason.isFinished()) {
			results.add(currSeason.seasonResult());
			currSeason.offSeason(this.getPlayerPool(), this.getCoachPool(), isFirstSeason);
		}

	}

	public void start() throws Exception {
		
//		int testTotal = 0;
//		Team test = teams.get(0);
//		for(Player p: test.getRoster())
//		{
//			System.out.println(p.getSalary());
//			testTotal += p.getSalary();
//		}
//		System.out.println(testTotal);
//		
//		System.exit(0);


		for (int i = 0; i < numberOfSeasons; i++) {
			seasons.add(new Season(this.teams, 16));
			Season s = seasons.get(i);
			boolean isFirstSeason = false;
			if (i == 0) {
				isFirstSeason = true;
			}
			s.scheduleRandSeason();
			while (!s.isFinished()) {
				System.out.println("\nSeason " + (i + 1) + " Week " + (s.getCurrWeek() + 1));
				System.out.println("How many weeks to run before stopping? ");
				s.play(weeksToRun);
				System.out.println("\nSeason " + (i + 1) + " Week " + (s.getCurrWeek() + 1));
				System.out.println("It worked");
				printOutputs(s);
				}
			}
			//esults.add(s.seasonResult());
			//s.offSeason(this.getPlayerPool(), this.getCoachPool(), isFirstSeason);

		}

		//printInputs(numberOfTeams, numberOfSeasons);
	
	
	public static double numberOfGames() {
		
		/*
		 * TODO : 
		 * 
		 * How do we calculate this value ??
		 */
		
		return 16;
	}
	
	public void setWeeksToRun(int weeks)
	{
		this.weeksToRun = weeks;
	}
	
	public Season getCurrSeason()
	{
		return currSeason;
	}

	/*
	 * Generalizes team creation Assigns GM, Coach, Players to roster, and
	 * divides into offensive and devensive rosters. returns a list of teams
	 * participating in the simulation
	 */

	public void createTeams(int numberOfTeams, GeneralManager_Pool generalManagerPool, Coach_Pool coachPool,
			Player_Pool playerPool) {
		String[] teamNames = { "Boston Patriots", "Buffalo Bills", "New York Jets", "Miami Dolphins",
				"Pittsburgh Steelers", "Baltimore Ravens", "Cincinnati Bengals", "Cleveland Browns", "Houston Texans",
				"Jacksonville Jaguars", "Tennessee Titans", "Indianapolis Colts", "Denver Broncos", "Oakland Raiders",
				"Kansas City Chiefs", "San Diego Chargers" };
		String[] teamHometowns = { "Boston", "Buffalo", "New York", "Miami", "Pittsburgh", "Baltimore", "Cincinnati",
				"Cleveland", "Houston", "Jacksonville", "Tennessee", "Indianapolis", "Denver", "Oakland", "Kansas City",
				"San Diego" };

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
			// t.printTeam();
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
		for (i = 0; i < (rosterSize / numberOfPositions); i++) {
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
		if (remaining == 0)
			return;

		this.allTeamsPick(Player.RECEIVERSCORE);
		remaining--;
		if (remaining == 0)
			return;

		this.allTeamsPick(Player.OFFENSIVELINESCORE);
		remaining--;
		if (remaining == 0)
			return;

		this.allTeamsPick(Player.SECONDARYSCORE);
		remaining--;
		if (remaining == 0)
			return;

		this.allTeamsPick(Player.LINEBACKERSCORE);
		remaining--;

	}

	private void allTeamsPick(int position) {
		for (Team team : this.teams) {
			Player player = team.getGM().pickAPlayer(this.playerPool, position);
			team.addToRoster(player);
			// System.out.println("inside all teams pick");
			player.setIsInATeam(true);
			player.setTeam(team);
			player.setCoach(team.getCoach());
			player.setSalary(player.calculateSalary());
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
	
	public static int numberOfTeams(){
		return 16;
	}
	
	public ArrayList<Team> getTeams()
	{
		return this.teams;
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

	public static void printOutputs(Season s) {
		while (true) {
			System.out.println("Option 1: Print out game statistics for one team");
			System.out.println("Option 2: Print full game statistics for one week");
			System.out.println("Option 3: Print out win/loss records");
			System.out.println("Option 4: Print out team roster details");
			System.out.println("Option 5: Print out team revenue details");
			System.out.println("Option 6: Print out full revenue statistics for one week");
			System.out.print("Enter an option number (1-6): ");
			String input = mainScanner.nextLine();
			System.out.println("");
			switch (input) {
			case "1":
				printTeamGames(s,"Buffalo Bills");
				break;
			case "2":
				printWeekDetails(s);
				break;
			case "3":
				printWinLossRecords(s);
				break;
			case "4":
				printTeamDetails(s);
				break;
			case "5":
				printTeamRevenues(s);
				break;
			case "6":
				// TODO
				break;
			default:
				System.out.println("Invalid number. Program terminated. ");
				break;

			}
			System.out.print("Would you like to continue with the season? (y/n): ");
			input = mainScanner.nextLine();
			if (input.equals("y"))
				break;
			System.out.println("");
		}

	}

	public static String printTeamRevenues(Season s) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
		for (Team t : s.getTeams()) {
			t.printRevenue();
			System.out.println("");
		}
		System.out.flush();
		System.setOut(old);
		System.out.println(baos.toString());
		return baos.toString();
	}

	public static String printTeamGames(Season s,String teamName) {
		System.out.println("We made it fam" + s.getCurrWeek());
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
		boolean found = false;
//		do {
//			System.out.println("");
//			for (int i = 0; i < s.getTeams().size(); i++) {
//				System.out.print(s.getTeams().get(i).getTeamName() + ", ");
//				if ((i+1) % 5 == 0)
//					System.out.println("");
//			}
//			System.out.println("\n");
//			System.out.print("Please select a team: ");
//			String input = mainScanner.nextLine();

			Team t = new Team();
			for (int i = 0; i < s.getTeams().size(); i++) {
				if (s.getTeams().get(i).getTeamName().equals(teamName)) {
					t = s.getTeams().get(i);
					found = true;
				}
			}

			for (int i = 0; i < t.getCurrentSeasonResult().getGames().length; i++) {
				if (t.getCurrentSeasonResult().getGames()[i] != null) {
					System.out.println("Game " + (i + 1));
					t.getCurrentSeasonResult().getGames()[i].printGame();
					System.out.println("");
				}
			}
//			if (!found)
//				System.out.println("\nTeam not found, check the spelling.\n");
//		} while (!found);
		System.out.flush();
		System.setOut(old);
		System.out.println(baos.toString());
		System.out.println("hello");
		return baos.toString();
	}

	// Print out week outcome and game statistics in one season
	public static String printWeekDetails(Season s) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
		int week = 0;
		do {
			if (week > s.getCurrWeek())
				System.out.println("That week has not been played yet.");

			System.out.println("What week would you like to look at? ");
			week = Integer.parseInt(mainScanner.nextLine());
		} while (week > s.getCurrWeek());

		System.out.println("WEEK #" + week);
		for (int j = 0; j < s.getWeek(week).length; j++) {
			s.getWeek(week)[j].printGame();
			System.out.println("");
		}
		System.out.flush();
		System.setOut(old);
		System.out.println(baos.toString());
		return baos.toString();
	}

	public static String printWinLossRecords(Season s) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
		System.out.println("WIN-LOSS RECORDS");
		for (Team t : s.getTeams()) {
			System.out.println(t.getTeamName() + ": " + "Wins-" + t.getWins() + " " + "Losses-" + t.getLosses());
		}
		System.out.println("");
		System.out.flush();
		System.setOut(old);
		System.out.println(baos.toString());
		return baos.toString();

	}

	public static String printTeamDetails(Season s) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
		for (int i = 0; i < s.getTeams().size(); i++) {
			System.out.println(s.getTeams().get(i).getTeamName());
		}
		System.out.println("");
		System.out.print("Please select a team: ");
		String input = mainScanner.nextLine();
		s.printRosterDetails(input);
		System.out.flush();
		System.setOut(old);
		System.out.println(baos.toString());
		return baos.toString();


	}

	public static void printOffseasonDetails(ArrayList<Season> seasons) {
		for (int i = 0; i < seasons.size(); i++) {
			Season s = seasons.get(i);
			System.out.println("SEASON #" + (i + 1));
			System.out.print("Retired Players:");
			for (int j = 0; j < s.retiredPlayers.size(); j++) {
				s.retiredPlayers.get(i).printPlayer();
			}
			System.out.println("Fired Coaches:");
			for (int j = 0; j < s.firedCoaches.size(); j++) {
				s.firedCoaches.get(i).printCoach();
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
