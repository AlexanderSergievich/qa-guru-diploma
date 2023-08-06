package components;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;

import static com.codeborne.selenide.Selenide.$;

public class HamburgerBar {
    String bounds = "[47,185][100,237]";
    SelenideElement hamburgerButton = $(AppiumBy
            .xpath("(//android.widget.TextView)[1]")),
            materialsButton = $(AppiumBy.xpath("//*[contains(@text, 'Материалы')]"));


    public HamburgerBar clickOnBurgerButton() {
        hamburgerButton.click();
        return this;
    }

    public HamburgerBar clickOnMaterialsButton() {
        materialsButton.click();
        return this;
    }

}
