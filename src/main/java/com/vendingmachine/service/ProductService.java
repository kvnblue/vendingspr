package com.vendingmachine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vendingmachine.domain.Product;
import com.vendingmachine.exception.ProductNotFoundException;
import com.vendingmachine.repository.ProductRepository;
import com.vendingmachine.to.PurchaseProductRequest;
import com.vendingmachine.to.PurchaseProductResponse;

/**
 * Title : ProductService
 * 
 * Description : Class to provide the service for getting products information
 * and product purchase service
 * 
 * @author Kalai
 *
 */
@Service
public class ProductService {

	enum STATUSES {
		PURCHASE_SUCCESSFUL("Purchase Successful"), INSUFFICIENT_AMOUNT_PAID("Insufficient amount paid"), OUT_OF_STOCK(
				"Out of stock"), TECHNICAL_ERROR("Technical Error");
		String message;

		private STATUSES(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

	}

	private static final String SUCCESS = "SUCCESS";

	private static final String FAILURE = "FAILURE";

	@Autowired
	private ProductRepository productRepository;

	public List<Product> getProductByCategoryId(String categoryId) {
		return productRepository.findByProductCategoryId(categoryId);
	}

	public Product getProductById(String id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product " + id + "  does not exist"));

	}

	public void addProduct(Product products) {
		productRepository.save(products);
	}

	public void updateProduct(Product products) {
		productRepository.save(products);
	}

	public void deleteProduct(String id) {
		productRepository.deleteById(id);
	}

	/**
	 * Method to generate response for the product purchase request
	 * 
	 * @param req
	 * @return {@link PurchaseProductResponse}
	 */
	public PurchaseProductResponse retrieveAvailableProductAfterPurchase(PurchaseProductRequest req) {
		PurchaseProductResponse resp = new PurchaseProductResponse();
		int amountPaid = parseInt(req.getAmountPaid());

		Product product = productRepository.findById(req.getPid())
				.orElseThrow(() -> new ProductNotFoundException("Product " + req.getPid() + "  does not exist"));
		/*
		 * if (product == null) { throw new ProductNotFoundException("Product "
		 * + req.getPid() + "  does not exist"); }
		 */

		STATUSES status = checkStatusOfProductAndRequest(req, product);
		if (status.ordinal() == 0) {
			product.setAvailableCount(product.getAvailableCount() - 1);
			productRepository.save(product);
			resp.setReturnAmount(amountPaid - product.getPrice());
			resp.setPurchaseStatus(SUCCESS);
			resp.setPurchaseFeedback(status.getMessage());
			resp.setErrorCode(status.ordinal());
		}

		else {
			resp.setReturnAmount(amountPaid);
			resp.setPurchaseStatus(FAILURE);
			resp.setPurchaseFeedback(status.getMessage());
			resp.setErrorCode(status.ordinal());
		}
		return resp;
	}

	/**
	 * Validates the input amount and confirms purchase status
	 * 
	 * @param req
	 * @param product
	 * @param amountPaid
	 * @return {@link STATUSES}
	 */
	protected STATUSES checkStatusOfProductAndRequest(PurchaseProductRequest req, Product product) {
		int amountPaid = parseInt(req.getAmountPaid());

		if (amountPaid >= product.getPrice() && product.getAvailableCount() > 0) {
			return STATUSES.PURCHASE_SUCCESSFUL;
		}

		else if (Integer.parseInt(req.getAmountPaid()) < product.getPrice()) {
			return STATUSES.INSUFFICIENT_AMOUNT_PAID;
		} else if (product.getAvailableCount() < 1) {
			return STATUSES.OUT_OF_STOCK;
		}
		return STATUSES.TECHNICAL_ERROR;
	}

	/**
	 * Parses input string and returns int
	 * 
	 * @param str
	 * @return
	 */
	private int parseInt(String str) {
		return Integer.parseInt(str);
	}

}
