package com.example.inventoryManagmentSystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.inventoryManagmentSystem.entity.Inventory;
import com.example.inventoryManagmentSystem.repository.InventoryRepository;
import com.example.inventoryManagmentSystem.service.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService {
    
    @Autowired
    private InventoryRepository inventoryRepository;
    
    @Override
    public Inventory getInventoryByProductId(Long productId) {
        return inventoryRepository.findByProductId(productId).orElse(null);
    }
    
    @Override
    public Inventory addOrUpdateInventory(Inventory inventory) {
        Inventory existing = getInventoryByProductId(inventory.getProductId());
        if (existing == null) {
            // Create new inventory entry
            return inventoryRepository.save(inventory);
        } else {
            // Update existing inventory
            existing.setQuantity(inventory.getQuantity());
            return inventoryRepository.save(existing);
        }
    }
    
    @Override
    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }
    
    @Override
    public boolean updateInventoryQuantity(Long productId, Integer quantity) {
        Inventory inventory = getInventoryByProductId(productId);
        if (inventory != null) {
            inventory.setQuantity(quantity);
            inventoryRepository.save(inventory);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean deleteInventory(Long productId) {
        Inventory inventory = getInventoryByProductId(productId);
        if (inventory != null) {
            inventoryRepository.delete(inventory);
            return true;
        }
        return false;
    }
}