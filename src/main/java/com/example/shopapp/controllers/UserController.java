package com.example.shopapp.controllers;

import com.example.shopapp.dto.UserDto;
import com.example.shopapp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "user controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    @Operation(summary = "for all users")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    @Operation(summary = "for ADMIN")
    public UserDto getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping("/current")
    public UserDto getCurrentUser() {
        return userService.getCurrentUser();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    @Operation(summary = "for ADMIN")
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @PutMapping("/update/{id}")
    public UserDto updateById(@RequestBody UserDto userDto, @PathVariable Long id) {
        return userService.updateById(userDto, id);
    }

    @DeleteMapping("/current")
    public void deleteCurrentUser() {
        userService.deleteCurrentUser();
    }
}