package me.shubhamjain.cricketsimulation.model;

/**
 * Team class identifies team structure containing playingTeam, oppositionTeam,
 * wickets, runsToWin and overs. Team is Immutable Class
 * 
 * @author Shubham Jain
 * @version 1.0
 *
 */
public class Team {

	/**
	 * The Team who is current playing in the game
	 */
	private final String playingTeam;
	/**
	 * The Team who is opposite to team currently playing
	 */
	private final String oppositionTeam;
	/**
	 * Number of wicket left of current playing team
	 */
	private final int wickets;
	/**
	 * Total number of runs needed to win
	 */
	private final int runsToWin;

	/**
	 * Total number of overs left to win
	 */
	private final int overs;

	public Team(final String playingTeam, final String oppositionTeam, final int wickets, final int runsToWin,
			final int overs) {
		this.playingTeam = playingTeam;
		this.oppositionTeam = oppositionTeam;
		this.wickets = wickets;
		this.runsToWin = runsToWin;
		this.overs = overs;
	}

	public int getOvers() {
		return overs;
	}

	public String getPlayingTeam() {
		return playingTeam;
	}

	public String getOppositionTeam() {
		return oppositionTeam;
	}

	public int getWickets() {
		return wickets;
	}

	public int getRunsToWin() {
		return runsToWin;
	}

	@Override
	public String toString() {
		return "Team Details [playingTeam=" + playingTeam + ", oppositionTeam=" + oppositionTeam + ", wickets="
				+ wickets + ", runsToWin=" + runsToWin + ", overs=" + overs + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((oppositionTeam == null) ? 0 : oppositionTeam.hashCode());
		result = prime * result + overs;
		result = prime * result + ((playingTeam == null) ? 0 : playingTeam.hashCode());
		result = prime * result + runsToWin;
		result = prime * result + wickets;
		return result;
	}

	/* (non-Javadoc)
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
		if (!(obj instanceof Team)) {
			return false;
		}
		Team other = (Team) obj;
		if (oppositionTeam == null) {
			if (other.oppositionTeam != null) {
				return false;
			}
		} else if (!oppositionTeam.equals(other.oppositionTeam)) {
			return false;
		}
		if (overs != other.overs) {
			return false;
		}
		if (playingTeam == null) {
			if (other.playingTeam != null) {
				return false;
			}
		} else if (!playingTeam.equals(other.playingTeam)) {
			return false;
		}
		if (runsToWin != other.runsToWin) {
			return false;
		}
		if (wickets != other.wickets) {
			return false;
		}
		return true;
	}
	
	

}
