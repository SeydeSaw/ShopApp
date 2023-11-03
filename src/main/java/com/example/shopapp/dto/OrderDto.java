package com.example.shopapp.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
public class OrderDto {
    private Long cartId;
    private BigDecimal totalPrice;
}