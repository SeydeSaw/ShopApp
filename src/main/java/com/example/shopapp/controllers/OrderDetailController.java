package com.example.shopapp.controllers;

import com.example.shopapp.domain.entity.OrderDetail;
import com.example.shopapp.dto.OrderDetailDto;
import com.example.shopapp.dto.OrderDto;
import com.example.shopapp.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/orderDetail")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    @PostMapping("/create")
    public OrderDetail createNewOrderDetail(@RequestBody OrderDetailDto orderDetailDto) {
        return orderDetailService.createNewOrderDetail(orderDetailDto);
    }

    @GetMapping("/{id}")
    public OrderDetailDto getById(@PathVariable long id) {
        return orderDetailService.getById(id);
    }

    @GetMapping("/all")
    public List<OrderDetailDto> getAll() {
        return orderDetailService.getAll();
    }

    @PutMapping("/update/{id}")
    public OrderDetailDto updateById(@RequestBody OrderDetailDto orderDetailDto, @PathVariable long id) {
        return orderDetailService.updateById(orderDetailDto, id);
    }

    @DeleteMapping("/{id}")
    public void deleteOrderDetailById(@PathVariable long id) {
        orderDetailService.deleteOrderDetailById(id);
    }
}