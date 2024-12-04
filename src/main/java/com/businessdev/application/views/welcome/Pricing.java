package com.businessdev.application.views.welcome;

import com.businessdev.application.views.welcome.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Location;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Locale;
import com.businessdev.application.config.ApiConfig;
import com.vaadin.flow.component.Text;


@PageTitle("Pricing")
@Route(value = "pricing", layout = MainLayout.class)
public class Pricing extends VerticalLayout {
    private final ApiConfig apiConfig;
    private final String API_KEY;
    private final String API_URL = "https://api.freecurrencyapi.com/v1/latest";
    private Map<String, Double> exchangeRates = new HashMap<>();
    private String userCurrency = "USD";
    private H1 heading;
    
    private static Map<String, Double> cachedRates = new HashMap<>();
    private static long lastFetchTime = 0;
    private static final long CACHE_DURATION = 24 * 60 * 60 * 1000; // 24 hours in milliseconds
    
    public Pricing(ApiConfig apiConfig) {
        this.apiConfig = apiConfig;
        this.API_KEY = apiConfig.getCurrencyApiKey();
        
        heading = new H1("Web Application Development");
        
        // Determine user's locale and currency
        Locale locale = UI.getCurrent().getLocale();
        java.util.Currency currency = java.util.Currency.getInstance(locale);
        if (currency != null && !currency.getCurrencyCode().equals("USD")) {
            userCurrency = currency.getCurrencyCode();
            fetchExchangeRates();
        }
        
        UI.getCurrent().access(() -> {
            Location location = UI.getCurrent().getInternals().getActiveViewLocation();
            QueryParameters queryParameters = location.getQueryParameters();
            
            if (queryParameters.getParameters().containsKey("service")) {
                String service = queryParameters.getParameters().get("service").get(0);
                heading.setText(service);
                add(pricing());
            }else{
                add(backToOffer());
            }
        });
        
    }
    
    private HorizontalLayout pricing() {
        setWidth("100%");
        setClassName("sectionTwo");
        Main mainLayout = new Main();
        mainLayout.addClassName("main_pricing");
        mainLayout.addClassName("flow_pricing");

        heading.addClassName("main__heading_pricing");

        Div cardsLayout = new Div();
        cardsLayout.addClassName("main__cards_pricing");
        cardsLayout.addClassName("cards_pricing");

        Div cardsInner = new Div();
        cardsInner.addClassName("cards__inner_pricing");

        String service = heading.getText();
        String[][] planDetails = getPlanDetailsForService(service);

        // Basic Plan
        Div basicCard = createPricingCard(
            "Basic",
            planDetails[0][0], // price
            planDetails[0][1].split("\\|"), // features array
            "Get Started",
            "#basic"
        );

        // Pro Plan
        Div proCard = createPricingCard(
            "Pro",
            planDetails[1][0], // price
            planDetails[1][1].split("\\|"), // features array
            "Upgrade to Pro",
            "#pro"
        );

        // Ultimate Plan
        Div ultimateCard = createPricingCard(
            "Ultimate",
            planDetails[2][0], // price
            planDetails[2][1].split("\\|"), // features array
            "Go Ultimate",
            "#ultimate"
        );

        cardsInner.add(basicCard, proCard, ultimateCard);
        // Create overlay div
        Div overlay = new Div();
        overlay.addClassName("overlay_pricing");
        overlay.addClassName("cards__inner_pricing");

        Paragraph paragraph = new Paragraph();
        paragraph.add(new Text("If you are only interested in a maintenance plan, "));
        Anchor contactLink = new Anchor("contact", "please contact us");
        paragraph.add(contactLink);
        paragraph.add(new Text("."));
        paragraph.addClassName("paragraph_pricing");
        cardsLayout.add(cardsInner, overlay);
        mainLayout.add(heading, cardsLayout, paragraph);

        // Add JavaScript
        UI.getCurrent().getPage().executeJs("""
            const cardsContainer = document.querySelector(".cards_pricing");
            const cardsContainerInner = document.querySelector(".cards__inner_pricing");
            const cards = Array.from(document.querySelectorAll(".card_pricing"));
            const overlay = document.querySelector(".overlay_pricing");

            const applyOverlayMask = (e) => {
                const overlayEl = e.currentTarget;
                const x = e.pageX - cardsContainer.offsetLeft;
                const y = e.pageY - cardsContainer.offsetTop;
                
                overlayEl.style = `--opacity: 1; --x: ${x}px; --y:${y}px;`;
            };

            const createOverlayCta = (overlayCard, ctaEl) => {
                const overlayCta = document.createElement("div");
                overlayCta.classList.add("cta_pricing");
                overlayCta.textContent = ctaEl.textContent;
                overlayCta.setAttribute("aria-hidden", true);
                overlayCard.append(overlayCta);
            };

            const observer = new ResizeObserver((entries) => {
                entries.forEach((entry) => {
                    const cardIndex = cards.indexOf(entry.target);
                    let width = entry.borderBoxSize[0].inlineSize;
                    let height = entry.borderBoxSize[0].blockSize;

                    if (cardIndex >= 0) {
                        overlay.children[cardIndex].style.width = `${width}px`;
                        overlay.children[cardIndex].style.height = `${height}px`;
                    }
                });
            });

            const initOverlayCard = (cardEl) => {
                const overlayCard = document.createElement("div");
                overlayCard.classList.add("card_pricing");
                createOverlayCta(overlayCard, cardEl.lastElementChild);
                overlay.append(overlayCard);
                observer.observe(cardEl);
            };

            cards.forEach(initOverlayCard);
            document.body.addEventListener("pointermove", applyOverlayMask);
        """);
        
        return new HorizontalLayout(mainLayout);
    }

