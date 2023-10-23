package com.example.shopapp.config;

import com.example.shopapp.repository.ProductRepository;
import com.example.shopapp.repository.UserRepository;
import com.example.shopapp.repository.mysql.MySqlProductRepository;
import com.example.shopapp.repository.mysql.MySqlUserRepository;
import com.example.shopapp.service.ProductService;
import com.example.shopapp.service.UserService;
import com.example.shopapp.service.impl.ProductServiceImpl;
import com.example.shopapp.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public UserRepository userRepository() {
        return new MySqlUserRepository();
    }

    @Bean
    public ProductRepository productRepository() {
        return new MySqlProductRepository();
    }

    @Bean
    public UserService customerService() {
        return new UserServiceImpl();
    }

    @Bean
    public ProductService productService() {
        return new ProductServiceImpl();
    }
}