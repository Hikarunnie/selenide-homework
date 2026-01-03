package ge.tbc.testautomation.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
public class CreateListPage {
    public SelenideElement newList = $("h1.title-hero");
    public SelenideElement listNameField = $("input.field[name='name']");
    public SelenideElement selectField = $(byId("frm-sharing-policy"));
    public SelenideElement rankedCheckbox = $("#show-item-numbers");
    public SelenideElement movieAddField = $(byId("frm-list-film-name"));
    public SelenideElement description = $("textarea[name='notes']");
    public ElementsCollection movieItems = $$("ul#list-items li.film-list-entry");
    public SelenideElement saveButton = $(byId("list-edit-save"));
    public SelenideElement notificationMessage = $("div.jnotify-container");



}
