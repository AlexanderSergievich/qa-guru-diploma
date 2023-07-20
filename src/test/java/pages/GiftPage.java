package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.switchTo;

public class GiftPage {
    SelenideElement giftButton = $(".site-header__get-gift-certificate");
    SelenideElement emailField = $("#order_email");
    SelenideElement confirmEmailField = $("#order_email_confirmation");
    SelenideElement buyButton = $(".bp-form__button");
    SelenideElement paymentIFrame = $("[allow='payment']");
    SelenideElement payButton = $(".button.button_primary");

    private String radioButtonDataId = "2";
    private String durationOfSubscription = "2 года";
    public GiftPage clickOnGiftButton(){
        giftButton.click();
        return this;
    }
    public GiftPage clickRadioButton(){
        $("[data-id='" + radioButtonDataId + "']").click();
        return this;
    }
    public GiftPage chooseSubDuration(){
        $("[data-id='" + radioButtonDataId + "']").find(byText(durationOfSubscription)).click();
        return this;
    }
    public GiftPage setEmail(String email){
        emailField.setValue(email);
        return this;
    }
    public GiftPage setConfirmEmail(String email){
        confirmEmailField.setValue(email);
        return this;
    }
    public GiftPage clickBuyButton(){
        buyButton.click();
        return this;
    }
    public GiftPage checkBuyButtonIsDisabled(){
        switchTo().frame(paymentIFrame);
        payButton.shouldBe(visible).shouldBe(disabled);
        return this;
    }
}
