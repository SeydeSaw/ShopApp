package com.example.shopapp.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

//@Getter
//@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
@Entity
@Table(name = "cart")
public class Cart {
    private static final String SEQ_NAME = "cart_seq";

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(name = "cart_product", // имя таблицы с которой работаем
            joinColumns = @JoinColumn(name = "cart_id"), // указываем поле которое ссылается на нашу сущность
            inverseJoinColumns = @JoinColumn(name = "product_id")) // где лежит ссылка на наши продукты
    private List<Product> product = new ArrayList<>();

    public Cart(User user) {
        this.user = user;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    public List<Product> getProduct() {
        return new ArrayList<>(product);
    }

    public void addProduct(Product product) {
        product.add(new Product(product.getId(), product.getName(), product.getPrice()));
    }

    public void removeProduct(Product product) {
        product.remove(product);
    }

    public void clear() {
        product.clear();
    }

    public double getTotalPrice() {
        return product.stream().mapToDouble(Product::getPrice).reduce(Double::sum).orElse(0); //(JpaProduct::getPrice).sum();
    }
}