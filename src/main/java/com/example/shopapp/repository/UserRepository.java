package com.example.shopapp.repository;

import com.example.shopapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select user from  User user where user.username = :username")
    Optional<User> findByUsername(@Param ("username")String username);

}