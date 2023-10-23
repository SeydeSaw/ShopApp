package com.example.shopapp.domain.entity;

import com.example.shopapp.domain.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
//@Builder
@Entity
@Table(name = "user")
public class User {
    private static final String SEQ_NAME = "user_seq";
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1) //шаг последовательности
    private long id;

    @Column(name = "username", unique = true)
    @NotEmpty
    @NotNull
    private String username;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "age")
    private int age;

    @Column(name = "city")
    private String city;

    @Column(name = "email", unique = true)
    @Email
    @NotEmpty
    @NotNull
    private String email;

    @NotEmpty
    @NotNull
    private String password;
    @Column(name = "balance")
    private double balance;

//    @Enumerated(EnumType.STRING)
//    private Role role;

    @OneToOne(cascade = CascadeType.REMOVE) // удаляя пользователя удаляется и корзина
    private Cart cart;


    public User(long id, @NotNull String username, Cart cart) {
        this.id = id;
        this.username = username;
        this.cart = cart;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return username;
    }
    public Cart getCart() {
        return cart;
    }

//    public User(long userId, String username, Cart cart) {
//    }


    //    @OneToMany(cascade = CascadeType.REMOVE) // удаляя пользователя удаляются все заказы
//    private Order order;
}