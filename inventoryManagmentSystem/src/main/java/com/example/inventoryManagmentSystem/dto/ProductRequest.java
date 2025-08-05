package com.example.inventoryManagmentSystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProductRequest {
    
    @NotBlank(message = "Product name is required")
    private String name;
    
    @NotNull(message = "Product price is required")
    @Positive(message = "Price must be positive")
    private Double price;
    
    // Constructors
    public ProductRequest() {}
    
    public ProductRequest(String name, Double price) {
        this.name = name;
        this.price = price;
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
}