package com.example.shopapp.controllers;

import com.example.shopapp.domain.entity.User;
import com.example.shopapp.dto.UserDto;
import com.example.shopapp.repository.UserRepository;
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

    @PostMapping("/create")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }


    @GetMapping("/{id}")
    public UserDto getById(@PathVariable long id) {
        return userService.getById(id);
    }

    @GetMapping("/all")
    public List<UserDto> getAll() {
        return userService.getAll();
    }
    @PutMapping("/update/{id}")
    public UserDto updateById(@RequestBody UserDto userDto, @PathVariable long id) {
        return userService.updateById(userDto, id);
    }
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable long id) {
        userService.deleteUserById(id);
    }
}