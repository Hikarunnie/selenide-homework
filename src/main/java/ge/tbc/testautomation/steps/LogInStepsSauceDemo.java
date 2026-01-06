package ge.tbc.testautomation.steps;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.pages.LogInPageSauceDemo;

public class LogInStepsSauceDemo {
    LogInPageSauceDemo logInPage = new LogInPageSauceDemo();
    public LogInStepsSauceDemo fillUserNameInput(){
        logInPage.username.sendKeys(Constants.STANDARD_USER);

        return this;
    }
    public LogInStepsSauceDemo fillPasswordInput(){
        logInPage.password.sendKeys(Constants.PASSWORD);

        return this;
    }
    public LogInStepsSauceDemo clickLoginButton(){
        logInPage.loginButton.click();
        return this;
    }
}
