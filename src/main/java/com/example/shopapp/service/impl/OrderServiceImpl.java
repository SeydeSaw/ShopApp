package com.example.shopapp.service.impl;

import com.example.shopapp.domain.entity.Cart;
import com.example.shopapp.domain.entity.Order;
import com.example.shopapp.dto.OrderDto;
import com.example.shopapp.repository.CartRepository;
import com.example.shopapp.repository.OrderRepository;
import com.example.shopapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    @Transactional
    @Override
    public Order createNewOrder(OrderDto orderDto) {
        Order order = new Order();
        Cart cart = cartRepository.findById(orderDto.getCartId()).orElseThrow(()->new RuntimeException("Cart not find"));
        order.setCart(cart);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        order.setTotalPrice(orderDto.getTotalPrice());
        orderRepository.save(order);
        return order;
    }
}