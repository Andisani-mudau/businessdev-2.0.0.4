package com.businessdev.application.views.welcome;

import com.businessdev.application.views.welcome.MainLayout;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

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
        Paragraph text = new Paragraph("Come back later!");

        imageContainer.add(image);
        mainContainter.add(imageContainer, text);
        commingSoon.add(mainContainter);
        return commingSoon;
    }
}   
