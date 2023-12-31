package com.example.shopapp.service;

import com.example.shopapp.dto.OrderDetailDto;

import java.util.List;

public interface OrderDetailService {
    OrderDetailDto createNewOrderDetail(OrderDetailDto orderDetailDto);
    OrderDetailDto getById(Long id);
    OrderDetailDto updateById(OrderDetailDto orderDetailDto, Long id);
    void deleteOrderDetailById(Long id);
}