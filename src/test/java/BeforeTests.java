import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.MarionetteDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import pages.HomePage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by rchepkunova on 18.08.2016.
 */
abstract class BeforeTests {
    WebDriver driver;
    HomePage homePage = null;
    private Properties testProperties = new Properties();

    BeforeTests(boolean openHomePage) throws IOException {
        String propertiesPath = "C:\\Users\\rchepkunova\\IdeaProjects\\Tests\\src\\test\\resources\\test.properties";
        FileInputStream testPropertiesFile = new FileInputStream(propertiesPath);
        testProperties.load(testPropertiesFile);
        openBrowser();
        if (openHomePage) {
            driver.get(testProperties.getProperty("siteUrl"));
            homePage = PageFactory.initElements(driver, HomePage.class);
        }
    }

    private void openBrowser() throws IOException {
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        System.setProperty("webdriver.gecko.driver", testProperties.getProperty("geckoDir"));
        capabilities.setCapability("marionette", true);
        capabilities.setCapability("binary", "firefoxDir");
        driver = new MarionetteDriver(capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
}
