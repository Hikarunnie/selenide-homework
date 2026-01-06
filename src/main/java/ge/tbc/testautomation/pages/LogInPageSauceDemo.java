package ge.tbc.testautomation.pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
public class LogInPageSauceDemo {
    public SelenideElement username= $(byId("user-name"));
    public SelenideElement password = $(byId("password"));
    public SelenideElement loginButton = $(byId("login-button"));
}
