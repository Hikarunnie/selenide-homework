package ge.tbc.testautomation.tests;
import org.testng.annotations.BeforeMethod;
import com.codeborne.selenide.Configuration;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.steps.swoopGe.CommonSteps;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.open;

public class ParametrizedSwoopTests2 {
     String categoryName;
     CommonSteps commonSteps;

    @BeforeMethod
    public void setUp() {
        commonSteps = new CommonSteps();
        Configuration.browser = "chrome";
        Configuration.timeout = 8000;
        open(Constants.SWOOP_GE_URL);
    }
    public ParametrizedSwoopTests2(String categoryName){
        this.categoryName = categoryName;
    }

    @Test(description = "price filter test", priority = 1)
    public void filterTest(){
        commonSteps.navigatingToCategory(categoryName)
                .openFilterMenu()
                .selectPriceFilter(Constants.PRICE_RANGE)
                .verifyProductsRange();
    }

    @Test(description = "price range tests", priority = 2)
    public void rangeTest(){
        commonSteps.navigatingToCategory(categoryName)
                .openFilterMenu()
                .selectRange()
                .verifyProductsRange();
    }
}
