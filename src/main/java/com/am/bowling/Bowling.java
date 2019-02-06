/**
 * 
 */
package com.am.bowling;

/**
 * @author 612385366
 *
 */
public class Bowling {
	
	private long matchID;
	private long seriesID;
	private long playerID;
	private Integer runs;
	private double overs;
	private Integer wickets;
	private Integer maidins;
	private double economy;
	private Integer extras;
	public Bowling() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Bowling(long matchID, long seriesID, long playerID, Integer runs, double overs, Integer wickets,
			Integer maidins, double economy, Integer extras) {
		super();
		this.matchID = matchID;
		this.seriesID = seriesID;
		this.playerID = playerID;
		this.runs = runs;
		this.overs = overs;
		this.wickets = wickets;
		this.maidins = maidins;
		this.economy = economy;
		this.extras = extras;
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
	public double getOvers() {
		return overs;
	}
	public void setOvers(double overs) {
		this.overs = overs;
	}
	public Integer getWickets() {
		return wickets;
	}
	public void setWickets(Integer wickets) {
		this.wickets = wickets;
	}
	public Integer getMaidins() {
		return maidins;
	}
	public void setMaidins(Integer maidins) {
		this.maidins = maidins;
	}
	public double getEconomy() {
		return economy;
	}
	public void setEconomy(double economy) {
		this.economy = economy;
	}
	public Integer getExtras() {
		return extras;
	}
	public void setExtras(Integer extras) {
		this.extras = extras;
	}
	@Override
	public String toString() {
		return "Bowling [matchID=" + matchID + ", seriesID=" + seriesID + ", playerID=" + playerID + ", runs=" + runs
				+ ", overs=" + overs + ", wickets=" + wickets + ", maidins=" + maidins + ", economy=" + economy
				+ ", extras=" + extras + "]";
	}

}
