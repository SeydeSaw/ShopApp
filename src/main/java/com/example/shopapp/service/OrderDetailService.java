package com.example.shopapp.service;

import com.example.shopapp.domain.entity.OrderDetail;
import com.example.shopapp.dto.OrderDetailDto;

public interface OrderDetailService {
    OrderDetail createNewOrderDetail(OrderDetailDto orderDetailDto);
}