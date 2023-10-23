package com.example.shopapp.repository;


import com.example.shopapp.domain.entity.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getAll();
    Product getById(long id);

//    Product getByName(String name);
    //List<Product> findAllByCategoryId(long categoryId);

    void add(String name, double price);

    void delete(long id);
}
