package application;

import java.util.ArrayList;

public class OutputData 
{
	private Player_Pool playerPool;
	private Coach_Pool coachPool;
	private GeneralManager_Pool gmPool;
	private ArrayList<Team> teams;
	private ArrayList<Season> seasons;
	
	/*
	 * Constructor accepting all the fields as parameters
	 */
	public OutputData(Player_Pool playerPool, Coach_Pool coachPool,
			GeneralManager_Pool gmPool, ArrayList<Team> teams,
			ArrayList<Season> seasons) {
		this.playerPool = playerPool;
		this.coachPool = coachPool;
		this.gmPool = gmPool;
		this.teams = teams;
		this.seasons = seasons;
	}
	
	public OutputData()
	{
		/*
		 * Will have to figure out if this is needed
		 * If it is then I will implement 
		 */
	}
	
	/*
	 * Getters and setters for the fields in the class
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */
	

	public Player_Pool getPlayerPool() {
		return playerPool;
	}
	public void setPlayerPool(Player_Pool playerPool) {
		this.playerPool = playerPool;
	}
	public Coach_Pool getCoachPool() {
		return coachPool;
	}
	public void setCoachPool(Coach_Pool coachPool) {
		this.coachPool = coachPool;
	}
	public GeneralManager_Pool getGmPool() {
		return gmPool;
	}
	public void setGmPool(GeneralManager_Pool gmPool) {
		this.gmPool = gmPool;
	}
	public ArrayList<Team> getTeams() {
		return teams;
	}
	public void setTeams(ArrayList<Team> teams) {
		this.teams = teams;
	}
	public ArrayList<Season> getSeasons() {
		return seasons;
	}
	public void setSeasons(ArrayList<Season> seasons) {
		this.seasons = seasons;
	}
	
	/*
	 * End of Getters and Setters
	 * -------------------------------------------------------------------------
	 * ------------------------
	 */


}
