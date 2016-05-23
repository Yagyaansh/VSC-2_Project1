import java.util.ArrayList;

public class Player_Pool {

	private ArrayList<Player> PlayerPool = new ArrayList<Player>();

	public Player_Pool() throws Exception{
		NameGenerator randomNames=new NameGenerator();

		for (int x = 0; x < 200; x++) {

			Player e = new Player(randomNames.randomFirstName(), randomNames.randomLastName());

			this.PlayerPool.add(e);

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
