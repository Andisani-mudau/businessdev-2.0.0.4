package com.businessdev.application.views.welcome;

import com.businessdev.application.views.welcome.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Pricing")
@Route(value = "pricing", layout = MainLayout.class)
public class Pricing extends VerticalLayout {
    public Pricing() {
        setClassName("sectionTwo");
        add(sectionOne());
    }
    
    private HorizontalLayout sectionOne() {
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
        webDevCard.addClassNames("cards__card", "card", "offersCard", "map");
        H2 webDevHeading = new H2("About");
        webDevHeading.addClassName("card__heading");
        Paragraph webDevPrice = new Paragraph("About");
        webDevPrice.addClassName("card__price");
        UnorderedList webDevBullets = new UnorderedList();
        webDevBullets.addClassNames("card__bullets", "flow");
        webDevBullets.add(
            new ListItem("Access to standard workouts and nutrition plans"),
            new ListItem("Email support")
        );
        Anchor webDevCta = new Anchor("#basic", "Get Started");
        webDevCta.addClassNames("card__cta", "cta");
        webDevCard.add(webDevHeading, webDevPrice, webDevBullets);

        // App Development Card
        Div appDevCard = new Div();
        appDevCard.addClassNames("cards__card", "card", "offersCard");
        H2 appDevHeading = new H2("Mission");
        appDevHeading.addClassName("card__heading");
        Paragraph appDevPrice = new Paragraph("Mission");
        appDevPrice.addClassName("card__price");
        UnorderedList appDevBullets = new UnorderedList();
        appDevBullets.addClassNames("card__bullets", "flow");
        appDevBullets.add(
            new ListItem("Access to advanced workouts and nutrition plans"),
            new ListItem("Priority Email support"),
            new ListItem("Exclusive access to live Q&A sessions")
        );
        Anchor appDevCta = new Anchor("#pro", "Upgrade to Pro");
        appDevCta.addClassNames("card__cta", "cta");
        appDevCard.add(appDevHeading, appDevPrice, appDevBullets);

        // UI Design Card
        Div uiDesignCard = new Div();
        uiDesignCard.addClassNames("cards__card", "card", "offersCard");
        H2 uiDesignHeading = new H2("Vision");
        uiDesignHeading.addClassName("card__heading");
        Paragraph uiDesignPrice = new Paragraph("Vision");
        uiDesignPrice.addClassName("card__price");
        UnorderedList uiDesignBullets = new UnorderedList();
        uiDesignBullets.addClassNames("card__bullets", "flow");
        uiDesignBullets.add(
            new ListItem("Access to all premium workouts and nutrition plans"),
            new ListItem("24/7 Priority support"),
            new ListItem("1-on-1 virtual coaching session every month"),
            new ListItem("Exclusive content and early access to new features")
        );
        Anchor uiDesignCta = new Anchor("#ultimate", "Go Ultimate");
        uiDesignCta.addClassNames("card__cta", "cta");
        uiDesignCard.add(uiDesignHeading, uiDesignPrice, uiDesignBullets);

        // Logo Design Card
        Div logoDesignCard = new Div();
        logoDesignCard.addClassNames("cards__card", "card", "offersCard");
        H2 logoDesignHeading = new H2("About");
        logoDesignHeading.addClassName("card__heading");
        Paragraph logoDesignPrice = new Paragraph("About");
        logoDesignPrice.addClassName("card__price");
        UnorderedList logoDesignBullets = new UnorderedList();
        logoDesignBullets.addClassNames("card__bullets", "flow");
        logoDesignBullets.add(
            new ListItem("Access to standard workouts and nutrition plans"),
            new ListItem("Email support")
        );
        Anchor logoDesignCta = new Anchor("#basic", "Get Started");
        logoDesignCta.addClassNames("card__cta", "cta");
        logoDesignCard.add(logoDesignHeading, logoDesignPrice, logoDesignBullets);

        // Graphic Design Card
        Div graphicDesignCard = new Div();
        graphicDesignCard.addClassNames("cards__card", "card", "offersCard");
        H2 graphicDesignHeading = new H2("Mission");
        graphicDesignHeading.addClassName("card__heading");
        Paragraph graphicDesignPrice = new Paragraph("Mission");
        graphicDesignPrice.addClassName("card__price");
        UnorderedList graphicDesignBullets = new UnorderedList();
        graphicDesignBullets.addClassNames("card__bullets", "flow");
        graphicDesignBullets.add(
            new ListItem("Access to advanced workouts and nutrition plans"),
            new ListItem("Priority Email support"),
            new ListItem("Exclusive access to live Q&A sessions")
        );
        Anchor graphicDesignCta = new Anchor("#pro", "Upgrade to Pro");
        graphicDesignCta.addClassNames("card__cta", "cta");
        graphicDesignCard.add(graphicDesignHeading, graphicDesignPrice, graphicDesignBullets);

        //...
        cardsInner.add(webDevCard, appDevCard, uiDesignCard, 
            logoDesignCard, graphicDesignCard);

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
