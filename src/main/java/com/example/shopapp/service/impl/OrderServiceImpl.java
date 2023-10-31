package com.example.shopapp.service.impl;

import com.example.shopapp.domain.entity.Cart;
import com.example.shopapp.domain.entity.Order;
import com.example.shopapp.domain.entity.User;
import com.example.shopapp.dto.OrderDto;
import com.example.shopapp.mapper.OrderMapper;
import com.example.shopapp.repository.CartRepository;
import com.example.shopapp.repository.OrderRepository;
import com.example.shopapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

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

    @Transactional
    @Override
    public OrderDto getById(long id) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new RuntimeException("Order not find"));
        OrderDto newMapperOrderDto = orderMapper.mapToDto(order);
        return newMapperOrderDto;
    }

    @Transactional
    @Override
    public OrderDto updateById(OrderDto orderDto, long id) {
        Order order = orderRepository.findById(id).orElseThrow(()-> new RuntimeException("Order not find"));
        updateOrderNewData(orderDto, order);
        order.setUpdatedAt(LocalDateTime.now());
        orderRepository.save(order);
        OrderDto newOrderDto = convertToOrderDto(order);
        return newOrderDto;
    }

    @Transactional
    @Override
    public void deleteOrderById(long id) {
        orderRepository.deleteById(id);
    }

    private static OrderDto convertToOrderDto(Order order) {
        OrderDto newOrderDto = new OrderDto();
        newOrderDto.setCartId(order.getCart().getId());
        newOrderDto.setTotalPrice(BigDecimal.valueOf(order.getTotalPrice().doubleValue()));
        return newOrderDto;
    }

    private void updateOrderNewData(OrderDto orderDto, Order order) {
        if (orderDto.getCartId() != null && !orderDto.getCartId().equals(order.getCart().getId())) {
            Cart cart = cartRepository.findById(orderDto.getCartId()).orElseThrow(() -> new RuntimeException("Cart not find"));
            order.setCart(cart);
        }
        if (orderDto.getTotalPrice() != null) {
            order.setTotalPrice(orderDto.getTotalPrice());
        };

    }
}