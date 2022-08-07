package com.pp.cricketsimulation.rules;

import java.util.List;

import com.pp.cricketsimulation.model.Player;
import com.pp.cricketsimulation.model.State;

/**
 * <h1>IRule</h1>
 * 
 * @author Shubham Jain
 * @version 1.0
 */
public interface IRule {
	/**
	 * @return returns the next state after applying rule
	 */
	State nextState(State currentState, List<Player> players);
}
