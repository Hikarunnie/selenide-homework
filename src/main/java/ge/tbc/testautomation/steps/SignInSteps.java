package ge.tbc.testautomation.steps;

import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.pages.SigningInPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class SignInSteps {
    SigningInPage signingInPage = new SigningInPage();

    public SignInSteps goToSignInSection(){
        signingInPage.signInSection.click();
        return this;
    }
    public SignInSteps fillUserNameInput(){
        signingInPage.userName.setValue(Constants.USER_NAME);
        return this;
    }
    public SignInSteps fillPasswordInput(){
        signingInPage.password.sendKeys(Constants.PASSWORD);
        return this;
    }
    public SignInSteps clickSignInButton(){
        signingInPage.signInButton.click();
        return this;
    }
    public SignInSteps assertHomepageMove(){
        signingInPage.welcomeBack.shouldBe(visible).shouldHave(text(Constants.WELCOME_BACK));
        return this;
    }


}
