package tests.end2end;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import tests.api.AuthorizationAPITests;
import utils.RequestsUtils;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;

public class ProfileTests extends TestBaseEnd2End {
    String globalAuthCookieKey = "globalSessionJwt";

    @Test
    @Tag("e2e")
    @DisplayName("Change password")
    public void testChangePassword() {
        AuthorizationAPITests authorizationAPITests = new AuthorizationAPITests();
        RequestsUtils requestsUtils = new RequestsUtils();

        step("Log in via Api", () -> {
            authorizationAPITests.testSignInWithOldPassword();
            open("/favicon-32x32.png");
            Cookie authCookie = new Cookie(globalAuthCookieKey, authorizationAPITests.token);
            getWebDriver().manage().addCookie(authCookie);
        });
        accountPageSteps.openAccountPageStep()
                .setOldPasswordStep(userConfig.password())
                .setNewPasswordStep(userConfig.newPassword())
                .repeatNewPasswordStep(userConfig.newPassword())
                .clickChangePasswordStep()
                .checkInputsAreEmptyStep();

        step("Validate password have been changed", () ->
                requestsUtils.passwordBeenChanged());

        step("Change password back", () ->
                requestsUtils.updatePasswordToOld());
    }
}
