/**
 * 
 */
package com.am.matches;

import javax.persistence.Entity;


/**
 * @author 612385366
 *
 */
@Entity
public class Matches {
	
	private long matchID;
	private long seriesID;
	private String format;
	private String level;
	private String inningsTeam;
	private Integer runs;
	private double overs;
	private Integer wickets;
	private Integer extras;
	private String status;
	private String teamA;
	private String teamB;
	private String toss;
	private String chooseTo;
	
	//CRR, RRR
	public Matches() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Matches(long matchID, long seriesID, String format, String level, String inningsTeam, Integer runs,
			double overs, Integer wickets, Integer extras, String status, String teamA, String teamB, String toss,
			String chooseTo) {
		super();
		this.matchID = matchID;
		this.seriesID = seriesID;
		this.format = format;
		this.level = level;
		this.inningsTeam = inningsTeam;
		this.runs = runs;
		this.overs = overs;
		this.wickets = wickets;
		this.extras = extras;
		this.status = status;
		this.teamA = teamA;
		this.teamB = teamB;
		this.toss = toss;
		this.chooseTo = chooseTo;
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
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getInningsTeam() {
		return inningsTeam;
	}
	public void setInningsTeam(String inningsTeam) {
		this.inningsTeam = inningsTeam;
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
	public Integer getExtras() {
		return extras;
	}
	public void setExtras(Integer extras) {
		this.extras = extras;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTeamA() {
		return teamA;
	}
	public void setTeamA(String teamA) {
		this.teamA = teamA;
	}
	public String getTeamB() {
		return teamB;
	}
	public void setTeamB(String teamB) {
		this.teamB = teamB;
	}
	public String getToss() {
		return toss;
	}
	public void setToss(String toss) {
		this.toss = toss;
	}
	public String getChooseTo() {
		return chooseTo;
	}
	public void setChooseTo(String chooseTo) {
		this.chooseTo = chooseTo;
	}
	@Override
	public String toString() {
		return "Matches [matchID=" + matchID + ", seriesID=" + seriesID + ", format=" + format + ", level=" + level
				+ ", inningsTeam=" + inningsTeam + ", runs=" + runs + ", overs=" + overs + ", wickets=" + wickets
				+ ", extras=" + extras + ", status=" + status + ", teamA=" + teamA + ", teamB=" + teamB + ", toss="
				+ toss + ", chooseTo=" + chooseTo + "]";
	}

}
