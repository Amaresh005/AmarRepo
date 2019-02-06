/**
 * 
 */
package com.am.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.am.batting.Batting;
import com.am.batting.BattingService;
import com.am.beans.BowlAction;
import com.am.bowling.Bowling;
import com.am.bowling.BowlingService;
import com.am.matches.Matches;
import com.am.matches.MatchesService;
import com.am.player.Player;
import com.am.player.PlayerService;

/**
 * @author 612385366
 *
 */
@Component
public class CricServiceImpl implements CricService {
	
	Logger logger = LoggerFactory.getLogger(CricServiceImpl.class);
	
	@Autowired
    @Qualifier(value = "matchesServiceImpl")
    private MatchesService matchesService;
	
	@Autowired
    @Qualifier(value = "battingServiceImpl")
    private BattingService battingService;
	
	@Autowired
    @Qualifier(value = "bowlingServiceImpl")
    private BowlingService bowlingService;
	
	@Autowired
    @Qualifier(value = "playerServiceImpl")
    private PlayerService playerService;

	/* (non-Javadoc)
	 * @see com.am.service.CricService#bowlUpdate()
	 */
	@Override
	public String bowlUpdate(BowlAction bowlAction) {
		logger.info("CricServiceImpl :: New Request for updateBowl");
		/*
		 * Run (runs, 4, 6)
		 * Extra (Wide, Noball, LegByes, Byes)
		 * Wicket (Run out, Bowled, Caught, Stumped, LBW)
		 * Dot
		 * 
		 * Over ==> Update bowler data.
		 * update bowler with dot, run, wkt, extra Runs if scored, to total, To batsMan,
		 * to bowler, to extra if any.
		 */
		
		updateMatch(bowlAction);
		updateBatting(bowlAction);
		updateBowling(bowlAction);
		updateBatPlayer(bowlAction);
		updateBowlPlayer(bowlAction);
		return "Test Success";
	}
	
	private void updateMatch(BowlAction bowlAction) {
		Matches match = matchesService.getMatchByID(bowlAction.getMatchID());
		
		if(bowlAction.getActionType().equalsIgnoreCase("EXTRA")) {
			if(!bowlAction.getAction().equalsIgnoreCase("NOBALL")) {
				match.setRuns(match.getRuns() + bowlAction.getRunsConceeded());
				match.setExtras(match.getExtras() + bowlAction.getRunsConceeded());
			} if(bowlAction.getAction().equalsIgnoreCase("NOBALL")) {
				match.setRuns(match.getRuns() + bowlAction.getRunsConceeded() + 1);
				match.setExtras(match.getExtras() + 1);
			}
		} else {
			match.setOvers(match.getOvers() + 0.1);		//Write logic to convert into base 6.
			match.setRuns(match.getRuns() + bowlAction.getRunsConceeded());
		}
		if(bowlAction.getActionType().equalsIgnoreCase("OUT")) {
			match.setWickets(match.getWickets() + 1);
		}
		
		matchesService.saveOrUpdate(match);
	}

	private void updateBatting(BowlAction bowlAction) {
		Batting bat = battingService.getBattingByID(bowlAction.getMatchID(), bowlAction.getBatPlayerID());
		
		//EXTRA
		if(!bowlAction.getAction().equalsIgnoreCase("WIDE")) {
			bat.setBalls(bat.getBalls() + 1);
		}
		
		//RUN
		if(bowlAction.getActionType().equalsIgnoreCase("RUN") 
				|| (bowlAction.getActionType().equalsIgnoreCase("EXTRA") && bowlAction.getAction().equalsIgnoreCase("NOBALL"))
				|| (bowlAction.getActionType().equalsIgnoreCase("OUT") && bowlAction.getAction().equalsIgnoreCase("RUNOUT"))) {
			bat.setRuns(bat.getRuns() + bowlAction.getRunsConceeded());
			
			if(0 == bowlAction.getRunsConceeded()) {
				bat.setZeros(bat.getZeros() + 1);
			} else if(1 == bowlAction.getRunsConceeded()) {
				bat.setOnes(bat.getOnes() + 1);
			} else if(2 == bowlAction.getRunsConceeded()) {
				bat.setTwos(bat.getTwos() + 1);
			} else if(3 == bowlAction.getRunsConceeded()) {
				bat.setThrees(bat.getThrees() + 1);
			} else if(4 == bowlAction.getRunsConceeded()) {
				bat.setFours(bat.getFours() + 1);
			} else if(5 == bowlAction.getRunsConceeded()) {
				bat.setFives(bat.getFives() + 1);
			} else if(6 == bowlAction.getRunsConceeded()) {
				bat.setSixes(bat.getSixes() + 1);
			}
			bat.setBalls(bat.getBalls() + 1);
			bat.setStrikeRate((bat.getRuns()/bat.getBalls()) * 100);
		}
		
		//Wicket
		if(bowlAction.getActionType().equalsIgnoreCase("OUT")) {
			bat.setBowlerID(bowlAction.getBowlPlayerID());
			if(0 != bowlAction.getOutFielderID()) {
				bat.setFielderID(bowlAction.getOutFielderID());
			}
			bat.setHowOut(bowlAction.getAction());
		}
		
		battingService.saveOrUpdate(bat);
	}

