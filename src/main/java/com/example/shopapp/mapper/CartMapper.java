package com.example.shopapp.mapper;

import com.example.shopapp.domain.entity.Cart;
import com.example.shopapp.domain.entity.User;
import com.example.shopapp.dto.CartDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CartMapper {
    @Mapping(source = "client", target = "clientFullName", qualifiedByName = "getFullName")
    @Mapping(source = "client.id", target = "clientId")
    CartDto mapToDto(Cart cart);

//    Cart mapToCart(CartDto cartDto);

    @Named("getFullName")
    default String getFullName(User user) {
        return user.getFirstName() + " " + user.getLastName();
    }
}
