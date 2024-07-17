package com.store.discount_engine.model;

import lombok.Data;

@Data
public class Product {
    private String name;
    private String category;

    public Product(String name, String category, double price, int quantity) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }
    public Product(){

    }

    private double price;
    private int quantity;
}
