package com.vendingmachine.service;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.vendingmachine.domain.Product;
import com.vendingmachine.to.PurchaseProductRequest;

/**
 * Title : ProductServiceTest
 * 
 * Description : Test class to test methods in ProductService class
 * 
 * @author Kalai
 *
 */
public class ProductServiceTest {

	private ProductService productService = new ProductService();
	private PurchaseProductRequest purchaseProductReq;
	private Product product;

	@Before
	public void setUp() {
	}

	/**
	 * Tests to validate product purchase response for successful purchase
	 */
	@Test
	public void checkStatusForSuccessfulPurchase() {
		purchaseProductReq = new PurchaseProductRequest();
		purchaseProductReq.setAmountPaid("8");
		product = new Product();
		product.setAvailableCount(1);
		product.setId("p01");
		product.setPrice(8);
		ProductService.STATUSES output = productService.checkStatusOfProductAndRequest(purchaseProductReq, product);
		assertEquals(0, output.ordinal());
		assertEquals("Purchase Successful", output.getMessage());
	}

	/**
	 * Tests to validate product purchase response for Out of Stock Product
	 */
	@Test
	public void checkStatusForPurchaseOfOutOfStockProduct() {
		purchaseProductReq = new PurchaseProductRequest();
		purchaseProductReq.setAmountPaid("8");
		product = new Product();
		product.setAvailableCount(0);
		product.setId("p01");
		product.setPrice(8);
		ProductService.STATUSES output = productService.checkStatusOfProductAndRequest(purchaseProductReq, product);
		assertEquals(2, output.ordinal());
		assertEquals("Out of stock", output.getMessage());
	}

	/**
	 * Tests to validate product purchase response when Insufficient amount paid
	 */
	@Test
	public void checkStatusOfPurchaseOnInsufficientAmountPaid() {
		purchaseProductReq = new PurchaseProductRequest();
		purchaseProductReq.setAmountPaid("7");
		product = new Product();
		product.setId("p01");
		product.setPrice(8);
		ProductService.STATUSES output = productService.checkStatusOfProductAndRequest(purchaseProductReq, product);
		assertEquals(1, output.ordinal());
		assertEquals("Insufficient amount paid", output.getMessage());
	}
}
