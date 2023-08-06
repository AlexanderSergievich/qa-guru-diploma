package pages.mobile;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

import io.appium.java_client.AppiumBy;


public class OnboardingPage {
    SelenideElement inWhatSenseButton = $(AppiumBy.xpath("//*[contains(@text, 'В каком смысле?')]")),
            classWhatElseButton = $(AppiumBy.xpath("//*[contains(@text, 'Класс, что еще?')]")),
            niceButton = $(AppiumBy.xpath("//*[contains(@text, 'Отлично')]")),
            understandButton = $(AppiumBy.xpath("//*[contains(@text, 'Понятно')]")),
            noButton = $(AppiumBy.xpath("//*[contains(@text, 'Нее…')]")),
            skipButton = $(AppiumBy.xpath("//*[contains(@text, 'Пропустить')]")),
            goButton = $(AppiumBy.xpath("//*[contains(@text, 'Поехали!')]")),
            contentId = $(AppiumBy.id("android:id/content"));


    public OnboardingPage clickInWhatSenseButton() {
        inWhatSenseButton.click();
        return this;
    }

    public OnboardingPage clickClassWhatElseButton() {
        classWhatElseButton.click();
        return this;
    }

    public OnboardingPage clickNiceButton() {
        niceButton.click();
        return this;
    }

    public OnboardingPage clickUnderstandButton() {
        understandButton.click();
        return this;
    }

    public OnboardingPage clickNoButton() {
        noButton.click();
        return this;
    }

    public OnboardingPage clickSkipButton() {
        skipButton.click();
        return this;
    }

    public OnboardingPage clickGoButton() {
        goButton.click();
        return this;
    }

    public OnboardingPage checkContentAppears() {
        contentId.should(Condition.exist);
        return this;
    }
}
