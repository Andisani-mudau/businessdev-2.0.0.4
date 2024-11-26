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
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H2;
import java.time.LocalDate;
import com.vaadin.flow.component.combobox.ComboBox;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.QueryParameters;

@PageTitle("contact")
@Route(value = "contact", layout = MainLayout.class)
public class ContactView extends VerticalLayout implements BeforeEnterObserver {
    private final VerticalLayout contactFormSection = new VerticalLayout();
    private final VerticalLayout schedulingSection = new VerticalLayout();
    
    // Contact form components
    private final TextField name = new TextField("Name");
    private final TextField email = new TextField("Email");
    private final TextField phone = new TextField("Phone");
    private final TextField message = new TextField("Message");
    private final Button submitContact = new Button("Send Message");
    
    // Scheduling components
    private final DatePicker datePicker = new DatePicker("Select Date");
    private final ComboBox<String> timeSlot = new ComboBox<>("Select Time");
    private boolean timeStepVisible = false;
    private final ComboBox<String> durationSelect = new ComboBox<>("Meeting Duration");
    private boolean durationStepVisible = false;
    private final ComboBox<String> platformSelect = new ComboBox<>("Meeting Platform");
    private boolean platformStepVisible = false;
    private final VerticalLayout meetingDetailsForm = new VerticalLayout();
    private final Button scheduleButton = new Button("Schedule Meeting");
    private final TextField meetingName = new TextField("Name");
    private final TextField meetingEmail = new TextField("Email");
    private final TextField meetingPhone = new TextField("Phone");
    private final TextField meetingNotes = new TextField("Additional Notes");
    private final Button nextButton = new Button("Next");
    private final TextField service = new TextField("Service");
    private final TextField serviceType = new TextField("Service Type");
    
