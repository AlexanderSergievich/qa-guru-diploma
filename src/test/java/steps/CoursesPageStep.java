package steps;

import io.qameta.allure.Step;
import pages.CoursesPage;

public class CoursesPageStep {
    CoursesPage coursesPage = new CoursesPage();
    @Step("Open courses page")
    public CoursesPageStep openCoursesPageStep(){
        coursesPage.openCoursesPage();
        return this;
    }
    @Step("Click all types button")
    public CoursesPageStep clickAllCourseTypesStep(){
        coursesPage.clickAllTypesButton();
        return this;
    }
    @Step("Click all genres button")
    public CoursesPageStep clickAllCourseGenresStep(){
        coursesPage.clickAllGenresButton();
        return this;
    }
    @Step("Find course by name")
    public CoursesPageStep findCourseByNameStep(String courseName){
        coursesPage.findCourseByName(courseName);
        return this;
    }
    @Step("Check course title")
    public CoursesPageStep checkCourseTitleStep(String courseName){
        coursesPage.checkCourseTitle(courseName);
        return this;
    }
}
