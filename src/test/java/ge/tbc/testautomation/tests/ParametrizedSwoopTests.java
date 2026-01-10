package ge.tbc.testautomation.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.data.PriceDataProvider;
import ge.tbc.testautomation.steps.swoopGe.CommonSteps;
import ge.tbc.testautomation.steps.swoopGe.SportsPageSteps;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

public class ParametrizedSwoopTests {
    CommonSteps homeSteps = new CommonSteps();
    SportsPageSteps sportsPageSteps = new SportsPageSteps();

    @BeforeSuite // so that dataProvider has time to collect data dynamically
    public void setUp(){  //ill keep this here since hw didn't say to use xml and basetest or not T_T
        Configuration.browser = "chrome";
        Configuration.timeout = 8000;
        open(Constants.SWOOP_GE_URL);
        homeSteps.navigatingToSportsSection();
        sleep(2000); //workssssss
}

    @Test(dataProviderClass = PriceDataProvider.class, dataProvider = "sportsSectionOffersAndPrices", description = "checking sales values test")
    public void checkSaleValuesTest(SelenideElement offer, double originalPrice, int discount){
        sportsPageSteps.AssertAfterDiscountPrice(offer, originalPrice,discount);
    }
}
