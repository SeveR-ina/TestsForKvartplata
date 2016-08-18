package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by rchepkunova on 18.08.2016.
 */
public abstract class BasePage {
    private final WebDriver driver;

    private final static Long DEFAULT_TIMEOUT_IN_SECONDS = 10L;

    BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement waitForVisibilityOf(WebElement webElement, long timeOutInSeconds) {
        return new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.visibilityOf(webElement));
    }

    public WebElement waitForVisibilityOfElementLocated(By locator, long timeOutInSeconds) {
        return new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForVisibilityOf(WebElement webElement) {
        return waitForVisibilityOf(webElement, DEFAULT_TIMEOUT_IN_SECONDS);
    }

    public WebElement waitForVisibilityOfElementLocated(By locator) {
        return waitForVisibilityOfElementLocated(locator, DEFAULT_TIMEOUT_IN_SECONDS);
    }

    public boolean waitForInvisibilityOfElementLocated(By locator, long timeOutInSeconds) {
        return new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public boolean waitForStalenessOf(WebElement webElement, long timeOutInSeconds) {
        return new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.stalenessOf(webElement));
    }

    public boolean waitForTextToBePresentInElement(By locator, String text, long timeOutInSeconds) {
        return new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.textToBePresentInElement(locator, text));
    }

    public boolean waitForTextToBePresentInElement(By locator, String text) {
        return waitForTextToBePresentInElement(locator, text, DEFAULT_TIMEOUT_IN_SECONDS);
    }

    public boolean waitForTextToBePresentInElement(WebElement webElement, String text, long timeOutInSeconds) {
        return new WebDriverWait(driver, timeOutInSeconds).until(ExpectedConditions.textToBePresentInElement(webElement, text));
    }

    public boolean waitForTextToBePresentInElement(WebElement webElement, String text) {
        return waitForTextToBePresentInElement(webElement, text, DEFAULT_TIMEOUT_IN_SECONDS);
    }
}
