package tests.end2end;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import utils.RandomUtils;

import static com.codeborne.selenide.Selenide.open;

public class SearchTests extends TestBaseEnd2End {
    String csvCourses = "src/test/resources/testdata/courses.csv";
    String csvModules = "src/test/resources/testdata/modules.csv";
    String course = RandomUtils.getRandomValueFromCSV(csvCourses);
    String module = RandomUtils.getRandomValueFromCSV(csvModules);

    @Test
    @Tag("e2e")
    @DisplayName("Use search field to find course")
    public void testSearchField() {
        open("/");
        searchPageSteps.clickOnSearchStep()
                .enterCourseNameAndPressEnterStep(course)
                .findCourseOnPageStep(course);
    }

    @Test
    @Tag("e2e")
    @DisplayName("Find course by name using courses tab")
    public void testFindCourseByName() {
        coursesPageSteps.openCoursesPageStep()
                .clickAllCourseTypesStep()
                .clickAllCourseGenresStep()
                .findCourseByNameStep(course)
                .checkCourseTitleStep(course);
    }

    @Test
    @Tag("e2e")
    @DisplayName("Find online university course about history of russian culture by name")
    public void testFindUniversityCourse() {
        open("/");
        onlineUniversityPageSteps.clickOnEllipsisButtonStep()
                .clickOnUniversityLinkStep()
                .verifyPageTitleStep()
                .findModuleByNameStep(module)
                .verifyModuleTitleStep(module);
    }
}
