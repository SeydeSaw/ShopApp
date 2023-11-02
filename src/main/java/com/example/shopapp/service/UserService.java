package com.example.shopapp.service;

import com.example.shopapp.domain.entity.User;
import com.example.shopapp.dto.UserDto;

import java.util.List;


public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto getById(long id);

    List<UserDto> getAll();
    UserDto updateById(UserDto userDto, long id);

    void deleteUserById(Long id);




//    List<User> getAll();
//
//    void add(User user);
////    void login(String username, String password);
//    User getById(long id);
////    User getByUsername(String username);
////    User getByEmail(String email);
//
//    void deleteById(long id);
//
//    void deleteByUsername(String username);
//
//    int getCount();
//
//    double getTotalPriceById(long id);
//
//    double getAveragePriceById(long id);
//
//    void addToCartById(long userId, long productId);
//
//    void deleteFromCart(long userId, long productId);
//
//    void clearCart(long userId);

}