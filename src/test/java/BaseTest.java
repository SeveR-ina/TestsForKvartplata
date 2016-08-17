/**
 * Created by rchepkunova on 17.08.2016.
 */

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


    @RunWith(Parameterized.class)
    public abstract class BaseTest {

        protected final DesiredCapabilities capabilities;
        protected final WebDriver browser;
        protected final WebDriver augmentedBrowser;
        protected final String baseUrl;
        protected final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS");
        private final static Long DEFAULT_TIMEOUT_IN_SECONDS = 10L;
        private boolean closeBrowser = true;

        public BaseTest(DesiredCapabilities capabilities) throws MalformedURLException {

            this.capabilities = capabilities;
            baseUrl = getBaseUrl();
            System.out.println("http://" + System.getProperty("integration.grid.hostname") + ":" + System.getProperty("integration.grid.port") + "/wd/hub");
            System.out.println(capabilities);
            browser = new RemoteWebDriver(new URL("http://" + System.getProperty("integration.grid.hostname") + ":" + System.getProperty("integration.grid.port") + "/wd/hub"), capabilities);

            // RemoteWebDriver does not implement the TakesScreenshot class
            // if the driver does have the Capabilities to take a screenshot
            // then Augmenter will add the TakesScreenshot methods to the instance
            augmentedBrowser = new Augmenter().augment(browser);
        }

        @Parameterized.Parameters(name = "{index}: {0}")
        public static LinkedList<DesiredCapabilities[]> getBrowsers() {
            LinkedList<DesiredCapabilities[]> browsers = new LinkedList<DesiredCapabilities[]>();

            browsers.add(new DesiredCapabilities[] {DesiredCapabilities.chrome()});
            //browsers.add(new DesiredCapabilities[] {DesiredCapabilities.internetExplorer()});
            browsers.add(new DesiredCapabilities[] {DesiredCapabilities.firefox()});

            return browsers;
        }

        protected long getImplicitlyWait() {
            return 10000;
        }

        protected abstract String getBaseUrl();

        @Deprecated
        protected abstract void login();

        @Deprecated
        protected abstract void logout();

        @Deprecated
        public boolean isElementPresent(By by) {
            try {
                browser.findElement(by);
                return true;
            }
            catch (NoSuchElementException e) {
                return false;
            }
        }

        public String getBrowserCode() {
            if (DesiredCapabilities.chrome().getBrowserName().equals(capabilities.getBrowserName()))
                return "CH";
            else if (DesiredCapabilities.firefox().getBrowserName().equals(capabilities.getBrowserName()))
                return "FF";
            if (DesiredCapabilities.internetExplorer().getBrowserName().equals(capabilities.getBrowserName()))
                return "IE";

            throw new RuntimeException("unsupported browser");
        }

        /**
         * @deprecated
         * TODO: this is duplicated code. Original in BasePage.
         */
        public boolean isAtUri(String uri) {
            return browser.getCurrentUrl().contains(uri);
        }

        /**
         * @deprecated
         * TODO: this is duplicated code. Original in BasePage.
         */
        public WebElement waitForVisibilityOf(WebElement webElement, long timeOutInSeconds) {
            return new WebDriverWait(browser, timeOutInSeconds).until(ExpectedConditions.visibilityOf(webElement));
        }

        /**
         * @deprecated
         * TODO: this is duplicated code. Original in BasePage.
         */
        public WebElement waitForVisibilityOfElementLocated(By locator, long timeOutInSeconds) {
            return new WebDriverWait(browser, timeOutInSeconds).until(ExpectedConditions.visibilityOfElementLocated(locator));
        }

        /**
         * @deprecated
         * TODO: this is duplicated code. Original in BasePage.
         */
        public WebElement waitForVisibilityOf(WebElement webElement) {
            return waitForVisibilityOf(webElement, DEFAULT_TIMEOUT_IN_SECONDS);
        }

        /**
         * @deprecated
         * TODO: this is duplicated code. Original in BasePage.
         */
        public WebElement waitForVisibilityOfElementLocated(By locator) {
            return waitForVisibilityOfElementLocated(locator, DEFAULT_TIMEOUT_IN_SECONDS);
        }

        /**
         * @deprecated
         * TODO: this is duplicated code. Original in BasePage.
         */
        public boolean waitForInvisibilityOfElementLocated(By locator, long timeOutInSeconds) {
            return new WebDriverWait(browser, timeOutInSeconds).until(ExpectedConditions.invisibilityOfElementLocated(locator));
        }

        /**
         * @deprecated
         * TODO: this is duplicated code. Original in BasePage.
         */
        public boolean waitForStalenessOf(WebElement webElement, long timeOutInSeconds) {
            return new WebDriverWait(browser, timeOutInSeconds).until(ExpectedConditions.stalenessOf(webElement));
        }

        /**
         * @deprecated
         * TODO: this is duplicated code. Original in BasePage.
         */
        public boolean waitForTextToBePresentInElement(By locator, String text, long timeOutInSeconds) {
            return new WebDriverWait(browser, timeOutInSeconds).until(ExpectedConditions.textToBePresentInElement(locator, text));
        }

        /**
         * @deprecated
         */
        public boolean waitForTextToBePresentInElement(By locator, String text) {
            return waitForTextToBePresentInElement(locator, text, DEFAULT_TIMEOUT_IN_SECONDS);
        }

        /**
         * @deprecated
         * TODO: this is duplicated code. Original in BasePage.
         */
        public boolean waitForTextToBePresentInElement(WebElement webElement, String text, long timeOutInSeconds) {
            return new WebDriverWait(browser, timeOutInSeconds).until(ExpectedConditions.textToBePresentInElement(webElement, text));
        }

        /**
         * @deprecated
         */
        public boolean waitForTextToBePresentInElement(WebElement webElement, String text) {
            return waitForTextToBePresentInElement(webElement, text, DEFAULT_TIMEOUT_IN_SECONDS);
        }
        //
        public boolean textToBePresentInElementLocated(By locator, String text, long timeOutInSeconds) {
            return new WebDriverWait(browser, timeOutInSeconds).until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
        }

        public boolean textToBePresentInElementLocated(By locator, String text) {
            return textToBePresentInElementLocated(locator, text, DEFAULT_TIMEOUT_IN_SECONDS);
        }
        /**
         * @deprecated
         * TODO: this is duplicated code. Original in BasePage.
         */
        public void sleep(long millis) {
            try {
                Thread.sleep(millis);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void setCloseBrowser(boolean closeBrowser) {
            this.closeBrowser = closeBrowser;
        }

        @Rule
        public TestRule testWatcher = new TestWatcher() {
            @Override
            protected void starting(Description description) {
                super.starting(description);

                browser.manage().window().maximize();
                browser.manage().timeouts().implicitlyWait(getImplicitlyWait(), TimeUnit.MILLISECONDS);
            }

            @Override
            protected void finished(Description description) {
                super.finished(description);

                if (closeBrowser)
                    browser.quit();
            }

        };


}
