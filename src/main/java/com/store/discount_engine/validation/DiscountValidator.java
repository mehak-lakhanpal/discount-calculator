package com.store.discount_engine.validation;

import com.store.discount_engine.Constants;
import com.store.discount_engine.exception.BadRequestException;
import com.store.discount_engine.model.Customer;
import com.store.discount_engine.model.Product;
import com.store.discount_engine.request.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

import static com.store.discount_engine.Constants.CUSTOMER_OBJECT_NULL;

@Slf4j
public class DiscountValidator {

    public static void validateRequest(Order order){
        log.info("In DiscountValidator::validateRequest method");
        if(Objects.isNull(order)){
            log.info("Request object is null");
            throw new BadRequestException(Constants.REQUEST_OBJECT_NULL);
        }
        if(ObjectUtils.isEmpty(order.getProducts())){
            log.info("Product List is null or empty");
            throw new BadRequestException(Constants.PRODUCT_LIST_IS_NULL);
        }
        if(Objects.isNull(order.getCustomer())){
            log.info("Customer object is null");
            throw new BadRequestException(CUSTOMER_OBJECT_NULL);
        }

        for(Product product:order.getProducts()){
            if(Objects.isNull(product.getName()) || product.getName().strip().equals("")){
                log.info("Product name is not valid");
                throw new BadRequestException(Constants.PRODUCT_NAME_INVALID);
            }
            if(Objects.isNull(product.getCategory()) || product.getCategory().strip().equals("")){
                log.info("Product category is not valid for product name::"+product.getName());
                throw new BadRequestException(Constants.PRODUCT_CATEGORY_INVALID+product.getName());
            }
            if(Objects.isNull(product.getPrice())||product.getPrice()==0.0){
                log.info("Product price is not valid for product name::"+product.getName());
                throw new BadRequestException(Constants.PRODUCT_PRICE_INVALID+product.getName());
            }
            if(Objects.isNull(product.getQuantity())||product.getQuantity()==0.0){
                log.info("Product quantity is not valid for product name::"+product.getName());
                throw new BadRequestException(Constants.PRODUCT_QUANTITY_INVALID+product.getName());
            }
        }
    }
}
