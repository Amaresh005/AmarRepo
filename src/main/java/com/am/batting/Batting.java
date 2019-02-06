/**
 * 
 */
package com.am.batting;

/**
 * @author 612385366
 *
 */
public class Batting {
	
	private long matchID;
	private long seriesID;
	private long playerID;
	private Integer runs;
	private Integer balls;
	private Integer zeros;
	private Integer ones;
	private Integer twos;
	private Integer threes;
	private Integer fours;
	private Integer fives;
	private Integer sixes;
	private double strikeRate;
	private String howOut;
	//private String fielder;
	//private long bowler;
	private long fielderID;
	private long bowlerID;
	public Batting() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Batting(long matchID, long seriesID, long playerID, Integer runs, Integer balls, Integer zeros, Integer ones,
			Integer twos, Integer threes, Integer fours, Integer fives, Integer sixes, double strikeRate, String howOut) {
		super();
		this.matchID = matchID;
		this.seriesID = seriesID;
		this.playerID = playerID;
		this.runs = runs;
		this.balls = balls;
		this.zeros = zeros;
		this.ones = ones;
		this.twos = twos;
		this.threes = threes;
		this.fours = fours;
		this.fives = fives;
		this.sixes = sixes;
		this.strikeRate = strikeRate;
		this.howOut = howOut;
	}
	
	public long getMatchID() {
		return matchID;
	}
	public void setMatchID(long matchID) {
		this.matchID = matchID;
	}
	public long getSeriesID() {
		return seriesID;
	}
	public void setSeriesID(long seriesID) {
		this.seriesID = seriesID;
	}
	public long getPlayerID() {
		return playerID;
	}
	public void setPlayerID(long playerID) {
		this.playerID = playerID;
	}
	public Integer getRuns() {
		return runs;
	}
	public void setRuns(Integer runs) {
		this.runs = runs;
	}
	public Integer getBalls() {
		return balls;
	}
	public void setBalls(Integer balls) {
		this.balls = balls;
	}
	public Integer getZeros() {
		return zeros;
	}
	public void setZeros(Integer zeros) {
		this.zeros = zeros;
	}
	public Integer getOnes() {
		return ones;
	}
	public void setOnes(Integer ones) {
		this.ones = ones;
	}
	public Integer getTwos() {
		return twos;
	}
	public void setTwos(Integer twos) {
		this.twos = twos;
	}
	public Integer getThrees() {
		return threes;
	}
	public void setThrees(Integer threes) {
		this.threes = threes;
	}
	public Integer getFours() {
		return fours;
	}
	public void setFours(Integer fours) {
		this.fours = fours;
	}
	public Integer getFives() {
		return fives;
	}
	public void setFives(Integer fives) {
		this.fives = fives;
	}
	public Integer getSixes() {
		return sixes;
	}
	public void setSixes(Integer sixes) {
		this.sixes = sixes;
	}
	public double getStrikeRate() {
		return strikeRate;
	}
	public void setStrikeRate(double strikeRate) {
		this.strikeRate = strikeRate;
	}
	public String getHowOut() {
		return howOut;
	}
	public void setHowOut(String howOut) {
		this.howOut = howOut;
	}
	public long getFielderID() {
		return fielderID;
	}
	public void setFielderID(long fielderID) {
		this.fielderID = fielderID;
	}
	public long getBowlerID() {
		return bowlerID;
	}
	public void setBowlerID(long bowlerID) {
		this.bowlerID = bowlerID;
	}
	@Override
	public String toString() {
		return "Batting [matchID=" + matchID + ", seriesID=" + seriesID + ", playerID=" + playerID + ", runs=" + runs
				+ ", balls=" + balls + ", zeros=" + zeros + ", ones=" + ones + ", twos=" + twos + ", threes=" + threes
				+ ", fours=" + fours + ", fives=" + fives + ", sixes=" + sixes + ", strikeRate=" + strikeRate
				+ ", howOut=" + howOut + "]";
	}

}
