package com.example.shopapp.dto;

import lombok.Data;

@Data
public class OrderDetailDto {
    private Long cartId;
    private Long productId;
    private int quantity;
}