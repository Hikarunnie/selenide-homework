package ge.tbc.testautomation.steps;

import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.pages.ListPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class ListPageSteps {
    ListPage listPage = new ListPage();

    public ListPageSteps navigateToListPage() {
        listPage.listPageHeader.click();
        return this;
    }
    public ListPageSteps assertListPageTitle(){
        listPage.featuredList.shouldBe(visible).shouldHave(text(Constants.FEATURED_LISTS));
        return this;
    }
    public ListPageSteps assertListPageUrl(){
        webdriver().shouldHave(url(Constants.LIST_PAGE_URL));
        return this;
    }
    public ListPageSteps creatingNewList(){
        listPage.newListCreateButton.click();
        return this;
    }


}
