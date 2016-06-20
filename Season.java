import java.util.*;

public class Season {

	/*
	 * teams - all the teams that are playing in the season games - all the
	 * games that have been scheduled for the season - all possible games are
	 * included with each team playing every other team 2 times - once away and
	 * once at home victors - list of all the winners of every game
	 */
	
	ArrayList<Team> teams;
	ArrayList<Game> games;
	ArrayList<Team> victors;

	// Make a deep copy of the team for printing references
	ArrayList<Team> tempTeams;

	/*
	 * Initializes all the lists
	 */
	public Season() {
		this.teams = new ArrayList<Team>();
		this.games = new ArrayList<Game>();
		this.victors = new ArrayList<Team>();
		this.tempTeams = new ArrayList<Team>();

	}

	/*
	 * Accepts the list of teams and initializes the other lists
	 */
	public Season(ArrayList<Team> teams) {
		this.teams = teams;
		this.games = new ArrayList<Game>();
		this.victors = new ArrayList<Team>();
		this.tempTeams = new ArrayList<Team>();

	}

	/*
	 * Accepts the teams and games list and initializes the victors list
	 */
	public Season(ArrayList<Team> teams, ArrayList<Game> games) {
		this.teams = teams;
		this.games = games;
		this.victors = new ArrayList<Team>();
		this.tempTeams = new ArrayList<Team>();

	}

	/*
	 * this is where all the possible games in the season are set up in a list
	 * The games list will contain all the games that are to be played The team
	 * mentioned first (when creating the Game instance) is the team playing at
	 * home This is the 1st step before starting the season
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
	 * All the games in the games list are played. The results of every game are
	 * returned They are stored in the victors list
	 */
	public void play() {
		for (Game g : this.games) {
			Team victor = g.runGameSimulation();
			victors.add(victor);
		}
	}

