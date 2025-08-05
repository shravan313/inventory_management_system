package com.example.inventoryManagmentSystem.service;

import java.util.List;

import com.example.inventoryManagmentSystem.dto.AuthResponse;
import com.example.inventoryManagmentSystem.dto.LoginRequest;
import com.example.inventoryManagmentSystem.dto.RegisterRequest;
import com.example.inventoryManagmentSystem.entity.User;

public interface AuthService {
    AuthResponse login(LoginRequest loginRequest);
    AuthResponse register(RegisterRequest registerRequest);
    List<User> getAllUsers();
    boolean deleteUser(Long userId);
    boolean updateUser(User user);
    User getUserById(Long id);
    User getUserByUsername(String username);
}