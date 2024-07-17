package com.store.discount_engine.controller;

import com.store.discount_engine.request.Order;
import com.store.discount_engine.response.DiscountResponse;
import com.store.discount_engine.service.DiscountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/discount")
@Slf4j
public class DiscountController {
    @Autowired
    private DiscountService discountService;

    /**
     * This API calculates discount over a list of products on the basis of Customer Type and returns the net amount to be paid by customer
     * @param order
     * @return Net amount
     */
    @PostMapping
    public DiscountResponse calculateNetAmount(@RequestBody Order order){
        log.info("In DiscountController::calculateNetAmount method");
        return discountService.calculateNetAmount(order);
    }
}
