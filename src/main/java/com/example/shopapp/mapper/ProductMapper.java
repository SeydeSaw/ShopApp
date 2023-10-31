package com.example.shopapp.mapper;

import com.example.shopapp.domain.entity.Product;
import com.example.shopapp.domain.entity.User;
import com.example.shopapp.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "seller", target = "sellerFullName", qualifiedByName = "getFullName")
    @Mapping(source = "seller.id", target = "sellerId")
    ProductDto mapToDto(Product product);


    List<ProductDto> mapToListProductDto(List<Product> productList);

    @Mapping(source = "price", target = "price", qualifiedByName = "doubleToBigDecimal")
    Product mapToProduct(ProductDto productDto);
    @Named("doubleToBigDecimal")
    default BigDecimal doubleToBigDecimal(Double value) {
        return BigDecimal.valueOf(value);
    };

    @Named("getFullName")
    default String getFullName(User user) {
        return user.getFirstName() + " " + user.getLastName();
    }
}