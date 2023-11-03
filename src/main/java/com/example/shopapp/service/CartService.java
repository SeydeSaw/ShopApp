package com.example.shopapp.service;

import com.example.shopapp.domain.entity.Cart;
import com.example.shopapp.dto.CartDto;
import com.example.shopapp.dto.OrderDetailDto;

public interface CartService {
    Cart createNewCart(CartDto cartDto);
    CartDto getById(Long id);
    CartDto updateById(CartDto cartDto, Long id);
    void deleteCartById(Long id);
    CartDto addProductToCart(OrderDetailDto orderDetailDto, Long userId);
    CartDto completeCartOfUser(Long userId);
}