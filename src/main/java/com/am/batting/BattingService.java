/**
 * 
 */
package com.am.batting;

/**
 * @author 612385366
 *
 */
public interface BattingService {

	Batting getBattingByID(long matchID, long batPlayerID);

	void saveOrUpdate(Batting bat);

}
