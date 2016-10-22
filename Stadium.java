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
		if (hometown == "Boston"){
			capacity = 66829;
		} else if (hometown == "Buffalo") {
			capacity = 71608;
		} else if (hometown == "New York") {
			capacity = 82500;
		} else if(hometown == "Miami"){
			capacity = 65326;
		} else if (hometown == "Pittsburgh") {
			capacity = 68400;
		} else if (hometown == "Baltimore") {
			capacity = 71008;
		} else if (hometown == "Cincinnati") {
			capacity = 65515;
		} else if (hometown == "Cleveland") {
			capacity = 67431;
		} else if (hometown == "Houston") {
			capacity = 72220;
		} else if (hometown == "Jacksonville") {
			capacity = 67246;
		} else if (hometown == "Tennessee") {
			capacity = 69143;
		} else if (hometown == "Indianapolis") {
			capacity = 67000;
		} else if (hometown == "Denver") {
			capacity = 76125;
		} else if (hometown == "Oakland") {
			capacity = 56063;
		} else if (hometown == "Kansas City") {
			capacity = 76416;
		} else if (hometown == "San Diego") {
			capacity = 70561;
		}
		
		
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
		if (hometown == "Boston"){
			capacity = 87;
		} else if (hometown == "Buffalo") {
			capacity = 121;
		} else if (hometown == "New York") {
			capacity = 218;
		} else if(hometown == "Miami"){
			capacity = 193;
		} else if (hometown == "Pittsburgh") {
			capacity = 129;
		} else if (hometown == "Baltimore") {
			capacity = 125;
		} else if (hometown == "Cincinnati") {
			capacity = 114;
		} else if (hometown == "Cleveland") {
			capacity = 147;
		} else if (hometown == "Houston") {
			capacity = 196;
		} else if (hometown == "Jacksonville") {
			capacity = 75;
		} else if (hometown == "Tennessee") {
			capacity = 177;
		} else if (hometown == "Indianapolis") {
			capacity = 137;
		} else if (hometown == "Denver") {
			capacity = 132;
		} else if (hometown == "Oakland") {
			capacity = 143;
		} else if (hometown == "Kansas City") {
			capacity = 80;
		} else if (hometown == "San Diego") {
			capacity = 113;
		}
		
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
		
		if (hometown == "Boston"){
			basePriceRegular = 130.73;
			basePriceLuxury = 10000;
		} else if (hometown == "Buffalo") {
			basePriceRegular = 88.18;
			basePriceLuxury = 8000;
		} else if (hometown == "New York") {
			basePriceRegular = 115.50;
			basePriceLuxury = 16500;
		} else if(hometown == "Miami"){
			basePriceRegular = 98.25;
			basePriceLuxury = 15000;
		} else if (hometown == "Pittsburgh") {
			basePriceRegular = 93.56;
			basePriceLuxury = 15000;
		} else if (hometown == "Baltimore") {
			basePriceRegular = 112.11;
			basePriceLuxury = 10000;
		} else if (hometown == "Cincinnati") {
			basePriceRegular = 74.13;
			basePriceLuxury = 8000;
		} else if (hometown == "Cleveland") {
			basePriceRegular = 69.13;
			basePriceLuxury = 8000;
		} else if (hometown == "Houston") {
			basePriceRegular = 94.73;
			basePriceLuxury = 6000;
		} else if (hometown == "Jacksonville") {
			basePriceRegular = 61.36;
			basePriceLuxury = 8000;
		} else if (hometown == "Tennessee") {
			basePriceRegular = 67.15;
			basePriceLuxury = 8000;
		} else if (hometown == "Indianapolis") {
			basePriceRegular = 87.44;
			basePriceLuxury = 10000;
		} else if (hometown == "Denver") {
			basePriceRegular = 114.00;
			basePriceLuxury = 20000;
		} else if (hometown == "Oakland") {
			basePriceRegular = 71.03;
			basePriceLuxury = 10000;
		} else if (hometown == "Kansas City") {
			basePriceRegular = 73.79;
			basePriceLuxury = 11000;
		} else if (hometown == "San Diego") {
			basePriceRegular = 84.55;
			basePriceLuxury = 10000;
		}
		
		this.setPriceRegular(basePriceRegular);
		this.setPriceLuxury(basePriceLuxury);
		
	}


}
