
public class Owner 
{
	/*
	 * All the fields required for the team class are declared here
	 */
	private int happyValue;
	private int unhappyValue;
	private int patience;
	
	/*
	 * Constructors for the Owner class
	 */
	
	public Owner()
	{
		this.happyValue = generateHappyValue();
		this.unhappyValue = generateUnhappyValue();
		this.patience = generatePatience();
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
		// generate a random number between -3 and 3
		// return that number 
		// number has a uniform distribution 
		return 0;
	}
	
	/*
	 * Getters and setters for the fields in the class
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */
	
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
	   
	 }

}
