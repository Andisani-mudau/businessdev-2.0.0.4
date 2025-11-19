package com.businessdev.application.repository;

import com.businessdev.application.entity.SurveyQuestion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SurveyQuestionRepository extends MongoRepository<SurveyQuestion, String> {
    
    // Find questions by target type
    List<SurveyQuestion> findByTargetType(String targetType);
    
    // Find questions by target ID
    List<SurveyQuestion> findByTargetId(String targetId);
    
    // Find questions by target type and ID
    List<SurveyQuestion> findByTargetTypeAndTargetId(String targetType, String targetId);
    
    // Find questions by question type
    List<SurveyQuestion> findByQuestionType(String questionType);
    
    // Find questions by target type, ordered by order index
    List<SurveyQuestion> findByTargetTypeOrderByOrderIndex(String targetType);
    
    // Find questions by target ID, ordered by order index
    List<SurveyQuestion> findByTargetIdOrderByOrderIndex(String targetId);
    
    // Find required questions by target type
    List<SurveyQuestion> findByTargetTypeAndIsRequiredTrue(String targetType);
    
    // Find questions by target name
    List<SurveyQuestion> findByTargetNameContainingIgnoreCase(String targetName);
}
