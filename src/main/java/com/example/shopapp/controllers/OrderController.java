package com.example.shopapp.controllers;

import com.example.shopapp.domain.entity.Order;
import com.example.shopapp.dto.OrderDto;
import com.example.shopapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public Order createNewOrder(@RequestBody OrderDto orderDto) {
        return orderService.createNewOrder(orderDto);
    }
}