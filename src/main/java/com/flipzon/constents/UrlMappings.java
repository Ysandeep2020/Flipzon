package com.flipzon.constents;

public class UrlMappings {

	// Base Version
	public static final String BASE = "/api";
	public static final String VERSION = "/v1";
	public static final String BASE_VERSION = BASE + VERSION;

	public static final String PK = "/{pk}"; // pathVariable
	public static final String BY_PK = "/byPk"; // RequestParam

	// Customers
	public static final String CUSTOMERS = BASE_VERSION + "/customers";
	public static final String BY_MOBILE_NO = "/mobileNo";
	public static final String BY_EMAIL = "/byEmail";
	public static final String PRODUCTS_BY_CUSTOMER_PK ="/products/customerPk" + BY_PK;
	// products
	public static final String PRODUCTS = BASE_VERSION + "/products";
	// product Type
	public static final String PRODUCT_TYPES = BASE_VERSION + "/productTypes";

	// commetns

	// review

	// ratings

}
