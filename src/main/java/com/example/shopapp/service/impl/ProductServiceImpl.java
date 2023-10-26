package com.example.shopapp.service.impl;


import com.example.shopapp.domain.entity.Product;
import com.example.shopapp.domain.entity.User;
import com.example.shopapp.dto.ProductDto;
import com.example.shopapp.repository.ProductRepository;
import com.example.shopapp.repository.UserRepository;
import com.example.shopapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Transactional
    @Override
    public Product createNewProduct(ProductDto productDto) {
        Product product = new Product();
        User seller = userRepository.findById(productDto.getSellerId()).orElseThrow(()->new RuntimeException("Seller not find"));
        product.setSeller(seller);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        productRepository.save(product);
        return product;
    }


//    @Autowired
//    private ProductRepository repository;
//    @Override
//    public List<Product> getAll() {
//        return repository.getAll();
//    }
//
//    @Override
//    public Product getById(long id) {
//        return repository.getById(id);
//    }
//
//    @Override
//    public void add(Product product) {
//        repository.add(product.getName(), product.getPrice());
//    }
//
//    @Override
//    public void deleteById(long id) {
//        repository.delete(id);
//    }
//
//    @Override
//    public void deleteByName(String name) {
//        long idToDelete = repository.getAll().stream().filter(x -> x.getName().equals(name)).findFirst().get().getId();
//        repository.delete(idToDelete);
//    }
//
//    @Override
//    public long getCount() {
//        return repository.getAll().size();
//    }
//
//    @Override
//    public double getTotalPrice() {
//        return repository.getAll().stream().mapToDouble(Product::getPrice).reduce(Double::sum).orElse(0);
//    }
//
//    @Override
//    public double getAveragePrice() {
//        return getTotalPrice() / getCount();
//    }
}
