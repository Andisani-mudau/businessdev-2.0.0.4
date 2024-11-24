package com.businessdev.application.views.welcome;

import com.businessdev.application.views.welcome.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Height;

@PageTitle("services")
@Route(value = "services", layout = MainLayout.class)
public class ServicesView extends VerticalLayout {
    public ServicesView(){
        add(commingSoon());
    }
    
    private VerticalLayout commingSoon(){
        VerticalLayout commingSoon = new VerticalLayout();
        commingSoon.setHeight("calc(100dvh - 30px)");
        commingSoon.setAlignItems(Alignment.CENTER);
        commingSoon.setJustifyContentMode(JustifyContentMode.CENTER);
        VerticalLayout mainContainter = new VerticalLayout();
        mainContainter.setSpacing(true);
        mainContainter.setMaxWidth("400px");
        mainContainter.setAlignItems(Alignment.CENTER);
        mainContainter.setJustifyContentMode(JustifyContentMode.CENTER);
        
        
        VerticalLayout imageContainer = new VerticalLayout();
        imageContainer.setHeight("400px");
        Image image = new Image("https://illustrations.popsy.co/amber/page-under-construction.svg", "Page under construcion");
        image.setWidth("100%");
        Paragraph text = new Paragraph("Still under construction.");

        imageContainer.add(image);
        mainContainter.add(imageContainer, text);
        commingSoon.add(mainContainter);
        return commingSoon;
    }
}   
