package com.vendingmachine.to;

/**
 * Title : ErrorResponse
 * 
 * Description : Class to generate customized error response
 * 
 * @author Kalai
 *
 */
public class ErrorResponse {

	private int statusCode;
	private String message;
	private String details;

	public ErrorResponse() {
	}

	public ErrorResponse(String message, String details, int statusCode) {
		super();
		this.message = message;
		this.details = details;
		this.statusCode = statusCode;
	}

	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode
	 *            the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * @param details
	 *            the details to set
	 */
	public void setDetails(String details) {
		this.details = details;
	}

}