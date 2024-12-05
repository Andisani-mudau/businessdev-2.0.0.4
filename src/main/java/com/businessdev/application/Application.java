package com.businessdev.application;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.AppShellSettings;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.businessdev.application.config.ApiConfig;

import java.util.Date;

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

    @Override
    public void configurePage(AppShellSettings settings) {
        // Basic meta tags
        settings.addMetaTag("http-equiv", "X-UA-Compatible");
        settings.addMetaTag("theme-color", "#000");
        settings.addLink("manifest", "/etc/secrets/manifest.json");
        
        // Apple specific
        settings.addMetaTag("apple-mobile-web-app-capable", "yes");
        settings.addMetaTag("apple-mobile-web-app-status-bar-style", "black-translucent");
        settings.addMetaTag("apple-mobile-web-app-title", "businessdev.");
        
        // General meta
        settings.addMetaTag("content-language", "en");
        settings.addMetaTag("robots", "index, follow");
        settings.addMetaTag("author", "businessdev.");
        
        // Open Graph
        settings.addMetaTag("og:image", "icons/secondary.png");
        settings.addMetaTag("og:image:width", "1000");
        settings.addMetaTag("og:image:height", "1000");
        
        // Twitter Card
        settings.addMetaTag("twitter:card", "summary_large_image");
        settings.addMetaTag("twitter:image", "icons/tertiary.png");
        
        // Schema.org
        settings.addMetaTag("itemprop", "icons/secondary.png");
        
        // Icons and favicons
        settings.addLink("apple-touch-icon", "icons/secondary.png");
        settings.addLink("icon", "icons/logo.ico");
        
        // Windows Pinned Sites
        settings.addMetaTag("msapplication-TileImage", "icons/secondary.png");
        
        // Keywords and description
        settings.addMetaTag("keywords", "Web Development, Business, UI, UX, Business Analysis, Revenue, profit, loss, strategy, marketing, branding, sales, customer, client, stakeholder, negotiation, contract, investment, equity, assets, liabilities, capital, venture, innovation, leadership, management, operations, logistics, supply chain, distribution, finance, accounting, auditing, balance sheet, income statement, cash flow, budget, forecast, expenses, overhead, inventory, merger, acquisition, partnership, joint venture, globalization, outsourcing, productivity, efficiency, scalability, market share, competition, pricing, demand, supply, growth, sustainability, ethics, compliance, regulation, risk, opportunity, entrepreneurship, startup, corporation, enterprise, small business, business model, vision, mission, goals, objectives, KPI, ROI, CRM, ERP, human resources, recruitment, retention, training, development, performance, appraisal, motivation, culture, diversity, inclusion, team, collaboration, communication, decision-making, problem-solving, innovation, creativity, strategy, planning, analysis, data, metrics, research, development, technology, disruption, digital transformation, automation, AI, Algorithm, data, database, server, client, network, firewall, encryption, cybersecurity, cloud, storage, virtualization, hardware, software, operating system, application, API, code, programming, development, deployment, testing, debugging, patch, update, upgrade, version, protocol, IP address, DNS, TCP/IP, HTTP, HTTPS, SSL, SSH, FTP, LAN, WAN, VPN, router, switch, firewall, gateway, bandwidth, latency, throughput, redundancy, failover, backup, restore, recovery, disaster recovery, uptime, downtime, SLA, IoT, AI, machine learning, deep learning, blockchain, big data, analytics, data mining, data science, SQL, NoSQL, JSON, XML, HTML, CSS, JavaScript, Python, Java, C++, Ruby, PHP, .NET, framework, library, SDK, IDE, GUI, CLI, UX, UI, agile, Scrum, DevOps, continuous integration, continuous deployment, automation, containerization, Docker, Kubernetes, microservices, API, REST, SOAP, Git, version control");
        settings.addMetaTag("description", "Web, App Developer | UI, Logo, Graphic Design | Architecture | 3D Modeling | Business Analysis | Solutions Architect");
        settings.addMetaTag("mobile-web-app-capable", "yes");
        
        // SEO Meta Tags
        settings.addMetaTag("og:type", "website");
        settings.addMetaTag("og:title", "businessdev. - IT Development & Business Solutions");
        settings.addMetaTag("og:description", "Professional IT development, business analysis, and digital solutions");
        settings.addMetaTag("og:url", "https://businessdev.co.za");
        
        settings.addMetaTag("twitter:title", "businessdev.");
        settings.addMetaTag("twitter:description", "Professional IT development, business analysis, and digital solutions");
        settings.addMetaTag("twitter:creator", "@busincssdcv");
        settings.addMetaTag("twitter:site", "@businessdev.co.za");
        
        settings.addMetaTag("geo.region", "ZA"); // Replace with your region
        settings.addMetaTag("geo.position", "-23.922415, 29.442512"); // Your location
        settings.addMetaTag("ICBM", "-23.922415, 29.442512"); // Your location
        
        settings.addMetaTag("revisit-after", "7 days");
        settings.addMetaTag("distribution", "global");
        settings.addMetaTag("rating", "general");
        settings.addMetaTag("copyright", "© " + java.time.Year.now().getValue() + " businessdev.");
        
        // Additional SEO Tags
        settings.addMetaTag("format-detection", "telephone=yes");
        settings.addMetaTag("apple-touch-fullscreen", "yes");
        settings.addMetaTag("application-name", "businessdev.");
        settings.addMetaTag("mobile-web-app-capable", "yes");              // Android fullscreen
        settings.addMetaTag("theme-color", "#000000");                     // Android status bar color
        settings.addMetaTag("android-mobile-web-app-capable", "yes");      // Alternative Android fullscreen
        settings.addMetaTag("android-theme", "#000000");                   // Alternative theme declaration
        
        // Rich Snippets
        settings.addMetaTag("og:site_name", "businessdev.");
        settings.addMetaTag("og:locale", "en_US");
        settings.addMetaTag("article:publisher", "https://x.com/busincssdcv");
        settings.addMetaTag("article:modified_time", java.time.Instant.now().toString());
        
        // Security and Technical
        settings.addMetaTag("referrer", "no-referrer-when-downgrade");
        settings.addMetaTag("x-dns-prefetch-control", "on");
        settings.addMetaTag("x-frame-options", "SAMEORIGIN");


        // Technical Performance
        settings.addMetaTag("resource-type", "document");
        settings.addMetaTag("cache-control", "public, max-age=3600"); // Cache for 1 hour
        settings.addMetaTag("expires", new Date(System.currentTimeMillis() + 3600000).toString());
        
        // Content Information
        settings.addMetaTag("category", "technology, business, IT development, digital solutions, innovation, entrepreneurship");
        settings.addMetaTag("coverage", "Worldwide");
        settings.addMetaTag("target", "all");
        settings.addMetaTag("HandheldFriendly", "True");
        
        // Additional Social
        settings.addMetaTag("linkedin:owner", "https://www.linkedin.com/in/andisani-m-1718aa222/");
        settings.addMetaTag("instagram:creator", "https://www.instagram.com/busincssdcv");
        
        // Browser Specific
        settings.addMetaTag("apple-mobile-web-app-orientations", "portrait");
        settings.addMetaTag("apple-mobile-web-app-capable", "yes");
        settings.addMetaTag("mobile-web-app-capable", "yes");
        settings.addMetaTag("apple-touch-startup-image", "icons/secondary.png");
        
        // Analytics and Tracking
        settings.addMetaTag("analytics-track", "true");
        settings.addMetaTag("google-analytics", "UA-XXXXX-Y");

        // Language and Region
        settings.addMetaTag("language", "English");
        settings.addMetaTag("country", "South Africa");
        settings.addMetaTag("locality", "Polokwane");
        
        // Business Information
        settings.addMetaTag("business:contact_data:street_address", "Ext 22, Himation Ave, Ivy Park");
        settings.addMetaTag("business:contact_data:locality", "Polokwane");
        settings.addMetaTag("business:contact_data:region", "Limpopo");
        settings.addMetaTag("business:contact_data:postal_code", "0699");
        settings.addMetaTag("business:contact_data:country_name", "South Africa");
        settings.addMetaTag("business:contact_data:email", "customer@businessdev.co.za");
        settings.addMetaTag("business:contact_data:phone_number", "+27 68 213 9840");
        
        // Content Ratings
        settings.addMetaTag("rating:value", "4.8");
        settings.addMetaTag("rating:count", "80259");
        settings.addMetaTag("og:price:amount", "0");
        settings.addMetaTag("og:price:currency", "ZAR");
        
        // Technical Preferences
        settings.addMetaTag("renderer", "webkit|ie-comp|ie-stand");
        settings.addMetaTag("format-detection", "address=no");
        settings.addMetaTag("format-detection", "date=no");
        settings.addMetaTag("wap-font-scale", "no");
        
        // Additional Browser Support
        settings.addMetaTag("MobileOptimized", "width");
        settings.addMetaTag("screen-orientation", "portrait");
        settings.addMetaTag("full-screen", "yes");
        settings.addMetaTag("browsermode", "application");
        
        // Content Security
        settings.addMetaTag("content-security-policy", "default-src 'self'");
    }
}
