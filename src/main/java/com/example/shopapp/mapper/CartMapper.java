package com.example.shopapp.mapper;

import com.example.shopapp.entity.Cart;
import com.example.shopapp.entity.OrderDetail;
import com.example.shopapp.entity.User;
import com.example.shopapp.dto.CartDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.List;

@Mapper(componentModel = "spring", uses = OrderDetailMapper.class)
public interface CartMapper {
    @Mapping(source = "client", target = "clientFullName", qualifiedByName = "getFullName")
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "orderDetails", target = "orderDetailDtoList", qualifiedByName = "mapToListOrderDetailDto")
    @Mapping(source = "orderDetails", target = "totalPrice", qualifiedByName = "calculateCurrentTotalPrice")
    CartDto mapToDto(Cart cart);

    @Named("getFullName")
    default String getFullName(User user) {
        return user.getFirstName() + " " + user.getLastName();
    }

    @Named("calculateCurrentTotalPrice")
    default Double calculateCurrentTotalPrice(List<OrderDetail> orderDetailList) {
        return orderDetailList.stream()
                .map(orderDetail -> orderDetail.getProduct().getPrice()
                        .multiply(BigDecimal.valueOf(orderDetail.getQuantity())))
                .reduce(BigDecimal.ZERO,BigDecimal::add).doubleValue();
    }

    @Named("mapToListDto")
    List<CartDto> mapToListDto(List<Cart> allCarts);
}