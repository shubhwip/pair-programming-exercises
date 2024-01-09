package me.shubhamjain.cricketsimulation.exceptions;

/**
 * InvalidTeamException throws exception when team structure is not valid
 * 
 * @author Shubham Jain
 * @version 1.0
 * 
 */
public class InvalidTeamException extends Exception {
	private final String exceptionMsg;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidTeamException(String exceptionMsg) {
		super();
		this.exceptionMsg = exceptionMsg;
	}

	public String getExceptionMsg() {
		return exceptionMsg;
	}
}
