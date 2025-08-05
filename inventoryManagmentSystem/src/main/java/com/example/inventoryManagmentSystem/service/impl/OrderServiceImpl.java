package com.example.inventoryManagmentSystem.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.inventoryManagmentSystem.entity.CartItem;
import com.example.inventoryManagmentSystem.entity.Order;
import com.example.inventoryManagmentSystem.entity.OrderItem;
import com.example.inventoryManagmentSystem.entity.Product;
import com.example.inventoryManagmentSystem.repository.CartItemRepository;
import com.example.inventoryManagmentSystem.repository.OrderItemRepository;
import com.example.inventoryManagmentSystem.repository.OrderRepository;
import com.example.inventoryManagmentSystem.repository.ProductRepository;
import com.example.inventoryManagmentSystem.service.CartService;
import com.example.inventoryManagmentSystem.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OrderItemRepository orderItemRepository;
    
    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private CartService cartService;
    
    @Override
    @Transactional
    public Order placeOrder(Long userId) {
        List<CartItem> cartItems = cartItemRepository.findByUserId(userId);
        
        if (cartItems.isEmpty()) {
            return null; // Cart is empty
        }
        
        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount = 0.0;
        
        for (CartItem cartItem : cartItems) {
            Product product = productRepository.findById(cartItem.getProductId()).orElse(null);
            if (product != null) {
                double itemTotal = product.getPrice() * cartItem.getQuantity();
                totalAmount += itemTotal;
                
                OrderItem orderItem = new OrderItem();
                orderItem.setProductId(cartItem.getProductId());
                orderItem.setQuantity(cartItem.getQuantity());
                orderItem.setPrice(product.getPrice());
                orderItems.add(orderItem);
            }
        }
        
        if (orderItems.isEmpty()) {
            return null;
        }
        
        // Create order
        Order order = new Order();
        order.setUserId(userId);
        order.setTotalAmount(totalAmount);
        order.setItems(orderItems);
        
        Order savedOrder = orderRepository.save(order);
        
        // Save order items
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrderId(savedOrder.getId());
            orderItemRepository.save(orderItem);
        }
        
        // Clear cart
        cartService.clearCart(userId);
        
        return savedOrder;
    }
    
    @Override
    public List<Order> getOrdersByUser(Long userId) {
        return orderRepository.findByUserId(userId);
    }
    
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    
    @Override
    public Order getOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }
}