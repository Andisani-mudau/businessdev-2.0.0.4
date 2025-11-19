package com.businessdev.application.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "survey_questions")
public class SurveyQuestion {
    
    @Id
    private String id;
    
    @Field("question_text")
    private String questionText;
    
    @Field("question_type")
    private String questionType; // "multiple_choice", "rating", "text", "yes_no"
    
    @Field("options")
    private List<String> options; // For multiple choice questions
    
    @Field("target_type")
    private String targetType; // "product", "service"
    
    @Field("target_id")
    private String targetId; // ID of the product or service
    
    @Field("target_name")
    private String targetName; // Name of the product or service for easy reference
    
    @Field("is_required")
    private boolean isRequired;
    
    @Field("order_index")
    private int orderIndex;
    
    @Field("created_at")
    private LocalDateTime createdAt;
    
    @Field("updated_at")
    private LocalDateTime updatedAt;
    
    // Constructors
    public SurveyQuestion() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.isRequired = true;
        this.orderIndex = 0;
    }
    
    public SurveyQuestion(String questionText, String questionType, String targetType, String targetId, String targetName) {
        this();
        this.questionText = questionText;
        this.questionType = questionType;
        this.targetType = targetType;
        this.targetId = targetId;
        this.targetName = targetName;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getQuestionText() {
        return questionText;
    }
    
    public void setQuestionText(String questionText) {
        this.questionText = questionText;
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getQuestionType() {
        return questionType;
    }
    
    public void setQuestionType(String questionType) {
        this.questionType = questionType;
        this.updatedAt = LocalDateTime.now();
    }
    
    public List<String> getOptions() {
        return options;
    }
    
    public void setOptions(List<String> options) {
        this.options = options;
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getTargetType() {
        return targetType;
    }
    
    public void setTargetType(String targetType) {
        this.targetType = targetType;
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getTargetId() {
        return targetId;
    }
    
    public void setTargetId(String targetId) {
        this.targetId = targetId;
        this.updatedAt = LocalDateTime.now();
    }
    
    public String getTargetName() {
        return targetName;
    }
    
    public void setTargetName(String targetName) {
        this.targetName = targetName;
        this.updatedAt = LocalDateTime.now();
    }
    
    public boolean isRequired() {
        return isRequired;
    }
    
    public void setRequired(boolean required) {
        isRequired = required;
        this.updatedAt = LocalDateTime.now();
    }
    
    public int getOrderIndex() {
        return orderIndex;
    }
    
    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
        this.updatedAt = LocalDateTime.now();
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    @Override
    public String toString() {
        return "SurveyQuestion{" +
                "id='" + id + '\'' +
                ", questionText='" + questionText + '\'' +
                ", questionType='" + questionType + '\'' +
                ", options=" + options +
                ", targetType='" + targetType + '\'' +
                ", targetId='" + targetId + '\'' +
                ", targetName='" + targetName + '\'' +
                ", isRequired=" + isRequired +
                ", orderIndex=" + orderIndex +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
