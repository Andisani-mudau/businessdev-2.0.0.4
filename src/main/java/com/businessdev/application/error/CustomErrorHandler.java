package com.businessdev.application.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.vaadin.flow.server.ErrorEvent;
import com.vaadin.flow.server.ErrorHandler;

@Component
public class CustomErrorHandler implements ErrorHandler {
    
    private static final Logger logger = LoggerFactory.getLogger(CustomErrorHandler.class);
    
    @Override
    public void error(ErrorEvent event) {
        Throwable throwable = event.getThrowable();
        if (throwable != null) {
            logger.error("Unhandled error in Vaadin application", throwable);
        } else {
            logger.error("Unknown error occurred in Vaadin application");
        }
    }
} 