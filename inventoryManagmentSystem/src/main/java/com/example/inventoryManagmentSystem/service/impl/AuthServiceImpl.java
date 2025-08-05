package com.example.inventoryManagmentSystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.inventoryManagmentSystem.dto.AuthResponse;
import com.example.inventoryManagmentSystem.dto.LoginRequest;
import com.example.inventoryManagmentSystem.dto.RegisterRequest;
import com.example.inventoryManagmentSystem.entity.User;
import com.example.inventoryManagmentSystem.repository.UserRepository;
import com.example.inventoryManagmentSystem.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElse(null);
        
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            return new AuthResponse(user.getUsername(), user.getRole().name(), user.getId());
        }
        
        return new AuthResponse("Invalid credentials");
    }
    
    @Override
    public AuthResponse register(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return new AuthResponse("Username already exists");
        }
        
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword()); // Store password as plain text for simplicity
        user.setRole(User.Role.valueOf(registerRequest.getRole()));
        
        User savedUser = userRepository.save(user);
        return new AuthResponse(savedUser.getUsername(), savedUser.getRole().name(), savedUser.getId());
    }
    
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    @Override
    public boolean deleteUser(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean updateUser(User user) {
        if (userRepository.existsById(user.getId())) {
            userRepository.save(user);
            return true;
        }
        return false;
    }
    
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    
    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}