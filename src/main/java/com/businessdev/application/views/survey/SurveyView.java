package com.businessdev.application.views.survey;

import com.businessdev.application.views.welcome.MainLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@PageTitle("survey")
@Route(value = "survey", layout = MainLayout.class)
public class SurveyView extends VerticalLayout {
    
    public SurveyView() {
        getStyle().set("margin-top", "70px");
        add(surveyFormContainer());
    }

    private VerticalLayout surveyFormContainer() {
        // Survey form components
        VerticalLayout surveyFormContainer = new VerticalLayout();
        surveyFormContainer.setSpacing(true);
        surveyFormContainer.setPadding(true);
        surveyFormContainer.setWidthFull();
        surveyFormContainer.getStyle().set("display", "flex")
            .set("flex-direction", "column")
            .set("align-items", "center")
            .set("justify-content", "center")
            .set("padding", "10px");
        
        VerticalLayout surveyFormLayout = new VerticalLayout();
        surveyFormLayout.setSpacing(true);
        surveyFormLayout.setPadding(true);
        surveyFormLayout.setWidthFull();
        surveyFormLayout.getStyle().set("display", "flex")
            .set("flex-direction", "column")
            .set("align-items", "start")
            .set("justify-content", "start")
            .set("max-width", "800px")
            .set("border-radius", "10px")
            .set("height", "100%")
            .set("padding", "10px");
        
        // Add survey form details
        H1 surveyHeading = new H1("Survey");
        surveyHeading.getStyle().set("font-size", "24px");
        surveyHeading.getStyle().set("font-weight", "bold");
        surveyFormLayout.add(surveyHeading);
        
        //add a drop down field for the survey type
        ComboBox<String> surveyTypeDropdown = new ComboBox<>("Survey type");
        surveyTypeDropdown.setItems(Arrays.asList("Consumables", "Non-consumables", "Services", "Hobby or interest","Clinical diagnosis"));
        surveyTypeDropdown.setWidthFull();
        surveyTypeDropdown.getStyle().set("margin-bottom", "10px")
        .set("min-width", "210px")
        .set("flex", "1")
        .set("padding", "0");

        //add combobox for type of consumable products, non consumable products or service depending on the survey type choice chosen. If nothing is chosen it should just be "Type"
        // Help: Dynamically update the "Type" ComboBox based on the selected survey type.
        ComboBox<String> typeOfPreviouslySelected = new ComboBox<>("Type of previously selected");
        typeOfPreviouslySelected.setWidthFull();
        typeOfPreviouslySelected.getStyle().set("margin-bottom", "10px")
        .set("min-width", "210px")
        .set("flex", "1")
        .set("padding", "0");

        // Update the items in the typeOfPreviouslySelected ComboBox when surveyTypeDropdown changes
        surveyTypeDropdown.addValueChangeListener(event -> {
            String selectedType = event.getValue();
            if (selectedType == null) {
                typeOfPreviouslySelected.setLabel("Type of previously selected");
                typeOfPreviouslySelected.setEnabled(true);
            } else if ("Consumables".equals(selectedType)) {
                typeOfPreviouslySelected.setLabel("Type of " + selectedType.toLowerCase());
                typeOfPreviouslySelected.setItems(Arrays.asList(
                    "Fruits", "Vegetables"
                ));
                typeOfPreviouslySelected.setEnabled(true);
            } else if ("Non-consumables".equals(selectedType)) {
                typeOfPreviouslySelected.setLabel("Type of " + selectedType.toLowerCase());
                typeOfPreviouslySelected.setItems(Arrays.asList(
                    "Clothing", "Electronics"
                ));
                typeOfPreviouslySelected.setEnabled(true);
            } else if ("Services".equals(selectedType)) {
                typeOfPreviouslySelected.setLabel("Type of " + selectedType.toLowerCase());
                typeOfPreviouslySelected.setItems(Arrays.asList(
                    "Cleaning", "Repair"
                ));
                typeOfPreviouslySelected.setEnabled(true);
            }else if ("Hobby or interest".equals(selectedType)) {
                typeOfPreviouslySelected.setLabel("Type of " + selectedType.toLowerCase());
                typeOfPreviouslySelected.setItems(Arrays.asList(
                    "Sports", "Music"
                ));
                typeOfPreviouslySelected.setEnabled(true);
            }else if ("Clinical diagnosis".equals(selectedType)) {
                typeOfPreviouslySelected.setLabel("Type of " + selectedType.toLowerCase());
                typeOfPreviouslySelected.setItems(Arrays.asList(
                    "Mental illness", "Physical illness"
                ));
                typeOfPreviouslySelected.setEnabled(true);
            }
             else {
                typeOfPreviouslySelected.setLabel("Type of previously selected");
                typeOfPreviouslySelected.setEnabled(true);
            }
        });

        // Now add combobox for kind of previously selected, with dynamic label and items based on previous selection
        ComboBox<String> kindOfPreviouslySelected = new ComboBox<>("Kind of previously selected");
        kindOfPreviouslySelected.setWidthFull();
        kindOfPreviouslySelected.getStyle().set("margin-bottom", "10px")
        .set("min-width", "210px")
        .set("flex", "1")
        .set("padding", "0");
        kindOfPreviouslySelected.setEnabled(true); // initially enabled until a type is chosen. If no type is chosen, it should be disabled.

        // Map of type to sample kinds
        Map<String, List<String>> kindOptions = new HashMap<>();
        kindOptions.put("Fruits", Arrays.asList("Grapes", "Apples", "Bananas", "Oranges", "Pears", "Plums", "Strawberries", "Cherries", "Raspberries", "Blueberries"));
        kindOptions.put("Vegetables", Arrays.asList("Carrots", "Broccoli", "Spinach", "Potatoes", "Tomatoes", "Cabbage", "Peppers", "Onions", "Lettuce", "Cucumbers"));
        kindOptions.put("Clothing", Arrays.asList("Shirts", "T-shirts", "Jeans", "Dresses", "Shoes", "Hats", "Gloves", "Scarves", "Belts", "Coats"));
        kindOptions.put("Electronics", Arrays.asList("Phones", "Laptops", "Tablets", "TVs", "Audio devices", "Cameras", "Smartwatches", "Other"));
        kindOptions.put("Cleaning", Arrays.asList("Detergents", "Floor cleaners", "Window cleaners", "Air fresheners", "Laundry products", "Other"));
        kindOptions.put("Repair", Arrays.asList("Electronics", "Furniture", "Appliances", "Other"));
        kindOptions.put("Sports", Arrays.asList("Basketball", "Soccer", "Tennis", "Golf", "Baseball", "Other"));
        kindOptions.put("Music", Arrays.asList("Songs", "Albums", "Artists", "Genres", "Playlists", "Other"));
        kindOptions.put("Mental illness", Arrays.asList("Depression", "Anxiety", "Bipolar disorder", "Schizophrenia", "Gooning"));
        kindOptions.put("Physical illness", Arrays.asList("Heart disease", "Diabetes", "Cancer", "Other"));
            

        // Listen for changes on typeOfPreviouslySelected to update kindOfPreviouslySelected
        typeOfPreviouslySelected.addValueChangeListener(event -> {
            String selectedType = event.getValue();
            if (selectedType != null && kindOptions.containsKey(selectedType)) {
                kindOfPreviouslySelected.setLabel("Kind of " + selectedType.toLowerCase());
                kindOfPreviouslySelected.setItems(kindOptions.get(selectedType));
                kindOfPreviouslySelected.setEnabled(true);
            } else if (selectedType != null) {
                // fallback for types not in the map
                kindOfPreviouslySelected.setLabel("Kind of " + selectedType.toLowerCase());
                kindOfPreviouslySelected.setItems(kindOptions.get(selectedType));
                kindOfPreviouslySelected.setEnabled(true);
            } else {
                kindOfPreviouslySelected.setLabel("Kind of previously selected");
                kindOfPreviouslySelected.setEnabled(true);
            }
        });
        

        //put this in its if statement and event listener for surveyTypeDropdown
        surveyTypeDropdown.addValueChangeListener(event -> {
            String selectedValue = event.getValue();
            if (selectedValue != null && !"Type of previously selected".equals(selectedValue)) {
                // Disable the dropdown since it's already in the URL (if that's the desired behavior)
                surveyTypeDropdown.setEnabled(false);
            }
        });

        
        surveyFormLayout.add(typeOfPreviouslySelected);
        // Optionally, disable the typeOfPreviouslySelected ComboBox after a selection is made, also disable the surveyTypeDropdown
        typeOfPreviouslySelected.addValueChangeListener(event -> {
            String selectedValue = event.getValue();
            if (selectedValue != null && !"Type of previously selected".equals(selectedValue)) {
                // Disable the dropdown since it's already in the URL (if that's the desired behavior)
                typeOfPreviouslySelected.setEnabled(false);
            }
        });
        
        
        surveyFormLayout.add(kindOfPreviouslySelected);
        kindOfPreviouslySelected.addValueChangeListener(event -> {
            String selectedValue = event.getValue();
            if (selectedValue != null && !"Kind of previously selected".equals(selectedValue)) {
                // Disable the dropdown since it's already in the URL (if that's the desired behavior)
                kindOfPreviouslySelected.setEnabled(false);
            }
        });

        //stage 1 of the survey form
        VerticalLayout stage1 = new VerticalLayout();
        stage1.setSpacing(true);
        stage1.setPadding(true);
        stage1.setWidthFull();
        stage1.getStyle().set("display", "flex")
            .set("gap", "10px")
            .set("flex-direction", "row")
            .set("align-items", "start")
            .set("justify-content", "start")
            .set("flex-wrap", "wrap");
        
        VerticalLayout stage2 = new VerticalLayout();
        stage2.setSpacing(true);
        stage2.setPadding(true);
        stage2.setWidthFull();
        stage2.getStyle().set("display", "flex")
            .set("gap", "10px")
            .set("flex-direction", "row")
            .set("align-items", "start")
            .set("justify-content", "start")
            .set("flex-wrap", "wrap");

        ComboBox<String> continents = new ComboBox<>("Continent");
        continents.setWidthFull();
        continents.getStyle().set("margin-bottom", "10px")
        .set("min-width", "210px")
        .set("flex", "1")
        .set("padding", "0");
        continents.setEnabled(true);

        ComboBox<String> countries = new ComboBox<>("Country");
        countries.setWidthFull();
        countries.getStyle().set("margin-bottom", "10px")
        .set("min-width", "210px")
        .set("flex", "1")
        .set("padding", "0");
        countries.setEnabled(true);

        ComboBox<String> provinceState = new ComboBox<>("Province/State");
        provinceState.setWidthFull();
        provinceState.getStyle().set("margin-bottom", "10px")
        .set("min-width", "210px")
        .set("flex", "1")
        .set("padding", "0");
        provinceState.setEnabled(true);

        ComboBox<String> cityTown = new ComboBox<>("City/Town");
        cityTown.setWidthFull();
        cityTown.getStyle().set("margin-bottom", "10px")
        .set("min-width", "210px")
        .set("flex", "1")
        .set("padding", "0");
        cityTown.setEnabled(true);

        ComboBox<String> ruralUrban = new ComboBox<>("Rural/Urban");
        ruralUrban.setWidthFull();
        ruralUrban.getStyle().set("margin-bottom", "10px")
        .set("min-width", "210px")
        .set("flex", "1")
        .set("padding", "0");
        ruralUrban.setEnabled(true);

        //settlement type
        ComboBox<String> settlementType = new ComboBox<>("Settlement Type");
        settlementType.setWidthFull();
        settlementType.getStyle().set("margin-bottom", "10px")
        .set("min-width", "210px")
        .set("flex", "1")
        .set("padding", "0");
        settlementType.setEnabled(true);

        VerticalLayout stage3 = new VerticalLayout();
        stage3.setSpacing(true);
        stage3.setPadding(true);
        stage3.setWidthFull();
        stage3.getStyle().set("display", "flex")
            .set("gap", "10px")
            .set("flex-direction", "row")
            .set("align-items", "start")
            .set("justify-content", "start")
            .set("flex-wrap", "wrap");
        
        //email address
        TextField emailAddress = new TextField("Email Address");
        emailAddress.setLabel("Email Address");
        emailAddress.setWidthFull();
        emailAddress.getStyle().set("margin-bottom", "10px")
        .set("min-width", "210px")
        .set("flex", "1")
        .set("padding", "0");
        emailAddress.setEnabled(true);

        //Adding to view
        stage1.add(surveyTypeDropdown, typeOfPreviouslySelected, kindOfPreviouslySelected);
        stage2.add(continents, countries, provinceState, cityTown, ruralUrban, settlementType);
        stage3.add(emailAddress);
        surveyFormLayout.add(stage1, stage2, stage3);
        
        // Create a container for the dynamic questions
        VerticalLayout questionsContainer = new VerticalLayout();
        questionsContainer.setVisible(false);
        
        // Function to check if questions should be shown
        Runnable updateQuestionsVisibility = () -> {
            boolean shouldShow = emailAddress.getValue() != null && 
                               !emailAddress.getValue().trim().isEmpty() && 
                               kindOfPreviouslySelected.getValue() != null;
            
            if (shouldShow && !questionsContainer.isVisible()) {
                questionsContainer.removeAll();
                if(surveyTypeDropdown.getValue().equals("Consumables")) {
                    questionsContainer.add(consumableQuestions(kindOfPreviouslySelected));
                } else if(surveyTypeDropdown.getValue().equals("Non-consumables")) {
                    questionsContainer.add(nonConsumableQuestions(kindOfPreviouslySelected));
                } else if(surveyTypeDropdown.getValue().equals("Services")) {
                    questionsContainer.add(serviceQuestions(kindOfPreviouslySelected));
                } else if(surveyTypeDropdown.getValue().equals("Hobby or interest")) {
                    questionsContainer.add(hobbyOrInterestQuestions(kindOfPreviouslySelected));
                }else if(surveyTypeDropdown.getValue().equals("Clinical diagnosis")) {
                    questionsContainer.add(clinicalDiagnosisQuestions(kindOfPreviouslySelected));
                }
                questionsContainer.setVisible(true);
            } else if (!shouldShow && questionsContainer.isVisible()) {
                questionsContainer.setVisible(false);
            }
        };
        
        // Add listeners to update visibility
        emailAddress.addValueChangeListener(e -> updateQuestionsVisibility.run());
        kindOfPreviouslySelected.addValueChangeListener(e -> updateQuestionsVisibility.run());
        
        surveyFormLayout.add(questionsContainer);
        surveyFormContainer.add(surveyFormLayout);
        add(surveyFormContainer);
        return surveyFormContainer;
    }

