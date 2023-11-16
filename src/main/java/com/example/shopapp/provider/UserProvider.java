package com.example.shopapp.provider;

import com.example.shopapp.entity.User;
import com.example.shopapp.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserProvider {
    public User getCurrentUser() {
        return ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).user();
    }
}
