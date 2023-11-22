package com.example.shopapp.service;

import com.example.shopapp.dto.UserCartsDto;
import com.example.shopapp.entity.Cart;
import com.example.shopapp.dto.CartDto;
import com.example.shopapp.dto.OrderDetailDto;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {
    CartDto createNewCartOnCurrentUser();

    CartDto getById(Long id);

    CartDto updateById(CartDto cartDto, Long id);

    void deleteCartById(Long id);

    CartDto addProductToCart(OrderDetailDto orderDetailDto);

    CartDto completeCartOfUser();

    BigDecimal calculateTotalPrice(Cart cart);

    List<UserCartsDto> getAllCarts();
}