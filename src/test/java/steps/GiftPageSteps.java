package steps;

import io.qameta.allure.Step;
import pages.GiftPage;

public class GiftPageSteps {
    GiftPage giftPage = new GiftPage();
    @Step("Click on gift button")
    public GiftPageSteps clickOnGiftButtonStep(){
        giftPage.clickOnGiftButton();
        return this;
    }
    @Step("Choose the subscription")
    public GiftPageSteps clickOnRadioButtonStep(){
        giftPage.clickRadioButton();
        return this;
    }
    @Step("Choose the duration of subscription")
    public GiftPageSteps chooseSubDurationStep(){
        giftPage.chooseSubDuration();
        return this;
    }
    @Step("Set email '{email}'")
    public GiftPageSteps setEmailStep(String email){
        giftPage.setEmail(email);
        return this;
    }
    @Step("Confirm email '{email}'")
    public GiftPageSteps setConfirmEmailStep(String email){
        giftPage.setConfirmEmail(email);
        return this;
    }
    @Step("Click buy button")
    public GiftPageSteps clickBuyButtonStep(){
        giftPage.clickBuyButton();
        return this;
    }
    @Step("Check whether buy button is displayed and disabled")
    public GiftPageSteps checkPayButtonStep(){
        giftPage.checkBuyButtonIsDisabled();
        return this;
    }
}

