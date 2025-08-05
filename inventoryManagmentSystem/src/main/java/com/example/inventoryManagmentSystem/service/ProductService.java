package com.example.inventoryManagmentSystem.service;

import java.util.List;

import com.example.inventoryManagmentSystem.dto.ProductRequest;
import com.example.inventoryManagmentSystem.entity.Product;

public interface ProductService {
    Product addProduct(ProductRequest productRequest);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    boolean deleteProduct(Long id);
    Product updateProduct(Long id, ProductRequest productRequest);
}