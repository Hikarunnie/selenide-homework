package ge.tbc.testautomation.tests;
import com.codeborne.selenide.*;
import ge.tbc.testautomation.data.Constants;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

@Test(groups = {"E2E - Creating and saving a list  - SCRUM-T27"})
public class CreatingAndSavingList{
    @BeforeClass
    public void setUp(){
        Configuration.browser = "Edge";
        Configuration.timeout = 8000;
        Configuration.holdBrowserOpen=true;
    }

    @Test(description = "signing in", priority = 1)
    public void signingIn(){
        open("https://letterboxd.com/");
        SelenideElement signInSection = $("a[href ='/sign-in/']");
        signInSection.click();

        SelenideElement userName = $(byId("username"));
        userName.setValue(Constants.USER_NAME);
        SelenideElement password = $(byId("password"));
        password.sendKeys(Constants.PASSWORD);
        SelenideElement signInButton = $("input[value='Sign in']");
        signInButton.click();

        SelenideElement welcomeBack = $("h1.title-hero");
        welcomeBack.shouldBe(visible).shouldHave(text(Constants.WELCOME_BACK));

  }
    @Test(description = "navigating to List page", priority = 2)
    public void navigatingToListPage(){
        SelenideElement listPageHeader = $("a[href ='/lists/']");
        listPageHeader.click();

        SelenideElement featuredList = $("a[href='/lists/featured/']");
        featuredList.shouldBe(visible).shouldHave(text(Constants.FEATURED_LISTS));
        webdriver().shouldHave(url(Constants.LIST_PAGE_URL)); //wanted to try this
    }

    @Test(description = "starting new list ", priority = 3)
    public void startingNewList(){
        SelenideElement newListCreateButton = $("a.text-slug[href='/list/new/']");
        newListCreateButton.click();

        SelenideElement welcomeBack = $("h1.title-hero");
        welcomeBack.shouldBe(visible).shouldHave(text(Constants.NEW_LIST_PAGE_TITLE));

    }

    @Test(description = "filling list details ", priority = 4)
    public void fillingListDetails(){
        SelenideElement listNameField = $("input.field[name='name']");
        listNameField.sendKeys(Constants.LIST_NAME);

        SelenideElement selectField = $(byId("frm-sharing-policy"));
        selectField.selectOption(Constants.WHO_CAN_VIEW);

        SelenideElement rankedCheckbox = $("#show-item-numbers");
        executeJavaScript("arguments[0].click();", rankedCheckbox);

        SelenideElement movieAddField = $(byId("frm-list-film-name"));
        movieAddField.sendKeys(Constants.MOVIE_1);
        sleep(1000);
        movieAddField.pressEnter();
        sleep(1000);
        movieAddField.sendKeys(Constants.MOVIE_2);
        sleep(1000);
        movieAddField.pressEnter();

        SelenideElement description = $("textarea[name='notes']");
        description.sendKeys(Constants.DESCRIPTION);

        ////
        listNameField.shouldHave(value(Constants.LIST_NAME));
        rankedCheckbox.shouldBe(checked);
        selectField.getSelectedOption().shouldHave(text(Constants.WHO_CAN_VIEW));

        sleep(2000);
        ElementsCollection movieItems = $$("ul#list-items li.film-list-entry");
        movieItems.shouldHave(size(2));
        movieItems.shouldHave(textsInAnyOrder(Constants.MOVIE_1, Constants.MOVIE_2));

    }

    @Test(description = "saving list", priority = 5)
    public void savingList(){
        SelenideElement saveButton = $(byId("list-edit-save"));
        saveButton.click();
        sleep(2000);
        SelenideElement notificationMessage = $("div.jnotify-container");
        notificationMessage.shouldBe(visible).shouldNotBe(empty);
        saveButton.shouldHave(cssClass("-disabled"));

        System.out.println("Bye");
    }
}
