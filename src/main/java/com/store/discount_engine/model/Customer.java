package com.store.discount_engine.model;

import com.store.discount_engine.enums.CustomerType;
import lombok.Data;

@Data
public class Customer {
    private String name;
    private CustomerType customerType;
    private int yearsWithStore;
}
