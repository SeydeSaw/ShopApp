package com.example.shopapp.service;

import com.example.shopapp.domain.entity.Cart;
import com.example.shopapp.dto.CartDto;
import org.springframework.web.bind.annotation.DeleteMapping;

public interface CartService {
    Cart createNewCart(CartDto cartDto);


    CartDto getById(long id);

    CartDto updateById(CartDto cartDto, long id);

    void deleteCartById(long id);



}