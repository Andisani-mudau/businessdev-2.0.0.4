package com.businessdev.application.config;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class EnvLoader implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        Resource resource = new FileSystemResource(".env");
        
        if (resource.exists()) {
            try {
                Properties props = new Properties();
                props.load(resource.getInputStream());
                
                Map<String, Object> map = new HashMap<>();
                props.forEach((key, value) -> map.put(key.toString(), value));
                
                MapPropertySource propertySource = new MapPropertySource("dotenv", map);
                environment.getPropertySources().addFirst(propertySource);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load .env file", e);
            }
        }
    }
}
