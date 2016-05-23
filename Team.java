import java.util.ArrayList;

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

	Team(String name, String hometown, GeneralManager GM, Coach coach,
			ArrayList<Player> roster, ArrayList<Player> starters) {

		if (hometown == "Chicago") {
			population = 2695598;
		} else if (hometown == "Phoenix") {
			population = 1445632;
		}

		this.name = name;
		this.coach = coach;
		this.GM = GM;
		this.hometown = hometown;
		this.roster = roster;
		this.starters = starters;
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

	public void updateGrossRevenue() {
		grossRevenue = grossRevenue + population / 10;
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
			expenses += starters.get(i).getSalary();
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
				
				int rand = // generate 1 or 0 randomly .. select player if its 1, dont select if 0
				if(rand == 1)
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
}
