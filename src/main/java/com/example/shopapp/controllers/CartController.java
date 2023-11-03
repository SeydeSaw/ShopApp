package com.example.shopapp.controllers;

import com.example.shopapp.domain.entity.Cart;
import com.example.shopapp.dto.CartDto;
import com.example.shopapp.dto.OrderDetailDto;
import com.example.shopapp.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/cart")
public class CartController {

    private final CartService cartService;
    @PostMapping("/create")
    public Cart createNewCart(@RequestBody CartDto cartDto) {
        return cartService.createNewCart(cartDto);
    }
    @GetMapping("/{id}")
    public CartDto getById(@PathVariable Long id) {
        return cartService.getById(id);
    }
    @PutMapping("/update/{id}")
    public CartDto updateById(@RequestBody CartDto cartDto, @PathVariable Long id) {
        return cartService.updateById(cartDto, id);
    }
    @DeleteMapping("/{id}")
    public void deleteCartById(@PathVariable Long id) {
        cartService.deleteCartById(id);
    }
    @PostMapping("/add-product/{userId}")
    public CartDto addProductToCart(@RequestBody OrderDetailDto orderDetailDto, @PathVariable(name = "userId") Long userId) {
        return cartService.addProductToCart(orderDetailDto, userId);
    }
    @PutMapping("/complete-cart/{userId}")
    public CartDto completeCartOfUser(@PathVariable(name = "userId") Long userId) {
        return cartService.completeCartOfUser(userId);
    }
}