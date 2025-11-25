package com.businessdev.application.views.welcome;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("businessdev.")
@Route(value = "", layout = MainLayout.class)
public class WelcomeView extends VerticalLayout {

    public WelcomeView() {
	VerticalLayout welcomeLayout = new VerticalLayout(banner1(), banner2(), banner3());
	welcomeLayout.getStyle().set("margin-top","-15px")
	    .set("padding","0")
	    .set("scroll-snap-type", "y mandatory")
	    .set("overflow-y", "scroll")
            .set("overflow-x", "hidden")
	    .set("max-height", "100dvh")
	    .set("gap", "32px");
	add(welcomeLayout);
	
	// Initialize simple banner animations
	initializeBannerAnimations();
    }

    private void initializeBannerAnimations() {
        getElement().executeJs("""
            // Simple intersection observer for individual elements
            const observer = new IntersectionObserver((entries) => {
                entries.forEach(entry => {
                    if (entry.isIntersecting) {
                        // Element is in view, animate it in
                        entry.target.style.opacity = '1';
                        entry.target.classList.add('animate-in');
                    } else {
                        // Element is out of view, hide it
                        entry.target.style.opacity = '0';
                        entry.target.classList.remove('animate-in');
                    }
                });
            }, {
                root: null,
                rootMargin: '0px 0px -10% 0px', // Trigger when element is 10% from bottom
                threshold: 0.1 // Trigger when 10% of element is visible
            });
            
            // Observe all animated elements
            const animatedElements = document.querySelectorAll('.scroll-animate, .scroll-animate-text, .scroll-animate-image');
            animatedElements.forEach((element, index) => {
                // Set initial state
                element.style.opacity = '0';
                element.style.transition = 'opacity 0.8s ease-in-out';
                
                // Add staggered delay
                element.style.transitionDelay = (index * 0.1) + 's';
                
                // Start observing
                observer.observe(element);
            });
            
            console.log('Observing', animatedElements.length, 'animated elements');
            """);
    }

    //...nav was here

    private HorizontalLayout banner1(){
        //setSizeFull();
        H1 heading = new H1("Business Lives Matter");
        heading.getStyle().set("font-size", "4.5rem")
            .set("color", "var(--lumo-body-text-color)");
        heading.addClassName("scroll-animate-text");
        heading.addClassName("welcome-heading-text");
        
        Paragraph paragraph = new Paragraph("Our mission is to become a leading company in connecting businesses and driving innovation by providing best solutions to everyday business problems.");
        paragraph.getStyle().set("max-width", "500px").set("font-size", "16px");
        paragraph.addClassName("scroll-animate-text");
        
        Button moreInfo = new Button("Learn more...", e -> getUI().ifPresent(ui -> ui.navigate("about")));
        moreInfo.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        moreInfo.getStyle()
            .set("cursor", "pointer")
            .set("font-weight", "500")
            .set("font-size", "12px")
            .set("padding", "10px 30px")
            .set("border-radius", "5px");
        moreInfo.addClassName("scroll-animate");
        moreInfo.addClassName("button-link");
        Image image = new Image("https://illustrations.popsy.co/gray/work-party.svg", "Two men at work party");
        image.getStyle().set("width", "100%")
                .set("max-width", "520px")
                .set("height", "auto")
                .set("object-position", "center");
        image.addClassName("scroll-animate-image-1");
	Div sectionOne  = new Div(heading, paragraph, moreInfo);
	sectionOne.getStyle().set("padding", "0")
                .set("flex", "1 1 400px")
                .set("display", "flex")
                .set("align-items", "start")
                .set("justify-content", "center")
                .set("flex-direction", "column")
	            .set("margin", "0");
	Div sectionTwo  = new Div(image);
	sectionTwo.getStyle().set("padding", "0")
                .set("flex", "2 1 400px")
                .set("display", "flex")
                .set("align-items", "center")
                .set("justify-content", "center")
                .set("overflow", "hidden")
	            .set("margin", "0");
	HorizontalLayout banner = new HorizontalLayout(sectionOne, sectionTwo);
	banner.getStyle().set("flex-wrap", "wrap-reverse")
                .set("justify-content", "center")
                .set("flex-direction", "row")
                .set("width", "100%")
                .set("min-height", "100dvh")
                .set("scroll-snap-align", "start")                                                           
                .set("padding", "20px")
                .set("overflow", "hidden")
                .set("margin", "0");
	banner.getElement().setAttribute("data-banner-section", "0");
	return banner;
    }

