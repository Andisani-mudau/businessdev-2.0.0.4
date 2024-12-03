package com.businessdev.application.views.welcome;

import com.businessdev.application.views.welcome.MainLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.UnorderedList;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Text;

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
        Paragraph aboutText1 = new Paragraph("We specialise in providing comprehensive business and IT solutions designed to meet the unique needs of our clients. Established in 2024, we use latest technology and industry best practices to drive efficiency and innovation across various sectors. We collaborate with professionals with expertise in IT system development, designs, and business process optimisation. We are aware that the landscape of technology is constantly evolving, and we are committed to staying ahead of the curve.");
        Anchor basicCta = new Anchor("#basic", "Get Started");
        basicCta.addClassNames("card__cta", "cta");
        basicCard.add(basicHeading, aboutText1);

        // Pro Card
        Div proCard = new Div();
        proCard.addClassNames("cards__card", "card");
        H2 proHeading = new H2("Mission");
        proHeading.addClassName("card__heading");
        Paragraph proPrice = new Paragraph("Mission");
        proPrice.addClassName("card__price");
        Paragraph missionText = new Paragraph(
            "Our mission is to become a leading company in connecting businesses and driving innovation by providing best solutions to everyday business problems."+
            "And to provide innovative solutions that enhance operational efficiency and facilitate growth. " +
            "We build strong partnerships with our clients, understanding their challenges and working together to achieve their goals. " +
            "We stay at the forefront of technology trends, ensuring our clients benefit from the latest advancements and best practices. "
        );
        proCard.add(proHeading, missionText);

        // Ultimate Card
        Div ultimateCard = new Div();
        ultimateCard.addClassNames("cards__card", "card");
        H2 ultimateHeading = new H2("Vision");
        ultimateHeading.addClassName("card__heading");
        Paragraph ultimatePrice = new Paragraph("Vision");
        ultimatePrice.addClassName("card__price");
        Paragraph visionText = new Paragraph();
        Anchor hubspotLink = new Anchor("https://www.hubspot.com", "HubSpot");
        hubspotLink.getStyle().set("text-decoration", "underline");
        visionText.add(
            new Text("Just as companies like "), 
            hubspotLink,
            new Text(" have successfully integrated technology into marketing strategies, we aspire to revolutionise how businesses operate through specific designed technology solutions that enhance productivity and engagement. " +
            "We believe in keeping long-term partnerships with our clients, similar to the collaborative approaches seen in successful firms. Our goal is to unlock the full potential of businesses by implementing strategic solutions that drive sustainable growth.")
        );
        ultimateCard.add(ultimateHeading, visionText);

        // More info Card
        Div moreInfoCard = new Div();
        moreInfoCard.addClassNames("cards__card", "card", "moreInfocard");
        H2 moreInfoHeading = new H2("Why us?");
        moreInfoHeading.addClassName("card__heading");
        Paragraph moreInfoPrice = new Paragraph("Why us?");
        moreInfoPrice.addClassName("card__price");
        moreInfoCard.add(moreInfoHeading, research());

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

    private VerticalLayout research() {
        VerticalLayout research = new VerticalLayout();
        
        Paragraph introductionParagraph = new Paragraph(
            "Our company focuses on delivering high-quality, versatile services, including, " +
            "Web Application Development, Mobile Application Development, UI/UX Design, Brand Identity Design, " +
            "Graphic Design, Construction Architecture, 3D Modeling & Visualization, Business Analysis, and " +
            "Solutions Architecture. Operating with zero employees apart from the CEO, we stand out as one of " +
            "the first companies to successfully implement this model. By collaborating with talented professionals " +
            "worldwide, we ensure that every project is handled by the best experts in the field, providing " +
            "tailored solutions that exceed expectations."
        );
        introductionParagraph.addClassName("introduction-text");
        
        // Industry Analysis Section
        H2 industryAnalysis = new H2("Industry analysis");
        industryAnalysis.addClassName("section-heading");
        
        // Market Size and Growth subsection
        H3 marketSize = new H3("Market size and growth");
        marketSize.addClassName("subsection-heading");
        
        Paragraph marketSizeParagraph = new Paragraph(
            "The global web and mobile application development market is booming. According to recent studies, the market for web application development alone is projected to grow significantly in the coming years, driven by the increasing use of smartphones, internet penetration, and businesses transitioning to digital platforms. Mobile applications, essential for modern-day businesses, are seeing similar growth, particularly with the shift toward mobile-first strategies. "+
            "This rapid expansion highlights the importance of offering services like ours, which cater to a wide array of industries needing innovative, user-friendly digital solutions."
        );
        
        // Trends subsection
        H3 trends = new H3("Trends shaping the market");
        trends.addClassName("subsection-heading");
        
        Paragraph trendsContent = new Paragraph(
            "The current technological landscape is being reshaped by several key innovations. Progressive Web Apps (PWAs) " +
            "have emerged as a revolutionary approach, seamlessly combining the best aspects of websites and mobile applications. " +
            "These applications offer superior performance with faster loading times and offline functionality, marking a significant " +
            "shift in web development paradigms. The widespread adoption of cloud-based solutions represents another fundamental " +
            "shift in the industry. As businesses increasingly migrate to cloud environments, the demand for scalable and " +
            "efficient cloud-native applications continues to surge. Furthermore, the integration of artificial intelligence " +
            "has become increasingly sophisticated, transforming how applications function and interact with users. From " +
            "predictive analytics to natural language processing, AI is revolutionizing user experiences and automating " +
            "complex processes across various domains."
        );
        
        // Customer Segmentation Section
        H2 customerSegmentation = new H2("Customer segmentation");
        customerSegmentation.addClassName("section-heading");
        
        // Target Audience subsection
        H3 targetAudience = new H3("Target audience identification");
        targetAudience.addClassName("subsection-heading");
        
        Paragraph targetIntro = new Paragraph("Our services are designed for:"+
            "Our target audience includes Startups - entrepreneurs seeking brand identity design to establish their business in competitive markets; " +
            "Businesses - organisations requiring custom software solutions to improve operations and enhance customer engagement; and " +
            "Industries - sectors like construction and real estate needing 3D modeling and visualisation services for projects and proposals."
        );
        
        // Behavioral Insights subsection
        H3 behavioralInsights = new H3("Behavioral insights");
        behavioralInsights.addClassName("subsection-heading");
        
        Paragraph insightsIntro = new Paragraph("Analytics and surveys reveal that customers prioritise: "+
            "User Experience: The demand for intuitive and visually appealing designs is at an all-time high. " +
            "Cost-Effective Solutions: Businesses seek quality services without the overhead of traditional operations."
        );
        
        // Service Offerings Evaluation Section
        H2 serviceOfferings = new H2("Service offerings evaluation");
        serviceOfferings.addClassName("section-heading");
        
        // Competitive Analysis subsection
        H3 competitiveAnalysis = new H3("Competitive analysis");
        competitiveAnalysis.addClassName("subsection-heading");
        
        Paragraph competitiveAnalysisParagraph = new Paragraph(
            "The market is filled with companies offering web development, UI/UX design, and architectural " +
            "visualisation. However, many of them rely on fixed teams, limiting flexibility and innovation. " +
            "By analyzing competitors' pricing, delivery times, and customer feedback, we identified a gap " +
            "for services that are highly customisable and executed by specialised global professionals."
        );
        
        // USP subsection
        H3 usp = new H3("Our unique selling proposition (USP)");
        usp.addClassName("subsection-heading");
        
        Paragraph uspIntro = new Paragraph(
            "Our operational model is unconventional yet highly effective. By collaborating with professionals " +
            "worldwide, we tap into a global talent pool that ensures every project is handled by the best in " +
            "the industry. We reduce overhead costs, passing savings to the client. " +
            "We increase flexibility in service delivery. " +
            "We enable access to a diverse range of expertise for any project."
        );
        
        // Regulatory Environment Section
        H2 regulatoryEnvironment = new H2("Regulatory framework and compliance");
        regulatoryEnvironment.addClassName("section-heading");
        
        Paragraph regulatoryContent = new Paragraph(
            "The regulatory landscape governing digital services and technology solutions continues to evolve at a rapid pace. " +
            "Our comprehensive analysis of global regulatory requirements reveals two critical areas that significantly impact " +
            "service delivery in the digital sphere. Data protection legislation, particularly the General Data Protection " +
            "Regulation (GDPR) in Europe and the Protection of Personal Information Act (POPIA) in South Africa, has " +
            "established stringent requirements for data handling and privacy protection. These regulations fundamentally " +
            "shape our approach to solution design and implementation. Additionally, adherence to international standards " +
            "such as ISO/IEC 27001 for information security management has become increasingly crucial in ensuring service " +
            "quality and maintaining client trust. Our global collaboration model uniquely positions us to navigate these " +
            "complex regulatory environments, as we integrate expertise from professionals well-versed in regional compliance " +
            "requirements, ensuring our solutions remain both innovative and compliant across different jurisdictions."
        );

        // Technology Trends Section
        H2 technologyTrends = new H2("Technological innovation and market evolution");
        technologyTrends.addClassName("section-heading");
        
        Paragraph technologyContent = new Paragraph(
            "The technological landscape continues to undergo rapid transformation, with several key innovations driving " +
            "industry evolution. Artificial intelligence has emerged as a cornerstone of modern business solutions, with " +
            "AI-driven analytics tools revolutionizing decision-making processes across industries. Machine learning " +
            "applications have demonstrated particular promise in enhancing application performance and user experience, " +
            "from sophisticated behavior prediction models to dynamic functionality adaptation. The adoption of advanced " +
            "frameworks such as Flutter, React Native, and Three.js has significantly elevated the capabilities of modern " +
            "applications, enabling the development of high-performance, cross-platform solutions and immersive 3D " +
            "experiences. These technological advancements are not merely theoretical possibilities but are being actively " +
            "integrated into business operations, with adoption rates showing steady growth across various sectors. " +
            "Organizations increasingly recognize the potential of cloud computing and AI technologies to drive cost " +
            "efficiencies and enhance operational capabilities, leading to a growing demand for solutions that leverage " +
            "these advanced technologies."
        );

        // Marketing Strategy Section
        H2 marketingStrategy = new H2("Strategic market engagement and brand development");
        marketingStrategy.addClassName("section-heading");
        
        Paragraph marketingContent = new Paragraph(
            "Our market engagement strategy is built upon a deep understanding of digital channels and their effectiveness " +
            "in reaching diverse client segments. Through careful analysis of engagement patterns, we have identified key " +
            "platforms that facilitate meaningful connections with our target audience. Social media platforms, particularly " +
            "Instagram and LinkedIn, serve as powerful vehicles for showcasing our portfolio and establishing credibility " +
            "in the creative and technical domains. Professional networks have proven especially effective for engaging " +
            "decision-makers interested in technical services such as business analysis and solutions architecture. Our " +
            "digital advertising strategy employs sophisticated targeting mechanisms across search engines and social " +
            "platforms, ensuring our message reaches potential clients at crucial decision-making moments. This multi-channel " +
            "approach is complemented by our distinctive brand positioning, which emphasizes our innovative operational " +
            "model and global collaboration framework. By highlighting these unique aspects, we have successfully " +
            "differentiated ourselves in a competitive market, attracting clients who value both innovation and efficiency " +
            "in their technology partners."
        );

        // Sustainability Section
        H2 sustainability = new H2("Environmental sustainability and digital innovation");
        sustainability.addClassName("section-heading");
        
        Paragraph sustainabilityContent = new Paragraph(
            "Environmental sustainability has emerged as a critical consideration in modern business operations, particularly " +
            "within the technology sector. Our research indicates a growing awareness of the environmental impact of " +
            "traditional IT infrastructure and business practices. In response to these concerns, we have developed a " +
            "comprehensive approach to environmental sustainability that integrates ecological considerations into our " +
            "service delivery model. The adoption of cloud computing technologies represents a significant step toward " +
            "reducing energy consumption, as cloud-based solutions typically demonstrate superior energy efficiency " +
            "compared to traditional on-premises infrastructure. Our commitment to digital transformation extends beyond " +
            "mere technological advancement, encompassing the broader goal of helping businesses transition from " +
            "resource-intensive paper-based processes to environmentally conscious digital solutions. Furthermore, our " +
            "distributed operational model, which emphasizes global collaboration through digital channels, significantly " +
            "reduces the environmental impact associated with traditional office-based operations. This approach not only " +
            "aligns with our sustainability goals but also resonates strongly with environmentally conscious clients who " +
            "seek partners sharing their commitment to ecological responsibility."
        );

        // Future-Ready Solutions Section
        H2 futureReady = new H2("Future-oriented technology solutions");
        futureReady.addClassName("section-heading");
        
        Paragraph futureContent = new Paragraph(
            "Our research into future technology trends and market evolution has informed a forward-looking approach to " +
            "solution development. The integration of artificial intelligence and machine learning capabilities represents " +
            "a fundamental shift in how applications process information and interact with users, enabling unprecedented " +
            "levels of automation and intelligence in business processes. Cloud-native architectures have emerged as a " +
            "crucial foundation for future-ready applications, providing the scalability and flexibility necessary to " +
            "accommodate rapid business growth and changing market demands. Our emphasis on modular design principles " +
            "ensures that solutions can evolve alongside technological advancements, allowing for seamless integration " +
            "of new capabilities and adaptation to emerging business requirements. This forward-thinking approach extends " +
            "beyond technical considerations to encompass broader business implications, ensuring our clients are well-positioned " +
            "to leverage new opportunities and navigate future challenges in an increasingly digital business landscape."
        );

        research.add(
            introductionParagraph,
            industryAnalysis,
            marketSize,
            marketSizeParagraph,
            trends,
            trendsContent,
            customerSegmentation,
            targetAudience,
            targetIntro,
            behavioralInsights,
            insightsIntro,
            serviceOfferings,
            competitiveAnalysis,
            competitiveAnalysisParagraph,
            usp,
            uspIntro,
            regulatoryEnvironment,
            regulatoryContent,
            technologyTrends,
            technologyContent,
            marketingStrategy,
            marketingContent,
            sustainability,
            sustainabilityContent,
            futureReady,
            futureContent
        );
        
        research.setSpacing(true);
        research.setPadding(true);
        
        return research;
    }
}