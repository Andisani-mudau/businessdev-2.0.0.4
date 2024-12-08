package com.businessdev.application.views.services;

import com.businessdev.application.views.welcome.MainLayout;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("services")
@Route(value = "services", layout = MainLayout.class)
public class ServicesView extends VerticalLayout {
    public ServicesView(){
    }
    
    private VerticalLayout servicesLayout(){
        VerticalLayout servicesLayout = new VerticalLayout();
        VerticalLayout mainContainer = new VerticalLayout();
        HorizontalLayout a = new HorizontalLayout();
        VerticalLayout aa = new VerticalLayout();
        HorizontalLayout aaa = new HorizontalLayout();
        HorizontalLayout aab = new HorizontalLayout();
        VerticalLayout aaba = new VerticalLayout();
        VerticalLayout aabb = new VerticalLayout();
        HorizontalLayout ab = new HorizontalLayout();
        VerticalLayout aba = new VerticalLayout();
        VerticalLayout abb = new VerticalLayout();
        HorizontalLayout b = new HorizontalLayout();
        return servicesLayout;
    }
}   
