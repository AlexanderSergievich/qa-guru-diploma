package pages.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selectors.byText;

public class SearchPage {
    private SelenideElement searchButton = $("[aria-label='Поиск']");
    private SelenideElement searchField = $("#search");

    public SearchPage clickOnSearchButton() {
        searchButton.click();
        return this;
    }

    public SearchPage enterCourseNameAndPushEnter(String courseName) {
        searchField.setValue(courseName).sendKeys(Keys.ENTER);
        return this;
    }

    public SearchPage findCourseOnAPage(String courseName) {
        $(byText(courseName)).should(Condition.exist).click();
        return this;
    }
}
