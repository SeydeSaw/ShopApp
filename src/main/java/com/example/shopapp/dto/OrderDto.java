package com.example.shopapp.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDto {
    private Long cartId;
    private BigDecimal totalPrice;
}
