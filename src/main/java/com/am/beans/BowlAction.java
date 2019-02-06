/**
 * 
 */
package com.am.beans;

import java.io.Serializable;

/**
 * @author 612385366
 *
 */
public class BowlAction implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long matchID;
	private long bowlPlayerID;
	private long batPlayerID;
	private double bowlInstatnt;
	private String actionType;
	private String action;					//HowOut, WhatExtra
	private int runsConceeded;
	private long outPlayerID;
	private long outFielderID;
	
	public BowlAction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getMatchID() {
		return matchID;
	}
	public void setMatchID(long matchID) {
		this.matchID = matchID;
	}
	public long getBowlPlayerID() {
		return bowlPlayerID;
	}
	public void setBowlPlayerID(long bowlPlayerID) {
		this.bowlPlayerID = bowlPlayerID;
	}
	public long getBatPlayerID() {
		return batPlayerID;
	}
	public void setBatPlayerID(long batPlayerID) {
		this.batPlayerID = batPlayerID;
	}
	public double getBowlInstatnt() {
		return bowlInstatnt;
	}
	public void setBowlInstatnt(double bowlInstatnt) {
		this.bowlInstatnt = bowlInstatnt;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public int getRunsConceeded() {
		return runsConceeded;
	}
	public void setRunsConceeded(int runsConceeded) {
		this.runsConceeded = runsConceeded;
	}
	public long getOutPlayerID() {
		return outPlayerID;
	}
	public void setOutPlayerID(long outPlayerID) {
		this.outPlayerID = outPlayerID;
	}
	public long getOutFielderID() {
		return outFielderID;
	}
	public void setOutFielderID(long outFielderID) {
		this.outFielderID = outFielderID;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	@Override
	public String toString() {
		return "BowlAction [matchID=" + matchID + ", bowlPlayerID=" + bowlPlayerID + ", batPlayerID=" + batPlayerID
				+ ", bowlInstatnt=" + bowlInstatnt + ", actionType=" + actionType + ", runsConceeded=" + runsConceeded
				+ ", outPlayerID=" + outPlayerID + ", outFielderID=" + outFielderID + "]";
	}

}
