package ge.tbc.testautomation.tests;
import com.codeborne.selenide.*;
import com.codeborne.selenide.ex.UIAssertionError;
import ge.tbc.testautomation.data.Constants;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
public class SelenideBasics {
    @BeforeClass
    public void setUp(){
        Configuration.browser = "chrome";
        Configuration.timeout = 8000;
    }

    @Test(description = "validating bundle offers", priority =1)
    public void validateBundleOffers() {
        open("https://www.telerik.com/support/demos");
        SelenideElement pricePage = $(byText("See Plans & Pricing"));
        pricePage.click();
        SelenideElement showMoreButton = $x("//button[contains(text() ,'detailed comparison')]");
        showMoreButton.click();

        ElementsCollection purchaseCells = $$("div#ContentPlaceholder1_C1037_Col00 div.Purchase-cell");
        ElementsCollection purchaseDetails = $$("div#ContentPlaceholder1_C1037_Col00 div.Purchase-cell div.Purchase-details");

        //trying to love maps
        Map<String, Integer> columnIndexMap = new HashMap<>();
        for (int i = 0; i < purchaseCells.size(); i++) {
            String columnName = purchaseCells.get(i).$("div.Purchase-title h5").getText().trim();
            columnIndexMap.put(columnName, i);
        }
        int devCraftUIIndex = columnIndexMap.get(Constants.DEVCRAFT_UI);
        int devCraftCompleteIndex = columnIndexMap.get(Constants.DEVCRAFT_COMPLETE);
        int devCraftUltimateIndex = columnIndexMap.get(Constants.DEVCRAFT_ULTIMATE);

        //1) 'Mocking solution for rapid unit testing' feature is not included in DevCraft UI.
        purchaseDetails.get(devCraftUIIndex).$$("ul li").forEach(feature ->
                feature.shouldNotHave(text(Constants.MOCK_SOL_RAPID_UNIT)));

        ElementsCollection featureRows = $$("dl.track--pricing-body>div");
        String[] ultimateOnlyFeatures = {
                Constants.ISSUE_ESCALATION_TEXT,
                Constants.E2E_REPORT_MANAGEMENT,
                Constants.TELERIK_TEST_STUDIO_TEXT
        };
        for (String feature : ultimateOnlyFeatures) {
                if (feature.equals(Constants.E2E_REPORT_MANAGEMENT)) {
                    try {
                        purchaseDetails.get(devCraftUIIndex).$$("ul li").forEach(ftname ->
                                ftname.shouldNotHave(text(Constants.E2E_REPORT_MANAGEMENT)));

                        purchaseDetails.get(devCraftCompleteIndex).$$("ul li").forEach(ftname ->
                                ftname.shouldNotHave(text(Constants.E2E_REPORT_MANAGEMENT)));

                        purchaseDetails.get(devCraftUltimateIndex).$$("ul li")
                                .shouldHave(CollectionCondition.itemWithText(Constants.E2E_REPORT_MANAGEMENT));
                    } catch (Exception e) {
                        System.out.println("bug: this feature is not present on the page");
                    }
                } else {
                    try {
                        ElementsCollection columns = featureRows.findBy(text(feature)).$$("dd");
                        columns.get(devCraftUIIndex).shouldNotHave(text(Constants.CELL_CONTENT_YES));
                        columns.get(devCraftCompleteIndex).shouldNotHave(text(Constants.CELL_CONTENT_YES));
                        columns.get(devCraftUltimateIndex).shouldHave(text(Constants.CELL_CONTENT_YES));
                    } catch (NoSuchElementException | UIAssertionError e) {
                        System.out.println("bug: this feature is not present on the page");
                    }
                }
        }

        String[] allOfferFeatures = {
                Constants.KENDO_UI_JQUERY_TEXT,
                Constants.ON_DEMAND_VIDEO_ACCESS_TEXT
        };
        for (String feature : allOfferFeatures) {
            ElementsCollection featureColumn = featureRows.findBy(text(feature)).$$("dd");
            featureColumn.get(devCraftUIIndex).shouldHave(text(Constants.CELL_CONTENT_YES));
            featureColumn.get(devCraftCompleteIndex).shouldHave(text(Constants.CELL_CONTENT_YES));
            featureColumn.get(devCraftUltimateIndex).shouldHave(text(Constants.CELL_CONTENT_YES));
        }
        //6) DevCraft Ultimate supports 1 instance of 'Telerik Report Server' with 15 users .
        ElementsCollection telerikReportServer = featureRows.findBy(text("Telerik Report Server")).$$("dd");
        telerikReportServer.get(devCraftUltimateIndex).shouldHave(text(Constants.CELL_CONTENT_TELERIK_REPORTING));

        //7) 'Telerik Reporting' is supported by only DevCraft Complete and DevCraft Ultimate.
        ElementsCollection telerikReporting = featureRows.findBy(text("Telerik Reporting")).$$("dd");
        telerikReporting.get(devCraftUIIndex).shouldNotHave(text(Constants.CELL_CONTENT_YES));
        telerikReporting.get(devCraftCompleteIndex).shouldHave(text(Constants.CELL_CONTENT_YES));
        telerikReporting.get(devCraftUltimateIndex).shouldHave(text(Constants.CELL_CONTENT_YES));

        //sticky
        $("footer").scrollTo();
        $$("div").findBy(text(Constants.DEVCRAFT_UI)).shouldBe(visible);
        $$("div").findBy(text(Constants.DEVCRAFT_COMPLETE)).shouldBe(visible);
        $$("div").findBy(text(Constants.DEVCRAFT_ULTIMATE)).shouldBe(visible);
    }

@Test(description = "validating bundle offers", priority =2)
    public void validateIndividualOffers(){
       open("https://www.telerik.com/support/demos");
       SelenideElement pricePage = $("a[href='/purchase.aspx']");
       pricePage.click();
       SelenideElement individualOffers = $(byText(Constants.INDIVIDUAL_OFFERS));
       individualOffers.click();

       ElementsCollection allIndividualOffers = $$("div.Purchase-title.Purchase-title-cards-kendo");
       allIndividualOffers.shouldHave(size(3));

       allIndividualOffers.findBy(exactText(Constants.KENDO_REACT))
            .$x("./ancestor::div[contains(@class,'Purchase-card')]//button[@class='ComboBox-control']")
            .shouldHave(text(Constants.PRIORITY_SUPPORT));

       allIndividualOffers.findBy(exactText(Constants.KENDO_UI))
            .$x("./ancestor::div[contains(@class,'Purchase-card')]//button[@class='ComboBox-control']")
            .shouldHave(text(Constants.PRIORITY_SUPPORT));

        ElementsCollection allCards = $$("div.Purchase-card");

       allCards.get(1).$(".js-priority").shouldHave(text("749"));
       allCards.get(2).$(".js-priority").shouldHave(matchText("1199"));
    }
}
