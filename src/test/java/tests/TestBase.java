package tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import config.UserConfig;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import steps.AccountPageSteps;
import steps.CoursesPageSteps;
import steps.GiftPageSteps;

public class TestBase {
    public UserConfig userConfig = ConfigFactory.create(UserConfig.class, System.getProperties());
    public static AccountPageSteps accountPageSteps = new AccountPageSteps();
    public static CoursesPageSteps coursesPageSteps = new CoursesPageSteps();
    public static GiftPageSteps giftPageSteps = new GiftPageSteps();
    public static Faker faker = new Faker();

    @BeforeAll
    static void setup(){
        Configuration.timeout = 10000;
        RestAssured.baseURI = "https://radio.arzamas.academy";
        Configuration.baseUrl = "https://arzamas.academy";
    }
}
