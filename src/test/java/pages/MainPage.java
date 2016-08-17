package pages;

//import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

/**
 * Created by rchepkunova on 17.08.2016.
 */
public class MainPage {

    WebDriver driver;

    @BeforeTest
    public void setup(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://autoqa.pp.ua/wp-login.php");
    }

    @Test(priority=0)
    public void test_Home_Page_Appear_Correct(){
        }
}