	/*
	 * Calculates the final winner of the season return the season victor to the
	 * calling object updates the victor's revenue with the additional 1,000,000
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

		// Make a deep copy for future references
		for (Team t : this.teams) {
			tempTeams.add(t.deepCopy());
		}

		return seasonVictor;
	}

	/*
	 * Order the teams in a list depending on the win to loss ratio this is used
	 * for the second round of player drafting also used to determine worst and
	 * best team for the season Calculated as follows : Wins - Losses This is
	 * used to order the teams Ties are settled randomly for now returns the
	 * list of teams in the desired order
	 */
	public ArrayList<Team> determineLosertoWinner() {
		ArrayList<Team> order = new ArrayList<Team>();
		ArrayList<Team> temp = new ArrayList<Team>();
		temp.addAll(this.teams);
		Team loser = teams.get(0);
		while (!temp.isEmpty()) {
			int index = 0;
			for (int i = 1; i < temp.size(); i++) {
				if ((loser.getWins() - loser.getLosses()) > (temp.get(i).getWins() - temp.get(i).getLosses())) {
					loser = temp.get(i);
					index = i;
				} else if ((loser.getWins() - loser.getLosses()) == (temp.get(i).getWins() - temp.get(i).getLosses())) {
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
	 * Implements everything that is needed for the off-season period :
	 * 
	 * Age of all players is increased by 1 - Old players are retired (removed from all
	 * pools and teams) 
	 * 
	 * The fit score for every player is updated - depends on
	 * scheme value of the coach 
	 * 
	 * All the players removed due to old age are
	 * replaced by rookies players 
	 * 
	 * career ending injuries are identified
	 * they are removed from all lists they are not replaced by new players ! -
	 * check if this is what was required 
	 * 
	 * Team with the worst record for the season is given a new coach 
	 * 
	 * To fill up the empty spots in the team rosters the rookies are drafted for selection 
	 * Teams pick till they have 50 players 
	 * Teams pick in reverse order of win to loss ratio, that is, from the worst record to the best record 
	 * Every turn the player with the highest athleticism score is chosen 
	 * selection was made efficient using a PQ
	 * 
	 * -- my PQ might be working in the reverse order - that is prioritizing the
	 * min athleticism score -- we need to test this
	 */
	
	/*
	 * Questions for iteration 3 : 
	 * Do players that have faced career ending injuries have to be replaced with rookies 
	 * Currently this is not required but to maintain a 50/50 pool ratio this will be required
	 * The pool might not have an even number of players if we dont replcae the career injured type
	 * of players. In this case and many others the ratio would obviously not be 50/50.
	 * 
	 * The rookies are drafted as follows 
	 * players with higher athleticism score given priority by GMs
	 * but if you want to restrict teams to have a 50/50 roster then in addition to athleticism score
	 * the choices by GMs will be restricted by the position type  (some teams might 
	 * have to choose a defensive player even if an offensive player with a higher athleticism score is 
	 * available). 
	 * 
	 * Additions : 
	 * The type (offensive/defensive) of the rookie player added to the pool will be 
	 * dependent on the type of the player that has either retired of faced a career ending injury 
	 * GMs will have a new scheme of choosing players for rosters. (to maintain offensive to defensive
	 * ratio of 50/50) - this scheme cannot have athleticism score as the only constraint.
	 * 
	 */

	public void offSeason(Player_Pool players, Coach_Pool coachPool) {
		// MinPQ_Athleticism<Wrapper_DataKey<Player, Integer>> rookies = new MinPQ_Athleticism<>();

		/*
		* New algorithm to age and remove players : 
		* Iterate over the player pool and age all the players
		* Create a new field in players called isSelected (in roster)
		* this way you know if a player belongs to some team 
		* Iterate over the player pool again
		* If the player isSelected in roster then remove him from player pool and team roster 
		* No need to remove from starting line up because the starters are re-picked
		* 
		* If the player is not in any team then just remove from the player pool
		* players can be removed from the player pool and not team roster only due to old age
		* players not on a team roster cannot be injured
		* 
		* So these two actions must be seperated
		*/
		
		for(Player player: players.getPlayerPool())
		{
			player.increaseAge();
			if(player.getAge() == 40)
			{
				// remove the player from the player pool
				if(player.isInATeam())
				{
					// if the player belongs to a team roster
					// remove the player from the team roster too
					
					// remove player from the roster list
					// THE PLAYER SHOULD SOMEHOW POINT TO THE TEAM !!!
					// then get the team -> get the roster -> remove player from the roster
					
					// AND
					player.setIsInATeam(false);
					player.setTeam(null);
				}
			// continue; // if the player has been removed - no need to update his fit score
			}
			// we dont have the teams coach here
			// for players that belong to a team roster
			// there should be some way to retrieve coach information for the player
			// that is the player should point to his team somehow
		//	player.updateFit(coach.getScheme()); // update the player fit scores 
			// the update fit score method will have to change
			// it will have to check if the player belongs to some team 
			// if the player belongs to a team then update fit score accordingly
			// or else fit score is unchanged
			
			// we can also update fit score as we were doing before instead of adding it here.
		}
		
		for (int i = 0; i < teams.size(); i++) {
			Team team = teams.get(i);
			for (int j = 0; j < team.getRoster().size(); j++) {
				Coach coach = team.getCoach();
				Player player = team.getRoster().get(j);
				// player.increaseAge(); // this is wrong ! All the players must age not just those in team rosters !!
				player.updateFit(coach.getScheme()); // this is fine because players not in any team roster dont have a coach
			}
			
			
			// ITERATION 3 CHANGES 

			// teams.get(i).removeOldPlayers(players); // have to remove old players not in any team roster too !!
			teams.get(i).removeCareerEndingInjuredPlayers(players);
			
		}
			/*
			 * We are removing Old players and then adding rookies using the while below
			 * If career ending players are also to be replaced in the pool then 
			 * teams.get(i).removeCareerEndingInjuredPlayers(players);
			 * should also come before then while loop
			 * 
			 * then the players generated in the while loop must make sure the ratio is 
			 * 50/50 in the pool. 
			 * 
			 * The PQ might now work with this change - PQ will also have to change
			 * 
			 * 
			 * 
			 * CHANGES HAVE BEEN MADE HERE - 
			 * IF THE PROF DOESNT AGREE THEN WE WILL REVERT BACK TO OLDER VERSION
			 * 
			 * 
			 */
			
			// removed all the players from the list "player"
			// the player pool now has only that incomplete list
			// have to fill it up with players (they have to be rookies) now such that the ratio is maintained 
			
			players.fillPlayerList(Main.getPlayerPoolSize(), players.getSize(), 21);

			// DONE
			// player pool should be full again with enough players - all with age 21 !!
			// and maintaining all the ratio constraints
			
			
			
			// NOW WE HAVE TO OPTIMIZE THE RESELECTION OF ROOKIES
			
			
			// ----------- END -------------
			
			// The rookies have to re-drafted
			// If a team needs to have defensive type then we choose the defensive player with 
			// highest athleticisim score - (same for offensive type)
			// or else we just choose the player with highest athleticism score overall
			
			
			
			for (Player p: players.getPlayerPool())
			{
				if(p.getRookie() == true)
				{
					if(p.isOffensive())
					{
						// add to minPQ of Offensive rookie players
						// pqOffensive is the PQ
					}
					else
					{
						// add to minPQ of defensive rookie players
						// pqDefensive is the PQ
					}
				}
			}
			// now offensive PQ and defensive PQ have been set up
		
		// ITERATION 3 CHANGES 
		
		ArrayList<Team> teamLosstoWinOrder = new ArrayList<Team>();
		teamLosstoWinOrder = determineLosertoWinner();

		Team worstTeam = teamLosstoWinOrder.get(0);
		Coach newCoach = worstTeam.getGM().pickACoach(coachPool);
		worstTeam.setCoach(newCoach);
		
		
		// till here the new coach has been assigned to the worst team and the 2 PQs are ready
		// we have to just re-draft all the rookies till the team rosters
		// are complete and have a 50/50 ratio.
		
		redraftPlayers(pqOffensive, pqDefensive, teamLosstoWinOrder);
		
		// redrafting of all the players is complete now
		
		// int count = 1;
		// while (count != 0) {
		// 	count = 0;
		// 	for (Team t : teamLosstoWinOrder) {
		// 		if (t.getRoster().size() == 50)
		// 			continue;
		// 		count++;
		// 		t.addToRoster((Player) rookies.delMin().getData());
		// 	}
		// }

		// Reset wins and losses to prepare for next season
		// Reset revenue info
		for (Team t : this.teams) {
			t.resetScores();
			t.resetRevenue();
		}

	}
	
	/*
	* This method is used to redraft the rookies that replace the players removed from all pools
	* passes in a PQ of offensive type rookies and a PQ of defensive type rookies
	* the PQs are ordered by athleticism score
	* the 3rd parameter is the order in which teams redraft the players
	*/
	
	public void redraftPlayers(PQ pqOffensive, PQ pqDefensive, ArrayList<Team> teamLosstoWinOrder)
	{
		int rosterSize = 0;
		int offensiveSize = 0;
		int defensiveSize = 0;
		
		while // all teams do not have 50 players we keep drafting
		
		for (Team t: teamLosstoWinOrder)
		{
			rosterSize = t.getRoster().getSize();
			offensiveSize = t.getOffensiveRoster().size();
			defensiveSize = t.getDefensiveRoster().size();
			
			if(rosterSize > 50)
			{
				System.out.println("Error : Roster size has exceeded 50");
				System.exit(0);
			}
			else if(rosterSize == 50)
			{
				continue;
			}
			else if(offensiveSize == 25)
			{
				// pick the defensive player with the highest athleticism score
				// For the player that is added to the roster - set isInATeam to true !
				// player.setIsInATeam(true);
				// player.setTeam(t);
			}
			else if(defensiveSize == 25)
			{
				// pick the offesnive player with the highest athleticism score
				// For the player that is added to the roster - set isInATeam to true !
				// player.setIsInATeam(true);
				// player.setTeam(t);
			}
			else {
				// when the team needs both offensive and defensive type
				// compare the best (highest athleticism score) offensive player
				// to the best defensive player
				// add the one with the higher athleticism score to the team
				
				// For the player that is added to the roster - set isInATeam to true !
				// player.setIsInATeam(true);
				// player.setTeam(t);

			}
		}
	}

	/*
	 * Printing methods
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */

	public void printSeason() {
		for (int i = 0; i < games.size(); i++) {
			Game g = this.games.get(i);
			System.out.println("Game #" + (i + 1) + " Winner: " + g.getVictor().getTeamName());
			g.printGame();
			System.out.println(
					"----------------------------------------------------------------------------------------");
		}
	}
	
	

	public void printRosterDetails() {
		for (int i = 0; i < games.size(); i++) {
			Game g = this.games.get(i);
			System.out.println("Game #" + (i + 1));
			g.getTempTeam1().printTeam();
			g.getTempTeam2().printTeam();
			System.out.println(
					"----------------------------------------------------------------------------------------");
		}
	}

	/*
	 * End of printing methods
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */

}
