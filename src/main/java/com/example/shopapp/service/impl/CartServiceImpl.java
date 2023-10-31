package com.example.shopapp.service.impl;

import com.example.shopapp.domain.entity.Cart;
import com.example.shopapp.domain.entity.Product;
import com.example.shopapp.domain.entity.User;
import com.example.shopapp.domain.enums.CartStatus;
import com.example.shopapp.dto.CartDto;
import com.example.shopapp.dto.ProductDto;
import com.example.shopapp.mapper.CartMapper;
import com.example.shopapp.repository.CartRepository;
import com.example.shopapp.repository.UserRepository;
import com.example.shopapp.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class CartServiceImpl implements CartService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

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

    @Transactional
    @Override
    public CartDto getById(long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(()-> new RuntimeException("Cart not find"));
        CartDto newMapperCartDto = cartMapper.mapToDto(cart);
        return newMapperCartDto;
    }

    @Transactional
    @Override
    public CartDto updateById(CartDto cartDto, long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(()-> new RuntimeException("Cart not find"));
        updateCartNewData(cartDto, cart);
        cart.setUpdatedAt(LocalDateTime.now());
        cartRepository.save(cart);
        CartDto newCartDto = convertToCartDto(cart);
        return newCartDto;
    }

    @Transactional
    @Override
    public void deleteCartById(long id) {
        cartRepository.deleteById(id);
    }

    private static CartDto convertToCartDto(Cart cart) {
        CartDto newCartDto = new CartDto();
        newCartDto.setClientId(cart.getClient().getId());
        return newCartDto;
    }
    private void updateCartNewData(CartDto cartDto, Cart cart) {
        if (cartDto.getClientId() != null && !cartDto.getClientId().equals(cart.getClient().getId())) {
            User client = userRepository.findById(cartDto.getClientId()).orElseThrow(() -> new RuntimeException("Client not find"));
            cart.setClient(client);
        }
    }

}