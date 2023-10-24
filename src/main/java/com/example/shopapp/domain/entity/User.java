package com.example.shopapp.domain.entity;

import com.example.shopapp.domain.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
//@Builder
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;


    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "client", fetch = FetchType.LAZY) // удаляя пользователя удаляется и корзина
    private Set<Cart> carts = new HashSet<>();

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "seller", fetch = FetchType.LAZY) // удаляя пользователя удаляется и корзина
    private Set<Product> products = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}