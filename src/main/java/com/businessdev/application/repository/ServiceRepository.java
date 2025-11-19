package com.businessdev.application.repository;

import com.businessdev.application.entity.Service;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceRepository extends MongoRepository<Service, String> {
    
    // Find services by category
    List<Service> findByCategory(String category);
    
    // Find services by subcategory
    List<Service> findBySubcategory(String subcategory);
    
    // Find services by category and subcategory
    List<Service> findByCategoryAndSubcategory(String category, String subcategory);
    
    // Find service by name
    Optional<Service> findByName(String name);
    
    // Find services by name containing (case insensitive)
    List<Service> findByNameContainingIgnoreCase(String name);
    
    // Find services by category and name containing
    List<Service> findByCategoryAndNameContainingIgnoreCase(String category, String name);
}
