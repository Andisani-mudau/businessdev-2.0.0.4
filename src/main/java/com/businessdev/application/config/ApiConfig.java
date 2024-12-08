package com.businessdev.application.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfig {
    
    @Value("${currency.api.key}")
    private String currencyApiKey;

    @Value("${brevo.api.key}")
    private String brevoApiKey;

    @Value("${brevo.sender.email}")
    private String brevoSenderEmail;

    @Value("${brevo.sender.name}")
    private String brevoSenderName;

    @Value("${google.calendar.id}")
    private String calendarId;

    @Value("${google.calendar.credentials.file}")
    private String serviceAccountCredentialsFile;

    @Value("${google.calendar.application.name}")
    private String calendarApplicationName;

    @Value("${app.default.timezone}")
    private String defaultTimezone;

    @Value("${business.hours.start}")
    private int businessHoursStart;

    @Value("${business.hours.end}")
    private int businessHoursEnd;

    @Value("${groq.api.key}")
    private String groqApiKey;

    @Value("${groq.model}")
    private String groqModel;

    @Value("${groq.url}")
    private String groqUrl;

    @Value("${mongo.uri}")
    private String mongoUri;

    @Value("${mapbox.access.token}")
    private String mapboxAccessToken;

    // Getters
    public String getCurrencyApiKey() {
        return currencyApiKey;
    }

    public String getBrevoApiKey() {
        return brevoApiKey;
    }

    public String getBrevoSenderEmail() {
        return brevoSenderEmail;
    }

    public String getBrevoSenderName() {
        return brevoSenderName;
    }

    public String getCalendarId() {
        return calendarId;
    }

    public String getServiceAccountCredentialsFile() {
        return serviceAccountCredentialsFile;
    }

    public String getCalendarApplicationName() {
        return calendarApplicationName;
    }

    public String getDefaultTimezone() {
        return defaultTimezone;
    }

    public int getBusinessHoursStart() {
        return businessHoursStart;
    }

    public int getBusinessHoursEnd() {
        return businessHoursEnd;
    }

    public String getGroqApiKey() {
        return groqApiKey;
    }

    public String getGroqModel() {
        return groqModel;
    }

    public String getGroqUrl() {
        return groqUrl;
    }

    public String getMongoUri() {
        return mongoUri;
    }

    public String getMapboxAccessToken() {
        return mapboxAccessToken;
    }
} 