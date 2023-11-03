package com.example.shopapp.mapper;

import com.example.shopapp.domain.entity.User;
import com.example.shopapp.dto.UserDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto mapToDto(User user);
    User mapToEntity(UserDto userDto);
    List<UserDto> mapToListUserDto(List<User> userList);
}