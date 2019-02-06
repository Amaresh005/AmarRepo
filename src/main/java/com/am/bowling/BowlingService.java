/**
 * 
 */
package com.am.bowling;

/**
 * @author 612385366
 *
 */
public interface BowlingService {

	Bowling getBowlerByID(long matchID, long bowlPlayerID);

	void saveOrUpdate(Bowling bowl);

}
