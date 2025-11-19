package com.businessdev.application.repository;

import com.businessdev.application.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    
    // Find products by type
    List<Product> findByType(String type);
    
    // Find products by category
    List<Product> findByCategory(String category);
    
    // Find products by subcategory
    List<Product> findBySubcategory(String subcategory);
    
    // Find products by type and category
    List<Product> findByTypeAndCategory(String type, String category);
    
    // Find product by name
    Optional<Product> findByName(String name);
    
    // Find products by name containing (case insensitive)
    List<Product> findByNameContainingIgnoreCase(String name);
    
    // Find products by type and name containing
    List<Product> findByTypeAndNameContainingIgnoreCase(String type, String name);
}
