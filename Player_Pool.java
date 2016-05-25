import java.util.ArrayList;

public class Player_Pool {
	// player pool is basically an ArrayList of players right now

	private ArrayList<Player> PlayerPool = new ArrayList<Player>();

	// The constructor accepts the size of the pool
	// This will help generalize the program in case of more changes
	public Player_Pool(int sizeOfPool) {
		// I removed throw for Exceptions to try and catch
		// throwing can make it hard to find the problem
		// plus i dont think throwing is good if you actually want to use the exception case
		try
		{
		NameGenerator randomNames = new NameGenerator();
		for (int x = 0; x < sizeOfPool; x++) {
			Player e = new Player(randomNames.randomFirstName(), randomNames.randomLastName());
			this.PlayerPool.add(e);
		}
		catch(Exception e)
		{
			System.out.println("Exception in Player_Pool constructor");
			System.exit(0);
		}

		}
	}

	public ArrayList<Player> getPlayerPool() {
		return PlayerPool;
	}

	public void setPlayerPool(ArrayList<Player> playerPool) {
		PlayerPool = playerPool;
	}

	public int getSize() {
		return PlayerPool.size();
	}
	
	// method will not work since this is only for 2 teams
	// now we can have an arbitrary number of teams
	
	// public void pickAlternator(ArrayList<Player> GMPicksTeam1,
	// 		ArrayList<Player> GMPicksTeam2, Player_Pool PlayerPool, GeneralManager GM_of_team1, GeneralManager GM_of_team2) { 
	// 	//General managers alternate between selecting of players in the PlayerPool
	// 	boolean bool = true;
	// 	for (int i = 0; i < 100; i++) {
	// 		if (bool == true) {
	// 			GMPicksTeam1.add(GM_of_team1.GeneralManagerPick(PlayerPool));
	// 			bool = false;
	// 		} else {
	// 			GMPicksTeam2.add(GM_of_team2.GeneralManagerPick(PlayerPool));
	// 			bool = true;
	// 		}
	// 	}

	// }

}
