package com.example.shopapp.mapper;

import com.example.shopapp.domain.entity.OrderDetail;
import com.example.shopapp.dto.OrderDetailDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    @Mapping(source = "cart.id", target = "cartId")
    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    OrderDetailDto mapToDto(OrderDetail orderDetail);

    @Named("mapToListOrderDetailDto")
    List<OrderDetailDto> mapToListOrderDetailDto(List<OrderDetail> orderDetailList);
}