package com.store.discount_engine.service.impl;

import com.store.discount_engine.calculator.DiscountCalculator;
import com.store.discount_engine.exception.BadRequestException;
import com.store.discount_engine.model.Customer;
import com.store.discount_engine.model.Product;
import com.store.discount_engine.request.Order;
import com.store.discount_engine.response.DiscountResponse;
import com.store.discount_engine.service.DiscountService;
import com.store.discount_engine.validation.DiscountValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class DiscountServiceImpl implements DiscountService {

    @Value("${non.discounted.products}")
    private String nonDiscountedProducts;

    private final DiscountCalculator discountCalculator;

    @Autowired
    public DiscountServiceImpl(DiscountCalculator discountCalculator){
        this.discountCalculator=discountCalculator;
    }

    /**
     * This method calculate net amount to be paid by customer after applying specified discount
     * @param order
     * @return Net Amount
     */
    @Override
    public DiscountResponse calculateNetAmount(Order order){
        log.info("In DiscountService::calculateNetAmount method");
        DiscountValidator.validateRequest(order);
        List<Product> discountedProductList = order.getProducts().stream().filter(prod -> !prod.getCategory().equals(nonDiscountedProducts)).toList();
        Double totalPrice = calculateTotalPrice(discountedProductList);
        double totalBillAfterDiscount = totalPrice - discountCalculator.calculateDiscountOnTotalAmount(totalPrice,order.getCustomer());
        List<Product> nonDiscountedProductList = order.getProducts().stream().filter(prod -> prod.getCategory().equals(nonDiscountedProducts)).toList();
        if(!nonDiscountedProductList.isEmpty()){
            totalBillAfterDiscount+= calculateTotalPrice(nonDiscountedProductList);
        }
        log.info("Net amount after discount is ::"+totalBillAfterDiscount);
        DiscountResponse discountResponse = new DiscountResponse();
        discountResponse.setNetAmount(totalBillAfterDiscount);
        return  discountResponse;
    }

    /**
     * This method calculates total price of all the products * their quantity
     * @param products
     * @return total price
     */
    private double calculateTotalPrice(List<Product> products) {
        double totalPrice = 0.0;
        for(Product product:products){
            totalPrice= totalPrice+(product.getPrice()*product.getQuantity());
        }
        return totalPrice;
    }
}
