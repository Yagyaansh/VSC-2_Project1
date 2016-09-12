public class PlayerPositionStats {
  
  private int secondaryScore;
  private int linebackerScore;
  private int defensivelineScore;
  private int offensivelineScore;
  private int receiverScore;
  private int runningbackScore;
  
  // Is it a good idea to maintain a PQ with all the scores. We will always want them in 
  // descending order. But it will not be very easy to implement this.
  
  public PlayerPositionStats()
  {
	  this.secondaryScore = 0;
	  this.linebackerScore = 0;
	  this.defensivelineScore = 0;
	  this.offensivelineScore = 0;
	  this.receiverScore = 0;
	  this.runningbackScore = 0;
  }

  
 /*
 * Getters and setters for the fields in the class
 * -------------------------------------------------------------------------
 * ------------------------
 */
  
  public int getSecondaryScore() {
	return secondaryScore;
  }

  public void setSecondaryScore(int secondaryScore) {
	this.secondaryScore = secondaryScore;
  }

  public int getLinebackerScore() {
	return linebackerScore;
  }

  public void setLinebackerScore(int linebackerScore) {
	this.linebackerScore = linebackerScore;
  }

  public int getDefensivelineScore() {
	return defensivelineScore;
  }	

  public void setDefensivelineScore(int defensivelineScore) {
	this.defensivelineScore = defensivelineScore;
  }

  public int getOffensivelineScore() {
	return offensivelineScore;
  }

  public void setOffensivelineScore(int offensivelineScore) {
	this.offensivelineScore = offensivelineScore;
  }

  public int getReceiverScore() {
	return receiverScore;
  }

  public void setReceiverScore(int receiverScore) {
	this.receiverScore = receiverScore;
  }

  public int getRunningbackScore() {
	return runningbackScore;
  }

  public void setRunningbackScore(int runningbackScore) {
	this.runningbackScore = runningbackScore;
  }
  
  public void getBestPosition()
  {
    // the position with the highest score will be returned 
  }
  
 /*
 * End of Getters and Setters
 * -------------------------------------------------------------------------
 * ------------------------
 */

}
