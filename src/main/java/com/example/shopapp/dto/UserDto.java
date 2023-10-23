package com.example.shopapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class UserDto {
    private String username;
    private String firstName;
    private String lastName;
    private int age;
    private String city;
    private String email;
    private String password;
    private double balance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return age == userDto.age && Objects.equals(username, userDto.username) && Objects.equals(firstName, userDto.firstName) && Objects.equals(lastName, userDto.lastName) && Objects.equals(city, userDto.city) && Objects.equals(email, userDto.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, firstName, lastName, age, city, email);
    }
}
