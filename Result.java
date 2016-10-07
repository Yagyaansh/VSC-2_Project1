
public class Result {

	/*
	 * Stores the season's cumulative result for every season
	 */
	
	private int wins;
	private int losses;
	private int draws;
	private Game[] games;
	
	public Result()
	{
		this.wins = 0;
		this.losses = 0;
		this.draws = 0;
		this.games = new Game[16];
	}
	
	
	/*
	 * Getters and setters for the fields in the class
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */
	public void addGame(int week, Game g)
	{
		games[week-1] = g;
	}
	
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getLosses() {
		return losses;
	}
	public void setLosses(int losses) {
		this.losses = losses;
	}
	public int getDraws() {
		return draws;
	}
	public void setDraws(int draws) {
		this.draws = draws;
	}
	public Game[] getGames()
	{
		return games;
	}
	
	/*
	 * End of Getters and Setters
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */
	
	public void incrementWins()
	{
		this.wins++;
	}
	public void incrementLosses()
	{
		this.losses++;
	}
	public void incrementDraws()
	{
		this.draws++;
	}
	
	/*
	* Creating a deep copy of Result and returning it to the calling method
	*/
	
	public Result getDeepCopy()
	{
		Result copy = new Result();
		copy.setWins(this.getWins());
		copy.setLosses(this.getLosses());
		copy.setDraws(this.getDraws());
		return copy;
	}
	
	public String toString()
	{
		String s = this.wins + "-" + this.draws + "-" + this.losses;
		return s;
	}
	
}
