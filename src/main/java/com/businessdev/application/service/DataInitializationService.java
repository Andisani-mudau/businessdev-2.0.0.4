package com.businessdev.application.service;

import com.businessdev.application.entity.Product;
import com.businessdev.application.entity.Service;
import com.businessdev.application.entity.SurveyQuestion;
import com.businessdev.application.entity.SurveyTemplate;
import com.businessdev.application.repository.ProductRepository;
import com.businessdev.application.repository.ServiceRepository;
import com.businessdev.application.repository.SurveyQuestionRepository;
import com.businessdev.application.repository.SurveyTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.Arrays;
import java.util.List;

@org.springframework.stereotype.Service
@org.springframework.boot.autoconfigure.condition.ConditionalOnProperty(
    name = "spring.data.mongodb.uri",
    matchIfMissing = false
)
public class DataInitializationService implements CommandLineRunner {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ServiceRepository serviceRepository;
    
    @Autowired
    private SurveyQuestionRepository surveyQuestionRepository;
    
    @Autowired
    private SurveyTemplateRepository surveyTemplateRepository;
    
    @Override
    public void run(String... args) throws Exception {
        try {
            initializeSampleData();
        } catch (Exception e) {
            // Log error but don't fail startup if data initialization fails
            // This allows the application to start even if MongoDB is temporarily unavailable
            System.err.println("Warning: Failed to initialize sample data: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void initializeSampleData() {
        try {
            // Only initialize if database is empty
            if (productRepository.count() == 0) {
                initializeProducts();
            }
            
            if (serviceRepository.count() == 0) {
                initializeServices();
            }
            
            if (surveyQuestionRepository.count() == 0) {
                initializeSurveyQuestions();
            }
        } catch (Exception e) {
            // Handle MongoDB connection errors gracefully
            System.err.println("Warning: Could not access MongoDB for data initialization: " + e.getMessage());
            throw e;
        }
        
        if (surveyTemplateRepository.count() == 0) {
            initializeSurveyTemplates();
        }
    }
    
    private void initializeProducts() {
        // Consumable Products
        List<Product> consumableProducts = Arrays.asList(
            // Fruits
            new Product("Banana", "consumable", "fruits", "bananas", "Yellow curved fruit rich in potassium"),
            new Product("Orange", "consumable", "fruits", "citrus", "Orange citrus fruit high in vitamin C"),
            new Product("Apple", "consumable", "fruits", "apples", "Red or green crisp fruit"),
            new Product("Grape", "consumable", "fruits", "grapes", "Small round fruits in clusters"),
            
            // Vegetables
            new Product("Potato", "consumable", "vegetables", "tubers", "Starchy tuber vegetable"),
            new Product("Carrot", "consumable", "vegetables", "root", "Orange root vegetable"),
            new Product("Tomato", "consumable", "vegetables", "nightshade", "Red fruit vegetable"),
            new Product("Lettuce", "consumable", "vegetables", "leafy", "Green leafy vegetable"),
            
            // Grains & Staples
            new Product("Rice", "consumable", "grains", "cereals", "White or brown grain staple"),
            new Product("Wheat", "consumable", "grains", "cereals", "Cereal grain for bread making"),
            new Product("Oats", "consumable", "grains", "cereals", "Whole grain for breakfast"),
            new Product("Quinoa", "consumable", "grains", "pseudocereals", "Protein-rich grain alternative")
        );
        
        // Non-Consumable Products
        List<Product> nonConsumableProducts = Arrays.asList(
            // Electronics
            new Product("Smartphone", "non-consumable", "electronics", "mobile", "Portable communication device"),
            new Product("Laptop", "non-consumable", "electronics", "computers", "Portable personal computer"),
            new Product("Headphones", "non-consumable", "electronics", "audio", "Audio listening device"),
            
            // Clothing
            new Product("T-Shirt", "non-consumable", "clothing", "tops", "Short-sleeved casual shirt"),
            new Product("Jeans", "non-consumable", "clothing", "bottoms", "Denim trousers"),
            new Product("Sneakers", "non-consumable", "clothing", "footwear", "Casual athletic shoes"),
            
            // Home & Garden
            new Product("Chair", "non-consumable", "furniture", "seating", "Furniture for sitting"),
            new Product("Table", "non-consumable", "furniture", "surfaces", "Flat surface furniture"),
            new Product("Lamp", "non-consumable", "furniture", "lighting", "Electric light fixture")
        );
        
        productRepository.saveAll(consumableProducts);
        productRepository.saveAll(nonConsumableProducts);
        
        System.out.println("Initialized " + (consumableProducts.size() + nonConsumableProducts.size()) + " products");
    }
    
    private void initializeServices() {
        List<Service> services = Arrays.asList(
            // Consulting Services
            new Service("Business Consulting", "consulting", "business", "Strategic business advice and planning"),
            new Service("IT Consulting", "consulting", "technology", "Information technology consulting"),
            new Service("Financial Consulting", "consulting", "finance", "Financial planning and advice"),
            
            // Maintenance Services
            new Service("Home Maintenance", "maintenance", "residential", "General home repair and maintenance"),
            new Service("Car Maintenance", "maintenance", "automotive", "Vehicle service and repair"),
            new Service("IT Support", "maintenance", "technology", "Computer and network support"),
            
            // Delivery Services
            new Service("Food Delivery", "delivery", "food", "Restaurant meal delivery service"),
            new Service("Package Delivery", "delivery", "logistics", "Package and parcel delivery"),
            new Service("Grocery Delivery", "delivery", "food", "Grocery shopping and delivery"),
            
            // Professional Services
            new Service("Legal Services", "professional", "legal", "Legal advice and representation"),
            new Service("Accounting Services", "professional", "finance", "Bookkeeping and tax services"),
            new Service("Marketing Services", "professional", "marketing", "Digital and traditional marketing")
        );
        
        serviceRepository.saveAll(services);
        System.out.println("Initialized " + services.size() + " services");
    }
    
    private void initializeSurveyQuestions() {
        // Initialize questions for all products
        initializeProductQuestions();
        
        // Initialize questions for all services
        initializeServiceQuestions();
    }
    
    private void initializeProductQuestions() {
        List<Product> allProducts = productRepository.findAll();
        List<SurveyQuestion> allProductQuestions = new java.util.ArrayList<>();
        
        for (Product product : allProducts) {
            List<SurveyQuestion> productQuestions = createProductQuestions(product);
            allProductQuestions.addAll(productQuestions);
        }
        
        surveyQuestionRepository.saveAll(allProductQuestions);
        System.out.println("Initialized " + allProductQuestions.size() + " survey questions for " + allProducts.size() + " products");
    }
    
    private void initializeServiceQuestions() {
        List<Service> allServices = serviceRepository.findAll();
        List<SurveyQuestion> allServiceQuestions = new java.util.ArrayList<>();
        
        for (Service service : allServices) {
            List<SurveyQuestion> serviceQuestions = createServiceQuestions(service);
            allServiceQuestions.addAll(serviceQuestions);
        }
        
        surveyQuestionRepository.saveAll(allServiceQuestions);
        System.out.println("Initialized " + allServiceQuestions.size() + " survey questions for " + allServices.size() + " services");
    }
    
    private List<SurveyQuestion> createProductQuestions(Product product) {
        List<SurveyQuestion> questions = new java.util.ArrayList<>();
        
        // Question 1: Purchase frequency
        SurveyQuestion freqQuestion = new SurveyQuestion(
            "How often do you purchase " + product.getName().toLowerCase() + "?",
            "multiple_choice",
            "product",
            product.getId(),
            product.getName()
        );
        freqQuestion.setOptions(Arrays.asList("Daily", "Weekly", "Monthly", "Rarely", "Never"));
        freqQuestion.setOrderIndex(1);
        questions.add(freqQuestion);
        
        // Question 2: Quality rating
        SurveyQuestion qualityQuestion = new SurveyQuestion(
            "How would you rate the quality of " + product.getName().toLowerCase() + " you typically buy?",
            "rating",
            "product",
            product.getId(),
            product.getName()
        );
        qualityQuestion.setOrderIndex(2);
        questions.add(qualityQuestion);
        
        // Question 3: Price satisfaction
        SurveyQuestion priceQuestion = new SurveyQuestion(
            "How satisfied are you with the price of " + product.getName().toLowerCase() + "?",
            "rating",
            "product",
            product.getId(),
            product.getName()
        );
        priceQuestion.setOrderIndex(3);
        questions.add(priceQuestion);
        
        // Question 4: Specific to product type
        SurveyQuestion specificQuestion = createSpecificProductQuestion(product);
        if (specificQuestion != null) {
            questions.add(specificQuestion);
        }
        
        return questions;
    }
    
    private List<SurveyQuestion> createServiceQuestions(Service service) {
        List<SurveyQuestion> questions = new java.util.ArrayList<>();
        
        // Question 1: Usage frequency
        SurveyQuestion usageQuestion = new SurveyQuestion(
            "How often do you use " + service.getName().toLowerCase() + "?",
            "multiple_choice",
            "service",
            service.getId(),
            service.getName()
        );
        usageQuestion.setOptions(Arrays.asList("Daily", "Weekly", "Monthly", "Rarely", "Never"));
        usageQuestion.setOrderIndex(1);
        questions.add(usageQuestion);
        
        // Question 2: Satisfaction rating
        SurveyQuestion satisfactionQuestion = new SurveyQuestion(
            "How satisfied are you with " + service.getName().toLowerCase() + "?",
            "rating",
            "service",
            service.getId(),
            service.getName()
        );
        satisfactionQuestion.setOrderIndex(2);
        questions.add(satisfactionQuestion);
        
        // Question 3: Value for money
        SurveyQuestion valueQuestion = new SurveyQuestion(
            "How would you rate the value for money of " + service.getName().toLowerCase() + "?",
            "rating",
            "service",
            service.getId(),
            service.getName()
        );
        valueQuestion.setOrderIndex(3);
        questions.add(valueQuestion);
        
        // Question 4: Recommendation likelihood
        SurveyQuestion recommendQuestion = new SurveyQuestion(
            "How likely are you to recommend " + service.getName().toLowerCase() + " to others?",
            "rating",
            "service",
            service.getId(),
            service.getName()
        );
        recommendQuestion.setOrderIndex(4);
        questions.add(recommendQuestion);
        
        return questions;
    }
    
    private SurveyQuestion createSpecificProductQuestion(Product product) {
        String productName = product.getName().toLowerCase();
        String category = product.getCategory().toLowerCase();
        
        // Specific questions based on product type
        if (productName.contains("banana")) {
            SurveyQuestion question = new SurveyQuestion(
                "What is your preferred ripeness level for bananas?",
                "multiple_choice",
                "product",
                product.getId(),
                product.getName()
            );
            question.setOptions(Arrays.asList("Green (unripe)", "Yellow with green tips", "Fully yellow", "Yellow with brown spots", "Very ripe (brown)"));
            question.setOrderIndex(4);
            return question;
        } else if (category.equals("fruits")) {
            SurveyQuestion question = new SurveyQuestion(
                "Do you prefer " + productName + " to be organic or conventional?",
                "multiple_choice",
                "product",
                product.getId(),
                product.getName()
            );
            question.setOptions(Arrays.asList("Organic", "Conventional", "No preference"));
            question.setOrderIndex(4);
            return question;
        } else if (category.equals("electronics")) {
            SurveyQuestion question = new SurveyQuestion(
                "What is the most important factor when buying " + productName + "?",
                "multiple_choice",
                "product",
                product.getId(),
                product.getName()
            );
            question.setOptions(Arrays.asList("Price", "Quality", "Brand", "Features", "Warranty"));
            question.setOrderIndex(4);
            return question;
        } else if (category.equals("clothing")) {
            SurveyQuestion question = new SurveyQuestion(
                "What size do you typically buy for " + productName + "?",
                "multiple_choice",
                "product",
                product.getId(),
                product.getName()
            );
            question.setOptions(Arrays.asList("XS", "S", "M", "L", "XL", "XXL"));
            question.setOrderIndex(4);
            return question;
        }
        
        return null;
    }
    
    private void initializeSurveyTemplates() {
        // Initialize consumable products survey template
        initializeConsumableSurveyTemplate();
        
        // Initialize non-consumable products survey template
        initializeNonConsumableSurveyTemplate();
        
        // Initialize services survey template
        initializeServicesSurveyTemplate();
    }
    
    private void initializeConsumableSurveyTemplate() {
        SurveyTemplate template = new SurveyTemplate(
            "consumable",
            "🌍 Global Food Demand & Consumption Survey",
            "To understand consumer preferences, purchasing behavior, and influencing factors for key food categories",
            "To understand consumer preferences, purchasing behavior, and influencing factors for key food categories — including staple crops, fruits, vegetables, dairy, and meat — and to compare this demand-side data with supply-side (production) data for market gap analysis and forecasting."
        );
        
        List<SurveyTemplate.SurveySection> sections = Arrays.asList(
            createConsumableSection1(),
            createConsumableSection2(),
            createConsumableSection3(),
            createConsumableSection4(),
            createConsumableSection5()
        );
        
        template.setSections(sections);
        surveyTemplateRepository.save(template);
        System.out.println("Initialized consumable products survey template");
    }
    
    private void initializeNonConsumableSurveyTemplate() {
        SurveyTemplate template = new SurveyTemplate(
            "non-consumable",
            "🧰 Non-Consumable Product Preference & Purchase Survey",
            "To understand consumer preferences, buying behavior, replacement cycles, influencing factors, and future demand trends for durable and semi-durable products",
            "To understand consumer preferences, buying behavior, replacement cycles, influencing factors, and future demand trends for durable and semi-durable products such as electronics, furniture, clothing, tools, vehicles, and home appliances. Data collected can be used for demand forecasting, supply gap analysis, product development, and market strategy planning."
        );
        
        List<SurveyTemplate.SurveySection> sections = Arrays.asList(
            createNonConsumableSection1(),
            createNonConsumableSection2(),
            createNonConsumableSection3(),
            createNonConsumableSection4(),
            createNonConsumableSection5()
        );
        
        template.setSections(sections);
        surveyTemplateRepository.save(template);
        System.out.println("Initialized non-consumable products survey template");
    }
    
    private void initializeServicesSurveyTemplate() {
        SurveyTemplate template = new SurveyTemplate(
            "service",
            "📡 Service Usage & Satisfaction Survey",
            "To collect detailed information on consumer and business service usage, satisfaction levels, decision drivers, barriers, and future intentions",
            "To collect detailed information on consumer and business service usage, satisfaction levels, decision drivers, barriers, and future intentions. This data helps identify demand trends, quality gaps, pricing opportunities, and market expansion potential in both B2C and B2B service sectors."
        );
        
        List<SurveyTemplate.SurveySection> sections = Arrays.asList(
            createServiceSection1(),
            createServiceSection2(),
            createServiceSection3(),
            createServiceSection4(),
            createServiceSection5(),
            createServiceSection6()
        );
        
        template.setSections(sections);
        surveyTemplateRepository.save(template);
        System.out.println("Initialized services survey template");
    }
    
    // Consumable Survey Sections
    private SurveyTemplate.SurveySection createConsumableSection1() {
        SurveyTemplate.SurveySection section = new SurveyTemplate.SurveySection(
            "consumable_section_1", "🥗 General Consumption Preferences", "", 1
        );
        
        List<SurveyQuestion> questions = Arrays.asList(
            new SurveyQuestion("Which of the following food types do you or your household regularly consume?", "multiple_choice", "consumable", null, null),
            new SurveyQuestion("For each food type you selected, how much do you like or value it in your diet?", "rating", "consumable", null, null),
            new SurveyQuestion("In what form do you usually prefer these products?", "multiple_choice", "consumable", null, null)
        );
        
        questions.get(0).setOptions(Arrays.asList(
            "Grains & staples (e.g., maize, rice, wheat, potatoes)",
            "Vegetables", "Fruits", "Dairy products (milk, cheese, yogurt)",
            "Meat & poultry", "Fish & seafood", "Plant-based alternatives (tofu, plant milks, etc.)",
            "Other (please specify)"
        ));
        questions.get(0).setOrderIndex(1);
        
        questions.get(1).setOptions(Arrays.asList("Love it", "Like it", "Neutral", "Rarely consume", "Never consume"));
        questions.get(1).setOrderIndex(2);
        
        questions.get(2).setOptions(Arrays.asList(
            "Fresh/raw (to prepare at home)", "Frozen", "Processed/packaged (ready-to-eat or cook)",
            "Restaurant/prepared meals", "I don't consume this type"
        ));
        questions.get(2).setOrderIndex(3);
        
        section.setQuestions(questions);
        return section;
    }
    
    private SurveyTemplate.SurveySection createConsumableSection2() {
        SurveyTemplate.SurveySection section = new SurveyTemplate.SurveySection(
            "consumable_section_2", "🛒 Buying & Consumption Behavior", "", 2
        );
        
        List<SurveyQuestion> questions = Arrays.asList(
            new SurveyQuestion("How often do you purchase each of these food types?", "multiple_choice", "consumable", null, null),
            new SurveyQuestion("On average, how much do you purchase per month (household or business total)?", "multiple_choice", "consumable", null, null),
            new SurveyQuestion("Where do you usually buy your food?", "multiple_choice", "consumable", null, null),
            new SurveyQuestion("Do you grow or produce any of these foods yourself?", "multiple_choice", "consumable", null, null)
        );
        
        questions.get(0).setOptions(Arrays.asList("Daily", "Weekly", "Monthly", "Rarely/Never"));
        questions.get(0).setOrderIndex(4);
        
        questions.get(1).setOptions(Arrays.asList("Less than 5 kg", "5–20 kg", "20–50 kg", "50–100 kg", "More than 100 kg"));
        questions.get(1).setOrderIndex(5);
        
        questions.get(2).setOptions(Arrays.asList(
            "Local markets", "Supermarkets", "Wholesalers", "Direct from farmers",
            "Online delivery platforms", "Restaurants/catering suppliers (for businesses)"
        ));
        questions.get(2).setOrderIndex(6);
        
        questions.get(3).setOptions(Arrays.asList("Yes (for home use)", "Yes (for commercial sale)", "No"));
        questions.get(3).setOrderIndex(7);
        
        section.setQuestions(questions);
        return section;
    }
    
    private SurveyTemplate.SurveySection createConsumableSection3() {
        SurveyTemplate.SurveySection section = new SurveyTemplate.SurveySection(
            "consumable_section_3", "💡 Purchase Influences & Market Drivers", "", 3
        );
        
        List<SurveyQuestion> questions = Arrays.asList(
            new SurveyQuestion("What most influences your decision to buy each food type?", "multiple_choice", "consumable", null, null),
            new SurveyQuestion("What would make you buy more often?", "multiple_choice", "consumable", null, null),
            new SurveyQuestion("If prices rise significantly, how would your consumption change?", "multiple_choice", "consumable", null, null)
        );
        
        questions.get(0).setOptions(Arrays.asList(
            "Price", "Availability", "Quality/freshness", "Brand or source", "Convenience (ready-made)",
            "Nutritional value/health", "Family or cultural preference", "Environmental or ethical reasons"
        ));
        questions.get(0).setOrderIndex(8);
        
        questions.get(1).setOptions(Arrays.asList(
            "Lower prices", "Better quality", "Greater availability", "More variety",
            "More local/organic options", "More processed/ready-to-cook options", "Nothing — I already buy enough"
        ));
        questions.get(1).setOrderIndex(9);
        
        questions.get(2).setOptions(Arrays.asList(
            "I would reduce consumption", "I would switch to cheaper alternatives",
            "I would continue buying the same amount", "I would grow/produce more myself"
        ));
        questions.get(2).setOrderIndex(10);
        
        section.setQuestions(questions);
        return section;
    }
    
    private SurveyTemplate.SurveySection createConsumableSection4() {
        SurveyTemplate.SurveySection section = new SurveyTemplate.SurveySection(
            "consumable_section_4", "📈 Future Demand & Trends", "", 4
        );
        
        List<SurveyQuestion> questions = Arrays.asList(
            new SurveyQuestion("Are you interested in alternative options (organic, plant-based, locally grown, etc.)?", "multiple_choice", "consumable", null, null),
            new SurveyQuestion("Would you pay more for higher-quality or ethically sourced food?", "multiple_choice", "consumable", null, null),
            new SurveyQuestion("Do you expect your household/business food consumption to increase, decrease, or stay the same in the next 5 years?", "multiple_choice", "consumable", null, null)
        );
        
        questions.get(0).setOptions(Arrays.asList("Yes, very interested", "Somewhat interested", "Not interested"));
        questions.get(0).setOrderIndex(11);
        
        questions.get(1).setOptions(Arrays.asList("Yes, significantly more", "Yes, slightly more", "No"));
        questions.get(1).setOrderIndex(12);
        
        questions.get(2).setOptions(Arrays.asList("Increase", "Decrease", "Stay the same", "Unsure"));
        questions.get(2).setOrderIndex(13);
        
        section.setQuestions(questions);
        return section;
    }
    
    private SurveyTemplate.SurveySection createConsumableSection5() {
        SurveyTemplate.SurveySection section = new SurveyTemplate.SurveySection(
            "consumable_section_5", "🧑‍🤝‍🧑 Demographics & Segmentation", "", 5
        );
        
        List<SurveyQuestion> questions = Arrays.asList(
            new SurveyQuestion("Age group:", "multiple_choice", "consumable", null, null),
            new SurveyQuestion("Household size / Business size:", "multiple_choice", "consumable", null, null),
            new SurveyQuestion("Income or budget range (optional):", "multiple_choice", "consumable", null, null),
            new SurveyQuestion("Location:", "multiple_choice", "consumable", null, null)
        );
        
        questions.get(0).setOptions(Arrays.asList("Under 18", "18–25", "26–40", "41–60", "Over 60"));
        questions.get(0).setOrderIndex(14);
        
        questions.get(1).setOptions(Arrays.asList(
            "1–2 people", "3–5 people", "6+ people",
            "Small business (1–10 employees)", "Medium business (11–100)", "Large business (100+)"
        ));
        questions.get(1).setOrderIndex(15);
        
        questions.get(2).setOptions(Arrays.asList("Low", "Middle", "High"));
        questions.get(2).setOrderIndex(16);
        
        questions.get(3).setOptions(Arrays.asList(
            "Continent", "Country", "Province / State", "City / Town", "Rural / Urban"
        ));
        questions.get(3).setOrderIndex(17);
        
        section.setQuestions(questions);
        return section;
    }
    
    // Non-Consumable Survey Sections (simplified for brevity)
    private SurveyTemplate.SurveySection createNonConsumableSection1() {
        SurveyTemplate.SurveySection section = new SurveyTemplate.SurveySection(
            "non_consumable_section_1", "🧡 General Product Preferences", "", 1
        );
        
        List<SurveyQuestion> questions = Arrays.asList(
            new SurveyQuestion("Which of the following product categories do you regularly purchase or use?", "multiple_choice", "non-consumable", null, null),
            new SurveyQuestion("How important are these product types in your life or business?", "rating", "non-consumable", null, null),
            new SurveyQuestion("Which type of products do you prefer to invest more money in?", "multiple_choice", "non-consumable", null, null)
        );
        
        questions.get(0).setOptions(Arrays.asList(
            "Clothing & footwear", "Mobile phones & gadgets", "Home appliances (e.g., fridge, washing machine)",
            "Furniture (e.g., sofa, table, bed)", "Vehicles (e.g., car, motorcycle, bicycle)",
            "Tools & equipment (e.g., power tools, lawn mower)", "Kitchenware (e.g., pots, plates)",
            "Toys & games", "Books & stationery", "Personal care devices (e.g., hair dryer, shaver)"
        ));
        questions.get(0).setOrderIndex(1);
        
        questions.get(1).setOptions(Arrays.asList("Very important", "Somewhat important", "Neutral", "Not very important", "Not important at all"));
        questions.get(1).setOrderIndex(2);
        
        questions.get(2).setOptions(Arrays.asList(
            "Quality and durability", "Brand and reputation", "Latest technology or design",
            "Budget-friendly options", "Sustainable or eco-friendly products"
        ));
        questions.get(2).setOrderIndex(3);
        
        section.setQuestions(questions);
        return section;
    }
    
    private SurveyTemplate.SurveySection createNonConsumableSection2() {
        SurveyTemplate.SurveySection section = new SurveyTemplate.SurveySection(
            "non_consumable_section_2", "🛍️ Purchase Behavior & Replacement Cycle", "", 2
        );
        
        List<SurveyQuestion> questions = Arrays.asList(
            new SurveyQuestion("How often do you buy new products in each category?", "multiple_choice", "non-consumable", null, null),
            new SurveyQuestion("What is your typical spending range for each purchase?", "multiple_choice", "non-consumable", null, null),
            new SurveyQuestion("What do you usually do with old or replaced products?", "multiple_choice", "non-consumable", null, null),
            new SurveyQuestion("Where do you usually buy these products?", "multiple_choice", "non-consumable", null, null)
        );
        
        questions.get(0).setOptions(Arrays.asList("Every 6 months", "Every 1–2 years", "Every 3–5 years", "Rarely (only when old one breaks)", "Never bought"));
        questions.get(0).setOrderIndex(4);
        
        questions.get(1).setOptions(Arrays.asList("Less than R1,000", "R1,000 – R5,000", "R5,000 – R20,000", "R20,000 – R50,000", "More than R50,000"));
        questions.get(1).setOrderIndex(5);
        
        questions.get(2).setOptions(Arrays.asList("Keep as backup", "Sell or trade-in", "Recycle or donate", "Throw away", "Other (please specify)"));
        questions.get(2).setOrderIndex(6);
        
        questions.get(3).setOptions(Arrays.asList(
            "Physical retail stores", "Online stores / e-commerce platforms", "Direct from manufacturer or brand store",
            "Second-hand or refurbished markets", "Local small businesses or markets"
        ));
        questions.get(3).setOrderIndex(7);
        
        section.setQuestions(questions);
        return section;
    }
    
    private SurveyTemplate.SurveySection createNonConsumableSection3() {
        SurveyTemplate.SurveySection section = new SurveyTemplate.SurveySection(
            "non_consumable_section_3", "💡 Purchase Influences & Decision Drivers", "", 3
        );
        
        List<SurveyQuestion> questions = Arrays.asList(
            new SurveyQuestion("What most influences your decision to buy a new product?", "multiple_choice", "non-consumable", null, null),
            new SurveyQuestion("What would make you buy more frequently or upgrade sooner?", "multiple_choice", "non-consumable", null, null),
            new SurveyQuestion("If a product becomes significantly more expensive, what would you do?", "multiple_choice", "non-consumable", null, null)
        );
        
        questions.get(0).setOptions(Arrays.asList(
            "Price", "Brand reputation", "Quality/durability", "Features or design", "Availability and convenience",
            "Warranty or after-sales service", "Environmental impact", "Peer or family recommendations"
        ));
        questions.get(0).setOrderIndex(8);
        
        questions.get(1).setOptions(Arrays.asList(
            "Lower prices or discounts", "New and innovative features", "Trade-in or upgrade programs",
            "Better after-sales support", "Sustainable or recyclable options", "Nothing — I'm satisfied with my current purchase rate"
        ));
        questions.get(1).setOrderIndex(9);
        
        questions.get(2).setOptions(Arrays.asList(
            "Delay the purchase", "Choose a cheaper alternative", "Switch to second-hand",
            "Still buy the same product", "Stop buying that category"
        ));
        questions.get(2).setOrderIndex(10);
        
        section.setQuestions(questions);
        return section;
    }
    
    private SurveyTemplate.SurveySection createNonConsumableSection4() {
        SurveyTemplate.SurveySection section = new SurveyTemplate.SurveySection(
            "non_consumable_section_4", "🔮 Future Purchase Intentions & Technology Trends", "", 4
        );
        
        List<SurveyQuestion> questions = Arrays.asList(
            new SurveyQuestion("Are you planning to purchase or upgrade any major products in the next 12 months?", "multiple_choice", "non-consumable", null, null),
            new SurveyQuestion("Would you pay more for products that are:", "multiple_choice", "non-consumable", null, null),
            new SurveyQuestion("Are you interested in renting or subscribing to products instead of owning them?", "multiple_choice", "non-consumable", null, null)
        );
        
        questions.get(0).setOptions(Arrays.asList("Yes, definitely", "Maybe", "Not sure", "No"));
        questions.get(0).setOrderIndex(11);
        
        questions.get(1).setOptions(Arrays.asList(
            "Higher quality and longer lasting", "Eco-friendly or sustainably made", "Locally produced",
            "Smart/AI-enabled", "Backed by excellent service and warranty", "I prefer the cheapest option"
        ));
        questions.get(1).setOrderIndex(12);
        
        questions.get(2).setOptions(Arrays.asList("Yes, very interested", "Somewhat interested", "No"));
        questions.get(2).setOrderIndex(13);
        
        section.setQuestions(questions);
        return section;
    }
    
    private SurveyTemplate.SurveySection createNonConsumableSection5() {
        SurveyTemplate.SurveySection section = new SurveyTemplate.SurveySection(
            "non_consumable_section_5", "🧑‍🤝‍🧑 Demographics & Segmentation", "", 5
        );
        
        List<SurveyQuestion> questions = Arrays.asList(
            new SurveyQuestion("Age group:", "multiple_choice", "non-consumable", null, null),
            new SurveyQuestion("Household or business size:", "multiple_choice", "non-consumable", null, null),
            new SurveyQuestion("Income or budget range (optional):", "multiple_choice", "non-consumable", null, null),
            new SurveyQuestion("Location:", "multiple_choice", "non-consumable", null, null)
        );
        
        questions.get(0).setOptions(Arrays.asList("Under 18", "18–25", "26–40", "41–60", "Over 60"));
        questions.get(0).setOrderIndex(14);
        
        questions.get(1).setOptions(Arrays.asList(
            "1–2 people", "3–5 people", "6+ people",
            "Small business (1–10 employees)", "Medium business (11–100)", "Large business (100+)"
        ));
        questions.get(1).setOrderIndex(15);
        
        questions.get(2).setOptions(Arrays.asList("Low", "Middle", "High"));
        questions.get(2).setOrderIndex(16);
        
        questions.get(3).setOptions(Arrays.asList(
            "Continent", "Country", "Province / State", "City / Town", "Rural / Urban"
        ));
        questions.get(3).setOrderIndex(17);
        
        section.setQuestions(questions);
        return section;
    }
    
    // Service Survey Sections (simplified for brevity)
    private SurveyTemplate.SurveySection createServiceSection1() {
        SurveyTemplate.SurveySection section = new SurveyTemplate.SurveySection(
            "service_section_1", "📍 General Service Usage", "", 1
        );
        
        List<SurveyQuestion> questions = Arrays.asList(
            new SurveyQuestion("Which of the following services do you or your household/business currently use?", "multiple_choice", "service", null, null),
            new SurveyQuestion("How important are these services to your daily life or operations?", "rating", "service", null, null),
            new SurveyQuestion("How often do you use these services?", "multiple_choice", "service", null, null)
        );
        
        questions.get(0).setOptions(Arrays.asList(
            "Internet / mobile network", "Banking & financial services", "Healthcare & medical services",
            "Education / tutoring", "Transportation & logistics", "Insurance (health, vehicle, property)",
            "Home maintenance (cleaning, plumbing, etc.)", "IT / cloud computing services",
            "Tourism & hospitality", "Legal / consulting", "Government or municipal services"
        ));
        questions.get(0).setOrderIndex(1);
        
        questions.get(1).setOptions(Arrays.asList("Extremely important", "Very important", "Somewhat important", "Not very important", "Not important at all"));
        questions.get(1).setOrderIndex(2);
        
        questions.get(2).setOptions(Arrays.asList("Daily", "Weekly", "Monthly", "Occasionally", "Rarely"));
        questions.get(2).setOrderIndex(3);
        
        section.setQuestions(questions);
        return section;
    }
    
    private SurveyTemplate.SurveySection createServiceSection2() {
        SurveyTemplate.SurveySection section = new SurveyTemplate.SurveySection(
            "service_section_2", "💡 Service Acquisition & Decision Factors", "", 2
        );
        
        List<SurveyQuestion> questions = Arrays.asList(
            new SurveyQuestion("How do you usually find or choose a new service provider?", "multiple_choice", "service", null, null),
            new SurveyQuestion("What are the top 3 factors you consider before using a service?", "multiple_choice", "service", null, null),
            new SurveyQuestion("How long have you been using your current service providers?", "multiple_choice", "service", null, null)
        );
        
        questions.get(0).setOptions(Arrays.asList(
            "Personal recommendation", "Online search or ads", "Social media", "Walk-in or physical branch",
            "Company sales team / representative", "Government or institutional assignment"
        ));
        questions.get(0).setOrderIndex(4);
        
        questions.get(1).setOptions(Arrays.asList(
            "Price or fees", "Quality and reliability", "Customer support and responsiveness",
            "Brand reputation", "Ease of access or convenience", "Extra features / added value",
            "Trust / security / privacy", "Flexibility or customization"
        ));
        questions.get(1).setOrderIndex(5);
        
        questions.get(2).setOptions(Arrays.asList("Less than 6 months", "6 months – 1 year", "1–3 years", "3–5 years", "More than 5 years"));
        questions.get(2).setOrderIndex(6);
        
        section.setQuestions(questions);
        return section;
    }
    
    private SurveyTemplate.SurveySection createServiceSection3() {
        SurveyTemplate.SurveySection section = new SurveyTemplate.SurveySection(
            "service_section_3", "🧰 Satisfaction & Experience", "", 3
        );
        
        List<SurveyQuestion> questions = Arrays.asList(
            new SurveyQuestion("How satisfied are you with the following aspects of your current service providers?", "rating", "service", null, null),
            new SurveyQuestion("Have you ever switched service providers?", "multiple_choice", "service", null, null),
            new SurveyQuestion("How likely are you to recommend your current service provider to others?", "rating", "service", null, null)
        );
        
        questions.get(0).setOptions(Arrays.asList("Quality and reliability", "Customer service", "Pricing/value for money", "Transparency and trust", "Flexibility/customization", "Overall experience"));
        questions.get(0).setOrderIndex(7);
        
        questions.get(1).setOptions(Arrays.asList("Yes", "No"));
        questions.get(1).setOrderIndex(8);
        
        questions.get(2).setOptions(Arrays.asList("Very likely", "Likely", "Neutral", "Unlikely", "Very unlikely"));
        questions.get(2).setOrderIndex(9);
        
        section.setQuestions(questions);
        return section;
    }
    
    private SurveyTemplate.SurveySection createServiceSection4() {
        SurveyTemplate.SurveySection section = new SurveyTemplate.SurveySection(
            "service_section_4", "🔄 Renewal, Loyalty & Future Intentions", "", 4
        );
        
        List<SurveyQuestion> questions = Arrays.asList(
            new SurveyQuestion("When your current service period ends, what do you plan to do?", "multiple_choice", "service", null, null),
            new SurveyQuestion("What would make you switch to a different provider?", "multiple_choice", "service", null, null),
            new SurveyQuestion("Would you pay more for services that are:", "multiple_choice", "service", null, null)
        );
        
        questions.get(0).setOptions(Arrays.asList("Renew with the same provider", "Look for alternative providers", "Cancel the service entirely", "Undecided"));
        questions.get(0).setOrderIndex(10);
        
        questions.get(1).setOptions(Arrays.asList(
            "Lower prices", "Better quality or reliability", "Faster service", "More features / benefits",
            "Better customer support", "Stronger privacy / security policies", "I am unlikely to switch"
        ));
        questions.get(1).setOrderIndex(11);
        
        questions.get(2).setOptions(Arrays.asList(
            "Significantly higher quality", "Eco-friendly or sustainable", "Local or community-owned",
            "Highly customizable or personalized", "I prefer the cheapest available option"
        ));
        questions.get(2).setOrderIndex(12);
        
        section.setQuestions(questions);
        return section;
    }
    
    private SurveyTemplate.SurveySection createServiceSection5() {
        SurveyTemplate.SurveySection section = new SurveyTemplate.SurveySection(
            "service_section_5", "🌱 Future Service Trends & Innovations", "", 5
        );
        
        List<SurveyQuestion> questions = Arrays.asList(
            new SurveyQuestion("Are you interested in new service models such as:", "multiple_choice", "service", null, null),
            new SurveyQuestion("Do you expect your service usage to increase, decrease, or stay the same in the next 5 years?", "multiple_choice", "service", null, null)
        );
        
        questions.get(0).setOptions(Arrays.asList(
            "Subscription-based services", "AI-driven or automated services", "On-demand (pay-as-you-go) services",
            "Decentralized / peer-to-peer services", "Government-subsidized or community models"
        ));
        questions.get(0).setOrderIndex(13);
        
        questions.get(1).setOptions(Arrays.asList("Increase", "Decrease", "Stay the same", "Not sure"));
        questions.get(1).setOrderIndex(14);
        
        section.setQuestions(questions);
        return section;
    }
    
    private SurveyTemplate.SurveySection createServiceSection6() {
        SurveyTemplate.SurveySection section = new SurveyTemplate.SurveySection(
            "service_section_6", "🧑‍🤝‍🧑 Demographics & Segmentation", "", 6
        );
        
        List<SurveyQuestion> questions = Arrays.asList(
            new SurveyQuestion("Age group:", "multiple_choice", "service", null, null),
            new SurveyQuestion("Household or business size:", "multiple_choice", "service", null, null),
            new SurveyQuestion("Industry (if business):", "multiple_choice", "service", null, null),
            new SurveyQuestion("Location:", "multiple_choice", "service", null, null)
        );
        
        questions.get(0).setOptions(Arrays.asList("Under 18", "18–25", "26–40", "41–60", "Over 60"));
        questions.get(0).setOrderIndex(15);
        
        questions.get(1).setOptions(Arrays.asList(
            "1–2 people", "3–5 people", "6+ people",
            "Small business (1–10 employees)", "Medium business (11–100)", "Large business (100+)"
        ));
        questions.get(1).setOrderIndex(16);
        
        questions.get(2).setOptions(Arrays.asList(
            "Finance", "Healthcare", "Education", "Retail", "Manufacturing",
            "Technology", "Agriculture", "Other"
        ));
        questions.get(2).setOrderIndex(17);
        
        questions.get(3).setOptions(Arrays.asList(
            "Continent", "Country", "Province / State", "City / Town", "Rural / Urban"
        ));
        questions.get(3).setOrderIndex(18);
        
        section.setQuestions(questions);
        return section;
    }
}
