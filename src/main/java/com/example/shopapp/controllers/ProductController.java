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

//    @PostMapping("/create")
//    public Product createNewProduct(@RequestBody ProductDto productDto) {
//        return productService.createNewProduct(productDto);
//    }

    @PostMapping("/create/mapper")
    public ProductDto createNewMapperProduct(@RequestBody ProductDto productDto) {
        return productService.createNewMapperProduct(productDto);
    }

    @PutMapping("/update/{id}")
    public ProductDto updateById(@RequestBody ProductDto productDto, @PathVariable long id) {
        return productService.updateById(productDto, id);
    }

//    @GetMapping("/{id}")
//    public ProductDto getById(@PathVariable long id) {
//        return productService.getById(id);
//    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable long id) {
        return productService.getById(id);
    }

    @GetMapping("/all")
    public List<ProductDto> getAll() {
        return productService.getAll();
    }



    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable long id) {
        productService.deleteProductById(id);
    }

}

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
