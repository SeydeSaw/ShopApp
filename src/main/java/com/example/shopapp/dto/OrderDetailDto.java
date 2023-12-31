package com.example.shopapp.dto;

import lombok.*;

@Data
public class OrderDetailDto {
    private Long cartId;
    private Long productId;
    private String productName;
    private Integer quantity;
}