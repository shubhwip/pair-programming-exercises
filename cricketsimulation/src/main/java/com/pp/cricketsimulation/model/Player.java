package com.pp.cricketsimulation.model;

import java.util.List;

/**
 * 
 * Player is a Model class containing player details i.e. playerName, team,
 * playerProbablity, totalRuns, totalBallsPlayed, isOut.
 * 
 * @author Shubham Jain
 * @version 1.0
 *
 */
public class Player {

	private String playerName; // Name of player
	private Team team; // Player's Team

	/**
	 * This list will contain Probability of getting 0, 1, 2, 3, 4, 5, 6 runs in
	 * respective order.
	 */

	private List<Double> playerProbablity; // Player's Probability
	private int totalRuns; // total Run by player
	private int totalBallsPlayed; // total balls played
	private boolean isOut; // during playing the match if he is out

	public Player(String playerName, Team team, List<Double> playerProbablity, int totalRuns, int totalBallsPlayed,
			boolean isOut) {
		this.playerName = playerName;
		this.team = team;
		this.playerProbablity = playerProbablity;
		this.totalRuns = totalRuns;
		this.totalBallsPlayed = totalBallsPlayed;
		this.isOut = isOut;
	}

	public Player(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * @return
	 */
	public int getTotalRuns() {
		return totalRuns;
	}

	/**
	 * @param totalRuns
	 */
	public void setTotalRuns(int totalRuns) {
		this.totalRuns = totalRuns;
	}

	/**
	 * @return returns the total balls played by the team
	 */
	public int getTotalBallsPlayed() {
		return totalBallsPlayed;
	}

	/**
	 * @param totalBallsPlayed
	 */
	public void setTotalBallsPlayed(int totalBallsPlayed) {
		this.totalBallsPlayed = totalBallsPlayed;
	}

	/**
	 * @return returns true if player is out
	 */
	public boolean isOut() {
		return isOut;
	}

	/**
	 * @param isOut
	 */
	public void setOut(boolean isOut) {
		this.isOut = isOut;
	}

	/**
	 * @return returns player name
	 */
	public String getPlayerName() {
		return playerName;
	}

	public Team getTeam() {
		return team;
	}

	/**
	 * @return returns the list of player's probability
	 */
	public List<Double> getPlayerProbablity() {
		return playerProbablity;
	}

	/**
	 * This method is used to create copy of Player
	 * 
	 * @return Player
	 */
	public Player createCopy() {
		return new Player(playerName, team, playerProbablity, totalRuns, totalBallsPlayed, isOut);
	}

	@Override
	public String toString() {
		return "Player Details [playerName=" + playerName + ", team=" + team + ", totalRuns=" + totalRuns
				+ ", totalBallsPlayed=" + totalBallsPlayed + ", isOut=" + isOut + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isOut ? 1231 : 1237);
		result = prime * result + ((playerName == null) ? 0 : playerName.hashCode());
		result = prime * result + ((playerProbablity == null) ? 0 : playerProbablity.hashCode());
		result = prime * result + ((team == null) ? 0 : team.hashCode());
		result = prime * result + totalBallsPlayed;
		result = prime * result + totalRuns;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Player)) {
			return false;
		}
		Player other = (Player) obj;
		if (isOut != other.isOut) {
			return false;
		}
		if (playerName == null) {
			if (other.playerName != null) {
				return false;
			}
		} else if (!playerName.equals(other.playerName)) {
			return false;
		}
		if (playerProbablity == null) {
			if (other.playerProbablity != null) {
				return false;
			}
		} else if (!playerProbablity.equals(other.playerProbablity)) {
			return false;
		}
		if (team == null) {
			if (other.team != null) {
				return false;
			}
		} else if (!team.equals(other.team)) {
			return false;
		}
		if (totalBallsPlayed != other.totalBallsPlayed) {
			return false;
		}
		if (totalRuns != other.totalRuns) {
			return false;
		}
		return true;
	}

}
