import java.util.ArrayList;
import java.util.Random;

public class Coaches_Pool {

	private ArrayList<Coach> CoachesPool = new ArrayList<Coach>();

	public Coaches_Pool() throws Exception {
		NameGenerator randomNames = new NameGenerator();
		Coach bearsCoach = new Coach(randomNames.randomFirstName(), randomNames.randomLastName(), 25);
		Coach cardinalsCoach = new Coach(randomNames.randomFirstName(), randomNames.randomLastName(), 75);
		this.CoachesPool.add(bearsCoach);
		this.CoachesPool.add(cardinalsCoach);

		/*
		 * for(int x = 0; x < 2; x++){
		 * 
		 * Coach e = new Coach(x);
		 * 
		 * this.CoachesPool.add(e);
		 * 
		 * }
		 */

	}

	public ArrayList<Coach> getCoachesPool() {
		return CoachesPool;
	}

	public void setCoachesPool(ArrayList<Coach> coaches_Pool) {
		CoachesPool = coaches_Pool;
	}

	public int getSize() {

		return CoachesPool.size();

	}

	public Coach chooseCoach() {
		// Chooses the coach for the team from the player pool
		Random rand = new Random();
		int index = rand.nextInt(CoachesPool.size());
		Coach person = CoachesPool.get(index);
		CoachesPool.remove(index);
		return person;
	}

}
