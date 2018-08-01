package com.vendingmachine.to;

import java.io.Serializable;

/**
 * Title : PurchaseProductResponse
 * 
 * Description : Class to generate response for product purchase requests.
 * 
 * @author Kalai
 *
 */
public class PurchaseProductResponse implements Serializable {

	private static final long serialVersionUID = -1624986191875286152L;

	private String purchaseStatus;

	private String purchaseFeedback;

	private int returnAmount;

	private int errorCode;

	/**
	 * @return the purchaseStatus
	 */
	public String getPurchaseStatus() {
		return purchaseStatus;
	}

	/**
	 * @param purchaseStatus
	 *            the purchaseStatus to set
	 */
	public void setPurchaseStatus(String purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}

	/**
	 * @return the purchaseFeedback
	 */
	public String getPurchaseFeedback() {
		return purchaseFeedback;
	}

	/**
	 * @param purchaseFeedback
	 *            the purchaseFeedback to set
	 */
	public void setPurchaseFeedback(String purchaseFeedback) {
		this.purchaseFeedback = purchaseFeedback;
	}

	/**
	 * @return the returnAmount
	 */
	public int getReturnAmount() {
		return returnAmount;
	}

	/**
	 * @param returnAmount
	 *            the returnAmount to set
	 */
	public void setReturnAmount(int returnAmount) {
		this.returnAmount = returnAmount;
	}

	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
