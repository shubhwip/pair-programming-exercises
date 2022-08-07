package com.pp.cricketsimulation.rules;

import java.util.List;

import com.pp.cricketsimulation.model.Player;
import com.pp.cricketsimulation.model.State;
import com.pp.cricketsimulation.utils.CricketMatchUtils;

/**
 * <h1>StrikeRuleImpl implements rule of over complete or scoring odd runs</h1>
 * 
 * @author Shubham Jain
 * @version 1.0
 */
public class StrikeRuleImpl implements IRule {

	/**
	 * @return returns the next state after applying rule
	 */
	@Override
	public State nextState(State currentState, List<Player> players) {
		State newState = currentState.createCopy();
		int currentRunCount = newState.getCurrentRunCount();
		int currentBallsPlayedCount = newState.getCurrentBallsPlayed();
		if (!currentState.isCurrentPlayerIsOut()
				&& (currentRunCount == 1 || currentRunCount == 3 || currentRunCount == 5)
				&& !currentState.getCurrentNonStriker().isEmpty()) {
			newState = CricketMatchUtils.strikeChange(players, newState);
		}
		if (!currentState.isCurrentPlayerIsOut() && currentBallsPlayedCount % 6 == 0
				&& !currentState.getCurrentNonStriker().isEmpty()) {
			newState = CricketMatchUtils.strikeChange(players, newState);
		}
		return newState;
	}

}
