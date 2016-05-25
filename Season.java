import java.util.*;

public class Season
{
  ArrayList<Team> teams; // all the teams playing in the season
  ArrayList<Game> games; // every game played in the season
  ArrayList<Team> victors; // list of victors for every game - not sure if you all need this
  
  public Season()
  {
    this.teams = new ArrayList<Team>();
    this.games = new ArrayList<Game>();
    this.victors = new ArrayList<Team>();
  }
  
  public Season(ArrayList<Team> teams, ArrayList<Game> games)
  {
    this.teams = teams;
    this.games = games;
    this.victors = new ArrayList<>();
  }
  
  // starts the season
  public void startSeason()
  {
    int numberOfGames = 12;// confirm how to calculate this from the number of teams
    
    // iterating over the list of teaams such that each team plays each other twice
    for(int i=0; i<teams.size() - 1; i++)
    {
      for(int j=i+1; j<teams.size(); j++)
      {
        Team team1 = teams.get(i);
        Team team2 = teams.get(j);
        games.add(new Game(team1, team2)); // the team mentioned 1st is the home team the second team is the visitor - team1 home
        games.add(new Game(team2, team1)); // similarly - team2 home
      }
    }
    
  }
  
  // starts playing each season game by game
  // the list of all the games to be played is now in "games"
  // the list can be sorted or reordered if the games are supposed to be played in a particular sequence
  public void play()
  {
    for(Game g: this.games)
    {
      Team victor = g.play(); // method <Game>.play() plays the game and returns the victor of the game
      victors.add(victor);
    }
  }
  
  // returns the result of the whole season
  // the value returned is the team that won the season
  public Team seasonResult()
  {
    // tell me how to calculate in the case of ties and all that 
    // then I can complete the method
  }
    
    
    // first find the team with the best Won-Loss Record
    // update this team's gross revenue - have to add 1,000,000 to the current gross revenues for the team
    
  }


