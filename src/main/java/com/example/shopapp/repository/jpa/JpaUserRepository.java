package com.example.shopapp.repository.jpa;

import com.example.shopapp.domain.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User, Integer> {
    @Transactional
    void deleteByName(String name);
}