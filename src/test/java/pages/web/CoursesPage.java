package pages.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoursesPage {
    private static final Logger LOGGER = LoggerFactory.getLogger(CoursesPage.class);
    SelenideElement allTypesButton = $(".kind-courses__filters.fade").$$(byTagName("button")).first(),
            allGenresButton = $(".catalog-courses__filters.fade").$$(byTagName("button")).first(),
            courseTitle = $(".course-title");
    public CoursesPage openCoursesPage(){
        open("/courses?kind=courses");
        return this;
    }
    public CoursesPage clickAllTypesButton(){
        allTypesButton.click();
        return this;
    }
    public CoursesPage clickAllGenresButton(){
        $(allGenresButton).click();
        return this;
    }
    public CoursesPage findCourseByName(String courseName){
        LOGGER.info("course name is {}", courseName);
        $(byText(courseName)).click();
        return this;
    }
    public CoursesPage checkCourseTitle(String courseName){
        courseTitle.shouldHave(Condition.text(courseName));
        return this;
    }
}
