package com.store.discount_engine.request;

import com.store.discount_engine.model.Customer;
import com.store.discount_engine.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class Order {
    private List<Product> products;
    private Customer customer;
}
