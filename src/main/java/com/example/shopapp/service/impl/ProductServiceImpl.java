package com.example.shopapp.service.impl;

import com.example.shopapp.domain.entity.Product;
import com.example.shopapp.repository.ProductRepository;
import com.example.shopapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;
    @Override
    public List<Product> getAll() {
        return repository.getAll();
    }

    @Override
    public Product getById(long id) {
        return repository.getById(id);
    }

    @Override
    public void add(Product product) {
        repository.add(product.getName(), product.getPrice());
    }

    @Override
    public void deleteById(long id) {
        repository.delete(id);
    }

    @Override
    public void deleteByName(String name) {
        long idToDelete = repository.getAll().stream().filter(x -> x.getName().equals(name)).findFirst().get().getId();
        repository.delete(idToDelete);
    }

    @Override
    public long getCount() {
        return repository.getAll().size();
    }

    @Override
    public double getTotalPrice() {
        return repository.getAll().stream().mapToDouble(Product::getPrice).reduce(Double::sum).orElse(0);
    }

    @Override
    public double getAveragePrice() {
        return getTotalPrice() / getCount();
    }
}
