package com.example.shopapp.controllers;

import com.example.shopapp.domain.entity.OrderDetail;
import com.example.shopapp.dto.OrderDetailDto;
import com.example.shopapp.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/orderDetail")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @PostMapping("/create")
    public OrderDetail createNewOrderDetail(@RequestBody OrderDetailDto orderDetailDto) {
        return orderDetailService.createNewOrderDetail(orderDetailDto);
    }
}