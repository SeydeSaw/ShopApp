package com.example.shopapp.service.impl;

import com.example.shopapp.entity.User;
import com.example.shopapp.entity.enums.Role;
import com.example.shopapp.dto.UserDto;
import com.example.shopapp.mapper.UserMapper;
import com.example.shopapp.provider.UserProvider;
import com.example.shopapp.repository.UserRepository;
import com.example.shopapp.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserProvider userProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = userMapper.mapToEntity(userDto);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        user.setRole(Role.USER);
        userRepository.save(user);
        return userMapper.mapToDto(user);
    }

    @Transactional
    @Override
    public UserDto getById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not find"));
        return userMapper.mapToDto(user);
    }

    @Transactional
    @Override
    public List<UserDto> getAll() {
        List<User> userList = userRepository.findAll();
        return userMapper.mapToListUserDto(userList);
    }

    @Transactional
    @Override
    public UserDto updateById(UserDto userDto, Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not find"));
        updateUserNewData(userDto, user);
        user.setUpdatedAt(LocalDateTime.now());
        userRepository.save(user);
        return convertToUserDto(user);
    }

    @Transactional
    @Override
    public void deleteCurrentUser() {
        userRepository.delete(userProvider.getCurrentUser());
    }

    @Transactional
    @Override
    public User findByUsernameAndPassword(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        throw new EntityNotFoundException("Username or password is not correct");
    }

    @Transactional
    @Override
    public UserDto getCurrentUser() {
        User user = userProvider.getCurrentUser();
        return userMapper.mapToDto(user);
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