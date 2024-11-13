package com.businessdev.application.views.welcome;

import com.businessdev.application.views.components.MainNavigation;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;

//@CssImport("./styles.css")  // Ensure this line is here
public class MainLayout extends VerticalLayout implements RouterLayout {
    public MainLayout(){
        addClassName("app-layout");
//        getElement().getStyle().set("padding", "0");  // Remove any padding
//        getElement().getStyle().set("padding-top", "0");
//        getElement().getStyle().set("padding-bottom", "0");
//        getElement().getStyle().set("margin", "0");
//        getElement().getStyle().set("margin-top", "0");
//        getElement().getStyle().set("margin-bottom", "0");
        MainNavigation nav = new MainNavigation();
        add(nav);
    }
}
