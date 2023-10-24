package com.example.shopapp.repository;

import com.example.shopapp.domain.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JpaProductRepository extends JpaRepository<Product, Long> {
}