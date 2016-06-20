import java.util.ArrayList;

public class Player_Pool {

	/*
	 * Using an ArrayList structure to represent the player pool
	 * Very inefficient - replace with a better structure
	 * What about the bag class ?
	 */
	
	private ArrayList<Player> PlayerPool;

	/*
	 * *** Note : the adding of players has been moved to the method fillPlayerList() in this class
	 * makes it easier to refill partial lists - used in off season and stuff
	 * 
	 * 
	 * Initializes the player pool with the size of the pool 
	 * passed as a parameter
	 * All the new players are initialized here and added to the pool
	 * New players are initialized using random first name and random last name
	 * 
	 * -- the number of offensive players variable is to keep a 50/50 distribution between
	 * offensive and defensive players
	 * 
	 * Algorithm to maintain 50/50 ratio : 
	 * Keep track of number of offensive and defensive type of players
	 * Once either type reaches 50 % of pool size
	 * Add the last player of that type
	 * Fill the remaining pool with players of the other type
	 * 
	 * If neither type of players has reached its limit
	 * Add them to the pool
	 * 
	 * RESTRICTION - Pool size must be an even number for the 50/50 restriction to be implemented
	 * 
	 */
	
	public Player_Pool(int sizeOfPool) {
		this.PlayerPool = new ArrayList<Player>();
		this.fillPlayerList(sizeOfPool,0);
	}
	

	/*
	 * Getters and setters for the fields in the class
	 * -------------------------------------------------------------------------------------------------
	 */


	public void setPlayerPool(ArrayList<Player> playerPool) {
		this.PlayerPool = playerPool;
	}
	
	public ArrayList<Player> getPlayerPool() {
		return this.PlayerPool;
	}

	public int getSize() {
		return this.PlayerPool.size();
	}

	/*
	 * End of Getters and Setters
	 * -------------------------------------------------------------------------------------------------
	 */
	
	/*
	 * Fills the player list with the restriction of maintaining a 50/50 ratio between
	 * offensive players and defensive players
	 * 
	 * the size of the pool parameter makes sure the total size of the player pool never exceeds this
	 * the number of players already there makes it very flexible :
	 * in many instances in the code the player pool has to be "re-filled"
	 * This is very useful there
	 * 
	 * Creating the pool from scratch is basically passing 0 as the second parameter
	 * 
	 */
	
	public void fillPlayerList(int sizeOfPool, int numberOfPlayersAlreadyThere) {
		try {
			NameGenerator randomNames = new NameGenerator();
			int numberOfOffensivePlayers = 0;
			int numberOfDefensivePlayers = 0;
			for (int x = numberOfPlayersAlreadyThere + 1; x <= sizeOfPool; x++) {
				Player player = new Player(randomNames.randomFirstName(), randomNames.randomLastName());
				if (player.isOffensive())
				{
					numberOfOffensivePlayers++;
				}
				numberOfDefensivePlayers = x - numberOfOffensivePlayers;
				if (numberOfOffensivePlayers == (sizeOfPool/2))
				{
					this.PlayerPool.add(player);
					while (x < sizeOfPool)
					{
						// add players that are defensive
						Player p = new Player(randomNames.randomFirstName(), randomNames.randomLastName(), false);
						this.PlayerPool.add(p);
						x++;
					}
					break; // all the players have been added so skip the rest of the iterations in for loop
				}
				if (numberOfDefensivePlayers == (sizeOfPool/2))
				{
					this.PlayerPool.add(player);
					while (x < sizeOfPool)
					{
						// add players that are offensive
						Player p = new Player(randomNames.randomFirstName(), randomNames.randomLastName(), true);
						this.PlayerPool.add(p);
						x++;
					}
					break; // all the players have been added so skip the rest of the iterations in for loop
				}
				this.PlayerPool.add(player);
			}
		} catch (Exception e) {
			System.out.println("Exception in Player_Pool fillPlayerList without age");
			System.exit(0);
		}
	}
	
	/*
	 * 
	 * DIFFERENCE FROM THE PREVIOUS METHOD IS : 
	 * The age for all the players that fill the list is accepted here as the second parameter
	 * This is used to fill up the list with all rookies in the off season
	 * that is, if the second field is "21" then all the players added to the pool
	 * to complete the list will be 21 years old
	 * all other constraints will also be maintained.
	 * 
	 * Fills the player list with the restriction of maintaining a 50/50 ratio between
	 * offensive players and defensive players
	 * 
	 * the size of the pool parameter makes sure the total size of the player pool never exceeds this
	 * the number of players already there makes it very flexible :
	 * in many instances in the code the player pool has to be "re-filled"
	 * This is very useful there
	 * 
	 * Creating the pool from scratch is basically passing 0 as the second parameter
	 * 
	 */
	
	public void fillPlayerList(int sizeOfPool, int numberOfPlayersAlreadyThere, int age) {
		try {
			NameGenerator randomNames = new NameGenerator();
			int numberOfOffensivePlayers = 0;
			int numberOfDefensivePlayers = 0;
			for (int x = numberOfPlayersAlreadyThere + 1; x <= sizeOfPool; x++) {
				Player player = new Player(randomNames.randomFirstName(), randomNames.randomLastName(), age);
				if (player.isOffensive())
				{
					numberOfOffensivePlayers++;
				}
				numberOfDefensivePlayers = x - numberOfOffensivePlayers;
				if (numberOfOffensivePlayers == (sizeOfPool/2))
				{
					this.PlayerPool.add(player);
					while (x < sizeOfPool)
					{
						// add players that are defensive
						Player p = new Player(randomNames.randomFirstName(), randomNames.randomLastName(), age, false);
						this.PlayerPool.add(p);
						x++;
					}
					break; // all the players have been added so skip the rest of the iterations in for loop
				}
				if (numberOfDefensivePlayers == (sizeOfPool/2))
				{
					this.PlayerPool.add(player);
					while (x < sizeOfPool)
					{
						// add players that are offensive
						Player p = new Player(randomNames.randomFirstName(), randomNames.randomLastName(), age, true);
						this.PlayerPool.add(p);
						x++;
					}
					break; // all the players have been added so skip the rest of the iterations in for loop
				}
				this.PlayerPool.add(player);
			}
		} catch (Exception e) {
			System.out.println("Exception in Player_Pool fillPlayerList with age");
			System.exit(0);
		}
	}

}
