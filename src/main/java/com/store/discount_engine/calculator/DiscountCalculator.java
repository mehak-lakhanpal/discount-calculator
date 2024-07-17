package com.store.discount_engine.calculator;

import com.store.discount_engine.model.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DiscountCalculator {


    @Value("${employee.discount.percentage}")
    private double employeeDiscountPercentage;

    @Value("${affiliate.discount.percentage}")
    private double affiliateDiscountPercentage;

    @Value("${old.customer.discount.percentage}")
    private double oldCustomerDiscountPercentage;

    @Value("${new.customer.discount.percentage}")
    private double newCustomerDiscountPercentage;

    /**
     * This method calculates discount on the basis of customer type
     *
     * @param totalPrice
     * @param customer
     * @return total discount
     */
    public double calculateDiscountOnTotalAmount(Double totalPrice, Customer customer) {
        double discount = 0;
        if (totalPrice != 0.0) {
            switch (customer.getCustomerType()) {
                case EMPLOYEE -> {
                    discount = (totalPrice * employeeDiscountPercentage) / 100;
                }
                case AFFILIATE -> {
                    discount = (totalPrice * affiliateDiscountPercentage) / 100;
                }
                case CUSTOMER -> {
                    if (customer.getYearsWithStore() > 2) {
                        discount = (totalPrice * oldCustomerDiscountPercentage) / 100;
                    } else {
                        discount = Math.floor(totalPrice / 100) * newCustomerDiscountPercentage;
                    }
                }
            }
        }
        return discount;
    }
}
