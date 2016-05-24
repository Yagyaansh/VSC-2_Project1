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

	Team(String name, String hometown, GeneralManager_Pool GMPool, Coaches_Pool coachPool) {

		if (hometown == "Chicago") {
			population = 2695598;
		} else if (hometown == "Phoenix") {
			population = 1445632;
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

}
