/**
 * 
 */
package com.am.matches;

/**
 * @author 612385366
 *
 */
public interface MatchesService {

	Matches getMatchByID(long matchID);

	void saveOrUpdate(Matches match);

}
