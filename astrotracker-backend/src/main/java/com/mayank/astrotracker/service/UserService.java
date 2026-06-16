package com.mayank.astrotracker.service;

import com.mayank.astrotracker.dto.CreateUserRequest;
import com.mayank.astrotracker.dto.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(CreateUserRequest request);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(Long id);

    void deleteUser(Long id);
}
