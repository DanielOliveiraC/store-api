package com.geek.store.service;

import com.geek.store.model.ProductModel;
import com.geek.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    public ProductModel findById(String id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    public List<ProductModel> findByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public ProductModel updateProduct(String id, ProductModel product) {
        ProductModel existingProduct = findById(id);
        
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setImage(product.getImage());

        return productRepository.save(existingProduct);
    }
}