    private Div createPricingCard(String title, String price, String[] features, String ctaText, String href) {
        Div card = new Div();
        card.addClassName("cards__card_pricing");
        card.addClassName("card_pricing");

        H2 cardHeading = new H2(title);
        cardHeading.addClassName("card__heading_pricing");

        Paragraph priceText = new Paragraph(price);
        priceText.addClassName("card__price_pricing");

        UnorderedList featuresList = new UnorderedList();
        featuresList.addClassName("card__bullets_pricing");
        featuresList.addClassName("flow_pricing");
        featuresList.getElement().setAttribute("role", "list");

        for (String feature : features) {
            ListItem item = new ListItem(feature);
            featuresList.add(item);
        }

        Button cta = new Button(ctaText);
        cta.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cta.addClassName("card__cta_pricing");
        cta.addClassName("cta_pricing");
        
        // Add click listener to handle navigation
        cta.addClickListener(e -> {
            String service = heading.getText();
            UI.getCurrent().navigate("contact", 
                QueryParameters.simple(Map.of(
                    "service", service,
                    "serviceType", title
                ))
            );
        });

        card.add(cardHeading, priceText, featuresList, cta);
        return card;
    }

    private String[][] getPlanDetailsForService(String service) {
        String[][] plans = switch (service) {
            case "Web Application Development" -> new String[][] {
                {"$999", "Basic web app development|Single page application|Basic database integration|Standard security features"},
                {"$2,499", "Advanced web app development|Multi-page application|Advanced database integration|Enhanced security|API integration|Basic analytics"},
                {"$4,999", "Enterprise web solutions|Microservices architecture|Full-scale database solutions|Advanced security features|Complete API suite|Advanced analytics|24/7 support"}
            };
            case "Mobile Application Development" -> new String[][] {
                {"$1,499", "Single platform (iOS or Android)|Basic features|Standard UI|Local data storage"},
                {"$2,999", "Dual platform development|Advanced features|Custom UI/UX|Cloud storage integration"},
                {"$5,999", "Cross-platform enterprise solution|Premium features|Advanced UI/UX|Full backend integration|Analytics"}
            };
            case "UI/UX Design" -> new String[][] {
                {"$799", "Basic UI design|2 design iterations|Essential user flows|Basic prototype"},
                {"$1,499", "Advanced UI/UX design|4 design iterations|Detailed user flows|Interactive prototype"},
                {"$2,999", "Complete design system|Unlimited iterations|Complex user flows|Advanced prototypes|User testing"}
            };
            case "Brand Identity Design" -> new String[][] {
                {"$599", "Logo design|Basic brand guidelines|2 revisions|Basic color palette"},
                {"$1,299", "Logo design suite|Comprehensive guidelines|5 revisions|Extended color palette"},
                {"$2,499", "Full brand identity|Complete style guide|Unlimited revisions|Marketing materials"}
            };
            case "Graphic Design" -> new String[][] {
                {"$399", "Basic design elements|2 concepts|2 revisions|Single format"},
                {"$799", "Advanced design package|4 concepts|4 revisions|Multiple formats"},
                {"$1,499", "Premium design suite|Unlimited concepts|Unlimited revisions|All formats"}
            };
            case "Construction Architecture" -> new String[][] {
                {"$1,999", "Basic floor plans|3D exterior views|Basic material list|2 revisions"},
                {"$3,999", "Detailed plans|Full 3D modeling|Complete material specs|4 revisions"},
                {"$7,999", "Complete architectural package|Advanced 3D visualization|Full documentation|Unlimited revisions"}
            };
            case "3D Modeling" -> new String[][] {
                {"$799", "Basic 3D model|2 rendering views|Basic texturing|2 revisions"},
                {"$1,599", "Detailed 3D model|5 rendering views|Advanced texturing|4 revisions"},
                {"$2,999", "Complex 3D model|Unlimited views|Premium texturing|Animation|Unlimited revisions"}
            };
            case "Business Analysis" -> new String[][] {
                {"$1,499", "Basic requirements analysis|Process mapping|Basic documentation|Weekly reports"},
                {"$2,999", "Detailed business analysis|Process optimization|Full documentation|Bi-weekly reports"},
                {"$5,999", "Enterprise analysis|Complete optimization|Strategic planning|Daily reports|Implementation support"}
            };
            case "Solutions Architecture" -> new String[][] {
                {"$2,499", "Basic architecture design|System documentation|Basic scalability plan|Standard security"},
                {"$4,999", "Advanced architecture design|Detailed documentation|Scalability strategy|Enhanced security"},
                {"$9,999", "Enterprise architecture|Complete documentation|Full scaling strategy|Premium security|24/7 support"}
            };
            default -> new String[][] {
                {"$0", "No features available"},
                {"$0", "No features available"},
                {"$0", "No features available"}
            };
        };
        
        // Convert prices if needed
        if (!userCurrency.equals("USD")) {
            for (String[] plan : plans) {
                plan[0] = convertPrice(plan[0]);
            }
        }
        
        return plans;
    }