    private VerticalLayout consumableQuestions(ComboBox<String> kindOfPreviouslySelected) {
        VerticalLayout consumableQuestions = new VerticalLayout();
        consumableQuestions.setSpacing(true);
        consumableQuestions.setPadding(true);
        consumableQuestions.setWidthFull();
        consumableQuestions.getStyle().set("display", "flex")
            .set("gap", "20px")
            .set("flex-direction", "row")
            .set("align-items", "start")
            .set("justify-content", "start")
            .set("flex-wrap", "wrap");

        // Add a text for the question
        Span valueQuestionSpan = new Span("How do you value " + kindOfPreviouslySelected.getValue().toLowerCase() + "?");
        // Add a ComboBox for the answer
        ComboBox<String> valueAnswerComboBox = new ComboBox<>("Answer");
        valueAnswerComboBox.setItems(
            "Like it",
            "Don't like it"
        );
        valueAnswerComboBox.setWidthFull();
        valueAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout question1 = new VerticalLayout();
        question1.setSpacing(false);
        question1.setPadding(false);
        question1.setWidthFull();
        question1.add(valueQuestionSpan, valueAnswerComboBox);

        //add a combobox for the question "How often do you consume this product?" with the options "Daily", "Weekly", "Monthly", "Rarely", "Never", the question should be in a text separate from the combobox but the combobox is labelled "Answer"
        Span oftenQuestionSpan = new Span("How often do you consume this?");
        ComboBox<String> oftenAnswerComboBox = new ComboBox<>("Answer");
        oftenAnswerComboBox.setItems(
            "Daily",
            "Weekly",
            "Monthly",
            "Rarely",
            "Never"
        );
        oftenAnswerComboBox.setWidthFull();
        oftenAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question2 = new VerticalLayout();    
        question2.setSpacing(false);
        question2.setPadding(false);
        question2.setWidthFull();
        question2.add(oftenQuestionSpan, oftenAnswerComboBox);

        //add a combobox for the question "How much do you purchase per month (household or business total)?" with the options "Less than 5 kg", "5–20 kg", "20–50 kg", "50–100 kg", "More than 100 kg", the question should be in a text separate from the combobox but the combobox is labelled "Answer"
        Span amountQuestionSpan = new Span("How much " + kindOfPreviouslySelected.getValue().toLowerCase() + " do you buy per month?");
        ComboBox<String> amountAnswerComboBox = new ComboBox<>("Answer");
        amountAnswerComboBox.setItems(
            "Less than 5 kg",
            "5–20 kg",
            "20–50 kg",
            "50–100 kg",
            "More than 100 kg"
        );
        amountAnswerComboBox.setWidthFull();
        amountAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout question3 = new VerticalLayout();
        question3.setSpacing(false);
        question3.setPadding(false);
        question3.setWidthFull();
        question3.add(amountQuestionSpan, amountAnswerComboBox);

        //add a combobox for the question "Where do you usually buy your food?" with the options "Local markets", "Supermarkets", "Wholesalers", "Direct from farmers", "Online delivery platforms", "Restaurants/catering suppliers (for businesses)", the question should be in a text separate from the combobox but the combobox is labelled "Answer"
        Span whereQuestionSpan = new Span("Where do you usually buy this?");
        ComboBox<String> whereAnswerComboBox = new ComboBox<>("Answer");
        whereAnswerComboBox.setItems(
            "Local markets",
            "Supermarkets",
            "Wholesalers",
            "Direct from farmers",
            "Online delivery platforms",
            "Restaurants/catering suppliers (for businesses)"
        );
        whereAnswerComboBox.setWidthFull();
        whereAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout question4 = new VerticalLayout();
        question4.setSpacing(false);
        question4.setPadding(false);
        question4.setWidthFull();
        question4.add(whereQuestionSpan, whereAnswerComboBox);

        //add a combobox for the question "Do you grow or produce any of these foods yourself?" with the options "Yes (for home use)", "Yes (for commercial sale)", "No", the question should be in a text separate from the combobox but the combobox is labelled "Answer"
        Span produceQuestionSpan = new Span("Do you grow or produce " + kindOfPreviouslySelected.getValue().toLowerCase() + " yourself?");
        ComboBox<String> produceAnswerComboBox = new ComboBox<>("Answer");
        produceAnswerComboBox.setItems(
            "Yes (for home use)",
            "Yes (for commercial sale)",
            "No"
        );
        
        produceAnswerComboBox.setWidthFull();
        produceAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout question5 = new VerticalLayout();
        question5.setSpacing(false);
        question5.setPadding(false);
        question5.setWidthFull();
        question5.add(produceQuestionSpan, produceAnswerComboBox);
        //add a combobox for the question "What most influences your decision to buy this product?" with the options "Price", "Availability", "Quality/freshness", "Brand or source", "Convenience (ready-made)", "Nutritional value/health", "Family or cultural preference", "Environmental or ethical reasons", the question should be in a text separate from the combobox but the combobox is labelled "Answer"
        Span influenceQuestionSpan = new Span("What most influences your decision to buy this?");
        CheckboxGroup<String> influenceAnswerCheckboxGroup = new CheckboxGroup<>();
        influenceAnswerCheckboxGroup.setLabel("Answer");
        influenceAnswerCheckboxGroup.setItems(
            "Price",
            "Availability",
            "Quality/freshness",
            "Brand or source",
            "Convenience (ready-made)",
            "Nutritional value/health",
            "Family or cultural preference",
            "Environmental or ethical reasons"
        );
        influenceAnswerCheckboxGroup.getStyle().set("min-width", "210px");
        influenceAnswerCheckboxGroup.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        VerticalLayout question6 = new VerticalLayout();
        question6.setSpacing(false);
        question6.setPadding(false);
        question6.setWidthFull();
        question6.add(influenceQuestionSpan, influenceAnswerCheckboxGroup);
        //add a combobox for the question "What would make you buy more often?" with the options "Lower prices", "Better quality", "Greater availability", "More variety", "More local/organic options", "More processed/ready-to-cook options", "Nothing — I already buy enough", the question should be in a text separate from the combobox but the combobox is labelled "Answer"
        Span makeMoreQuestionSpan = new Span("What would make you buy more often?");
        ComboBox<String> makeMoreAnswerComboBox = new ComboBox<>("Answer");
        makeMoreAnswerComboBox.setItems(
            "Lower prices",
            "Better quality",
            "Greater availability",
            "More variety",
            "More local/organic options",
            "More processed/ready-to-cook or eat options",
            "Nothing"
        );
        makeMoreAnswerComboBox.setWidthFull();
        makeMoreAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout question7 = new VerticalLayout();
        question7.setSpacing(false);
        question7.setPadding(false);
        question7.setWidthFull();
        question7.add(makeMoreQuestionSpan, makeMoreAnswerComboBox);
        //add a combobox for the question "If prices rise significantly, how would your consumption change?" with the options "I would reduce consumption", "I would switch to cheaper alternatives", "I would continue buying the same amount", "I would grow/produce more myself", the question should be in a text separate from the combobox but the combobox is labelled "Answer"
        Span changeQuestionSpan = new Span("If prices rise significantly, how would your consumption change?");
        ComboBox<String> changeAnswerComboBox = new ComboBox<>("Answer");
        changeAnswerComboBox.setItems(
            "I would reduce consumption",
            "I would switch to cheaper alternatives",
            "I would continue buying the same amount",
            "I would grow/produce more myself"
        );
        changeAnswerComboBox.setWidthFull();
        changeAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout question8 = new VerticalLayout();
        question8.setSpacing(false);
        question8.setPadding(false);
        question8.setWidthFull();
        question8.add(changeQuestionSpan, changeAnswerComboBox);
        
        //add a combobox for the question "Your gender" with the options "Male", "Female", the question should be in a text separate from the combobox but the combobox is labelled "Answer"
        Span genderQuestionSpan = new Span("Your gender");
        ComboBox<String> genderAnswerComboBox = new ComboBox<>("Answer");
        genderAnswerComboBox.setItems(
            "Male",
            "Female"
        );
        genderAnswerComboBox.setWidthFull();
        genderAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout question10 = new VerticalLayout();
        question10.setSpacing(false);
        question10.setPadding(false);
        question10.setWidthFull();
        question10.add(genderQuestionSpan, genderAnswerComboBox);

        //add a combobox for the question "Do you expect your household/business food consumption to increase, decrease, or stay the same in the next 5 years?" with the options "Increase", "Decrease", "Stay the same", the question should be in a text separate from the combobox but the combobox is labelled "Answer"
        Span consumptionQuestionSpan = new Span("How do you expect your household " + kindOfPreviouslySelected.getValue().toLowerCase() + " consumption to change in the next 5 years?");
        ComboBox<String> consumptionAnswerComboBox = new ComboBox<>("Answer");
        consumptionAnswerComboBox.setItems(
            "Increase",
            "Decrease",
            "Stay the same"
        );
        consumptionAnswerComboBox.setWidthFull();
        consumptionAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout question11 = new VerticalLayout();
        question11.setSpacing(false);
        question11.setPadding(false);
        question11.setWidthFull();
        question11.add(consumptionQuestionSpan, consumptionAnswerComboBox);
        
        //Age group
        Span ageQuestionSpan = new Span("Your age group");
        ComboBox<String> ageAnswerComboBox = new ComboBox<>("Answer");
        ageAnswerComboBox.setItems(
            "Under 18",
            "18–25",
            "26–40",
            "41–60",
            "Over 60"
        );
        ageAnswerComboBox.setWidthFull();
        ageAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout question12 = new VerticalLayout();
        question12.setSpacing(false);
        question12.setPadding(false);
        question12.setWidthFull();
        question12.add(ageQuestionSpan, ageAnswerComboBox);

        //Household size / Business size
        Span sizeQuestionSpan = new Span("Your household size");
        ComboBox<String> sizeAnswerComboBox = new ComboBox<>("Answer");
        sizeAnswerComboBox.setItems(
            "1–2 people",
            "3–5 people",
            "6+ people"
        );
        sizeAnswerComboBox.setWidthFull();
        sizeAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout question13 = new VerticalLayout();
        question13.setSpacing(false);
        question13.setPadding(false);
        question13.setWidthFull();
        question13.add(sizeQuestionSpan, sizeAnswerComboBox);

        //Income or budget range (optional) 
        Span incomeQuestionSpan = new Span("Your income or budget range");
        ComboBox<String> incomeAnswerComboBox = new ComboBox<>("Answer");
        incomeAnswerComboBox.setItems(
            "Low",
            "Middle",
            "High"
        );
        incomeAnswerComboBox.setWidthFull();
        incomeAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout question14 = new VerticalLayout();
        question14.setSpacing(false);
        question14.setPadding(false);
        question14.setWidthFull();
        question14.add(incomeQuestionSpan, incomeAnswerComboBox);

        //Income or budget range (optional) 
        Span employmentQuestionSpan = new Span("Your employment status");
        ComboBox<String> employmentAnswerComboBox = new ComboBox<>("Answer");
        employmentAnswerComboBox.setItems(
            "Employed",
            "Unemployed",
            "Retired"
        );
        employmentAnswerComboBox.setWidthFull();
        employmentAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout employmentQuestion = new VerticalLayout();
        employmentQuestion.setSpacing(false);
        employmentQuestion.setPadding(false);
        employmentQuestion.setWidthFull();
        employmentQuestion.add(employmentQuestionSpan, employmentAnswerComboBox);

        // Add a combobox question for "Are you allergic to this product?" with yes and no options
        Span allergyQuestionSpan = new Span("Are you allergic to " + kindOfPreviouslySelected.getValue().toLowerCase() + "?");
        ComboBox<String> allergyAnswerComboBox = new ComboBox<>("Answer");
        allergyAnswerComboBox.setItems("Yes", "No");
        allergyAnswerComboBox.setWidthFull();
        allergyAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout allergyQuestion = new VerticalLayout();
        allergyQuestion.setSpacing(false);
        allergyQuestion.setPadding(false);
        allergyQuestion.setWidthFull();
        allergyQuestion.add(allergyQuestionSpan, allergyAnswerComboBox);
        

        // Create a container for the dynamic questions
        VerticalLayout consumerEat = new VerticalLayout();
        consumerEat.setVisible(false);
        
        // Function to check if questions should be shown
        Runnable updateQuestionsVisibility = () -> {
            boolean shouldShow = oftenAnswerComboBox.getValue() == "Daily" || oftenAnswerComboBox.getValue() == "Weekly" || oftenAnswerComboBox.getValue() == "Monthly" || oftenAnswerComboBox.getValue() == "Rarely";
            
            if (shouldShow) {
                consumerEat.removeAll();
                consumerEat.add(question3, question4, question5, question6, question7, question8, question11);
                consumerEat.setVisible(true);
            } else if (!shouldShow) {
                consumerEat.setVisible(false);
            }
        };
        
        // Add listeners to update visibility
        oftenAnswerComboBox.addValueChangeListener(e -> updateQuestionsVisibility.run());
        
        consumableQuestions.add(question1, question2, allergyQuestion, consumerEat, question10, question12, question13, question14, employmentQuestion);
        return consumableQuestions;
    }

