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
	
	public void pickAlternator(Player_Pool players, ArrayList<Team> teams) { 
		//General managers alternate between selecting of players in the PlayerPool
		for (int i = 0; i<50; i++){
			for (int j=0; j<teams.size(); j++){
				teams.get(j).getGM().GeneralManagerPick(players);
			}
		}
		
		

	}

}
