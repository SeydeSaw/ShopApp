package com.example.shopapp.service.impl;

import com.example.shopapp.domain.entity.Cart;
import com.example.shopapp.domain.entity.OrderDetail;
import com.example.shopapp.domain.entity.Product;
import com.example.shopapp.dto.OrderDetailDto;
import com.example.shopapp.repository.CartRepository;
import com.example.shopapp.repository.OrderDetailRepository;
import com.example.shopapp.repository.ProductRepository;
import com.example.shopapp.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final OrderDetailRepository orderDetailRepository;
    @Transactional
    @Override
    public OrderDetail createNewOrderDetail(OrderDetailDto orderDetailDto) {
        OrderDetail orderDetail = new OrderDetail();
        Cart cart = cartRepository.findById(orderDetailDto.getCartId()).orElseThrow(()->new RuntimeException("Cart not find"));
        Product product = productRepository.findById(orderDetailDto.getProductId()).orElseThrow(()->new RuntimeException("Product not find"));
        orderDetail.setCart(cart);
        orderDetail.setProduct(product);
        orderDetail.setQuantity(orderDetailDto.getQuantity());
        orderDetailRepository.save(orderDetail);
        return orderDetail;
    }
}
