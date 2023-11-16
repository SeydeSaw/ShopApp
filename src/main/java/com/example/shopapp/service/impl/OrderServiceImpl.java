package com.example.shopapp.service.impl;

import com.example.shopapp.domain.entity.Cart;
import com.example.shopapp.domain.entity.Order;
import com.example.shopapp.domain.entity.User;
import com.example.shopapp.domain.enums.CartStatus;
import com.example.shopapp.dto.OrderDto;
import com.example.shopapp.mapper.OrderMapper;
import com.example.shopapp.provider.UserProvider;
import com.example.shopapp.repository.CartRepository;
import com.example.shopapp.repository.OrderRepository;
import com.example.shopapp.service.CartService;
import com.example.shopapp.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final CartRepository cartRepository;
    private final CartService cartService;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Transactional
    @Override
    public OrderDto createNewOrder(OrderDto orderDto) {
        Order order = new Order();
        Cart cart = cartRepository.findById(orderDto.getCartId()).orElseThrow(() ->
                new EntityNotFoundException("Cart not find"));
        order.setCart(cart);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());
        order.setTotalPrice(cartService.calculateTotalPrice(cart));
        orderRepository.save(order);
        return orderMapper.mapToDto(order);
    }

    @Transactional
    @Override
    public OrderDto getById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not find"));
        return orderMapper.mapToDto(order);
    }

    @Transactional
    @Override
    public OrderDto updateById(OrderDto orderDto, Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not find"));
        updateOrderNewData(orderDto, order);
        order.setUpdatedAt(LocalDateTime.now());
        orderRepository.save(order);
        return orderMapper.mapToDto(order);
    }

    @Transactional
    @Override
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }

    private void updateOrderNewData(OrderDto orderDto, Order order) {
        Cart cart = null;
        if (orderDto.getCartId() != null && !orderDto.getCartId().equals(order.getCart().getId())) {
            cart = cartRepository.findById(orderDto.getCartId()).orElseThrow(() ->
                    new EntityNotFoundException("Cart not find"));
            order.setCart(cart);
        } else if (orderDto.getCartId() != null) {
            cart = order.getCart();
        }
        order.setTotalPrice(cartService.calculateTotalPrice(cart));
    }
}