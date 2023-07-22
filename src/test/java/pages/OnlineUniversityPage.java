package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byText;


public class OnlineUniversityPage {
    SelenideElement ellipsisButton = $(".site-header__subnav-button");
    SelenideElement universityLink = $("[href='/university']");
    SelenideElement universityTitle = $(byText("История русской культуры"));
    SelenideElement moduleTitle = $(".course-title");
    public OnlineUniversityPage clickOnEllipsisButton() {
        ellipsisButton.click();
        return this;
    }

    public OnlineUniversityPage clickOnUniversityLink() {
        universityLink.click();
        return this;
    }
    public OnlineUniversityPage verifyPageTitle() {
        universityTitle.should(Condition.exist);
        return this;
    }
    public OnlineUniversityPage findModuleByName(String moduleName) {
        $(byText(moduleName)).scrollIntoView(true).parent().parent().preceding(0).click();
        return this;
    }

    public OnlineUniversityPage verifyModuleTitle(String moduleName) {
        moduleTitle.shouldHave(Condition.text(moduleName));
        return this;
    }
}
