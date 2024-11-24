package com.businessdev.application.views.components;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class MainNavigation extends VerticalLayout{
    private Button minimize;
    private VerticalLayout menu;
    private Button menuDots;
    private HorizontalLayout menuDotsLayout = new HorizontalLayout();
    private VerticalLayout menuContent;

    public MainNavigation(){
        addClassName("main-nav");
        getElement().getStyle().set("background-color", "#000");
        // Initialize the menu dots with a new Div for each dot
        for (int i = 0; i < 3; i++) {
            Div dot = new Div();  // Create a new Div for each dot
            dot.getStyle().set("width", "5px")
                    .set("height", "5px")
                    .set("background-color", "var(--lumo-primary-color)")
                    .set("border-radius", "50%");
            menuDotsLayout.getStyle().set("gap", "3px");
            menuDotsLayout.add(dot);  // Add each new dot to the layout
        }

        menuDots = new Button(menuDotsLayout);
        menuDots.getStyle().set("margin", "0")
                .set("border-radius","10px")
                .set("width","50px")
                .set("height","50px")
                .set("cursor","pointer");
        Div logo2 = new Div();
        logo2.setClassName("logo");
        logo2.getStyle().set("margin", "0")
                .set("padding", "0")
                .set("position", "relative")
                .set("mix-blend-mode", "difference"); /* Changes color based on background */
        //.set("filter", "invert(1)"); /* Inverts color from black to white */;
        logo2.getElement().setProperty("innerHTML", "<svg id=\"Layer_2\" data-name=\"Layer 2\" xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 96.93 50\">\n" +
                "  <defs>\n" +
                "    <style>\n" +
                "      .cls-1 {\n" +
                "        stroke-width: 0px;\n" +
                "      }\n" +
                "    </style>\n" +
                "  </defs>\n" +
                "  <g id=\"Layer_1-2\" data-name=\"Layer 1\">\n" +
                "    <g>\n" +
                "      <path class=\"cls-1\" d=\"M37.96,30.81h0c0,10.45-8.54,18.99-18.98,18.99h0C8.53,49.8.06,41.35,0,30.91v-14.09c0-2.76,2.24-5,5-5h14.09c10.43.06,18.88,8.54,18.88,18.98Z\"/>\n" +
                "      <path class=\"cls-1\" d=\"M76.27,11.82c2.76,0,5,2.24,5,5v14.09h-.11c0,10.49-8.49,18.99-18.98,18.99s-18.98-8.5-18.98-18.99,8.5-18.98,18.98-18.98v-.11h14.09Z\"/>\n" +
                "      <path class=\"cls-1\" d=\"M35.3,3.02c.59-.95,1.34-1.68,2.27-2.2.95-.54,1.95-.81,3.02-.81s2.07.27,3.01.81c.93.57,1.67,1.31,2.24,2.24.56.9.85,1.9.85,2.98,0,1.88-.63,3.41-1.9,4.61-1.17,1.1-2.78,1.7-4.39,1.66-1.05-.03-2.02-.3-2.89-.81-.95-.54-1.69-1.3-2.24-2.27-.54-.99-.81-2.06-.81-3.18s.28-2.11.85-3.02Z\"/>\n" +
                "      <path class=\"cls-1\" d=\"M85.55,40.72c.59-.95,1.34-1.68,2.27-2.2.95-.54,1.95-.81,3.02-.81s2.07.27,3.01.81c.93.57,1.67,1.31,2.24,2.24.56.9.85,1.9.85,2.98,0,1.88-.63,3.41-1.9,4.61-1.17,1.1-2.78,1.7-4.39,1.66-1.05-.03-2.02-.3-2.89-.81-.95-.54-1.69-1.3-2.24-2.27-.54-.99-.81-2.06-.81-3.18s.28-2.11.85-3.02Z\"/>\n" +
                "    </g>\n" +
                "  </g>\n" +
                "</svg>");
        // Optional: style the icon size and add to layout
        Div minimizeIcon = new Div();
        minimizeIcon.getElement().setProperty("innerHTML", "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\" class=\"feather feather-minimize-2\"><polyline points=\"4 14 10 14 10 20\"></polyline><polyline points=\"20 10 14 10 14 4\"></polyline><line x1=\"14\" y1=\"10\" x2=\"21\" y2=\"3\"></line><line x1=\"3\" y1=\"21\" x2=\"10\" y2=\"14\"></line></svg>"); // Paste SVG code here
        minimizeIcon.setWidth("24px");
        minimizeIcon.setHeight("24px");
        minimizeIcon.getStyle().set("pointer-events", "none")
                .set("padding", "0")
                .set("margin", "0");
        minimize = new Button(minimizeIcon);
        minimize.getStyle().set("padding", "5px")
                .set("position","absolute")
                .set("top","10px")
                .set("right","15px")
                .set("cursor","pointer")
                .set("background-color","transparent")
                .set("border-radius", "5px")
                .set("width","50px")
                .set("height","50px")
                .set("display","flex")
                .set("align-items","center")
                .set("justify-content","center");
        menuContent = new VerticalLayout(minimize); // Ensure menuContent is initialized
        menuContent.setClassName("menu-popup");
        menuContent.getStyle().set("max-width","450px")
                .set("height","calc(100dvh - 20px)")
                .set("border-radius","10px")
                .set("transition","all 0.3s ease-in-out")
                .set("background-color","var(--lumo-base-color)")
                .set("box-shadow","var(--lumo-box-shadow-xl)")
                .set("position","absolute")
                .set("top","0px")
                .set("right","0px")
                .set("z-index","300")
                .set("gap","15px")
                .set("margin-left","0")
                .set("padding","12px 15px");
        menu = new VerticalLayout(menuDots);
        menu.getStyle().set("width", "fit-content")
                .set("margin", "0")
                .set("padding", "0");
        menuDots.addClickListener(e -> {
            menu.add(menuContent);
            minimize.addClickListener(f -> {
                // Close after animation
                menu.remove(menuContent);
            });
        });

        // Set up the navBar layout
        HorizontalLayout navBar = new HorizontalLayout();
        navBar.add(logo2, menu);

        // Apply styles to navBar
        navBar.getStyle().set("justify-content", "space-between")
                .set("align-items", "center")
                .set("position", "fixed")
                .set("top", "10px")
                .set("left", "10px")
                .set("right", "10px")
                .set("padding","10px")
                .set("z-index", "3000");
        //navBar.setWidthFull(); // Ensure navBar takes full width

        VerticalLayout navSection1 = new VerticalLayout();
        navSection1.getStyle().set("height", "50px");
        navSection1.setWidthFull();
        VerticalLayout navSection2 = new VerticalLayout();
        navSection2.getStyle().set("flex", "1")
                .set("gap", "5px")
                .set("padding", "0")
                .set("margin", "0")
                .set("flex-warp", "wrap");

        HorizontalLayout navSection3 = new HorizontalLayout();
        navSection3.getStyle().set("height", "fit-content")
                .set("padding", "0")
                .set("margin", "0");
        navSection3.setWidthFull();
        //...
        Div homeIcon = new Div();
        homeIcon.getStyle().set("margin", "0")
                .set("padding", "0")
                .set("height", "unset");
        homeIcon.getElement().setProperty("innerHTML", "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"20\" height=\"20\" viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\" class=\"feather feather-monitor\"><rect x=\"2\" y=\"3\" width=\"20\" height=\"14\" rx=\"2\" ry=\"2\"></rect><line x1=\"8\" y1=\"21\" x2=\"16\" y2=\"21\"></line><line x1=\"12\" y1=\"17\" x2=\"12\" y2=\"21\"></line></svg>");
        Div offersIcon = new Div();
        offersIcon.getStyle().set("margin", "0")
                .set("padding", "0")
                .set("height", "unset");
        offersIcon.getElement().setProperty("innerHTML", "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"20\" height=\"20\" viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\" class=\"feather feather-package\"><line x1=\"16.5\" y1=\"9.4\" x2=\"7.5\" y2=\"4.21\"></line><path d=\"M21 16V8a2 2 0 0 0-1-1.73l-7-4a2 2 0 0 0-2 0l-7 4A2 2 0 0 0 3 8v8a2 2 0 0 0 1 1.73l7 4a2 2 0 0 0 2 0l7-4A2 2 0 0 0 21 16z\"></path><polyline points=\"3.27 6.96 12 12.01 20.73 6.96\"></polyline><line x1=\"12\" y1=\"22.08\" x2=\"12\" y2=\"12\"></line></svg>");
        Div servicesIcon = new Div();
        servicesIcon.getStyle().set("margin", "0")
                .set("padding", "0")
                .set("height", "unset");
        servicesIcon.getElement().setProperty("innerHTML", "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"20\" height=\"20\" viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\" class=\"feather feather-file\"><path d=\"M13 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V9z\"></path><polyline points=\"13 2 13 9 20 9\"></polyline></svg>");
        Div aboutIcon = new Div();
        aboutIcon.getStyle().set("margin", "0")
                .set("padding", "0")
                .set("height", "unset");
        aboutIcon.getElement().setProperty("innerHTML", "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"20\" height=\"20\" viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\" class=\"feather feather-info\"><circle cx=\"12\" cy=\"12\" r=\"10\"></circle><line x1=\"12\" y1=\"16\" x2=\"12\" y2=\"12\"></line><line x1=\"12\" y1=\"8\" x2=\"12.01\" y2=\"8\"></line></svg>");
        Div contactIcon = new Div();
        contactIcon.getStyle().set("margin", "0")
                .set("padding", "0")
                .set("height", "unset");
        contactIcon.getElement().setProperty("innerHTML", "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"20\" height=\"20\" viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\" class=\"feather feather-phone\"><path d=\"M22 16.92v3a2 2 0 0 1-2.18 2 19.79 19.79 0 0 1-8.63-3.07 19.5 19.5 0 0 1-6-6 19.79 19.79 0 0 1-3.07-8.67A2 2 0 0 1 4.11 2h3a2 2 0 0 1 2 1.72 12.84 12.84 0 0 0 .7 2.81 2 2 0 0 1-.45 2.11L8.09 9.91a16 16 0 0 0 6 6l1.27-1.27a2 2 0 0 1 2.11-.45 12.84 12.84 0 0 0 2.81.7A2 2 0 0 1 22 16.92z\"></path></svg>");

        Paragraph homeText = new Paragraph("Home");
        homeText.setClassName("linkText");
        Paragraph offersText = new Paragraph("Offers");
        offersText.setClassName("linkText");
        Paragraph servicesText = new Paragraph("Services");
        servicesText.setClassName("linkText");
        Paragraph aboutText = new Paragraph("About");
        aboutText.setClassName("linkText");
        Paragraph contactText = new Paragraph("Contact");
        contactText.setClassName("linkText");

        HorizontalLayout homeContainer = new HorizontalLayout(homeIcon, homeText);
        //homeContainer.setWidthFull();
        setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, homeIcon, homeText);
        HorizontalLayout offersContainer = new HorizontalLayout(offersIcon, offersText);
        //offersContainer.setWidthFull();
        setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, offersIcon, offersText);
        HorizontalLayout servicesContainer = new HorizontalLayout(servicesIcon, servicesText);
        //servicesContainer.setWidthFull();
        setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, servicesIcon, servicesText);
        HorizontalLayout aboutContainer = new HorizontalLayout(aboutIcon, aboutText);
        setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, aboutIcon, aboutText);
        HorizontalLayout contactContainer = new HorizontalLayout(contactIcon, contactText);
        setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, contactIcon, contactText);

        Anchor home = new Anchor (" ", homeContainer);
        //home.setWidthFull();
        home.setClassName("link");
        home.getStyle().setPadding("10px")
                .setBorderRadius("5px");
        Anchor offers = new Anchor("offers", offersContainer);
        //offers.setWidthFull();
        offers.setClassName("link");
        offers.getStyle().setPadding("10px").setBorderRadius("5px");
        Anchor services = new Anchor("services", servicesContainer);
        //services.setWidthFull();
        services.setClassName("link");
        services.getStyle().setPadding("10px").setBorderRadius("5px");
        Anchor about = new Anchor("about", aboutContainer);
        //about.setWidthFull();
        about.setClassName("link");
        about.getStyle().setPadding("10px").setBorderRadius("5px");
        Anchor contact = new Anchor("contact", contactContainer);
        //contact.setWidthFull();
        contact.setClassName("link");
        contact.getStyle().setPadding("10px").setBorderRadius("5px");
        navSection2.add(home, offers, services, contact, about);
        //...
        TextField chat = new TextField();
        chat.getStyle().set("flex", "1");
        Button sendChat = new Button("Chat");
        sendChat.addClickShortcut(Key.ENTER);
        navSection3.add(chat, sendChat);
        setHorizontalComponentAlignment(FlexComponent.Alignment.END, chat, sendChat);
        menuContent.add(navSection1, navSection2, navSection3);
        // Adding navBar and hello layout to the main layout
        add(navBar);
    }
}
