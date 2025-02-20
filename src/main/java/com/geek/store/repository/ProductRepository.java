package com.geek.store.repository;

import com.geek.store.model.ProductModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<ProductModel, String> {
    List<ProductModel> findByCategory(String category);
    List<ProductModel> findByNameContainingIgnoreCase(String name);
    List<ProductModel> findByPriceLessThanEqual(String maxPrice);
    List<ProductModel> findByCategoryAndPriceLessThanEqual(String category, String maxPrice);
}
