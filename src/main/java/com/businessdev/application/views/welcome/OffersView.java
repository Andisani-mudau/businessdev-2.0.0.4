package com.businessdev.application.views.welcome;

import com.businessdev.application.views.welcome.MainLayout;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.Main;

@PageTitle("offers")
@Route(value = "offers", layout = MainLayout.class)
public class OffersView extends VerticalLayout {
    private void addPricingButtonListener(Button button, String service) {
        button.addClickListener(e -> UI.getCurrent().navigate("pricing?service=" + service));
    }

    public OffersView() {
        add(sectionOne());
    }

    private HorizontalLayout sectionOne(){
        setClassName("sectionTwo");
        Main main = new Main();
        main.addClassName("main");
        main.addClassName("flow");

        H1 mainHeading = new H1("");
        mainHeading.addClassName("main__heading");

        Div mainCards = new Div();
        mainCards.addClassNames("main__cards", "cards");

        Div cardsInner = new Div();
        cardsInner.addClassName("cards__inner");

        // Web Development Card
        Div webDevCard = new Div();
        webDevCard.addClassNames("cards__card", "card", "offersCard");
        Image webDevImage = new Image("https://illustrations.popsy.co/gray/web-design.svg", "Web App development");
        webDevImage.setWidth("100%");
        H2 webDevHeading = new H2("Web Application Development");
        webDevHeading.addClassNames("card__heading", "offer-heading");
        VerticalLayout content_container = new VerticalLayout(webDevImage, webDevHeading);
        
        VerticalLayout wrapper = new VerticalLayout();
        Button pricing = new Button("View Pricing");
        pricing.addClassName("pricing");
        pricing.setWidth("100%");
        pricing.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addPricingButtonListener(pricing, "Web Application Development");
        HorizontalLayout tools = new HorizontalLayout();
        tools.addClassName("tools-container");
        
        Span html = new Span("HTML");
        Span css = new Span("CSS");
        Span js = new Span("JavaScript"); 
        Span ts = new Span("TypeScript");
        Span java = new Span("Java");
        Span spring = new Span("Spring");
        Span springBoot = new Span("Spring-boot");
        Span vaadin = new Span("Vaadin");
        Span react = new Span("React");
        Span vue = new Span("Vue");
        Span angular = new Span("Angular");
        Span node = new Span("Node.js");
        Span mongodb = new Span("MongoDB");
        
        tools.add(html, css, js, ts, java, spring, springBoot, vaadin, react, vue, angular, node, mongodb);
        tools.setSpacing(true);
        wrapper.add(tools, pricing);
        webDevCard.add(content_container, wrapper);

        // App Development Card
        Div appDevCard = new Div();
        appDevCard.addClassNames("cards__card", "card", "offersCard");
        Image appDevImage = new Image("https://illustrations.popsy.co/gray/app-launch.svg", "Mobile App development");
        appDevImage.setWidth("100%");
        H2 appDevHeading = new H2("Mobile Application Development");
        appDevHeading.addClassNames("card__heading", "offer-heading");
        VerticalLayout appDevContent = new VerticalLayout(appDevImage, appDevHeading);
        
        VerticalLayout appDevWrapper = new VerticalLayout();
        Button appDevPricing = new Button("View Pricing");
        appDevPricing.addClassName("pricing");
        appDevPricing.setWidth("100%");
        appDevPricing.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addPricingButtonListener(appDevPricing, "Mobile Application Development");
        HorizontalLayout appDevTools = new HorizontalLayout();
        appDevTools.addClassName("tools-container");
        
        Span swift = new Span("Swift");
        Span kotlin = new Span("Kotlin");
        Span flutter = new Span("Flutter");
        Span reactNative = new Span("React Native");
        Span xamarin = new Span("Xamarin");
        Span androidStudio = new Span("Android Studio");
        
        appDevTools.add(swift, kotlin, flutter, reactNative, xamarin, androidStudio);
        appDevTools.setSpacing(true);
        appDevWrapper.add(appDevTools, appDevPricing);
        appDevCard.add(appDevContent, appDevWrapper);

        // UI Design Card
        Div uiDesignCard = new Div();
        uiDesignCard.addClassNames("cards__card", "card", "offersCard");
        Image uiDesignImage = new Image("https://illustrations.popsy.co/gray/designer.svg", "UI/UX Design");
        uiDesignImage.setWidth("100%");
        H2 uiDesignHeading = new H2("UI/UX Design");
        uiDesignHeading.addClassNames("card__heading", "offer-heading");
        VerticalLayout uiDesignContent = new VerticalLayout(uiDesignImage, uiDesignHeading);
        
        VerticalLayout uiDesignWrapper = new VerticalLayout();
        Button uiDesignPricing = new Button("View Pricing");
        uiDesignPricing.addClassName("pricing");
        uiDesignPricing.setWidth("100%");
        uiDesignPricing.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addPricingButtonListener(uiDesignPricing, "UI/UX Design");
        HorizontalLayout uiDesignTools = new HorizontalLayout();
        uiDesignTools.addClassName("tools-container");
        
        Span figma = new Span("Figma");
        Span sketch = new Span("Sketch");
        Span xd = new Span("Adobe XD");
        Span ai = new Span("Illustrator");
        Span ps = new Span("Photoshop");
        Span principle = new Span("Principle");
        Span invision = new Span("InVision");
        Span zeplin = new Span("Zeplin");
        
        uiDesignTools.add(figma, sketch, xd, ai, ps, principle, invision, zeplin);
        uiDesignTools.setSpacing(true);
        uiDesignWrapper.add(uiDesignTools, uiDesignPricing);
        uiDesignCard.add(uiDesignContent, uiDesignWrapper);

        // Logo Design Card
        Div logoDesignCard = new Div();
        logoDesignCard.addClassNames("cards__card", "card", "offersCard");
        Image logoDesignImage = new Image("https://illustrations.popsy.co/gray/podcast-listening.svg", "Brand Identity Design");
        logoDesignImage.setWidth("100%");
        H2 logoDesignHeading = new H2("Brand Identity Design");
        logoDesignHeading.addClassNames("card__heading", "offer-heading");
        VerticalLayout logoDesignContent = new VerticalLayout(logoDesignImage, logoDesignHeading);
        
        VerticalLayout logoDesignWrapper = new VerticalLayout();
        Button logoDesignPricing = new Button("View Pricing");
        logoDesignPricing.addClassName("pricing");
        logoDesignPricing.setWidth("100%");
        logoDesignPricing.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addPricingButtonListener(logoDesignPricing, "Brand Identity Design");
        HorizontalLayout logoDesignTools = new HorizontalLayout();
        logoDesignTools.addClassName("tools-container");
        
        Span illustrator = new Span("Illustrator");
        Span photoshop = new Span("Photoshop");
        Span indesign = new Span("InDesign");
        Span afterEffects = new Span("After Effects");
        Span premiere = new Span("Premiere Pro");
        Span lightroom = new Span("Lightroom");
        Span dimension = new Span("Dimension");
        
        logoDesignTools.add(illustrator, photoshop, indesign, afterEffects, premiere, lightroom, dimension);
        logoDesignTools.setSpacing(true);
        logoDesignWrapper.add(logoDesignTools, logoDesignPricing);
        logoDesignCard.add(logoDesignContent, logoDesignWrapper);

        // Graphic Design Card
        Div graphicDesignCard = new Div();
        graphicDesignCard.addClassNames("cards__card", "card", "offersCard");
        Image graphicDesignImage = new Image("https://illustrations.popsy.co/gray/graphic-design.svg", "Graphic Design");
        graphicDesignImage.setWidth("100%");
        H2 graphicDesignHeading = new H2("Graphic Design");
        graphicDesignHeading.addClassNames("card__heading", "offer-heading");
        VerticalLayout graphicDesignContent = new VerticalLayout(graphicDesignImage, graphicDesignHeading);
        
        VerticalLayout graphicDesignWrapper = new VerticalLayout();
        Button graphicDesignPricing = new Button("View Pricing");
        graphicDesignPricing.addClassName("pricing");
        graphicDesignPricing.setWidth("100%");
        graphicDesignPricing.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addPricingButtonListener(graphicDesignPricing, "Graphic Design");
        HorizontalLayout graphicDesignTools = new HorizontalLayout();
        graphicDesignTools.addClassName("tools-container");
        
        Span canva = new Span("Canva");
        Span corelDraw = new Span("CorelDraw");
        graphicDesignTools.add(
            new Span("Illustrator"), 
            new Span("Photoshop"), 
            canva, 
            corelDraw,
            new Span("Figma"),
            new Span("Sketch"),
            new Span("InDesign"),
            new Span("Affinity Designer"),
            new Span("XD"),
            new Span("GIMP")
        );
        graphicDesignTools.setSpacing(true);
        graphicDesignWrapper.add(graphicDesignTools, graphicDesignPricing);
        graphicDesignCard.add(graphicDesignContent, graphicDesignWrapper);

        // Architecture Design Card
        Div architectureCard = new Div();
        architectureCard.addClassNames("cards__card", "card", "offersCard");
        Image architectureImage = new Image("https://illustrations.popsy.co/gray/abstract-art-6.svg", "Software Architecture");
        architectureImage.setWidth("100%");
        H2 architectureHeading = new H2("Construction Architecture");
        architectureHeading.addClassNames("card__heading", "offer-heading");
        VerticalLayout architectureContent = new VerticalLayout(architectureImage, architectureHeading);
        
        VerticalLayout architectureWrapper = new VerticalLayout();
        Button architecturePricing = new Button("View Pricing");
        architecturePricing.addClassName("pricing");
        architecturePricing.setWidth("100%");
        architecturePricing.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addPricingButtonListener(architecturePricing, "Construction Architecture");
        HorizontalLayout architectureTools = new HorizontalLayout();
        architectureTools.addClassName("tools-container");
        
        Span autocad = new Span("AutoCAD");
        Span revit = new Span("Revit");
        Span sketchup = new Span("SketchUp");
        Span archicad = new Span("ArchiCAD");
        Span vectorworks = new Span("Vectorworks");
        Span rhino = new Span("Rhino3D");
        Span microstation = new Span("MicroStation");
        Span chiefarchitect = new Span("Chief Architect");
        
        architectureTools.add(autocad, revit, sketchup, archicad, vectorworks, rhino, microstation, chiefarchitect);
        architectureTools.setSpacing(true);
        architectureWrapper.add(architectureTools, architecturePricing);
        architectureCard.add(architectureContent, architectureWrapper);

        // 3D Modelling Card
        Div modellingCard = new Div();
        modellingCard.addClassNames("cards__card", "card", "offersCard");
        Image modellingImage = new Image("https://illustrations.popsy.co/gray/creative-work.svg", "3D Modeling");
        modellingImage.setWidth("100%");
        H2 modellingHeading = new H2("3D Modeling");
        modellingHeading.addClassNames("card__heading", "offer-heading");
        VerticalLayout modellingContent = new VerticalLayout(modellingImage, modellingHeading);
        
        VerticalLayout modellingWrapper = new VerticalLayout();
        Button modellingPricing = new Button("View Pricing");
        modellingPricing.addClassName("pricing");
        modellingPricing.setWidth("100%");
        modellingPricing.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addPricingButtonListener(modellingPricing, "3D Modeling");
        HorizontalLayout modellingTools = new HorizontalLayout();
        modellingTools.addClassName("tools-container");
        
        Span blender = new Span("Blender");
        Span maya = new Span("Maya");
        Span unity = new Span("Unity");
        Span unreal = new Span("Unreal Engine");
        
        modellingTools.add(blender, maya, unity, unreal);
        modellingTools.setSpacing(true);
        modellingWrapper.add(modellingTools, modellingPricing);
        modellingCard.add(modellingContent, modellingWrapper);

        // Business Analysis Card
        Div businessAnalysisCard = new Div();
        businessAnalysisCard.addClassNames("cards__card", "card", "offersCard");
        Image businessAnalysisImage = new Image("https://illustrations.popsy.co/gray/woman-on-laptop-excel.svg", "Business Analysis");
        businessAnalysisImage.setWidth("100%");
        H2 businessAnalysisHeading = new H2("Business Analysis");
        businessAnalysisHeading.addClassNames("card__heading", "offer-heading");
        VerticalLayout businessAnalysisContent = new VerticalLayout(businessAnalysisImage, businessAnalysisHeading);
        
        VerticalLayout businessAnalysisWrapper = new VerticalLayout();
        Button businessAnalysisPricing = new Button("View Pricing");
        businessAnalysisPricing.addClassName("pricing");
        businessAnalysisPricing.setWidth("100%");
        businessAnalysisPricing.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addPricingButtonListener(businessAnalysisPricing, "Business Analysis");
        HorizontalLayout businessAnalysisTools = new HorizontalLayout();
        businessAnalysisTools.addClassName("tools-container");
        
        Span jira = new Span("Jira");
        Span tableau = new Span("Tableau");
        Span powerBi = new Span("Power BI");
        Span excel = new Span("Excel");
        Span visio = new Span("Visio");
        Span miro = new Span("Miro");
        Span lucidchart = new Span("Lucidchart");
        Span confluence = new Span("Confluence");
        Span msProject = new Span("MS Project");
        Span trello = new Span("Trello");
        
        businessAnalysisTools.add(jira, tableau, powerBi, excel, visio, miro, lucidchart, confluence, msProject, trello);
        businessAnalysisTools.setSpacing(true);
        businessAnalysisWrapper.add(businessAnalysisTools, businessAnalysisPricing);
        businessAnalysisCard.add(businessAnalysisContent, businessAnalysisWrapper);

        // Solutions Architecture Card
        Div solutionsArchCard = new Div();
        solutionsArchCard.addClassNames("cards__card", "card", "offersCard");
        Image solutionsArchImage = new Image("https://illustrations.popsy.co/gray/man-with-a-laptop.svg", "Solutions Architecture");
        solutionsArchImage.setWidth("100%");
        H2 solutionsArchHeading = new H2("Solutions Architecture");
        solutionsArchHeading.addClassNames("card__heading", "offer-heading");
        VerticalLayout solutionsArchContent = new VerticalLayout(solutionsArchImage, solutionsArchHeading);
        
        VerticalLayout solutionsArchWrapper = new VerticalLayout();
        Button solutionsArchPricing = new Button("View Pricing");
        solutionsArchPricing.addClassName("pricing");
        solutionsArchPricing.setWidth("100%");
        solutionsArchPricing.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addPricingButtonListener(solutionsArchPricing, "Solutions Architecture");
        HorizontalLayout solutionsArchTools = new HorizontalLayout();
        solutionsArchTools.addClassName("tools-container");
        
        Span aws = new Span("AWS");
        Span azure = new Span("Azure");
        Span kubernetes = new Span("Kubernetes");
        Span docker = new Span("Docker");
        Span terraform = new Span("Terraform");
        Span ansible = new Span("Ansible");
        
        solutionsArchTools.add(terraform, ansible, aws, azure, kubernetes, docker);
        solutionsArchTools.setSpacing(true);
        solutionsArchWrapper.add(solutionsArchTools, solutionsArchPricing);
        solutionsArchCard.add(solutionsArchContent, solutionsArchWrapper);

        //...
        cardsInner.add(webDevCard, appDevCard, uiDesignCard, 
            logoDesignCard, graphicDesignCard, architectureCard,
            modellingCard, businessAnalysisCard, solutionsArchCard);

        Div overlay = new Div();
        overlay.addClassNames("overlay", "cards__inner");

        mainCards.add(cardsInner, overlay);
        main.add(mainHeading, mainCards);

        //Script
        UI.getCurrent().getPage().executeJs(
            "console.clear();\n" +
                    "\n" +
                    "const cardsContainer = document.querySelector(\".cards\");\n" +
                    "const cardsContainerInner = document.querySelector(\".cards__inner\");\n" +
                    "const cards = Array.from(document.querySelectorAll(\".card\"));\n" +
                    "const overlay = document.querySelector(\".overlay\");\n" +
                    "\n" +
                    "const applyOverlayMask = (e) => {\n" +
                    "  const overlayEl = e.currentTarget;\n" +
                    "  const x = e.pageX - cardsContainer.offsetLeft;\n" +
                    "  const y = e.pageY - cardsContainer.offsetTop;\n" +
                    "\n" +
                    "  overlayEl.style = `--opacity: 1; --x: ${x}px; --y:${y}px;`;\n" +
                    "};\n" +
                    "\n" +
                    "const createOverlayCta = (overlayCard, ctaEl) => {\n" +
                    "  const overlayCta = document.createElement(\"div\");\n" +
                    "  overlayCta.classList.add(\"cta\");\n" +
                    "  overlayCta.textContent = ctaEl.textContent;\n" +
                    "  overlayCta.setAttribute(\"aria-hidden\", true);\n" +
                    "  overlayCard.append(overlayCta);\n" +
                    "};\n" +
                    "\n" +
                    "const observer = new ResizeObserver((entries) => {\n" +
                    "  entries.forEach((entry) => {\n" +
                    "    const cardIndex = cards.indexOf(entry.target);\n" +
                    "    let width = entry.borderBoxSize[0].inlineSize;\n" +
                    "    let height = entry.borderBoxSize[0].blockSize;\n" +
                    "\n" +
                    "    if (cardIndex >= 0) {\n" +
                    "      overlay.children[cardIndex].style.width = `${width}px`;\n" +
                    "      overlay.children[cardIndex].style.height = `${height}px`;\n" +
                    "    }\n" +
                    "  });\n" +
                    "});\n" +
                    "\n" +
                    "const initOverlayCard = (cardEl) => {\n" +
                    "  const overlayCard = document.createElement(\"div\");\n" +
                    "  overlayCard.classList.add(\"card\");\n" +
                    "  overlayCard.classList.add(\"offersCard\");\n" +
                    "  overlay.append(overlayCard);\n" +
                    "  observer.observe(cardEl);\n" +
                    "};\n" +
                    "\n" +
                    "cards.forEach(initOverlayCard);\n" +
                    "document.body.addEventListener(\"pointermove\", applyOverlayMask);"
        );
        return new HorizontalLayout(main);
    }
}
