import java.text.DecimalFormat;
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
	private Owner owner;
	private ArrayList<Result> results;
	private Result result;
	private String divison;
	private Stadium stadium;
	private Sponsor sponsor;

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
		/*
		 * Hard coded population value depending on the hometown
		 */
		if (hometown == "Boston"){
			this.population = 645966;
		} else if (hometown == "Buffalo") {
			this.population = 258959;
		} else if (hometown == "New York") {
			this.population = 8406000;
		} else if(hometown == "Miami"){
			this.population = 417650;
		} else if (hometown == "Pittsburgh") {
			this.population = 305704;
		} else if (hometown == "Baltimore") {
			this.population = 622104;
		} else if (hometown == "Cincinnati") {
			this.population = 297517;
		} else if (hometown == "Cleveland") {
			this.population = 390113;
		} else if (hometown == "Houston") {
			this.population = 2196000;
		} else if (hometown == "Jacksonville") {
			this.population = 842583;
		} else if (hometown == "Tennessee") {
			this.population = 6549000;
		} else if (hometown == "Indianapolis") {
			this.population = 852866;
		} else if (hometown == "Denver") {
			this.population = 649495;
		} else if (hometown == "Oakland") {
			this.population = 406253;
		} else if (hometown == "Kansas City") {
			this.population = 467007;
		} else if (hometown == "San Diego") {
			this.population = 1356000;
		}
		this.owner = new Owner(this);
		this.results = new ArrayList<>();
		this.result = new Result();
		  
		if( this.name.equals("Patriots") || this.name.equals("Bills") || this.name.equals("Jets") ||this.name.equals("Dolphins")){
			this.divison = "AFC EAST";
		}
		if( this.name.equals("Steelers") || this.name.equals("Ravens") || this.name.equals("Bengals") ||this.name.equals("Browns")){
			this.divison = "AFC NORTH";
		}
		if( this.name.equals("Texans") || this.name.equals("Jaguars") || this.name.equals("Titans") ||this.name.equals("Colts")){
			this.divison = "AFC SOUTH";
		}
		if( this.name.equals("Broncos") || this.name.equals("Ravens") || this.name.equals("Cheifs") ||this.name.equals("Chargers")){
			this.divison = "AFC WEST";
		}
		
		this.stadium = new Stadium(this);
		this.sponsor = new Sponsor(this);
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
		this.population = 0;
		this.owner = new Owner(this);
		this.results = new ArrayList<>();
		this.result = new Result();
		this.stadium = new Stadium(this);
		this.sponsor = new Sponsor(this);
	}

	/*
	 * Getters and setters for the fields in the class
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */
	
	public double getProfit()
	{
		return profit;
	}

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
		return this.result.getWins();
	}

	public int getLosses() {
		return this.result.getLosses();
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
	public void newTeamResult()
	{
		result = new Result();
	}
	
	// off-season for this "current season" has just started
	
	public Result getCurrentSeasonResult()
	{
		return this.result;
	}
	
	public Stadium getStadium() {
		return stadium;
	}

	public void setStadium(Stadium stadium) {
		this.stadium = stadium;
	}
	
	// this is the season before the current season
	// if the off-season has been called then this past season should return null
	// since there is no prior season
	// to ensure that this works the owner must call this before the season result is saved to the list
	// the season result is saved to the list as the final action in the offseason method of the season class
	
	// If this returns null means the 1st season has just ended
	public Result getPreviousSeasonResult()
	{
		if(this.results.size() == 0)
			return null;
		return this.results.get(this.results.size() - 1);
	}
	
	public int getPopulation()
	{
		return this.population;
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
	
	public void printRevenue(){
		System.out.println("Team Name: " + name);
		System.out.println("Hometown: " + hometown);
		System.out.println("General Manager: " + GM.getName() + " Salary: "+ "$"+moneyFormatter(GM.getSalary()));
		System.out.println("Coach: " + coach.getName() + " Salary: " + "$"+moneyFormatter(coach.getSalary()));
		System.out.println("Total Management Expense: " + "$"+moneyFormatter((GM.getSalary() + coach.getSalary())));
		System.out.println("Roster Expense: " + "$"+ moneyFormatter((expenses - (GM.getSalary() + coach.getSalary()))));
		System.out.println("Total Payroll Expense: " +"$"+ moneyFormatter(expenses));
		System.out.println("Home Game Revenue: " + "$"+moneyFormatter(this.grossRevenue));
		System.out.println("Win Revenue: $100,000.00");
		
		
	}

	public void printTeam() {
		System.out.println("Team Name: " + name);
		System.out.println("Hometown: " + hometown);
		owner.printOwner();
		GM.printGeneralManager();
		coach.printCoach();
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("COMPLETE ROSTER: " + this.getRoster().size());
		for (int i = 0; i < roster.size(); i++) {
			roster.get(i).printPlayer();
		}
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("STARTING LINEUP: " +this.getStarters().size());
		for (int i = 0; i < starters.size(); i++) {
			starters.get(i).printPlayer();
		}
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("OFFENSE ROSTER: " + this.getOffensivePlayersInRoster().size());
		for (int i = 0; i < offensiveRoster.size(); i++) {
			offensiveRoster.get(i).printPlayer();
		}
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("DEFENSE ROSTER: " + this.getDefensivePlayersInRoster().size());
		for (int i = 0; i < defensiveRoster.size(); i++) {
			defensiveRoster.get(i).printPlayer();
		}
		System.out.println("----------------------------------------------------------------------------------------");
		System.out.println("INJURED PLAYERS: " + findInjuredPlayers().size());
		printInjuredPlayers();
		System.out.println("");
	}

	public void profitPrinter() {
		System.out.println(name + "'s Total Profit: $" + profit );
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
		this.result.incrementLosses();
	}

	public void incrementWins() {
		this.result.incrementWins();
	}
	
	public void incrementDraws() {
		this.result.incrementDraws();
	}

	public void updateHomeTeamRevenue() {
		double revenue = 0.0;
		double fromStadium = stadium.revenueGenerated();
		double fromMedia = Media.getMediaRevenueFor(this);
		double fromSponsors = sponsor.getSponsorshipPerGame();
		revenue = fromStadium + fromMedia + fromSponsors;
		this.grossRevenue += revenue;
	}
	
	public void updateAwayTeamRevenue(){
		double revenue = 0.0;
		double fromMedia = (Media.getMediaRevenueFor(this)/10); // because the team is playing away it gets only 1/10 of media revenue
		double fromSponsors = sponsor.getSponsorshipPerGame(); // gets sopnsorship whether home or away
		revenue = fromMedia + fromSponsors;
		this.grossRevenue += revenue;
	}

	public void updateWinningTeamRevenue() {
		this.grossRevenue += 100000;
	}

	public void updateSeasonWinningTeamRevenue() {
		this.grossRevenue += 1000000;
	}

	public double TeamDeterminateCalculator() {
		double determinante = 0.0;
		for (int i = 0; i < 22; i++) {
			Player member = starters.get(i);
			determinante += (member.getAthleticism() - Math.abs(coach.getScheme()
					- member.getFit()));
		}

		return determinante;

	}

	public void updateHomeTeamExpenses() 
	{
		for (int i = 0; i < roster.size(); i++) 
		{
			expenses += roster.get(i).getSalary();
		}
		expenses += GM.getSalary();
		expenses += coach.getSalary();
		expenses += stadium.getCostForMaintainance()*1.5; // since the team played a home game the cost for maintainance for that week increases
	}
	
	public void updateAwayTeamExpenses() 
	{
		for (int i = 0; i < roster.size(); i++) 
		{
			expenses += roster.get(i).getSalary();
		}
		expenses += GM.getSalary();
		expenses += coach.getSalary();
		expenses += stadium.getCostForMaintainance(); // basic cost of maintainance
	}
	
	public void updateProfit()
	{
		this.profit += (this.grossRevenue - this.expenses);
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

	
	public ArrayList<Player> findInjuredPlayers(){
		ArrayList<Player> injuredPlayers = new ArrayList<Player>();
		for (Player p : this.roster) {
			if (p.isInjured()) {
				injuredPlayers.add(p);
			}
		}
		return injuredPlayers;
	}
	
	public void printInjuredPlayers(){
		int numberOfInjuries=0;
		for (Player p : this.roster) {
			if (p.isInjured()) {
				p.printPlayer();
				numberOfInjuries++;
			}
		}
		if (numberOfInjuries == 0) {
			System.out.println("None");
		}
	}
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
	
	/*
	* The offseason has started and current seasons result has to stored in the record 
	* The results array list stores the current results
	* make sure to store the deep copy of the result in the array list
	*/
	
	public void saveResultToList()
	{
		this.results.add(this.result.getDeepCopy());
	}
	
	/*
	* Clear the result field so that the result of the next season can be recorded here.
	*/
	
	// Win and loss record must be reset after each season
	

	public void resetRevenue() {
		this.grossRevenue = 0;
		this.expenses = 0;
		this.profit = 0;
	}
	
	public String moneyFormatter(int number){
		String convertedString = new DecimalFormat("#,###.00").format(number);
		return convertedString;
	}
	public String moneyFormatter(double db){
		String convertedString = new DecimalFormat("#,###.00").format(db);
		return convertedString;
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

		
		
		
		// Need to add deep copy for the 2 new fields that I have created
		// results - ArrayList of results - history of results (each result stores W-D-L for every seasosn)
		// result - type Result
		
		return t;
	}

}
