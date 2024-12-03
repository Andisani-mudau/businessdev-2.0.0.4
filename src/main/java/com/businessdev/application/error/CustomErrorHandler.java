package com.businessdev.application.error;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.vaadin.flow.server.ErrorEvent;
import com.vaadin.flow.server.ErrorHandler;

@Component
public class CustomErrorHandler implements ErrorHandler {
    
    @Override
    public void error(ErrorEvent event) {
        System.err.println("There was an error");
        Throwable throwable = event.getThrowable();
        if (throwable instanceof Exception) {
            LoggerFactory.getLogger(getClass()).error("Error in application", throwable);
        }
    }
} 