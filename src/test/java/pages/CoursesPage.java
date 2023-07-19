package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Condition.*;


public class CoursesPage {
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
        $(byText(courseName)).shouldBe(interactable).click();
        return this;
    }
    public CoursesPage checkCourseTitle(String courseName){
        courseTitle.shouldHave(Condition.text(courseName));
        return this;
    }
}
