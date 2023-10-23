//package com.example.shopapp.domain.entity;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.HashSet;
//import java.util.Objects;
//import java.util.Set;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
////@Builder
//@Entity
//@Table(name = "category")
//public class Category {
//    private static final String SEQ_NAME = "category_seq";
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
//    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1) //шаг последовательности
//    private long id;
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL)
//    private Set<Product> books = new HashSet<>();
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Category category = (Category) o;
//        return id == category.id; //.getId();
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
//}
