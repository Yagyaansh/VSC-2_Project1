
public class Result {

	/*
	 * Stores the season's cumulative result for every season
	 */
	
	private int wins;
	private int losses;
	private int draws;
	
	public Result()
	{
		this.wins = 0;
		this.losses = 0;
		this.draws = 0;
	}
	
	
	/*
	 * Getters and setters for the fields in the class
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */
	
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
	
}
