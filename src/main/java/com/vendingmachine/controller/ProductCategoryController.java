package com.vendingmachine.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.vendingmachine.constants.VmApiRestEndpoints;
import com.vendingmachine.domain.ProductCategory;
import com.vendingmachine.service.ProductCategoryService;

/**
 * Title: ProductCategoryController
 * 
 * Description: Controller class which provides the services related to product
 * categories
 * 
 * 
 * @author Kalai
 *
 */
@RestController
@RequestMapping(path = VmApiRestEndpoints.VM_API)
public class ProductCategoryController {

	@Autowired
	private ProductCategoryService vendingMachineService;

	/**
	 * Gets all available product categories
	 * 
	 * @return {@link List} of {@link ProductCategory}
	 */
	@RequestMapping(path = VmApiRestEndpoints.PRODUCT_CATEGORIES)
	public List<ProductCategory> getProductCategories() {
		return vendingMachineService.getProductCategories();
	}

	/**
	 * Gets all Products by Product Category Id
	 * 
	 * @param id
	 *            ProductCategory categoryId
	 * @return {@link ProductCategory}
	 */
	@RequestMapping(path = VmApiRestEndpoints.PRODUCTS_FOR_SELECTED_CATEGORY)
	public ProductCategory getProductCategories(@PathVariable String categoryId) {

		return vendingMachineService.getSelectedProductCategory(categoryId);
	}

}
