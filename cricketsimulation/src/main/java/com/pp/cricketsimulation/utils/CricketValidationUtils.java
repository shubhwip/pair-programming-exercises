package com.pp.cricketsimulation.utils;

import com.pp.cricketsimulation.model.Team;

/**
 * <h1>CricketValidationUtils class defines utilities method for validation of
 * input</h1>
 * 
 * @author Shubham Jain
 * @version 1.0
 */
public class CricketValidationUtils {
	/**
	 * Private Constructor
	 */
	private CricketValidationUtils() {
	}

	public static boolean isInvalidTeam(final Team team) {
		return (team.getOvers() < 1 || team.getWickets() < 1 || team.getRunsToWin() < 1
				|| team.getPlayingTeam().isEmpty() || team.getOppositionTeam().isEmpty());
	}

}
