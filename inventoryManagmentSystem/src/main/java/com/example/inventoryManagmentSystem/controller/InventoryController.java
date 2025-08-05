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

import com.example.inventoryManagmentSystem.dto.InventoryRequest;
import com.example.inventoryManagmentSystem.entity.Inventory;
import com.example.inventoryManagmentSystem.service.InventoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "*")
public class InventoryController {
    
    @Autowired
    private InventoryService inventoryService;
    
    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventory() {
        return ResponseEntity.ok(inventoryService.getAllInventory());
    }
    
    @GetMapping("/{productId}")
    public ResponseEntity<Inventory> getInventoryByProductId(@PathVariable Long productId) {
        Inventory inventory = inventoryService.getInventoryByProductId(productId);
        if (inventory != null) {
            return ResponseEntity.ok(inventory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<Inventory> addOrUpdateInventory(@Valid @RequestBody InventoryRequest inventoryRequest) {
        Inventory inventory = new Inventory();
        inventory.setProductId(inventoryRequest.getProductId());
        inventory.setQuantity(inventoryRequest.getQuantity());
        
        Inventory savedInventory = inventoryService.addOrUpdateInventory(inventory);
        return ResponseEntity.ok(savedInventory);
    }
    
    @PutMapping("/{productId}/quantity")
    public ResponseEntity<?> updateInventoryQuantity(@PathVariable Long productId, @RequestParam Integer quantity) {
        boolean updated = inventoryService.updateInventoryQuantity(productId, quantity);
        if (updated) {
            return ResponseEntity.ok("Inventory quantity updated successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{productId}")
    public ResponseEntity<?> deleteInventory(@PathVariable Long productId) {
        boolean deleted = inventoryService.deleteInventory(productId);
        if (deleted) {
            return ResponseEntity.ok("Inventory deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}