package com.example.shopapp.repository.jpa;

import com.example.shopapp.domain.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaProductRepository extends JpaRepository<Product, Integer> {
    @Transactional
    void deleteByName(String name);

    @Query(value = "select sum(price) from product;", nativeQuery = true)
    double getTotalPrice();

    @Query(value = "select avg(price) from product;", nativeQuery = true)
    double getAveragePrice();
}