package com.example.shopapp.service.impl;


import com.example.shopapp.domain.entity.Product;
import com.example.shopapp.domain.entity.User;
import com.example.shopapp.dto.ProductDto;
import com.example.shopapp.mapper.ProductMapper;
import com.example.shopapp.repository.ProductRepository;
import com.example.shopapp.repository.UserRepository;
import com.example.shopapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

//    @Transactional
//    @Override
//    public Product createNewProduct(ProductDto productDto) {
//        Product product = new Product();
//        User seller = userRepository.findById(productDto.getSellerId()).orElseThrow(() -> new RuntimeException("Seller not find"));
//        product.setSeller(seller);
//        product.setCreatedAt(LocalDateTime.now());
//        product.setUpdatedAt(LocalDateTime.now());
//        product.setName(productDto.getName());
//        product.setPrice(BigDecimal.valueOf(productDto.getPrice()));
//        product.setDescription(productDto.getDescription());
//        productRepository.save(product);
//        return product;
//    }

    @Transactional
    @Override
    public ProductDto createNewMapperProduct(ProductDto productDto) {
        Product product = productMapper.mapToProduct(productDto);
        User seller = userRepository.findById(productDto.getSellerId()).orElseThrow(() -> new RuntimeException("Seller not find"));
        product.setSeller(seller);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        productRepository.save(product);
        return productMapper.mapToDto(product);
    }


//    @Transactional
//    @Override
//    public ProductDto getById(long id) {
//        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not find"));
//        ProductDto newProductDto = convertToProductDto(product);
//        return newProductDto;
//    }

    @Transactional
    @Override
    public ProductDto getMapperById(long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not find"));
        ProductDto newMapperProductDto = productMapper.mapToDto(product);
        return newMapperProductDto;
    }

    @Transactional
    @Override
    public ProductDto updateById(ProductDto productDto, long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not find"));
        updateProductNewData(productDto, product);
        product.setUpdatedAt(LocalDateTime.now());
        productRepository.save(product);
        ProductDto newProductDto = convertToProductDto(product);
        return newProductDto;
    }

    @Transactional
    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }


    @Transactional
    @Override
    public List<ProductDto> getAll() {
        List<Product> productList = productRepository.findAll();
//        List<ProductDto> productDtoList = productList
//                .stream()
//                .map(product -> convertToProductDto(product))
//                .collect(Collectors.toList());

        List<ProductDto> productDtoList = productMapper.mapToListProductDto(productList);
        return productDtoList;
    }

    private static ProductDto convertToProductDto(Product product) {
        ProductDto newProductDto = new ProductDto();
        newProductDto.setSellerId(product.getSeller().getId());
        newProductDto.setName(product.getName());
        newProductDto.setPrice(product.getPrice().doubleValue());
        newProductDto.setDescription(product.getDescription());
        return newProductDto;
    }

    private void updateProductNewData(ProductDto productDto, Product product) {
        if (productDto.getSellerId() != null && !productDto.getSellerId().equals(product.getSeller().getId())) {// полученный селлер id не налл И полученный селлер id  сравниваем с сохраненным id
            User seller = userRepository.findById(productDto.getSellerId()).orElseThrow(() -> new RuntimeException("Seller not find"));// достаем из базы продавца
            product.setSeller(seller);
        }
        if (productDto.getName() != null) {
            product.setName(productDto.getName());
        }
        if (productDto.getPrice() != null) {
            product.setPrice(BigDecimal.valueOf(productDto.getPrice()));
        }
        if (productDto.getDescription() != null) {
            product.setDescription(product.getDescription());
        }
    }
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