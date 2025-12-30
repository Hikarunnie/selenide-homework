package ge.tbc.testautomation;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class Tests{
    @BeforeClass
    public void setUp(){
        open("//www.telerik.com/support/demos");
    }

//1) In Web section, validate that all cards have purple overlay effect on hover.
    @Test(description = "validating that all cards have purple overlay effect", priority = 1)

    }


}
