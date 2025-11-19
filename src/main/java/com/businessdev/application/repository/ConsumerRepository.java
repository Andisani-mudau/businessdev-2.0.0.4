package com.businessdev.application.repository;

import com.businessdev.application.entity.Consumer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsumerRepository extends MongoRepository<Consumer, String> {
    
    // Find consumer by email
    Optional<Consumer> findByEmail(String email);
    
    // Find consumers by name (case insensitive)
    List<Consumer> findByNameIgnoreCase(String name);
    
    // Check if consumer exists by email
    boolean existsByEmail(String email);
    
    // Find consumers by name containing (case insensitive)
    List<Consumer> findByNameContainingIgnoreCase(String name);
}
