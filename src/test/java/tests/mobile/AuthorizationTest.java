package tests.mobile;

import components.HamburgerBar;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class AuthorizationTest extends TestBaseMobile {
    HamburgerBar hamburgerBar = new HamburgerBar();
    OnboardingTest onboardingTest = new OnboardingTest();
    @Test
    @Tag("support")
    @DisplayName("Perform authorization")
    public void testAuthorisation(){
        hamburgerBar.clickOnBurgerButton();
    }
}
