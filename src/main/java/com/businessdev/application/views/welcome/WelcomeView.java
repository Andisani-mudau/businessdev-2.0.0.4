package com.businessdev.application.views.welcome;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Welcome")
@Route("")
public class WelcomeView extends VerticalLayout {

    private TextField name;
    private Button sayHello;
    private HorizontalLayout hello;
    private final Image logo = new Image("https://github.com/Andisani-mudau/businessdev/blob/master/public/static/css/icons/logo.png?raw=true", "Placeholder logo");
    //private final  Image minimizeIcon = new Image("icons/minimize-2.svg", "Minimize Icon");
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
        add(hello);
    }

    public void navBar(){
        // Initialize the menu dots with a new Div for each dot
        for (int i = 0; i < 3; i++) {
            Div dot = new Div();  // Create a new Div for each dot
            dot.getStyle().set("width", "5px")
                    .set("height", "5px")
                    .set("background-color", "#000")
                    .set("border-radius", "50%");
            menuDotsLayout.getStyle().set("gap", "3px");
            menuDotsLayout.add(dot);  // Add each new dot to the layout
        }

        menuDots = new Button(menuDotsLayout);
        menuDots.getStyle().set("margin", "0")
                .set("border-radius","50%")
                .set("width","50px")
                .set("height","50px");
        logo.getStyle().set("margin", "0")
                .set("padding", "0")
                .set("height", "50px")
                .set("filter", "invert(1)");
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
        menuContent.getStyle().set("width","400px")
                .set("height","calc(100vh - 20px)")
                .set("border-radius","10px")
                .set("background-color","#000")
                .set("position","fixed")
                .set("top","10px")
                .set("right","10px")
                .set("z-index","300")
                .set("gap","20px");
        menu = new VerticalLayout(menuDots);
        menu.getStyle().set("width", "fit-content")
                .set("margin", "0")
                .set("padding", "0");
        menuDots.addClickListener(e -> {
            menu.add(menuContent);
            minimize.addClickListener(f -> {
                menu.remove(menuContent);
            });
        });

        // Set up the navBar layout
        HorizontalLayout navBar = new HorizontalLayout();
        navBar.add(logo, menu);

        // Apply styles to navBar
        navBar.getStyle().set("justify-content", "space-between")
                .set("align-items", "center");
        navBar.setWidthFull(); // Ensure navBar takes full width

        VerticalLayout navSection1 = new VerticalLayout();
            navSection1.getStyle().set("height", "50px");
            navSection1.setWidthFull();
        VerticalLayout navSection2 = new VerticalLayout();
            navSection2.getStyle().set("flex", "1")
                    .set("padding", "0");
            navSection2.setWidthFull();

        HorizontalLayout navSection3 = new HorizontalLayout();
            navSection3.getStyle().set("height", "fit-content");
            navSection3.setWidthFull();
        //...
        Div homeIcon = new Div();
        homeIcon.getStyle().set("margin", "0")
                .set("padding", "0")
                .set("height", "unset");
        homeIcon.getElement().setProperty("innerHTML", "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\" class=\"feather feather-monitor\"><rect x=\"2\" y=\"3\" width=\"20\" height=\"14\" rx=\"2\" ry=\"2\"></rect><line x1=\"8\" y1=\"21\" x2=\"16\" y2=\"21\"></line><line x1=\"12\" y1=\"17\" x2=\"12\" y2=\"21\"></line></svg>");
        Div offersIcon = new Div();
        offersIcon.getStyle().set("margin", "0")
                .set("padding", "0")
                .set("height", "unset");
        offersIcon.getElement().setProperty("innerHTML", "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\" class=\"feather feather-package\"><line x1=\"16.5\" y1=\"9.4\" x2=\"7.5\" y2=\"4.21\"></line><path d=\"M21 16V8a2 2 0 0 0-1-1.73l-7-4a2 2 0 0 0-2 0l-7 4A2 2 0 0 0 3 8v8a2 2 0 0 0 1 1.73l7 4a2 2 0 0 0 2 0l7-4A2 2 0 0 0 21 16z\"></path><polyline points=\"3.27 6.96 12 12.01 20.73 6.96\"></polyline><line x1=\"12\" y1=\"22.08\" x2=\"12\" y2=\"12\"></line></svg>");
        Div servicesIcon = new Div();
        servicesIcon.getStyle().set("margin", "0")
                .set("padding", "0")
                .set("height", "unset");
        servicesIcon.getElement().setProperty("innerHTML", "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\" class=\"feather feather-file\"><path d=\"M13 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V9z\"></path><polyline points=\"13 2 13 9 20 9\"></polyline></svg>");
        Div aboutIcon = new Div();
        aboutIcon.getStyle().set("margin", "0")
                .set("padding", "0")
                .set("height", "unset");
        aboutIcon.getElement().setProperty("innerHTML", "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\" class=\"feather feather-info\"><circle cx=\"12\" cy=\"12\" r=\"10\"></circle><line x1=\"12\" y1=\"16\" x2=\"12\" y2=\"12\"></line><line x1=\"12\" y1=\"8\" x2=\"12.01\" y2=\"8\"></line></svg>");
        Div contactIcon = new Div();
        contactIcon.getStyle().set("margin", "0")
                .set("padding", "0")
                .set("height", "unset");
        contactIcon.getElement().setProperty("innerHTML", "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\" class=\"feather feather-phone\"><path d=\"M22 16.92v3a2 2 0 0 1-2.18 2 19.79 19.79 0 0 1-8.63-3.07 19.5 19.5 0 0 1-6-6 19.79 19.79 0 0 1-3.07-8.67A2 2 0 0 1 4.11 2h3a2 2 0 0 1 2 1.72 12.84 12.84 0 0 0 .7 2.81 2 2 0 0 1-.45 2.11L8.09 9.91a16 16 0 0 0 6 6l1.27-1.27a2 2 0 0 1 2.11-.45 12.84 12.84 0 0 0 2.81.7A2 2 0 0 1 22 16.92z\"></path></svg>");

        Paragraph homeText = new Paragraph("Home");
        Paragraph offersText = new Paragraph("Offers");
        Paragraph servicesText = new Paragraph("Services");
        Paragraph aboutText = new Paragraph("About");
        Paragraph contactText = new Paragraph("Contact");

        HorizontalLayout homeContainer = new HorizontalLayout(homeIcon, homeText);
        homeContainer.setWidthFull();
        setHorizontalComponentAlignment(Alignment.CENTER, homeIcon, homeText);
        HorizontalLayout offersContainer = new HorizontalLayout(offersIcon, offersText);
        offersContainer.setWidthFull();
        setHorizontalComponentAlignment(Alignment.CENTER, offersIcon, offersText);
        HorizontalLayout servicesContainer = new HorizontalLayout(servicesIcon, servicesText);
        servicesContainer.setWidthFull();
        setHorizontalComponentAlignment(Alignment.CENTER, servicesIcon, servicesText);
        HorizontalLayout aboutContainer = new HorizontalLayout(aboutIcon, aboutText);
        setHorizontalComponentAlignment(Alignment.CENTER, aboutIcon, aboutText);
        HorizontalLayout contactContainer = new HorizontalLayout(contactIcon, contactText);
        setHorizontalComponentAlignment(Alignment.CENTER, contactIcon, contactText);

        Anchor home = new Anchor ("#", homeContainer);
        home.setWidthFull();
        home.setClassName("link");
        home.getStyle().setPadding("10px");
        Anchor offers = new Anchor("#", offersContainer);
        offers.setWidthFull();
        offers.getStyle().setPadding("10px");
        Anchor services = new Anchor("#", servicesContainer);
        services.setWidthFull();
        services.getStyle().setPadding("10px");
        Anchor about = new Anchor("#", aboutContainer);
        about.setWidthFull();
        about.getStyle().setPadding("10px");
        Anchor contact = new Anchor("#", contactContainer);
        contact.setWidthFull();
        contact.getStyle().setPadding("10px");

        navSection2.add(home, offers, services, about, contact);
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
}
