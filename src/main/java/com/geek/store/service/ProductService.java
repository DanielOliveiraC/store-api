package com.geek.store.service;

import com.geek.store.model.ProductModel;
import com.geek.store.repository.ProductRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public List<ProductModel> getAllProducts() {
        return productRepository.findAll();
    }

    public ProductModel createProduct(ProductModel product) {
        return productRepository.save(product);
    }
    
    public List<ProductModel> findByName(String name) {
        return productRepository.findByName(name);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}       
