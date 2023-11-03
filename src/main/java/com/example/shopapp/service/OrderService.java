package com.example.shopapp.service;

import com.example.shopapp.domain.entity.Order;
import com.example.shopapp.dto.OrderDto;

public interface OrderService {
    Order createNewOrder (OrderDto orderDto);
    OrderDto getById(Long id);
    OrderDto updateById(OrderDto orderDto, Long id);
    void deleteOrderById(Long id);
}