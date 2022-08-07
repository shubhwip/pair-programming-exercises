package com.pp.cricketsimulation.rules;

import java.util.List;

import com.pp.cricketsimulation.model.Player;
import com.pp.cricketsimulation.model.State;
import com.pp.cricketsimulation.utils.CricketMatchUtils;

/**
 * <h1>PlayerOutRuleImpl implements rule when current striker player is out</h1>
 * 
 * @author Shubham Jain
 * @version 1.0
 */
public class PlayerOutRuleImpl implements IRule {

	/**
	 * @return returns the next state after applying rule
	 */
	@Override
	public State nextState(State currentState, List<Player> players) {
		State newState = currentState.createCopy();
		if (currentState.isCurrentPlayerIsOut()) {
			newState = CricketMatchUtils.strikeChange(players, newState);
		}
		return newState;
	}

}
