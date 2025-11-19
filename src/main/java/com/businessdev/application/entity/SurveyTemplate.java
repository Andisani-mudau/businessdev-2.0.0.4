package com.businessdev.application.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Document(collection = "survey_templates")
public class SurveyTemplate {
    
    @Id
    private String id;
    
    private String surveyType; // "consumable", "non-consumable", "service"
    private String title;
    private String description;
    private String objective;
    
    // Sections of the survey
    private List<SurveySection> sections;
    
    // Metadata
    private boolean isActive;
    private int version;
    private String createdBy;
    
    public SurveyTemplate() {}
    
    public SurveyTemplate(String surveyType, String title, String description, String objective) {
        this.surveyType = surveyType;
        this.title = title;
        this.description = description;
        this.objective = objective;
        this.isActive = true;
        this.version = 1;
    }
    
    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getSurveyType() { return surveyType; }
    public void setSurveyType(String surveyType) { this.surveyType = surveyType; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getObjective() { return objective; }
    public void setObjective(String objective) { this.objective = objective; }
    
    public List<SurveySection> getSections() { return sections; }
    public void setSections(List<SurveySection> sections) { this.sections = sections; }
    
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
    
    public int getVersion() { return version; }
    public void setVersion(int version) { this.version = version; }
    
    public String getCreatedBy() { return createdBy; }
    public void setCreatedBy(String createdBy) { this.createdBy = createdBy; }
    
    // Inner class for survey sections
    public static class SurveySection {
        private String id;
        private String title;
        private String description;
        private List<SurveyQuestion> questions;
        private int orderIndex;
        
        public SurveySection() {}
        
        public SurveySection(String id, String title, String description, int orderIndex) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.orderIndex = orderIndex;
        }
        
        // Getters and Setters
        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        
        public List<SurveyQuestion> getQuestions() { return questions; }
        public void setQuestions(List<SurveyQuestion> questions) { this.questions = questions; }
        
        public int getOrderIndex() { return orderIndex; }
        public void setOrderIndex(int orderIndex) { this.orderIndex = orderIndex; }
    }
}