    private VerticalLayout nonConsumableQuestions(ComboBox<String> kindOfPreviouslySelected) {
        VerticalLayout nonConsumableQuestions = new VerticalLayout();
        nonConsumableQuestions.setSpacing(true);
        nonConsumableQuestions.setPadding(true);
        nonConsumableQuestions.setWidthFull();
        nonConsumableQuestions.getStyle().set("display", "flex")
            .set("gap", "20px")
            .set("flex-direction", "row")
            .set("align-items", "start")
            .set("justify-content", "start")
            .set("flex-wrap", "wrap");

        // Add a text for the first question
        Span valueQuestionSpan = new Span("How do you value this product?"); //use surveyFormContainer() to get data from kindOfPreviouslySelected combobox and create a question like "How do you value "kindOfPreviouslySelected"?"
        String selectedKind = kindOfPreviouslySelected.getValue() != null ? kindOfPreviouslySelected.getValue().toLowerCase() : null;
        valueQuestionSpan.setText("How do you value " + selectedKind + "?");
        // Add a ComboBox for the answer
        ComboBox<String> valueAnswerComboBox = new ComboBox<>("Answer");
        valueAnswerComboBox.setItems(
            "Like it",
            "Don't like it"
        );
        valueAnswerComboBox.setWidthFull();
        valueAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout question1 = new VerticalLayout();
        question1.setSpacing(false);
        question1.setPadding(false);
        question1.setWidthFull();
        question1.add(valueQuestionSpan, valueAnswerComboBox);

        //add a combobox for the question "How often do you consume this product?" with the options "Daily", "Weekly", "Monthly", "Rarely", "Never", the question should be in a text separate from the combobox but the combobox is labelled "Answer"
        Span feelQuestionSpan = new Span("What do you like about this product?");
        ComboBox<String> feelAnswerComboBox = new ComboBox<>("Answer");
        feelAnswerComboBox.setItems(
            "Quality and durability",
            "Brand and reputation",
            "Latest technology or design",
            "Budget-friendly",
            "Sustainable or eco-friendly"
        );
        feelAnswerComboBox.setWidthFull();
        feelAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question2 = new VerticalLayout();    
        question2.setSpacing(false);
        question2.setPadding(false);
        question2.setWidthFull();
        question2.add(feelQuestionSpan, feelAnswerComboBox);

        Span oftenQuestionSpan = new Span("How often you use this product?");
        ComboBox<String> oftenAnswerComboBox = new ComboBox<>("Answer");
        oftenAnswerComboBox.setItems(
            "Daily",
            "Weekly",
            "Monthly",
            "Rarely",
            "Never"
        );
        oftenAnswerComboBox.setWidthFull();
        oftenAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question3 = new VerticalLayout();    
        question3.setSpacing(false);
        question3.setPadding(false);
        question3.setWidthFull();
        question3.add(oftenQuestionSpan, oftenAnswerComboBox);

        Span oftenBuyQuestionSpan = new Span("How often do you buy this product?");
        ComboBox<String> oftenBuyAnswerComboBox = new ComboBox<>("Answer");
        oftenBuyAnswerComboBox.setItems(
            "Every 6 months",
            "Every 1–2 years",
            "Every 3–5 years",
            "Rarely (only when old one teres or breaks)",
            "Never bought"
        );
        oftenBuyAnswerComboBox.setWidthFull();
        oftenBuyAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question4 = new VerticalLayout();    
        question4.setSpacing(false);
        question4.setPadding(false);
        question4.setWidthFull();
        question4.add(oftenBuyQuestionSpan, oftenBuyAnswerComboBox);

        Span spendingQuestionSpan = new Span("What is your typical spending range for this product?");
        ComboBox<String> spendingAnswerComboBox = new ComboBox<>("Answer");
        spendingAnswerComboBox.setItems(
            "Less than R1,000",
            "R1,000 – R5,000",
            "R5,000 – R20,000",
            "R20,000 – R50,000",
            "More than R50,000"
        );
        spendingAnswerComboBox.setWidthFull();
        spendingAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question5 = new VerticalLayout();    
        question5.setSpacing(false);
        question5.setPadding(false);
        question5.setWidthFull();
        question5.add(spendingQuestionSpan, spendingAnswerComboBox);

        Span outdatedQuestionSpan = new Span("What do you usually do with old, replaced or outdated product?");
        ComboBox<String> outdatedAnswerComboBox = new ComboBox<>("Answer");
        outdatedAnswerComboBox.setItems(
            "Keep as backup",
            "Sell or trade-in",
            "Recycle or donate",
            "Throw away"
        );
        outdatedAnswerComboBox.setWidthFull();
        outdatedAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question6 = new VerticalLayout();    
        question6.setSpacing(false);
        question6.setPadding(false);
        question6.setWidthFull();
        question6.add(outdatedQuestionSpan, outdatedAnswerComboBox);

        Span usuallyBuyPlaceQuestionSpan = new Span("Where do you usually buy this product?");
        ComboBox<String> usuallyBuyPlaceAnswerComboBox = new ComboBox<>("Answer");
        usuallyBuyPlaceAnswerComboBox.setItems(
            "Physical retail stores",
            "Online stores / e-commerce platforms",
            "Direct from manufacturer or brand store",
            "Second-hand or refurbished markets",
            "Local small businesses or markets"
        );
        usuallyBuyPlaceAnswerComboBox.setWidthFull();
        usuallyBuyPlaceAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question7 = new VerticalLayout();    
        question7.setSpacing(false);
        question7.setPadding(false);
        question7.setWidthFull();
        question7.add(usuallyBuyPlaceQuestionSpan, usuallyBuyPlaceAnswerComboBox);

        Span influenceQuestionSpan = new Span("What most influences your decision to buy this product?");
        ComboBox<String> influenceAnswerComboBox = new ComboBox<>("Answer");
        influenceAnswerComboBox.setItems(
            "Price",
            "Brand reputation",
            "Quality/durability",
            "Features or design",
            "Availability and convenience",
            "Warranty or after-sales service",
            "Environmental impact",
            "Peer or family recommendations"
        );
        influenceAnswerComboBox.setWidthFull();
        influenceAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question8 = new VerticalLayout();    
        question8.setSpacing(false);
        question8.setPadding(false);
        question8.setWidthFull();
        question8.add(influenceQuestionSpan, influenceAnswerComboBox);

        Span frequentlyQuestionSpan = new Span("What would make you buy this more frequently or upgrade sooner?");
        ComboBox<String> frequentlyAnswerComboBox = new ComboBox<>("Answer");
        frequentlyAnswerComboBox.setItems(
            "Lower prices or discounts",
            "New and innovative features",
            "Trade-in or upgrade programs",
            "Better after-sales support",
            "Sustainable or recyclable options",
            "Nothing"
        );
        frequentlyAnswerComboBox.setWidthFull();
        frequentlyAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question9 = new VerticalLayout();    
        question9.setSpacing(false);
        question9.setPadding(false);
        question9.setWidthFull();
        question9.add(frequentlyQuestionSpan, frequentlyAnswerComboBox);

        Span expensiveQuestionSpan = new Span("If this product becomes significantly more expensive, what would you do?");
        ComboBox<String> expensiveAnswerComboBox = new ComboBox<>("Answer");
        expensiveAnswerComboBox.setItems(
            "Delay the purchase",
            "Choose a cheaper alternative",
            "Switch to second-hand",
            "Still buy the same product",
            "Stop buying"
        );
        expensiveAnswerComboBox.setWidthFull();
        expensiveAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question10 = new VerticalLayout();    
        question10.setSpacing(false);
        question10.setPadding(false);
        question10.setWidthFull();
        question10.add(expensiveQuestionSpan, expensiveAnswerComboBox);

        Span planningQuestionSpan = new Span("Are you planning to purchase or upgrade any major products in the next 12 months?");
        ComboBox<String> planningAnswerComboBox = new ComboBox<>("Answer");
        planningAnswerComboBox.setItems(
            "Yes, definitely",
            "Maybe",
            "No"
        );
        planningAnswerComboBox.setWidthFull();
        planningAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question11 = new VerticalLayout();    
        question11.setSpacing(false);
        question11.setPadding(false);
        question11.setWidthFull();
        question11.add(planningQuestionSpan, planningAnswerComboBox);

        Span payMoreQuestionSpan = new Span("Would you pay more for products that are:");
        ComboBox<String> payMoreAnswerComboBox = new ComboBox<>("Answer");
        payMoreAnswerComboBox.setItems(
            "Higher quality and longer lasting",
            "Eco-friendly or sustainably made",
            "Locally produced",
            "Smart/AI-enabled",
            "Backed by excellent service and warranty"
        );
        payMoreAnswerComboBox.setWidthFull();
        payMoreAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question12 = new VerticalLayout();    
        question12.setSpacing(false);
        question12.setPadding(false);
        question12.setWidthFull();
        question12.add(payMoreQuestionSpan, payMoreAnswerComboBox);

        Span rentingQuestionSpan = new Span("Are you interested in renting or subscribing to products instead of owning them (e.g., renting appliances or vehicles)?");
        ComboBox<String> rentingAnswerComboBox = new ComboBox<>("Answer");
        rentingAnswerComboBox.setItems(
            "Yes",
            "No"
        );
        rentingAnswerComboBox.setWidthFull();
        rentingAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question13 = new VerticalLayout();    
        question13.setSpacing(false);
        question13.setPadding(false);
        question13.setWidthFull();
        question13.add(rentingQuestionSpan, rentingAnswerComboBox);

        //add a combobox for the question "Your gender" with the options "Male", "Female", the question should be in a text separate from the combobox but the combobox is labelled "Answer"
        Span genderQuestionSpan = new Span("Your gender");
        ComboBox<String> genderAnswerComboBox = new ComboBox<>("Answer");
        genderAnswerComboBox.setItems(
            "Male",
            "Female"
        );
        genderAnswerComboBox.setWidthFull();
        genderAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout genderQuestion = new VerticalLayout();
        genderQuestion.setSpacing(false);
        genderQuestion.setPadding(false);
        genderQuestion.setWidthFull();
        genderQuestion.add(genderQuestionSpan, genderAnswerComboBox);

        //Age group
        Span ageQuestionSpan = new Span("Your age group");
        ComboBox<String> ageAnswerComboBox = new ComboBox<>("Answer");
        ageAnswerComboBox.setItems(
            "Under 18",
            "18–25",
            "26–40",
            "41–60",
            "Over 60"
        );
        ageAnswerComboBox.setWidthFull();
        ageAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout question14 = new VerticalLayout();
        question14.setSpacing(false);
        question14.setPadding(false);
        question14.setWidthFull();
        question14.add(ageQuestionSpan, ageAnswerComboBox);
        
        //Household size / Business size
        Span sizeQuestionSpan = new Span("Your household size");
        ComboBox<String> sizeAnswerComboBox = new ComboBox<>("Answer");
        sizeAnswerComboBox.setItems(
            "1–2 people",
            "3–5 people",
            "6+ people"
        );
        sizeAnswerComboBox.setWidthFull();
        sizeAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout question15 = new VerticalLayout();
        question15.setSpacing(false);
        question15.setPadding(false);
        question15.setWidthFull();
        question15.add(sizeQuestionSpan, sizeAnswerComboBox);

        //Income or budget range (optional) 
        Span incomeQuestionSpan = new Span("Your income or budget range");
        ComboBox<String> incomeAnswerComboBox = new ComboBox<>("Answer");
        incomeAnswerComboBox.setItems(
            "Low",
            "Middle",
            "High"
        );
        incomeAnswerComboBox.setWidthFull();
        incomeAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout question16 = new VerticalLayout();
        question16.setSpacing(false);
        question16.setPadding(false);
        question16.setWidthFull();
        question16.add(incomeQuestionSpan, incomeAnswerComboBox);

        //Income or budget range (optional) 
        Span employmentQuestionSpan = new Span("Your employment status");
        ComboBox<String> employmentAnswerComboBox = new ComboBox<>("Answer");
        employmentAnswerComboBox.setItems(
            "Employed",
            "Unemployed",
            "Retired"
        );
        employmentAnswerComboBox.setWidthFull();
        employmentAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout employmentQuestion = new VerticalLayout();
        employmentQuestion.setSpacing(false);
        employmentQuestion.setPadding(false);
        employmentQuestion.setWidthFull();
        employmentQuestion.add(employmentQuestionSpan, employmentAnswerComboBox);

        nonConsumableQuestions.add(question1, question2, question3, question4, question5, question6, question7, question8, question9, question10, question11, question12, question13, genderQuestion, question14, question15, question16, employmentQuestion);
        return nonConsumableQuestions;
    }

