package com.example.shopapp.mapper;

import com.example.shopapp.entity.Order;
import com.example.shopapp.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mapping(source = "cart.id", target = "cartId")
    OrderDto mapToDto(Order order);

    @Mapping(source = "totalPrice", target = "totalPrice", qualifiedByName = "doubleToBigDecimal")
    Order mapToOrder(OrderDto orderDto);

    @Named("doubleToBigDecimal")
    default BigDecimal doubleToBigDecimal(Double value) {
        return BigDecimal.valueOf(value);
    };
}