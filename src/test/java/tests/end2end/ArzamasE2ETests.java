package tests.end2end;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.api.ArzamasApiTests;
import org.openqa.selenium.Cookie;
import tests.TestBase;
import utils.RandomUtils;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;

public class ArzamasE2ETests extends TestBase {
    String globalAuthCookieKey = "globalSessionJwt";
    String csvFile = "src/test/resources/testdata/courses.csv";

    String course = RandomUtils.getRandomValueFromCSV(csvFile);

    @Test
    @Tag("you")
    @DisplayName("Change password")
    public void testChangePassword() {
        ArzamasApiTests arzamasApiTests = new ArzamasApiTests();
        step("Log in via Api", () -> {
            arzamasApiTests.testSignIn();
            open("/favicon-32x32.png");
            Cookie authCookie = new Cookie(globalAuthCookieKey, arzamasApiTests.token);
            getWebDriver().manage().addCookie(authCookie);
        });
        accountPageSteps.openAccountPageStep()
                .setOldPasswordStep()
                .setNewPasswordStep()
                .repeatNewPasswordStep()
                .ClickChangePasswordStep()
                .checkInputsAreEmptyStep();
        step("Validate password have been changed", () ->
                arzamasApiTests.testPasswordBeenChanged());
    }

    @Test
    @Tag("you")
    @DisplayName("Find course by name")
    public void testFindCourseByName() {
            coursesPageSteps.openCoursesPageStep()
                    .clickAllCourseTypesStep()
                    .clickAllCourseGenresStep()
                    .findCourseByNameStep(course)
                    .checkCourseTitleStep(course);
    }
    @Test
    @Tag("e2e")
    @DisplayName("Buy as a Gift flow without payment")
    public void testBuyAsAGift(){
        open("/");
        giftPageSteps.clickOnGiftButtonStep()
                .clickOnRadioButtonStep()
                .chooseSubDurationStep()
                .setEmailStep(userConfig.getEmail())
                .setConfirmEmailStep(userConfig.getEmail())
                .clickBuyButtonStep()
                .checkPayButtonStep();
    }
    //тест на поиск
}