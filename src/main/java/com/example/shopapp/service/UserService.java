package com.example.shopapp.service;

import com.example.shopapp.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto getById(Long id);
    List<UserDto> getAll();
    UserDto updateById(UserDto userDto, Long id);
    void deleteUserById(Long id);
}