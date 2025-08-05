package com.example.inventoryManagmentSystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.inventoryManagmentSystem.dto.CartItemRequest;
import com.example.inventoryManagmentSystem.entity.CartItem;
import com.example.inventoryManagmentSystem.repository.CartItemRepository;
import com.example.inventoryManagmentSystem.service.CartService;

@Service
public class CartServiceImpl implements CartService {
    
    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Override
    public CartItem addToCart(Long userId, CartItemRequest cartItemRequest) {
        // Check if item already exists in cart
        Optional<CartItem> existingItem = cartItemRepository.findByUserId(userId)
                .stream()
                .filter(item -> item.getProductId().equals(cartItemRequest.getProductId()))
                .findFirst();
        
        if (existingItem.isPresent()) {
            // Update quantity
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + cartItemRequest.getQuantity());
            return cartItemRepository.save(item);
        } else {
            // Add new item
            CartItem newItem = new CartItem();
            newItem.setUserId(userId);
            newItem.setProductId(cartItemRequest.getProductId());
            newItem.setQuantity(cartItemRequest.getQuantity());
            return cartItemRepository.save(newItem);
        }
    }
    
    @Override
    public List<CartItem> getCartItems(Long userId) {
        return cartItemRepository.findByUserId(userId);
    }
    
    @Override
    public boolean removeFromCart(Long userId, Long productId) {
        Optional<CartItem> item = cartItemRepository.findByUserId(userId)
                .stream()
                .filter(cartItem -> cartItem.getProductId().equals(productId))
                .findFirst();
        
        if (item.isPresent()) {
            cartItemRepository.delete(item.get());
            return true;
        }
        return false;
    }
    
    @Override
    public boolean clearCart(Long userId) {
        List<CartItem> items = cartItemRepository.findByUserId(userId);
        if (!items.isEmpty()) {
            cartItemRepository.deleteAll(items);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean updateCartItemQuantity(Long userId, Long productId, Integer quantity) {
        Optional<CartItem> item = cartItemRepository.findByUserId(userId)
                .stream()
                .filter(cartItem -> cartItem.getProductId().equals(productId))
                .findFirst();
        
        if (item.isPresent()) {
            CartItem cartItem = item.get();
            cartItem.setQuantity(quantity);
            cartItemRepository.save(cartItem);
            return true;
        }
        return false;
    }
}