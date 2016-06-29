package application;
//import java.util.ArrayList;
//import java.util.Random;
//
//
//public class DoWeNeedTHis {
//	
////	public void setPlayerList() {
////	// Determines the roster for the team
////	this.roster = this.GM.GMPickTeam;
////
////	// Determines the starters for the team
////	this.coach.coachPickStarters(this.roster);
////	this.starters = coach.coachPicks;
////}
//	
//	
//
////	public Team(String name, String hometown, GeneralManager_Pool GMPool, Coach_Pool coachPool) {
////
////		if (hometown == "Chicago") {
////			population = 2695598;
////		} else if (hometown == "Phoenix") {
////			population = 1445632;
////		} else if (hometown == "Pittsburgh") {
////			population = 305841;
////		} else if (hometown == "Boston") {
////			population = 645966;
////		}
////
////		this.name = name;
////		pickACoach(coachPool);
////		this.GM = GMPool.getRandomGM();
////		this.hometown = hometown;
////		this.wins = 0;
////		this.losses = 0;
////		this.roster = new ArrayList<Player>();
////		this.starters = new ArrayList<Player>();
////		this.offensiveRoster = new ArrayList<Player>();
////		this.defensiveRoster = new ArrayList<Player>();
////	}
//	
//	
//	
//	/*
//	 * Selects a player randomly from the pool
//	 * used to create the roster
//	 * 
//	 * -- do we need this still
//	 */
//
//	public void pickNewRandomPlayerForRoster(Player_Pool pool) {
//
//		// select a random player from the list of players in playerPool.
//		// add the player to the roster of "this" team
//		// remove that player from playerPool to avoid duplicates
//
//		Player player;
//		Random ranIndex = new Random();
//		int index = ranIndex.nextInt(pool.getSize());
//		player = pool.getPlayerPool().get(index);
//		this.roster.add(player);
//		pool.getPlayerPool().remove(index);
//
//	}
//	
//	public ArrayList<Player> coachPicks; // is this field needed 
//
//	
//	public void coachPickStarters(ArrayList<Player> GMPicks) {
//		//Coach chooses starters from the players selected by the GM
//		Random ranIndex = new Random();
//		for (int i = 0; i < 22; i++) {
//			int index = ranIndex.nextInt(GMPicks.size());
//			coachPicks.add(GMPicks.get(index));
//			GMPicks.remove(index);
//		}
//		// System.out.println(PlayerPool.getSize());
//
//	}
//	
//	
//
//	// Implemented in the team class, no need for so many levels of methods
//	
//	// public Coach chooseCoach() {
//	// 	// Chooses the coach for the team from the player pool
//	// 	Random rand = new Random();
//	// 	int index = rand.nextInt(CoachesPool.size());
//	// 	Coach person = CoachesPool.get(index);
//	// 	CoachesPool.remove(index);
//	// 	return person;
//	// }
//	
//	
//	
//	// this is done in the Team class in method pickNewRandomPlayerForRoster
//	
//	// public void GeneralManagerPickPlayer(Player_Pool PlayerPool) { //General Manager choose from player pool
//	// 	Player player;
//	// 	Random ranIndex = new Random();
//	// 	int index = ranIndex.nextInt(PlayerPool.getSize());
//	// 	player = PlayerPool.getPlayerPool().get(index);
//
//	// 	PlayerPool.getPlayerPool().remove(index);
//	// 	// System.out.println(PlayerPool.getSize());
//	// 	GMPickTeam.add(player);
//	// }
//	
//	
//	//import java.io.File;
//	//import java.io.FileNotFoundException;
//	//import java.io.FileReader;
//	//import java.util.ArrayList;
//	//import java.util.Scanner;
//	//
//	//public class NumberGenerator {
////		int count=0;
////		//String[] firstName=new String[100];
////		ArrayList<String> firstName=new ArrayList<String>();
////		ArrayList<String> lastName=new ArrayList<String>();
////		String fileFirstName="names.txt";
////		String fileLastName="lastnames.txt";
////		Scanner file1=new Scanner(new File(fileFirstName));
////		Scanner file2=new Scanner(new File(fileLastName));
////		while(file1.hasNextLine()) {
////			String line = file1.nextLine();
////			firstName.add(line);
////			
////		}
////		while(file2.hasNextLine()){
////			String line=file2.nextLine();
////			lastName.add(line);
////		}
//	///*	for(int i=0; i<100; i++){
////			String name=firstName.get(i);
////			System.out.println(name);
////		}*/
////		for(int i=0; i<1000; i++){
////			String name=lastName.get(i);
////			System.out.println(name);
////			count++;
////			
////		}
//	//}
//	//}
//	
//	
//	public void initialDrafting(ArrayList<Team> teams) {
//		// General managers alternate between selecting of players in the
//		// PlayerPool
//		for (int i = 0; i < 50; i++) {
//			for (int j = 0; j < teams.size(); j++) {
//				// teams.get(j).getGM().GeneralManagerPickPlayers(players);
//				teams.get(j).pickNewRandomPlayerForRoster(this);
//			}
//		}
//	}
//	
//
//	public void drafting(ArrayList<Team> order) {
//		// Method used to add more players to replace the injured ones at the end of the season
//		for (int i = 0; i < order.size(); i++) {
//			if(order.get(i).getRoster().size()!=50){
//				// Adds players to team until 50 is reached
//			Player bestPlayer = PlayerPool.get(0);
//			for (int j = 1; j < PlayerPool.size(); j++) {
//				// Find the player with the highest athleticism
//				if (bestPlayer.getAthleticism() < PlayerPool.get(j).getAthleticism()) {
//					bestPlayer=PlayerPool.get(j);
//				}
//			}
//			order.get(i).addToRoster(bestPlayer);
//			PlayerPool.remove(i);
//			}
//		}
//	}
//
//
//}
