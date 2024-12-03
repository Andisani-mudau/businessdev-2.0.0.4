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
        Div dotsContainer = new Div();
        dotsContainer.getElement().setProperty("innerHTML", 
            "<svg width='21' height='5' viewBox='0 0 21 5'>" +
            "<circle cx='2.5' cy='2.5' r='2.5' fill='var(--lumo-primary-color)'/>" +
            "<circle cx='10.5' cy='2.5' r='2.5' fill='var(--lumo-primary-color)'/>" + 
            "<circle cx='18.5' cy='2.5' r='2.5' fill='var(--lumo-primary-color)'/>" +
            "</svg>");
        menuDotsLayout.getStyle().set("gap", "3px");
        menuDotsLayout.add(dotsContainer);

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
                .set("z-index", "5");
        //navBar.setWidthFull(); // Ensure navBar takes full width

        Div logo3 = new Div();
        logo3.getStyle().set("display", "flex")
                .set("align-items", "center") 
                .set("justify-content", "center")
                .set("padding", "0")
                .set("margin", "0");

        logo3.setClassName("logo-on-menu");
        //logo3.getStyle().set("background-color", "var(--lumo-primary-color)");
        logo3.getElement().setProperty("innerHTML", "<svg id=\"Layer_2\" data-name=\"Layer 2\" xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 370.51 51.26\">\n" +
                "  <defs>\n" +
                "    <style>\n" +
                "      .cls-1 {\n" +
                "        stroke-width: 0px;\n" +
                "      }\n" +
                "    </style>\n" +
                "  </defs>\n" +
                "  <g id=\"Layer_1-2\" data-name=\"Layer 1\">\n" +
                "    <g>\n" +
                "      <path class=\"cls-1\" d=\"M35.12,33.01h0c0,9.67-7.9,17.57-17.56,17.57h0C7.89,50.58.05,42.77,0,33.11v-12.66c0-2.76,2.24-5,5-5h12.66c9.65.05,17.46,7.9,17.46,17.56Z\"/>\n" +
                "      <path class=\"cls-1\" d=\"M53.46,41.37h.61c2.76-.09,4.13-2,4.13-5.73v-19.01c0-.65.53-1.18,1.18-1.18h8.47c.65,0,1.18.53,1.18,1.18v19.01c0,9.83-4.99,15.01-14.98,15.55h-.61c-9.98-.54-14.98-5.73-14.98-15.55v-19.01c0-.65.53-1.18,1.18-1.18h8.47c.65,0,1.18.53,1.18,1.18v19.01c0,3.73,1.38,5.64,4.13,5.73Z\"/>\n" +
                "      <path class=\"cls-1\" d=\"M75.43,40.28c.41-.54,1.19-.65,1.71-.22,1.96,1.59,4.28,2.39,6.94,2.39,3.23,0,4.84-.95,4.84-2.85,0-.99-.89-1.73-2.68-2.2-1.27-.29-3.28-.8-6.03-1.52-2.24-.59-4.12-1.67-5.66-3.25-1.51-1.56-2.27-3.95-2.27-7.18,0-2.48.62-4.58,1.86-6.3,1.26-1.72,2.9-2.98,4.91-3.79,2.06-.84,4.3-1.25,6.74-1.25,1.38,0,2.78.19,4.2.58,1.54.47,2.91,1.06,4.13,1.76.88.49,1.7,1.03,2.44,1.6.59.46.65,1.33.12,1.86l-4.28,4.26c-.39.39-1.01.49-1.48.2-1.53-.96-3.18-1.44-4.94-1.44-2.46,0-3.69.69-3.69,2.07,0,1.24.91,2.11,2.74,2.61,2.01.54,4.03,1.05,6.06,1.52,2.24.57,4.1,1.6,5.59,3.12,1.51,1.54,2.27,3.95,2.27,7.25,0,2.46-.66,4.59-1.97,6.37-1.31,1.81-3.06,3.15-5.25,4.03-2.19.93-4.46,1.39-6.81,1.39-1.97,0-3.74-.16-5.32-.47-1.58-.29-3.06-.79-4.44-1.49-1.11-.55-2.26-1.24-3.45-2.07-.57-.4-.68-1.19-.26-1.74l3.94-5.2Z\"/>\n" +
                "      <path class=\"cls-1\" d=\"M111.19,8.54c-.5.84-1.17,1.51-2.03,2.03-.86.52-1.81.78-2.85.78s-1.98-.25-2.81-.75c-.86-.52-1.54-1.21-2.03-2.07-.52-.9-.78-1.86-.78-2.88s.25-1.99.75-2.85c.54-.88,1.23-1.57,2.07-2.07.84-.5,1.77-.75,2.81-.75s1.99.25,2.85.75c.84.5,1.51,1.19,2.03,2.07.5.86.75,1.81.75,2.85s-.25,2.04-.75,2.88ZM111.9,16.67v32.36c0,.65-.53,1.18-1.18,1.18h-8.44c-.65,0-1.18-.53-1.18-1.18V16.7c0-.65.53-1.18,1.18-1.18l8.44-.03c.65,0,1.19.53,1.19,1.18Z\"/>\n" +
                "      <path class=\"cls-1\" d=\"M128.61,23.89c-2.76.11-4.13,2.03-4.13,5.76v19.31c0,.66-.53,1.19-1.19,1.19h-8.46c-.66,0-1.19-.53-1.19-1.19v-19.31c0-9.87,4.99-15.05,14.98-15.55h.61c9.98.5,14.98,5.68,14.98,15.55v19.31c0,.66-.53,1.19-1.19,1.19h-8.46c-.66,0-1.19-.53-1.19-1.19v-19.31c0-3.73-1.38-5.65-4.13-5.76h-.61Z\"/>\n" +
                "      <path class=\"cls-1\" d=\"M190.36,40.28c.41-.54,1.18-.64,1.7-.22,1.96,1.6,4.28,2.39,6.95,2.39,3.23,0,4.85-.95,4.85-2.85,0-.99-.89-1.73-2.68-2.2-1.27-.29-3.28-.8-6.03-1.52-2.24-.59-4.12-1.67-5.66-3.25-1.51-1.56-2.27-3.95-2.27-7.18,0-2.48.62-4.58,1.86-6.3,1.26-1.72,2.9-2.98,4.91-3.79,2.06-.84,4.3-1.25,6.74-1.25,1.38,0,2.78.19,4.2.58,1.53.47,2.91,1.06,4.13,1.76.89.5,1.71,1.04,2.47,1.62.58.45.64,1.31.12,1.82l-4.3,4.28c-.39.39-1.01.49-1.48.2-1.53-.96-3.17-1.44-4.94-1.44-2.46,0-3.69.69-3.69,2.07,0,1.24.92,2.11,2.74,2.61,2.01.54,4.03,1.05,6.06,1.52,2.24.57,4.1,1.6,5.59,3.12,1.51,1.54,2.27,3.95,2.27,7.25,0,2.46-.65,4.59-1.96,6.37-1.31,1.81-3.06,3.15-5.25,4.03-2.19.93-4.46,1.39-6.81,1.39-1.97,0-3.74-.16-5.32-.47-1.58-.29-3.06-.79-4.44-1.49-1.12-.55-2.27-1.24-3.47-2.08-.55-.39-.67-1.17-.26-1.71l3.95-5.22Z\"/>\n" +
                "      <path class=\"cls-1\" d=\"M218.96,40.27c.41-.54,1.18-.64,1.7-.22,1.96,1.6,4.28,2.4,6.95,2.4,3.23,0,4.85-.95,4.85-2.85,0-.99-.89-1.73-2.68-2.2-1.27-.29-3.28-.8-6.03-1.52-2.24-.59-4.12-1.67-5.66-3.25-1.51-1.56-2.27-3.95-2.27-7.18,0-2.48.62-4.58,1.86-6.3,1.26-1.72,2.9-2.98,4.91-3.79,2.05-.84,4.3-1.25,6.74-1.25,1.38,0,2.78.19,4.2.58,1.54.47,2.91,1.06,4.13,1.76.89.5,1.72,1.04,2.47,1.62.58.45.64,1.3.12,1.82l-4.3,4.28c-.39.39-1.01.49-1.48.2-1.53-.96-3.17-1.44-4.94-1.44-2.46,0-3.69.69-3.69,2.07,0,1.24.92,2.11,2.74,2.61,2.01.54,4.03,1.05,6.07,1.52,2.24.57,4.1,1.6,5.59,3.12,1.51,1.54,2.27,3.95,2.27,7.25,0,2.46-.66,4.59-1.97,6.37-1.31,1.81-3.06,3.15-5.25,4.03-2.19.93-4.46,1.39-6.81,1.39-1.97,0-3.74-.16-5.32-.47-1.58-.29-3.06-.79-4.44-1.49-1.12-.55-2.27-1.24-3.47-2.08-.55-.39-.66-1.17-.26-1.71l3.96-5.23Z\"/>\n" +
                "      <path class=\"cls-1\" d=\"M277.82,14.09c2.76,0,5,2.24,5,5v13.31h-.1c0,10.07-8.15,18.22-18.21,18.22s-18.21-8.16-18.21-18.22,8.16-18.21,18.21-18.21v-.1h13.31Z\"/>\n" +
                "      <path class=\"cls-1\" d=\"M355.71,17.21l-16.31,32.22c-.44.88-1.7.87-2.13-.01l-15.73-32.17c-.39-.79.18-1.71,1.06-1.71l8.33-.05c.48,0,.91.28,1.1.71l6.43,14.66,6.33-14.66c.19-.44.62-.72,1.09-.72h8.77c.89,0,1.46.94,1.06,1.73Z\"/>\n" +
                "      <path class=\"cls-1\" d=\"M359.13,41.77c.59-.95,1.34-1.68,2.27-2.2.95-.54,1.95-.81,3.02-.81s2.07.27,3.01.81c.93.57,1.67,1.31,2.24,2.24.56.9.85,1.9.85,2.98,0,1.88-.63,3.41-1.9,4.61-1.17,1.1-2.78,1.7-4.39,1.66-1.05-.03-2.02-.3-2.89-.81-.95-.54-1.69-1.3-2.24-2.27-.54-.99-.81-2.06-.81-3.18s.28-2.11.85-3.02Z\"/>\n" +
                "      <path class=\"cls-1\" d=\"M182.44,25.24c-1.02-2.3-2.42-4.28-4.2-5.93-1.81-1.69-3.85-2.98-6.13-3.86-2.3-.9-4.7-1.36-7.18-1.36s-4.89.45-7.22,1.36c-2.26.88-4.29,2.18-6.1,3.9-1.78,1.67-3.18,3.64-4.2,5.9-1.02,2.24-1.52,4.68-1.52,7.32,0,3.52.88,6.7,2.64,9.52,1.72,2.82,4.05,5.05,7.01,6.67,2.89,1.6,6.02,2.41,9.38,2.41,2.48,0,4.88-.45,7.18-1.36,1.39-.57,2.77-1.35,4.14-2.35.54-.39.64-1.16.26-1.71l-4.24-6.06c-.42-.61-1.26-.69-1.84-.23-1.29,1.04-2.88,1.71-4.63,1.88-.07,0-.14,0-.21.01-.2.01-.4.03-.61.03,0,0-.02,0-.03,0,0,0-.02,0-.03,0-1.47,0-2.82-.34-4.05-1.02-2.76-1.47-4.64-4.37-4.64-7.72,0-3.65,2.23-6.77,5.4-8.08.06-.03.13-.05.2-.07.23-.09.45-.17.69-.24.16-.05.32-.09.49-.12.15-.03.3-.06.46-.09.17-.03.34-.06.51-.08.24-.03.49-.04.74-.05.07,0,.13-.01.2-.01,0,0,.02,0,.02,0,.01,0,.02,0,.03,0,4.83,0,8.74,3.91,8.74,8.74,0,.43-.04.84-.1,1.25h6.04v-.07h4.2l.07-.58c.02-.14.03-.26.03-.37v-.3c0-2.6-.51-5.04-1.52-7.32Z\"/>\n" +
                "      <path class=\"cls-1\" d=\"M322.2,25.24c-1.02-2.3-2.42-4.28-4.2-5.93-1.81-1.69-3.85-2.98-6.13-3.86-2.3-.9-4.7-1.36-7.18-1.36s-4.89.45-7.22,1.36c-2.26.88-4.29,2.18-6.1,3.9-1.78,1.67-3.19,3.64-4.2,5.9-1.02,2.24-1.53,4.68-1.53,7.32,0,3.52.88,6.7,2.64,9.52,1.72,2.82,4.05,5.05,7.01,6.67,2.89,1.6,6.02,2.41,9.38,2.41,2.48,0,4.88-.45,7.18-1.36,1.41-.57,2.8-1.37,4.18-2.39.51-.38.61-1.11.25-1.63l-4.33-6.2c-.4-.57-1.2-.66-1.74-.22-.67.55-1.43,1.01-2.25,1.35-.07.03-.15.05-.22.08-.16.06-.32.13-.49.18-.81.26-1.67.39-2.58.39-.3,0-.6-.02-.89-.05,0,0-.01,0-.02,0-.28-.03-.56-.07-.84-.13-.06-.01-.12-.03-.19-.05-.2-.05-.4-.09-.59-.15-3.59-1.09-6.21-4.42-6.21-8.37s2.76-7.45,6.5-8.44c.03,0,.06-.01.08-.02.29-.07.58-.13.88-.17.08-.01.16-.03.24-.04.34-.04.68-.07,1.03-.07s.69.03,1.03.07c.08,0,.16.02.24.04.3.04.59.1.88.18.03,0,.05.01.08.02,3.74.99,6.51,4.39,6.51,8.44,0,.43-.04.84-.1,1.25h6.1v-.07h4.2l.07-.58c.02-.14.03-.26.03-.37v-.3c0-2.6-.51-5.04-1.53-7.32Z\"/>\n" +
                "    </g>\n" +
                "  </g>\n" +
                "</svg>");

        VerticalLayout navSection1 = new VerticalLayout();
        navSection1.getStyle().set("height", "50px");
        navSection1.setWidthFull();
        navSection1.add(logo3);
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

        HorizontalLayout copyRightLayout = new HorizontalLayout();
        copyRightLayout.getStyle().set("height", "fit-content")
                .set("padding", "0")
                .set("height", "25px")
                .set("font-size", "10px")
                .set("gap", "5px")
                .set("align-items", "center")
                .set("justify-content", "center")
                .set("margin", "0");
        copyRightLayout.setWidthFull();

        Paragraph enterpriseNumber = new Paragraph("2024/470976/07");
        Paragraph copyRight = new Paragraph("\u00A9 " + java.time.Year.now().getValue() + ". ");
        copyRight.getStyle().set("margin", "0").set("padding", "0");
        Paragraph copyRight2 = new Paragraph("All Rights Reserved.");
        copyRight2.getStyle().set("margin", "0").set("padding", "0"); 
        copyRightLayout.add(enterpriseNumber, copyRight, copyRight2);
        menuContent.add(navSection1, navSection2, copyRightLayout);
        
        // Adding navBar and hello layout to the main layout
        add(navBar);
    }
}
