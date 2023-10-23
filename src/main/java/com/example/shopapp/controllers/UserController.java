package com.example.shopapp.controllers;

import com.example.shopapp.domain.entity.User;
import com.example.shopapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    //@Qualifier("clientService")
    @Autowired
    private UserService service;

    @GetMapping
    public List<User> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable long id) {
        return service.getById(id);
    }

    @PostMapping
    public User add(@RequestBody User user) {
        service.add(user);
        return user;
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable long id) {
        service.deleteById(id);
    }

    @GetMapping("/deletename/{name}")
    public void delete(@PathVariable String name) {
        service.deleteByUsername(name);
    }

    @GetMapping("/count")
    public long getCount() {
        return service.getCount();
    }

    @GetMapping("/total/{id}")
    public double getTotalPrice(@PathVariable long id) {
        return service.getTotalPriceById(id);
    }

    @GetMapping("/average/{id}")
    public double getAverage(@PathVariable long id) {
        return service.getAveragePriceById(id);
    }

    @GetMapping("/add/{userId}/{productId}")
    public void addToCart(@PathVariable long userId, @PathVariable long productId) {
        service.addToCartById(userId, productId);
    }

    @GetMapping("/delete/{userId}/{productId}")
    public void deleteFromCart(@PathVariable long userId, @PathVariable long productId) {
        service.deleteFromCart(userId, productId);
    }

    @GetMapping("/clear/{id}")
    public void clearCart(@PathVariable long id) {
        service.clearCart(id);
    }
}