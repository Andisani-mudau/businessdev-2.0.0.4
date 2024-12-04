package com.businessdev.application;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import com.businessdev.application.config.ApiConfig;


/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
@Theme(value = "businessdev")
public class Application implements AppShellConfigurator {

    private static ApiConfig apiConfig;

    public Application(ApiConfig apiConfig) {
        super();
        Application.apiConfig = apiConfig;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("Currency API Key present: " + (apiConfig.getCurrencyApiKey() != null && !apiConfig.getCurrencyApiKey().isEmpty()));
    }
}
