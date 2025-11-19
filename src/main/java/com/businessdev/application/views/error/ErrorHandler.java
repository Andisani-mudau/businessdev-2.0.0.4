package com.businessdev.application.views.error;

import com.businessdev.application.views.welcome.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.NotFoundException;
import com.vaadin.flow.router.ParentLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@ParentLayout(MainLayout.class)
public class ErrorHandler extends VerticalLayout implements com.vaadin.flow.router.HasErrorParameter<NotFoundException> {

    @Override
    public int setErrorParameter(BeforeEnterEvent event, ErrorParameter<NotFoundException> parameter) {
        removeAll();
        
        setHeight("calc(100dvh - 30px)");
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        
        VerticalLayout mainContainer = new VerticalLayout();
        mainContainer.setSpacing(true);
        mainContainer.setMaxWidth("400px");
        mainContainer.setAlignItems(Alignment.CENTER);
        mainContainer.setJustifyContentMode(JustifyContentMode.CENTER);
        
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
        mainContainer.add(imageContainer, text, goHomeButton);
        add(mainContainer);
        
        return 404;
    }
}
