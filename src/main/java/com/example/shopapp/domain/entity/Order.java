//package com.example.shopapp.domain.entity;
//
//import com.example.shopapp.domain.enums.OrderStatus;
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Entity
//@Table(name = "order")
//public class Order {
//    private static final String SEQ_NAME = "order_seq";
//
//    @Column(name = "id")
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
//    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME)
//    private int id;
//
//    @CreationTimestamp
//    private LocalDateTime created;
//
//    @UpdateTimestamp
//    private LocalDateTime updated;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    @Column(name = "sum")
//    @NotNull
//    private BigDecimal sum;
//
////    @OneToMany(cascade = CascadeType.ALL)
////    private List<OrderDetails> details = new ArrayList<>();
//
//    @Enumerated(EnumType.STRING)
//    private OrderStatus status;
//}