public class Media 
{
	/*
	 * All funds are annually updated and given to the teams
	 */
	private double totalBaseFunds; // divided equally for all teams
	private double advertisementFund;
	private double licensingFund;
	private int numberOfTeams;
	
	
	public Media()
	{
		this.totalBaseFunds = 100;
		this.advertisementFund = 100;
		this.licensingFund = 100;
		this.numberOfTeams = Main.numberOfTeams();
	}
	
	public double getMediaRevenueFor(Team team)
	{
		double revenue = 0.0;
		
		/*
		 * TODO : 
		 * Depending on the team, population, home town and how well its performing
		 * We have to figure out how much each team makes from
		 * media coverage and licensing
		 */
		
		double baseRevenue = totalBaseFunds / numberOfTeams;
		double advertisementRevenue = getAdvertisementRevenue(team);
		double licensingRevenue = getLicensingRevenue(team);
		
		revenue = baseRevenue + advertisementRevenue + licensingRevenue;
		
		return revenue;
	}

	private double getLicensingRevenue(Team team) {
		
		double revenue = 0.0;
		
		double factor = 1.0;
		
		/*
		 * TODO : 
		 * Use the home town, population and stadium capacity
		 * to calculate a number between -0.5 and +0.5
		 * Store the value in factor
		 */
		
		revenue = licensingFund + (licensingFund*factor);
	
		return revenue;
	}

	private double getAdvertisementRevenue(Team team) {
		
		double revenue = 0.0;
		
		double factor = 1.0;
		
		/*
		 * TODO : 
		 * Use the current team form to calculate a number between -0.5 and +0.5
		 * Store the value in factor
		 */
		
		revenue = advertisementFund + (advertisementFund*factor);
	
		return revenue;
	}
	
}
