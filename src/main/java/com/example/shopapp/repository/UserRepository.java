package com.example.shopapp.repository;

import com.example.shopapp.domain.entity.User;

import java.util.List;

public interface UserRepository  {

    List<User> getAll();
    User getById(long id);
//    User getByUsername(String username);
//
//    User getByEmail(String email);

    void add(String username);
    void delete(long id);

    void addToCartById(long userId, long productId);

    void deleteFromCart(long userId, long productId);

    void clearCart(long userId);
}