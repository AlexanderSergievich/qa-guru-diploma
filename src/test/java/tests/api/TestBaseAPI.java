package tests.api;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.github.javafaker.Faker;
import config.UserConfig;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import steps.*;

public class TestBaseAPI {
    public UserConfig userConfig = ConfigFactory.create(UserConfig.class, System.getProperties());
    public static Faker faker = new Faker();

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://radio.arzamas.academy";
    }
}
