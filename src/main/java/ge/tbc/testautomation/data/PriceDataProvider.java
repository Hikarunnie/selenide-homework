package ge.tbc.testautomation.data;
import com.codeborne.selenide.SelenideElement;
import ge.tbc.testautomation.pages.swoopGe.SportsPage;
import org.testng.annotations.DataProvider;
public class PriceDataProvider {
    @DataProvider(name = "sportsSectionOffersAndPrices")
    public static Object[][] sportsSectionPriceDataProvider(){
        SportsPage sportsPage= new SportsPage();
        Object[][] data = new Object[sportsPage.offers10.size()][3];

        for (int i = 0; i < sportsPage.offers10.size(); i++) {
            SelenideElement offer =sportsPage.offers10.get(i);

            double originalPrice = Double.parseDouble(sportsPage.originalPrice(offer).getText().replace("₾", "").trim());
            int discountPercent = Integer.parseInt(sportsPage.discountElement(offer).getText().replace("-", "").replace("%", "").trim());

            data[i][0] = offer;
            data[i][1] = originalPrice;
            data[i][2] = discountPercent;
        }
        return data;
    }
}
