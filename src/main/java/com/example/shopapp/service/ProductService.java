package com.example.shopapp.service;

import com.example.shopapp.dto.ProductDto;

import java.util.List;

public interface ProductService {
//    Product createNewProduct(ProductDto productDto);
    ProductDto createNewMapperProduct(ProductDto productDto);
    ProductDto getById(long id);
    ProductDto updateById(ProductDto productDto, long id);
    void deleteProductById(Long id);
    List<ProductDto> getAll();
    


//    List<Product> getAll();
//
//    Product getById(long id);
//
//    void add(Product product);
//    void deleteById(long id);
//
//    long getCount();
//
//    double getTotalPrice();
//
//    double getAveragePrice();
}