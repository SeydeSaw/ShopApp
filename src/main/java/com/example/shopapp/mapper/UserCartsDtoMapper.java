package com.example.shopapp.mapper;

import com.example.shopapp.dto.UserCartsDto;
import com.example.shopapp.entity.Cart;
import com.example.shopapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = CartMapper.class)
public interface UserCartsDtoMapper {
    default List<UserCartsDto> mapToListDto(Map<User, List<Cart>> userCartsMap) {
        return userCartsMap.entrySet().stream().map(this::mapEntryToDto).collect(Collectors.toList());
    }

    ;

    @Mapping(source = "key", target = "clientFullName", qualifiedByName = "getFullName")
    @Mapping(source = "value", target = "cartDtoList", qualifiedByName = "mapToListDto")
    @Mapping(source = "value", target = "totalPrice", qualifiedByName = "calculateCartsTotalPrice")
    UserCartsDto mapEntryToDto(Map.Entry<User, List<Cart>> entry);

    @Named("calculateCartsTotalPrice")
    default Double calculateCartsTotalPrice(List<Cart> cartList) {
        return cartList.stream()
                .flatMap(cart -> cart.getOrderDetails().stream())
                .map(orderDetail -> orderDetail.getProduct().getPrice()
                        .multiply(BigDecimal.valueOf(orderDetail.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .doubleValue();
    }
}