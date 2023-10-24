package com.example.shopapp.controllers;

import com.example.shopapp.domain.entity.Product;
import com.example.shopapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

//    @Autowired
//    private ProductService service;
//
////    @GetMapping
////    public List<Product> getAll() {
////        List<Product> products = service.getAll();
////        if (products.size() == 7) {
////            throw new ThirdTestException("Список продуктов пуст!");
////        }
////        return products;
////    }
//
//    @GetMapping("/{id}")
//    public Product getById(@PathVariable long id) {
//        return service.getById(id);
//    }
//
////    @PostMapping
////    public Product add(@Valid @RequestBody CommonProduct product) { // делаем валидацию при создании нового продукта
////        try {
////            service.add(product);
////            return product;
////        } catch (Exception e) {
////            throw new EntityValidationException(e.getMessage());
////        }
////    }
//
//    @GetMapping("/delete/{id}")
//    public void delete(@PathVariable long id) {
//        service.deleteById(id);
//    }
//
//    @GetMapping("/deletename/{name}")
//    public void delete(@PathVariable String name) {
//        service.deleteByName(name);
//    }
//
//    @GetMapping("/count")
//    public long getCount() {
//        return service.getCount();
//    }
//
//    @GetMapping("/total")
//    public double getTotalPrice() {
//        return service.getTotalPrice();
//    }
//
//    @GetMapping("/average")
//    public double getAverage() {
//        return service.getAveragePrice();
//    }
}