import java.util.ArrayList;
import java.util.Random;

public class GeneralManager_Pool {

	/*
	 * The GeneralManager_Pool is represented using an ArrayList Structure
	 * This is very inefficient
	 * We have to improve this
	 */
	private ArrayList<GeneralManager> GeneralManagerPool = new ArrayList<GeneralManager>();

	/*
	 * Initialize the GM pool by accepting the size of the pool as the parameter
	 * Each GM is also initialized here with a random first name and a random last name
	 */
	public GeneralManager_Pool(int sizeOfGeneralManagerPool) {
		try {
			NameGenerator randomNames = new NameGenerator();

			for (int i = 0; i < sizeOfGeneralManagerPool; i++) 
			{
				GeneralManagerPool.add(new GeneralManager(randomNames.randomFirstName(), randomNames.randomLastName()));
			}
		} catch (Exception e) {
			System.out.println("Error encountered in GeneralManager_Pool");
			e.printStackTrace();
			System.exit(0);
		}
	}
	
	/*
	 * Getters and setters for the fields in the class
	 * -------------------------------------------------------------------------------------------------
	 */
	
	public ArrayList<GeneralManager> getGeneralManagerPool() {
		return GeneralManagerPool;
	}

	public void setGeneralManagerPool(
			ArrayList<GeneralManager> GeneralManager_Pool) {
		GeneralManagerPool = GeneralManager_Pool;
	}
	
	public int getSize() {
		return GeneralManagerPool.size();
	}
	
	/*
	 * End of Getters and Setters
	 * -------------------------------------------------------------------------------------------------
	 */
	
	/*
	 * Returns a random GM from the pool of available GMs
	 * removes the GM from the pool
	 */
	public GeneralManager getRandomGM(){ 
		Random rand = new Random();
		int index = rand.nextInt(GeneralManagerPool.size());
		GeneralManager gm = GeneralManagerPool.get(index);
		GeneralManagerPool.remove(index);
		return gm;
	}

}
