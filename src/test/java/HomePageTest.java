import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;


/**
 * Created by rchepkunova on 18.08.2016.
 */
public class HomePageTest extends BeforeTests {


    public HomePageTest() throws IOException {
        super(true);
    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.close();
    }

    @Test(priority = 1)
    public void isHomePageVisibleTest() {
        assertTrue(homePage.isHomePageVisible());
    }

    @Test(dependsOnMethods = { "isHomePageVisibleTest" })
    public void getRegisterFormTest() {
        homePage.callRegisterPopup();

        assertTrue(homePage.isRegisterPopUpVisible());
    }
}
