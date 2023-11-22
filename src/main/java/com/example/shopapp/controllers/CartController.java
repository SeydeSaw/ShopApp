package com.example.shopapp.controllers;

import com.example.shopapp.dto.CartDto;
import com.example.shopapp.dto.OrderDetailDto;
import com.example.shopapp.dto.UserCartsDto;
import com.example.shopapp.entity.Cart;
import com.example.shopapp.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/cart")
public class CartController {

    private final CartService cartService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    @Operation(summary = "for current user")
    public CartDto createNewCartOnCurrentUser() {
        return cartService.createNewCartOnCurrentUser();
    }

    @GetMapping("/{id}")
    public CartDto getById(@PathVariable Long id) {
        return cartService.getById(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    @Operation(summary = "for ADMIN")
    public List<UserCartsDto> getAllCarts() {
        return cartService.getAllCarts();
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
    @Operation(summary = "for current cart")
    public CartDto addProductToCart(@RequestBody OrderDetailDto orderDetailDto) {
        return cartService.addProductToCart(orderDetailDto);
    }

    @PutMapping("/complete-cart")
    @Operation(summary = "for current cart")
    public CartDto completeCartOfUser() {
        return cartService.completeCartOfUser();
    }
}