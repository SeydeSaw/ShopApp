package com.example.shopapp.controllers;

import com.example.shopapp.dto.CartDto;
import com.example.shopapp.dto.OrderDetailDto;
import com.example.shopapp.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/cart")
public class CartController {

    private final CartService cartService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public CartDto createNewCartOnCurrentUser() {
        return cartService.createNewCartOnCurrenUser();
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
    @PostMapping("/add-product")
    public CartDto addProductToCart(@RequestBody OrderDetailDto orderDetailDto) {
        return cartService.addProductToCart(orderDetailDto);
    }
    @PutMapping("/complete-cart")
    public CartDto completeCartOfUser() {
        return cartService.completeCartOfUser();
    }
}