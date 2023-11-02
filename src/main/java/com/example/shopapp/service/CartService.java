package com.example.shopapp.service;

import com.example.shopapp.domain.entity.Cart;
import com.example.shopapp.dto.CartDto;
import com.example.shopapp.dto.OrderDetailDto;
import com.example.shopapp.dto.ProductDto;
import com.example.shopapp.dto.UserDto;
import org.springframework.web.bind.annotation.DeleteMapping;

public interface CartService {
    Cart createNewCart(CartDto cartDto);
    CartDto getById(Long id);
    CartDto updateById(CartDto cartDto, Long id);
    void deleteCartById(Long id);
    CartDto addProductToCart(OrderDetailDto orderDetailDto, Long userId);

    CartDto completeCartOfUser(Long userId);
}