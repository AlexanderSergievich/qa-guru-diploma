package tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import config.UserConfig;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import steps.AccountPageSteps;
import steps.CoursesPageStep;
import utils.RandomUtils;

public class TestBase {
    public UserConfig userConfig = ConfigFactory.create(UserConfig.class, System.getProperties());
    public static AccountPageSteps accountPageSteps = new AccountPageSteps();
    public static CoursesPageStep coursesPageStep = new CoursesPageStep();
    public static Faker faker = new Faker();

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://radio.arzamas.academy";
        Configuration.baseUrl = "https://arzamas.academy";
    }
}
