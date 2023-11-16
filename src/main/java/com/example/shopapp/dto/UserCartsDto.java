package com.example.shopapp.dto;

import com.example.shopapp.entity.Cart;
import lombok.Data;

import java.util.List;

@Data
public class UserCartsDto {
    private String clientFullName;
    private List<CartDto> cartDtoList;
    private Double totalPrice;
}
