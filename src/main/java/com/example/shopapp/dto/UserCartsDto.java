package com.example.shopapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserCartsDto {
    private String clientFullName;
    private List<CartDto> cartDtoList;
    private Double totalPrice;
}