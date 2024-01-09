package me.shubhamjain.cricketsimulation.rules;

import java.util.List;

import me.shubhamjain.cricketsimulation.model.Player;
import me.shubhamjain.cricketsimulation.model.State;
import me.shubhamjain.cricketsimulation.utils.CricketMatchUtils;

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
