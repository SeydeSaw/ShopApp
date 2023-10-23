package com.example.shopapp.repository.jpa;

import com.example.shopapp.domain.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCartRepository extends JpaRepository<Cart, Integer> {
}