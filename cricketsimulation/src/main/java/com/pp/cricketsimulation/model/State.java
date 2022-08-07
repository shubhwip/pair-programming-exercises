package com.pp.cricketsimulation.model;

/**
 * State class shows simulation of cricket match ball by ball
 * 
 * @author Shubham Jain
 * @version 1.0
 * 
 */
public class State {

	private String currentStriker; // Current bats man player in the field
	private String currentNonStriker; // Current Non Striker in the field
	private int currentRunCount; // Current Run count
	private int currentWicketLeft; // Current wickets left
	private int currentBallsPlayed; // Current balls played
	private int currentRunsToWin; // Total number of runs currently needed to win
	private boolean currentPlayerIsOut; // if current player is out
	private int currentPlayerPosition; // Position of current Player

	public State(String currentStriker, String currentNonStriker, int currentRunCount, int currentWicketLeft,
			int currentBallsPlayed, int currentRunsToWin, boolean currentPlayerIsOut, int currentPlayerPosition) {
		this.currentStriker = currentStriker;
		this.currentNonStriker = currentNonStriker;
		this.currentRunCount = currentRunCount;
		this.currentWicketLeft = currentWicketLeft;
		this.currentBallsPlayed = currentBallsPlayed;
		this.currentRunsToWin = currentRunsToWin;
		this.currentPlayerIsOut = currentPlayerIsOut;
		this.currentPlayerPosition = currentPlayerPosition;
	}

	public int getCurrentPlayerPosition() {
		return currentPlayerPosition;
	}

	public void setCurrentPlayerPosition(int currentPlayerPosition) {
		this.currentPlayerPosition = currentPlayerPosition;
	}

	public String getCurrentStriker() {
		return currentStriker;
	}

	public void setCurrentStriker(String currentStriker) {
		this.currentStriker = currentStriker;
	}

	public String getCurrentNonStriker() {
		return currentNonStriker;
	}

	public void setCurrentNonStriker(String currentNonStriker) {
		this.currentNonStriker = currentNonStriker;
	}

	public int getCurrentRunCount() {
		return currentRunCount;
	}

	public void setCurrentRunCount(int currentRunCount) {
		this.currentRunCount = currentRunCount;
	}

	public int getCurrentWicketLeft() {
		return currentWicketLeft;
	}

	public void setCurrentWicketLeft(int currentWicketLeft) {
		this.currentWicketLeft = currentWicketLeft;
	}

	public int getCurrentBallsPlayed() {
		return currentBallsPlayed;
	}

	public void setCurrentBallsPlayed(int currentBallsPlayed) {
		this.currentBallsPlayed = currentBallsPlayed;
	}

	public int getRunsToWin() {
		return currentRunsToWin;
	}

	public void setRunsToWin(int currentRunsToWin) {
		this.currentRunsToWin = currentRunsToWin;
	}

	public boolean isCurrentPlayerIsOut() {
		return currentPlayerIsOut;
	}

	public void setCurrentPlayerIsOut(boolean currentPlayerIsOut) {
		this.currentPlayerIsOut = currentPlayerIsOut;
	}

	/**
	 * This method is used to create copy of State
	 * 
	 * @return State
	 */
	public State createCopy() {
		return new State(currentStriker, currentNonStriker, currentRunCount, currentWicketLeft, currentBallsPlayed,
				currentRunsToWin, currentPlayerIsOut, currentPlayerPosition);
	}

	@Override
	public String toString() {
		return "State [currentStriker=" + currentStriker + ", currentNonStriker=" + currentNonStriker
				+ ", currentRunCount=" + currentRunCount + ", currentWicketLeft=" + currentWicketLeft
				+ ", currentBallsPlayed=" + currentBallsPlayed + ", currentRunsToWin=" + currentRunsToWin
				+ ", currentPlayerIsOut=" + currentPlayerIsOut + ", currentPlayerPosition=" + currentPlayerPosition
				+ "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + currentBallsPlayed;
		result = prime * result + ((currentNonStriker == null) ? 0 : currentNonStriker.hashCode());
		result = prime * result + (currentPlayerIsOut ? 1231 : 1237);
		result = prime * result + currentPlayerPosition;
		result = prime * result + currentRunCount;
		result = prime * result + currentRunsToWin;
		result = prime * result + ((currentStriker == null) ? 0 : currentStriker.hashCode());
		result = prime * result + currentWicketLeft;
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
		if (!(obj instanceof State)) {
			return false;
		}
		State other = (State) obj;
		if (currentBallsPlayed != other.currentBallsPlayed) {
			return false;
		}
		if (currentNonStriker == null) {
			if (other.currentNonStriker != null) {
				return false;
			}
		} else if (!currentNonStriker.equals(other.currentNonStriker)) {
			return false;
		}
		if (currentPlayerIsOut != other.currentPlayerIsOut) {
			return false;
		}
		if (currentPlayerPosition != other.currentPlayerPosition) {
			return false;
		}
		if (currentRunCount != other.currentRunCount) {
			return false;
		}
		if (currentRunsToWin != other.currentRunsToWin) {
			return false;
		}
		if (currentStriker == null) {
			if (other.currentStriker != null) {
				return false;
			}
		} else if (!currentStriker.equals(other.currentStriker)) {
			return false;
		}
		if (currentWicketLeft != other.currentWicketLeft) {
			return false;
		}
		return true;
	}
	
	

}