	private void updateBowling(BowlAction bowlAction) {
		Bowling bowl = bowlingService.getBowlerByID(bowlAction.getMatchID(), bowlAction.getBowlPlayerID());
		
		//EXTRA
		if(bowlAction.getActionType().equalsIgnoreCase("EXTRA")) {
			if(bowlAction.getAction().equalsIgnoreCase("WIDE")) {
				bowl.setRuns(bowl.getRuns() + bowlAction.getRunsConceeded());
			} if(bowlAction.getAction().equalsIgnoreCase("NOBALL")) {
				bowl.setRuns(bowl.getRuns() + bowlAction.getRunsConceeded() + 1);
			} else {
				bowl.setOvers(bowl.getOvers() + 0.1);   //Write logic to convert into base 6.
			}
		}
		
		//RUN
		if(bowlAction.getActionType().equalsIgnoreCase("RUN")) {
			bowl.setOvers(bowl.getOvers() + 0.1);   //Write logic to convert into base 6.
			bowl.setRuns(bowl.getRuns() + bowlAction.getRunsConceeded());
			bowl.setEconomy(bowl.getRuns()/bowl.getOvers());     //((run/balls) * 6)  correct calculation
		}

		//WICKET
		if(bowlAction.getActionType().equalsIgnoreCase("WICKET")) {
			bowl.setOvers(bowl.getOvers() + 0.1);   //Write logic to convert into base 6.
			bowl.setEconomy(bowl.getRuns()/bowl.getOvers());     //((run/balls) * 6)  correct calculation
			if(!bowlAction.getAction().equalsIgnoreCase("RUNOUT")) {
				bowl.setWickets(bowl.getWickets() + 1);
			}
		}
		
		bowlingService.saveOrUpdate(bowl);
	}
	
	private void updateBatPlayer(BowlAction bowlAction) {
		Player batPlayer = playerService.getPlayerByID(bowlAction.getBatPlayerID());
		
		//RUN
		if(!(bowlAction.getActionType().equalsIgnoreCase("WIDE") 
				|| bowlAction.getActionType().equalsIgnoreCase("BYE") 
				|| bowlAction.getActionType().equalsIgnoreCase("LEGBYE"))) {
			batPlayer.setRuns(batPlayer.getRuns() + bowlAction.getRunsConceeded());
			if(4 == bowlAction.getRunsConceeded()) {
				batPlayer.setFours(batPlayer.getFours() + 1);
			} else if(6 == bowlAction.getRunsConceeded()) {
				batPlayer.setSixes(batPlayer.getSixes() + 1);
			}
			
			if(100 == batPlayer.getRuns()) {
				batPlayer.setHundreds(batPlayer.getHundreds() + 1);
			} else if(50 == batPlayer.getRuns()) {
				batPlayer.setFifties(batPlayer.getFifties() + 1);
			}
			
			batPlayer.setBallFaced(batPlayer.getBallFaced() + 1);
			batPlayer.setBatStrikeRate((batPlayer.getRuns() / batPlayer.getBallFaced()) * 100);
			batPlayer.setBatAverage(batPlayer.getRuns() / batPlayer.getMatches());
		}
		
		playerService.saveOrUpdate(batPlayer);
	}
	
	private void updateBowlPlayer(BowlAction bowlAction) {
		Player bowlPlayer = playerService.getPlayerByID(bowlAction.getBowlPlayerID());
		
		if(!(bowlAction.getAction().equalsIgnoreCase("BYE") || bowlAction.getAction().equalsIgnoreCase("LEGBYE"))) {
			bowlPlayer.setRunsConceeded(bowlPlayer.getRunsConceeded() + bowlAction.getRunsConceeded());
			
			if(!bowlAction.getActionType().equalsIgnoreCase("EXTRA")){
				bowlPlayer.setBallBowled(bowlPlayer.getBallBowled() + 1);
				bowlPlayer.setOvers(bowlPlayer.getOvers() + 0.1);
			}
			
			bowlPlayer.setBowlStrikeRate(bowlPlayer.getWickets() / bowlPlayer.getBallBowled());
			bowlPlayer.setBowlAverage(bowlPlayer.getWickets() / bowlPlayer.getRunsConceeded());
			
			if(bowlAction.getActionType().equalsIgnoreCase("WICKET") && !bowlAction.getAction().equalsIgnoreCase("RUNOUT")) {
				bowlPlayer.setWickets(bowlPlayer.getWickets() + 1);
			}
		}
		
		playerService.saveOrUpdate(bowlPlayer);
	}
}
