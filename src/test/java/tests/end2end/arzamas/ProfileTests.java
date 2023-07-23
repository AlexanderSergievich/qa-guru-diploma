package tests.end2end.arzamas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import tests.TestBase;
import tests.api.arzamas.AuthorizationAPITests;
import tests.api.arzamas.UserProfileAPITests;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;

public class ProfileTests extends TestBase {
    String globalAuthCookieKey = "globalSessionJwt";
    @Test
    @Tag("e2e")
    @DisplayName("Change password")
    public void testChangePassword() {
        AuthorizationAPITests authorizationAPITests = new AuthorizationAPITests();
        UserProfileAPITests userProfileAPITests = new UserProfileAPITests();
        step("Log in via Api", () -> {
            authorizationAPITests.testSignInWithOldPassword();
            open("/favicon-32x32.png");
            Cookie authCookie = new Cookie(globalAuthCookieKey, authorizationAPITests.token);
            getWebDriver().manage().addCookie(authCookie);
        });
        accountPageSteps.openAccountPageStep()
                .setOldPasswordStep(userConfig.getPassword())
                .setNewPasswordStep(userConfig.getNewPassword())
                .repeatNewPasswordStep(userConfig.getNewPassword())
                .ClickChangePasswordStep()
                .checkInputsAreEmptyStep();
        step("Validate password have been changed", () ->
                authorizationAPITests.testPasswordBeenChanged());
        step("Change password back", () ->
                userProfileAPITests.testUpdatePasswordToOld());
    }
}
