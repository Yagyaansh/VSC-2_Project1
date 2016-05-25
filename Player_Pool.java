import java.util.ArrayList;

public class Player_Pool {
	// player pool is basically an ArrayList of players right now

	private ArrayList<Player> PlayerPool = new ArrayList<Player>();

	// The constructor accepts the size of the pool
	// This will help generalize the program in case of more changes
	public Player_Pool(int sizeOfPool) {
		// I removed throw for Exceptions to try and catch
		// throwing can make it hard to find the problem
		// plus i dont think throwing is good if you actually want to use the
		// exception case
		try {
			NameGenerator randomNames = new NameGenerator();
			for (int x = 0; x < sizeOfPool; x++) {
				Player e = new Player(randomNames.randomFirstName(), randomNames.randomLastName());
				this.PlayerPool.add(e);
			}
		} catch (Exception e) {
			System.out.println("Exception in Player_Pool constructor");
			System.exit(0);
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

	public void pickAlternator(Player_Pool players, ArrayList<Team> teams) {
		// General managers alternate between selecting of players in the
		// PlayerPool
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < teams.size(); j++) {
				teams.get(j).getGM().GeneralManagerPickPlayers(players);
			}
		}

	}

}
