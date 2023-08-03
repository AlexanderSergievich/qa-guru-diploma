package tests.end2end.arzamas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.web.CoursesPage;
import tests.end2end.TestBaseEnd2End;

import static com.codeborne.selenide.Selenide.open;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SubPurchaseTests extends TestBaseEnd2End {
    private static final Logger LOGGER = LoggerFactory.getLogger(CoursesPage.class);
    @Test
    @Tag("e2e")
    @DisplayName("Buy as a Gift flow without payment")
    public void testBuyAsAGift(){
        LOGGER.info("log");
        open("/");
        giftPageSteps.clickOnGiftButtonStep()
                .clickOnRadioButtonStep()
                .chooseSubDurationStep()
                .setEmailStep(userConfig.getEmail())
                .setConfirmEmailStep(userConfig.getEmail())
                .clickBuyButtonStep()
                .checkPayButtonStep();
    }
}
