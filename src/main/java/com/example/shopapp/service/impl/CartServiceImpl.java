package com.example.shopapp.service.impl;

import com.example.shopapp.domain.entity.Cart;
import com.example.shopapp.domain.entity.User;
import com.example.shopapp.domain.enums.CartStatus;
import com.example.shopapp.dto.CartDto;
import com.example.shopapp.repository.CartRepository;
import com.example.shopapp.repository.UserRepository;
import com.example.shopapp.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    @Transactional
    @Override
    public Cart createNewCart(CartDto cartDto) {
        Cart cart = new Cart();
        User client = userRepository.findById(cartDto.getClientId()).orElseThrow(()->new RuntimeException("Client not find"));
        cart.setClient(client);
        cart.setCreatedAt(LocalDateTime.now());
        cart.setUpdatedAt(LocalDateTime.now());
        cart.setStatus(CartStatus.NEW);
        cartRepository.save(cart);
        return cart;
    }
}