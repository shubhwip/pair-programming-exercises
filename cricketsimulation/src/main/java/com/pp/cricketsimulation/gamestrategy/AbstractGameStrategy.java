package com.pp.cricketsimulation.gamestrategy;

import java.util.List;

import com.pp.cricketsimulation.model.Player;
import com.pp.cricketsimulation.model.State;
import com.pp.cricketsimulation.rules.IRule;
import com.pp.cricketsimulation.utils.CricketMatchUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AbstractGameStrategy class contains methods for setting and getting rules and
 * getNextRuns method calculates the runs based on probablity distribution.
 * 
 * @author Shubham Jain
 * @version 1.0
 * 
 */
public abstract class AbstractGameStrategy implements IGameStrategy {

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractGameStrategy.class);

	private IRule[] rules;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.simpl.cricketsimulation.main.IGameStrategy#setRules(com.simpl.
	 * cricketsimulation.rules.IRule[])
	 * 
	 * @param rules rules which will be applied for game
	 */
	@Override
	public void setRules(IRule[] rules) {
		this.rules = rules;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.simpl.cricketsimulation.main.IGameStrategy#getRules()
	 * 
	 * @return returns the rules for game
	 * 
	 */
	@Override
	public IRule[] getRules() {
		return rules;
	}

	/**
	 * getNextRuns will be used to calculate next runs It is based on probablity
	 * distribution given for each player
	 * 
	 * @param currentState
	 *            current state in the match
	 * @param players
	 *            players currently in batting
	 * @return runs
	 */
	@Override
	public int getNextRuns(State currentState, List<Player> players) {
		String playerName = currentState.getCurrentStriker();
		int position = CricketMatchUtils.findPlayerPosition(playerName, players);
		LOGGER.trace("Position of Player {} is {}", playerName, position);
		double sumOfAllProbablities = players.get(position).getPlayerProbablity().stream()
				.mapToDouble(Double::doubleValue).sum();
		LOGGER.trace("Total Probablities Sum {}", sumOfAllProbablities);
		double randomNumber = CricketMatchUtils.randomNumberInRange(0, (int) sumOfAllProbablities);
		LOGGER.trace("Random Number {}", randomNumber);
		double weightSum = 0;
		List<Double> playersProb = players.get(position).getPlayerProbablity();
		for (int i = 0; i < playersProb.size(); i++) {
			weightSum += playersProb.get(i);
			LOGGER.trace("Weight Sum {}", weightSum);
			if (randomNumber <= weightSum) {
				if (i == playersProb.size() - 1)
					break;
				return i;
			}
		}
		return -1;
	}

}
