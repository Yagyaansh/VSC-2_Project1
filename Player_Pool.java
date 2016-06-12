import java.util.ArrayList;

public class Player_Pool {

	/*
	 * Using an ArrayList structure to represent the player pool
	 * Very inefficient - replace with a better structure
	 * What about the bag class ?
	 */
	private ArrayList<Player> PlayerPool = new ArrayList<Player>();

	/*
	 * Initializes the player pool with the size of the pool 
	 * passed as a parameter
	 * All the new players are initialized here and added to the pool
	 * New players are initialized using random first name and random last name
	 */
	public Player_Pool(int sizeOfPool) {
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
	
	/*
	 * Getters and setters for the fields in the class
	 * -------------------------------------------------------------------------------------------------
	 */


	public void setPlayerPool(ArrayList<Player> playerPool) {
		PlayerPool = playerPool;
	}
	
	public ArrayList<Player> getPlayerPool() {
		return PlayerPool;
	}

	public int getSize() {
		return PlayerPool.size();
	}

	/*
	 * End of Getters and Setters
	 * -------------------------------------------------------------------------------------------------
	 */

}
