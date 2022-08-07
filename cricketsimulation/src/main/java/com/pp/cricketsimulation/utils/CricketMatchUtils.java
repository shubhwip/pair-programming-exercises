package com.pp.cricketsimulation.utils;

import java.util.List;
import java.util.Random;

import com.pp.cricketsimulation.model.Player;
import com.pp.cricketsimulation.model.State;

/**
 * <h1>CricketMatchUtils class defines utilities method for cricket
 * simulation</h1>
 * 
 * @author Shubham Jain
 * @version 1.0
 */
public class CricketMatchUtils {

	/**
	 * Private Constructor
	 */
	private CricketMatchUtils() {
	}

	/**
	 * @param players
	 * @param currentState
	 * @return returns the updated state
	 */
	public static State strikeChange(List<Player> players, State currentState) {

		State nextState = currentState.createCopy();
		String striker = currentState.getCurrentStriker();
		String nonStriker = currentState.getCurrentNonStriker();
		int positionOfStriker = findPlayerPosition(striker, players);
		int positionOfNonStriker = findPlayerPosition(nonStriker, players);

		if (players.get(positionOfStriker).isOut()) {
			for (int i = 0; i < players.size(); i++) {
				if (!players.get(i).isOut() && !players.get(i).getPlayerName().equals(nonStriker)) {
					nextState.setCurrentStriker(players.get(i).getPlayerName());
					nextState.setCurrentPlayerPosition(i);
					nextState.setCurrentPlayerIsOut(false);
					return nextState;
				}
			}
			nextState.setCurrentStriker(nonStriker);
			nextState.setCurrentPlayerPosition(positionOfNonStriker);
			nextState.setCurrentPlayerIsOut(false);
			nextState.setCurrentNonStriker("");
			return nextState;

		} else if (players.get(positionOfNonStriker).isOut()) {
			for (int i = 0; i < players.size(); i++) {
				if (!players.get(i).isOut() && !players.get(i).getPlayerName().equals(striker)) {
					nextState.setCurrentNonStriker(players.get(i).getPlayerName());
					nextState.setCurrentPlayerPosition(i);
					nextState.setCurrentPlayerIsOut(false);
					return nextState;
				}
			}
		} else {
			nextState.setCurrentStriker(nonStriker);
			nextState.setCurrentPlayerPosition(positionOfNonStriker);
			nextState.setCurrentNonStriker(striker);
		}
		return nextState;
	}

	/**
	 * @param playerName
	 * @param players
	 * @return returns position of playerName in players list
	 */
	public static int findPlayerPosition(String playerName, List<Player> players) {
		players.stream().forEach(player -> player.getPlayerName().equals(playerName));
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getPlayerName().equals(playerName))
				return i;
		}
		return -1;
	}

	/**
	 * @param min
	 * @param max
	 * @return returns random number in the range of min and max
	 */
	public static int randomNumberInRange(int min, int max) {
		Random random = new Random();
		return random.nextInt((max - min) + 1) + min;
	}

}
