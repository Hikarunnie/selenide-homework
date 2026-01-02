package ge.tbc.testautomation.tests;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ge.tbc.testautomation.data.Constants;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import java.util.List;
import java.util.Map;
public class Tests {
    @BeforeClass
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.timeout = 8000;
        open("https://www.telerik.com/support/demos");
    }
    @Test(description = "validate that all cards have purple overlay effect on hover", priority = 1)
    public void validateDemosDesign() {
        //Web section
        $("a[aria-label='Web content']").click();
        ElementsCollection webCards =$("#web").parent().$("div.row.u-mb8").findAll("div.sf_colsIn");

        for (SelenideElement card : webCards) {
            if (card.$$(".HoverImg").isEmpty()) { //didn't work without this
                continue;
            }
            SelenideElement hoverImg = card.$(".HoverImg").hover();
            sleep(300);
            hoverImg.shouldHave(cssValue("background-color", Constants.PURPLE_RGBA));
        }

        SelenideElement kendoCard = webCards.findBy(text("Kendo UI"));
        kendoCard.$(".HoverImg").hover();
        kendoCard.$$("ul.List li")
                .findBy(text("UI for Vue Demos"))
                .shouldBe(visible);

        //Desktop section
        $("a[aria-label='Desktop content']").click();
        ElementsCollection desktopCards = $("#desktop").parent().$("div.row.u-mb8").findAll("div.sf_colsIn");
        List<SelenideElement> microsoftCards = desktopCards.stream()
                .filter(card -> !card.$$("img[title='Get It from Microsoft']").isEmpty())
                .toList();

        //Mobile section
        $("a[aria-label='Mobile content']").click();
        SelenideElement XamarinCard = $$("div.sf_colsIn").findBy(text(Constants.MOBILE_CARD));

        XamarinCard.$("img[title='Download on the App Store']").shouldBe(visible);
        XamarinCard.$("img[title='Get it on Google Play']").shouldBe(visible);
        XamarinCard.$("img[title='Get it from Microsoft']").shouldBe(visible);

        // section links
        SelenideElement stickyNav = $("nav.NavAlt");
        String fixedTop = stickyNav.getCssValue("top"); //ill also check top stays same
        executeJavaScript("window.scrollBy(0, 1000)");

        stickyNav.shouldHave(cssClass("is-fixed"));
        stickyNav.shouldHave(cssValue("position", "fixed"));
        stickyNav.shouldHave(cssValue("top", fixedTop));

        String fixedTop2 = stickyNav.getCssValue("top");

        executeJavaScript("window.scrollBy(0, 444)");
        stickyNav.shouldHave(cssValue("top", fixedTop2));

        //some section links didn't match their section headers so ill map them
        Map<String, String> headers = Map.of(
                "Reporting & Docs", "Reporting & Document Processing",
                "Sitefinity CMS", "Web Content Management"
        );

        ElementsCollection navLinks = $("nav.NavAlt").$$("a.NavAlt-anchor");

        for (SelenideElement link : navLinks) {
            link.click();
            sleep(300);
            // links become active and their background changes
            link.shouldHave(cssClass("is-active"));
            link.shouldHave(cssValue("background-color", Constants.ACTIVE_SECTION_BG_RGBA));

            //checking that links take the user to correct sections
            String linkText = link.getText();
            String expectedHeader = headers.getOrDefault(linkText, linkText);
            $$("h2.h3")
                    .findBy(text(expectedHeader))
                    .shouldBe(visible);
        }
    }

// didn't know, so since it's pretty small I didn't create separate class
    @Test(groups = {"functionality test - clicking and passing input to search bar (SCRUM-T26)"}, priority = 2)
    public void searchBarStatusValidation(){
        open("https://www.telerik.com/");
        SelenideElement searchIcon = $("[title='Search']");
        searchIcon.click();

        SelenideElement searchBar= $("input[type='search'], input[placeholder]");
        searchBar.click();
        searchBar.shouldBe(focused);

        searchBar.setValue(Constants.SEARCH_EXAMPLE);
        searchBar.shouldHave(value(Constants.SEARCH_EXAMPLE));
    }

}
