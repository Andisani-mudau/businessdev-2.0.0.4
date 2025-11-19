package com.businessdev.application.views.welcome;

import com.businessdev.application.views.survey.*;
import com.businessdev.application.views.welcome.MainLayout;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Div; 
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;


@PageTitle("Page Not Found")
public class NotFound extends VerticalLayout {
    public NotFound(){
        add(notFound());
    }
    
    private VerticalLayout notFound(){
        VerticalLayout notFound = new VerticalLayout();
        notFound.setHeight("calc(100dvh - 30px)");
        notFound.setAlignItems(Alignment.CENTER);
        notFound.setJustifyContentMode(JustifyContentMode.CENTER);
        VerticalLayout mainContainter = new VerticalLayout();
        mainContainter.setSpacing(true);
        mainContainter.setMaxWidth("400px");
        mainContainter.setAlignItems(Alignment.CENTER);
        mainContainter.setJustifyContentMode(JustifyContentMode.CENTER);
        
        VerticalLayout imageContainer = new VerticalLayout();
        imageContainer.setHeight("400px");
        Image image = new Image("https://illustrations.popsy.co/amber/crashed-error.svg", "Page not found");
        image.setWidth("100%");
        Paragraph text = new Paragraph("Oops! You can't land here.");
        text.getStyle().set("text-align", "center").set("font-size", "18px");
        
        Button goHomeButton = new Button("Go Home", e -> getUI().ifPresent(ui -> ui.navigate("")));
        goHomeButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        goHomeButton.getStyle()
            .set("cursor", "pointer")
            .set("font-weight", "500")
            .set("font-size", "16px")
            .set("padding", "10px 30px")
            .set("border-radius", "5px");
        
        imageContainer.add(image);
        mainContainter.add(imageContainer, text, goHomeButton);
        notFound.add(mainContainter);

        return notFound;
    }
}