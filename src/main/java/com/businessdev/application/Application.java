package com.businessdev.application;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;
import org.json.JSONException;

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

    public Application() {
        super();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        loadConfiguration();
    }

    private static void loadConfiguration() {
        try {
            String content = new String(Files.readAllBytes(Paths.get("/etc/secrets/businessdev-calender-api-5ddd33a1bed7.json")));
            JSONObject jsonObject = new JSONObject(content);
            // Use the jsonObject as needed
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
