package tests.end2end;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.api.GeniusApiTests;
import org.openqa.selenium.Cookie;
import tests.TestBase;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;

public class GeniusE2ETests extends TestBase {
    String globalAuthCookieKey = "globalSessionJwt";

    @Test
    @Tag("e2e")
    public void changePasswordTest() {
        GeniusApiTests geniusApiTests = new GeniusApiTests();
        step("Log in via Api", () -> {
            geniusApiTests.testLogin();
            open("/favicon-32x32.png");
            Cookie authCookie = new Cookie(globalAuthCookieKey, geniusApiTests.token);
            getWebDriver().manage().addCookie(authCookie);
        });
        accountPageSteps.openAccountPageStep()
                .setOldPasswordStep()
                .setNewPasswordStep()
                .repeatNewPasswordStep()
                .ClickChangePasswordStep()
                .checkInputsAreEmptyStep();
        step("Validate password have been changed", () ->
                geniusApiTests.passwordBeenChangedTest());

    }
}
