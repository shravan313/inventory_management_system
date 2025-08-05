package com.example.inventoryManagmentSystem.dto;

public class AuthResponse {
    private String username;
    private String role;
    private Long userId;
    private String message;
    
    // Constructors
    public AuthResponse() {}
    
    public AuthResponse(String username, String role, Long userId) {
        this.username = username;
        this.role = role;
        this.userId = userId;
    }
    
    public AuthResponse(String message) {
        this.message = message;
    }
    
    // Getters and Setters
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public Long getUserId() {
        return userId;
    }
    
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}