    private VerticalLayout serviceQuestions(ComboBox<String> kindOfPreviouslySelected) {
        VerticalLayout serviceQuestions = new VerticalLayout();
        serviceQuestions.setSpacing(true);
        serviceQuestions.setPadding(true);
        serviceQuestions.setWidthFull();
        serviceQuestions.getStyle().set("display", "flex")
            .set("gap", "20px")
            .set("flex-direction", "row")
            .set("align-items", "start")
            .set("justify-content", "start")
            .set("flex-wrap", "wrap");
        
        // Add a text for the first question
        Span question1Span = new Span("How do you value this product?"); //use surveyFormContainer() to get data from kindOfPreviouslySelected combobox and create a question like "How do you value "kindOfPreviouslySelected"?"
        String selectedKind = kindOfPreviouslySelected.getValue() != null ? kindOfPreviouslySelected.getValue().toLowerCase() : null;
        question1Span.setText("How important is " + selectedKind + " in your life?");
        // Add a ComboBox for the answer
        ComboBox<String> question1ComboBox = new ComboBox<>("Answer");
        question1ComboBox.setItems(
            "Very important",
            "Not important"
        );
        question1ComboBox.setWidthFull();
        question1ComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout question1 = new VerticalLayout();
        question1.setSpacing(false);
        question1.setPadding(false);
        question1.setWidthFull();
        question1.add(question1Span, question1ComboBox);

        Span question2Span = new Span("How often do you use this service?");
        ComboBox<String> question2ComboBox = new ComboBox<>("Answer");
        question2ComboBox.setItems(
            "Daily",
            "Weekly",
            "Monthly",
            "Occasionally",
            "Rarely"
        );
        question2ComboBox.setWidthFull();
        question2ComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question2 = new VerticalLayout();    
        question2.setSpacing(false);
        question2.setPadding(false);
        question2.setWidthFull();
        question2.add(question2Span, question2ComboBox);

        Span question3Span = new Span("What influences you to use this service?");
        ComboBox<String> question3ComboBox = new ComboBox<>("Answer");
        question3ComboBox.setItems(
            "Personal recommendation",
            "Online search or ads",
            "Social media",
            "Government or institutional assignment",
            "Peer pressure"
        );
        question3ComboBox.setWidthFull();
        question3ComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question3 = new VerticalLayout();    
        question3.setSpacing(false);
        question3.setPadding(false);
        question3.setWidthFull();
        question3.add(question3Span, question3ComboBox);

        Span question4Span = new Span("Why do you use this service?");
        ComboBox<String> question4ComboBox = new ComboBox<>("Answer");
        question4ComboBox.setItems(
            "Price or fees",
            "Quality and reliability",
            "Customer support and responsiveness",
            "Brand reputation",
            "Ease of access or convenience",
            "Extra features / added value",
            "Trust / security / privacy",
            "Flexibility or customization"
        );
        question4ComboBox.setWidthFull();
        question4ComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question4 = new VerticalLayout();    
        question4.setSpacing(false);
        question4.setPadding(false);
        question4.setWidthFull();
        question4.add(question4Span, question4ComboBox);

        Span question5Span = new Span("How long have you been using this service");
        ComboBox<String> question5ComboBox = new ComboBox<>("Answer");
        question5ComboBox.setItems(
            "Less than 6 months",
            "6 months – 1 year",
            "1–3 years",
            "3–5 years",
            "More than 5 years"
        );
        question5ComboBox.setWidthFull();
        question5ComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question5 = new VerticalLayout();    
        question5.setSpacing(false);
        question5.setPadding(false);
        question5.setWidthFull();
        question5.add(question5Span, question5ComboBox);

        Span question6Span = new Span("Would you switch this service for another provider?");
        ComboBox<String> question6ComboBox = new ComboBox<>("Answer");
        question6ComboBox.setItems(
            "Yes",
            "No"
        );
        question6ComboBox.setWidthFull();
        question6ComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question6 = new VerticalLayout();    
        question6.setSpacing(false);
        question6.setPadding(false);
        question6.setWidthFull();
        question6.add(question6Span, question6ComboBox);

        Span question8Span = new Span("Why would you?");
        ComboBox<String> question8ComboBox = new ComboBox<>("Answer");
        question8ComboBox.setItems(
            "Better price elsewhere",
            "Poor quality or reliability",
            "Poor customer service",
            "Better features or benefits",
            "If it relocate or no longer supported",
            "Faster service",
            "More features / benefits",
            "Better customer support",
            "Stronger privacy / security policies"
        );
        question8ComboBox.setWidthFull();
        question8ComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question8 = new VerticalLayout();    
        question8.setSpacing(false);
        question8.setPadding(false);
        question8.setWidthFull();
        question8.add(question8Span, question8ComboBox);

        Span question9Span = new Span("How likely are you to recommend this to others");
        ComboBox<String> question9ComboBox = new ComboBox<>("Answer");
        question9ComboBox.setItems(
            "Very likely",
            "Very unlikely"
        );
        question9ComboBox.setWidthFull();
        question9ComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question9 = new VerticalLayout();    
        question9.setSpacing(false);
        question9.setPadding(false);
        question9.setWidthFull();
        question9.add(question9Span, question9ComboBox);

        Span question10Span = new Span("Would you pay more for this service if it is");
        ComboBox<String> question10ComboBox = new ComboBox<>("Answer");
        question10ComboBox.setItems(
            "Significantly higher quality",
            "Eco-friendly or sustainable",
            "Local or community-owned",
            "Highly customizable or personalized",
            "Never"
        );
        question10ComboBox.setWidthFull();
        question10ComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question10 = new VerticalLayout();    
        question10.setSpacing(false);
        question10.setPadding(false);
        question10.setWidthFull();
        question10.add(question10Span, question10ComboBox);

        Span question11Span = new Span("How do you expect your service usage in the next 5 years?");
        ComboBox<String> question11ComboBox = new ComboBox<>("Answer");
        question11ComboBox.setItems(
            "Increase",
            "Decrease",
            "Stay the same",
            "Stop"
        );
        question11ComboBox.setWidthFull();
        question11ComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question11 = new VerticalLayout();    
        question11.setSpacing(false);
        question11.setPadding(false);
        question11.setWidthFull();
        question11.add(question11Span, question11ComboBox);

        //add a combobox for the question "Your gender" with the options "Male", "Female", the question should be in a text separate from the combobox but the combobox is labelled "Answer"
        Span genderQuestionSpan = new Span("Your gender");
        ComboBox<String> genderAnswerComboBox = new ComboBox<>("Answer");
        genderAnswerComboBox.setItems(
            "Male",
            "Female"
        );
        genderAnswerComboBox.setWidthFull();
        genderAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout genderQuestion = new VerticalLayout();
        genderQuestion.setSpacing(false);
        genderQuestion.setPadding(false);
        genderQuestion.setWidthFull();
        genderQuestion.add(genderQuestionSpan, genderAnswerComboBox);

        //Age group
        Span ageQuestionSpan = new Span("Your age group");
        ComboBox<String> ageAnswerComboBox = new ComboBox<>("Answer");
        ageAnswerComboBox.setItems(
            "Under 18",
            "18–25",
            "26–40",
            "41–60",
            "Over 60"
        );
        ageAnswerComboBox.setWidthFull();
        ageAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout question12 = new VerticalLayout();
        question12.setSpacing(false);
        question12.setPadding(false);
        question12.setWidthFull();
        question12.add(ageQuestionSpan, ageAnswerComboBox);
        
        //Household size / Business size
        Span sizeQuestionSpan = new Span("Your household size");
        ComboBox<String> sizeAnswerComboBox = new ComboBox<>("Answer");
        sizeAnswerComboBox.setItems(
            "1–2 people",
            "3–5 people",
            "6+ people"
        );
        sizeAnswerComboBox.setWidthFull();
        sizeAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout question13 = new VerticalLayout();
        question13.setSpacing(false);
        question13.setPadding(false);
        question13.setWidthFull();
        question13.add(sizeQuestionSpan, sizeAnswerComboBox);

        //Income or budget range (optional) 
        Span incomeQuestionSpan = new Span("Your income or budget range");
        ComboBox<String> incomeAnswerComboBox = new ComboBox<>("Answer");
        incomeAnswerComboBox.setItems(
            "Low",
            "Middle",
            "High"
        );
        incomeAnswerComboBox.setWidthFull();
        incomeAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout question14 = new VerticalLayout();
        question14.setSpacing(false);
        question14.setPadding(false);
        question14.setWidthFull();
        question14.add(incomeQuestionSpan, incomeAnswerComboBox);

        //Income or budget range (optional) 
        Span employmentQuestionSpan = new Span("Your employment status");
        ComboBox<String> employmentAnswerComboBox = new ComboBox<>("Answer");
        employmentAnswerComboBox.setItems(
            "Employed",
            "Unemployed",
            "Retired"
        );
        employmentAnswerComboBox.setWidthFull();
        employmentAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout employmentQuestion = new VerticalLayout();
        employmentQuestion.setSpacing(false);
        employmentQuestion.setPadding(false);
        employmentQuestion.setWidthFull();
        employmentQuestion.add(employmentQuestionSpan, employmentAnswerComboBox);

        serviceQuestions.add(question1, question2, question3, question4, question5, question6, question8, question9, question10, question11, genderQuestion, question12, question13, question14, employmentQuestion);
        return serviceQuestions;
    }

