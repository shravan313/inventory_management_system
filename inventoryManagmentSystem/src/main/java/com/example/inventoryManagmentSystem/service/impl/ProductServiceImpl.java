package com.example.inventoryManagmentSystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.inventoryManagmentSystem.dto.ProductRequest;
import com.example.inventoryManagmentSystem.entity.Product;
import com.example.inventoryManagmentSystem.repository.ProductRepository;
import com.example.inventoryManagmentSystem.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public Product addProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());
        return productRepository.save(product);
    }
    
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
    
    @Override
    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    @Override
    public Product updateProduct(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            product.setName(productRequest.getName());
            product.setPrice(productRequest.getPrice());
            return productRepository.save(product);
        }
        return null;
    }
}