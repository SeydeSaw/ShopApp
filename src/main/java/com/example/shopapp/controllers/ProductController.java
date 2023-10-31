package com.example.shopapp.controllers;

import com.example.shopapp.domain.entity.Product;
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

    @GetMapping("/mapper/{id}")
    public ProductDto getMapperById(@PathVariable long id) {
        return productService.getMapperById(id);
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
