package com.vendingmachine.exception;

/**
 * Title : ProductNotFoundException
 * 
 * Description : Custom exception class when product not found
 * 
 * @author Kalai
 *
 */
public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3617025154477323734L;
	private String errorMessage;

	public ProductNotFoundException() {
		super();
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public ProductNotFoundException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

}
