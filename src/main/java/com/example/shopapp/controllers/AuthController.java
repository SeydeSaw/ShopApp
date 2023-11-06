package com.example.shopapp.controllers;

import com.example.shopapp.domain.entity.User;
import com.example.shopapp.dto.UserDto;
import com.example.shopapp.security.JwtService;
import com.example.shopapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/auth")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;
    @PostMapping
    public String authorize (@RequestBody UserDto userDto) {
        User user = userService.findByUsernameAndPassword(userDto.getUsername(), userDto.getPassword());
        return jwtService.generateToken(user.getUsername());
    }
}