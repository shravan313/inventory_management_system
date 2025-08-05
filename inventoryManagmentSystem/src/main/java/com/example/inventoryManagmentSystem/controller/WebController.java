package com.example.inventoryManagmentSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
    
    @GetMapping("/")
    public String home() {
        return "index";
    }
    
    @GetMapping("/auth")
    public String auth() {
        return "auth";
    }
    
    @GetMapping("/products")
    public String products() {
        return "products";
    }
    
    @GetMapping("/inventory")
    public String inventory() {
        return "inventory";
    }
    
    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }
    
    @GetMapping("/orders")
    public String orders() {
        return "orders";
    }
}