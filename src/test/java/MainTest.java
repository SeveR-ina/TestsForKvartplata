import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import pages.MainPage;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by rchepkunova on 17.08.2016.
 */
public abstract class MainTest extends BaseTest {
    private String baseUrl = System.getProperty("integration.url");
    private final Properties config = new Properties();

    public MainTest(DesiredCapabilities capabilities, boolean openHomePage) throws IOException {
        super(capabilities);

        config.load(getClass().getResourceAsStream("C:\\Users\\rchepkunova\\IdeaProjects\\Tests\\src\\test\\resources"));

        if (openHomePage) {
            browser.get(baseUrl);
            MainPage mainPage = PageFactory.initElements(browser, MainPage.class);
            //homePage = loginPage.loginAs(config.getProperty("login"), config.getProperty("password"));
        }
    }

    public MainTest(DesiredCapabilities capabilities) throws IOException {
        this(capabilities, true);
    }

    @Override
    protected String getBaseUrl() {
        return baseUrl;
    }

    @Override
    protected void login() {
    }

    @Override
    protected void logout() {
    }
/*
    public ProjectsPage getHomePage() {
        return homePage;
    }*/

    public Properties getConfig() {
        return config;
    }
}
