package com.geek.store.repository;

import com.geek.store.model.ProductModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ProductRepository extends MongoRepository<ProductModel, String> {
    List<ProductModel> findByName(String name);
    List<ProductModel> findByCategory(String category);
    List<ProductModel> findByPrice(String price);

    @Override
    default void deleteById(@SuppressWarnings("null") String id) {
        deleteById(id);
    }
}
