package com.vendingmachine.constants;

/**
 * This class contains list of end points used for Vending machine App
 * 
 * @author Kalai
 *
 */
public class VmApiRestEndpoints {

	private VmApiRestEndpoints() {

	}

	public static final String VM_API = "/vm/api";
	public static final String PRODUCT_CATEGORIES = "/productcategories";
	public static final String PRODUCTS_FOR_SELECTED_CATEGORY = "/productcategories/{categoryId}";
	public static final String PRODUCTS_BY_CATEGORY_ID = "/productcategories/{categoryId}/products";
	public static final String PRODUCT_BY_PRODUCT_ID = "/productcategories/{categoryId}/products/{id}";
	public static final String PRODUCT_PURCHASE_REQUEST = "/productcategories/product/purchase";

}