    private void fetchExchangeRates() {
        // Check cache first
        long currentTime = System.currentTimeMillis();
        if (!cachedRates.isEmpty() && (currentTime - lastFetchTime) < CACHE_DURATION) {
            exchangeRates = new HashMap<>(cachedRates);
            return;
        }

        try {
            // Check if API key is available
            if (API_KEY == null || API_KEY.trim().isEmpty()) {
                System.err.println("Currency API key is not configured");
                userCurrency = "USD";
                return;
            }

            HttpClient client = HttpClient.newHttpClient();
            String apiUrl = API_URL + "?apikey=" + API_KEY;
            System.out.println("Attempting to fetch exchange rates from: " + API_URL); // Log API call

            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Accept", "application/json")
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            // Log response status and body for debugging
            System.out.println("API Response Status: " + response.statusCode());
            System.out.println("API Response Body: " + response.body());

            if (response.statusCode() != 200) {
                System.err.println("Currency API returned non-200 status: " + response.statusCode());
                userCurrency = "USD";
                return;
            }

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.body());
            JsonNode rates = root.get("data");
            
            if (rates == null || rates.isEmpty()) {
                System.err.println("No exchange rates data in response");
                userCurrency = "USD";
                return;
            }

            rates.fields().forEachRemaining(entry -> 
                exchangeRates.put(entry.getKey(), entry.getValue().asDouble()));
                
            System.out.println("Successfully loaded exchange rates for " + exchangeRates.size() + " currencies");
                
            // Update cache if successful
            cachedRates = new HashMap<>(exchangeRates);
            lastFetchTime = currentTime;
        } catch (Exception e) {
            System.err.println("Error fetching exchange rates: " + e.getMessage());
            e.printStackTrace();
            userCurrency = "USD";
        }
    }

    private String convertPrice(String usdPrice) {
        try {
            // Extract numeric value from price string
            double amount = Double.parseDouble(usdPrice.replace("$", "").replace(",", ""));
            
            if (!userCurrency.equals("USD") && exchangeRates.containsKey(userCurrency)) {
                double convertedAmount = amount * exchangeRates.get(userCurrency);
                // Get currency instance and symbol
                java.util.Currency currencyInstance = java.util.Currency.getInstance(userCurrency);
                String symbol = currencyInstance.getSymbol();
                
                // Create NumberFormat for the current locale
                Locale locale = UI.getCurrent().getLocale();
                java.text.NumberFormat formatter = java.text.NumberFormat.getCurrencyInstance(locale);
                formatter.setCurrency(currencyInstance);
                
                // Format the number
                String formattedAmount = formatter.format(convertedAmount);
                
                // Some currencies might place the symbol at the end, so we'll use the formatted amount as is
                return formattedAmount;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // If still using USD or conversion failed, format the original price
        try {
            double amount = Double.parseDouble(usdPrice.replace("$", "").replace(",", ""));
            return String.format("$%,.2f", amount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return usdPrice; // Return original price if all formatting fails
    }

    private VerticalLayout backToOffer(){
        VerticalLayout backToOffer = new VerticalLayout();
        backToOffer.setHeight("calc(100dvh - 30px)");
        backToOffer.setAlignItems(Alignment.CENTER);
        backToOffer.setJustifyContentMode(JustifyContentMode.CENTER);
        VerticalLayout mainContainter = new VerticalLayout();
        mainContainter.setSpacing(true);
        mainContainter.setMaxWidth("500px");
        mainContainter.setAlignItems(Alignment.CENTER);
        mainContainter.setJustifyContentMode(JustifyContentMode.CENTER);
        
        
        VerticalLayout imageContainer = new VerticalLayout();
        //imageContainer.setHeight("400px");
        Image image = new Image("https://illustrations.popsy.co/amber/working-vacation.svg", "Chilling at the beach.");
        image.setWidth("100%");
        Paragraph text = new Paragraph("Oops! You skipped your way here, ");
        Anchor goBackToOffers = new Anchor("offers", "go back to offers.");
        goBackToOffers.getStyle().set("text-decoration", "underline");
        text.add(goBackToOffers);

        imageContainer.add(image);
        mainContainter.add(imageContainer, text);
        backToOffer.add(mainContainter);
        return backToOffer;
    }
}
