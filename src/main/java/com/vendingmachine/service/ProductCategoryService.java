package com.vendingmachine.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.vendingmachine.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;
import com.vendingmachine.domain.ProductCategory;
import com.vendingmachine.repository.ProductCategoryRepository;

/**
 * Title : ProductCategoryService
 * 
 * Description : Class to provide the service for getting different product
 * categories and products information
 * 
 * @author Kalai
 *
 */
@Service
public class ProductCategoryService {

	@Autowired
	private ProductCategoryRepository productCategoryRepository;

	/**
	 * This method return all product categories of vending machine
	 * 
	 * @return -{@link List} of {@link ProductCategory}
	 */
	public List<ProductCategory> getProductCategories() {
		List<ProductCategory> productCategories = new ArrayList<>();

		productCategoryRepository.findAll().forEach(productCategories::add);

		return productCategories;

	}

	/**
	 * This method returns product for provided category id
	 * 
	 * @param id
	 *            product category id
	 * @return -{@link ProductCategory}
	 */
	public ProductCategory getSelectedProductCategory(String id) {

		return productCategoryRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product category " + id + "  does not exist"));
	}

	/**
	 * This method adds the new product to existing vending machine categories.
	 * 
	 * @param productCategory
	 * 
	 */
	public void addProductCategory(ProductCategory productCategory) {
		productCategoryRepository.save(productCategory);
	}

	/**
	 * This method updates the existing product category
	 * 
	 * @param productCategory
	 */
	public void updateProductCategory(ProductCategory productCategory) {
		productCategoryRepository.save(productCategory);
	}

	/**
	 * This method deletes particular product category
	 * 
	 * @param id
	 */
	public void deleteProductCategory(String id) {
		productCategoryRepository.deleteById(id);
	}

}
