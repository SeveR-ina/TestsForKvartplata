package pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by rchepkunova on 18.08.2016.
 */
public class HomePage extends BasePage {

    @FindBy(className = "entrance")
    private WebElement registerLink;

    @FindBy(id = "phone")
    private WebElement phoneField;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isHomePageVisible() {
        waitForVisibilityOf(registerLink, 20);
        return registerLink.isDisplayed();
    }

    public boolean isRegisterPopUpVisible() {
        registerLink.click();
        try {
            waitForVisibilityOf(phoneField, 20);
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}
