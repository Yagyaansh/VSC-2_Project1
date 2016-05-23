import java.util.ArrayList;
import java.util.Random;

public class GeneralManager_Pool {

	private ArrayList<GeneralManager> GeneralManagerPool = new ArrayList<GeneralManager>();

	public GeneralManager_Pool() throws Exception {
		NameGenerator randomNames= new NameGenerator();
		GeneralManager bearsGeneralManager = new GeneralManager(randomNames.randomFirstName(), randomNames.randomLastName());
		GeneralManager cardinalsGeneralManager = new GeneralManager(randomNames.randomFirstName(), randomNames.randomLastName());
		this.GeneralManagerPool.add(bearsGeneralManager);
		this.GeneralManagerPool.add(cardinalsGeneralManager);

		/*
		 * for(int x = 0; x < 2; x++){
		 * 
		 * GeneralManager e = new GeneralManager(x);
		 * 
		 * this.GeneralManagerPool.add(e);
		 * 
		 * }
		 */

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
