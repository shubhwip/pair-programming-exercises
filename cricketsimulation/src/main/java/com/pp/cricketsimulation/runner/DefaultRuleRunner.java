package com.pp.cricketsimulation.runner;

import java.util.ArrayList;
import java.util.List;

import com.pp.cricketsimulation.model.Player;
import com.pp.cricketsimulation.model.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pp.cricketsimulation.exceptions.InvalidTeamException;
import com.pp.cricketsimulation.exceptions.NoPlayerOnStrikeException;
import com.pp.cricketsimulation.gamestrategy.IGameStrategy;
import com.pp.cricketsimulation.model.State;
import com.pp.cricketsimulation.rules.IRule;
import com.pp.cricketsimulation.utils.CricketValidationUtils;

/**
 * 
 * <h1>DefaultRuleRunner class defines default rules implementation of
 * IRuleRunner</h1>
 * 
 * 
 * @author Shubham Jain
 * @version 1.0
 *
 */
public class DefaultRuleRunner implements IRuleRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultRuleRunner.class);

	private IGameStrategy gameStrategy;

	/**
	 * @param gameStrategy
	 */
	public DefaultRuleRunner(IGameStrategy gameStrategy) {
		this.gameStrategy = gameStrategy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.simpl.cricketsimulation.runner.IRuleRunner#applyRules(java.util.List)
	 * 
	 * applyRules will be called in order to apply cricket rules to perform match
	 * simulation
	 * 
	 * @param players
	 * 
	 * @return updated list of players
	 * 
	 */
	@Override
	public List<Player> applyRules(List<Player> players) throws NoPlayerOnStrikeException, InvalidTeamException {
		if (players.isEmpty()) {
			throw new NoPlayerOnStrikeException("Team size is insufficient to play");
		}
		final Team team = players.get(0).getTeam();

		// Team Validation
		if (CricketValidationUtils.isInvalidTeam(team)) {
			throw new InvalidTeamException(
					"Team is Invalid as overs or wickets or runs to win are less than 1 or teams name might be not provided");
		}
		final int totalBalls = players.get(0).getTeam().getOvers() * 6;
		final Player firstPlayer = players.get(0) != null ? players.get(0) : new Player("");
		final Player secondPlayer = players.size() > 1 ? players.get(1) : new Player("");

		LOGGER.trace("Maximum number of balls team has {}", totalBalls);

		// Define initial State
		State currentState = new State(firstPlayer.getPlayerName(), secondPlayer.getPlayerName(), 0, team.getWickets(),
				0, team.getRunsToWin(), false, 0);

		return simulateCricketMatchBallByBall(currentState, players, team, totalBalls);
	}

	/**
	 * @param currentState
	 *            initial state
	 * @param players
	 *            list of players
	 * @param team
	 *            team details
	 * @param totalBalls
	 *            Total number of balls team has to play
	 * @return Returns updated list of players
	 */
	private List<Player> simulateCricketMatchBallByBall(State currentState, List<Player> players, Team team,
			int totalBalls) {

		int totalScore = 0;
		List<Player> updatedPlayers = new ArrayList<>(players);
		LOGGER.debug("Initial State {}", currentState);

		// Condition to check if we have still balls to play
		while (currentState.getCurrentBallsPlayed() < totalBalls) {
			int runsScoredInTheBall = gameStrategy.getNextRuns(currentState, players);
			int currentPlayerPosition = currentState.getCurrentPlayerPosition();
			Player currentPlayer = updatedPlayers.get(currentPlayerPosition);
			if (runsScoredInTheBall == -1) {
				currentState.setCurrentPlayerIsOut(true);
				currentState.setCurrentWicketLeft(currentState.getCurrentWicketLeft() - 1);
				updatedPlayers.get(currentState.getCurrentPlayerPosition()).setOut(currentState.isCurrentPlayerIsOut());
			} else {
				totalScore += runsScoredInTheBall;
				currentPlayer.setTotalRuns(currentPlayer.getTotalRuns() + runsScoredInTheBall);
				currentState.setCurrentRunCount(runsScoredInTheBall);
				currentState.setRunsToWin(currentState.getRunsToWin() - runsScoredInTheBall);
			}
			currentPlayer.setTotalBallsPlayed(currentPlayer.getTotalBallsPlayed() + 1);
			currentState.setCurrentBallsPlayed(currentState.getCurrentBallsPlayed() + 1);
			LOGGER.debug("Intermediate {}", currentState);
			currentState = processState(updatedPlayers, currentState);
			if (currentState.getCurrentWicketLeft() == 0 || totalScore > team.getRunsToWin()) {
				break;
			}
		}
		LOGGER.debug("Final {}", currentState);

		if (currentState.getRunsToWin() <= 0) {
			LOGGER.info("Final Result - Bengaluru won by {} wicket and {} balls remaining",
					currentState.getCurrentWicketLeft(), totalBalls - currentState.getCurrentBallsPlayed());
		} else {
			LOGGER.info("Final Result - Bengaluru lost by {} wicket and {} balls remaining",
					currentState.getCurrentWicketLeft(), totalBalls - currentState.getCurrentBallsPlayed());
		}
		return updatedPlayers;
	}

	/**
	 * processState will be used to get next state.
	 * 
	 * @param players
	 *            players in the cricket match
	 * @return returns the next state after striker hits the ball
	 */
	private State processState(List<Player> players, State currentState) {
		State newState = currentState.createCopy();
		for (IRule rule : gameStrategy.getRules()) {
			newState = rule.nextState(newState, players);
		}
		return newState;
	}

}
