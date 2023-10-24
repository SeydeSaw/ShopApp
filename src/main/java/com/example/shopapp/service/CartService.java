package com.example.shopapp.service;

import com.example.shopapp.domain.entity.Cart;
import com.example.shopapp.dto.CartDto;

public interface CartService {
    Cart createNewCart(CartDto cartDto);


}
