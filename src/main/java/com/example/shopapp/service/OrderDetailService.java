package com.example.shopapp.service;

import com.example.shopapp.domain.entity.OrderDetail;
import com.example.shopapp.dto.OrderDetailDto;

import java.util.List;

public interface OrderDetailService {
    OrderDetail createNewOrderDetail(OrderDetailDto orderDetailDto);

    OrderDetailDto getById(long id);

    List<OrderDetailDto> getAll();

    OrderDetailDto updateById(OrderDetailDto orderDetailDto, long id);

    void deleteOrderDetailById(long id);
}