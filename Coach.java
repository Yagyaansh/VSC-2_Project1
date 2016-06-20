import java.util.ArrayList;
import java.util.Random;

public class Coach_Pool {

	/*
	 * All the coaches in list form
	 * See if we should use a better data structure
	 * The array list is too inefficient for search
	 */
	private ArrayList<Coach> CoachesPool = new ArrayList<Coach>();

	/*
	 * Initializes the pool given the size of the pool as input 
	 * Name generator is used to assign last names
	 * 1st name is always "Coach"
	 */
	public Coach_Pool(int sizeOfCoachesPool) {
		try 
		{
			NameGenerator randomNames = new NameGenerator();
			for (int i = 0; i < sizeOfCoachesPool; i++) 
			{
				CoachesPool.add(new Coach("Coach", randomNames.randomLastName()));
			}
		} catch (Exception e) {
			System.out.println("Error encountered in Coach_Pool");
			e.printStackTrace();
			System.exit(0);
		}
	}

	
	/*
	 * Getters and setters for the fields in the class
	 * -------------------------------------------------------------------------------------------------
	 */
	public ArrayList<Coach> getCoachesPool() {
		return CoachesPool;
	}

	public void setCoachesPool(ArrayList<Coach> coaches_Pool) {
		CoachesPool = coaches_Pool;
	}

	public int getSize() {
		return CoachesPool.size();
	}
	
	/*
	 * End of Getters and Setters
	 * -------------------------------------------------------------------------------------------------
	 */
	
	

}
