package com.vendingmachine.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Title: Product
 * 
 * Description: This class contains all the product information of vending
 * machine
 * 
 * @author Kalai
 *
 *
 */
@Entity
public class Product {

	@Id
	@Size(min = 3, max = 5, message = "Product ID length should be between 3 to 5")
	private String id;
	private int price;
	private String productName;
	private int availableCount;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "categoryId", nullable = false)
	@JsonIgnore
	private ProductCategory productCategory;

	public Product() {

	}

	public Product(String id, String productName, Integer price, String categoryId, int availableCount) {
		super();
		this.id = id;
		this.productName = productName;
		this.productCategory = new ProductCategory(categoryId, "");
		this.price = price;
		this.availableCount = availableCount;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the availableCount
	 */
	public int getAvailableCount() {
		return availableCount;
	}

	/**
	 * @param availableCount
	 *            the availableCount to set
	 */
	public void setAvailableCount(int availableCount) {
		this.availableCount = availableCount;
	}

	/**
	 * @return the productCategory
	 */
	public ProductCategory getProductCategory() {
		return productCategory;
	}

	/**
	 * @param productCategory
	 *            the productCategory to set
	 */
	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

}
