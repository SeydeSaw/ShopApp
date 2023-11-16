package com.example.shopapp.controllers;

import com.example.shopapp.domain.entity.Order;
import com.example.shopapp.dto.CartDto;
import com.example.shopapp.dto.OrderDto;
import com.example.shopapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public OrderDto createNewOrder(@RequestBody OrderDto orderDto) {
        return orderService.createNewOrder(orderDto);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public OrderDto getById(@PathVariable Long id) {
        return orderService.getById(id);
    }
    @PutMapping("/update/{id}")
    public OrderDto updateById(@RequestBody OrderDto orderDto, @PathVariable Long id) {
        return orderService.updateById(orderDto, id);
    }
    @DeleteMapping("/{id}")
    public void deleteOrderById(@PathVariable Long id) {
        orderService.deleteOrderById(id);
    }
}