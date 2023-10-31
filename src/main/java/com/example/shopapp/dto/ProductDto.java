package com.example.shopapp.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private Long sellerId;
    private String name;
    private Double price;
    private String description;
    private String sellerFullName;

}