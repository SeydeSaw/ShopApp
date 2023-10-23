package com.example.shopapp.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Objects;
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Entity
@Table(name = "product")
public class Product  {
    private static final String SEQ_NAME = "product_seq";

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private long id;

    @Column(name = "name")
    @NotNull
    @NotEmpty
    @Pattern(regexp = "[A-Z][a-z]{2,}")
    private String name;

    @Column(name = "price")
    @NotNull
    @Min(value = 1)
    @Max(value = 999999)
    private double price;

    @Column(name = "description", length = 1000)
    @NotNull
    @NotEmpty
    private String description;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "category_id", nullable = true)
//    private Category category;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "products_categories",
//            joinColumns = @JoinColumn(name = "product_id"),
//            inverseJoinColumns = @JoinColumn(name = "category_id"))
//    private List<Category> categories;

//    @Column(name = "image")
//    private String imageUrl;
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id; // prduct.getId()
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Product(long id, @NotNull String name, @NotNull double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public void add(Product product) {
    }

    public void remove(Product product) {
    }
}