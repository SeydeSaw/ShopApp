package com.example.shopapp.service.impl;

import com.example.shopapp.entity.Cart;
import com.example.shopapp.entity.OrderDetail;
import com.example.shopapp.entity.Product;
import com.example.shopapp.dto.OrderDetailDto;
import com.example.shopapp.mapper.OrderDetailMapper;
import com.example.shopapp.repository.CartRepository;
import com.example.shopapp.repository.OrderDetailRepository;
import com.example.shopapp.repository.ProductRepository;
import com.example.shopapp.service.OrderDetailService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderDetailMapper orderDetailMapper;

    @Transactional
    @Override
    public OrderDetailDto createNewOrderDetail(OrderDetailDto orderDetailDto) {
        OrderDetail orderDetail = orderDetailMapper.mapToEntity(orderDetailDto);
        Cart cart = cartRepository.findById(orderDetailDto.getCartId())
                .orElseThrow(() -> new EntityNotFoundException("Cart not find"));
        Product product = productRepository.findById(orderDetailDto.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not find"));
        orderDetail.setCart(cart);
        orderDetail.setProduct(product);
        orderDetail.setCreatedAt(LocalDateTime.now());
        orderDetailRepository.save(orderDetail);
        return orderDetailMapper.mapToDto(orderDetail);
    }

    @Transactional
    @Override
    public OrderDetailDto getById(Long id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order detail not find"));
        return orderDetailMapper.mapToDto(orderDetail);
    }

    @Transactional
    @Override
    public OrderDetailDto updateById(OrderDetailDto orderDetailDto, Long id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order detail not find"));
        updateOrderDetailNewData(orderDetailDto, orderDetail);
        orderDetailRepository.save(orderDetail);
        return convertToOrderDetailDto(orderDetail);
    }

    @Transactional
    @Override
    public void deleteOrderDetailById(Long id) {
        orderDetailRepository.deleteById(id);
    }

    private OrderDetailDto convertToOrderDetailDto(OrderDetail orderDetail) {
        OrderDetailDto newOrderDetailDto = new OrderDetailDto();
        newOrderDetailDto.setCartId(orderDetail.getCart().getId());
        newOrderDetailDto.setProductId(orderDetail.getProduct().getId());
        newOrderDetailDto.setQuantity(orderDetail.getQuantity());
        return newOrderDetailDto;
    }

    private void updateOrderDetailNewData(OrderDetailDto orderDetailDto, OrderDetail orderDetail) {
        if (orderDetailDto.getCartId() != null && !orderDetailDto.getCartId().equals(orderDetail.getCart().getId())) {
            Cart cart = cartRepository.findById(orderDetailDto.getCartId())
                    .orElseThrow(() -> new EntityNotFoundException("Cart Detail  not find"));
            orderDetail.setCart(cart);
        }
        if (orderDetailDto.getProductId() != null && !orderDetailDto.getProductId().equals(orderDetail.getProduct().getId())) {
            Product product = productRepository.findById(orderDetailDto.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException("Product Detail not find"));
            orderDetail.setProduct(product);
        }
        if (orderDetailDto.getQuantity() != null) {
            orderDetail.setQuantity(orderDetailDto.getQuantity());
        }
        ;
    }
}