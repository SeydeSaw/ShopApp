package com.example.shopapp.service;

import com.example.shopapp.domain.entity.Order;
import com.example.shopapp.dto.OrderDto;

public interface OrderService {
    Order createNewOrder (OrderDto orderDto);

    OrderDto getById(long id);

    OrderDto updateById(OrderDto orderDto, long id);

    void deleteOrderById(long id);
}