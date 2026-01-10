package ge.tbc.testautomation.pages.swoopGe;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
public class CommonPage {
    public SelenideElement getCategoryIcon(String categoryName) {
        return $("img[alt='" + categoryName + "']");
    }
    public SelenideElement filterButton = $(byXpath("//p[text()='ფასი']"));
    public ElementsCollection priceRanges = $$("div[id*='headlessui-radiogroup-option']");
    public SelenideElement getPriceFilter(String priceRange) {
        return priceRanges.findBy(text(priceRange));
    }
    public ElementsCollection rangeEnds = $$("div.cursor-grab.bg-white.border-hotpink-100");
    public SelenideElement minPriceEnd = rangeEnds.first();
    public SelenideElement maxPriceEnd = rangeEnds.last();
    public ElementsCollection rangeOfPrices = $$("p[class*='text-hotpink-100']");
    public SelenideElement getMinPrice = rangeOfPrices.first();
    public SelenideElement getMaxPrice = rangeOfPrices.last();

    public ElementsCollection filteredProducts = $$("div[class *= 'grid grid-cols-1'] a");
    public SelenideElement getOfferPrice(SelenideElement offer) {
         return offer.$("p[class*='text-raisin-black-100 text-md']");
    }
}
