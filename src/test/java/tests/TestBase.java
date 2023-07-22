package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import config.UserConfig;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import steps.*;

public class TestBase {
    public UserConfig userConfig = ConfigFactory.create(UserConfig.class, System.getProperties());
    public static AccountPageSteps accountPageSteps = new AccountPageSteps();
    public static CoursesPageSteps coursesPageSteps = new CoursesPageSteps();
    public static GiftPageSteps giftPageSteps = new GiftPageSteps();
    public static SearchPageSteps searchPageSteps = new SearchPageSteps();
    public static OnlineUniversityPageSteps onlineUniversityPageSteps = new OnlineUniversityPageSteps();
    public static Faker faker = new Faker();

    @BeforeAll
    static void setup(){
        Configuration.timeout = 10000;
        RestAssured.baseURI = "https://radio.arzamas.academy";
        Configuration.baseUrl = "https://arzamas.academy";
    }
    @AfterAll
    static void closeBrowserBeforeNewTest(){
        Selenide.closeWebDriver();
    }
}