    private VerticalLayout hobbyOrInterestQuestions(ComboBox<String> kindOfPreviouslySelected) {
        VerticalLayout hobbyOrInterestQuestions = new VerticalLayout();
        hobbyOrInterestQuestions.setSpacing(true);
        hobbyOrInterestQuestions.setPadding(true);
        hobbyOrInterestQuestions.setWidthFull();
        hobbyOrInterestQuestions.getStyle().set("display", "flex")
            .set("gap", "20px")
            .set("flex-direction", "row")
            .set("align-items", "start")
            .set("justify-content", "start")
            .set("flex-wrap", "wrap");

        // Add a text for the first question
        Span question1Span = new Span("How do you value this?"); //use surveyFormContainer() to get data from kindOfPreviouslySelected combobox and create a question like "How do you value "kindOfPreviouslySelected"?"
        String selectedKind = kindOfPreviouslySelected.getValue() != null ? kindOfPreviouslySelected.getValue().toLowerCase() : null;
        question1Span.setText("How important is " + selectedKind + " in your life?");
        // Add a ComboBox for the answer
        ComboBox<String> question1ComboBox = new ComboBox<>("Answer");
        question1ComboBox.setItems(
            "Very important",
            "Not important"
        );
        question1ComboBox.setWidthFull();
        question1ComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout question1 = new VerticalLayout();
        question1.setSpacing(false);
        question1.setPadding(false);
        question1.setWidthFull();
        question1.add(question1Span, question1ComboBox);

        Span question2Span = new Span("What do you enjoy most about it?");
        ComboBox<String> question2ComboBox = new ComboBox<>("Answer");
        question2ComboBox.setItems(
            "Creative expression and personal fulfillment",
            "Social connection and community building",
            "Learning new skills and knowledge",
            "Physical health and fitness benefits",
            "Stress relief and relaxation",
            "Achievement and competition",
            "Financial opportunities"
        );
        question2ComboBox.setWidthFull();
        question2ComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question2 = new VerticalLayout();    
        question2.setSpacing(false);
        question2.setPadding(false);
        question2.setWidthFull();
        question2.add(question2Span, question2ComboBox);

        Span question3Span = new Span("How much time do you typically spend on it?");
        ComboBox<String> question3ComboBox = new ComboBox<>("Answer");
        question3ComboBox.setItems(
            "Less than 2 hours",
            "2–5 hours",
            "5–10 hours",
            "10–20 hours",
            "More than 20 hours"
        );
        question3ComboBox.setWidthFull();
        question3ComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question3 = new VerticalLayout();    
        question3.setSpacing(false);
        question3.setPadding(false);
        question3.setWidthFull();
        question3.add(question3Span, question3ComboBox);

        Span question4Span = new Span("How often do you participate in this?");
        ComboBox<String> question4ComboBox = new ComboBox<>("Answer");
        question3ComboBox.setItems(
            "Daily",
            "Several times a week",
            "Weekly",
            "Monthly",
            "Occasionally/seasonally"
        );
        question4ComboBox.setWidthFull();
        question4ComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question4 = new VerticalLayout();    
        question4.setSpacing(false);
        question4.setPadding(false);
        question4.setWidthFull();
        question4.add(question4Span, question4ComboBox);

        Span question5Span = new Span("How do you prefer to engage in this?");
        ComboBox<String> question5ComboBox = new ComboBox<>("Answer");
        question5ComboBox.setItems(
            "Alone (solo activities)",
            "With friends or family",
            "In organized groups or clubs",
            "Online communities",
            "Mix of all the above"
        );
        question5ComboBox.setWidthFull();
        question5ComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question5 = new VerticalLayout();    
        question5.setSpacing(false);
        question5.setPadding(false);
        question5.setWidthFull();
        question5.add(question5Span, question5ComboBox);

        Span question6Span = new Span("How much do you typically spend on this?");
        ComboBox<String> question6ComboBox = new ComboBox<>("Answer");
        question6ComboBox.setItems(
            "No spending",
            "Less than R500",
            "R500 – R1,500",
            "R1,500 – R5,000",
            "R5,000 – R15,000",
            "More than R15,000"
        );
        question6ComboBox.setWidthFull();
        question6ComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question6 = new VerticalLayout();    
        question6.setSpacing(false);
        question6.setPadding(false);
        question6.setWidthFull();
        question6.add(question6Span, question6ComboBox);

        Span question7Span = new Span("What do or would you spend your money on for this?");
        ComboBox<String> question7ComboBox = new ComboBox<>("Answer");
        question7ComboBox.setItems(
            "Equipment and tools",
            "Materials and supplies",
            "Classes, courses, or lessons",
            "Events, competitions, or exhibitions",
            "Travel related to the hobby",
            "Online subscriptions or apps",
            "Books, magazines, or educational content"
        );
        question7ComboBox.setWidthFull();
        question7ComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question7 = new VerticalLayout();    
        question7.setSpacing(false);
        question7.setPadding(false);
        question7.setWidthFull();
        question7.add(question7Span, question7ComboBox);

        Span question8Span = new Span("How would you describe your skill level in this?");
        ComboBox<String> question8ComboBox = new ComboBox<>("Answer");
        question8ComboBox.setItems(
            "Beginner (just starting out)",
            "Novice (learning basics)",
            "Intermediate (comfortable with fundamentals)",
            "Advanced (skilled and experienced)",
            "Expert (highly proficient, possibly teaching others)"
        );
        question8ComboBox.setWidthFull();
        question8ComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question8 = new VerticalLayout();    
        question8.setSpacing(false);
        question8.setPadding(false);
        question8.setWidthFull();
        question8.add(question8Span, question8ComboBox);

        Span question9Span = new Span("What are your main goals with this hobby/interest?");
        ComboBox<String> question9ComboBox = new ComboBox<>("Answer");
        question9ComboBox.setItems(
            "Personal enjoyment and relaxation",
            "Skill improvement and mastery",
            "Social connection and networking",
            "Creative expression and artistic development",
            "Physical fitness and health",
            "Potential career or business opportunities",
            "Community contribution and teaching others"
        );
        question9ComboBox.setWidthFull();
        question9ComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question9 = new VerticalLayout();    
        question9.setSpacing(false);
        question9.setPadding(false);
        question9.setWidthFull();
        question9.add(question9Span, question9ComboBox);

        Span question10Span = new Span("How do you typically learn new skills or improve in this hobby/interest?");
        ComboBox<String> question10ComboBox = new ComboBox<>("Answer");
        question10ComboBox.setItems(
            "Self-taught through online resources",
            "YouTube tutorials and videos",
            "Online courses or webinars",
            "In-person classes or workshops",
            "Books, magazines, or written guides",
            "Learning from friends or mentors",
            "Trial and error experimentation"
        );
        question10ComboBox.setWidthFull();
        question10ComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question10 = new VerticalLayout();    
        question10.setSpacing(false);
        question10.setPadding(false);
        question10.setWidthFull();
        question10.add(question10Span, question10ComboBox);

        Span question11Span = new Span("How do you share your hobby/interest with others?");
        ComboBox<String> question11ComboBox = new ComboBox<>("Answer");
        question11ComboBox.setItems(
            "Social media posts and photos",
            "Personal blogs or websites",
            "Participating in exhibitions or shows",
            "Teaching or mentoring others",
            "Selling products or services",
            "I don't share publicly"
        );
        question11ComboBox.setWidthFull();
        question11ComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question11 = new VerticalLayout();    
        question11.setSpacing(false);
        question11.setPadding(false);
        question11.setWidthFull();
        question11.add(question11Span, question11ComboBox);

        Span question12Span = new Span("What would encourage you to be more involved in this hobby?");
        ComboBox<String> question12ComboBox = new ComboBox<>("Answer");
        question12ComboBox.setItems(
            "More local events and meetups",
            "Better online platforms and tools",
            "Mentorship or teaching opportunities",
            "Recognition or awards programs",
            "Networking and collaboration opportunities",
            "Nothing, I'm satisfied with current involvement"
        );
        question12ComboBox.setWidthFull();
        question12ComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question12 = new VerticalLayout();    
        question12.setSpacing(false);
        question12.setPadding(false);
        question12.setWidthFull();
        question12.add(question12Span, question12ComboBox);

        Span question13Span = new Span("Do you plan to increase your involvement in this in the next 2 years?");
        ComboBox<String> question13ComboBox = new ComboBox<>("Answer");
        question13ComboBox.setItems(
            "Yes, significantly more time and investment",
            "Yes, moderately more involvement",
            "Stay the same level",
            "Decrease involvement",
            "Not sure"
        );
        question13ComboBox.setWidthFull();
        question13ComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question13 = new VerticalLayout();    
        question13.setSpacing(false);
        question13.setPadding(false);
        question13.setWidthFull();
        question13.add(question13Span, question13ComboBox);

        Span question14Span = new Span("Are you interested in turning any of your hobbies into a business or side income?");
        ComboBox<String> question14ComboBox = new ComboBox<>("Answer");
        question14ComboBox.setItems(
            "Yes, actively pursuing this",
            "Yes, considering it for the future",
            "Maybe, if the right opportunity comes",
            "No, I prefer to keep hobbies separate from work",
            "No, not interested in monetizing"
        );
        question14ComboBox.setWidthFull();
        question14ComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question14 = new VerticalLayout();    
        question14.setSpacing(false);
        question14.setPadding(false);
        question14.setWidthFull();
        question14.add(question14Span, question14ComboBox);

        Span question15Span = new Span("If you were to intergrate this hobby/interest with anything else, what would it be?");
        ComboBox<String> question15ComboBox = new ComboBox<>("Answer");
        question15ComboBox.setItems(
            "Sports",
            "Music",
            "Art",
            "Reading",
            "Writing",
            "Gaming",
            "Photography",
            "Travel",
            "Health and fitness",
            "Productivity or business"
        );
        question15ComboBox.setWidthFull();
        question15ComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question15 = new VerticalLayout();    
        question15.setSpacing(false);
        question15.setPadding(false);
        question15.setWidthFull();
        question15.add(question15Span, question15ComboBox);

        //add a combobox for the question "Your gender" with the options "Male", "Female", the question should be in a text separate from the combobox but the combobox is labelled "Answer"
        Span genderQuestionSpan = new Span("Your gender");
        ComboBox<String> genderAnswerComboBox = new ComboBox<>("Answer");
        genderAnswerComboBox.setItems(
            "Male",
            "Female"
        );
        genderAnswerComboBox.setWidthFull();
        genderAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout genderQuestion = new VerticalLayout();
        genderQuestion.setSpacing(false);
        genderQuestion.setPadding(false);
        genderQuestion.setWidthFull();
        genderQuestion.add(genderQuestionSpan, genderAnswerComboBox);

        //Age group
        Span ageQuestionSpan = new Span("Your age group");
        ComboBox<String> ageAnswerComboBox = new ComboBox<>("Answer");
        ageAnswerComboBox.setItems(
            "Under 18",
            "18–25",
            "26–40",
            "41–60",
            "Over 60"
        );
        ageAnswerComboBox.setWidthFull();
        ageAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout question16 = new VerticalLayout();
        question16.setSpacing(false);
        question16.setPadding(false);
        question16.setWidthFull();
        question16.add(ageQuestionSpan, ageAnswerComboBox);
        
        //Household size / Business size
        Span sizeQuestionSpan = new Span("Your household size");
        ComboBox<String> sizeAnswerComboBox = new ComboBox<>("Answer");
        sizeAnswerComboBox.setItems(
            "1–2 people",
            "3–5 people",
            "6+ people"
        );
        sizeAnswerComboBox.setWidthFull();
        sizeAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout question17 = new VerticalLayout();
        question17.setSpacing(false);
        question17.setPadding(false);
        question17.setWidthFull();
        question17.add(sizeQuestionSpan, sizeAnswerComboBox);

        //Income or budget range (optional) 
        Span incomeQuestionSpan = new Span("Your income or budget range");
        ComboBox<String> incomeAnswerComboBox = new ComboBox<>("Answer");
        incomeAnswerComboBox.setItems(
            "Low",
            "Middle",
            "High"
        );
        incomeAnswerComboBox.setWidthFull();
        incomeAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout question18 = new VerticalLayout();
        question14.setSpacing(false);
        question18.setPadding(false);
        question18.setWidthFull();
        question18.add(incomeQuestionSpan, incomeAnswerComboBox);

        //Income or budget range (optional) 
        Span employmentQuestionSpan = new Span("Your employment status");
        ComboBox<String> employmentAnswerComboBox = new ComboBox<>("Answer");
        employmentAnswerComboBox.setItems(
            "Employed",
            "Unemployed",
            "Retired"
        );
        employmentAnswerComboBox.setWidthFull();
        employmentAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout employmentQuestion = new VerticalLayout();
        employmentQuestion.setSpacing(false);
        employmentQuestion.setPadding(false);
        employmentQuestion.setWidthFull();
        employmentQuestion.add(employmentQuestionSpan, employmentAnswerComboBox);

        hobbyOrInterestQuestions.add(question1, question2, question3, question4, question5, question6, question7, question8, question9, question10, question11, question12, question13, question14, question15, genderQuestion, question16, question17, question18, employmentQuestion);
        return hobbyOrInterestQuestions;
    }

