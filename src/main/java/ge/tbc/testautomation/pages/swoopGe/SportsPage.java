package ge.tbc.testautomation.pages.swoopGe;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$$;
public class SportsPage {
    public ElementsCollection allOffers = $$("div[class *= 'grid grid-cols-1'] a");
    public ElementsCollection offers10 = allOffers.first(10);
    public SelenideElement discountElement(SelenideElement offer) {
        return offer.$("div[class*='bg-danger_red']");
    }
    public SelenideElement originalPrice(SelenideElement offer) {
        return offer.$("p[class*='line-through']");
    }
    public SelenideElement discountedPrice(SelenideElement offer) {
        return offer.$("p[class*='text-raisin-black-100']");
    }
}
