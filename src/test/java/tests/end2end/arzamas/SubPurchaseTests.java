package tests.end2end.arzamas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static com.codeborne.selenide.Selenide.open;

public class SubPurchaseTests extends TestBase {
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
}
