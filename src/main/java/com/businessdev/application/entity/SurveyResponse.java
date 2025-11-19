package com.businessdev.application.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Document(collection = "survey_responses")
public class SurveyResponse {
    
    @Id
    private String id;
    
    private String consumerId;
    private String consumerEmail;
    private String surveyType; // "consumable", "non-consumable", "service"
    private String productType; // specific type like "fruits", "electronics", etc.
    private String productId; // specific product/service ID
    private String productName; // specific product/service name
    
    // Store answers as key-value pairs where key is question ID and value is answer
    private Map<String, Object> answers;
    
    // Demographics
    private String ageGroup;
    private String householdSize;
    private String incomeRange;
    private String location;
    private String continent;
    private String country;
    private String province;
    private String city;
    private String ruralUrban;
    
    private LocalDateTime submittedAt;
    private LocalDateTime createdAt;
    
    public SurveyResponse() {
        this.createdAt = LocalDateTime.now();
    }
    
    public SurveyResponse(String consumerId, String consumerEmail, String surveyType, 
                         String productType, String productId, String productName) {
        this();
        this.consumerId = consumerId;
        this.consumerEmail = consumerEmail;
        this.surveyType = surveyType;
        this.productType = productType;
        this.productId = productId;
        this.productName = productName;
    }
    
    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getConsumerId() { return consumerId; }
    public void setConsumerId(String consumerId) { this.consumerId = consumerId; }
    
    public String getConsumerEmail() { return consumerEmail; }
    public void setConsumerEmail(String consumerEmail) { this.consumerEmail = consumerEmail; }
    
    public String getSurveyType() { return surveyType; }
    public void setSurveyType(String surveyType) { this.surveyType = surveyType; }
    
    public String getProductType() { return productType; }
    public void setProductType(String productType) { this.productType = productType; }
    
    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    
    public Map<String, Object> getAnswers() { return answers; }
    public void setAnswers(Map<String, Object> answers) { this.answers = answers; }
    
    public String getAgeGroup() { return ageGroup; }
    public void setAgeGroup(String ageGroup) { this.ageGroup = ageGroup; }
    
    public String getHouseholdSize() { return householdSize; }
    public void setHouseholdSize(String householdSize) { this.householdSize = householdSize; }
    
    public String getIncomeRange() { return incomeRange; }
    public void setIncomeRange(String incomeRange) { this.incomeRange = incomeRange; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public String getContinent() { return continent; }
    public void setContinent(String continent) { this.continent = continent; }
    
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
    
    public String getProvince() { return province; }
    public void setProvince(String province) { this.province = province; }
    
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    
    public String getRuralUrban() { return ruralUrban; }
    public void setRuralUrban(String ruralUrban) { this.ruralUrban = ruralUrban; }
    
    public LocalDateTime getSubmittedAt() { return submittedAt; }
    public void setSubmittedAt(LocalDateTime submittedAt) { this.submittedAt = submittedAt; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
