package com.example.shopapp.service;

import com.example.shopapp.domain.entity.Product;
import com.example.shopapp.dto.ProductDto;

public interface ProductService {
    Product createNewProduct(ProductDto productDto);

//    List<Product> getAll();
//
//    Product getById(long id);
//
//    void add(Product product);
//    void deleteById(long id);
//
//    void deleteByName(String name);
//
//    //List<Product> getAllByCategoryId(long categoryId);
//    long getCount();
//
//    double getTotalPrice();
//
//    double getAveragePrice();
}