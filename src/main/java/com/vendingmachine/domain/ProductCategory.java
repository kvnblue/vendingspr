package com.vendingmachine.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

/**
 * Title : ProductCategory
 * 
 * Description : This class defines the different product categories of the
 * vending machine
 * 
 * @author Kalai
 *
 */
@Entity
public class ProductCategory {
	@Id
	@Column(name = "categoryId")
	private String id;
	private String category;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "productCategory")
	@OrderBy("productName ASC")
	private List<Product> products;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public ProductCategory() {

	}

	public ProductCategory(String id, String category) {
		super();
		this.id = id;
		this.setCategory(category);
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
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

}
