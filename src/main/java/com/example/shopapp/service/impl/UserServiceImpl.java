package com.example.shopapp.service.impl;

import com.example.shopapp.domain.entity.User;
import com.example.shopapp.domain.enums.Role;
import com.example.shopapp.dto.UserDto;
import com.example.shopapp.mapper.UserMapper;
import com.example.shopapp.repository.UserRepository;
import com.example.shopapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.mapToEntity(userDto);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setRole(Role.USER);
        userRepository.save(user);
        return userMapper.mapToDto(user);
    }


    @Transactional
    @Override
    public UserDto getById(long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not find"));
        UserDto newMapperUserDto = userMapper.mapToDto(user);
        return newMapperUserDto;
    }

    @Transactional
    @Override
    public List<UserDto> getAll() {
        List<User> userList = userRepository.findAll();
        return userMapper.mapToListUserDto(userList);
    }

    @Transactional
    @Override
    public UserDto updateById(UserDto userDto, long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not find"));
        updateUserNewData(userDto, user);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        return convertToUserDto(user);
    }

    @Transactional
    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    private UserDto convertToUserDto(User user) {
        UserDto newUserDto = new UserDto();
        newUserDto.setUsername(user.getUsername());
        newUserDto.setFirstName(user.getFirstName());
        newUserDto.setLastName(user.getLastName());
        newUserDto.setEmail(user.getEmail());
        newUserDto.setPassword(user.getPassword());
        return newUserDto;
    }

    private void updateUserNewData(UserDto userDto, User user) {
        if (userDto.getUsername() != null) {
            user.setUsername(userDto.getUsername());
        }
        if (userDto.getFirstName() != null) {
            user.setFirstName(userDto.getFirstName());
        }
        if (userDto.getLastName() != null) {
            user.setLastName(userDto.getLastName());
        }
        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }
        if (userDto.getPassword() != null) {
            user.setPassword(userDto.getPassword());
        }
    }
}