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
import java.nio.file.Path;

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
            String sourcePath = "/etc/secrets/businessdev-calender-api-5ddd33a1bed7.json";
            String content = new String(Files.readAllBytes(Paths.get(sourcePath)));
            JSONObject jsonObject = new JSONObject(content);
            // Use the jsonObject as needed

            // Define the target path in the resources folder
            String targetPath = "src/main/resources/businessdev-calender-api-5ddd33a1bed7.json";
            
            // Ensure the target directory exists
            Path targetDir = Paths.get("src/main/resources");
            if (!Files.exists(targetDir)) {
                Files.createDirectories(targetDir);
            }

            // Move the file
            Files.move(Paths.get(sourcePath), Paths.get(targetPath));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