    VerticalLayout clinicalDiagnosisQuestions(ComboBox<String> kindOfPreviouslySelected) {
        VerticalLayout clinicalDiagnosisQuestions = new VerticalLayout();
        clinicalDiagnosisQuestions.setSpacing(true);
        clinicalDiagnosisQuestions.setPadding(true);
        clinicalDiagnosisQuestions.setWidthFull();
        clinicalDiagnosisQuestions.getStyle().set("display", "flex")
            .set("gap", "20px")
            .set("flex-direction", "row")
            .set("align-items", "start")
            .set("justify-content", "start")
            .set("flex-wrap", "wrap");

        // Add a text for the first question
        String selectedKind = kindOfPreviouslySelected.getValue() != null ? kindOfPreviouslySelected.getValue().toLowerCase() : null;
        Span question1Span = new Span("How how long have you been " + selectedKind + "?");
        // Add a ComboBox for the answer
        ComboBox<String> question1ComboBox = new ComboBox<>("Answer");
        question1ComboBox.setItems(
            "Less than 6 months",
            "6 months – 1 year",
            "1–3 years",
            "3–5 years",
            "More than 5 years"
        );
        question1ComboBox.setWidthFull();
        question1ComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout question1 = new VerticalLayout();
        question1.setSpacing(false);
        question1.setPadding(false);
        question1.setWidthFull();
        question1.add(question1Span, question1ComboBox);

        Span question2Span = new Span("Who provided your diagnosis?");
        ComboBox<String> question2ComboBox = new ComboBox<>("Answer");
        question2ComboBox.setItems(
            "Medical doctor (e.g., psychiatrist, neurologist, general practitioner)",
            "Psychologist or therapist",
            "Self-diagnosed",
            "Other healthcare professional",
            "University",
            "Collage"
        );
        question2ComboBox.setWidthFull();
        question2ComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question2 = new VerticalLayout();    
        question2.setSpacing(false);
        question2.setPadding(false);
        question2.setWidthFull();
        question2.add(question2Span, question2ComboBox);

        Span question3Span = new Span("What symptoms or challenges do you experience most frequently?");
        ComboBox<String> question3ComboBox = new ComboBox<>("Answer");
        question3ComboBox.setItems(
            "Physical symptoms",
            "Emotional symptoms",
            "Cognitive symptoms",
            "Social/relationship challenges",
            "Occupational/academic challenges"
        );
        question3ComboBox.setWidthFull();
        question3ComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question3 = new VerticalLayout();    
        question3.setSpacing(false);
        question3.setPadding(false);
        question3.setWidthFull();
        question3.add(question3Span, question3ComboBox);
        
        Span question4Span = new Span("How would you rate the impact of your diagnosis on your daily life?");
        ComboBox<String> question4ComboBox = new ComboBox<>("Answer");
        question4ComboBox.setItems(
            "Severe impact",
            "Moderate impact",
            "Mild impact",
            "No significant impact"
        );
        question4ComboBox.setWidthFull();
        question4ComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question4 = new VerticalLayout();    
        question4.setSpacing(false);
        question4.setPadding(false);
        question4.setWidthFull();
        question4.add(question4Span, question4ComboBox);

        Span question5Span = new Span("What treatments or interventions are you currently using?");
        ComboBox<String> question5ComboBox = new ComboBox<>("Answer");
        question5ComboBox.setItems(
            "Prescription medication",
            "Therapy/counseling",
            "Motivational therapy",
            "Support groups",
            "Lifestyle changes (diet, exercise, sleep)",
            "Alternative/holistic therapies",
            "None"
        );
        question5ComboBox.setWidthFull();
        question5ComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question5 = new VerticalLayout();    
        question5.setSpacing(false);
        question5.setPadding(false);
        question5.setWidthFull();
        question5.add(question5Span, question5ComboBox);

        Span question6Span = new Span("How satisfied are you with your current treatment or support?");
        ComboBox<String> question6ComboBox = new ComboBox<>("Answer");
        question6ComboBox.setItems(
            "Very satisfied",
            "Somewhat satisfied",
            "Somewhat dissatisfied",
            "Very dissatisfied"
        );
        question6ComboBox.setWidthFull();
        question6ComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question6 = new VerticalLayout();    
        question6.setSpacing(false);
        question6.setPadding(false);
        question6.setWidthFull();
        question6.add(question6Span, question6ComboBox);

        Span question7Span = new Span("What are your biggest unmet needs related to this condition?");
        ComboBox<String> question7ComboBox = new ComboBox<>("Answer");
        question7ComboBox.setItems(
            "Medical treatment",
            "Therapy/counseling",
            "Lifestyle changes (diet, exercise, sleep)",
            "Support groups",
            "Alternative/holistic therapies",
            "None"
        );
        question7ComboBox.setWidthFull();
        question7ComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question7 = new VerticalLayout();    
        question7.setSpacing(false);
        question7.setPadding(false);
        question7.setWidthFull();
        question7.add(question7Span, question7ComboBox);

        Span question8Span = new Span("How often do you seek medical or professional support for your condition?");
        ComboBox<String> question8ComboBox = new ComboBox<>("Answer");
        question8ComboBox.setItems(
            "Regularly (monthly or more)",
            "Occasionally (a few times a year)",
            "Rarely",
            "Never"
        );
        question8ComboBox.setWidthFull();
        question8ComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question8 = new VerticalLayout();    
        question8.setSpacing(false);
        question8.setPadding(false);
        question8.setWidthFull();
        question8.add(question8Span, question8ComboBox);

        Span question9Span = new Span("Do you feel comfortable discussing your diagnosis with others?");
        ComboBox<String> question9ComboBox = new ComboBox<>("Answer");
        question9ComboBox.setItems(
            "Yes, very comfortable",
            "Only with close friends/family",
            "Only with healthcare professionals",
            "No, I prefer to keep it private"
        );
        question9ComboBox.setWidthFull();
        question9ComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");

        VerticalLayout question9 = new VerticalLayout();    
        question9.setSpacing(false);
        question9.setPadding(false);
        question9.setWidthFull();
        question9.add(question9Span, question9ComboBox);

        //add a combobox for the question "Your gender" with the options "Male", "Female", the question should be in a text separate from the combobox but the combobox is labelled "Answer"
        Span genderQuestionSpan = new Span("Your gender");
        ComboBox<String> genderAnswerComboBox = new ComboBox<>("Answer");
        genderAnswerComboBox.setItems(
            "Male",
            "Female"
        );
        genderAnswerComboBox.setWidthFull();
        genderAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout genderQuestion = new VerticalLayout();
        genderQuestion.setSpacing(false);
        genderQuestion.setPadding(false);
        genderQuestion.setWidthFull();
        genderQuestion.add(genderQuestionSpan, genderAnswerComboBox);

        //Age group
        Span ageQuestionSpan = new Span("Your age group");
        ComboBox<String> ageAnswerComboBox = new ComboBox<>("Answer");
        ageAnswerComboBox.setItems(
            "Under 18",
            "18–25",
            "26–40",
            "41–60",
            "Over 60"
        );
        ageAnswerComboBox.setWidthFull();
        ageAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout question10 = new VerticalLayout();
        question10.setSpacing(false);
        question10.setPadding(false);
        question10.setWidthFull();
        question10.add(ageQuestionSpan, ageAnswerComboBox);
        
        //Household size / Business size
        Span sizeQuestionSpan = new Span("Your household size");
        ComboBox<String> sizeAnswerComboBox = new ComboBox<>("Answer");
        sizeAnswerComboBox.setItems(
            "1–2 people",
            "3–5 people",
            "6+ people"
        );
        sizeAnswerComboBox.setWidthFull();
        sizeAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout question11 = new VerticalLayout();
        question11.setSpacing(false);
        question11.setPadding(false);
        question11.setWidthFull();
        question11.add(sizeQuestionSpan, sizeAnswerComboBox);

        //Income or budget range (optional) 
        Span incomeQuestionSpan = new Span("Your income or budget range");
        ComboBox<String> incomeAnswerComboBox = new ComboBox<>("Answer");
        incomeAnswerComboBox.setItems(
            "Low",
            "Middle",
            "High"
        );
        incomeAnswerComboBox.setWidthFull();
        incomeAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout question12 = new VerticalLayout();
        question12.setSpacing(false);
        question12.setPadding(false);
        question12.setWidthFull();
        question12.add(incomeQuestionSpan, incomeAnswerComboBox);

        //Income or budget range (optional) 
        Span employmentQuestionSpan = new Span("Your employment status");
        ComboBox<String> employmentAnswerComboBox = new ComboBox<>("Answer");
        employmentAnswerComboBox.setItems(
            "Employed",
            "Unemployed",
            "Retired"
        );
        employmentAnswerComboBox.setWidthFull();
        employmentAnswerComboBox.getStyle().set("min-width", "210px").set("flex", "1").set("padding", "0");
        VerticalLayout employmentQuestion = new VerticalLayout();
        employmentQuestion.setSpacing(false);
        employmentQuestion.setPadding(false);
        employmentQuestion.setWidthFull();
        employmentQuestion.add(employmentQuestionSpan, employmentAnswerComboBox);

        clinicalDiagnosisQuestions.add(question1, question2, question3, question4, question5, question6, question7, question8, question9, question10, question11, genderQuestion, question12, employmentQuestion);
        return clinicalDiagnosisQuestions;
    }
}   