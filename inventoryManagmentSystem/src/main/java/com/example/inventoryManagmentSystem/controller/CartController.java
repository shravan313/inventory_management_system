package com.example.inventoryManagmentSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.inventoryManagmentSystem.dto.CartItemRequest;
import com.example.inventoryManagmentSystem.entity.CartItem;
import com.example.inventoryManagmentSystem.service.CartService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user/cart")
@CrossOrigin(origins = "*")
public class CartController {
    
    @Autowired
    private CartService cartService;
    
    @PostMapping
    public ResponseEntity<CartItem> addToCart(@RequestParam Long userId, @Valid @RequestBody CartItemRequest cartItemRequest) {
        CartItem cartItem = cartService.addToCart(userId, cartItemRequest);
        return ResponseEntity.ok(cartItem);
    }
    
    @GetMapping
    public ResponseEntity<List<CartItem>> getCartItems(@RequestParam Long userId) {
        List<CartItem> cartItems = cartService.getCartItems(userId);
        return ResponseEntity.ok(cartItems);
    }
    
    @DeleteMapping("/{productId}")
    public ResponseEntity<?> removeFromCart(@RequestParam Long userId, @PathVariable Long productId) {
        boolean removed = cartService.removeFromCart(userId, productId);
        if (removed) {
            return ResponseEntity.ok("Item removed from cart");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping
    public ResponseEntity<?> clearCart(@RequestParam Long userId) {
        boolean cleared = cartService.clearCart(userId);
        if (cleared) {
            return ResponseEntity.ok("Cart cleared successfully");
        } else {
            return ResponseEntity.ok("Cart was already empty");
        }
    }
    
    @PutMapping("/{productId}")
    public ResponseEntity<?> updateCartItemQuantity(@RequestParam Long userId, @PathVariable Long productId, @RequestParam Integer quantity) {
        boolean updated = cartService.updateCartItemQuantity(userId, productId, quantity);
        if (updated) {
            return ResponseEntity.ok("Quantity updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}