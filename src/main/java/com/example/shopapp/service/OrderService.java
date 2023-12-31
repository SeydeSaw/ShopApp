package com.example.shopapp.service;

import com.example.shopapp.dto.OrderDto;

public interface OrderService {
    OrderDto createNewOrder (OrderDto orderDto);
    OrderDto getById(Long id);
    OrderDto updateById(OrderDto orderDto, Long id);
    void deleteOrderById(Long id);

}