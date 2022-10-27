/**
 * 
 */
package com.arun.seecity;

/**
 * An attempt to own our mistakes gracefully.
 * 
 * @author Arun S P
 *
 */
public class AppException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6670295434560961672L;

	/**
	 * We will use this to quickly identify the area of the issue.
	 */
	private String exceptionCode;

	/**
	 * 
	 */
	public AppException(String exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	/**
	 * @param message
	 */
	public AppException(String exceptionCode, String message) {
		super(message);
		this.exceptionCode = exceptionCode;
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AppException(String exceptionCode, String message, Throwable cause) {
		super(message, cause);
		this.exceptionCode = exceptionCode;
	}

	public String getCode() {
		return exceptionCode;
	}
}
