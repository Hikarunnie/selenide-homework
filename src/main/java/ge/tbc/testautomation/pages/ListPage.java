package ge.tbc.testautomation.pages;

import com.codeborne.selenide.SelenideElement;
import ge.tbc.testautomation.data.Constants;

import static com.codeborne.selenide.Selenide.$;

public class ListPage {
    public SelenideElement listPageHeader = $("a[href ='/lists/']");
    public SelenideElement featuredList = $("a[href='/lists/featured/']");
    public SelenideElement newListCreateButton = $("a.text-slug[href='/list/new/']");


}
