package com.example.shopapp.service;

import com.example.shopapp.domain.entity.Cart;
import com.example.shopapp.dto.CartDto;
import com.example.shopapp.dto.OrderDetailDto;

import java.math.BigDecimal;

public interface CartService {
    CartDto createNewCartOnCurrenUser();
    CartDto getById(Long id);
    CartDto updateById(CartDto cartDto, Long id);
    void deleteCartById(Long id);
    CartDto addProductToCart(OrderDetailDto orderDetailDto);
    CartDto completeCartOfUser();
    BigDecimal calculateTotalPrice(Cart cart);
}