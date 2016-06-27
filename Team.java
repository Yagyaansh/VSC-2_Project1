import java.util.ArrayList;
import java.util.Random;

public class Team {

	/*
	 * All the fields required for the team class are declared here
	 */

	private String name;
	private Coach coach;
	private ArrayList<Player> roster;
	private ArrayList<Player> starters;
	private GeneralManager GM;
	private String hometown;
	private int population;
	private double grossRevenue;
	private double expenses;
	private double profit;
	private ArrayList<Player> offensiveRoster;
	private ArrayList<Player> defensiveRoster;
	private int wins;
	private int losses;
	private Owner owner;
	private ArrayList<Result> results;
	private Result result;

	/*
	 * Constructor to initialize a Team with NAME, HOMETOWN and GM population is
	 * determined and initialized in the constructor
	 */

	public Team(String name, String hometown, GeneralManager gm) {
		this.name = name;
		this.coach = new Coach();
		this.roster = new ArrayList<Player>();
		this.starters = new ArrayList<Player>();
		this.GM = gm;
		this.hometown = hometown;
		this.grossRevenue = 0.0;
		this.expenses = 0.0;
		this.profit = 0.0;
		this.offensiveRoster = new ArrayList<Player>();
		this.defensiveRoster = new ArrayList<Player>();
		this.wins = 0;
		this.losses = 0;
		/*
		 * Hard coded population value depending on the hometown
		 */
		if (hometown == "Baltimore"){
			this.population = 621849;
		} else if (hometown == "Cincinnati") {
			this.population = 296943;
		} else if (hometown == "Cleveland") {
			this.population = 396815;
		} else if (hometown == "Pittsburgh") {
			this.population = 305704 ;
		} else if (hometown == "Chicago") {
			this.population = 2695598 ;
		} else if (hometown == "Detroit") {
			this.population = 711299 ;
		} else if (hometown == "Green Bay") {
			this.population = 104057  ;
		} else if (hometown == "Minneapolis") {
			this.population = 382578;
		} else if (hometown == "Houston") {
			this.population = 2099451;
		} else if (hometown == "Indianapolis") {
			this.population = 6484229;
		} else if (hometown == "Jacksonville") {
			this.population = 821784;
		} else if (hometown == "Nashville") {
			this.population = 601222;
		} else if (hometown == "Atlanta") {
			this.population = 420003;
		} else if (hometown == "Charlotte") {
			this.population = 731424;
		} else if (hometown == "New Orleans") {
			this.population = 343829;
		} else if (hometown == "Tampa") {
			this.population = 335715;
		} else if (hometown == "Phoenix") {
			this.population = 1447624;
		} else if (hometown == "Boston") {
			this.population = 617680;
		}
		this.owner = new Owner(this);
		this.results = new ArrayList<>();
		this.result = new Result();
	}

	/*
	 * Default constructor for Team
	 */
	public Team() {
		this.name = null;
		this.coach = new Coach();
		this.roster = new ArrayList<Player>();
		this.starters = new ArrayList<Player>();
		this.GM = null;
		this.hometown = null;
		this.grossRevenue = 0.0;
		this.expenses = 0.0;
		this.profit = 0.0;
		this.offensiveRoster = new ArrayList<Player>();
		this.defensiveRoster = new ArrayList<Player>();
		this.wins = 0;
		this.losses = 0;
		this.population = 0;
		this.owner = new Owner(this);
		this.results = new ArrayList<>();
		this.result = new Result();
	}

	/*
	 * Getters and setters for the fields in the class
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */

	public double getGrossRevenue() {
		return grossRevenue;
	}

	public String getHometown() {
		return hometown;
	}

	public String getTeamName() {
		return name;
	}

	public ArrayList<Player> getStarters() {
		return this.starters;
	}

	public ArrayList<Player> getOffensivePlayersInRoster() {
		return this.offensiveRoster;
	}

