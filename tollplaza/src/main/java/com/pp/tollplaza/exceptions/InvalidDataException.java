/**
 * InvalidDataException class defines exception for invalid data
 */
package com.pp.tollplaza.exceptions;

/**
 * @author SJain
 *
 */
public class InvalidDataException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidDataException(String message) {
		super(message);
	}

}
