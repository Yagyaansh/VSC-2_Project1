
public class Owner 
{
	/*
	 * All the fields required for the team class are declared here
	 */
	private int happyValue;
	private int unhappyValue;
	private int patience;
	private String firstName;
	private String lastName;
	private Team team;
	
	/*
	 * Constructors for the Owner class
	 */
	
	public Owner()
	{
		this.happyValue = generateHappyValue();
		this.unhappyValue = generateUnhappyValue();
		this.patience = generatePatience();
		NameGenerator randomNames = new NameGenerator();
		this.firstName = randomNames.randomFirstName();
		this.lastName = randomNames.randomLastName();
		this.team = new Team();
	}
	
	/*
	 * Random generators for the fields of the Owner class
	 */
	
	public int generateHappyValue()
	{
		return 12;
	}
	
	public int generateUnhappyValue()
	{
		return 6;
	}
	
	public int generatePatience()
	{	
		return ((int) (Math.random() * (3 - (-3)) + (-3)));
	}
	
	/*
	 * Getters and setters for the fields in the class
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */
	
	public String getFirstName() {
		return this.firstName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public int getHappyValue() {
		return happyValue;
	}
	public void setHappyValue(int happyValue) {
		this.happyValue = happyValue;
	}
	public int getUnhappyValue() {
		return unhappyValue;
	}
	public void setUnhappyValue(int unhappyValue) {
		this.unhappyValue = unhappyValue;
	}
	public int getPatience() {
		return patience;
	}
	public void setPatience(int patience) {
		this.patience = patience;
	}
	
	/*
	 * End of Getters and Setters
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */
	 
	 /*
	 * Owner's might fire coaches during the off season
	 * Criteria : 
	 * First off season - none of the coaches get fired
	 * If the team won atleast owner's happyValue number of games then the coach will stay
	 * If the team won lesser than or equal to the owner's unhappyValue number of games then the coach will be fired
	 * 
	 */
	 public boolean shouldCoachBeFired()
	 {
	   	/* 
	   	* We need to store history of performances for each team over every season
	   	* We need that for the output display anyway
	   	* Once we have done that we can get the history of season performances
	   	* use that here to determine if the coach will be fired or not 
	   	*/
	 }
	 


	/*
	 * Determines if the coach needs to be fired or not
	 * 
	 * If the team won at least Happy games, then the owner will not fire the
	 * Coach. If the team won equal to or fewer than Unhappy games, then the
	 * Owner will fire the Coach. If the team won a number of games less than
	 * Happy and more than Unhappy, then the Owner will retain the Coach so long
	 * as this yearfs number of wins is greater than Patience plus last yearfs
	 * wins.
	 * 
	 * Overwrites last years wins and losses variables
	 */
	public boolean fireCoach(int teamWins, int teamLosses) {
		if (teamWins >= this.happyValue) {
			this.lastYearWins = teamWins;
			this.lastYearLosses = teamLosses;
			return false;
		} else if (teamWins <= this.unhappyValue) {
			this.lastYearWins = teamWins;
			this.lastYearLosses = teamLosses;
			return true;
		} else if (teamWins >= this.unhappyValue && teamWins <= this.happyValue) {
			if (teamWins > (this.patienceValue + this.lastYearWins)) {
				this.lastYearWins = teamWins;
				this.lastYearLosses = teamLosses;
				return false;
			} else {
				this.lastYearWins = teamWins;
				this.lastYearLosses = teamLosses;
				return true;
			}
		} else {
			this.lastYearWins = teamWins;
			this.lastYearLosses = teamLosses;
			return true;
		}
	}
}


}
