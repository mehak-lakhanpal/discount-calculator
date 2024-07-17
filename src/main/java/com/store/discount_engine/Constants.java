package com.store.discount_engine;

public class Constants {
    public static final String REQUEST_OBJECT_NULL = "Request object cannot be null";
    public static final String PRODUCT_LIST_IS_NULL = "Product List should not be empty";
    public static final String CUSTOMER_OBJECT_NULL = "Customer information cannot be null";
    public static final String PRODUCT_NAME_INVALID = "Please enter valid product name";
    public static final String PRODUCT_CATEGORY_INVALID = "Please enter valid product category of product:: %s";
    public static final String PRODUCT_PRICE_INVALID = "Please enter valid price of product:: %s";
    public static final String PRODUCT_QUANTITY_INVALID = "Please enter valid quantity of product:: %s";

    private Constants() {
        throw new IllegalStateException("Constant class");
    }
}
