package ge.tbc.testautomation.tests;

import com.codeborne.selenide.Configuration;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.steps.LogInStepsSauceDemo;
import org.testng.annotations.*;

import static com.codeborne.selenide.Selenide.open;

public class BaseTests {
    @BeforeSuite
    public void initializingDatabase(){
        System.out.println("Initializing a dummy database connection");
    }

    //@BeforeTest and @AfterTest: Launch and close the browser
    @BeforeTest
    @Parameters("browserType")
    public void launchingBrowser(String browserType){
        System.out.println("Initializing Browser");
        if(browserType.equalsIgnoreCase("chrome")){
            Configuration.browser = "chrome";
        }else if (browserType.equalsIgnoreCase("edge")) {
            Configuration.browser = "edge";
        }
        Configuration.timeout = 8000;
    }

    //@BeforeClass and @AfterClass: Login and logout of the e-commerce application.
    @BeforeClass
    public void setUp() {
        open(Constants.SAUCE_DEMO_URL);
        LogInStepsSauceDemo logInSteps = new LogInStepsSauceDemo();
        logInSteps.fillUserNameInput()
                .fillPasswordInput()
                .clickLoginButton();
        System.out.println("logging in ");
    }

    @BeforeMethod
    public void navigateToHomePage() {
        System.out.println("Navigating to home page");
    }

    @AfterMethod
    public void returnToHomePage() {
        System.out.println("Returning to home page");
    }

    @AfterClass
    public void LogOut() {
        System.out.println("logging out ");
    }

    @AfterTest
    public void closingBrowser(){
        System.out.println("closing Browser");
    }

    @AfterSuite
    public void closingDatabase(){
        System.out.println("closing a dummy database connection");
    }
}
