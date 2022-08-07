/**
 * 
 */
package com.pp.cricketsimulation.exceptions;

/**
 * NoPlayerOnStrikeException throws exception when no player can be on strike as
 * number of team members is less than 1
 * 
 * @author Shubham Jain
 * @version 1.0
 * 
 */
public class NoPlayerOnStrikeException extends Exception {

	private final String exceptionMsg;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoPlayerOnStrikeException(String exceptionMsg) {
		super();
		this.exceptionMsg = exceptionMsg;
	}

	public String getExceptionMsg() {
		return exceptionMsg;
	}
}
