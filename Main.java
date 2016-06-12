import java.util.ArrayList;
import java.util.Collections;

public class Main {
	
	/*
	 * README :
	 * 
	 * All three pools are created here.
	 * Instead of using the main it is easier to create an instance of the Main class
	 * 
	 * The start method is basically the entire main method we has earlier
	 * The skeleton description is as follows :
	 * 
	 * The generalized create teams method us used for creating the teams.
	 * The names of the teams have to be hard-coded in any way so I hard-coded them in alphabetical order
	 * The method creates a list of teams
	 * Each team is assigned a GM (randomly)
	 * The GM then randomly picks a coach for the team
	 * The GM then picks players, taking turns as desired. This can handle any number of teams so we 
	 * don't have to make changes
	 * The roster for each team is then divided into an offensiveRoster and a defensiveRoster 
	 * A list of all the teams is then returned by the method createTeams()
	 * Then all the seasons are created using the Season class
	 * The seasons are added to a list
	 * The number of seasons is hard coded since the number should notchange too much
	 * Each season is then simulated and the results are stored in a list called results
	 * This stores all the final results for the simulation
	 * 
	 * 
	 */
	private GeneralManager_Pool generalManagerPool;
	private Coach_Pool coachPool;
	private Player_Pool playerPool;
	
	/*
	 * Constructor - hard-coded with the sizes of the pools
	 * Change pool size here if needed
	 */
	
	public Main()
	{
		this.generalManagerPool = new GeneralManager_Pool(100);
		this.coachPool = new Coach_Pool(100);
		this.playerPool = new Player_Pool(10000);
	}
	
	/*
	 * Only creates an object called m1 and then runs the simulation. 
	 * Code begins here.
	 */
	public static void main(String[] args)
	{
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

		teams = createTeams(4, getGeneralManagerPool(), getCoachPool(), getPlayerPool());

		ArrayList<Season> seasons = new ArrayList<Season>();
		ArrayList<Team> results = new ArrayList<Team>();
		int numberOfSeasons = 4; 

		for (int i = 0; i < numberOfSeasons; i++) 
		{
			seasons.add(new Season(teams));
		}

		for (Season s : seasons) {
			s.startSeason();
			s.play();
			results.add(s.seasonResult());
			s.offSeason(this.getPlayerPool(), this.getCoachPool());
		}
		printEverything(seasons);
	}
	
	/*
	 * Generalizes team creation
	 * Assigns GM, Coach, Players to roster, and divides into offensive and devensive rosters.
	 * returns a list of teams participating in the simulation
	 */
	
	public static ArrayList<Team> createTeams(int numberOfTeams, GeneralManager_Pool generalManagerPool, Coach_Pool coachPool, Player_Pool playerPool)
	{
		 ArrayList<Team> allTeams = new ArrayList<Team>();
		 String[] teamNames = {"Boston Paatriots","Bears","Cardinals","Pittsburgh Steelers"};
		 String[] teamHometowns = {"Boston","Chicago","Phoenix","Pittsburgh"};
		 
		 for(int i=0; i<numberOfTeams; i++)
		 {
			 Team t = new Team(teamNames[i],teamHometowns[i], generalManagerPool.getRandomGM());
			 Coach coach = t.getGM().pickACoach(coachPool);
			 t.setCoach(coach);
			 allTeams.add(t);
		 }
		 
		for (int i = 0; i < 50; i++) 
		{
			for(Team t : allTeams)
			{
				Player player = t.getGM().pickAPlayer(playerPool);
				t.addToRoster(player);
			}
		}

		 for(Team t: allTeams)
		 {
			 t.divideRoster_OffensiveAndDefensive();
		 }
		 
		 return allTeams;
	 }
		
	/*
	 * Getters and setters for GeneralManagerPool, PLayerPool and CoachPool
	 * -----------------------------------------------------------------------------------------------------
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

	/*
	 * End of Getters and Setters
	 * -----------------------------------------------------------------------------------------------------
	 */
	

	/*
	 * Helper print methods for debugging
	 * -----------------------------------------------------------------------------------------------------
	 */
	
	public static void printEverything(ArrayList<Season> seasons){
		printSeasonDetails(seasons);
		printTeamDetails(seasons);
		printRevenueDetails(seasons);
	}
	
	public static void printSeasonDetails(ArrayList<Season> seasons){
		// Print out season outcome and game statistics in each season
		for (int i=0; i< seasons.size(); i++){
			Season s = seasons.get(i);
			System.out.println("SEASON #" + (i+1) + " Victor: " + s.seasonResult().getTeamName());
			s.printSeason();
			System.out.println("");
		}
	}
	
	public static void printTeamDetails(ArrayList<Season> seasons){
		for (int i=0; i<seasons.size(); i++) {
			Season s = seasons.get(i);
			System.out.println("SEASON #" + (i+1));
			s.printRosterDetails();
			System.out.println("");
		}
	}

	public static void printRevenueDetails(ArrayList<Season> seasons){
		for(int i=0; i< seasons.size(); i++) {
			Season s = seasons.get(i);
			System.out.println("SEASON #" + (i+1));
			for(int j=0; j< s.teams.size(); j++){
				s.teams.get(j).profitPrinter();
			}
			System.out.println("");
		}

	}
	
	/* All the print methods are between the lines
	 * -----------------------------------------------------------------------------------------------------
	 */
}
	