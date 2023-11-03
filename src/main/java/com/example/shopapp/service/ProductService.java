package com.example.shopapp.service;

import com.example.shopapp.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createNewProduct(ProductDto productDto);
    ProductDto getById(Long id);
    ProductDto updateById(ProductDto productDto, Long id);
    void deleteProductById(Long id);
    List<ProductDto> getAll();
}