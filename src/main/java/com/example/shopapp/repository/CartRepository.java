package com.example.shopapp.repository;

import com.example.shopapp.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("select cart from  Cart cart where cart.client.id = :id AND cart.status = 'ACTIVE'")
    Optional<Cart> getCartByUserId(@Param("id") Long id);
}