
public class Sponsor 
{
	private Team team;
	private int contractYears;
	private double annualSponsorship;
	private String hometown;
	private int population;
	
	/*
	 * Getters and setters for the fields in the class
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */
	
	public Sponsor(Team team)
	{
		this.team = team;
		this.hometown = team.getHometown();
		this.population = team.getPopulation();
		this.determineContractYears();
		this.determineAnnualSponsorship();
		
	}

	public String getHometown() {
		return hometown;
	}
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public int getContractYears() {
		return contractYears;
	}
	public void setContractYears(int contractYears) {
		this.contractYears = contractYears;
	}
	public double getAnnualSponsorship() {
		return annualSponsorship;
	}
	public void setAnnualSponsorship(double annualSponsorship) {
		this.annualSponsorship = annualSponsorship;
	}
	
	/*
	 * End of Getters and Setters
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */
	

	private void determineAnnualSponsorship() 
	{
		double sponsorship = 0.0;
		
		/*
		 * TODO : 
		 * Depending on population home town and previous record the team
		 * is given a sponsorship
		 */
		
		this.setAnnualSponsorship(sponsorship);
	}

	private void determineContractYears() 
	{
		int contractYear = 2;
		
		/*
		 * TODO : 
		 * I don't know if we will want to change this 
		 * But currently the length of all contract years is 2
		 */
		
		this.setContractYears(contractYear);
	}
	
	
}
