package com.businessdev.application.repository;

import com.businessdev.application.entity.SurveyTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SurveyTemplateRepository extends MongoRepository<SurveyTemplate, String> {
    
    // Find template by survey type
    Optional<SurveyTemplate> findBySurveyType(String surveyType);
    
    // Find all active templates
    List<SurveyTemplate> findByIsActiveTrue();
    
    // Find template by survey type and active status
    Optional<SurveyTemplate> findBySurveyTypeAndIsActiveTrue(String surveyType);
    
    // Find all templates by survey type
    List<SurveyTemplate> findAllBySurveyType(String surveyType);
}
