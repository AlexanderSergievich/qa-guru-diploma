package pages.web;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class GiftPage {
    private SelenideElement giftButton = $(".site-header__get-gift-certificate");
    private SelenideElement emailField = $("#order_email");
    private SelenideElement confirmEmailField = $("#order_email_confirmation");
    private SelenideElement buyButton = $(".bp-form__button");
    private SelenideElement paymentIFrame = $("[allow='payment']");
    private SelenideElement payButton = $(".button.button_primary");
    private String subscriptionPrice = "[data-gift='1']";

    public GiftPage clickOnGiftButton() {
        giftButton.click();
        return this;
    }

    public GiftPage clickRadioButton(int radioButtonDataId) {
        $("[data-id='" + radioButtonDataId + "']").click();
        return this;
    }

    public GiftPage chooseSubDuration(int radioButtonDataId) {
        $("[data-id='" + radioButtonDataId + "']").find(subscriptionPrice).click();
        return this;
    }

    public GiftPage setEmail(String email) {
        emailField.setValue(email);
        return this;
    }

    public GiftPage setConfirmEmail(String email) {
        confirmEmailField.setValue(email);
        return this;
    }

    public GiftPage clickBuyButton() {
        buyButton.click();
        return this;
    }

    public GiftPage checkBuyButtonIsDisabled() {
        switchTo().frame(paymentIFrame);
        payButton.shouldBe(visible).shouldBe(disabled);
        return this;
    }
}
