package com.example.Shop.service;

import com.example.Shop.dto.UserDto;
import com.example.Shop.model.User;
import com.example.Shop.request.CreateUserRequest;
import com.example.Shop.request.UserUpdateRequest;

public interface UserService {

    User getUserById(Long userId);
    User createUser(CreateUserRequest request);
    User updateUser(UserUpdateRequest request, Long userId);
    void deleteUser(Long userId);

    UserDto convertUserToDto(User user);

    User getAuthenticatedUser();
}