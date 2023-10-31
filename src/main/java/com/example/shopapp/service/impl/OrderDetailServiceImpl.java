package com.example.shopapp.service.impl;

import com.example.shopapp.domain.entity.Cart;
import com.example.shopapp.domain.entity.OrderDetail;
import com.example.shopapp.domain.entity.Product;
import com.example.shopapp.dto.OrderDetailDto;
import com.example.shopapp.mapper.OrderDetailMapper;
import com.example.shopapp.repository.CartRepository;
import com.example.shopapp.repository.OrderDetailRepository;
import com.example.shopapp.repository.ProductRepository;
import com.example.shopapp.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    @Override
    public OrderDetailDto getById(long id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id).orElseThrow(()-> new RuntimeException("Order detail not find"));
        OrderDetailDto newMapperOrderDetailDto = orderDetailMapper.mapToDto(orderDetail);
        return newMapperOrderDetailDto;
    }

    @Transactional
    @Override
    public List<OrderDetailDto> getAll() {
        List<OrderDetail> orderDetailList = orderDetailRepository.findAll();
        return orderDetailMapper.mapToListOrderDetailDto(orderDetailList);
    }

    @Transactional
    @Override
    public OrderDetailDto updateById(OrderDetailDto orderDetailDto, long id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id).orElseThrow(()-> new RuntimeException("Order detail not find"));
        updateOrderDetailNewData(orderDetailDto, orderDetail);
        orderDetailRepository.save(orderDetail);
        OrderDetailDto newOrderDetailDto = convertToOrderDetailDto(orderDetail);
        return newOrderDetailDto;
    }

    @Transactional
    @Override
    public void deleteOrderDetailById(long id) {
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
            Cart cart = cartRepository.findById(orderDetailDto.getCartId()).orElseThrow(() -> new RuntimeException("Cart not find"));
            orderDetail.setCart(cart);
        }
        if (orderDetailDto.getProductId() != null && !orderDetailDto.getProductId().equals(orderDetail.getProduct().getId())) {
            Product product = productRepository.findById(orderDetailDto.getProductId()).orElseThrow(() -> new RuntimeException("Product not find"));
            orderDetail.setProduct(product);
        }
        if (orderDetailDto.getQuantity() >=1) {
            orderDetail.setQuantity(orderDetailDto.getQuantity());
        };
    }
}
