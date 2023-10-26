package com.example.shopapp.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private Long sellerId;
    private String name;
    private BigDecimal price;
    private String description;

}