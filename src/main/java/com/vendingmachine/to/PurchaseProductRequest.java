package com.vendingmachine.to;

import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Title : PurchaseProductRequest
 * 
 * Description : Class to generate product purchase requests.
 * 
 * @author Kalai
 *
 */
public class PurchaseProductRequest implements Serializable {

	private static final long serialVersionUID = -5108854099536624051L;

	@Size(min = 3, max = 5, message = "Product ID length constraint violated, Length should be between 3 to 5")
	@NotNull(message = "Please enter valid product ID")
	private String pid;

	@Pattern(regexp = "^[1-9][0-9]?$|^100$", message = " Amount should be in range 1 to 100 without decimals and leading zeroes")
	private String amountPaid;

	/**
	 * @return the pid
	 */
	public String getPid() {
		return pid;
	}

	/**
	 * @param pid
	 *            the pid to set
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}

	/**
	 * @return the amountPaid
	 */
	public String getAmountPaid() {
		return amountPaid;
	}

	/**
	 * @param amountPaid
	 *            the amountPaid to set
	 */
	public void setAmountPaid(String amountPaid) {
		this.amountPaid = amountPaid;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
