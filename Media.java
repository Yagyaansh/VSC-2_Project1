public class Media 
{
	/*
	 * All funds are annually updated and given to the teams
	 */
	private double totalBaseFunds; // divided equally for all teams
	private double totalAdvertisementFunds;
	private double totalLicensingFunds;
	private int numberOfTeams;
	
	
	public Media()
	{
		this.totalBaseFunds = 100;
		this.totalAdvertisementFunds = 100;
		this.totalLicensingFunds = 100;
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
		
		
		
		return revenue;
	}
	
}
