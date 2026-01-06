package ge.tbc.testautomation.tests;

import com.codeborne.selenide.Configuration;
import ge.tbc.testautomation.data.Constants;
import ge.tbc.testautomation.steps.LogInStepsSauceDemo;
import org.testng.Assert;
import org.testng.annotations.*;
import static com.codeborne.selenide.Selenide.*;
import java.util.Random;


public class TestngTests{
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

    @AfterTest
    public void closingBrowser(){
        System.out.println("closing Browser");
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

    //////////////////////////
    @Test(description = "discount applying status", priority = 1, invocationCount = 5, successPercentage = 60)
    public void testFlakyDiscountCode() {
        Random rand = new Random();
        boolean successFailure = rand.nextBoolean();

        System.out.println("discount apply was " + successFailure);
        Assert.assertTrue(successFailure);
    }

    @Test(description = "adding item to cart")
    public void addToCartTest() {
        System.out.println("adding item to cart");
//        Assert.assertTrue(true);
    }

    @Test(description = "Proceeding to checkout", dependsOnMethods = {"addToCartTest"})
    public void proceedToCheckoutTest() {
        System.out.println("Proceeding to checkout");
//        Assert.assertTrue(false);
    }

    @Test(description = "making payment", dependsOnMethods = {"proceedToCheckoutTest"})
    //got skipped since previous one failed
    public void makePaymentTest() {
        System.out.println("making payment");
//        Assert.assertTrue(true);
    }

    @Test(description = "validating image sizes", priority = 2)
    public void validateImageSizes() {
        System.out.println("validating image sizes");
    }

    @Test(description = "sorting offers", priority = 1)
    public void sortOffers() {
        System.out.println("sorting offers");
    }

    @Test(description = "validating burger menu", priority = 3)
    public void validateBurgerMenu() {
        System.out.println("validating burger menu");
    }

    /////////////////////////
    @AfterMethod
    public void returnToHomePage() {
        System.out.println("Returning to home page");
    }
    @AfterClass
    public void LogOut() {
        System.out.println("logging out ");
    }
    @AfterSuite
    public void closingDatabase(){
        System.out.println("closing a dummy database connection");
    }
}
