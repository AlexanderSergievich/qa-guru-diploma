package tests.api;

import models.UserPatchRequestModel;
import models.UserPatchResponseModel;
import models.UserPostRequestModel;
import models.UserPostResponseModel;
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
    UserPostRequestModel.Data postData = new UserPostRequestModel().new Data();
    UserPostRequestModel.Data.Attributes postAttributes = new UserPostRequestModel().new Data().new Attributes();

    UserPatchRequestModel.Data patchData = new UserPatchRequestModel().new Data();
    UserPatchRequestModel.Data.Attributes patchAttributes = new UserPatchRequestModel().new Data().new Attributes();
    @Test
    @Tag("Api")
    @DisplayName("Sign in user")
    public String testSignIn() {

        postData.setType("email_identities");
        postAttributes.setEmail(userConfig.getEmail());
        postAttributes.setPassword(userConfig.getPassword());

        UserPostRequestModel constructedRequestModel = new UserPostRequestModel();
        constructedRequestModel.setData(postData);
        postData.setAttributes(postAttributes);
        step("Perform authentication", () -> {
            UserPostResponseModel userPostResponseModel = given(SignInPostRequestSpecification)
                    .body(constructedRequestModel)
                    .when()
                    .post(url + "/api/v1/accounts/email_identities/sign_in")
                    .then()
                    .spec(PostResponseSpecification)
                    .extract().as(UserPostResponseModel.class);
            step("Check response", () -> {
                assertNotNull(userPostResponseModel.getSession_jwt());
                assertNotNull("authorized", userPostResponseModel.getAction());
            });
            step("Perform authorisation", () ->{
                token = userPostResponseModel.getSession_jwt();
                testPerformAuthorisation();
            });
        });
        return token;
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
        postData.setType("email_identities");
        postAttributes.setEmail(faker.internet().emailAddress());
        postAttributes.setPassword(faker.internet().password());
        postAttributes.setName(faker.name().firstName() + " " + faker.name().lastName());
        postAttributes.setNewsletter(false);
        UserPostRequestModel constructedUserPostRequestModel = new UserPostRequestModel();
        constructedUserPostRequestModel.setData(postData);
        postData.setAttributes(postAttributes);
        step("Sign up with random user postData", () ->{
            UserPostResponseModel userPostResponseModel = given(SignUpPostRequestSpecification)
                    .body(constructedUserPostRequestModel)
                    .when()
                    .post("/api/v1/accounts/email_identities/sign_up")
                    .then()
                    .spec(PostResponseSpecification)
                    .extract().as(UserPostResponseModel.class);
            step("Check response", () -> {
                assertNotNull(userPostResponseModel.getSession_jwt());
                assertNotNull("authorized", userPostResponseModel.getAction());
            });
            step("Perform authorisation", () ->{
                token = userPostResponseModel.getSession_jwt();
                testPerformAuthorisation();
            });
        });
    }
    @Test
    @Tag("Api")
    @DisplayName("Sign in to verify password has been changed")
    public void testPasswordBeenChanged() {
        UserPostRequestModel.Data data = new UserPostRequestModel().new Data();
        UserPostRequestModel.Data.Attributes attributes = new UserPostRequestModel().new Data().new Attributes();
        data.setType("email_identities");

        attributes.setEmail(userConfig.getEmail());
        attributes.setPassword(userConfig.getNewPassword());

        UserPostRequestModel constructedRequestModel = new UserPostRequestModel();
        constructedRequestModel.setData(data);
        data.setAttributes(attributes);
        step("Perform authentication", () -> {
            UserPostResponseModel userPostResponseModel = given(SignInPostRequestSpecification)
                    .body(constructedRequestModel)
                    .when()
                    .post(url + "/api/v1/accounts/email_identities/sign_in")
                    .then()
                    .spec(PostResponseSpecification)
                    .extract().as(UserPostResponseModel.class);
            step("Check response", () -> {
                assertNotNull(userPostResponseModel.getSession_jwt());
                assertNotNull("authorized", userPostResponseModel.getAction());
            });
        });
    }
    @Test
    @Tag("Api")
    @DisplayName("Update newsletter distribution")
    public void testUpdateNewsDistribution(){
        step("Perform authorisation and get token", () -> {
            String jwtToken = testSignIn();
            step("Perform update", () -> {
                patchData.setType("email_identities");
                patchAttributes.setEmail(userConfig.getEmail());
                patchAttributes.setNewsletter(true);

                UserPatchRequestModel constructedRequestModel = new UserPatchRequestModel();
                constructedRequestModel.setData(patchData);
                patchData.setAttributes(patchAttributes);

                UserPatchResponseModel userPatchResponseModel = given(patchRequestSpecification)
                        .body(constructedRequestModel)
                        .header("Session-jwt", jwtToken)
                        .when()
                        .patch("/api/v1/accounts/email_identities/update")
                        .then()
                        .spec(PostResponseSpecification)
                        .extract().as(UserPatchResponseModel.class);
                step("Check response", () -> {
                    assertEquals("success", userPatchResponseModel.getStatus());
                    assertEquals("Изменения сохранены", userPatchResponseModel.getMessage());
                    assertNull(userPatchResponseModel.getError());
                    assertArrayEquals(new String[]{}, userPatchResponseModel.getErrors());
                });
            });
        });
    }
}
