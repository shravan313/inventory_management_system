package com.example.inventoryManagmentSystem.service;

import java.util.List;

import com.example.inventoryManagmentSystem.entity.Inventory;

public interface InventoryService {
    Inventory getInventoryByProductId(Long productId);
    Inventory addOrUpdateInventory(Inventory inventory);
    List<Inventory> getAllInventory();
    boolean updateInventoryQuantity(Long productId, Integer quantity);
    boolean deleteInventory(Long productId);
}