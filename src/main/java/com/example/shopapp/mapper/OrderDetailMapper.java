package com.example.shopapp.mapper;

import com.example.shopapp.domain.entity.Order;
import com.example.shopapp.domain.entity.OrderDetail;
import com.example.shopapp.dto.OrderDetailDto;
import com.example.shopapp.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    @Mapping(source = "cart.id", target = "cartId")
    @Mapping(source = "product.id", target = "productId")
    OrderDetailDto mapToDto(OrderDetail orderDetail);

    List<OrderDetailDto> mapToListOrderDetailDto(List<OrderDetail> orderDetailList);

}