package com.example.shopapp.controllers;

import com.example.shopapp.dto.ProductDto;
import com.example.shopapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/product")
public class ProductController {

    private final ProductService productService;
    @PostMapping("/create")
    public ProductDto createNewProduct(@RequestBody ProductDto productDto) {
        return productService.createNewProduct(productDto);
    }
    @PutMapping("/update/{id}")
    public ProductDto updateById(@RequestBody ProductDto productDto, @PathVariable Long id) {
        return productService.updateById(productDto, id);
    }
    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable Long id) {
        return productService.getById(id);
    }
    @GetMapping("/all")
    public List<ProductDto> getAll() {
        return productService.getAll();
    }
    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }
}