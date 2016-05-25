import java.util.ArrayList;
import java.util.Random;

public class Coaches_Pool {

	// list of coaches that make up the coach pool
	private ArrayList<Coach> CoachesPool = new ArrayList<Coach>();

	public Coaches_Pool(int sizeOfCoachesPool) {

		try {
			// creates the random name generator to generate last names for all
			// the coaches
			NameGenerator randomNames = new NameGenerator();

			for (int i = 0; i < sizeOfCoachesPool; i++) // sizeOfCoaches is used
														// for code flexibility
			{
				// creates a coach and adds to the pool
				CoachesPool.add(new Coach("Coach", randomNames.randomLastName()));
			}
		} catch (Exception e) {
			System.out.println("Error encountered in Coach_Pool");
			e.printStackTrace();
			System.exit(0);
		}
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
