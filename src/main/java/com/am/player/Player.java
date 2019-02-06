/**
 * 
 */
package com.am.player;

/**
 * @author 612385366
 *
 */
public class Player {
	
	private long playerID;
	private String firstName;
	private String lastName;
	private int matches;
	private int runs;
	private int ballFaced;
	private double batStrikeRate;
	private int notOut;
	private double batAverage;
	private int fours;
	private int sixes;
	private int fifties;
	private int hundreds;
	private double overs;
	private int maidins;
	private double bowlStrikeRate;
	private double bowlAverage;
	private int runsConceeded;
	private int BallBowled;
	private int wickets;
	public Player() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Player(long playerID, String firstName, String lastName, int matches, int runs, double batStrikeRate,
			int notOut, double batAverage, int fours, int sixes, int fifties, int hundreds, double overs, int maidins,
			double bowlStrikeRate, double bowlAverage) {
		super();
		this.playerID = playerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.matches = matches;
		this.runs = runs;
		this.batStrikeRate = batStrikeRate;
		this.notOut = notOut;
		this.batAverage = batAverage;
		this.fours = fours;
		this.sixes = sixes;
		this.fifties = fifties;
		this.hundreds = hundreds;
		this.overs = overs;
		this.maidins = maidins;
		this.bowlStrikeRate = bowlStrikeRate;
		this.bowlAverage = bowlAverage;
	}
	public long getPlayerID() {
		return playerID;
	}
	public void setPlayerID(long playerID) {
		this.playerID = playerID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getMatches() {
		return matches;
	}
	public void setMatches(int matches) {
		this.matches = matches;
	}
	public int getRuns() {
		return runs;
	}
	public void setRuns(int runs) {
		this.runs = runs;
	}
	public double getBatStrikeRate() {
		return batStrikeRate;
	}
	public void setBatStrikeRate(double batStrikeRate) {
		this.batStrikeRate = batStrikeRate;
	}
	public int getNotOut() {
		return notOut;
	}
	public void setNotOut(int notOut) {
		this.notOut = notOut;
	}
	public double getBatAverage() {
		return batAverage;
	}
	public void setBatAverage(double batAverage) {
		this.batAverage = batAverage;
	}
	public int getFours() {
		return fours;
	}
	public void setFours(int fours) {
		this.fours = fours;
	}
	public int getSixes() {
		return sixes;
	}
	public void setSixes(int sixes) {
		this.sixes = sixes;
	}
	public int getFifties() {
		return fifties;
	}
	public void setFifties(int fifties) {
		this.fifties = fifties;
	}
	public int getHundreds() {
		return hundreds;
	}
	public void setHundreds(int hundreds) {
		this.hundreds = hundreds;
	}
	public double getOvers() {
		return overs;
	}
	public void setOvers(double overs) {
		this.overs = overs;
	}
	public int getMaidins() {
		return maidins;
	}
	public void setMaidins(int maidins) {
		this.maidins = maidins;
	}
	public double getBowlStrikeRate() {
		return bowlStrikeRate;
	}
	public void setBowlStrikeRate(double bowlStrikeRate) {
		this.bowlStrikeRate = bowlStrikeRate;
	}
	public double getBowlAverage() {
		return bowlAverage;
	}
	public void setBowlAverage(double bowlAverage) {
		this.bowlAverage = bowlAverage;
	}
	public int getBallFaced() {
		return ballFaced;
	}
	public void setBallFaced(int ballFaced) {
		this.ballFaced = ballFaced;
	}
	public int getRunsConceeded() {
		return runsConceeded;
	}
	public void setRunsConceeded(int runsConceeded) {
		this.runsConceeded = runsConceeded;
	}
	public int getBallBowled() {
		return BallBowled;
	}
	public void setBallBowled(int ballBowled) {
		BallBowled = ballBowled;
	}
	public int getWickets() {
		return wickets;
	}
	public void setWickets(int wickets) {
		this.wickets = wickets;
	}
	@Override
	public String toString() {
		return "Player [playerID=" + playerID + ", firstName=" + firstName + ", lastName=" + lastName + ", matches="
				+ matches + ", runs=" + runs + ", batStrikeRate=" + batStrikeRate + ", notOut=" + notOut
				+ ", batAverage=" + batAverage + ", fours=" + fours + ", sixes=" + sixes + ", fifties=" + fifties
				+ ", hundreds=" + hundreds + ", overs=" + overs + ", maidins=" + maidins + ", bowlStrikeRate="
				+ bowlStrikeRate + ", bowlAverage=" + bowlAverage + "]";
	}
	

}
