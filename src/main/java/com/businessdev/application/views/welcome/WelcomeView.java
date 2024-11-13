package com.businessdev.application.views.welcome;

import com.businessdev.application.views.welcome.MainLayout;
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
@Route(value = " ", layout = MainLayout.class)
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
        //navBar();
	//banner();
	VerticalLayout welcomeLayout = new VerticalLayout(banner1(), banner2(), banner3(), banner4());
	welcomeLayout.getStyle().set("margin-top","-15px")
	    .set("padding","0")
	    .set("scroll-snap-type", "y mandatory")
	    .set("overflow-y", "scroll")
            .set("overflow-x", "hidden")
	    .set("max-height", "100dvh")
	    .set("gap", "32px");
	    
	add(welcomeLayout);
    }

    //...nav was here

    private HorizontalLayout banner1(){
        H1 heading = new H1("Business Lives Matters");
        Paragraph paragraph = new Paragraph("Our mission is to become a leading company in connecting businesses and driving innovation by providing best solutions to everyday business problems.");
        paragraph.getStyle().set("max-width", "500px");
        Button moreInfo = new Button("Learn more...");
        moreInfo.getStyle().set("cursor","pointer");
        Image image = new Image("https://illustrations.popsy.co/gray/home-office.svg", "Man in home office");
        image.getStyle().set("width", "100%")
                .set("max-width", "520px")
                .set("height", "auto")
                .set("object-position", "center");
	Div sectionOne  = new Div(heading, paragraph, moreInfo);
	sectionOne.getStyle().set("padding", "0")
                .set("flex", "1")
                .set("display", "flex")
                .set("align-items", "start")
                .set("justify-content", "center")
                .set("flex-direction", "column")
                .set("min-width", "400px")
	        .set("margin", "0");
	Div sectionTwo  = new Div(image);
	sectionTwo.getStyle().set("padding", "0")
                .set("flex", "2")
                .set("display", "flex")
                .set("align-items", "center")
                .set("justify-content", "center")
                .set("min-width", "400px")
                .set("overflow", "hidden")
	        .set("margin", "0");
	HorizontalLayout banner = new HorizontalLayout(sectionOne, sectionTwo);
	banner.getStyle().set("flex-wrap", "wrap-reverse")
                .set("justify-content", "center")
                .set("flex-direction", "row")
                .set("width", "calc(100% - 20px)")
                .set("min-height", "100dvh")
                .set("scroll-snap-align", "start")                                                           
                .set("padding", "20px")
                .set("overflow", "hidden")
                .set("margin", "0");
	return banner;
    }

    private HorizontalLayout banner2(){
	H1 heading = new H1("No To Outdated Infrustructure");
	Paragraph paragraph = new Paragraph("Businesses are seeking innovative ways to remain competitive and adapt to technological advancements. We collaborate with professional individuals from all over the world to make this possible.");
        paragraph.getStyle().set("max-width", "500px");
        Button moreInfo = new Button("Learn more...");
        moreInfo.getStyle().set("cursor","pointer");
	Image image = new Image("https://illustrations.popsy.co/gray/designer.svg", "Man in home office");
	image.getStyle().set("width", "100%")
                .set("max-width", "520px")
                .set("height", "auto")
                .set("object-position", "center");
	Div sectionOne  = new Div(heading, paragraph, moreInfo);
	sectionOne.getStyle().set("padding", "0")
                .set("flex", "1")
                .set("display", "flex")
                .set("align-items", "start")
                .set("justify-content", "center")
                .set("flex-direction", "column")
                .set("min-width", "400px")
	        .set("margin", "0");
	Div sectionTwo  = new Div(image);
	sectionTwo.getStyle().set("padding", "0")
                .set("flex", "2")
                .set("display", "flex")
                .set("align-items", "center")
                .set("justify-content", "center")
                .set("min-width", "400px")
                .set("overflow", "hidden")
	        .set("margin", "0");
	HorizontalLayout banner = new HorizontalLayout(sectionOne, sectionTwo);
	banner.getStyle().set("flex-wrap", "wrap-reverse")
                .set("justify-content", "center")
                .set("flex-direction", "row")
                .set("width", "calc(100% - 20px)")
                .set("min-height", "100dvh")
                .set("scroll-snap-align", "start")                                                           
                .set("padding", "20px")
                .set("overflow", "hidden")
                .set("margin", "0");
	return banner;
    }

    private HorizontalLayout banner3(){
	H1 heading = new H1("Solutions Architecture");
	Paragraph paragraph = new Paragraph("We aim to improve the way businesses innovate and solve problems. Identifying and analysing business problems and specialising modern business practices.");
	paragraph.getStyle().set("max-width", "500px");
        Button moreInfo = new Button("Learn more...");
        moreInfo.getStyle().set("cursor","pointer");
	Image image = new Image("https://illustrations.popsy.co/gray/keynote-presentation.svg", "Man in home office");
	image.getStyle().set("width", "100%")
                .set("max-width", "520px")
                .set("height", "auto")
                .set("object-position", "center");
	Div sectionOne  = new Div(heading, paragraph, moreInfo);
	sectionOne.getStyle().set("padding", "0")
                .set("flex", "1")
                .set("display", "flex")
                .set("align-items", "start")
                .set("justify-content", "center")
                .set("flex-direction", "column")
                .set("min-width", "400px")
	        .set("margin", "0");
	Div sectionTwo  = new Div(image);
	sectionTwo.getStyle().set("padding", "0")
                .set("flex", "2")
                .set("display", "flex")
                .set("align-items", "center")
                .set("justify-content", "center")
                .set("min-width", "400px")
                .set("overflow", "hidden")
	        .set("margin", "0");
	HorizontalLayout banner = new HorizontalLayout(sectionOne, sectionTwo);
	banner.getStyle().set("flex-wrap", "wrap-reverse")
                .set("justify-content", "center")
                .set("flex-direction", "row")
                .set("width", "calc(100% - 20px)")
                .set("min-height", "100dvh")
                .set("scroll-snap-align", "start")                                                           
                .set("padding", "20px")
                .set("overflow", "hidden")
                .set("margin", "0");
	return banner;
    }

    private HorizontalLayout banner4(){
	H1 heading = new H1("Graduate Support");
	Paragraph paragraph = new Paragraph("We also focus on job placement and entrepreneurship support for recent graduates. We support recent graduates in securing employment or starting their own businesses.");
	paragraph.getStyle().set("max-width", "500px");
        Button moreInfo = new Button("Learn more...");
        moreInfo.getStyle().set("cursor","pointer");
	Image image = new Image("https://illustrations.popsy.co/gray/student-going-to-school.svg", "Man in home office");
	image.getStyle().set("width", "100%")
                .set("max-width", "520px")
                .set("height", "auto")
                .set("object-position", "center");
	Div sectionOne  = new Div(heading, paragraph, moreInfo);
	sectionOne.getStyle().set("padding", "0")
                .set("flex", "1")
                .set("display", "flex")
                .set("align-items", "start")
                .set("justify-content", "center")
                .set("flex-direction", "column")
                .set("min-width", "400px")
	        .set("margin", "0");
	Div sectionTwo  = new Div(image);
	sectionTwo.getStyle().set("padding", "0")
                .set("flex", "2")
                .set("display", "flex")
                .set("align-items", "center")
                .set("justify-content", "center")
                .set("min-width", "400px")
                .set("overflow", "hidden")
	        .set("margin", "0");
	HorizontalLayout banner = new HorizontalLayout(sectionOne, sectionTwo);
	banner.getStyle().set("flex-wrap", "wrap-reverse")
                .set("justify-content", "center")
                .set("flex-direction", "row")
                .set("width", "calc(100% - 20px)")
                .set("min-height", "100dvh")
                .set("scroll-snap-align", "start")                                                           
                .set("padding", "20px")
                .set("overflow", "hidden")
                .set("margin", "0");
	return banner;
    }
}
