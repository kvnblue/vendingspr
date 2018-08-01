package com.vendingmachine.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.vendingmachine.constants.VmApiRestEndpoints;
import com.vendingmachine.domain.Product;
import com.vendingmachine.domain.ProductCategory;
import com.vendingmachine.service.ProductService;
import com.vendingmachine.to.PurchaseProductRequest;
import com.vendingmachine.to.PurchaseProductResponse;

/**
 * Title: ProductController
 * 
 * Description: Controller class which provides the services related to product
 * 
 * @author Kalai
 *
 */
@RestController
@RequestMapping(path = VmApiRestEndpoints.VM_API)
public class ProductController {

	@Autowired
	private ProductService productService;

	/**
	 * Get all available products under the selected product category
	 * 
	 * @param id
	 *            ProductCategory id
	 * @return {@link List} of {@link Product}
	 */
	@RequestMapping(path = VmApiRestEndpoints.PRODUCTS_BY_CATEGORY_ID)
	public List<Product> getProductByCategoryId(@PathVariable String categoryId) {

		return productService.getProductByCategoryId(categoryId);
	}

	/**
	 * Get product by product id
	 * 
	 * @param id
	 *            Product id
	 * @return {@link Product}
	 */
	@RequestMapping(path = VmApiRestEndpoints.PRODUCT_BY_PRODUCT_ID)
	public Product getProductById(@PathVariable String id) {
		return productService.getProductById(id);
	}

	/**
	 * Creates new product for the given product category
	 * 
	 * @param product
	 * @param categoryId
	 */
	@RequestMapping(path = VmApiRestEndpoints.PRODUCTS_BY_CATEGORY_ID, method = RequestMethod.POST)
	public void addProduct(@RequestBody Product product, @PathVariable String categoryId) {
		product.setProductCategory(new ProductCategory(categoryId, ""));
		productService.addProduct(product);
	}

	/**
	 * Updates the product for the selected product id
	 * 
	 * @param product
	 * @param categoryId
	 * @param id
	 *            Product id
	 * 
	 */
	@RequestMapping(path = VmApiRestEndpoints.PRODUCT_BY_PRODUCT_ID, method = RequestMethod.PUT)
	public void updateProduct(@RequestBody Product product, @PathVariable String categoryId, @PathVariable String id) {
		product.setProductCategory(new com.vendingmachine.domain.ProductCategory(categoryId, ""));
		product.setId(id);
		productService.updateProduct(product);
	}

	/**
	 * Deletes the product by Id
	 * 
	 * @param id
	 *            Product id
	 */
	@RequestMapping(path = VmApiRestEndpoints.PRODUCT_BY_PRODUCT_ID, method = RequestMethod.DELETE)
	public void deleteProduct(@PathVariable String id) {
		productService.deleteProduct(id);
	}

	/**
	 * Method to purchase selected product
	 * 
	 * @param purchaseProductRequest
	 * @return {@link ResponseEntity} of {@link PurchaseProductResponse}
	 */
	@RequestMapping(path = VmApiRestEndpoints.PRODUCT_PURCHASE_REQUEST, method = RequestMethod.POST)
	public ResponseEntity<PurchaseProductResponse> updateProductCategories(
			@Valid @RequestBody PurchaseProductRequest purchaseProductRequest) {
		purchaseProductRequest.setPid(purchaseProductRequest.getPid());
		purchaseProductRequest.setAmountPaid(purchaseProductRequest.getAmountPaid());
		return new ResponseEntity<>(productService.retrieveAvailableProductAfterPurchase(purchaseProductRequest),
				HttpStatus.OK);

	}

}
