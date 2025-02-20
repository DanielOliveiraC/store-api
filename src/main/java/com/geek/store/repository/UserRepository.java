package com.geek.store.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.geek.store.model.UserModel;

@Repository
public interface UserRepository extends MongoRepository <UserModel, String>{
    UserModel findByUsername(String username);   
}
