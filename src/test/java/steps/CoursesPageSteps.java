package steps;

import io.qameta.allure.Step;
import pages.web.CoursesPage;

public class CoursesPageSteps {
    CoursesPage coursesPage = new CoursesPage();

    @Step("Open courses page")
    public CoursesPageSteps openCoursesPageStep() {
        coursesPage.openCoursesPage();
        return this;
    }

    @Step("Click all types button")
    public CoursesPageSteps clickAllCourseTypesStep() {
        coursesPage.clickAllTypesButton();
        return this;
    }

    @Step("Click all genres button")
    public CoursesPageSteps clickAllCourseGenresStep() {
        coursesPage.clickAllGenresButton();
        return this;
    }

    @Step("Find course '{courseName}'")
    public CoursesPageSteps findCourseByNameStep(String courseName) {
        coursesPage.findCourseByName(courseName);
        return this;
    }

    @Step("Check course '{courseName}'")
    public CoursesPageSteps checkCourseTitleStep(String courseName) {
        coursesPage.checkCourseTitle(courseName);
        return this;
    }
}
