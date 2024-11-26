package com.businessdev.application.views.welcome;

import com.businessdev.application.views.welcome.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.AttachNotifier;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.Location;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.component.AttachNotifier;
import java.util.Map;

@PageTitle("Pricing")
@Route(value = "pricing", layout = MainLayout.class)
public class Pricing extends VerticalLayout {
    private H1 heading;
    
    public Pricing() { 
        heading = new H1("Web Application Development");
        
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

        cardsLayout.add(cardsInner, overlay);
        mainLayout.add(heading, cardsLayout);

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
        return switch (service) {
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
            case "3D Modeling & Visualization" -> new String[][] {
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
        Image image = new Image("https://illustrations.popsy.co/amber/page-under-construction.svg", "Page under construcion");
        image.setWidth("100%");
        Paragraph text = new Paragraph("Oops! You skipped your way here, ");
        Anchor goBackToOffers = new Anchor("offers", "go back to offers.");
        text.add(goBackToOffers);

        imageContainer.add(image);
        mainContainter.add(imageContainer, text);
        backToOffer.add(mainContainter);
        return backToOffer;
    }
}
