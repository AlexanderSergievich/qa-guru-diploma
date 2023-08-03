package tests.mobile;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.AppiumDriver;
import drivers.BrowserStackDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;

public class TestBaseMobile {
    public static String deviceHost = System.getProperty("deviceHost");

    @BeforeAll
    static void beforeAll() {
        if (deviceHost == null) {
            deviceHost = "android";
        }

        switch (deviceHost) {
            case "browserstack":
                Configuration.browser = BrowserStackDriver.class.getName();
                break;
            case "android":
                Configuration.browser = AppiumDriver.class.getName();
                break;
        }
        Configuration.browserSize = null;
        Configuration.browserBinary = "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome";
        open();
    }
    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void afterEach() {
        String sessionId = sessionId().toString();
        Attach.pageSource();
        closeWebDriver();
        if (!deviceHost.equals("android")) {
            Attach.addVideoMobile(sessionId);
        }
    }
}
