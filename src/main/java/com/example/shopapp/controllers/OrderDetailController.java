package com.example.shopapp.controllers;

import com.example.shopapp.domain.entity.OrderDetail;
import com.example.shopapp.dto.OrderDetailDto;
import com.example.shopapp.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/orderDetail")
public class OrderDetailController {

    private final OrderDetailService orderDetailService;
    @PostMapping("/create")
    public OrderDetailDto createNewOrderDetail(@RequestBody OrderDetailDto orderDetailDto) {
        return orderDetailService.createNewOrderDetail(orderDetailDto);
    }
    @GetMapping("/{id}")
    public OrderDetailDto getById(@PathVariable Long id) {
        return orderDetailService.getById(id);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    public List<OrderDetailDto> getAll() {
        return orderDetailService.getAll();
    }
    @PutMapping("/update/{id}")
    public OrderDetailDto updateById(@RequestBody OrderDetailDto orderDetailDto, @PathVariable Long id) {
        return orderDetailService.updateById(orderDetailDto, id);
    }
    @DeleteMapping("/{id}")
    public void deleteOrderDetailById(@PathVariable Long id) {
        orderDetailService.deleteOrderDetailById(id);
    }
}