import java.util.Random;

public class Stadium 
{
	private Team team;
	private int capacityRegular;
	private int capacityLuxury;
	private double priceRegular;
	private double priceLuxury;
	private int age;
	private String hometown;
	private int population;
	private double costForMaintainance;
	private double matchHype; // between 0.75 and 2
	
	public Stadium(Team team)
	{
		this.team = team;
		this.hometown = team.getHometown();
		this.population = team.getPopulation();
		this.age = 0;
		this.determinedCapacityRegular();
		this.determinedCapacityLuxury();
		this.determineBasePrices();
		this.calculateCostOfMaintainance();
		this.setMatchHype(1.0);
		this.determineTicketPicesAfterHype();
	}
	


	/*
	 * Getters and setters for the fields in the class
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */
	
	public double getCostForMaintainance() {
		return costForMaintainance;
	}

	public void setCostForMaintainance(double costForMaintainance) {
		this.costForMaintainance = costForMaintainance;
	}

	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public int getCapacityRegular() {
		return capacityRegular;
	}
	public void setCapacityRegular(int capacityRegular) {
		this.capacityRegular = capacityRegular;
	}
	public int getCapacityLuxury() {
		return capacityLuxury;
	}
	public void setCapacityLuxury(int capacityLuxury) {
		this.capacityLuxury = capacityLuxury;
	}
	public double getPriceRegular() {
		return priceRegular;
	}
	public void setPriceRegular(double priceRegular) {
		this.priceRegular = priceRegular;
	}
	public double getPriceLuxury() {
		return priceLuxury;
	}
	public void setPriceLuxury(double priceLuxury) {
		this.priceLuxury = priceLuxury;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
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
	
	public double getMatchHype() {
		return matchHype;
	}

	public void setMatchHype(double matchHype) {
		this.matchHype = matchHype;
	}
	
	
	/*
	 * End of Getters and Setters
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */
	
	public double revenueGenerated()
	{
		Random rand = new Random();
		int attendantsRegular = rand.nextInt((int) 0.25*capacityRegular + 1) + ((int) 0.75*capacityRegular);
		int attendantsLuxury = rand.nextInt((int) 0.5*capacityLuxury + 1) + ((int) 0.45*capacityLuxury);
		double revenueRegular = (attendantsRegular*priceRegular);
		double revenueLuxury = (attendantsLuxury*priceLuxury);
		return (revenueRegular + revenueLuxury);
	}
	
	public void calculateCostOfMaintainance()
	{
		/*
		 * Set cost per week !!
		 */
		double costRegular = capacityRegular * 100;
		double costLuxury = capacityLuxury * 5000;
		this.setCostForMaintainance(costRegular + costLuxury);
	}
	
	public void determineTicketPicesAfterHype()
	{
		this.setPriceRegular(priceRegular*matchHype);
		this.setPriceLuxury(priceLuxury*(matchHype+0.1));
	}
	
	public void determinedCapacityRegular()
	{
		String hometown = this.hometown;
		int poulation = this.population;
		
		int capacity = 0;
		
		/*
		 * TODO : 
		 * We can determine the capacity depending on home-town
		 * and towns population
		 * What data and teams to use for this ?
		 */
		
		this.setCapacityRegular(capacity);
	}
	
	public void determinedCapacityLuxury()
	{
		String hometown = this.hometown;
		int poulation = this.population;
		
		int capacity = 0;
		
		/*
		 * TODO : 
		 * We can determine the capacity depending on home-town
		 * and towns population
		 * What data and teams to use for this ?
		 */
		
		this.setCapacityLuxury(capacity);
	}
	
	public void incrementAge()
	{
		this.age++ ;
	}
	

	private void determineBasePrices() 
	{
		String hometown = this.hometown;
		int poulation = this.population;
		
		double basePriceRegular = 0.0;
		double basePriceLuxury = 0.0;
		
		/*
		 * TODO : 
		 * Need data to determine this 
		 * depending on home town and team we can determine price
		 * we can use population too if needed
		 */
		
		this.setPriceRegular(basePriceRegular);
		this.setPriceLuxury(basePriceLuxury);
		
	}


}
