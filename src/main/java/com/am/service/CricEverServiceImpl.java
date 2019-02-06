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
public class CricEverServiceImpl implements CricService {
	
	Logger logger = LoggerFactory.getLogger(CricEverServiceImpl.class);
	
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
		logger.info("CricEverServiceImpl :: New Request for updateBowl");
		/*
		 * MatchID, bowlPlayerID, batPlayerID, actionType are Mandatory
		 */
		
		if(bowlAction.getActionType().equalsIgnoreCase("RUN")) {
			updateRun(bowlAction);
		}else if(bowlAction.getActionType().equalsIgnoreCase("OUT")) {
			updateOut(bowlAction);
		}
		
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
		return "Test CricEverServiceImpl Success";
	}

	private void updateOut(BowlAction bowlAction) {
		updateMatch(bowlAction);				//Update Match	==> overs, Wicket
		updateBowlingWicket(bowlAction);			//Bowling	==> overs, wickets
		updateBatPlayerWicket(bowlAction);			//Player - Bat	==> batSrt, batAvg, ballPlayed
		updateBowlPlayerWicket(bowlAction);			//Player - Bowl ==> Wickets, BlStrikeRate, BlAvg, Overs, BallBowled.
													//Player - Keeper	==> Stump.
		
		if(bowlAction.getAction().equalsIgnoreCase("CATCH")) {
			updateBattingWicket(bowlAction, "CATCH");	//Batsmen ==> Howout, ball, bowler, filder
		} else if(bowlAction.getAction().equalsIgnoreCase("BOWLED")) {
			updateBattingWicket(bowlAction, "BOWLED");	//Batsmen	==> Howout, ball, bowler
		} else if(bowlAction.getAction().equalsIgnoreCase("STUMPED")) {
			updateBattingWicket(bowlAction, "STUMPED");	//Batsmen	==> Howout, ball, bowler, Keeper
		} else if(bowlAction.getAction().equalsIgnoreCase("LBW")) {
			updateBattingWicket(bowlAction, "LBW");	//Batsmen	==> Howout, ball, bowler
		}
	}

	//Player - Bowl ==> Wickets, BlStrikeRate, BlAvg, Overs, BallBowled.
	private void updateBowlPlayerWicket(BowlAction bowlAction) {
		Player bowlPlayer = playerService.getPlayerByID(bowlAction.getBowlPlayerID());
		if(!bowlAction.getAction().equalsIgnoreCase("RUNOUT")) {
			bowlPlayer.setWickets(bowlPlayer.getWickets() + 1);
		}
		bowlPlayer.setBallBowled(bowlPlayer.getBallBowled() + 1);
		bowlPlayer.setOvers(bowlPlayer.getOvers() + 0.1);
		bowlPlayer.setBowlStrikeRate(bowlPlayer.getWickets() / bowlPlayer.getBallBowled());
		bowlPlayer.setBowlAverage(bowlPlayer.getWickets() / bowlPlayer.getRunsConceeded());
		playerService.saveOrUpdate(bowlPlayer);
	}

	//Player - Bat	==> batSrt, batAvg, ballPlayed
	private void updateBatPlayerWicket(BowlAction bowlAction) {
		Player batPlayer = playerService.getPlayerByID(bowlAction.getBatPlayerID());
		batPlayer.setBallFaced(batPlayer.getBallFaced() + 1);
		batPlayer.setBatStrikeRate((batPlayer.getRuns() / batPlayer.getBallFaced()) * 100);
		batPlayer.setBatAverage(batPlayer.getRuns() / batPlayer.getMatches());   //Consider not outs (matches - notout)
		playerService.saveOrUpdate(batPlayer);
	}

	//Bowling	==> overs, wickets, Economy
	private void updateBowlingWicket(BowlAction bowlAction) {
		Bowling bowl = bowlingService.getBowlerByID(bowlAction.getMatchID(), bowlAction.getBowlPlayerID());
		bowl.setOvers(bowl.getOvers() + 0.1);   //Write logic to convert into base 6.
		bowl.setEconomy(bowl.getRuns()/bowl.getOvers());     //((run/balls) * 6)  correct calculation
		if(!bowlAction.getAction().equalsIgnoreCase("RUNOUT")) {
			bowl.setWickets(bowl.getWickets() + 1);
		}
		
		bowlingService.saveOrUpdate(bowl);
	}

	//Howout, ball, bowler, filder
	private void updateBattingWicket(BowlAction bowlAction, String howOut) {
		Batting bat = battingService.getBattingByID(bowlAction.getMatchID(), bowlAction.getBatPlayerID());
		bat.setHowOut(howOut);
		bat.setBalls(bat.getBalls() + 1);
		bat.setBowlerID(bowlAction.getBowlPlayerID());
		if(0 != bowlAction.getOutFielderID()) {
			bat.setFielderID(bowlAction.getOutFielderID());
		}
		battingService.saveOrUpdate(bat);
	}

	private void updateMatch(BowlAction bowlAction) {
		Matches match = matchesService.getMatchByID(bowlAction.getMatchID());
			match.setOvers(match.getOvers() + 0.1);		//Write logic to convert into base 6.
			match.setRuns(match.getRuns() + bowlAction.getRunsConceeded());
			if(bowlAction.getActionType().equalsIgnoreCase("OUT")) {
				match.setWickets(match.getWickets() + 1);
			}
			
		matchesService.saveOrUpdate(match);
	}

	private void updateRun(BowlAction bowlAction) {
		//Update match;
			//runs, overs(Base 6)
		updateMatch(bowlAction);
		
		//update batting
			//runs, bowl, calculate strike rate.
		updateBattingRun(bowlAction);
		
		//update bowling
			//runs, overs, calculate economy
		updateBowlingRun(bowlAction);
		
		//update Bat player
			//runs, batStrikeRate, batAvg, 4s, 6s, 50s, 100s
		updateBatPlayerRun(bowlAction);
		
		//update bowl player
			//runs, BlStrike, Eco, BlAvg
		updateBowlPlayerRun(bowlAction);
	}

	/**
	 * @param bowlAction
	 */
	private void updateBowlingRun(BowlAction bowlAction) {
		Bowling bowl = bowlingService.getBowlerByID(bowlAction.getMatchID(), bowlAction.getBowlPlayerID());
		bowl.setRuns(bowl.getRuns() + bowlAction.getRunsConceeded());
		bowl.setOvers(bowl.getOvers() + 0.1);   //Write logic to convert into base 6.
		bowl.setEconomy(bowl.getRuns()/bowl.getOvers());     //((run/balls) * 6)  correct calculation
		bowlingService.saveOrUpdate(bowl);
	}

	/**
	 * @param bowlAction
	 */
	private void updateBowlPlayerRun(BowlAction bowlAction) {
		Player bowlPlayer = playerService.getPlayerByID(bowlAction.getBowlPlayerID());
		bowlPlayer.setRunsConceeded(bowlPlayer.getRunsConceeded() + bowlAction.getRunsConceeded());
		bowlPlayer.setBallBowled(bowlPlayer.getBallBowled() + 1);
		bowlPlayer.setBowlStrikeRate(bowlPlayer.getWickets() / bowlPlayer.getBallBowled());
		bowlPlayer.setBowlAverage(bowlPlayer.getWickets() / bowlPlayer.getRunsConceeded());
		playerService.saveOrUpdate(bowlPlayer);
	}

	/**
	 * @param bowlAction
	 */
	private void updateBatPlayerRun(BowlAction bowlAction) {
		Player batPlayer = playerService.getPlayerByID(bowlAction.getBatPlayerID());
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
		playerService.saveOrUpdate(batPlayer);
	}

	/**
	 * @param bowlAction
	 * @return
	 */
	private Batting updateBattingRun(BowlAction bowlAction) {
		Batting bat = battingService.getBattingByID(bowlAction.getMatchID(), bowlAction.getBatPlayerID());
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
		battingService.saveOrUpdate(bat);
		return bat;
	}

}
