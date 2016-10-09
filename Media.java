public class Media 
{
	private double totalAnnualFunds;
	
	public Media()
	{
		this.setTotalAnnualFunds(100000000);
	}


	/*
	 * Getters and setters for the fields in the class
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */
	
	public double getTotalAnnualFunds() {
		return totalAnnualFunds;
	}

	public void setTotalAnnualFunds(double totalAnnualFunds) {
		this.totalAnnualFunds = totalAnnualFunds;
	}
	
	/*
	 * End of Getters and Setters
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */
	
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
