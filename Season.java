import java.util.*;

public class Season {
	
	/*
	 * teams - all the teams that are playing in the season
	 * games - all the games that have been scheduled for the season - all possible games are incuded
	 * with each team playing every other team 2 times - once away and once at home
	 * victors - list of all the winners of every game
	 */
	ArrayList<Team> teams; 
	ArrayList<Game> games; 
	ArrayList<Team> victors; 

	/*
	 * Initializes all the lists
	 */
	public Season() {
		this.teams = new ArrayList<Team>();
		this.games = new ArrayList<Game>();
		this.victors = new ArrayList<Team>();
	}

	/*
	 * Accepts the list of teams and initializes the other lists
	 */
	public Season(ArrayList<Team> teams) {
		this.teams = teams;
		this.games = new ArrayList<Game>();
		this.victors = new ArrayList<Team>();
	}

	/*
	 * Accepts the teams and games list and initializes the victors list
	 */
	public Season(ArrayList<Team> teams, ArrayList<Game> games) {
		this.teams = teams;
		this.games = games;
		this.victors = new ArrayList<Team>();
	}


	/*
	 * this is where all the possible games in the season are set up in a list
	 * The games list will contain all the games that are to be played
	 * The team mentioned first (when creating the Game instance)
	 * is the team playing at home
	 * This is the 1st step before starting the season
	 * 
	 */
	public void startSeason() {
		for (int i = 0; i < teams.size() - 1; i++) {
			for (int j = i + 1; j < teams.size(); j++) {
				Team team1 = teams.get(i);
				Team team2 = teams.get(j);
				games.add(new Game(team1, team2)); 
				games.add(new Game(team2, team1)); 
			}
		}

	}
	
	/*
	 * All the games in the games list are played. 
	 * The results of every game are returned
	 * They are stored in the victors list
	 */
	public void play() {
		for (Game g : this.games) {
			Team victor = g.runGameSimulation();
			victors.add(victor);
		}
	}

	/*
	 * Calculates the final winner of the season
	 * return the season victor to the calling object
	 * updates the victor's revenue with the additional 1,000,000
	 */
	public Team seasonResult() {
		Team seasonVictor = teams.get(0);
		for (int i = 1; i < teams.size(); i++) {
			if (seasonVictor.getWins() < teams.get(i).getWins()) {
				seasonVictor = teams.get(i);
			} else if (seasonVictor.getWins() == teams.get(i).getWins()) {
				Random rand = new Random();
				if (rand.nextBoolean()) {
					seasonVictor = teams.get(i);
				}
			}
		}
		seasonVictor.updateSeasonWinningTeamRevenue();
		return seasonVictor;
	}

	
	/*
	 * Order the teams in a list depending on the win to loss ratio
	 * this is used for the second round of player drafting
	 * also used to determine worst and best team for the season
	 * Calculated as follows : 
	 * Wins - Losses
	 * This is used to order the teams
	 * Ties are settled randomly for now
	 * returns the list of teams in the desired order
	 */
	public ArrayList<Team> determineLosertoWinner() {
		ArrayList<Team> order = new ArrayList<Team>();
		ArrayList<Team> temp = new ArrayList<Team>();
		temp.addAll(this.teams);
		Team loser = teams.get(0);
		while(!temp.isEmpty()){
			int index=0;
			for (int i=1; i< temp.size(); i++) {
				if ((loser.getWins() - loser.getLosses()) > (temp.get(i).getWins()
						- temp.get(i).getLosses())) {
					loser = temp.get(i);
					index = i;
				} else if ((loser.getWins() - loser.getLosses()) == (temp.get(i).getWins()
						- temp.get(i).getLosses())) {
					Random rand = new Random();
					if (rand.nextBoolean()) {
						loser = teams.get(i);
						index = i;
					}
				}
			}
			order.add(loser);
			temp.remove(index);
		}
		return order;
	}
	
	/*
	 * Implements everything that is needed for the off-season period
	 * Age of all players is increased by 1 - Old players are retired (removed from all pools and teams)
	 * The fit score for every player is updated - depends on scheme value of the coach
	 * All the players removed due to old age are replaced by rookies
	 * players with career ending injuries are identified
	 * they are removed from all lists
	 * they are not replaced by new players ! - check if this is what was required
	 * team with the worst record for the season is given a new coach
	 * to fill up the empty spots in the team rosters the rookies are drafted for selection
	 * Teams pick till they have 50 players
	 * Teams pick in reverse order or win to loss ratio
	 * that is from the worst record to the best record
	 * Every turn the player with the highest athleticism score is chosen
	 * selection was made efficient using a PQ 
	 * 
	 * -- my PQ might be working in the reverse order - that is prioritizing the min athleticism score
	 * -- we need to test this 
	 */

	public void offSeason(Player_Pool players, Coach_Pool coachPool) 
	{
		MinPQ_Athleticism<Wrapper_DataKey<Player,Integer>> rookies = new MinPQ_Athleticism<>();
		
		for (int i = 0; i < teams.size(); i++) 
		{
			Team team = teams.get(i);
			for (int j = 0; j < team.getRoster().size(); j++) 
			{
				Coach coach = team.getCoach();
				Player player = team.getRoster().get(j);	
				player.increaseAge();
				player.updateFit(coach.getScheme());
			}
			teams.get(i).removeOldPlayers(players);
			while(players.getPlayerPool().size() < 10000)
			{
				try
				{
					NameGenerator randomNames = new NameGenerator();
					Player player = new Player(randomNames.randomFirstName(), randomNames.randomLastName(), 21);
					players.getPlayerPool().add(player);
					Wrapper_DataKey<Player,Integer> w = new Wrapper_DataKey<Player,Integer>(player,player.getAthleticism());
					rookies.insert(w);
				}
				catch(Exception e)
				{
					e.printStackTrace();
					System.exit(0);
				}
			}
			
			teams.get(i).removeCareerEndingInjuredPlayers(players);
		}	
		ArrayList<Team> teamLosstoWinOrder = new ArrayList<Team>();
		teamLosstoWinOrder = determineLosertoWinner();
		
		Team worstTeam = teamLosstoWinOrder.get(0);
		Coach newCoach = worstTeam.getGM().pickACoach(coachPool);
		worstTeam.setCoach(newCoach);
		int count = 1;
		while(count != 0)
		{
		count = 0;
		for(Team t: teamLosstoWinOrder)
		{
			if(t.getRoster().size() == 50)
				continue;
			count++;
			t.addToRoster((Player)rookies.delMin().getData()); 
		}
		}
	}
	
	
	/*
	 * Printing methods
	 * -------------------------------------------------------------------------------------------------
	 */
	
	public void printSeason(){
		for(int i=0; i<games.size(); i++){
			Game g = this.games.get(i);
			System.out.println("Game #" + (i+1) + " Winner: " + g.getVictor().getTeamName());
			g.printGame();
			System.out.println("----------------------------------------------------------------------------------------");
		}
	}
	
	public void printRosterDetails() {
		for(int i=0; i<games.size(); i++){
			Game g = this.games.get(i);
			System.out.println("Game #" + (i+1));
			g.getTeam1().printTeam();
			g.getTeam2().printTeam();
			System.out.println("----------------------------------------------------------------------------------------");
		}
	}
	

	/*
	 * End of printing methods
	 * -------------------------------------------------------------------------------------------------
	 */
	
}
