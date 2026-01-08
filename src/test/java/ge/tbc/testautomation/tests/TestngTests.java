package ge.tbc.testautomation.tests;
import ge.tbc.testautomation.Util.RetryAnalyzer;
import ge.tbc.testautomation.Util.RetryCount;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.Random;
public class TestngTests{

    @RetryCount(count = 5)
    @Test(groups = {"purchase_tests"}, description = "discount applying status",priority = 1,retryAnalyzer = RetryAnalyzer.class)
    public void testFlakyDiscountCode() {
        System.out.println("discount with invocationCount");

        Random rand = new Random();
        boolean successFailure = rand.nextBoolean();

        System.out.println("discount apply was " + successFailure);
        Assert.assertTrue(successFailure);
    }

    @Test(groups = {"purchase_tests"}, description = "adding item to cart")
    public void addToCartTest() {
        System.out.println("adding item to cart");
//        Assert.assertTrue(true);
    }

    @Test(groups = {"purchase_tests"}, description = "Proceeding to checkout",dependsOnMethods = {"addToCartTest"})
    public void proceedToCheckoutTest() {
        System.out.println("Proceeding to checkout");
//        Assert.assertTrue(false);
    }

    @Test(groups = {"purchase_tests"}, description = "making payment",dependsOnMethods = {"proceedToCheckoutTest"})
    //got skipped since previous one failed
    public void makePaymentTest() {
        System.out.println("making payment");
//        Assert.assertTrue(true);
    }

    @Test(groups = {"non_purchase_tests"}, description = "validating image sizes", priority = 2)
    public void validateImageSizes() {
        System.out.println("validating image sizes");
    }

    @Test(groups = {"non_purchase_tests"}, description = "sorting offers",priority = 1)
    public void sortOffers() {
        System.out.println("sorting offers");
    }

    @Test(groups = {"non_purchase_tests"}, description = "validating burger menu",priority = 3)
    public void validateBurgerMenu() {
        System.out.println("validating burger menu");
    }

    /////////////////////////
}
