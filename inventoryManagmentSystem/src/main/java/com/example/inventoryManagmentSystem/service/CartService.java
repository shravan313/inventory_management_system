package com.example.inventoryManagmentSystem.service;

import java.util.List;

import com.example.inventoryManagmentSystem.dto.CartItemRequest;
import com.example.inventoryManagmentSystem.entity.CartItem;

public interface CartService {
    CartItem addToCart(Long userId, CartItemRequest cartItemRequest);
    List<CartItem> getCartItems(Long userId);
    boolean removeFromCart(Long userId, Long productId);
    boolean clearCart(Long userId);
    boolean updateCartItemQuantity(Long userId, Long productId, Integer quantity);
}