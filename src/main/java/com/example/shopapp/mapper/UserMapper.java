package com.example.shopapp.mapper;

import com.example.shopapp.domain.entity.User;
import com.example.shopapp.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "password", expression = "java(\"******\")")
    UserDto mapToDto(User user);

    User mapToEntity(UserDto userDto);

    List<UserDto> mapToListUserDto(List<User> userList);
}