package tests.end2end;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.UserConfig;
import config.WebDriverConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import steps.*;

import java.util.Map;

public class TestBaseEnd2End {
    public UserConfig userConfig = ConfigFactory.create(UserConfig.class, System.getProperties());
    public static AccountPageSteps accountPageSteps = new AccountPageSteps();
    public static CoursesPageSteps coursesPageSteps = new CoursesPageSteps();
    public static GiftPageSteps giftPageSteps = new GiftPageSteps();
    public static SearchPageSteps searchPageSteps = new SearchPageSteps();
    public static OnlineUniversityPageSteps onlineUniversityPageSteps = new OnlineUniversityPageSteps();
    @BeforeAll
    static void setup(){
        WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
        if(config.isRemoteWebDriver()) {
            Configuration.browser = config.getBrowser();
            Configuration.browserVersion = config.getBrowserVersion();
            Configuration.pageLoadStrategy = "eager";
            Configuration.remote = config.getRemoteURL();
            Configuration.baseUrl = config.getBaseUrl();
            Configuration.browserSize = config.getBrowserSize();
        } else {
            Configuration.baseUrl = config.getBaseUrl();
            Configuration.browser = config.getBrowser();
        }

        Configuration.timeout = 10000;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));

        Configuration.browserCapabilities = capabilities;
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterEach
    void addAttachments() {
        WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
        if (config.isRemoteWebDriver()) {
            Attach.addVideo();
        }
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
    }
    @AfterAll
    static void closeBrowserBeforeNewTest(){
        Selenide.closeWebDriver();
    }
}
