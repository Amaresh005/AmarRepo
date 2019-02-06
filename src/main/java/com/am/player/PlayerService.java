/**
 * 
 */
package com.am.player;

/**
 * @author 612385366
 *
 */
public interface PlayerService {

	Player getPlayerByID(long batPlayerID);

	void saveOrUpdate(Player batPlayer);

}
