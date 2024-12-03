package com.businessdev.application.views.welcome;

import com.businessdev.application.views.components.MainNavigation;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;

public class MainLayout extends VerticalLayout implements RouterLayout {
    public MainLayout(){
        addClassName("app-layout");
        MainNavigation nav = new MainNavigation();
        add(nav);
    }
}
