package ge.tbc.testautomation.pages;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
public class SigningInPage {
    public SelenideElement signInSection = $("a[href ='/sign-in/']");
    public SelenideElement userName = $(byId("username"));
    public SelenideElement password = $(byId("password"));
    public SelenideElement signInButton = $("input[value='Sign in']");
    public SelenideElement welcomeBack = $("h1.title-hero");

}
