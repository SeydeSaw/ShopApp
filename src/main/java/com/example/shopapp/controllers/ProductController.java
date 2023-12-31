package com.example.shopapp.controllers;

import com.example.shopapp.dto.ProductDto;
import com.example.shopapp.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Operation(summary = "for all users")
    public ProductDto getById(@PathVariable Long id) {
        return productService.getById(id);
    }
    @GetMapping("/all")
    @Operation(summary = "for all users")
    public List<ProductDto> getAll() {
        return productService.getAll();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "for ADMIN")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }
}