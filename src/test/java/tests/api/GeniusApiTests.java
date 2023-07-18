package tests.api;

import models.LoginRequestModel;
import models.LoginResponseModel;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.UserSpecs.*;
import static org.junit.jupiter.api.Assertions.*;

public class GeniusApiTests extends TestBase {
    public String token;
    public String authCookieKey = "Session-JWT";
    public String url = "https://radio.arzamas.academy";

    @Test
    @Tag("Api")
    public void testLogin() {
        LoginRequestModel.Data data = new LoginRequestModel().new Data();
        LoginRequestModel.Data.Attributes attributes = new LoginRequestModel().new Data().new Attributes();
        data.setType("email_identities");

        attributes.setEmail(userConfig.getEmail());
        attributes.setPassword(userConfig.getPassword());

        LoginRequestModel constructedRequestModel = new LoginRequestModel();
        constructedRequestModel.setData(data);
        data.setAttributes(attributes);
        step("Perform authentication", () -> {
            LoginResponseModel loginResponseModel = given(PostRequestSpecification)
                    .body(constructedRequestModel)
                    .when()
                    .post(url + "/api/v1/accounts/email_identities/sign_in")
                    .then()
                    .spec(PostResponseSpecification)
                    .extract().as(LoginResponseModel.class);
            step("Check response", () -> {
                assertNotNull(loginResponseModel.getSession_jwt());
                assertNotNull("authorized", loginResponseModel.getAction());
            });
            token = loginResponseModel.getSession_jwt();
        });
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
    public void passwordBeenChangedTest() {
        LoginRequestModel.Data data = new LoginRequestModel().new Data();
        LoginRequestModel.Data.Attributes attributes = new LoginRequestModel().new Data().new Attributes();
        data.setType("email_identities");

        attributes.setEmail(userConfig.getEmail());
        attributes.setPassword(userConfig.getNewPassword());

        LoginRequestModel constructedRequestModel = new LoginRequestModel();
        constructedRequestModel.setData(data);
        data.setAttributes(attributes);
        step("Perform authentication", () -> {
            LoginResponseModel loginResponseModel = given(PostRequestSpecification)
                    .body(constructedRequestModel)
                    .when()
                    .post(url + "/api/v1/accounts/email_identities/sign_in")
                    .then()
                    .spec(PostResponseSpecification)
                    .extract().as(LoginResponseModel.class);
            step("Check response", () -> {
                assertNotNull(loginResponseModel.getSession_jwt());
                assertNotNull("authorized", loginResponseModel.getAction());
            });
        });
    }
}
