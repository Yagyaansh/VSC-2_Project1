public class Media 
{
	/*
	 * All funds are annually updated and given to the teams
	 */
	private static double totalBaseFunds = 100; // divided equally for all teams
	private static double advertisementFund = 100;
	private static double licensingFund = 100;
	private static int numberOfTeams = Main.numberOfTeams();
	
	public static double getMediaRevenueFor(Team team)
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

	private static double getLicensingRevenue(Team team) {
		
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

	private static double getAdvertisementRevenue(Team team) {
		
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
