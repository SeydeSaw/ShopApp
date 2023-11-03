package com.example.shopapp.dto;

import lombok.*;

@Data
public class ProductDto {
    private Long id;
    private Long sellerId;
    private String name;
    private Double price;
    private String description;
    private String sellerFullName;
}