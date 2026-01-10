package ge.tbc.testautomation.steps.swoopGe;
import com.codeborne.selenide.SelenideElement;
import ge.tbc.testautomation.pages.swoopGe.SportsPage;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.text;
public class SportsPageSteps {
    SportsPage sportsPage = new SportsPage();
    public double calculatingCorrectSalePrice(double originalPrice, int discountPercent) {
        return originalPrice - (originalPrice * discountPercent / 100);
    }
    public void AssertAfterDiscountPrice(SelenideElement offer, double originalPrice, int discount) {
        double expectedSalePrice = calculatingCorrectSalePrice(originalPrice, discount);
        sportsPage.discountedPrice(offer).shouldBe(text(expectedSalePrice+"₾"));

        //since some prices are rounded some aren't on website, this logic just in case
//        double actualDiscountPrice = Double.parseDouble(sportsPage.discountedPrice(offer).getText().replace("₾", "").trim());
//        Assert.assertEquals(expectedSalePrice, actualDiscountPrice);
    }
}
