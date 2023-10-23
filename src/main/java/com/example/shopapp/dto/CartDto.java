package com.example.shopapp.dto;

import com.example.shopapp.domain.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class CartDto {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public double getTotalPrice() {
        return products.stream().mapToDouble(Product::getPrice).reduce(Double::sum).orElse(0);
    }
}