    private HorizontalLayout banner2(){
	H1 heading = new H1("No To Outdated Infrustructure");
    heading.getStyle().set("font-size", "4.5rem")
            .set("color", "var(--lumo-body-text-color)");
    heading.addClassName("scroll-animate-text");
    heading.addClassName("welcome-heading-text");
    
	Paragraph paragraph = new Paragraph("Businesses are seeking innovative ways to remain competitive and adapt to technological advancements. We collaborate with professional individuals from all over the world to make this possible.");
        paragraph.getStyle().set("max-width", "500px").set("font-size", "16px");
        paragraph.addClassName("scroll-animate-text");
        
        Button moreInfo = new Button("Learn more...", e -> getUI().ifPresent(ui -> ui.navigate("about")));
        moreInfo.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        moreInfo.getStyle()
            .set("cursor", "pointer")
            .set("font-weight", "500")
            .set("font-size", "16px")
            .set("padding", "10px 30px")
            .set("border-radius", "5px");
        moreInfo.addClassName("scroll-animate");
        
	Image image = new Image("https://illustrations.popsy.co/gray/designer.svg", "Man in home office");
	image.getStyle().set("width", "100%")
                .set("max-width", "520px")
                .set("height", "auto")
                .set("object-position", "center");
        image.addClassName("scroll-animate-image");
	Div sectionOne  = new Div(heading, paragraph);
	sectionOne.getStyle().set("padding", "0")
                .set("flex", "1 1 400px")
                .set("display", "flex")
                .set("align-items", "start")
                .set("justify-content", "center")
                .set("flex-direction", "column")
	            .set("margin", "0");
	Div sectionTwo  = new Div(image);
	sectionTwo.getStyle().set("padding", "0")
                .set("flex", "2 1 400px")
                .set("display", "flex")
                .set("align-items", "center")
                .set("justify-content", "center")
                .set("overflow", "hidden")
	            .set("margin", "0");
	HorizontalLayout banner = new HorizontalLayout(sectionOne, sectionTwo);
	banner.getStyle().set("flex-wrap", "wrap-reverse")
                .set("justify-content", "center")
                .set("flex-direction", "row")
                .set("width", "100%")
                .set("min-height", "100dvh")
                .set("scroll-snap-align", "start")                                                           
                .set("padding", "20px")
                .set("overflow", "hidden")
                .set("margin", "0");
	banner.getElement().setAttribute("data-banner-section", "1");
	return banner;
    }

    private HorizontalLayout banner3(){
	H1 heading = new H1("Solutions Architecture");
    heading.getStyle().set("font-size", "4.5rem")
            .set("color", "var(--lumo-body-text-color)");
    heading.addClassName("scroll-animate-text");
    heading.addClassName("welcome-heading-text");

	Paragraph paragraph = new Paragraph("We aim to improve the way businesses innovate and solve problems. Identifying and analysing business problems and specialising in modern business practices.");
	paragraph.getStyle().set("max-width", "500px").set("font-size", "16px");
	paragraph.addClassName("scroll-animate-text");
	
        Button moreInfo = new Button("Learn more...", e -> getUI().ifPresent(ui -> ui.navigate("about")));
        moreInfo.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        moreInfo.getStyle()
            .set("cursor", "pointer")
            .set("font-weight", "500")
            .set("font-size", "16px")
            .set("padding", "10px 30px")
            .set("border-radius", "5px");
        moreInfo.addClassName("scroll-animate");

        
	Image image = new Image("https://illustrations.popsy.co/gray/keynote-presentation.svg", "Man in home office");
	image.getStyle().set("width", "100%")
                .set("max-width", "520px")
                .set("height", "auto")
                .set("object-position", "center");
        image.addClassName("scroll-animate-image");
	Div sectionOne  = new Div(heading, paragraph);
	sectionOne.getStyle().set("padding", "0")
                .set("flex", "1 1 400px")
                .set("display", "flex")
                .set("align-items", "start")
                .set("justify-content", "center")
                .set("flex-direction", "column")
	            .set("margin", "0");
	Div sectionTwo  = new Div(image);
	sectionTwo.getStyle().set("padding", "0")
                .set("flex", "2 1 400px")
                .set("display", "flex")
                .set("align-items", "center")
                .set("justify-content", "center")
                .set("overflow", "hidden")
	            .set("margin", "0");
	HorizontalLayout banner = new HorizontalLayout(sectionOne, sectionTwo);
	banner.getStyle().set("flex-wrap", "wrap-reverse")
                .set("justify-content", "center")
                .set("flex-direction", "row")
                .set("width", "100%")
                .set("min-height", "100dvh")
                .set("scroll-snap-align", "start")                                                           
                .set("padding", "20px")
                .set("overflow", "hidden")
                .set("margin", "0");
	banner.getElement().setAttribute("data-banner-section", "2");
	return banner;
    }
}
