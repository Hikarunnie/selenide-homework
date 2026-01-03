package ge.tbc.testautomation.steps;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.pages.CreateListPage;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.textsInAnyOrder;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.sleep;

public class CreateListSteps {
    CreateListPage createListPage = new CreateListPage();;
    public CreateListSteps assertNewListPageTitle(){
        createListPage.newList.shouldBe(visible).shouldHave(text(Constants.NEW_LIST_PAGE_TITLE));
        return this;
    }
    public CreateListSteps fillListName() {
        createListPage.listNameField.sendKeys(Constants.LIST_NAME);
        return this;
    }
    public CreateListSteps selectSharingPolicy() {
        createListPage.selectField.selectOption(Constants.WHO_CAN_VIEW);
        return this;
    }
    public CreateListSteps checkRankedOption() {
        executeJavaScript("arguments[0].click();", createListPage.rankedCheckbox);
        return this;
    }
    public CreateListSteps addMovie(String movieName) {
        createListPage.movieAddField.sendKeys(movieName);
        sleep(1000);
        createListPage.movieAddField.pressEnter();
        sleep(1000);
        return this;
    }
    public CreateListSteps fillDescription() {
        createListPage.description.sendKeys(Constants.DESCRIPTION);
        return this;
    }
    public CreateListSteps assertListNameValue() {
        createListPage.listNameField.shouldHave(value(Constants.LIST_NAME));
        return this;
    }

    public CreateListSteps assertRankedCheckboxChecked() {
        createListPage.rankedCheckbox.shouldBe(checked);
        return this;
    }
    public CreateListSteps assertSharingPolicy() {
        createListPage.selectField.getSelectedOption().shouldHave(text(Constants.WHO_CAN_VIEW));
        return this;
    }
    public CreateListSteps assertMovieItems(int expectedSize, String[] movies) {
        sleep(2000);
        createListPage.movieItems.shouldHave(size(expectedSize));
        createListPage.movieItems.shouldHave(textsInAnyOrder(movies));
        return this;
    }
   public CreateListSteps saveList(){
        createListPage.saveButton.click();
       sleep(2000);
       return this;
   }
   public CreateListSteps assertSuccessNotification(){
        createListPage.notificationMessage.shouldBe(visible).shouldNotBe(empty);
        return this;
   }
    public CreateListSteps assertSaveButtonDisabled() {
        createListPage.saveButton.shouldHave(cssClass("-disabled"));
        return this;
    }
}
