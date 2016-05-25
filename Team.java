import java.util.ArrayList;
import java.util.Random;

public class Team {

	private String name;
	private Coach coach;
	private ArrayList<Player> roster;
	private ArrayList<Player> starters;
	private GeneralManager GM;
	private String hometown;
	private int population;
	private double grossRevenue = 0;
	double expenses = 0;
	double profit = 0;
	private ArrayList<Player> offensiveRoster;
	private ArrayList<Player> defensiveRoster;
	
	// constructor to intialize a team with only the name and hometown
	public Team(String name, String hometown)
	{
		this.name = name;
		this.hometown = hometown;
		
		// please someone give me all the populations for all the hometowns
		

	}

	public Team(String name, String hometown, GeneralManager_Pool GMPool, Coaches_Pool coachPool) {

		if (hometown == "Chicago") {
			population = 2695598;
			
		} else if (hometown == "Phoenix") {
			population = 1445632;
		}
		else if(hometown == "Pittsburgh"){
			population=305841;
		}
		else if(hometown == "Boston"){
			population = 645966;
		}

		this.name = name;
		this.coach = coachPool.chooseCoach();
		this.GM = GMPool.chooseGeneralManager();
		this.hometown = hometown;
	}

	public GeneralManager getGM(){
		return this.GM;
	}
	
	public Coach getCoach(){
		return this.coach;
	}
	
	public void setPlayerList(){
		// Determines the roster for the team
		this.roster=this.GM.GMPickTeam;
	
		// Determines the starters for the team
		this.coach.coachPickStarters(this.roster);
		this.starters=coach.coachPicks;
	}
	
	public void printTeam() {
		System.out.println("Team Name: " + name);
		System.out.println("Hometown: " + hometown);
		GM.printGeneralManager();
		coach.printCoach();
		System.out.println("Starting Lineup: ");
		for (int i = 0; i < starters.size(); i++) {
			starters.get(i).printPlayer();
		}
		System.out.println("Complete Roster: ");
		for (int i = 0; i < roster.size(); i++) {
			roster.get(i).printPlayer();
		}

	}

	public int TeamDeterminateCalculator() {
		int sum = 0;
		for (int i = 0; i < 22; i++) {
			Player member = starters.get(i);
			int determinate;
			determinate = (member.getAthleticism() - Math.abs(coach.getScheme()
					- member.getFit()));
			if (determinate > 0) {
				sum = sum + determinate;
			}
		}

		return sum;

	}

	public void updateHomeTeamRevenue() {
		this.grossRevenue += (population / 10); // update revenues from home games
	}
	
	public void updateWinningTeamRevenue()
	{
		this.grossRevenue += 100000; // update revenue if the team wins a game
	}

	public double getUpdateGrossRevenue() {
		return grossRevenue;
	}

	public String getHometown() {
		return hometown;
	}

	public String getTeamName() {
		return name;
	}

	public double profitCalculator() {

		for (int i = 0; i < starters.size(); i++) {
			expenses += starters.get(i).getSalaryAmount();
		}

		expenses += GM.getSalary();
		expenses += coach.getSalary();

		profit = grossRevenue - expenses;

		return profit;
	}

	public void profitPrinter() {
		System.out.println(name + "'s Total Profit: $" + profit);
	}
	
	public void selectStarters(ArrayList<Player> offensivePlayersInRoster, ArrayList<Player> defensivePlayersInRoster)
	{
		// Coach has to select 22 uninjured players from the roster
		// 11 have to be defensive
		// 11 have to be offensive
		
		// pass in the player pool
		// player pool should be passed in as 2 seperate lists
		// offensive players
		// defensive players
		
		// coach selects 11 from defensive
		// coach selects 11 from offensive
		// populates the starters list for the team with these 22 players
		// all are uninjured
		
		starters.addAll(getRandomUninjured11FromList(offensivePlayersInRoster));
		starters.addAll(getRandomUninjured11FromList(defensivePlayersInRoster));
		
		// the starters have been selected
	}
	
	// helper method to select 11 random uniinjured palayers from a list of players
	// returns null if the list that is passed does not have 11 players to select from
	public ArrayList<Player> getRandomUninjured11FromList(ArrayList<Player> players)
	{
		if(players.size()>11)
		{
		// figure out how to select random players
		// what is the distribution/criteria to be ued to determine which players to select.
		ArrayList<Player> selectedPlayers = new ArrayList<Player>();
		for(Player p: players)
		{
			if(!p.isInjured())
			{
				Random rand = new Random();
				// if select is true, then player is add; don't add if false
				boolean select = rand.nextBoolean();
				if(select)
				{
					players.add(p);
				}
				// select till the list has 11 players
			}
			if(selectedPlayers.size() == 11)
			{
				break;
			}
		}
		return selectedPlayers;
		}
		else
		{
			return null;
		}
	}
	
	// the team selects a new player from the players remaining in the playerPool.
	// gets the playerPool form the Main class
	
	// I need the player pool to complete the selection
	//ArrayList<Player> playerPool = Main.getPlayerPool();  //check if this is the correct class to get player pool from

	public void pickNewRandomPlayerForRoster(Player_Pool pool)
	{
		
		// select a random player from the list of players in playerPool.
		// add the player to the roster of "this" team
		// remove that player from playerPool to avoid duplicates
		
		Player player;
		Random ranIndex = new Random();
		int index = ranIndex.nextInt(pool.getSize());
		player = pool.getPlayerPool().get(index);

		pool.getPlayerPool().remove(index);
		this.roster.add(player);
	}
	
	// first get the pool of coaches in this class
	
	/*ArrayList<Coach> coaches = new ArrayList<>();
	coaches = Main.getCoachesPool(); // check if this is the correct method to get the pool of coaches from
	*/
	// picks a coach radomly from the pool of coaches to complete the team
	public void pickACoach(Coaches_Pool coaches)
	{
		// randomly select a coach from the list of coaches.
		Random rand = new Random();
		int index = rand.nextInt(coaches.size());
		Coach person = coaches.get(index);
		
		// remove the coach from the coach_Pool (coaches) - to avoid duplicates
		coaches.remove(index);
		
		// set that coach to be the coach of "this" team
		this.coach = person;
	}
	
	// a method that sets the passed in GM to the team
	public void setGM(GeneralManager gm)
	{
		this.GM = gm;
	}
	
	// calls for the entire roster of the team
	// uses the Player class to check if the players is offensive of defensive type
	// if the player is offensive - adds to the offensiveRoster
	// if the player is defensive - adds to the defensiveRoster
	public void divideRoster_OffensiveAndDefensive()
	{
		for(Player p: this.roster)
		{
			if(p.isOffensive())
			{
				this.offensiveRoster.add(p);
			}
			else
			{
				this.defensiveRoster.add(p);
			}
		}
	}
	
	public ArrayList<Player> getOffensivePlayersInRoster()
	{
		return this.offensiveRoster;
	}
	
	public ArrayList<Player> getDefensivePlayersInRoster()
	{
		return this.defensiveRoster;
	}
}
