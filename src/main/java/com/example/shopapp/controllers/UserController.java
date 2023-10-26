package com.example.shopapp.controllers;

import com.example.shopapp.domain.entity.User;
import com.example.shopapp.dto.UserDto;
import com.example.shopapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/createClient")
    public User createClient(@RequestBody UserDto userDto) {
        return userService.createNewClient(userDto);
    }

    @PostMapping("/createSeller")
    public User createSeller(@RequestBody UserDto userDto) {
        return userService.createNewSeller(userDto);
    }

}