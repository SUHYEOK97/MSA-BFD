package com.book.userservice.service;

import com.book.userservice.dto.UserDto;
import com.book.userservice.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface UserService extends UserDetailsService {
    void createUser(UserDto userDto);
    UserDto getUserByUserId(String userId);
    Iterable<UserEntity> getUserByAll();
    UserDto getUserDetailByUserEmail(String email);
}
