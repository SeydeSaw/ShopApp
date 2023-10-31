package com.example.shopapp.controllers;

import com.example.shopapp.domain.entity.Cart;
import com.example.shopapp.dto.CartDto;
import com.example.shopapp.dto.ProductDto;
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
    public CartDto getById(@PathVariable long id) {
        return cartService.getById(id);
    }

    @PutMapping("/update/{id}")
    public CartDto updateById(@RequestBody CartDto cartDto, @PathVariable long id) {
        return cartService.updateById(cartDto, id);
    }

    @DeleteMapping("/{id}")
    public void deleteCartById(@PathVariable long id) {
        cartService.deleteCartById(id);
    }

}