package com.businessdev.application.service;

import com.businessdev.application.entity.Consumer;
import com.businessdev.application.entity.Product;
import com.businessdev.application.entity.Service;
import com.businessdev.application.entity.SurveyResponse;
import com.businessdev.application.entity.SurveyTemplate;
import com.businessdev.application.repository.ProductRepository;
import com.businessdev.application.repository.ServiceRepository;
import com.businessdev.application.repository.SurveyResponseRepository;
import com.businessdev.application.repository.SurveyTemplateRepository;
import com.businessdev.application.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@org.springframework.boot.autoconfigure.condition.ConditionalOnProperty(
    name = "spring.data.mongodb.uri",
    matchIfMissing = false
)
public class SurveyService {
    
    @Autowired
    private SurveyTemplateRepository surveyTemplateRepository;
    
    @Autowired
    private SurveyResponseRepository surveyResponseRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ServiceRepository serviceRepository;
    
    @Autowired
    private ConsumerService consumerService;
    
    // Get survey template by type
    public Optional<SurveyTemplate> getSurveyTemplate(String surveyType) {
        return surveyTemplateRepository.findBySurveyTypeAndIsActiveTrue(surveyType);
    }
    
    // Get all available survey types
    public List<String> getAvailableSurveyTypes() {
        return Arrays.asList("consumable", "non-consumable", "service");
    }
    
    // Get products by type
    public List<Product> getProductsByType(String productType) {
        if ("consumable".equals(productType)) {
            return productRepository.findAll().stream()
                .filter(p -> "consumable".equals(p.getType()))
                .collect(Collectors.toList());
        } else if ("non-consumable".equals(productType)) {
            return productRepository.findAll().stream()
                .filter(p -> "non-consumable".equals(p.getType()))
                .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
    
    // Get services
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }
    
    // Get product categories
    public List<String> getProductCategories(String productType) {
        return getProductsByType(productType).stream()
            .map(Product::getCategory)
            .distinct()
            .collect(Collectors.toList());
    }
    
    // Get products by category
    public List<Product> getProductsByCategory(String productType, String category) {
        return getProductsByType(productType).stream()
            .filter(p -> category.equals(p.getCategory()))
            .collect(Collectors.toList());
    }
    
    // Get service categories
    public List<String> getServiceCategories() {
        return serviceRepository.findAll().stream()
            .map(Service::getCategory)
            .distinct()
            .collect(Collectors.toList());
    }
    
    // Get services by category
    public List<Service> getServicesByCategory(String category) {
        return serviceRepository.findAll().stream()
            .filter(s -> category.equals(s.getCategory()))
            .collect(Collectors.toList());
    }
    
    // Check if consumer has already responded to a specific survey
    public boolean hasConsumerResponded(String email, String surveyType, String productId) {
        return surveyResponseRepository
            .findByConsumerEmailAndSurveyTypeAndProductId(email, surveyType, productId)
            .isPresent();
    }
    
    // Submit survey response
    public SurveyResponse submitSurveyResponse(String consumerEmail, String surveyType, 
                                             String productType, String productId, String productName,
                                             Map<String, Object> answers, Map<String, String> demographics) {
        
        // Get or create consumer
        Consumer consumer = consumerService.getConsumerByEmail(consumerEmail)
            .orElseGet(() -> consumerService.createConsumer(
                demographics.getOrDefault("name", "Anonymous"), 
                consumerEmail
            ));
        
        // Create survey response
        SurveyResponse response = new SurveyResponse(
            consumer.getId(), 
            consumerEmail, 
            surveyType, 
            productType, 
            productId, 
            productName
        );
        
        // Set answers
        response.setAnswers(answers);
        
        // Set demographics
        response.setAgeGroup(demographics.get("ageGroup"));
        response.setHouseholdSize(demographics.get("householdSize"));
        response.setIncomeRange(demographics.get("incomeRange"));
        response.setLocation(demographics.get("location"));
        response.setContinent(demographics.get("continent"));
        response.setCountry(demographics.get("country"));
        response.setProvince(demographics.get("province"));
        response.setCity(demographics.get("city"));
        response.setRuralUrban(demographics.get("ruralUrban"));
        
        response.setSubmittedAt(LocalDateTime.now());
        
        return surveyResponseRepository.save(response);
    }
    
    // Get all survey responses
    public List<SurveyResponse> getAllSurveyResponses() {
        return surveyResponseRepository.findAll();
    }
    
    // Get responses by consumer
    public List<SurveyResponse> getResponsesByConsumer(String email) {
        return surveyResponseRepository.findByConsumerEmail(email);
    }
    
}
