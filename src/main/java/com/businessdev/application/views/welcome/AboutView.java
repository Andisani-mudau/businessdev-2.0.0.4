package com.businessdev.application.views.welcome;

import com.businessdev.application.views.welcome.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.uitest.model.chat.Link;
import com.vaadin.flow.component.UI;

@PageTitle("about")
@Route(value = "about", layout = MainLayout.class)
public class AboutView extends VerticalLayout {
    public AboutView(){
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

        // Basic Card
        Div basicCard = new Div();
        basicCard.addClassNames("cards__card", "card");
        H2 basicHeading = new H2("About");
        basicHeading.addClassName("card__heading");
        Paragraph basicPrice = new Paragraph("About");
        basicPrice.addClassName("card__price");
        UnorderedList basicBullets = new UnorderedList();
        basicBullets.addClassNames("card__bullets", "flow");
        basicBullets.add(
            new ListItem("Access to standard workouts and nutrition plans"),
            new ListItem("Email support")
        );
        Anchor basicCta = new Anchor("#basic", "Get Started");
        basicCta.addClassNames("card__cta", "cta");
        basicCard.add(basicHeading, basicBullets);

        // Pro Card
        Div proCard = new Div();
        proCard.addClassNames("cards__card", "card");
        H2 proHeading = new H2("Mission");
        proHeading.addClassName("card__heading");
        Paragraph proPrice = new Paragraph("Mission");
        proPrice.addClassName("card__price");
        UnorderedList proBullets = new UnorderedList();
        proBullets.addClassNames("card__bullets", "flow");
        proBullets.add(
            new ListItem("Access to advanced workouts and nutrition plans"),
            new ListItem("Priority Email support"),
            new ListItem("Exclusive access to live Q&A sessions")
        );
        Anchor proCta = new Anchor("#pro", "Upgrade to Pro");
        proCta.addClassNames("card__cta", "cta");
        proCard.add(proHeading, proBullets);

        // Ultimate Card
        Div ultimateCard = new Div();
        ultimateCard.addClassNames("cards__card", "card");
        H2 ultimateHeading = new H2("Vision");
        ultimateHeading.addClassName("card__heading");
        Paragraph ultimatePrice = new Paragraph("Vision");
        ultimatePrice.addClassName("card__price");
        UnorderedList ultimateBullets = new UnorderedList();
        ultimateBullets.addClassNames("card__bullets", "flow");
        ultimateBullets.add(
            new ListItem("Access to all premium workouts and nutrition plans"),
            new ListItem("24/7 Priority support"),
            new ListItem("1-on-1 virtual coaching session every month"),
            new ListItem("Exclusive content and early access to new features")
        );
        Anchor ultimateCta = new Anchor("#ultimate", "Go Ultimate");
        ultimateCta.addClassNames("card__cta", "cta");
        ultimateCard.add(ultimateHeading, ultimateBullets);

        // More info Card
        Div moreInfoCard = new Div();
        moreInfoCard.addClassNames("cards__card", "card", "moreInfocard");
        H2 moreInfoHeading = new H2("Why us?");
        moreInfoHeading.addClassName("card__heading");
        Paragraph moreInfoPrice = new Paragraph("Why us?");
        moreInfoPrice.addClassName("card__price");
        UnorderedList moreInfoBullets = new UnorderedList();
        moreInfoBullets.addClassNames("card__bullets", "flow");
        moreInfoBullets.add(
            new ListItem("Access to advanced workouts and nutrition plans"),
            new ListItem("Priority Email support"),
            new ListItem("Exclusive access to live Q&A sessions")
        );
        Anchor moreInfoCta = new Anchor("#moreInfo", "Upgrade to moreInfo");
        moreInfoCta.addClassNames("card__cta", "cta");
        moreInfoCard.add(moreInfoHeading, moreInfoBullets);

        cardsInner.add(basicCard, proCard, ultimateCard, moreInfoCard);

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
                    "  overlayCard.classList.add(\"moreInfocard\");\n" +
                    "  overlay.append(overlayCard);\n" +
                    "  observer.observe(cardEl);\n" +
                    "};\n" +
                    "\n" +
                    "cards.forEach(initOverlayCard);\n" +
                    "document.body.addEventListener(\"pointermove\", applyOverlayMask);"
        );
        return new HorizontalLayout(main);
    }
    private HorizontalLayout sectionThree(){
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

        // Basic Card
        Div basicCard = new Div();
        basicCard.addClassNames("cards__card", "card");
        H2 basicHeading = new H2("About");
        basicHeading.addClassName("card__heading");
        Paragraph basicPrice = new Paragraph("$9.99");
        basicPrice.addClassName("card__price");
        UnorderedList basicBullets = new UnorderedList();
        basicBullets.addClassNames("card__bullets", "flow");
        basicBullets.add(
            new ListItem("Access to standard workouts and nutrition plans"),
            new ListItem("Email support")
        );
        Anchor basicCta = new Anchor("#basic", "Get Started");
        basicCta.addClassNames("card__cta", "cta");
        basicCard.add(basicHeading, basicPrice, basicBullets, basicCta);

        // Pro Card
        Div proCard = new Div();
        proCard.addClassNames("cards__card", "card");
        H2 proHeading = new H2("Mission");
        proHeading.addClassName("card__heading");
        Paragraph proPrice = new Paragraph("$19.99");
        proPrice.addClassName("card__price");
        UnorderedList proBullets = new UnorderedList();
        proBullets.addClassNames("card__bullets", "flow");
        proBullets.add(
            new ListItem("Access to advanced workouts and nutrition plans"),
            new ListItem("Priority Email support"),
            new ListItem("Exclusive access to live Q&A sessions")
        );
        Anchor proCta = new Anchor("#pro", "Upgrade to Pro");
        proCta.addClassNames("card__cta", "cta");
        proCard.add(proHeading, proPrice, proBullets, proCta);

        // Ultimate Card
        Div ultimateCard = new Div();
        ultimateCard.addClassNames("cards__card", "card");
        H2 ultimateHeading = new H2("Vision");
        ultimateHeading.addClassName("card__heading");
        Paragraph ultimatePrice = new Paragraph("$29.99");
        ultimatePrice.addClassName("card__price");
        UnorderedList ultimateBullets = new UnorderedList();
        ultimateBullets.addClassNames("card__bullets", "flow");
        ultimateBullets.add(
            new ListItem("Access to all premium workouts and nutrition plans"),
            new ListItem("24/7 Priority support"),
            new ListItem("1-on-1 virtual coaching session every month"),
            new ListItem("Exclusive content and early access to new features")
        );
        Anchor ultimateCta = new Anchor("#ultimate", "Go Ultimate");
        ultimateCta.addClassNames("card__cta", "cta");
        ultimateCard.add(ultimateHeading, ultimatePrice, ultimateBullets, ultimateCta);

        cardsInner.add(basicCard, proCard, ultimateCard);

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
                    "  overlayCard.classList.add(\"moreInfocard\");\n" +
                    "  createOverlayCta(overlayCard, cardEl.lastElementChild);\n" +
                    "  overlay.append(overlayCard);\n" +
                    "  observer.observe(cardEl);\n" +
                    "};\n" +
                    "\n" +
                    "cards.forEach(initOverlayCard);\n" +
                    "document.body.addEventListener(\"pointermove\", applyOverlayMask);"
        );
        return new HorizontalLayout(main);
    }
    private HorizontalLayout sectionFour(){
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

        // Basic Card
        Div basicCard = new Div();
        basicCard.addClassNames("cards__card", "card");
        H2 basicHeading = new H2("About");
        basicHeading.addClassName("card__heading");
        Paragraph basicPrice = new Paragraph("$9.99");
        basicPrice.addClassName("card__price");
        UnorderedList basicBullets = new UnorderedList();
        basicBullets.addClassNames("card__bullets", "flow");
        basicBullets.add(
            new ListItem("Access to standard workouts and nutrition plans"),
            new ListItem("Email support")
        );
        Anchor basicCta = new Anchor("#basic", "Get Started");
        basicCta.addClassNames("card__cta", "cta");
        basicCard.add(basicHeading, basicPrice, basicBullets, basicCta);

        // Pro Card
        Div proCard = new Div();
        proCard.addClassNames("cards__card", "card");
        H2 proHeading = new H2("Mission");
        proHeading.addClassName("card__heading");
        Paragraph proPrice = new Paragraph("$19.99");
        proPrice.addClassName("card__price");
        UnorderedList proBullets = new UnorderedList();
        proBullets.addClassNames("card__bullets", "flow");
        proBullets.add(
            new ListItem("Access to advanced workouts and nutrition plans"),
            new ListItem("Priority Email support"),
            new ListItem("Exclusive access to live Q&A sessions")
        );
        Anchor proCta = new Anchor("#pro", "Upgrade to Pro");
        proCta.addClassNames("card__cta", "cta");
        proCard.add(proHeading, proPrice, proBullets, proCta);

        // Ultimate Card
        Div ultimateCard = new Div();
        ultimateCard.addClassNames("cards__card", "card");
        H2 ultimateHeading = new H2("Vision");
        ultimateHeading.addClassName("card__heading");
        Paragraph ultimatePrice = new Paragraph("$29.99");
        ultimatePrice.addClassName("card__price");
        UnorderedList ultimateBullets = new UnorderedList();
        ultimateBullets.addClassNames("card__bullets", "flow");
        ultimateBullets.add(
            new ListItem("Access to all premium workouts and nutrition plans"),
            new ListItem("24/7 Priority support"),
            new ListItem("1-on-1 virtual coaching session every month"),
            new ListItem("Exclusive content and early access to new features")
        );
        Anchor ultimateCta = new Anchor("#ultimate", "Go Ultimate");
        ultimateCta.addClassNames("card__cta", "cta");
        ultimateCard.add(ultimateHeading, ultimatePrice, ultimateBullets, ultimateCta);

        cardsInner.add(basicCard, proCard, ultimateCard);

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
                    "  createOverlayCta(overlayCard, cardEl.lastElementChild);\n" +
                    "  overlay.append(overlayCard);\n" +
                    "  observer.observe(cardEl);\n" +
                    "};\n" +
                    "\n" +
                    "cards.forEach(initOverlayCard);\n" +
                    "document.body.addEventListener(\"pointermove\", applyOverlayMask);"
        );
        return new HorizontalLayout(main);
    }
    private HorizontalLayout sectionFive(){
        return new HorizontalLayout();
    }
}