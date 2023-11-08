package com.example.shopapp.service.impl;

import com.example.shopapp.domain.entity.Product;
import com.example.shopapp.domain.entity.User;
import com.example.shopapp.dto.ProductDto;
import com.example.shopapp.mapper.ProductMapper;
import com.example.shopapp.repository.ProductRepository;
import com.example.shopapp.repository.UserRepository;
import com.example.shopapp.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Transactional
    @Override
    public ProductDto createNewProduct(ProductDto productDto) {
        Product product = productMapper.mapToProduct(productDto);
        User seller = userRepository.findById(productDto.getSellerId())
                .orElseThrow(() -> new EntityNotFoundException("Seller not find"));
        product.setSeller(seller);
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        productRepository.save(product);
        return productMapper.mapToDto(product);
    }

    @Transactional
    @Override
    public ProductDto getById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not find"));
        return productMapper.mapToDto(product);
    }

    @Transactional
    @Override
    public ProductDto updateById(ProductDto productDto, Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not find"));
        updateProductNewData(productDto, product);
        product.setUpdatedAt(LocalDateTime.now());
        productRepository.save(product);
        return convertToProductDto(product);
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
        return productMapper.mapToListProductDto(productList);
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
        if (productDto.getSellerId() != null && !productDto.getSellerId().equals(product.getSeller().getId())) {
            User seller = userRepository.findById(productDto.getSellerId())
                    .orElseThrow(() -> new EntityNotFoundException("Seller not find"));
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
}