    public ContactView() {
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
        basicCard.addClassNames("cards__card", "contactCard");
        H2 basicHeading = new H2("Contact Us");
        basicHeading.addClassName("card__heading");
        Paragraph basicPrice = new Paragraph("Contact Us");
        basicPrice.addClassName("card__price");
        basicCard.add(basicHeading, createContactForm());

        // Pro Card
        Div proCard = new Div();
        proCard.addClassNames("cards__card", "contactCard");
        H2 proHeading = new H2("Schedule Meeting");
        proHeading.addClassName("card__heading");
        Paragraph proPrice = new Paragraph("Schedule Meeting");
        proPrice.addClassName("card__price");
        proCard.add(proHeading, createInitialSchedulingStep());

        // Ultimate Card
        Div ultimateCard = new Div();
        ultimateCard.addClassNames("cards__card", "contactCard");
        H2 ultimateHeading = new H2("Contact details");
        ultimateHeading.addClassName("card__heading");
        Paragraph ultimatePrice = new Paragraph("Contact details");
        ultimatePrice.addClassName("card__price");
        ultimateCard.add(ultimateHeading, socialLinks());

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
                    "const cards = Array.from(document.querySelectorAll(\".contactCard\"));\n" +
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
                    "  overlayCard.classList.add(\"contactCard\");\n" +
                    "  overlay.append(overlayCard);\n" +
                    "  observer.observe(cardEl);\n" +
                    "};\n" +
                    "\n" +
                    "cards.forEach(initOverlayCard);\n" +
                    "document.body.addEventListener(\"pointermove\", applyOverlayMask);"
        );
        return new HorizontalLayout(main);
    }

    private Component createContactForm() {
        VerticalLayout formLayout = new VerticalLayout();
        formLayout.setSpacing(true);
        formLayout.setPadding(true);
        formLayout.getStyle().set("width", "100%");

        name.setRequired(true);
        name.getStyle().set("width", "100%");
        email.setRequired(true);
        email.getStyle().set("width", "100%");
        phone.setRequired(true);
        phone.getStyle().set("width", "100%");
        message.setRequired(true);
        message.getStyle().set("width", "100%");
        
        submitContact.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        submitContact.getStyle().set("font-weight", "200");
        submitContact.addClickListener(e -> handleContactSubmit());
        
        formLayout.add(
            name,
            email,
            phone,
            message,
            submitContact
        );
        return formLayout;
    }
    
    private Component createInitialSchedulingStep() {
        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setPadding(false);
        layout.getStyle().set("width", "100%");
        service.setVisible(false);
        service.getStyle().set("width", "100%");
        serviceType.setVisible(false);
        serviceType.getStyle().set("flex", "1").set("min-width", "130px");
        datePicker.setRequired(true);
        datePicker.getStyle().set("flex", "1").set("min-width", "130px");
        datePicker.setMin(LocalDate.now());
        datePicker.setMax(LocalDate.now().plusMonths(3));
        HorizontalLayout serviceLayout = new HorizontalLayout(serviceType, datePicker);
        serviceLayout.getStyle().set("width", "100%").set("flex-wrap", "wrap").set("flex-direction", "row-reverse");
        serviceLayout.setSpacing(true);
        // Create all selections and show them
        setupTimeSelection();
        setupDurationSelection();
        setupPlatformSelection();
        
        // Make all fields visible initially
        timeSlot.setVisible(true);
        timeSlot.getStyle().set("width", "100%");
        durationSelect.setVisible(true);
        durationSelect.getStyle().set("width", "100%");
        platformSelect.setVisible(true);
        platformSelect.getStyle().set("width", "100%");
        meetingDetailsForm.setVisible(false);
        meetingDetailsForm.getStyle().set("width", "100%");
        nextButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        nextButton.getStyle().set("font-weight", "200");
        nextButton.addClickListener(e -> handleNextButtonClick());
        
        // Add all components to the layout
        layout.add(
            service,
            serviceLayout, 
            timeSlot, 
            durationSelect, 
            platformSelect, 
            nextButton,
            meetingDetailsForm
        );
        return layout;
    }
    
    private void setupTimeSelection() {
        // Generate time slots from 9 AM to 5 PM
        List<String> times = new ArrayList<>();
        LocalTime time = LocalTime.of(9, 0);
        while (!time.isAfter(LocalTime.of(17, 0))) {
            times.add(time.format(DateTimeFormatter.ofPattern("hh:mm a")));
            time = time.plusMinutes(30);
        }
        
        timeSlot.setItems(times);
        timeSlot.setRequired(true);
        timeSlot.setPlaceholder("Choose a time");
        timeSlot.addValueChangeListener(e -> handleTimeSelection(e.getValue()));
    }
    
    private void setupDurationSelection() {
        List<String> durations = List.of(
            "15 minutes",
            "30 minutes",
            "45 minutes",
            "1 hour",
            "1.5 hours",
            "2 hours"
        );
        
        durationSelect.setItems(durations);
        durationSelect.setRequired(true);
        durationSelect.setPlaceholder("Select meeting duration");
        durationSelect.addValueChangeListener(e -> handleDurationSelection(e.getValue()));
    }
    
    private void setupPlatformSelection() {
        List<String> platforms = List.of(
            "Google Meet",
            "Microsoft Teams",
            "Zoom",
            "Skype",
            "WhatsApp",
            "Signal",
            "In Person"
        );
        
        platformSelect.setItems(platforms);
        platformSelect.setRequired(true);
        platformSelect.setPlaceholder("Select meeting platform");
        //platformSelect.addValueChangeListener(e -> handlePlatformSelection(e.getValue()));
    }
    
    private void handleContactSubmit() {
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || message.isEmpty()) {
            Notification.show("Please fill in all required fields", 3000, Notification.Position.TOP_CENTER);
            return;
        }
        // Add your contact form submission logic here
        Notification.show("Message sent successfully!", 3000, Notification.Position.TOP_CENTER);
    }
    
    private void handleDateSelection(LocalDate date) {
        if (date != null) {
            timeSlot.setVisible(true);
            timeStepVisible = true;
            // Hide and clear subsequent steps
            durationSelect.setVisible(true);
            platformSelect.setVisible(true);
            meetingDetailsForm.setVisible(false);
            //meetingDetailsForm.getStyle().set("width", "100%");
            // .set("top", "0")
            // .set("left", "0")
            // .set("z-index", "4");
            timeSlot.clear();
            durationSelect.clear();
            platformSelect.clear();
            resetMeetingDetailsForm();
        }
    }
    
    private void handleTimeSelection(String time) {
        if (time != null) {
            durationSelect.setVisible(true);
            durationStepVisible = true;
            durationSelect.clear();
            platformSelect.clear();
        }
    }
    
    private void handleDurationSelection(String duration) {
        if (duration != null) {
            platformSelect.setVisible(true);
            platformStepVisible = true;
            // Clear any previously selected platform
            platformSelect.clear();
        }
    }
    
    private void handlePlatformSelection(String platform) {
        if (platform != null) {
            showMeetingDetailsForm();
        }
    }
    
    private void showMeetingDetailsForm() {
        // Clear previous form if any
        meetingDetailsForm.removeAll();
        
        // Add a back button
        Button backButton = new Button("Back");
        backButton.getStyle().set("flex", "1");
        scheduleButton.getStyle().set("flex", "2");
        backButton.addClickListener(e -> handleBackButton());
        meetingName.setRequired(true);
        meetingName.getStyle().set("width", "100%");
        meetingEmail.setRequired(true);
        meetingEmail.getStyle().set("width", "100%");
        meetingPhone.setRequired(true);
        meetingPhone.getStyle().set("width", "100%");
        meetingNotes.setPlaceholder("Any specific topics or requirements?");
        meetingNotes.getStyle().set("width", "100%");

        scheduleButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        scheduleButton.getStyle().set("font-weight", "200");

        HorizontalLayout buttonLayout = new HorizontalLayout(backButton, scheduleButton);
        buttonLayout.setSpacing(true);
        buttonLayout.getStyle().set("width", "100%");
        meetingDetailsForm.add(
            meetingName,
            meetingEmail,
            meetingPhone,
            meetingNotes,
            buttonLayout
        );
        
        // Hide the next button when showing meeting details form
        nextButton.setVisible(false);
        meetingDetailsForm.setVisible(true);
    }
    
    private void handleBackButton() {
        datePicker.setVisible(true);
        timeSlot.setVisible(true);
        durationSelect.setVisible(true);
        platformSelect.setVisible(true);
        nextButton.setVisible(true);
        
        // Hide meeting details form
        meetingDetailsForm.setVisible(false);
    }
    
    private void handleNextButtonClick() {
        // Validate all fields
        if (datePicker.isEmpty() || timeSlot.isEmpty() || 
            durationSelect.isEmpty() || platformSelect.isEmpty()) {
            
            Notification.show("Please complete all fields", 
                3000, Notification.Position.TOP_CENTER);
            return;
        }
        
        // Hide scheduling fields and show meeting details form
        datePicker.setVisible(false);
        timeSlot.setVisible(false);
        durationSelect.setVisible(false);
        platformSelect.setVisible(false);
        
        // Show meeting details form
        showMeetingDetailsForm();
    }
    
    private void handleScheduleSubmit() {
        // Validate all required fields
        if (datePicker.isEmpty() || timeSlot.isEmpty() || 
            durationSelect.isEmpty() || platformSelect.isEmpty() ||
            meetingName.isEmpty() || meetingEmail.isEmpty() || 
            meetingPhone.isEmpty()) {
            
            Notification.show("Please complete all required fields", 
                3000, Notification.Position.TOP_CENTER);
            return;
        }
        
        // Format meeting details
        String meetingDetails = String.format(
            """
            Meeting Scheduled Successfully!
            
            Date: %s
            Time: %s
            Duration: %s
            Platform: %s
            
            Contact Information:
            Name: %s
            Email: %s
            Phone: %s
            %s
            """,
            datePicker.getValue(),
            timeSlot.getValue(),
            durationSelect.getValue(),
            platformSelect.getValue(),
            meetingName.getValue(),
            meetingEmail.getValue(),
            meetingPhone.getValue(),
            meetingNotes.isEmpty() ? "" : "\nNotes: " + meetingNotes.getValue()
        );
        
        // Show success notification
        Notification notification = new Notification(
            meetingDetails, 
            8000, 
            Notification.Position.MIDDLE
        );
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        notification.open();
        
        // Reset all fields
        resetAllFields();
    }
    
    private void resetAllFields() {
        datePicker.clear();
        timeSlot.clear();
        durationSelect.clear();
        platformSelect.clear();
        meetingName.clear();
        meetingEmail.clear();
        meetingPhone.clear();
        meetingNotes.clear();
        
        // Show scheduling fields
        datePicker.setVisible(true);
        timeSlot.setVisible(true);
        durationSelect.setVisible(true);
        platformSelect.setVisible(true);
        meetingDetailsForm.setVisible(false);
    }
    
    private void resetMeetingDetailsForm() {
        meetingName.clear();
        meetingEmail.clear();
        meetingPhone.clear();
        meetingNotes.clear();
    }

    private HorizontalLayout socialLinks() {
        //Icons
        Div instagramIcon = new Div();
        instagramIcon.getStyle().set("margin", "0")
                .set("padding", "0")
                .set("height", "unset");
        instagramIcon.getElement().setProperty("innerHTML", "<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 448 512\"><!--!Font Awesome Free 6.7.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path d=\"M224.1 141c-63.6 0-114.9 51.3-114.9 114.9s51.3 114.9 114.9 114.9S339 319.5 339 255.9 287.7 141 224.1 141zm0 189.6c-41.1 0-74.7-33.5-74.7-74.7s33.5-74.7 74.7-74.7 74.7 33.5 74.7 74.7-33.6 74.7-74.7 74.7zm146.4-194.3c0 14.9-12 26.8-26.8 26.8-14.9 0-26.8-12-26.8-26.8s12-26.8 26.8-26.8 26.8 12 26.8 26.8zm76.1 27.2c-1.7-35.9-9.9-67.7-36.2-93.9-26.2-26.2-58-34.4-93.9-36.2-37-2.1-147.9-2.1-184.9 0-35.8 1.7-67.6 9.9-93.9 36.1s-34.4 58-36.2 93.9c-2.1 37-2.1 147.9 0 184.9 1.7 35.9 9.9 67.7 36.2 93.9s58 34.4 93.9 36.2c37 2.1 147.9 2.1 184.9 0 35.9-1.7 67.7-9.9 93.9-36.2 26.2-26.2 34.4-58 36.2-93.9 2.1-37 2.1-147.8 0-184.8zM398.8 388c-7.8 19.6-22.9 34.7-42.6 42.6-29.5 11.7-99.5 9-132.1 9s-102.7 2.6-132.1-9c-19.6-7.8-34.7-22.9-42.6-42.6-11.7-29.5-9-99.5-9-132.1s-2.6-102.7 9-132.1c7.8-19.6 22.9-34.7 42.6-42.6 29.5-11.7 99.5-9 132.1-9s102.7-2.6 132.1 9c19.6 7.8 34.7 22.9 42.6 42.6 11.7 29.5 9 99.5 9 132.1s2.7 102.7-9 132.1z\"/></svg>");

        Div facebookIcon = new Div();
        facebookIcon.getStyle().set("margin", "0")
                .set("padding", "0")
                .set("height", "unset");
        facebookIcon.getElement().setProperty("innerHTML", "<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 448 512\"><!--!Font Awesome Free 6.7.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path d=\"M64 32C28.7 32 0 60.7 0 96V416c0 35.3 28.7 64 64 64h98.2V334.2H109.4V256h52.8V222.3c0-87.1 39.4-127.5 125-127.5c16.2 0 44.2 3.2 55.7 6.4V172c-6-.6-16.5-1-29.6-1c-42 0-58.2 15.9-58.2 57.2V256h83.6l-14.4 78.2H255V480H384c35.3 0 64-28.7 64-64V96c0-35.3-28.7-64-64-64H64z\"/></svg>");

        Div xIcon = new Div();
        xIcon.getStyle().set("margin", "0")
                .set("padding", "0")
                .set("height", "unset");
        xIcon.getElement().setProperty("innerHTML", "<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 512 512\"><!--!Font Awesome Free 6.7.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path d=\"M389.2 48h70.6L305.6 224.2 487 464H345L233.7 318.6 106.5 464H35.8L200.7 275.5 26.8 48H172.4L272.9 180.9 389.2 48zM364.4 421.8h39.1L151.1 88h-42L364.4 421.8z\"/></svg>");
        
        Div githubIcon = new Div();
        githubIcon.getStyle().set("margin", "0")
                .set("padding", "0")
                .set("height", "unset");
        githubIcon.getElement().setProperty("innerHTML", "<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 448 512\"><!--!Font Awesome Free 6.7.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path d=\"M448 96c0-35.3-28.7-64-64-64H64C28.7 32 0 60.7 0 96V416c0 35.3 28.7 64 64 64H384c35.3 0 64-28.7 64-64V96zM265.8 407.7c0-1.8 0-6 .1-11.6c.1-11.4 .1-28.8 .1-43.7c0-15.6-5.2-25.5-11.3-30.7c37-4.1 76-9.2 76-73.1c0-18.2-6.5-27.3-17.1-39c1.7-4.3 7.4-22-1.7-45c-13.9-4.3-45.7 17.9-45.7 17.9c-13.2-3.7-27.5-5.6-41.6-5.6s-28.4 1.9-41.6 5.6c0 0-31.8-22.2-45.7-17.9c-9.1 22.9-3.5 40.6-1.7 45c-10.6 11.7-15.6 20.8-15.6 39c0 63.6 37.3 69 74.3 73.1c-4.8 4.3-9.1 11.7-10.6 22.3c-9.5 4.3-33.8 11.7-48.3-13.9c-9.1-15.8-25.5-17.1-25.5-17.1c-16.2-.2-1.1 10.2-1.1 10.2c10.8 5 18.4 24.2 18.4 24.2c9.7 29.7 56.1 19.7 56.1 19.7c0 9 .1 21.7 .1 30.6c0 4.8 .1 8.6 .1 10c0 4.3-3 9.5-11.5 8C106 393.6 59.8 330.8 59.8 257.4c0-91.8 70.2-161.5 162-161.5s166.2 69.7 166.2 161.5c.1 73.4-44.7 136.3-110.7 158.3c-8.4 1.5-11.5-3.7-11.5-8zm-90.5-54.8c-.2-1.5 1.1-2.8 3-3.2c1.9-.2 3.7 .6 3.9 1.9c.3 1.3-1 2.6-3 3c-1.9 .4-3.7-.4-3.9-1.7zm-9.1 3.2c-2.2 .2-3.7-.9-3.7-2.4c0-1.3 1.5-2.4 3.5-2.4c1.9-.2 3.7 .9 3.7 2.4c0 1.3-1.5 2.4-3.5 2.4zm-14.3-2.2c-1.9-.4-3.2-1.9-2.8-3.2s2.4-1.9 4.1-1.5c2 .6 3.3 2.1 2.8 3.4c-.4 1.3-2.4 1.9-4.1 1.3zm-12.5-7.3c-1.5-1.3-1.9-3.2-.9-4.1c.9-1.1 2.8-.9 4.3 .6c1.3 1.3 1.8 3.3 .9 4.1c-.9 1.1-2.8 .9-4.3-.6zm-8.5-10c-1.1-1.5-1.1-3.2 0-3.9c1.1-.9 2.8-.2 3.7 1.3c1.1 1.5 1.1 3.3 0 4.1c-.9 .6-2.6 0-3.7-1.5zm-6.3-8.8c-1.1-1.3-1.3-2.8-.4-3.5c.9-.9 2.4-.4 3.5 .6c1.1 1.3 1.3 2.8 .4 3.5c-.9 .9-2.4 .4-3.5-.6zm-6-6.4c-1.3-.6-1.9-1.7-1.5-2.6c.4-.6 1.5-.9 2.8-.4c1.3 .7 1.9 1.8 1.5 2.6c-.4 .9-1.7 1.1-2.8 .4z\"/></svg>");
        
        Div linkedinIcon = new Div();
        linkedinIcon.getStyle().set("margin", "0")
                .set("padding", "0")
                .set("height", "unset");
        linkedinIcon.getElement().setProperty("innerHTML", "<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 448 512\"><!--!Font Awesome Free 6.7.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path d=\"M416 32H31.9C14.3 32 0 46.5 0 64.3v383.4C0 465.5 14.3 480 31.9 480H416c17.6 0 32-14.5 32-32.3V64.3c0-17.8-14.4-32.3-32-32.3zM135.4 416H69V202.2h66.5V416zm-33.2-243c-21.3 0-38.5-17.3-38.5-38.5S80.9 96 102.2 96c21.2 0 38.5 17.3 38.5 38.5 0 21.3-17.2 38.5-38.5 38.5zm282.1 243h-66.4V312c0-24.8-.5-56.7-34.5-56.7-34.6 0-39.9 27-39.9 54.9V416h-66.4V202.2h63.7v29.2h.9c8.9-16.8 30.6-34.5 62.9-34.5 67.2 0 79.7 44.3 79.7 101.9V416z\"/></svg>");
        
        Div signalIcon = new Div();
        signalIcon.getStyle().set("margin", "0")
                .set("padding", "0")
                .set("height", "unset");
        signalIcon.getElement().setProperty("innerHTML", "<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 512 512\"><!--!Font Awesome Free 6.7.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path d=\"M194.6 7.5l5.8 23.3C177.7 36.3 156 45.3 136 57.4L123.7 36.8c22-13.3 45.9-23.2 70.9-29.3zm122.9 0l-5.8 23.3C334.3 36.3 356 45.3 376 57.4l12.4-20.6c-22-13.3-46-23.2-71-29.3zM36.8 123.7c-13.3 22-23.2 45.9-29.3 70.9l23.3 5.8C36.3 177.7 45.3 156 57.4 136L36.8 123.7zM24 256c0-11.6 .9-23.3 2.6-34.8L2.9 217.6c-3.8 25.4-3.8 51.3 0 76.7l23.7-3.6C24.9 279.3 24 267.6 24 256zM388.3 475.2L376 454.6c-20 12.1-41.6 21-64.2 26.6l5.8 23.3c24.9-6.2 48.8-16 70.8-29.3zM488 256c0 11.6-.9 23.3-2.6 34.8l23.7 3.6c3.8-25.4 3.8-51.3 0-76.7l-23.7 3.6c1.7 11.5 2.6 23.1 2.6 34.8zm16.5 61.4l-23.3-5.8c-5.6 22.7-14.5 44.3-26.6 64.3l20.6 12.4c13.3-22 23.2-46 29.3-71zm-213.8 168c-23 3.5-46.5 3.5-69.5 0l-3.6 23.7c25.4 3.8 51.3 3.8 76.7 0l-3.6-23.7zm152-91.8c-13.8 18.7-30.4 35.3-49.2 49.1l14.2 19.3c20.7-15.2 39-33.4 54.2-54.1l-19.3-14.4zM393.6 69.2c18.8 13.8 35.3 30.4 49.2 49.2L462.1 104C446.9 83.4 428.6 65.1 408 49.9L393.6 69.2zM69.2 118.4c13.8-18.8 30.4-35.3 49.2-49.2L104 49.9C83.4 65.1 65.1 83.4 49.9 104l19.3 14.4zm406 5.3L454.6 136c12.1 20 21 41.6 26.6 64.2l23.3-5.8c-6.2-24.9-16-48.8-29.3-70.8zm-254-97.1c23-3.5 46.5-3.5 69.5 0l3.6-23.7C268.9-1 243.1-1 217.6 2.9l3.6 23.7zM81.6 468.4L32 480l11.6-49.6L20.2 425 8.6 474.5c-.9 4-.8 8.1 .3 12.1s3.2 7.5 6.1 10.4s6.5 5 10.4 6.1s8.1 1.2 12.1 .3L87 492l-5.4-23.6zM25.2 403.6L48.6 409l8-34.4c-11.7-19.6-20.4-40.8-25.8-63L7.5 317.4c5.2 21.2 13.2 41.7 23.6 60.8l-5.9 25.3zm112 52l-34.4 8 5.4 23.4 25.3-5.9c19.2 10.4 39.6 18.4 60.8 23.6l5.8-23.3c-22.1-5.5-43.3-14.3-62.8-26l-.2 .2zM256 48c-37.2 0-73.6 10-105.6 28.9s-58.4 46-76.3 78.6s-26.9 69.3-25.8 106.4s12 73.3 31.8 104.8L60 452l85.3-20c27.3 17.2 58.2 27.8 90.3 31s64.5-1.1 94.6-12.6s57.2-29.8 79-53.6s37.8-52.2 46.8-83.2s10.5-63.6 4.7-95.3s-19-61.6-38.4-87.4s-44.5-46.7-73.4-61S288.3 48 256 48z\"/></svg>");
        
        Div emailIcon = new Div();
        emailIcon.getStyle().set("margin", "0")
                .set("padding", "0")
                .set("height", "unset");
        emailIcon.getElement().setProperty("innerHTML", "<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 512 512\"><!--!Font Awesome Free 6.7.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path d=\"M64 112c-8.8 0-16 7.2-16 16l0 22.1L220.5 291.7c20.7 17 50.4 17 71.1 0L464 150.1l0-22.1c0-8.8-7.2-16-16-16L64 112zM48 212.2L48 384c0 8.8 7.2 16 16 16l384 0c8.8 0 16-7.2 16-16l0-171.8L322 328.8c-38.4 31.5-93.7 31.5-132 0L48 212.2zM0 128C0 92.7 28.7 64 64 64l384 0c35.3 0 64 28.7 64 64l0 256c0 35.3-28.7 64-64 64L64 448c-35.3 0-64-28.7-64-64L0 128z\"/></svg>");
        
        Div callIcon = new Div();
        callIcon.getStyle().set("margin", "0")
                .set("padding", "0")
                .set("height", "unset");
        callIcon.getElement().setProperty("innerHTML", "<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 512 512\"><!--!Font Awesome Free 6.7.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path d=\"M164.9 24.6c-7.7-18.6-28-28.5-47.4-23.2l-88 24C12.1 30.2 0 46 0 64C0 311.4 200.6 512 448 512c18 0 33.8-12.1 38.6-29.5l24-88c5.3-19.4-4.6-39.7-23.2-47.4l-96-40c-16.3-6.8-35.2-2.1-46.3 11.6L304.7 368C234.3 334.7 177.3 277.7 144 207.3L193.3 167c13.7-11.2 18.4-30 11.6-46.3l-40-96z\"/></svg>");
        
        Div threadsIcon = new Div();
        threadsIcon.getStyle().set("margin", "0")
                .set("padding", "0")
                .set("height", "unset");
        threadsIcon.getElement().setProperty("innerHTML", "<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 448 512\"><!--!Font Awesome Free 6.7.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path d=\"M331.5 235.7c2.2 .9 4.2 1.9 6.3 2.8c29.2 14.1 50.6 35.2 61.8 61.4c15.7 36.5 17.2 95.8-30.3 143.2c-36.2 36.2-80.3 52.5-142.6 53h-.3c-70.2-.5-124.1-24.1-160.4-70.2c-32.3-41-48.9-98.1-49.5-169.6V256v-.2C17 184.3 33.6 127.2 65.9 86.2C102.2 40.1 156.2 16.5 226.4 16h.3c70.3 .5 124.9 24 162.3 69.9c18.4 22.7 32 50 40.6 81.7l-40.4 10.8c-7.1-25.8-17.8-47.8-32.2-65.4c-29.2-35.8-73-54.2-130.5-54.6c-57 .5-100.1 18.8-128.2 54.4C72.1 146.1 58.5 194.3 58 256c.5 61.7 14.1 109.9 40.3 143.3c28 35.6 71.2 53.9 128.2 54.4c51.4-.4 85.4-12.6 113.7-40.9c32.3-32.2 31.7-71.8 21.4-95.9c-6.1-14.2-17.1-26-31.9-34.9c-3.7 26.9-11.8 48.3-24.7 64.8c-17.1 21.8-41.4 33.6-72.7 35.3c-23.6 1.3-46.3-4.4-63.9-16c-20.8-13.8-33-34.8-34.3-59.3c-2.5-48.3 35.7-83 95.2-86.4c21.1-1.2 40.9-.3 59.2 2.8c-2.4-14.8-7.3-26.6-14.6-35.2c-10-11.7-25.6-17.7-46.2-17.8H227c-16.6 0-39 4.6-53.3 26.3l-34.4-23.6c19.2-29.1 50.3-45.1 87.8-45.1h.8c62.6 .4 99.9 39.5 103.7 107.7l-.2 .2zm-156 68.8c1.3 25.1 28.4 36.8 54.6 35.3c25.6-1.4 54.6-11.4 59.5-73.2c-13.2-2.9-27.8-4.4-43.4-4.4c-4.8 0-9.6 .1-14.4 .4c-42.9 2.4-57.2 23.2-56.2 41.8l-.1 .1z\"/></svg>");

        Div linkIcon = new Div();
        linkIcon.getStyle().set("margin", "0")
                .set("padding", "0")
                .set("height", "unset");
        linkIcon.getElement().setProperty("innerHTML", "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"black\" stroke-width=\"2.5\" stroke-linecap=\"round\" stroke-linejoin=\"round\" class=\"feather feather-link-2\"><path d=\"M15 7h3a5 5 0 0 1 5 5 5 5 0 0 1-5 5h-3m-6 0H6a5 5 0 0 1-5-5 5 5 0 0 1 5-5h3\"></path><line x1=\"8\" y1=\"12\" x2=\"16\" y2=\"12\"></line></svg>");
        
        //text
        Paragraph instagramText = new Paragraph("Instagram");
        Paragraph facebookText = new Paragraph("Facebook");
        Paragraph xText = new Paragraph("X");
        Paragraph githubText = new Paragraph("GitHub");
        Paragraph linkedinText = new Paragraph("LinkedIn");
        Paragraph signalText = new Paragraph("Signal");
        Paragraph emailText = new Paragraph("Email");
        Paragraph callText = new Paragraph("Call");
        Paragraph threadsText = new Paragraph("Threads");

        //links
        Anchor instagram = new Anchor();
        HorizontalLayout instagramSeparator = new HorizontalLayout(instagramIcon, instagramText);
        instagram.add(instagramSeparator, createLinkIcon());
        instagram.setClassName("socialLink");
        
        Anchor facebook = new Anchor();
        HorizontalLayout facebookSeparator = new HorizontalLayout(facebookIcon, facebookText);
        facebook.add(facebookSeparator, createLinkIcon());
        facebook.setClassName("socialLink");
        
        Anchor x = new Anchor();
        HorizontalLayout xSeparator = new HorizontalLayout(xIcon, xText);
        x.add(xSeparator, createLinkIcon());
        x.setClassName("socialLink");
        
        Anchor github = new Anchor();
        HorizontalLayout githubSeparator = new HorizontalLayout(githubIcon, githubText);
        github.add(githubSeparator, createLinkIcon());
        github.setClassName("socialLink");
        
        Anchor linkedin = new Anchor();
        HorizontalLayout linkedinSeparator = new HorizontalLayout(linkedinIcon, linkedinText);
        linkedin.add(linkedinSeparator, createLinkIcon());
        linkedin.setClassName("socialLink");
        
        Anchor signal = new Anchor();
        HorizontalLayout signalSeparator = new HorizontalLayout(signalIcon, signalText);
        signal.add(signalSeparator, createLinkIcon());
        signal.setClassName("socialLink");
        
        Anchor email = new Anchor();
        HorizontalLayout emailSeparator = new HorizontalLayout(emailIcon, emailText);
        email.add(emailSeparator, createLinkIcon());
        email.setClassName("socialLink");
        
        Anchor call = new Anchor();
        HorizontalLayout callSeparator = new HorizontalLayout(callIcon, callText);
        call.add(callSeparator, createLinkIcon());
        call.setClassName("socialLink");
        
        Anchor threads = new Anchor();
        HorizontalLayout threadsSeparator = new HorizontalLayout(threadsIcon, threadsText);
        threads.add(threadsSeparator, createLinkIcon());
        threads.setClassName("socialLink");

        HorizontalLayout linksLayout = new HorizontalLayout(instagram, facebook, x, github, linkedin, signal, email, call, threads);
        linksLayout.setClassName("linksLayout");
        return linksLayout;
    }

    // Add this helper method to create new link icons
    private Div createLinkIcon() {
        Div linkIcon = new Div();
        linkIcon.setClassName("linkIcon");
        linkIcon.getStyle().set("margin", "0")
                .set("padding", "0")
                .set("height", "unset");
        linkIcon.getElement().setProperty("innerHTML", "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" height=\"24\" viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"black\" stroke-width=\"2.5\" stroke-linecap=\"round\" stroke-linejoin=\"round\" class=\"feather feather-link-2\"><path d=\"M15 7h3a5 5 0 0 1 5 5 5 5 0 0 1-5 5h-3m-6 0H6a5 5 0 0 1-5-5 5 5 0 0 1 5-5h3\"></path><line x1=\"8\" y1=\"12\" x2=\"16\" y2=\"12\"></line></svg>");
        return linkIcon;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        QueryParameters queryParameters = event.getLocation().getQueryParameters();
        
        if (queryParameters.getParameters().containsKey("service") && 
            queryParameters.getParameters().containsKey("serviceType")) {
            
            String serviceValue = queryParameters.getParameters().get("service").get(0);
            String serviceTypeValue = queryParameters.getParameters().get("serviceType").get(0);
            
            // Set the values in the text fields
            service.setValue(serviceValue);
            serviceType.setValue(serviceTypeValue);
            
            // Make the fields visible
            service.setVisible(true);
            serviceType.setVisible(true);
        }else{
            service.setVisible(false);
            serviceType.setVisible(false);
        }
    }
}
