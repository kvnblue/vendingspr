package com.vendingmachine.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vendingmachine.domain.ProductCategory;

/**
 * Title : ProductCategoryRepository
 * 
 * Description : This interface contains the details of different product
 * categories.
 * 
 * @author Kalai
 *
 */
public interface ProductCategoryRepository extends CrudRepository<ProductCategory, String> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	public List<ProductCategory> findAll();

}
