package com.example.shopapp.dto;

import lombok.*;

import java.util.List;

@Data
public class CartDto {
    private Long clientId;
    private String clientFullName;
    private List<OrderDetailDto> orderDetailDtoList;
    private String status;
    private Double totalPrice;
}