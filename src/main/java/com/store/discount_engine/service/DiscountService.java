package com.store.discount_engine.service;

import com.store.discount_engine.model.Customer;
import com.store.discount_engine.model.Product;
import com.store.discount_engine.request.Order;
import com.store.discount_engine.response.DiscountResponse;

import java.util.List;

public interface DiscountService {
    DiscountResponse calculateNetAmount(Order order);
}
