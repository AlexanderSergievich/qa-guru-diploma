package steps;

import io.qameta.allure.Step;
import pages.SearchPage;

public class SearchPageSteps {
    SearchPage searchPage = new SearchPage();
    @Step("Click on search button")
    public SearchPageSteps clickOnSearchStep(){
        searchPage.clickOnSearchButton();
        return this;
    }
    @Step("Enter course '{courseName}' in the search field")
    public SearchPageSteps enterCourseNameAndPressEnterStep(String courseName) {
        searchPage.enterCourseNameAndPushEnter(courseName);
        return this;
    }
    @Step("Find course '{courseName}' on page")
    public SearchPageSteps findCourseOnPageStep(String courseName) {
        searchPage.findCourseOnAPage(courseName);
        return this;
    }
}
