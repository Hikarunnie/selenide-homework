package ge.tbc.testautomation.steps.swoopGe;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.pages.swoopGe.CommonPage;
import org.testng.Assert;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.actions;
public class CommonSteps {
    CommonPage commonPage = new CommonPage();
    boolean range;
    public CommonSteps navigatingToSportsSection(){
        commonPage.getCategoryIcon(Constants.SPORTS_CATEGORY).click();
        return this;
    }
    public CommonSteps navigatingToCategory(String categoryName){
        commonPage.getCategoryIcon(categoryName).click();
        return this;
    }
    public CommonSteps openFilterMenu() {
        commonPage.filterButton.shouldBe(visible).click();
        return this;
    }
    public CommonSteps selectPriceFilter(String priceRange) {
        commonPage.getPriceFilter(priceRange).click();
        return this;
    }
    public CommonSteps selectRange(){
        actions().clickAndHold(commonPage.minPriceEnd).moveByOffset(44, 0).release().perform();
        actions().clickAndHold(commonPage.maxPriceEnd).moveByOffset(-124, 0).release() .perform();
        range = true;
        return this;
    }
    public CommonSteps verifyProductsRange() {
        int minPrice;
        int maxPrice;

        if (range) { //for range
            minPrice = Integer.parseInt(commonPage.getMinPrice.getText().replace("₾", "").replace(",", "").trim());
            maxPrice = Integer.parseInt(commonPage.getMaxPrice.getText().replace("₾", "").replace(",", "").trim());
        } else {
            String[] parts = Constants.PRICE_RANGE.replace("₾", "").replace(" ", "").split("-");//for filter
            minPrice = Integer.parseInt(parts[0]);
            maxPrice = Integer.parseInt(parts[1]);
        }

        ElementsCollection products = commonPage.filteredProducts;
        products.first().shouldBe(visible);

        for (SelenideElement product : products) {
            String priceText = commonPage.getOfferPrice(product).shouldBe(visible).getText();
            int price = Integer.parseInt(priceText.replace("₾", "").replace(",", "").trim());
            Assert.assertTrue(price >= minPrice && price <= maxPrice);
        }
        return this;
    }
}
