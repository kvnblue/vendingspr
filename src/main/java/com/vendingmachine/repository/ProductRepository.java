package com.vendingmachine.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.vendingmachine.domain.Product;

/**
 * Title : ProductRepository
 * 
 * Description : This interface contains the information of products for each category.
 * 
 * @author Kalai
 *
 */
public interface ProductRepository extends CrudRepository<Product, String> {

	/**
	 * returns the product for the provided category
	 * 
	 * @param categoryId
	 * @return -{@link List} of {@link Product}
	 */
	public List<Product> findByProductCategoryId(String categoryId);

}
