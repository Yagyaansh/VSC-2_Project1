import java.util.Random;

public class Owner {
	/*
	 * All the fields required for the team class are declared here
	 */
	private int happyValue;
	private int unhappyValue;
	private int patience;
	private String firstName;
	private String lastName;
	private Team team;

	// Maybe delete this
	private int lastYearWins;
	private int lastYearLosses;

	/*
	 * Constructors for the Owner class
	 */

	public Owner(Team team) {
		this.happyValue = generateHappyValue();
		this.unhappyValue = generateUnhappyValue();
		this.patience = generatePatience();
		NameGenerator randomNames = new NameGenerator();
		this.firstName = randomNames.randomFirstName();
		this.lastName = randomNames.randomLastName();
		this.team = team;
	}

	public Owner() {
		this.firstName = "";
		this.lastName = "";
		this.happyValue = 0;
		this.unhappyValue = 0;
		this.patience = 0;
	}

	/*
	 * Random generators for the fields of the Owner class
	 */

	public int generateHappyValue() {
		return 12;
	}

	public int generateUnhappyValue() {
		return 6;
	}

	public int generatePatience() {
		Random rand = new Random();
		return ((rand.nextInt(7)) - 3);
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

	public void setTeam(Team t) {
		this.team = t;
	}

	public Team getTeam() {
		return this.team;
	}

	/*
	 * End of Getters and Setters
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */

	/*
	 * Owner's might fire coaches during the off season Criteria : First off
	 * season - none of the coaches get fired If the team won atleast owner's
	 * happyValue number of games then the coach will stay If the team won
	 * lesser than or equal to the owner's unhappyValue number of games then the
	 * coach will be fired
	 * 
	 */

	/*
	 * Determines if the coach needs to be fired or not
	 * 
	 * If the team won at least Happy games, then the owner will not fire the
	 * Coach. If the team won equal to or fewer than Unhappy games, then the
	 * Owner will fire the Coach. If the team won a number of games less than
	 * Happy and more than Unhappy, then the Owner will retain the Coach so long
	 * as this yearÂ�fs number of wins is greater than Patience plus last
	 * yearÂ�fs wins.
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
			if (teamWins > (this.patience + this.lastYearWins)) {
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

	// Just need the team here.
	// Already being called by the owner object.
	// the owner has a reference to the team that he/she owns

	public boolean fireCoach() {
		Result current = this.team.getCurrentSeasonResult();
		Result previous = this.team.getPreviousSeasonResult();
		if (previous == null)
			return false;
		if (current.getWins() >= this.happyValue)
			return false;
		if (current.getWins() <= this.unhappyValue) {
			resetOwnersFeelings();
			return true;
		}
		if (current.getWins() > this.unhappyValue && current.getWins() < this.happyValue) {
			if (current.getWins() > (previous.getWins() + this.patience))
				return false;
			resetOwnersFeelings();
			return true;
		}
		return false;
	}

	public void resetOwnersFeelings() {
		this.happyValue = generateHappyValue();
		this.unhappyValue = generateUnhappyValue();
		this.patience = generatePatience();
	}

	/*
	 * Print methods
	 */

	public void printOwner() {
		System.out.print("Owner: " + this.firstName + " " + lastName + " , ");
		System.out.print("Happy Value: " + this.happyValue + " , ");
		System.out.print("Unhappy Value: " + this.unhappyValue + " , ");
		System.out.print("Patience: " + this.patience + " , ");
	}

	/*
	 * Deep copy method used to store information for printing purposes
	 */
	public Owner deepCopy() {
		Owner o = new Owner();
		o.happyValue = this.happyValue;
		o.unhappyValue = this.unhappyValue;
		o.patience = this.patience;
		o.firstName = this.firstName;
		o.lastName = this.lastName;
		o.team = this.team;
		return o;
	}

}
