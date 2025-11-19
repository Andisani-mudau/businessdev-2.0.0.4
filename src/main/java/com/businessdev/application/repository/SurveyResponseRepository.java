package com.businessdev.application.repository;

import com.businessdev.application.entity.SurveyResponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SurveyResponseRepository extends MongoRepository<SurveyResponse, String> {
    
    // Find responses by consumer email
    List<SurveyResponse> findByConsumerEmail(String email);
    
    // Find responses by survey type
    List<SurveyResponse> findBySurveyType(String surveyType);
    
    // Find responses by product type
    List<SurveyResponse> findByProductType(String productType);
    
    // Find responses by specific product/service
    List<SurveyResponse> findByProductId(String productId);
    
    // Find responses by consumer email and survey type
    List<SurveyResponse> findByConsumerEmailAndSurveyType(String email, String surveyType);
    
    // Check if consumer has already responded to a specific survey
    @Query("{'consumerEmail': ?0, 'surveyType': ?1, 'productId': ?2}")
    Optional<SurveyResponse> findByConsumerEmailAndSurveyTypeAndProductId(String email, String surveyType, String productId);
    
    // Find responses by location
    List<SurveyResponse> findByCountry(String country);
    List<SurveyResponse> findByProvince(String province);
    List<SurveyResponse> findByCity(String city);
    
    // Find responses by demographics
    List<SurveyResponse> findByAgeGroup(String ageGroup);
    List<SurveyResponse> findByHouseholdSize(String householdSize);
    List<SurveyResponse> findByIncomeRange(String incomeRange);
    
}
