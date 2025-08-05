package com.example.inventoryManagmentSystem.service;

import java.util.List;

import com.example.inventoryManagmentSystem.entity.Order;

public interface OrderService {
    Order placeOrder(Long userId);
    List<Order> getOrdersByUser(Long userId);
    List<Order> getAllOrders();
    Order getOrderById(Long orderId);
}