package com.example.shopapp.domain.entity;

import com.example.shopapp.domain.enums.CartStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

//@Getter
//@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Entity
@Table(name = "carts")
public class Cart {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private User client;

    @JsonIgnore
    @OneToOne(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Order order;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CartStatus status;

    @JsonIgnore
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<OrderDetail> orderDetails = new HashSet<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return id == cart.id && Objects.equals(createdAt, cart.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}