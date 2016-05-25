import java.util.ArrayList;
import java.util.Random;

public class GeneralManager_Pool {

	private ArrayList<GeneralManager> GeneralManagerPool = new ArrayList<GeneralManager>();

	public GeneralManager_Pool(int sizeOfGeneralManagerPool) {

		try {
			// creates the random name generator to generate last names for all
			// the coaches
			NameGenerator randomNames = new NameGenerator();

			for (int i = 0; i < sizeOfGeneralManagerPool; i++) // sizeOfCoaches is used
														// for code flexibility
			{
				// creates a coach and adds to the pool
				GeneralManagerPool.add(new GeneralManager(randomNames.randomFirstName(), randomNames.randomLastName()));
			}
		} catch (Exception e) {
			System.out.println("Error encountered in GeneralManager_Pool");
			e.printStackTrace();
			System.exit(0);
		}
	}
	

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
	
	public GeneralManager chooseGeneralManager(){ //Selects the general manager to assign to team
		Random rand= new Random();
		int index = rand.nextInt(GeneralManagerPool.size());
		GeneralManager person=GeneralManagerPool.get(index);
		GeneralManagerPool.remove(index);
		return person;
	}

}
