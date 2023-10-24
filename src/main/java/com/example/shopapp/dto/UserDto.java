package com.example.shopapp.dto;

import com.example.shopapp.domain.entity.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Data
@AllArgsConstructor
public class UserDto {
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private double balance;

}