	public ArrayList<Player> getDefensivePlayersInRoster() {
		return this.defensiveRoster;
	}

	public void addToRoster(Player player) {
		this.roster.add(player);
	}

	public GeneralManager getGM() {
		return this.GM;
	}

	public Coach getCoach() {
		return this.coach;
	}

	public ArrayList<Player> getRoster() {
		return this.roster;
	}

	public int getWins() {
		return this.wins;
	}

	public int getLosses() {
		return this.losses;
	}
	
	public Owner getOwner() {
		return this.owner;
	}
	
	public void setOwner(Owner o) {
		this.owner = o;
	}

	public void setGM(GeneralManager gm) {
		this.GM = gm;
	}

	public void setCoach(Coach coach) {
		this.coach = coach;
	}
	
	public void addToResults(Result r)
	{
		this.results.add(r);
	}
	
	public ArrayList<Result> getResults()
	{
		return this.results;
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

	public void printTeam() {
		System.out.println("Team Name: " + name);
		System.out.println("Hometown: " + hometown);
		owner.printOwner();
		GM.printGeneralManager();
		coach.printCoach();
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("COMPLETE ROSTER: ");
		for (int i = 0; i < roster.size(); i++) {
			roster.get(i).printPlayer();
		}
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("STARTING LINEUP: ");
		for (int i = 0; i < starters.size(); i++) {
			starters.get(i).printPlayer();
		}
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("OFFENSE ROSTER: ");
		for (int i = 0; i < offensiveRoster.size(); i++) {
			offensiveRoster.get(i).printPlayer();
		}
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("DEFENSE ROSTER: ");
		for (int i = 0; i < defensiveRoster.size(); i++) {
			defensiveRoster.get(i).printPlayer();
		}
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("INJURIED PLAYERS: ");
		int numberOfInjuried = 0;
		for (Player p : this.roster) {
			if (p.isInjured()) {
				p.printPlayer();
				numberOfInjuried++;
			}
		}
		if (numberOfInjuried == 0) {
			System.out.println("None");
		}
		System.out.println("");
	}

	public void profitPrinter() {
		System.out.println(name + "'s Total Profit: $" + profit);
	}

	/*
	 * End of printing methods
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */

	/*
	 * Methods to update and calculate values
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */

	public void incrementLosses() {
		this.losses++;
	}

	public void incrementWins() {
		this.wins++;
	}

	public void updateHomeTeamRevenue() {
		this.grossRevenue += (population / 10);
	}

	public void updateWinningTeamRevenue() {
		this.grossRevenue += 100000;
	}

	public void updateSeasonWinningTeamRevenue() {
		this.grossRevenue += 1000000;
	}

	public int TeamDeterminateCalculator() {
		int sum = 0;
		for (int i = 0; i < 22; i++) {
			Player member = starters.get(i);
			int determinate;
			determinate = (int)(member.getAthleticism() - Math.abs(coach.getScheme() - member.getFit()));
			if (determinate > 0) {
				sum = sum + determinate;
			}
		}
		return sum;
	}

	public double profitCalculator() {

		for (int i = 0; i < starters.size(); i++) {
			expenses += starters.get(i).getSalaryAmount(this.coach);
		}
		expenses += GM.getSalary();
		expenses += coach.getSalary();
		profit = grossRevenue - expenses;
		return profit;
	}

	/*
	 * End of methods to update and calculate values
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */

	/*
	 * This method selects the starters from the roster It updates the starters
	 * list 11 players are defensive 11 players are offensive
	 */
	public void selectStarters() {

		starters.clear();
		starters.addAll(getRandomUninjured11FromList(this.offensiveRoster));
		starters.addAll(getRandomUninjured11FromList(this.defensiveRoster));

	}

	/*
	 * Selects 11 uninjured players from the list passed to it as a parameter
	 * returns null if the list could not be formed
	 */
	public ArrayList<Player> getRandomUninjured11FromList(ArrayList<Player> players) {
		if (players.size() > 11) {
			ArrayList<Player> selectedPlayers = new ArrayList<Player>();
			while (selectedPlayers.size() != 11) {
				for (Player p : players) {
					if (!p.isInjured() && !selectedPlayers.contains(p)) {
						Random rand = new Random();

						boolean select = rand.nextBoolean();
						if (select) {
							selectedPlayers.add(p);
						}
					}
					if (selectedPlayers.size() == 11) {
						break;
					}
				}
			}
			return selectedPlayers;
		} else {
			return null;
		}
	}

	/*
	 * When a player object is initialized he/she is assigned offensive type or
	 * defensive type This method divides the teams roster into an offensive
	 * players' roster and a defensive players' roster
	 */
	public void divideRoster_OffensiveAndDefensive() {
		for (Player p : this.roster) {
			if (p.isOffensive() && !offensiveRoster.contains(p)) {
				this.offensiveRoster.add(p);
			} else if (!p.isOffensive() && !defensiveRoster.contains(p)) {
				this.defensiveRoster.add(p);
			}
		}
	}

	/*
	 * During off season the age of every player is incremented by 1 year If a
	 * player becomes 31 then he must retire Is removed from all pools and teams
	 * Not eligible for selection anymore
	 * 
	 * They are replaced by rookie players (not implemented in this method)
	 * 
	 */
	 
	 /*
	 * This method might not be needed anymore
	 */
	 
	// public void removeOldPlayers(Player_Pool players) {
	// 	ArrayList<Player> playerPool = players.getPlayerPool();
	// 	for (Player player : playerPool) {
	// 		if (player.getAge() == 31) {
	// 			this.roster.remove(player);
	// 			playerPool.remove(player);
	// 		}
	// 	}
	// }

	/*
	 * Removes all the players who have suffered career ending injuries for
	 * whatever reason For the player to be removed the careerEndingInjury field
	 * for the player must be true The player is removed form all pools
	 * 
	 * The players are not replaced by new players in the pool
	 */

	public void removeCareerEndingInjuredPlayers(Player_Pool players) {
		ArrayList<Player> playerPool = players.getPlayerPool();
		for (Player player : playerPool) {
			if (player.isEndedCareer()) {
				this.roster.remove(player);
				player.setIsInATeam(false);
				player.setTeam(null);
				playerPool.remove(player);
			}
		}

	}
	
	// Win and loss record must be reset after each season
	
	public void resetScores() {
		this.wins = 0;
		this.losses = 0;
	}

	public void resetRevenue() {
		this.grossRevenue = 0;
		this.expenses = 0;
		this.profit = 0;
	}

	public Team deepCopy() {
		Team t = new Team();
		t.name = this.getTeamName();
		t.hometown = this.getHometown();
		t.owner = this.getOwner().deepCopy();
		t.GM = this.getGM().deepCopy();
		t.coach = this.coach.deepCopy();
		t.grossRevenue = this.grossRevenue;
		t.expenses = this.expenses;
		t.profit = this.profit;
		ArrayList<Player> roster = new ArrayList<Player>();
		ArrayList<Player> starters = new ArrayList<Player>();
		ArrayList<Player> offensiveRoster = new ArrayList<Player>();
		ArrayList<Player> defensiveRoster = new ArrayList<Player>();
		for (Player p : this.roster) {
			roster.add(p.deepCopy());
		}
		for (Player p : this.starters) {
			starters.add(p.deepCopy());
		}
		for (Player p : this.offensiveRoster) {
			offensiveRoster.add(p.deepCopy());
		}
		for (Player p : this.defensiveRoster) {
			defensiveRoster.add(p.deepCopy());
		}
		t.roster = roster;
		t.starters = starters;
		t.offensiveRoster = offensiveRoster;
		t.defensiveRoster = defensiveRoster;
		t.wins = this.wins;
		t.losses = this.losses;
		return t;
	}

}
