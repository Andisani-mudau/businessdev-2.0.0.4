package com.businessdev.application.service;

import com.businessdev.application.entity.Consumer;
import com.businessdev.application.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumerService {
    
    @Autowired
    private ConsumerRepository consumerRepository;
    
    // Create a new consumer
    public Consumer createConsumer(String name, String email) {
        if (consumerRepository.existsByEmail(email)) {
            throw new RuntimeException("Consumer with email " + email + " already exists");
        }
        
        Consumer consumer = new Consumer(name, email);
        return consumerRepository.save(consumer);
    }
    
    // Get all consumers
    public List<Consumer> getAllConsumers() {
        return consumerRepository.findAll();
    }
    
    // Get consumer by ID
    public Optional<Consumer> getConsumerById(String id) {
        return consumerRepository.findById(id);
    }
    
    // Get consumer by email
    public Optional<Consumer> getConsumerByEmail(String email) {
        return consumerRepository.findByEmail(email);
    }
    
    // Update consumer
    public Consumer updateConsumer(String id, String name, String email) {
        Optional<Consumer> consumerOpt = consumerRepository.findById(id);
        if (consumerOpt.isPresent()) {
            Consumer consumer = consumerOpt.get();
            consumer.setName(name);
            consumer.setEmail(email);
            return consumerRepository.save(consumer);
        }
        throw new RuntimeException("Consumer with ID " + id + " not found");
    }
    
    // Delete consumer
    public void deleteConsumer(String id) {
        if (consumerRepository.existsById(id)) {
            consumerRepository.deleteById(id);
        } else {
            throw new RuntimeException("Consumer with ID " + id + " not found");
        }
    }
    
    // Search consumers by name
    public List<Consumer> searchConsumersByName(String name) {
        return consumerRepository.findByNameContainingIgnoreCase(name);
    }
    
    // Check if consumer exists
    public boolean consumerExists(String email) {
        return consumerRepository.existsByEmail(email);
    }
}
