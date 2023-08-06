package tests.mobile;

import com.codeborne.selenide.Selenide;
import components.HamburgerBar;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.mobile.OnboardingPage;
import tests.mobile.TestBaseMobile;

public class OnboardingTest extends TestBaseMobile {
    OnboardingPage onboardingPage = new OnboardingPage();
    HamburgerBar hamburgerBar = new HamburgerBar();
    @Test
    @Tag("mobile")
    @DisplayName("Complete onboarding without authorisation")
    public void testMobileOnboarding(){
        onboardingPage.clickInWhatSenseButton()
                .clickClassWhatElseButton()
                .clickNiceButton()
                .clickUnderstandButton()
                .clickNoButton()
                .clickSkipButton()
                .clickGoButton();
        hamburgerBar.clickOnMaterialsButton();
                //.checkContentAppears();
        }
}
