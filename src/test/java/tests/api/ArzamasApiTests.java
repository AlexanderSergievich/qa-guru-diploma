package tests.api;

import com.github.javafaker.Faker;
import models.UserRequestModel;
import models.UserResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.UserSpecs.*;
import static org.junit.jupiter.api.Assertions.*;

public class ArzamasApiTests extends TestBase {
    public String token;
    public String authCookieKey = "Session-JWT";
    public String url = "https://radio.arzamas.academy";
    UserRequestModel.Data data = new UserRequestModel().new Data();
    UserRequestModel.Data.Attributes attributes = new UserRequestModel().new Data().new Attributes();
    @Test
    @Tag("Api")
    @DisplayName("Sign in user")
    public void testSignIn() {

        data.setType("email_identities");
        attributes.setEmail(userConfig.getEmail());
        attributes.setPassword(userConfig.getPassword());

        UserRequestModel constructedRequestModel = new UserRequestModel();
        constructedRequestModel.setData(data);
        data.setAttributes(attributes);
        step("Perform authentication", () -> {
            UserResponseModel userResponseModel = given(SignInPostRequestSpecification)
                    .body(constructedRequestModel)
                    .when()
                    .post(url + "/api/v1/accounts/email_identities/sign_in")
                    .then()
                    .spec(PostResponseSpecification)
                    .extract().as(UserResponseModel.class);
            step("Check response", () -> {
                assertNotNull(userResponseModel.getSession_jwt());
                assertNotNull("authorized", userResponseModel.getAction());
            });
            step("Perform authorisation", () ->{
                token = userResponseModel.getSession_jwt();
                testPerformAuthorisation();
            });
        });
    }
    @Test
    @Tag("Api")
    @DisplayName("Perform authorisation via jwt token")
    public void testPerformAuthorisation(){
        step("Perform authorisation", () -> {
            given(GetRequestSpecification)
                    .header(authCookieKey, token)
                    .when()
                    .get(url + "/api/v1/account?include=subscriptions,phone_identity,facebook_identity,google_identity,vk_identity,apple_identity")
                    .then()
                    .spec(GetResponseSpecification);
        });
    }
    @Test
    @Tag("Api")
    @DisplayName("Sign up user without marketing distribution agreement")
    public void testSignUp(){
        data.setType("email_identities");
        attributes.setEmail(faker.internet().emailAddress());
        attributes.setPassword(faker.internet().password());
        attributes.setName(faker.name().firstName() + " " + faker.name().lastName());
        attributes.setNewsletter(false);
        UserRequestModel constructedUserRequestModel = new UserRequestModel();
        constructedUserRequestModel.setData(data);
        data.setAttributes(attributes);
        step("Sign up with random user data", () ->{
            UserResponseModel userResponseModel = given(SignUpPostRequestSpecification)
                    .body(constructedUserRequestModel)
                    .when()
                    .post("/api/v1/accounts/email_identities/sign_up")
                    .then()
                    .spec(PostResponseSpecification)
                    .extract().as(UserResponseModel.class);
            step("Check response", () -> {
                assertNotNull(userResponseModel.getSession_jwt());
                assertNotNull("authorized", userResponseModel.getAction());
            });
            step("Perform authorisation", () ->{
                token = userResponseModel.getSession_jwt();
                testPerformAuthorisation();
            });
        });
    }
    @Test
    @Tag("Api")
    @DisplayName("Sign in to verify password has been changed")
    public void passwordBeenChangedTest() {
        UserRequestModel.Data data = new UserRequestModel().new Data();
        UserRequestModel.Data.Attributes attributes = new UserRequestModel().new Data().new Attributes();
        data.setType("email_identities");

        attributes.setEmail(userConfig.getEmail());
        attributes.setPassword(userConfig.getNewPassword());

        UserRequestModel constructedRequestModel = new UserRequestModel();
        constructedRequestModel.setData(data);
        data.setAttributes(attributes);
        step("Perform authentication", () -> {
            UserResponseModel userResponseModel = given(SignInPostRequestSpecification)
                    .body(constructedRequestModel)
                    .when()
                    .post(url + "/api/v1/accounts/email_identities/sign_in")
                    .then()
                    .spec(PostResponseSpecification)
                    .extract().as(UserResponseModel.class);
            step("Check response", () -> {
                assertNotNull(userResponseModel.getSession_jwt());
                assertNotNull("authorized", userResponseModel.getAction());
            });
        });
    }
}
