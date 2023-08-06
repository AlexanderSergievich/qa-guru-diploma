package tests.end2end;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.UserConfig;
import config.WebDriverConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import steps.*;

import java.util.Map;

public class TestBaseEnd2End {
    public static UserConfig userConfig = ConfigFactory.create(UserConfig.class, System.getProperties());
    public static AccountPageSteps accountPageSteps = new AccountPageSteps();
    public static CoursesPageSteps coursesPageSteps = new CoursesPageSteps();
    public static GiftPageSteps giftPageSteps = new GiftPageSteps();
    public static SearchPageSteps searchPageSteps = new SearchPageSteps();
    public static OnlineUniversityPageSteps onlineUniversityPageSteps = new OnlineUniversityPageSteps();

    @BeforeAll
    static void setup() {
        WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
        if (config.isRemoteWebDriver()) {
            Configuration.remote = String.format("https://%s:%s@%s/wd/hub", config.login(), config.password(), config.remoteURL());

            Configuration.browser = config.browser();
            Configuration.browserVersion = config.browserVersion();
            Configuration.pageLoadStrategy = "eager";
            Configuration.baseUrl = config.baseUrl();
            Configuration.browserSize = config.browserSize();
        } else {
            Configuration.baseUrl = config.baseUrl();
            Configuration.browser = config.browser();
            Configuration.browserBinary = config.browserBinary();
        }
        RestAssured.baseURI = "https://radio.arzamas.academy";
        Configuration.timeout = 15000;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));

        Configuration.browserCapabilities = capabilities;
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
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

    @AfterEach
    void closeBrowserBeforeNewTest() {
        Selenide.closeWebDriver();
    }
}
