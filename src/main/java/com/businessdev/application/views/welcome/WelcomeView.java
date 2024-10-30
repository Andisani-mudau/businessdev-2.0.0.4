package com.businessdev.application.views.welcome;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("businessdev.")
@Route(" ")
public class WelcomeView extends VerticalLayout {

    private TextField name;
    private Button sayHello;
    private HorizontalLayout hello;
    private Button minimize;
    private VerticalLayout menu;
    private Button menuDots;
    private HorizontalLayout menuDotsLayout = new HorizontalLayout();
    private VerticalLayout menuContent;

    public WelcomeView() {
        // Initialize components
        name = new TextField("Your name");
        sayHello = new Button("Say hello");
        sayHello.addClickListener(e -> {
            Notification.show("Hello " + name.getValue());
        });
        sayHello.addClickShortcut(Key.ENTER);

        hello = new HorizontalLayout(name, sayHello);
        setHorizontalComponentAlignment(Alignment.END, name, sayHello);
        navBar();
	banner();
    }

    public void navBar(){
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
                .set("border-radius","50%")
                .set("width","50px")
                .set("height","50px");
        Div logo2 = new Div();
	logo2.setClassName("logo");
        logo2.getStyle().set("margin", "0")
                .set("padding", "0");
        logo2.getElement().setProperty("innerHTML", "<svg id=\"Layer_2\" data-name=\"Layer 2\" xmlns=\"http://www.w3.org/2000/svg\" height=\"30\" viewBox=\"0 0 484.41 66.89\">\n" +
                "  <defs>\n" +
                "    <style>\n" +
                "      .cls-1 {\n" +
                "        stroke-width: 0px;\n" +
                "      }\n" +
                "    </style>\n" +
                "  </defs>\n" +
                "  <g id=\"Layer_2-2\" data-name=\"Layer 2\">\n" +
                "    <g>\n" +
                "      <path class=\"cls-1\" d=\"M0,2.42l14.02-.04v18.54l1.01-.44,1.01-.44c3.16-1.14,6.4-1.71,9.71-1.71s6.59.57,9.76,1.71c3.08,1.14,5.84,2.83,8.31,5.05,2.43,2.2,4.32,4.76,5.67,7.69,1.38,2.96,2.07,6.14,2.07,9.54s-.67,6.68-2.02,9.58c-1.35,2.9-3.25,5.48-5.71,7.73-2.49,2.26-5.26,3.94-8.31,5.05-3.08,1.2-6.33,1.8-9.76,1.8-4.48,0-8.72-1.05-12.7-3.16-3.98-2.08-7.15-4.95-9.49-8.61C1.22,51.11.03,47.08,0,42.63V2.42ZM17.53,50.41c2.31,2.26,5.05,3.38,8.22,3.38s5.95-1.13,8.26-3.38c2.34-2.26,3.52-4.95,3.52-8.09,0-2.05-.54-3.93-1.63-5.63-1.08-1.73-2.53-3.1-4.35-4.13-1.79-1.03-3.72-1.54-5.8-1.54s-3.94.51-5.76,1.54c-1.82,1.03-3.27,2.4-4.35,4.13-1.08,1.7-1.63,3.57-1.63,5.63,0,3.13,1.17,5.83,3.52,8.09Z\"/>\n" +
                "      <path class=\"cls-1\" d=\"M73.17,53.66h.79c3.57-.12,5.36-2.59,5.36-7.43v-26.19h14.06v26.19c0,12.74-6.48,19.47-19.42,20.17h-.79c-12.95-.7-19.42-7.43-19.42-20.17v-26.19h14.06v26.19c0,4.83,1.79,7.31,5.36,7.43Z\"/>\n" +
                "      <path class=\"cls-1\" d=\"M102.7,50.89c2.78,2.78,6.18,4.17,10.2,4.17s6.28-1.23,6.28-3.69c0-1.29-1.16-2.24-3.47-2.86-1.64-.38-4.25-1.04-7.82-1.98-2.9-.76-5.35-2.17-7.34-4.22-1.96-2.02-2.94-5.13-2.94-9.32,0-3.22.81-5.95,2.42-8.17,1.64-2.23,3.76-3.87,6.37-4.92,2.67-1.08,5.58-1.63,8.75-1.63,1.79,0,3.6.25,5.45.75,1.99.62,3.78,1.38,5.36,2.29,1.73.97,3.25,2.05,4.57,3.25l-7.73,7.69c-2.26-1.7-4.72-2.55-7.38-2.55-3.19,0-4.79.89-4.79,2.68,0,1.61,1.19,2.74,3.56,3.38,2.61.7,5.23,1.36,7.87,1.98,2.9.73,5.32,2.08,7.25,4.04,1.96,1.99,2.94,5.13,2.94,9.4,0,3.19-.85,5.95-2.55,8.26-1.7,2.34-3.97,4.09-6.81,5.23-2.84,1.2-5.79,1.8-8.83,1.8-2.55,0-4.85-.21-6.9-.62-2.05-.38-3.97-1.03-5.76-1.93-1.85-.91-3.78-2.12-5.8-3.65l7.12-9.4Z\"/>\n" +
                "      <path class=\"cls-1\" d=\"M148.05,11.07c-.64,1.08-1.52,1.96-2.64,2.64-1.11.67-2.34,1.01-3.69,1.01s-2.56-.32-3.65-.97c-1.11-.67-1.99-1.57-2.64-2.68-.67-1.17-1.01-2.42-1.01-3.74s.32-2.58.97-3.69c.7-1.14,1.6-2.04,2.68-2.68,1.08-.64,2.3-.97,3.65-.97s2.58.32,3.69.97c1.08.64,1.96,1.54,2.64,2.68.64,1.11.97,2.34.97,3.69s-.32,2.65-.97,3.74ZM148.97,20.08v45.04h-14.02V20.13l14.02-.04Z\"/>\n" +
                "      <path class=\"cls-1\" d=\"M170.64,30.98c-3.57.15-5.36,2.64-5.36,7.47v26.59h-14.06v-26.59c0-12.8,6.47-19.53,19.42-20.17h.79c12.95.64,19.42,7.37,19.42,20.17v26.59h-14.06v-26.59c0-4.83-1.79-7.32-5.36-7.47h-.79Z\"/>\n" +
                "      <path class=\"cls-1\" d=\"M251.76,50.89c2.78,2.78,6.18,4.17,10.2,4.17s6.28-1.23,6.28-3.69c0-1.29-1.16-2.24-3.47-2.86-1.64-.38-4.25-1.04-7.82-1.98-2.9-.76-5.35-2.17-7.34-4.22-1.96-2.02-2.94-5.13-2.94-9.32,0-3.22.81-5.95,2.42-8.17,1.64-2.23,3.76-3.87,6.37-4.92,2.67-1.08,5.58-1.63,8.75-1.63,1.79,0,3.6.25,5.45.75,1.99.62,3.78,1.38,5.36,2.29,1.73.97,3.25,2.05,4.57,3.25l-7.73,7.69c-2.26-1.7-4.72-2.55-7.38-2.55-3.19,0-4.79.89-4.79,2.68,0,1.61,1.19,2.74,3.56,3.38,2.61.7,5.23,1.36,7.87,1.98,2.9.73,5.32,2.08,7.25,4.04,1.96,1.99,2.94,5.13,2.94,9.4,0,3.19-.85,5.95-2.55,8.26-1.7,2.34-3.97,4.09-6.81,5.23-2.84,1.2-5.79,1.8-8.83,1.8-2.55,0-4.85-.21-6.9-.62-2.05-.38-3.97-1.03-5.76-1.93-1.85-.91-3.78-2.12-5.8-3.65l7.12-9.4Z\"/>\n" +
                "      <path class=\"cls-1\" d=\"M288.85,50.89c2.78,2.78,6.18,4.17,10.2,4.17s6.28-1.23,6.28-3.69c0-1.29-1.16-2.24-3.47-2.86-1.64-.38-4.25-1.04-7.82-1.98-2.9-.76-5.35-2.17-7.34-4.22-1.96-2.02-2.94-5.13-2.94-9.32,0-3.22.81-5.95,2.42-8.17,1.64-2.23,3.76-3.87,6.37-4.92,2.67-1.08,5.58-1.63,8.75-1.63,1.79,0,3.6.25,5.45.75,1.99.62,3.78,1.38,5.36,2.29,1.73.97,3.25,2.05,4.57,3.25l-7.73,7.69c-2.26-1.7-4.72-2.55-7.38-2.55-3.19,0-4.79.89-4.79,2.68,0,1.61,1.19,2.74,3.56,3.38,2.61.7,5.23,1.36,7.87,1.98,2.9.73,5.32,2.08,7.25,4.04,1.96,1.99,2.94,5.13,2.94,9.4,0,3.19-.85,5.95-2.55,8.26-1.7,2.34-3.97,4.09-6.81,5.23-2.84,1.2-5.79,1.8-8.83,1.8-2.55,0-4.85-.21-6.9-.62-2.05-.38-3.97-1.03-5.76-1.93-1.85-.91-3.78-2.12-5.8-3.65l7.12-9.4Z\"/>\n" +
                "      <path class=\"cls-1\" d=\"M372.13,42.63c-.03,4.45-1.22,8.48-3.56,12.08-2.34,3.66-5.51,6.53-9.49,8.61-3.98,2.11-8.22,3.16-12.7,3.16-3.43,0-6.68-.6-9.76-1.8-3.05-1.11-5.82-2.8-8.31-5.05-2.46-2.26-4.37-4.83-5.71-7.73s-2.02-6.09-2.02-9.58.69-6.58,2.07-9.54c1.35-2.93,3.24-5.49,5.67-7.69,2.46-2.23,5.23-3.91,8.31-5.05,3.16-1.14,6.42-1.71,9.76-1.71s6.55.57,9.71,1.71l1.01.44,1.01.44V2.37l14.02.04v40.21ZM358.11,42.32c0-2.05-.54-3.93-1.63-5.63-1.08-1.73-2.53-3.1-4.35-4.13-1.79-1.03-3.71-1.54-5.76-1.54s-3.98.51-5.8,1.54c-1.82,1.03-3.27,2.4-4.35,4.13-1.08,1.7-1.63,3.57-1.63,5.63,0,3.13,1.17,5.83,3.52,8.09,2.31,2.26,5.07,3.38,8.26,3.38s5.9-1.13,8.22-3.38c2.34-2.26,3.52-4.95,3.52-8.09Z\"/>\n" +
                "      <path class=\"cls-1\" d=\"M466.35,20.08l-23.69,46.8-22.85-46.71,14.28-.09,8.75,19.95,8.61-19.95h14.9Z\"/>\n" +
                "      <path class=\"cls-1\" d=\"M469.64,54.19c.76-1.23,1.74-2.18,2.94-2.86,1.23-.7,2.53-1.05,3.91-1.05s2.68.35,3.91,1.05c1.2.73,2.17,1.7,2.9,2.9.73,1.17,1.1,2.46,1.1,3.87,0,2.34-.76,4.28-2.29,5.8s-3.43,2.33-5.62,2.33c-1.46,0-2.8-.35-4-1.05-1.23-.7-2.2-1.68-2.9-2.94-.7-1.29-1.05-2.67-1.05-4.13s.37-2.74,1.1-3.91Z\"/>\n" +
                "      <path class=\"cls-1\" d=\"M240.47,32.74c-1.32-2.99-3.13-5.55-5.45-7.69-2.34-2.2-5-3.87-7.96-5.01-2.99-1.17-6.09-1.76-9.32-1.76s-6.34.59-9.36,1.76c-2.93,1.14-5.57,2.83-7.91,5.05-2.31,2.17-4.13,4.72-5.45,7.65-1.32,2.9-1.98,6.06-1.98,9.49,0,4.57,1.14,8.69,3.43,12.35,2.23,3.66,5.26,6.55,9.1,8.66,3.75,2.08,7.81,3.12,12.17,3.12,3.22,0,6.33-.59,9.32-1.76,2.23-.91,4.42-2.24,6.59-4l-7.46-10.67c-2.06,2.39-5.08,3.9-8.45,3.9-6.21,0-11.25-5.12-11.25-11.45s5.04-11.45,11.25-11.45,11.25,5.12,11.25,11.45c0,.53-.05,1.05-.12,1.56h7.98v-.09h5.45l.09-.75c.03-.18.04-.34.04-.48v-.4c0-3.37-.66-6.53-1.98-9.49Z\"/>\n" +
                "      <path class=\"cls-1\" d=\"M421.74,32.74c-1.32-2.99-3.13-5.55-5.45-7.69-2.34-2.2-5-3.87-7.95-5.01-2.99-1.17-6.09-1.76-9.32-1.76s-6.34.59-9.36,1.76c-2.93,1.14-5.57,2.83-7.91,5.05-2.31,2.17-4.13,4.72-5.45,7.65-1.32,2.9-1.98,6.06-1.98,9.49,0,4.57,1.14,8.69,3.43,12.35,2.23,3.66,5.26,6.55,9.1,8.66,3.75,2.08,7.81,3.12,12.17,3.12,3.22,0,6.33-.59,9.32-1.76,2.23-.91,4.42-2.24,6.59-4l-7.49-10.71c-2.06,2.37-5.06,3.87-8.42,3.87-6.21,0-11.25-5.12-11.25-11.45s5.04-11.45,11.25-11.45,11.25,5.12,11.25,11.45c0,.55-.05,1.09-.13,1.63h7.99v-.09h5.45l.09-.75c.03-.18.04-.34.04-.48v-.4c0-3.37-.66-6.53-1.98-9.49Z\"/>\n" +
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
                .set("right","10px")
                .set("border-radius", "50%")
                .set("width","50px")
                .set("height","50px")
                .set("display","flex")
                .set("align-items","center")
                .set("justify-content","center");
        menuContent = new VerticalLayout(minimize); // Ensure menuContent is initialized
	menuContent.setClassName("menu-popup");
        menuContent.getStyle().set("max-width","400px")
                .set("height","calc(100dvh - 20px)")
                .set("border-radius","10px")
                .set("transition","all 0.3s ease-in-out")
                .set("background-color","var(--lumo-base-color)")
                .set("box-shadow","var(--lumo-box-shadow-xl)")
                .set("position","absolute")
                .set("top","0")
                .set("right","0")
                .set("z-index","300")
                .set("gap","10px")
                .set("padding-bottom","12px");
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
                .set("z-index", "300");
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
        setHorizontalComponentAlignment(Alignment.CENTER, homeIcon, homeText);
        HorizontalLayout offersContainer = new HorizontalLayout(offersIcon, offersText);
        //offersContainer.setWidthFull();
        setHorizontalComponentAlignment(Alignment.CENTER, offersIcon, offersText);
        HorizontalLayout servicesContainer = new HorizontalLayout(servicesIcon, servicesText);
        //servicesContainer.setWidthFull();
        setHorizontalComponentAlignment(Alignment.CENTER, servicesIcon, servicesText);
        HorizontalLayout aboutContainer = new HorizontalLayout(aboutIcon, aboutText);
        setHorizontalComponentAlignment(Alignment.CENTER, aboutIcon, aboutText);
        HorizontalLayout contactContainer = new HorizontalLayout(contactIcon, contactText);
        setHorizontalComponentAlignment(Alignment.CENTER, contactIcon, contactText);

        Anchor home = new Anchor ("#", homeContainer);
        //home.setWidthFull();
        home.setClassName("link");
        home.getStyle().setPadding("10px")
                .setBorderRadius("5px");
        Anchor offers = new Anchor("#", offersContainer);
        //offers.setWidthFull();
        offers.setClassName("link");
        offers.getStyle().setPadding("10px").setBorderRadius("5px");
        Anchor services = new Anchor("#", servicesContainer);
        //services.setWidthFull();
        services.setClassName("link");
        services.getStyle().setPadding("10px").setBorderRadius("5px");
        Anchor about = new Anchor("#", aboutContainer);
        //about.setWidthFull();
        about.setClassName("link");
        about.getStyle().setPadding("10px").setBorderRadius("5px");
        Anchor contact = new Anchor("#", contactContainer);
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
        setHorizontalComponentAlignment(Alignment.END, chat, sendChat);
        menuContent.add(navSection1, navSection2, navSection3);
        // Add navBar and hello layout to the main layout
        add(navBar);
    }

    public void banner(){
	H1 heading = new H1("Business Lives Matters");
	Paragraph paragraph = new Paragraph("Our mission is to become a leading company in connecting businesses and driving innovation by providing best solutions to everyday business problems.");
	Button moreInfo = new Button("Learn more...");
	Image image = new Image("https://illustrations.popsy.co/gray/home-office.svg", "Man in home office");
	image.getStyle().set("width", "100%");
	VerticalLayout sectionOne  = new VerticalLayout(heading, paragraph, moreInfo);
	sectionOne.getStyle().set("padding", "0")
	    .set("margin", "0");
	//.set("padding-top", "60px");
	VerticalLayout sectionTwo  = new VerticalLayout(image);
	sectionTwo.getStyle().set("padding", "0")
	    .set("margin", "0");
	HorizontalLayout banner = new HorizontalLayout(sectionOne, sectionTwo);
	banner.getStyle().set("flex-wrap", "wrap")
	.set("height", "calc(100dvh - 32px)")
	    .set("flex-direction","column-reverse")
	    .set("justify-content","center");
	add(banner);
    }
}
