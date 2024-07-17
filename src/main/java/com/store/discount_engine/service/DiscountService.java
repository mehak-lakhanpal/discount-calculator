package com.store.discount_engine.service;

import com.store.discount_engine.request.Order;
import com.store.discount_engine.response.DiscountResponse;

public interface DiscountService {
    DiscountResponse calculateNetAmount(Order order);
}
